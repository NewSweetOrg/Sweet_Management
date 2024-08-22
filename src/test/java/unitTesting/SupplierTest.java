package unitTesting;

import models.Supplier;
import services.SupplierService;
import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;

import static org.junit.Assert.*;

public class SupplierTest {

    private Supplier supplier;
    private SupplierService supplierService;

    @Before
    public void setUp() {
        LinkedList<String> messages = new LinkedList<>();
        LinkedList<String> products = new LinkedList<>();
        supplier = new Supplier("supplier123", "password", "Supplier", 1,
                "Supplier Name", 123456789, "123 Main St", 1000);
        supplierService = new SupplierService();
    }

    @Test
    public void testGetAndSetSales() {
        // Test initial sales value
        assertEquals(1000, supplier.getSales());

        // Set new sales value and test again
        supplier.setSales(1500);
        assertEquals(1500, supplier.getSales());
    }

    @Test
    public void testGetAndSetMessage() {
        // Create a new message list
        LinkedList<String> messages = new LinkedList<>();
        messages.add("Hello, this is a test message.");
        messages.add("Please respond to the inquiry.");

        // Set and get message list
        supplier.setMessage(messages);
        assertEquals(messages, supplier.getMessage());
    }

    @Test
    public void testGetAndSetProducts() {
        // Create a new product list
        LinkedList<String> products = new LinkedList<>();
        products.add("Product A");
        products.add("Product B");

        // Set and get products list
        supplier.setProducts(products);
        assertEquals(products, supplier.getProducts());
    }

    @Test
    public void testSetId() {
        // Test setting a new ID
        supplier.setId(5);
        assertEquals(5, supplier.getId());
    }

    @Test
    public void testToString() {
        // Test the toString method
        String expectedString = "Supplier{id=1, name='Supplier Name', phone=123456789, address='123 Main St'," +
                " sales=1000, message=[]," + " products=[], GeneralUser{username='supplier123', password='password', role='Supplier'}}";
        assertEquals(expectedString, supplier.toString());
    }

    @Test
    public void testAddSupplier() {
        // Add a supplier to the service
        supplierService.addSupplier(supplier);

        // Retrieve the supplier by ID and verify it's the same object
        Supplier retrievedSupplier = supplierService.getSupplierById(1);
        assertNotNull(retrievedSupplier);
        assertEquals(supplier, retrievedSupplier);
    }

    @Test
    public void testGetSupplierById() {
        // Add a supplier and retrieve it by ID
        supplierService.addSupplier(supplier);
        Supplier retrievedSupplier = supplierService.getSupplierById(1);

        // Verify the correct supplier is retrieved
        assertNotNull(retrievedSupplier);
        assertEquals("Supplier Name", retrievedSupplier.getName());

        // Verify that an invalid ID returns null
        Supplier nonExistentSupplier = supplierService.getSupplierById(999);
        assertNull(nonExistentSupplier);
    }

    @Test
    public void testUpdateSupplier() {
        // Add the supplier to the service
        supplierService.addSupplier(supplier);

        // Update the supplier's details
        supplierService.updateSupplier(1, "Updated Name", "987654321", "456 Elm St");

        // Retrieve the updated supplier and verify the changes
        Supplier updatedSupplier = supplierService.getSupplierById(1);
        assertNotNull(updatedSupplier);
        assertEquals("Updated Name", updatedSupplier.getName());
        assertEquals(987654321, updatedSupplier.getPhone());
        assertEquals("456 Elm St", updatedSupplier.getAddress());

        // Verify no update occurs if supplier ID does not exist
        supplierService.updateSupplier(999, "Nonexistent Name", "111111111", "789 Oak St");
        Supplier nonUpdatedSupplier = supplierService.getSupplierById(999);
        assertNull(nonUpdatedSupplier);
    }
}

