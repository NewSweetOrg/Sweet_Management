package models;
 

public class Order {
    private String id;
    private String productId;
    private String userId;
    private String status;

    // Constructors
    public Order() {}

    public Order(String id, String productId, String userId, String status) {
        this.id = id;
        this.productId = productId;
        this.userId = userId;
        this.status = status;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
