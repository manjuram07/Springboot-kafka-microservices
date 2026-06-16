package net.javaguides.stockservice.repository;

import net.javaguides.stockservice.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}