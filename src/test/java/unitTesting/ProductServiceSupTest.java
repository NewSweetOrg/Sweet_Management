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
        // Use a unique ID to avoid conflicts with other tests
        int uniqueId = productServiceSup.addProduct(new ProductsSup(0, 4, "Original Cake", 100, 50, "sugar-free"));

        // Update the product
        ProductsSup updatedProduct = new ProductsSup(uniqueId, 4, "Updated Cake", 150, 80, "gluten-free");
        productServiceSup.updateProduct(uniqueId, updatedProduct);

        // Retrieve the updated product and verify the changes
        ProductsSup retrievedProduct = productServiceSup.getProductById(uniqueId);
        assertNotNull("Retrieved product should not be null", retrievedProduct);
        assertEquals("Updated Cake", retrievedProduct.getName());
        assertEquals(150, retrievedProduct.getPrice());
        assertEquals(80, retrievedProduct.getTotalSold());
        assertEquals("gluten-free", retrievedProduct.getDietaryInfo());
    }

    @Test
    public void testRemoveProduct() {
        // Add a product to ensure it can be removed
        int id = productServiceSup.addProduct(new ProductsSup(0, 5, "Product To Remove", 100, 50, "gluten-free"));

        productServiceSup.removeProduct(id);
        ProductsSup product = productServiceSup.getProductById(id);
        assertNull(product);
    }

    @Test
    public void testPrintAllProducts_Sup() {
        // For now, we'll just ensure that the method runs without throwing exceptions.
        productServiceSup.printAllProducts_Sup();
        assertTrue("Print method ran successfully.", true);
    }
}
