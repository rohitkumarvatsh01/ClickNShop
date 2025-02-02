package com.clicknshop.service;

import com.clicknshop.model.Product;
import com.clicknshop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public String createNewProduct(Product product) {
        productRepository.save(product);
        return "New Product is Created";
    }
    
    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id)
                                .orElseThrow(() -> new NoSuchElementException("Product not found"));
    }

    public void updateProduct(Long id, Product updatedProduct) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()) {
            Product existingProduct = optionalProduct.get();
            existingProduct.setName(updatedProduct.getName());
            existingProduct.setPrice(updatedProduct.getPrice());
            existingProduct.setImageUrl(updatedProduct.getImageUrl());
            productRepository.save(existingProduct);
        } else {
            throw new NoSuchElementException("Product not found");
        }
    }

    public void deleteProductById(Long id) {
        productRepository.deleteById(id);
    }

}