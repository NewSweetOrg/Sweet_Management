package unitTesting;

import models.ProductsSup;
import services.ProductServiceSup;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
public class ProductServiceSupTest {

    private ProductServiceSup productServiceSup;

    @Before
    public void setUp() {
        productServiceSup = new ProductServiceSup();
    }

    @Test
    public void testAddProduct() {
        ProductsSup product = new ProductsSup(0, 3, "Vanilla Cake", 300, 200, "vegan");
        int id = productServiceSup.addProduct(product);
        assertEquals(3, id);
        assertNotNull(productServiceSup.getProductById(id));
    }

    @Test
    public void testGetProductById() {
        ProductsSup product = productServiceSup.getProductById(1);
        assertNotNull(product);
        assertEquals("Chocolate Cake", product.getName());

        ProductsSup nonExistentProduct = productServiceSup.getProductById(999);
        assertNull(nonExistentProduct);
    }

    @Test
    public void testUpdateProduct() {
        ProductsSup updatedProduct = new ProductsSup(0, 1, "Updated Cake", 150, 80, "sugar-free");
        productServiceSup.updateProduct(1, updatedProduct);
        ProductsSup retrievedProduct = productServiceSup.getProductById(1);
        assertEquals("Updated Cake", retrievedProduct.getName());
    }

    @Test
    public void testRemoveProduct() {
        productServiceSup.removeProduct(1);
        ProductsSup product = productServiceSup.getProductById(1);
        assertNull(product);
    }

    @Test
    public void testPrintAllProducts_Sup() {
        // This is harder to test as it involves logging, but you can check the log output or mock the logger.
        // For now, we'll just ensure that the method runs without throwing exceptions.
        //  productServiceSup.printAllProducts_Sup();
        productServiceSup.printAllProducts_Sup();
        assertTrue("Print method ran successfully.", true);
    }
}
