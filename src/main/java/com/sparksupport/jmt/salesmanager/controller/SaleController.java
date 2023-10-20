package com.sparksupport.jmt.salesmanager.controller;


import com.sparksupport.jmt.salesmanager.entity.Sale;
import com.sparksupport.jmt.salesmanager.service.SaleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sale")
public class SaleController {

    @Autowired
    private SaleService saleService;

    @Secured("ROLE_ADMIN")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping("/add")
    public ResponseEntity<Sale> addSaleDetails(@Valid @RequestBody Sale sale){
         saleService.addSaleDetails(sale);
         return new ResponseEntity<>(sale, HttpStatus.CREATED);
    }

    @Secured("ROLE_ADMIN")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/list")
    public  ResponseEntity<List<Sale>> getAllSales(){
        List<Sale> sale=saleService.getAllSales();
        return new ResponseEntity<>(sale, HttpStatus.OK);
    }

    @Secured("ROLE_ADMIN")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @DeleteMapping("/delete/{productId}")
    public ResponseEntity<String> deleteSales(@PathVariable("productId") Long productId){
        saleService.deleteSales(productId);
        return new ResponseEntity<>("Deleted Successfully", HttpStatus.OK);
    }

    @Secured("ROLE_ADMIN")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PutMapping("/update/{id}")
    public ResponseEntity<Sale> updateSales(@Valid @PathVariable Long id, @RequestBody Sale sale){
        saleService.updateSales(id, sale);
        return new ResponseEntity<>(sale, HttpStatus.OK);
    }
}
