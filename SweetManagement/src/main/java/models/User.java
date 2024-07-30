package models;

 

public class User {
    private String username;
    private String password;
    private String role; // "admin", "storeOwner", "supplier", "user"
    private String city;

    // Constructors
    public User() {}

    public User(String username, String password, String role, String city) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.city = city;
    }

    // Getters and Setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
