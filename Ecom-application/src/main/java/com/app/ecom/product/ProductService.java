package com.app.ecom.product;

import com.app.ecom.product.dto.ProductRequest;
import com.app.ecom.product.dto.ProductResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;




    public ProductResponse createProduct(ProductRequest productRequest){
        Product product = new Product();
        updateProductFromRequest(product, productRequest);
        product.setActive(true);

        Product savedProduct = productRepository.save(product);

        ProductResponse response = new ProductResponse();
        response.setId(savedProduct.getId());
        response.setName(savedProduct.getName());
        response.setDescription(savedProduct.getDescription());
        response.setPrice(savedProduct.getPrice());
        response.setStockQuantity(savedProduct.getStockQuantity());
        response.setCategory(savedProduct.getCategory());
        response.setImageUrl(savedProduct.getImageUrl());
        response.setActive(savedProduct.getActive());

        return response;
    }

    public void updateProductFromRequest(Product product, ProductRequest productRequest){
        product.setName(productRequest.getName());
        product.setCategory(productRequest.getCategory());
        product.setPrice(productRequest.getPrice());
        product.setDescription(productRequest.getDescription());
        product.setStockQuantity(productRequest.getStockQuantity());
        product.setImageUrl(productRequest.getImageUrl());
    }
}
