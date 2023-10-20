package com.sparksupport.jmt.salesmanager.service;

import com.sparksupport.jmt.salesmanager.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {

    Page<Product> getAllProducts(Pageable pageable);
    Product getProductById(Long id);
    void addProduct(Product product);
    void updateProduct(long id, Product product);
    void deleteProduct(long id);

    double getTotalRevenue();

    double getRevenueByProduct(Long productId);


}
