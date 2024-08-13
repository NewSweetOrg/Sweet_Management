package services;

import models.NormalUser;
import models.Products;
import models.Store;

import java.util.HashMap;
import java.util.Map;

public class PurchaseService {

    //map to track orders,status
    private Map<String, String> orders = new HashMap<>();


    public boolean purchaseDessertById(NormalUser user, int productId, ProductService productService, StoreService storeService) {
        Products product = productService.getProductById(productId);
        if (product == null) {
            return false; // Product not found
        }

        Store store = storeService.getStoreById(product.getStoreOrSupplierId());
        if (store == null) {
            return false; // Store not found
        }

        int price = product.getPrice();

        // Simulate purchase logic
        store.setSales(store.getSales() + product.getPrice());
        product.setTotalSold(product.getTotalSold() + 1);  // Increment total sold by 1

        // Generate a unique order ID (for simplicity, using user ID and product ID)
        String orderId = user.getId() + "-" + productId;

        orders.put(orderId, "delivered");

        return true;
    }

    public String checkOrderStatus(String orderId) {
        if (orders.containsKey(orderId)) {
            String status = orders.get(orderId);
            if ("delivered".equalsIgnoreCase(status)) {
                return "Order " + orderId + " is delivered. ✔"; // Display delivered check
            } else {
                return "Order " + orderId + " is " + status;
            }
        } else {
            return "Order " + orderId + " not found.";
        }
    }

}

