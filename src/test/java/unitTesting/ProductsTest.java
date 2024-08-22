package unitTesting;

import models.Products;
import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;

import static org.junit.Assert.*;
public class ProductsTest {

private Products product;

@Before
public void setUp() {
    LinkedList<String> feedback = new LinkedList<>();
    product = new Products(1, "Product A", 100, 50, "gluten-free");
}

@Test
public void testConstructorWithDietaryInfo() {
    // Test the constructor that calls another constructor
    Products productWithDietaryInfo = new Products(1, "Product B", 200, 75, "vegan");

    assertNotNull(productWithDietaryInfo);
    assertEquals(1, productWithDietaryInfo.getId());
    assertEquals("Product B", productWithDietaryInfo.getName());
    assertEquals(200, productWithDietaryInfo.getPrice());
    assertEquals(75, productWithDietaryInfo.getTotalSold());
    assertEquals("vegan", productWithDietaryInfo.getDietaryInfo());
}

@Test
public void testSetName() {
    // Test setting the name
    product.setName("Updated Product Name");
    assertEquals("Updated Product Name", product.getName());
}

@Test
public void testGetPriceWithDiscount() {
    // Set totalSold to trigger the discount condition
    product.setTotalSold(150);
    product.setPrice(100);

    // Expect a 10% discount because totalSold > 100
    assertEquals(90, product.getPrice());
}

@Test
public void testSetPrice() {
    // Test setting the price
    product.setPrice(250);
    assertEquals(250, product.getPrice());
}

@Test
public void testGetFeedback() {
    // Test retrieving feedback
    LinkedList<String> feedback = product.getFeedback();
    assertNotNull(feedback);
    assertTrue(feedback.isEmpty()); // Initially, the feedback list should be empty
}

@Test
public void testSetDietaryInfo() {
    // Test setting dietary information
    product.setDietaryInfo("nut-free");
    assertEquals("nut-free", product.getDietaryInfo());
}
}