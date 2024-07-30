package models;

 

public class Product {
    private String id;
    private String name;
    private double price;
    private String storeOwner;
    private int quantitySold;

    // Constructors
    public Product() {}

    public Product(String id, String name, double price, String storeOwner, int quantitySold) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.storeOwner = storeOwner;
        this.quantitySold = quantitySold;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }
    public double getCost() {
        return price; // دالة للحصول على تكلفة المنتج
    }
    public void setPrice(double price) {
        this.price = price;
    }

    public String getStoreOwner() {
        return storeOwner;
    }

    public void setStoreOwner(String storeOwner) {
        this.storeOwner = storeOwner;
    }

    public int getQuantitySold() {
        return quantitySold;
    }

    public void setQuantitySold(int quantitySold) {
        this.quantitySold = quantitySold;
    }
}
