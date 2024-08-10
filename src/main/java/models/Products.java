package models;

import java.util.LinkedList;

public class Products {
    private int id;
    private int storeOrSupplierId;
    private String name;
    private int price;
    private int totalSold;
    private LinkedList<String> feedback;

    public Products(int id, int storeOrSupplierId, String name, int price, int totalSold) {
        this.id = id;
        this.storeOrSupplierId = storeOrSupplierId;
        this.name = name;
        this.price = price;
        this.totalSold = totalSold;
        this.feedback = new LinkedList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStoreOrSupplierId() {
        return storeOrSupplierId;
    }

    public void setStoreOrSupplierId(int storeOrSupplierId) {
        this.storeOrSupplierId = storeOrSupplierId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        if(totalSold > 100){
            return (int) (price*0.80);
        }
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getTotalSold() {
        return totalSold;
    }

    public void setTotalSold(int totalSold) {
        this.totalSold = totalSold;
    }


    public LinkedList<String> getFeedback() {
        return feedback;
    }

    public void setFeedback(LinkedList<String> feedback) {
        this.feedback = feedback;
    }

    @Override
    public String toString() {
        return "Products{" +
                "id=" + id +
                ", storeOrSupplierId=" + storeOrSupplierId +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", totalSold=" + totalSold +
                ", feedback=" + feedback +
                '}';
    }
}
