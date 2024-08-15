package services;

import models.Order;

import java.util.ArrayList;
import java.util.List;

public class OrderService {
    private List<Order> orders = new ArrayList<>();

    public void updateOrderStatus(int orderId, String newStatus) {
        for (Order order : orders) {
            if (order.getId() == orderId) {
                order.setStatus(newStatus);
                return;
            }
        }
        System.out.println("Order not found.");
    }

    public List<Order> getAllOrders() {
        return new ArrayList<>(orders);
    }
}
