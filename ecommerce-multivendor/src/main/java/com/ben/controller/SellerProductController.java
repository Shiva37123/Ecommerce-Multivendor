package com.ben.controller;

import com.ben.exceptions.ProductException;
import com.ben.exceptions.SellerException;
import com.ben.modal.Product;
import com.ben.modal.Seller;
import com.ben.request.CreateProductRequest;
import com.ben.service.ProductService;
import com.ben.service.SellerService;
import jdk.jshell.spi.ExecutionControl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/sellers/products")
public class SellerProductController {

    private final ProductService productService;
    private final SellerService sellerService;

    @GetMapping
    public ResponseEntity<List<Product>> getProductBySellerId(
            @RequestHeader("Authorization") String jwt) throws Exception {

        Seller seller = sellerService.getSellerProfile(jwt);
        System.out.println(seller.toString());

        List<Product> products =productService.getProductBySellerId(seller.getId());
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(
            @RequestBody CreateProductRequest request,
            @RequestHeader("Authorization") String jwt) throws ExecutionControl.UserException, ProductException, SellerException, Exception{

        Seller seller = sellerService.getSellerProfile(jwt);

        Product product = productService.createProduct(request, seller);
        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> deleteSeller(@PathVariable Long productId) throws Exception{
        try{
            productService.deleteProduct(productId);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (ProductException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{productId}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long productId,
                                                 @RequestBody Product product) throws ProductException {

        Product updatedProduct = productService.updateProduct(productId, product);
        return new ResponseEntity<>(updatedProduct, HttpStatus.OK);

    }
}
