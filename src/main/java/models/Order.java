package models;

public class Order {
    private int id;
    private String productName;
    private int quantity;
    private String status;

    public Order(int id, String productName, int quantity, String status) {
        this.id = id;
        this.productName = productName;
        this.quantity = quantity;
        this.status = status;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "Order ID: " + id + ", Product: " + productName + ", Quantity: " + quantity + ", Status: " + status;
    }
}
