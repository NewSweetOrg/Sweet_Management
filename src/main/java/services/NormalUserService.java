package services;

import models.NormalUser;
import java.util.HashMap;
import java.util.Map;

public class NormalUserService {
    private Map<String, NormalUser> users = new HashMap<>();
    private NormalUser signedInUser;

    public boolean signUp(String username, String password, String role) {
        if (users.containsKey(username)) {
            return false; // User already exists
        }
        // users.size() will probably cause a bug for example if some users are deleted
        //also its not even necessary use the username as an id since its unique
        //so use this (username.hashCode()),instead of previous form
        NormalUser user = new NormalUser(username, password, role, users.size()+1, "", "", "");
        users.put(username, user);
        return true;
    }

    public boolean signIn(String username, String password) {
        NormalUser user = users.get(username);
        if (user != null && user.getPassword().equals(password)) {
            signedInUser = user;
            return true;
        }
        return false;
    }

    public NormalUser getSignedInUser() {
        return signedInUser;
    }

    public boolean updateUser(String phoneNumber, String city) {
        if (signedInUser != null) {
            if (!phoneNumber.isEmpty()) {
                signedInUser.setPhone(phoneNumber);
            }
            if (!city.isEmpty()) {
                signedInUser.setCity(city);
            }
            return true;
        }
        return false;
    }
}
