package net.javaguides.stockservice.service;

public interface ProductService {

    void reduceQuantity(Long productId, Integer quantity);
}