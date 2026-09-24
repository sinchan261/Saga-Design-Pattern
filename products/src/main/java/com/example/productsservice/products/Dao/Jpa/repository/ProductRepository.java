package com.example.productsservice.products.Dao.Jpa.repository;

import com.example.productsservice.products.Dao.Jpa.Entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, UUID> {
}
