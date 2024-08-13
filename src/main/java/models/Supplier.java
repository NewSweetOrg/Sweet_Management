package models;

import java.util.LinkedList;

public class Supplier extends GeneralUser {
    private int id;
    private String name;
    private int phone;
    private String address;
    private int sales;
    private LinkedList<String> message;
    private LinkedList<String> products;

    public Supplier(String username, String password, String role, int id, String name, int phone, String address, int sales) {
        super(username, password, role);
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.sales = sales;
        this.message = new LinkedList<>();
        this.products = new LinkedList<>();
    }

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

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getSales() {
        return sales;
    }

    public void setSales(int sales) {
        this.sales = sales;
    }

    public LinkedList<String> getMessage() {
        return message;
    }

    public void setMessage(LinkedList<String> message) {
        this.message = message;
    }

    public LinkedList<String> getProducts() {
        return products;
    }

    public void setProducts(LinkedList<String> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "Supplier{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phone=" + phone +
                ", address='" + address + '\'' +
                ", sales=" + sales +
                ", message=" + message +
                ", products=" + products +
                ", " + super.toString() +
                '}';
    }
}