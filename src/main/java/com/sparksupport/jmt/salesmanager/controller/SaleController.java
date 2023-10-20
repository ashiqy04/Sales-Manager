package com.sparksupport.jmt.salesmanager.controller;

import com.sparksupport.jmt.salesmanager.entity.Product;
import com.sparksupport.jmt.salesmanager.entity.Sale;
import com.sparksupport.jmt.salesmanager.service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sale")
public class SaleController {

    @Autowired
    private SaleService saleService;

    @PostMapping("/add")
    public ResponseEntity<Sale> addSaleDetails(@RequestBody Sale sale){
         saleService.addSaleDetails(sale);
         return new ResponseEntity<>(sale, HttpStatus.CREATED);
    }

    @GetMapping("/list")
    public  ResponseEntity<List<Sale>> getAllSales(){
        List<Sale> sale=saleService.getAllSales();
        return new ResponseEntity<>(sale, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{productId}")
    public ResponseEntity<String> deleteSales(@PathVariable("productId") Long productId){
        saleService.deleteSales(productId);
        return new ResponseEntity<>("Deleted Successfully", HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Sale> updateSales(@PathVariable Long id, @RequestBody Sale sale){
        saleService.updateSales(id, sale);
        return new ResponseEntity<>(sale, HttpStatus.OK);
    }
}
