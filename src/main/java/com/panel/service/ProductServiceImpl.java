package com.panel.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.panel.entity.Product;
import com.panel.repository.ProductRepository;

import io.jsonwebtoken.io.IOException;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Override
	public Product createProduct(String name, String description, MultipartFile image) throws java.io.IOException {

		try {
			byte[] imageBytes = image.getBytes();
			Product product = new Product();
			product.setName(name);
			product.setDescription(description);
			product.setImage(imageBytes);

			Product save = productRepository.save(product);

			return save;
		} catch (IOException e) {
			e.printStackTrace();
			ResponseEntity.status(500).body("Error uploading image");
		}
		return null;
	}

	@Override
	public List<Product> productList() {
		List<Product> findAll = productRepository.findAll();
		return findAll;
	}

	@Override
	public Product productById(Long id) {
		 Optional<Product> optionalProduct = productRepository.findById(id);

		    if (optionalProduct.isPresent()) {
		        return optionalProduct.get();
		    } else {
		        throw new NoSuchElementException("Product not found with ID: " + id);
		    }
	}

	@Override
	public Product updateProduct(Long id, String name, String description, MultipartFile image) throws IOException, java.io.IOException {
        // Check if the product with the given ID exists
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()) {
            Product existingProduct = optionalProduct.get();

            // Update the product properties
            existingProduct.setName(name);
            existingProduct.setDescription(description);

            // Handle image updating logic if needed
            if (image != null && !image.isEmpty()) {
                existingProduct.setImage(image.getBytes());
            }

            // Save the updated product
            return productRepository.save(existingProduct);
        } else {
            // Handle the case where the product with the given ID is not found
            throw new NoSuchElementException("Product not found with ID: " + id);
        }
    }

	@Override
	public void productDelete(Long id) {
		productRepository.deleteById(id);
	}

}
