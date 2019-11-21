package com.empresa;

import com.app.base.product.model.Product;
import com.app.products.dto.ProductDto;
import com.app.products.service.ProductService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;


import static org.mockito.Mockito.*;
import static com.shazam.shazamcrest.MatcherAssert.*;
import static com.shazam.shazamcrest.matcher.Matchers.sameBeanAs;

public class ProductControllerTest {
    @Mock
    ProductService productService;
    @InjectMocks
    ProductController productController;

    Product mockedProduct = new Product();

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        mockedProduct.setId(64);
        mockedProduct.setName("Norephedrine");
        mockedProduct.setDescription("asdf");
        mockedProduct.setActive(true);
        mockedProduct.setStockEntries(null);

        when(productService.getProductById(Long.valueOf(64)))
                .thenReturn(mockedProduct);
        when(productService.getActiveProducts())
                .thenReturn(Arrays.<Product>asList(new Product(Long.valueOf(1), "name", "description")));
        when(productService.getActiveProductsMatchingSearch(anyString()))
                .thenReturn(Arrays.<Product>asList(new Product(Long.valueOf(1), "name", "description")));

        when(productService.createProduct(any()))
                .thenReturn(new Product(Long.valueOf(1), "name", "description"));
        when(productService.getActiveProductsPage(any()))
                .thenReturn(null);
        when(productService.editProduct(any()))
                .thenReturn(new Product(Long.valueOf(1), "name", "description"));
        when(productService.deleteProduct(anyLong()))
                .thenReturn(new Product(Long.valueOf(1), "name", "description"));
    }

    @Test
    public void testGetProducts() throws Exception {
        ResponseEntity<List<ProductDto>> result = productController.getProducts("name");
        Assert.assertEquals(null, result);
    }

    @Test
    public void testGetProduct() throws Exception {
        ResponseEntity<ProductDto> result = productController.getProduct(Long.valueOf(64));
        ProductDto dto = new ProductDto();
        dto.setId(64);
        dto.setName("Norephedrine");
        dto.setDescription("asdf");
        dto.setActive(true);
        dto.setStockEntries(null);

        ResponseEntity<ProductDto> expected = new ResponseEntity<ProductDto>(dto, HttpStatus.OK);
        assertThat(result, sameBeanAs(expected));
    }

    @Test
    public void testGetProductsPage() throws Exception {
        ResponseEntity<Page<ProductDto>> result = productController.getProductsPage(0, 0);
        Assert.assertEquals(null, result);
    }

    @Test
    public void testCreateProduct() throws Exception {
        ResponseEntity<ProductDto> result = productController.createProduct(new ProductDto());
        Assert.assertEquals(null, result);
    }

    @Test
    public void testEditProduct() throws Exception {

        ResponseEntity<Product> result = productController.editProduct(new ProductDto());
        Assert.assertEquals(null, result);
    }

    @Test
    public void testDeleteProduct() throws Exception {

        ResponseEntity<ProductDto> result = productController.deleteProduct(Long.valueOf(1));
        Assert.assertEquals(null, result);
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme