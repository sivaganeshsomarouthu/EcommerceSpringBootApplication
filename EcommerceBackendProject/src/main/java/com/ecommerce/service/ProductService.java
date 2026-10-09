package com.ecommerce.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ecommerce.entity.Product;
import com.ecommerce.repository.ProductRepository;

@Service
public class ProductService {

    private ProductRepository repo;

    public ProductService(ProductRepository repo) {
        this.repo = repo;
    }

    public List<Product> getAll() {
        return repo.findAll();
    }

    public Product getById(Long id) {
        return repo.findById(id).orElse(null);
    }

    public Product addProduct(Product product) {
        return repo.save(product);
    }

    public String rateProduct(Long id, double rating) {

        Product p = repo.findById(id).orElse(null);

        if (p == null) {
            return "Product not found";
        }

        if (rating < 1 || rating > 5) {
            return "Rating should be between 1 and 5";
        }

        p.setRating(rating);
        repo.save(p);

        return "Product rating updated";
    }
}
