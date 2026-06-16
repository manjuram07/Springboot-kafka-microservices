package net.javaguides.stockservice.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import net.javaguides.stockservice.entity.Product;
import net.javaguides.stockservice.repository.ProductRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductServiceImpl implements ProductService {

    private final ProductRepository repository;

    @Override
    public void reduceQuantity(Long productId, Integer quantity) {

        Product product = repository.findById(productId)
                                    .orElseThrow(() -> new RuntimeException("Product not found"));

        System.out.println("Available Quantity : " + product.getAvailableQuantity());

        System.out.println("Requested Quantity : " + quantity);

        if (product.getAvailableQuantity() < quantity) {

            throw new RuntimeException("Insufficient stock");
        }

        product.setAvailableQuantity(product.getAvailableQuantity() - quantity);

        repository.save(product);

        System.out.println("Available Quantity : " + product.getAvailableQuantity());

        System.out.println("Stock updated");
    }
}
