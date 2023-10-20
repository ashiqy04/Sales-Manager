package com.sparksupport.jmt.salesmanager.repository;

import com.sparksupport.jmt.salesmanager.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
