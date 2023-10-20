package com.sparksupport.jmt.salesmanager.service;

import com.sparksupport.jmt.salesmanager.entity.Product;
import com.sparksupport.jmt.salesmanager.entity.Sale;
import com.sparksupport.jmt.salesmanager.exceptionHandling.ResourceNotFoundException;
import com.sparksupport.jmt.salesmanager.repository.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SaleServiceImpl implements SaleService{

    @Autowired
    private SaleRepository saleRepository;

    /*
     * This method adds the details of a sale by saving the Sale object in the database.
     * @param sale: The Sale object containing sale details to be added.
     */
    @Override
    public void addSaleDetails(Sale sale) {
        saleRepository.save(sale);
    }

    /*
     * This method retrieves all sales from the database and returns them as a List.
     * @return List<Sale>: A list containing all sales in the database.
     */
    @Override
    public List<Sale> getAllSales() {
        return saleRepository.findAll();
    }

    /*
     * This method deletes a sale from the database based on the provided productId.
     * @param productId: The unique identifier of the sale to be deleted.
     */
    @Override
    public void deleteSales(Long productId) {
        saleRepository.deleteById(productId);
    }

    /*
     * This method updates the details of an existing sale in the database.
     * It first checks if the sale with the given id exists. If not, it throws a ResourceNotFoundException.
     * If the sale exists, it updates the sale details with the provided Sale object.
     * @param id: The unique identifier of the sale to be updated.
     * @param sale: The Sale object containing updated sale details.
     * @throws ResourceNotFoundException: Thrown if the sale with the provided id is not found in the database.
     */
    @Override
    public void updateSales(Long id, Sale sale) {
        Sale existingSale = saleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Sales", "Id", id));
        existingSale.setProductId(sale.getProductId());
        existingSale.setQuantity(sale.getQuantity());
        existingSale.setSaleDate(sale.getSaleDate());

        saleRepository.save(existingSale);
    }
}
