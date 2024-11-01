package com.ben.controller;

import com.ben.domain.USER_ROLE;
import com.ben.exceptions.ProductException;
import com.ben.modal.Product;
import com.ben.repository.UserRepository;
import com.ben.request.LoginOtpRequest;
import com.ben.request.LoginRequest;
import com.ben.response.ApiResponse;
import com.ben.response.AuthResponse;
import com.ben.response.SignupRequest;
import com.ben.service.AuthService;
import com.ben.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/{productId}")
    public ResponseEntity<Product> getProductById(
            @PathVariable Long productId) throws ProductException {

        Product product = productService.findProductById(productId);
        return new ResponseEntity<>(product, HttpStatus.OK);

    }

    @GetMapping("/search")
    public ResponseEntity<List<Product>> getProductById(
            @RequestParam(required = false) String query) throws ProductException {

        List<Product> products = productService.searchProducts(query);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Page<Product>> getProductById(
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String brand,
            @RequestParam(required = false) String color,
            @RequestParam(required = false) String size,
            @RequestParam(required = false) Integer minPrice,
            @RequestParam(required = false) Integer maxPrice,
            @RequestParam(required = false) Integer minDiscount,
            @RequestParam(required = false) String sort,
            @RequestParam(required = false) String stock,
            @RequestParam(defaultValue = "0") Integer pageNumber
    ){

//        List<Product> products = productService.searchProducts(query);
        return new ResponseEntity<>(
                productService.getAllProduct(category,brand,color,size,minPrice,
                        maxPrice,minDiscount,sort,stock,pageNumber)
                , HttpStatus.OK);
    }


}
