package models;

import java.util.LinkedList;

public class ProductsSup {
    private int id;
    private int storeOrSupplierId;
    private String name;
    private int price;
    private int totalSold;
    private LinkedList<String> feedback;
    private String dietaryInfo;

    // Constructor with all fields
    public ProductsSup(int id, int storeOrSupplierId, String name, int price, int totalSold, String dietaryInfo) {
        this.id = id;
        this.storeOrSupplierId = storeOrSupplierId;
        this.name = name;
        this.price = price;
        this.totalSold = totalSold;
        this.dietaryInfo = dietaryInfo;
        this.feedback = new LinkedList<>();
    }

    // Overloaded constructor with essential fields
    public ProductsSup(int id, String name, int price, int totalSold) {
        this(id, 0, name, price, totalSold, ""); // Using another constructor
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        if (totalSold > 100) {
            return (int) (price * 0.80);
        }
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getTotalSold() {
        return totalSold;
    }

    public String getDietaryInfo() {
        return dietaryInfo;
    }

    public void setTotalSold(int totalSold) {
        this.totalSold = totalSold;
    }


    @Override
    public String toString() {
        return "Product Number: " + id +
                ", Name: " + name +
                ", Price: " + price;

    }
}
