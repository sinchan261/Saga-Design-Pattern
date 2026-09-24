package com.example.productsservice.products.Services;

import com.example.CoreService.CoreApplication.Dao.Product;
import com.example.CoreService.CoreApplication.Exceptions.ProductInsufficientQuantityException;
import com.example.productsservice.products.Dao.Jpa.Entity.ProductEntity;
import com.example.productsservice.products.Dao.Jpa.repository.ProductRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class ProductServiceImpl {
    @Autowired
    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    public Product reserve(Product desiredProduct, UUID orderId) {
        ProductEntity productEntity = productRepository.findById(desiredProduct.getId()).orElseThrow();
        if (desiredProduct.getQuantity() > productEntity.getQuantity()) {
            throw new ProductInsufficientQuantityException(productEntity.getId(), orderId);
        }

        productEntity.setQuantity(productEntity.getQuantity() - desiredProduct.getQuantity());
        productRepository.save(productEntity);

        var reservedProduct = new Product();
        BeanUtils.copyProperties(productEntity, reservedProduct);
        reservedProduct.setQuantity(desiredProduct.getQuantity());
        return reservedProduct;
    }
    public void cancelReservation(Product productToCancel, UUID orderId) {
        ProductEntity productEntity = productRepository.findById(productToCancel.getId()).orElseThrow();
        productEntity.setQuantity(productEntity.getQuantity() + productToCancel.getQuantity());
        productRepository.save(productEntity);
    }
    public Product save(Product product) {
        ProductEntity productEntity = new ProductEntity();
        productEntity.setName(product.getName());
        productEntity.setPrice(product.getPrice());
        productEntity.setQuantity(product.getQuantity());
        productRepository.save(productEntity);

        return new Product(productEntity.getId(), product.getName(), product.getPrice(), product.getQuantity());
    }
    public List<Product> findAll() {
        return productRepository.findAll().stream()
                .map(entity -> new Product(entity.getId(), entity.getName(), entity.getPrice(), entity.getQuantity()))
                .collect(Collectors.toList());
    }
}
