package unitTesting;

import models.ProductsSup;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class ProductsSubTest {
    private ProductsSup product;

    @Before
    public void setUp() {
        product = new ProductsSup(1, 100, "Chocolate Cake", 50, 120, "sugar-free");
    }

    @Test
    public void testFullConstructor() {
        ProductsSup fullProduct = new ProductsSup(2, 200, "Vanilla Cake", 60, 80, "gluten-free");
        assertEquals(2, fullProduct.getId());
        assertEquals("Vanilla Cake", fullProduct.getName());
        assertEquals(60, fullProduct.getPrice());
        assertEquals(80, fullProduct.getTotalSold());
        assertEquals("gluten-free", fullProduct.getDietaryInfo());
    }

    @Test
    public void testEssentialFieldsConstructor() {
        ProductsSup essentialProduct = new ProductsSup(3, "Strawberry Cake", 40, 50);
        assertEquals(3, essentialProduct.getId());
        assertEquals("Strawberry Cake", essentialProduct.getName());
        assertEquals(40, essentialProduct.getPrice());
        assertEquals(50, essentialProduct.getTotalSold());
    }

    @Test
    public void testGetPriceWithDiscount() {
        assertEquals(40, product.getPrice());
    }

    @Test
    public void testGetPriceWithoutDiscount() {
        product.setTotalSold(80);
        assertEquals(50, product.getPrice());
    }

    @Test
    public void testSettersAndGetters() {
        product.setId(10);
        assertEquals(10, product.getId());

        product.setName("Updated Cake");
        assertEquals("Updated Cake", product.getName());

        product.setPrice(70);
        product.setTotalSold(200);
        assertEquals(56, product.getPrice());
    }

    @Test
    public void testToString() {
        String expectedString = "Product Number: 1, Name: Chocolate Cake, Price: 50";
        assertEquals(expectedString, product.toString());
    }
}
