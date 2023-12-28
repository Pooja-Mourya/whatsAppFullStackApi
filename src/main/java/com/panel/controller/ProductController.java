package com.panel.controller;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.panel.entity.Product;
import com.panel.service.ProductServiceImpl;

@RestController
@RequestMapping("/api/products")
public class ProductController {

	@Autowired
	private ProductServiceImpl productService;

	@PostMapping("/create")
	public ResponseEntity<Product> addProduct(@RequestParam("name") String name,
			@RequestParam("description") String description, @RequestParam("image") MultipartFile image)
			throws java.io.IOException {
		Product createProduct = productService.createProduct(name, description, image);
		return new ResponseEntity<Product>(createProduct, HttpStatus.CREATED);
	}

	@GetMapping("/list")
	public ResponseEntity<List<Product>> allProduct() {
		List<Product> productList = productService.productList();
		return new ResponseEntity<List<Product>>(productList, HttpStatus.ACCEPTED);
	}

	@GetMapping("/productById/{id}")
	public ResponseEntity<Product> getProductById(@PathVariable Long id) {
		try {
			Product product = productService.productById(id);
			return ResponseEntity.ok(product);
		} catch (NoSuchElementException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestParam String name,
			@RequestParam String description, @RequestParam(required = false) MultipartFile image) {
		try {
			Product updatedProduct = productService.updateProduct(id, name, description, image);
			return ResponseEntity.ok(updatedProduct);
		} catch (IOException | NoSuchElementException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
		try {
			productService.productDelete(id);
			return ResponseEntity.ok("Product deleted successfully");
		} catch (NoSuchElementException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found with ID: " + id);
		}
	}


}
