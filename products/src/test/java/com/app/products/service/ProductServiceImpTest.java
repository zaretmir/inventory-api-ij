package com.app.products.service;

import com.app.base.product.model.Product;
import com.app.products.dao.ProductDAO;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

public class ProductServiceImpTest {
    @Mock
    ProductDAO productDAO;
    @InjectMocks
    ProductServiceImp productServiceImp;

    private Product mockedProductA = new Product(Long.valueOf(64),"Norephedrine","asdf",true);
    private Product mockedProductB = new Product(Long.valueOf(63),"Ephedrine","qwerty",true);

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        mockedProductA.setStockEntries(null);
        mockedProductB.setStockEntries(null);

        when(productDAO.findById(Long.valueOf(64)))
                .thenReturn(mockedProductA);
        when(productDAO.findByIsStateTrue())
                .thenReturn(Arrays.<Product>asList(mockedProductA, mockedProductB));
        when(productDAO.getActiveProductsPage(any()))
                .thenReturn(null);
        when(productDAO.findByIsStateTrueAndNameContaining("eph"))
                .thenReturn(Arrays.<Product>asList(mockedProductA, mockedProductB));
        when(productDAO.save(any()))
                .thenReturn(mockedProductA);
        when(productDAO.existsByName(anyString()))
                .thenReturn(true);
        when(productDAO.findById(anyLong()))
                .thenReturn(mockedProductA);
        when(productDAO.save(any()))
                .thenReturn(mockedProductA);
        when(productDAO.findById(anyLong()))
                .thenReturn(mockedProductA);
        when(productDAO.delete(any()))
                .thenReturn(mockedProductA);
        when(productDAO.findByIsStateTrue())
                .thenReturn(Arrays.<Product>asList(mockedProductA, mockedProductB));
        when(productDAO.findByIsStateTrue())
                .thenReturn(Arrays.<Product>asList(mockedProductA, mockedProductB));
    }

    @Test
    public void testGetProductById() throws Exception {
        Product result = productServiceImp.getProductById(Long.valueOf(64));
        //assertThat(result, sameBeanAs(mockedProductA));
        Assert.assertEquals(mockedProductA, result);
    }

    @Test
    public void testGetActiveProducts() throws Exception {
        List<Product> result = productServiceImp.getActiveProducts();
        Assert.assertEquals(Arrays.<Product>asList(mockedProductA, mockedProductB), result);
    }

    @Test
    public void testGetActiveProductsPage() throws Exception {
        Page<Product> result = productServiceImp.getActiveProductsPage(null);
        Assert.assertEquals(null, result);
    }

    @Test
    public void testGetActiveProductsMatchingSearch() throws Exception {
        List<Product> result = productServiceImp.getActiveProductsMatchingSearch(anyString());
        Assert.assertEquals(Arrays.<Product>asList(mockedProductA, mockedProductB), result);
    }

    @Test
    public void testCreateProduct() throws Exception {
        Product result = productServiceImp.createProduct(new Product(Long.valueOf(64), "Norephedrine", "asdf", true));
        Assert.assertEquals(mockedProductA, result);
    }

    @Test
    public void testEditProduct() throws Exception {
        Product result = productServiceImp.editProduct(new Product(Long.valueOf(64), "Norephedrine", "asdf", true));
        Assert.assertEquals(mockedProductA, result);
    }

    @Test
    public void testDeleteProduct() throws Exception {
        Product result = productServiceImp.deleteProduct(Long.valueOf(1));
        Assert.assertEquals(new Product(Long.valueOf(1), "name", "description"), result);
    }

    @Test
    public void testListProductsByFirstLetter() throws Exception {
        List<Product> result = productServiceImp.listProductsByFirstLetter('a');
        Assert.assertEquals(Arrays.<Product>asList(new Product(null, "name", null)), result);
    }

    @Test
    public void testListProductsUpperCase() throws Exception {
        List<Product> result = productServiceImp.listProductsUpperCase();
        Assert.assertEquals(Arrays.<Product>asList(new Product(Long.valueOf(1), "name", "description")), result);
    }

    @Test
    public void testListProductsLongestName() throws Exception {
        Optional<Product> result = productServiceImp.listProductsLongestName();
        Assert.assertEquals(null, result);
    }

    @Test
    public void testGetNullPropertyNames() throws Exception {
        String[] result = productServiceImp.getNullPropertyNames(new Product(Long.valueOf(1), "name", "description"));
        Assert.assertArrayEquals(new String[]{"replaceMeWithExpectedResult"}, result);
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme