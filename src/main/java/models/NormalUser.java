package models;

import java.util.LinkedList;
import java.util.List;

public class NormalUser extends GeneralUser {
    private int id;
    private String name;
    private String phone;
    private String city;
    private static int nextId = 1;
    private final LinkedList<String> message;
    private static final LinkedList<NormalUser> allUsers = new LinkedList<>();


    public NormalUser(int id, String name, String password, String city, String phone, String role) {
        super(name, password, role);
        this.id = nextId++;
        this.name = name;
        this.phone = phone;
        this.city = city;

        this.message = new LinkedList<>();


        getAllUsers().add(this);
    }

    public NormalUser(String username, String password, String role, int id, String name, String phone, String city) {
        super(username, password, role);
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.city = city;

        this.message = new LinkedList<>();

        getAllUsers().add(this);
    }

    public static LinkedList<NormalUser> getAllUsers() {
        return allUsers;
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


    public List<String> getMessage() {
        return message;
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
