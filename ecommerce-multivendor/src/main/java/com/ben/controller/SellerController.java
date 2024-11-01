package com.ben.controller;

import com.ben.config.JwtProvider;
import com.ben.domain.AccountStatus;
import com.ben.domain.USER_ROLE;
import com.ben.exceptions.SellerException;
import com.ben.modal.Seller;
import com.ben.modal.SellerReport;
import com.ben.modal.VerificationCode;
import com.ben.repository.VerificationCodeRepository;
import com.ben.request.LoginRequest;
import com.ben.response.ApiResponse;
import com.ben.response.AuthResponse;
import com.ben.service.AuthService;
import com.ben.service.EmailService;
import com.ben.service.SellerReportService;
import com.ben.service.SellerService;
import com.ben.util.OtpUtil;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/sellers")
public class SellerController {
    private final SellerService sellerService;
    private final VerificationCodeRepository verificationCodeRepository;
    private final AuthService authService;
    private final EmailService emailService;
    private final JwtProvider jwtProvider;
    private final SellerReportService sellerReportService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> loginSeller(
            @RequestBody LoginRequest req
            ) throws Exception {
        String otp = req.getOtp();
        String email = req.getEmail();

        req.setEmail("seller_"+email);
        AuthResponse authResponse = authService.signing(req);
        return ResponseEntity.ok(authResponse);
    }

    @PatchMapping("/verify/{otp}")
    public ResponseEntity<Seller> verifySellerEmail(@PathVariable String otp) throws Exception{
        VerificationCode verificationCode = verificationCodeRepository.findByOtp(otp);
        if(verificationCode==null || !verificationCode.getOtp().equals(otp)){
            throw new Exception("wrong otp...");
        }

        Seller seller = sellerService.verifyEmail(verificationCode.getEmail(), otp);

        return new ResponseEntity<>(seller, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Seller> createSeller(@RequestBody Seller seller) throws Exception, MessagingException {
        Seller savedSeller = sellerService.createSeller(seller);

        String otp = OtpUtil.generateOtp();

        VerificationCode verificationCode = new VerificationCode();
        verificationCode.setOtp(otp);
        verificationCode.setEmail(seller.getEmail());
        verificationCodeRepository.save(verificationCode);

        String subject = "Ecommerce Email Verification Code";
        String text = "Your login/signup otp is -"+otp;

//        String text = "Welcome to Ecommerce, verify you account using this link ";
        String frontend_url = "http://localhost:3000/verify-seller/";

        emailService.sendVerificationOtpEmail(seller.getEmail(), otp, subject, text);
        return new ResponseEntity<>(savedSeller, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Seller> getSellerById(@PathVariable Long id) throws SellerException {
        Seller seller = sellerService.getSellerById(id);
        return new ResponseEntity<>(seller, HttpStatus.OK);
    }


    @GetMapping("/profile")
    public ResponseEntity<Seller> getSellerByJwt(
            @RequestHeader("Authorization") String jwt) throws Exception{
        Seller seller = sellerService.getSellerProfile(jwt);
        return new ResponseEntity<>(seller, HttpStatus.OK);
    }

    @GetMapping("/report")
    public ResponseEntity<SellerReport> getSellerReport(
            @RequestHeader("Authorization") String jwt) throws Exception{
        Seller seller = sellerService.getSellerProfile(jwt);
        SellerReport sellerReport = sellerReportService.getSellerReport(seller);
        return new ResponseEntity<>(sellerReport, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Seller>> getAllSellers(
            @RequestParam(required = false)AccountStatus status) throws Exception{
        List<Seller> sellers = sellerService.getAllSellers(status);
        return ResponseEntity.ok(sellers);
    }

    @PatchMapping()
    public ResponseEntity<Seller> updateSeller(
            @RequestHeader("Authorization") String jwt,
            @RequestBody Seller seller

    ) throws Exception{
        Seller profile = sellerService.getSellerProfile(jwt);
        Seller updateSeller = sellerService.updateSeller(profile.getId(), seller);
        return ResponseEntity.ok(updateSeller);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSeller(@PathVariable Long id) throws Exception{
        sellerService.deleteSeller(id);
        return ResponseEntity.noContent().build();
    }
}
