package com.sparksupport.jmt.salesmanager.service;

import com.sparksupport.jmt.salesmanager.entity.Product;
import com.sparksupport.jmt.salesmanager.entity.Sale;
import com.sparksupport.jmt.salesmanager.exceptionHandling.ResourceNotFoundException;
import com.sparksupport.jmt.salesmanager.repository.ProductRepository;
import com.sparksupport.jmt.salesmanager.repository.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private SaleRepository saleRepository;

    /*
     * Retrieves all products with pagination support.
     * @param pageable: pagination information
     * @return Page<Product>: a page of products
     */
    @Override
    public Page<Product> getAllProducts(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    /*
     * Retrieves a specific product by its ID.
     * @param id: ID of the product to be retrieved
     * @return Product: the product with the given ID
     * @throws ResourceNotFoundException if the product with the given ID is not found
     */
    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "Id", id));
    }

    /*
     * Adds a new product to the database.
     * @param product: the product to be added
     */
    @Override
    public void addProduct(Product product) {
    productRepository.save(product);
    }

    /*
     * Updates an existing product in the database.
     * @param id: ID of the product to be updated
     * @param product: updated product information
     * @throws ResourceNotFoundException if the product with the given ID is not found
     */
    @Override
    public void updateProduct(long id, Product product) {
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "Id", id));
        existingProduct.setName(product.getName());
        existingProduct.setDescription(product.getDescription());
        existingProduct.setPrice(product.getPrice());
        existingProduct.setQuantity(product.getQuantity());

        productRepository.save(existingProduct);
    }

    /*
     * Deletes a product from the database by its ID.
     * @param id: ID of the product to be deleted
     * @throws ResourceNotFoundException if the product with the given ID is not found
     */
    @Override
    public void deleteProduct(long id) {
        productRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Product","id", id));
        productRepository.deleteById(id);
    }

    /*
     * Calculates the total revenue by summing up the revenue generated by all sales.
     * @return double: total revenue
     */
    @Override
    public double getTotalRevenue() {
        List<Sale> sales = saleRepository.findAll();
        double totalRevenue = 0;

        for (Sale sale : sales) {
            totalRevenue += sale.getQuantity() * sale.getProduct().getPrice();
        }

        return totalRevenue;
    }


    /*
     * Retrieves the revenue generated by a specific product based on its ID.
     * @param productId: ID of the product for which revenue is to be calculated
     * @return double: revenue generated by the product
     * @throws ResourceNotFoundException if the sale with the given product ID is not found
     */
    @Override
    public double getRevenueByProduct(Long productId)  {
        Optional<Sale> saleOptional = saleRepository.findById(productId);
        if (saleOptional.isPresent()) {
            Sale sale = saleOptional.get();
            return sale.getQuantity() * sale.getProduct().getPrice();
        } else {
            throw new ResourceNotFoundException("Sale", "productId", productId);
        }
    }
}
