package models;

import java.util.LinkedList;

public class NormalUser extends GeneralUser {
    private int id;
    private String name;
    private String phone;
    private String city;
    private LinkedList<String> message;

    public NormalUser(String username, String password, String role, int id, String name, String phone, String city) {
        super(username, password, role);
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.city = city;
        this.message = new LinkedList<>();
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public LinkedList<String> getMessage() {
        return message;
    }

    public void setMessage(LinkedList<String> message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "NormalUser{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", city='" + city + '\'' +
                ", message=" + message +
                ", " + super.toString() +
                '}';
    }
}
