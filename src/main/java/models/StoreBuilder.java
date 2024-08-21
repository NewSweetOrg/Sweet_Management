package models;

public class StoreBuilder {
    private String username;
    private String password;
    private String role;
    private int id;
    private String name;
    private int phone;
    private String address;
    private int sales;

    public StoreBuilder setUsername(String username) {
        this.username = username;
        return this;
    }

    public StoreBuilder setPassword(String password) {
        this.password = password;
        return this;
    }

    public StoreBuilder setRole(String role) {
        this.role = role;
        return this;
    }

    public StoreBuilder setId(int id) {
        this.id = id;
        return this;
    }

    public StoreBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public StoreBuilder setPhone(int phone) {
        this.phone = phone;
        return this;
    }

    public StoreBuilder setAddress(String address) {
        this.address = address;
        return this;
    }

    public StoreBuilder setSales(int sales) {
        this.sales = sales;
        return this;
    }

    public Store createStore() {
        return new Store(username, password, role, id, name, phone, address, sales);
    }
}