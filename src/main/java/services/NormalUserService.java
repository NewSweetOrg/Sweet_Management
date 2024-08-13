package services;

import models.NormalUser;
import java.util.HashMap;
import java.util.Map;

public class NormalUserService {
    private Map<String, NormalUser> users = new HashMap<>();
    private NormalUser signedInUser;

    public boolean signUp(String username, String password, String role) {
        if (!users.containsKey(username)) {
            users.put(username, new NormalUser(0, username, password, role, role, role));
            return true;
        }
        return false;
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
}
