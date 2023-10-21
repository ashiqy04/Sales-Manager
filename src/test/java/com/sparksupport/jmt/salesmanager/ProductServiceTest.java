package com.sparksupport.jmt.salesmanager;

import com.sparksupport.jmt.salesmanager.entity.Product;
import com.sparksupport.jmt.salesmanager.entity.Sale;
import com.sparksupport.jmt.salesmanager.repository.ProductRepository;
import com.sparksupport.jmt.salesmanager.repository.SaleRepository;
import com.sparksupport.jmt.salesmanager.service.ProductServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private SaleRepository saleRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    private Product product1;
    private Product product2;

    @Before
    public void setUp() {
        product1 = new Product(1L, "Product 1", "Description", 10.0, 100, new ArrayList<>());
        product2= new Product(2L, "Product 2", "Description", 20.0, 200, new ArrayList<>());
    }


    @Test
    public void testGetAllProducts(){
        //Mocking productRepository to return a Page of products when findAll is called
        List<Product> products = Arrays.asList(product1, product2);
        Page<Product> productPage = new PageImpl<>(products);
        Mockito.when(productRepository.findAll(Mockito.any(Pageable.class))).thenReturn(productPage);

        //Invoking the service method to get all products
        Page<Product> result = productService.getAllProducts(PageRequest.of(0, 10));

        //Asserting that the result is not null, contains 2 elements, and the first product's name is "Product 1"
        assertNotNull(result);
        assertEquals(2, result.getTotalElements());
        assertEquals("Product 1", result.getContent().get(0).getName());
    }

    @Test
    public void testGetProductById(){
        //Mocking productRepository to return product1 when findById is called with product1's id
        Mockito.when(productRepository.findById(product1.getId())).thenReturn(Optional.of(product1));

        //Invoking the service method to get a product by ID
        Product result = productService.getProductById(product1.getId());

        //Asserting that the result is not null and the product's name is "Product 1"
        assertNotNull(result);
        assertEquals("Product 1", result.getName());
    }

    @Test
    public void testAddProduct(){
        //Creating a new product and mocking productRepository to return savedProduct when save is called
        Product productToAdd = new Product(null, "Product 1", "Description 1", 15.3, 20, new ArrayList<>());
        Product savedProduct = new Product(1L, "Product 1", "Description 1", 15.3, 20, new ArrayList<>());

        Mockito.when(productRepository.save(productToAdd)).thenReturn(savedProduct);

        //Invoking the service method to add a new product
        productService.addProduct(productToAdd);

        //Verifying that productRepository's save method is called once with savedProduct
        Mockito.verify(productRepository, Mockito.times(1)).save(productToAdd);
    }

    @Test
    public void testUpdateProduct(){
        //Creating an updated product and mocking productRepository to return product1 when findById is called
        Product updatedProduct = new Product(1L, "Product 3", "Description 1", 15.3, 20, new ArrayList<>());

        Mockito.when(productRepository.findById(1L)).thenReturn(Optional.of(product1));
        Mockito.when(productRepository.save(updatedProduct)).thenReturn(updatedProduct);

        //Invoking the service method to update an existing product
        productService.updateProduct(1L, updatedProduct);

        //The product1's name is updated to "Product 3"
        assertEquals("Product 3", product1.getName());
    }

    @Test
    public void testDeleteProduct(){

        //Mocking productRepository to return product1 when findById is called with product1's id
        Mockito.when(productRepository.findById(1L)).thenReturn(Optional.of(product1));

        //Invoking the service method to delete a product by ID
        productService.deleteProduct(1L);

        //Verifying that productRepository's deleteById method is called once with product1's id
        Mockito.verify(productRepository, Mockito.times(1)).deleteById(1L);
    }

    @Test
    public void testGetRevenueByProduct(){

        //Creating a sale related to product1 and mocking saleRepository to return the sale when findById is called
        Sale sale = new Sale(1L, product1.getId(), 5, new Date(), product1);
        Mockito.when(saleRepository.findById(1L)).thenReturn(Optional.of(sale));

        //Invoking the service method to get revenue by product ID
        double revenueByProduct = productService.getRevenueByProduct(1L);

        //Asserting that the revenueByProduct is calculated correctly (5 units * 10.0 price = 50.0)
        assertEquals(50, revenueByProduct, 0.001);
    }

    @Test
    public void testGetTotalRevenue(){

        //Creating sales related to product1 and product2 and mocking saleRepository to return the sales list
        Sale sale1 = new Sale(1L, product1.getId(), 5, new Date(), product1);
        Sale sale2 = new Sale(2L, product2.getId(), 3, new Date(), product2);
        List<Sale> sales = Arrays.asList(sale1, sale2);
        Mockito.when(saleRepository.findAll()).thenReturn(sales);

        //Invoking the service method to get the total revenue
        double totalRevenue = productService.getTotalRevenue();

        //Asserting that the totalRevenue is calculated correctly (5 units * 10.0 price + 3 units * 20.0 price = 110.0)
        assertEquals(110.0, totalRevenue, 0.001);
    }
}
