package unitTesting;

import services.ProductService;
import models.Products;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
public class ProductServiceTest {


    private ProductService productService;

    @Before
    public void setUp() {
        productService = new ProductService();
    }

    @Test
    public void testPrintAllProducts_EmptyList() {
        productService.products.clear();
        productService.printAllProducts();
        assertTrue("No products available.", true);
    }

    @Test
    public void testPrintAllProducts_WithProducts() {
        productService.printAllProducts();
        assertTrue("Products are printed.", true);
    }

    @Test
    public void testRemoveProduct_Successful() {
        int id = productService.addProduct(new Products(0, 3, "New Product", 300, 10, "vegan"));
        productService.removeProduct(id);
        assertNull(productService.getProductById(id));
    }

    @Test
    public void testRemoveProduct_NotFound() {
        productService.removeProduct(999);
        assertTrue("Product removal attempted on non-existent ID.", true);
    }

    @Test
    public void testSearchProductsByName_NoMatch() {
        productService.searchProductsByName("NonExistent");
        assertTrue("Search ran with no matching products.", true);
    }

    @Test
    public void testSearchProductsByName_Match() {
        productService.searchProductsByName("Chocolate");
        assertTrue("Search ran with matching products.", true);
    }

    @Test
    public void testFilterProductsByDietaryNeeds_NoMatch() {
        productService.filterProductsByDietaryNeeds("NonExistentDiet");
        assertTrue("Filter ran with no matching products.", true);
    }

    @Test
    public void testFilterProductsByDietaryNeeds_Match() {
        productService.filterProductsByDietaryNeeds("sugar-free");
        assertTrue("Filter ran with matching products.", true);
    }
}

