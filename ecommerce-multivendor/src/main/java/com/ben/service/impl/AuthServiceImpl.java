package com.ben.service.impl;

import com.ben.config.JwtProvider;
import com.ben.domain.USER_ROLE;
import com.ben.modal.Cart;
import com.ben.modal.Seller;
import com.ben.modal.User;
import com.ben.modal.VerificationCode;
import com.ben.repository.CartRepository;
import com.ben.repository.SellerRepository;
import com.ben.repository.UserRepository;
import com.ben.repository.VerificationCodeRepository;
import com.ben.request.LoginRequest;
import com.ben.response.AuthResponse;
import com.ben.response.SignupRequest;
import com.ben.service.AuthService;
import com.ben.service.EmailService;
import com.ben.util.OtpUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final CartRepository cartRepository;
    private final JwtProvider jwtProvider;
    private final VerificationCodeRepository verificationCodeRepository;
    private final EmailService emailService;
    private final CustomUserServiceImpl customUserService;
    private final SellerRepository sellerRepository;

    @Override
    public void sentLoginOtp(String email, USER_ROLE role) throws Exception {
        String SIGNING_PREFIX = "signin_";
//        String SELLER_PREFIX="seller_";


        if(email.startsWith(SIGNING_PREFIX)){
            email=email.substring(SIGNING_PREFIX.length());

            if(role.equals(USER_ROLE.ROLE_SELLER)){
                Seller seller = sellerRepository.findByEmail(email);
                if(seller==null){
                    throw new Exception("seller not found - "+ email);
                }

            }

            else {
                User user = userRepository.findByEmail(email);
                if(user==null){
                    throw new Exception("user not exist with provided email - "+email);
                }
            }

        }

        VerificationCode isExist = verificationCodeRepository.findByEmail(email);
        if(isExist!=null){
            verificationCodeRepository.delete(isExist);
        }
        String otp = OtpUtil.generateOtp();

        VerificationCode verificationCode = new VerificationCode();
        verificationCode.setOtp(otp);
        verificationCode.setEmail(email);
        verificationCodeRepository.save(verificationCode);

        String subject = "Ecommerce login/signup otp";
        String text = "Your login/signup otp is -"+otp;

        emailService.sendVerificationOtpEmail(email, otp, subject, text);
    }

    @Override
    public String createUser(SignupRequest request) throws Exception {

        VerificationCode verificationCode = verificationCodeRepository.findByEmail(request.getEmail());
        System.out.println("request otp is " + request.getOtp() );
        System.out.println("Check conditions " + verificationCode.getOtp().equals(request.getOtp()) );

        if(verificationCode==null || !verificationCode.getOtp().equals(request.getOtp())){
            System.out.println("OTP error");
            throw new Exception("Wrong otp...");
        }

        User user = userRepository.findByEmail(request.getEmail());

        if(user==null){
            User createdUser = new User();
            createdUser.setEmail(request.getEmail());
            createdUser.setFullName(request.getFullName());
            createdUser.setRole(USER_ROLE.ROLE_CUSTOMER);
            createdUser.setMobile("8954632145");
            createdUser.setPassword(passwordEncoder.encode(request.getOtp()));

            user = userRepository.save(createdUser);

            Cart cart = new Cart();
            cart.setUser(user);
            cartRepository.save(cart);
        }

        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(USER_ROLE.ROLE_CUSTOMER.toString()));

        Authentication authentication = new UsernamePasswordAuthenticationToken(request.getEmail(), null, authorities);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return jwtProvider.generateToken(authentication);
    }

    @Override
    public AuthResponse signing(LoginRequest req) throws Exception {
        String username = req.getEmail();
        String otp = req.getOtp();


        Authentication authentication = authenticate(username, otp);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtProvider.generateToken(authentication);

        AuthResponse authResponse = new AuthResponse();
        authResponse.setJwt(token);
        authResponse.setMessage("login success");

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        String roleName = authorities.isEmpty()?null : authorities.iterator().next().getAuthority();

        authResponse.setRole(USER_ROLE.valueOf(roleName));
        return authResponse;
    }

    private Authentication authenticate(String username, String otp) throws Exception {
        UserDetails userDetails = customUserService.loadUserByUsername(username);
        String SELLER_PREFIX="seller_";


        if (username.startsWith(SELLER_PREFIX)){
            username = username.substring(SELLER_PREFIX.length());
        }

        if(userDetails==null){
            throw new BadCredentialsException("invalid username");
        }

        VerificationCode verificationCode = verificationCodeRepository.findByEmail(username);

        if(verificationCode==null || !verificationCode.getOtp().equals(otp)){
            throw new Exception("wrong otp");
        }

        return new UsernamePasswordAuthenticationToken(userDetails,
                null, userDetails.getAuthorities());
    }
}
