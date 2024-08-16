package services;

import models.Order;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class OrderService {
    private static final Logger LOGGER = Logger.getLogger(OrderService.class.getName());
    private List<Order> orders = new ArrayList<>();

    public void updateOrderStatus(int orderId, String newStatus) {
        for (Order order : orders) {
            if (order.getId() == orderId) {
                order.setStatus(newStatus);
                return;
            }
        }
        // Replace System.out.println with a logger
        if (LOGGER.isLoggable(Level.WARNING)) {
            LOGGER.warning(String.format("Order with ID %d not found.", orderId));
        }
    }

    public List<Order> getAllOrders() {
        return new ArrayList<>(orders);
    }
}
