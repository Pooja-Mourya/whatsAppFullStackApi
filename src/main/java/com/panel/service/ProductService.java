package com.panel.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.panel.entity.Product;

public interface ProductService {

    Product createProduct(String name, String description, MultipartFile image) throws IOException;

    List<Product> productList();

    Product productById(Long id);

    Product updateProduct(Long id, String name, String description, MultipartFile image) throws IOException;

    void productDelete(Long id); // Specify the ID or Product instance to delete
}
