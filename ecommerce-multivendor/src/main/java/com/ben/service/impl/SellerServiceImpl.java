package com.ben.service.impl;

import com.ben.config.JwtProvider;
import com.ben.domain.AccountStatus;
import com.ben.domain.USER_ROLE;
import com.ben.exceptions.SellerException;
import com.ben.modal.Address;
import com.ben.modal.Seller;
import com.ben.repository.AddressRepository;
import com.ben.repository.SellerRepository;
import com.ben.service.SellerService;
import jakarta.persistence.Id;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SellerServiceImpl implements SellerService {

    private final SellerRepository sellerRepository;
    private final JwtProvider jwtProvider;
    private final PasswordEncoder passwordEncoder;
    private final AddressRepository addressRepository;

    @Override
    public Seller getSellerProfile(String jwt) throws Exception {
        String email = jwtProvider.getEmailFromJwtToken(jwt);
        return this.getSellerByEmail(email);
    }

    @Override
    public Seller createSeller(Seller seller) throws Exception {
        Seller sellerExist = sellerRepository.findByEmail(seller.getEmail());
        if(sellerExist!=null){
            throw new Exception("seller already exist, use different email ");
        }
        Address savedAddress = addressRepository.save(seller.getPickUpAddress());

        Seller newSeller = new Seller();
        newSeller.setEmail(seller.getEmail());
        newSeller.setPassword(passwordEncoder.encode(seller.getPassword()));
        newSeller.setSellerName(seller.getSellerName());
        newSeller.setPickUpAddress(savedAddress);
        newSeller.setGSTIN(seller.getGSTIN());
        newSeller.setRole(USER_ROLE.ROLE_SELLER);
        newSeller.setMobile(seller.getMobile());
        newSeller.setBankDetails(seller.getBankDetails());
        newSeller.setBusinessDetails(seller.getBusinessDetails());
        newSeller.setGSTIN(seller.getGSTIN());

        return  sellerRepository.save(newSeller);
    }

    @Override
    public Seller getSellerById(Long id) throws SellerException {
        return sellerRepository.findById(id)
                .orElseThrow(()-> new SellerException("seller not found... "));
    }

    @Override
    public Seller getSellerByEmail(String email) throws Exception {
        Seller seller = sellerRepository.findByEmail(email);
        if(seller==null){
            throw new Exception("Seller Not found ...");
        }
        return seller;
    }

    @Override
    public List<Seller> getAllSellers(AccountStatus status) {
        return sellerRepository.findByAccountStatus(status);
    }

    @Override
    public Seller updateSeller(Long id, Seller seller) throws Exception {
        Seller existingSeller = this.getSellerById(id);

        if(seller.getSellerName()!=null){
            existingSeller.setSellerName(seller.getSellerName());
        }
        if(seller.getMobile()!=null){
            existingSeller.setMobile(seller.getMobile());
        }
        if(seller.getEmail()!=null){
            existingSeller.setEmail(seller.getEmail());
        }
        if(seller.getBusinessDetails()!=null
                && seller.getBusinessDetails().getBusinessName()!=null){

            existingSeller.getBusinessDetails().setBusinessName(
                    seller.getBusinessDetails().getBusinessName());
        }

        if(seller.getBankDetails()!=null
            && seller.getBankDetails().getAccountHolderName()!=null
            && seller.getBankDetails().getIfscCode()!=null
            && seller.getBankDetails().getAccountNumber()!=null)
        {
            existingSeller.getBankDetails().setAccountHolderName(
                    seller.getBankDetails().getAccountHolderName());

            existingSeller.getBankDetails().setIfscCode(
                    seller.getBankDetails().getIfscCode());

            existingSeller.getBankDetails().setAccountNumber(
                    seller.getBankDetails().getAccountNumber());
        }

        if(seller.getPickUpAddress()!=null
            && seller.getPickUpAddress().getAddress()!=null
            && seller.getPickUpAddress().getMobile()!=null
            && seller.getPickUpAddress().getCity()!=null
            && seller.getPickUpAddress().getState()!=null
            && seller.getPickUpAddress().getPinCode()!=null
        ){
            existingSeller.getPickUpAddress().setAddress(seller.getPickUpAddress().getAddress());
            existingSeller.getPickUpAddress().setMobile(seller.getPickUpAddress().getMobile());
            existingSeller.getPickUpAddress().setCity(seller.getPickUpAddress().getCity());
            existingSeller.getPickUpAddress().setState(seller.getPickUpAddress().getState());
            existingSeller.getPickUpAddress().setPinCode(seller.getPickUpAddress().getPinCode());
        }
        if(seller.getGSTIN()!=null){
            existingSeller.setGSTIN(seller.getGSTIN());
        }

        return sellerRepository.save(existingSeller);
    }

    @Override
    public void deleteSeller(Long id) throws Exception {
        Seller seller = this.getSellerById(id);
        sellerRepository.delete(seller);
    }

    @Override
    public Seller verifyEmail(String email, String otp) throws Exception {
        Seller seller = this.getSellerByEmail(email);
        seller.setEmailVerified(true);
        return sellerRepository.save(seller);
    }

    @Override
    public Seller updateSellerAccountStatus(Long sellerId, AccountStatus status) throws Exception {
        Seller seller = this.getSellerById(sellerId);
        seller.setAccountStatus(status);
        return sellerRepository.save(seller);
    }
}
