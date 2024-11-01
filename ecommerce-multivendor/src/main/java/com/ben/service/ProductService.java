package com.ben.service;

import com.ben.exceptions.ProductException;
import com.ben.modal.Product;
import com.ben.modal.Seller;
import com.ben.request.CreateProductRequest;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService {

    Product createProduct(CreateProductRequest req, Seller seller);

    void deleteProduct(Long productId) throws ProductException;

    Product updateProduct(Long productId, Product product) throws ProductException;

    Product findProductById(Long productId) throws ProductException;

    List<Product> searchProducts(String query);

    Page<Product> getAllProduct(
            String category,
            String brand,
            String colors,
            String sizes,
            Integer minPrice,
            Integer maxPrice,
            Integer minDiscount,
            String sort,
            String stock,
            Integer pageNumber
    );

    List<Product> getProductBySellerId(Long sellerId);
}
