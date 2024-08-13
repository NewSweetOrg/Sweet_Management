package models;


public class Admin extends GeneralUser {

    public Admin(String username, String password, String role) {
        super(username, password, role);
    }

    @Override
    public String toString() {
        return "Admin{" + super.toString() + "}";
    }
}