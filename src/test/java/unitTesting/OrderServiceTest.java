package unitTesting;

import models.Order;
import services.OrderService;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class OrderServiceTest {

    private OrderService orderService;

    @Before
    public void setUp() {
        orderService = new OrderService();
    }

    @Test
    public void testUpdateOrderStatus_OrderExists() {
        Order order1 = new Order(1, "Product A", 2, "Pending");
        orderService.addOrder(order1);

        orderService.updateOrderStatus(1, "Shipped");

        assertEquals("Shipped", order1.getStatus());
    }

    @Test
    public void testUpdateOrderStatus_OrderDoesNotExist() {
        Order order1 = new Order(1, "Product A", 2, "Pending");
        orderService.addOrder(order1);

        orderService.updateOrderStatus(999, "Shipped");

        assertEquals("Pending", order1.getStatus()); // Order 999 doesn't exist, so no changes
    }

    @Test
    public void testGetAllOrders() {
        // Arrange
        Order order1 = new Order(1, "Product A", 2, "Pending");
        Order order2 = new Order(2, "Product B", 5, "Pending");
        orderService.addOrder(order1);
        orderService.addOrder(order2);

        List<Order> orders = orderService.getAllOrders();

        // Assert
        assertEquals(2, orders.size());
        assertTrue(orders.contains(order1));
        assertTrue(orders.contains(order2));
    }

    @Test
    public void testOrderToString() {
        Order order = new Order(1, "Product A", 2, "Pending");

        String orderString = order.toString();

        assertEquals("Order ID: 1, Product: Product A, Quantity: 2, Status: Pending", orderString);
    }
}