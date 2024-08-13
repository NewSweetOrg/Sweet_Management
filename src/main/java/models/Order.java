package models;

public class Order {
    private int id;
    private String productName;
    private int quantity;
    private String status; // e.g., "Pending", "Shipped", "Delivered"

    public Order(int id, String productName, int quantity, String status) {
        this.id = id;
        this.productName = productName;
        this.quantity = quantity;
        this.status = status;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getProductName() { return productName; }
    public void setProductName(String productName) { this.productName = productName; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    @Override
    public String toString() {
        return "Order ID: " + id + ", Product: " + productName + ", Quantity: " + quantity + ", Status: " + status;
    }
}
