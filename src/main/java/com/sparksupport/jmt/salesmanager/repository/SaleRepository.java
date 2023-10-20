package com.sparksupport.jmt.salesmanager.repository;

import com.sparksupport.jmt.salesmanager.entity.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Long> {
}
