package com.sparksupport.jmt.salesmanager.service;

import com.sparksupport.jmt.salesmanager.entity.Sale;

import java.util.List;

public interface SaleService {


    void addSaleDetails(Sale sale);

    List<Sale> getAllSales();


    void deleteSales(Long productId);

    void updateSales(Long id, Sale sale);
}
