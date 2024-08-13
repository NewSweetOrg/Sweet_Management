package services;

import models.NormalUser;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class UserService {
    private Map<String, Map<String, NormalUser>> usersByCity = new HashMap<>();

    public void addNormalUser(NormalUser normalUser) {
        String city = normalUser.getCity();
        usersByCity.putIfAbsent(city, new HashMap<>());
        usersByCity.get(city).put(normalUser.getUsername(), normalUser); // Use getUsername()
    }

    public void deleteNormalUser(String normalUsername) {
        for (Map<String, NormalUser> cityUsers : usersByCity.values()) {
            if (cityUsers.containsKey(normalUsername)) {
                cityUsers.remove(normalUsername);
                if (cityUsers.isEmpty()) {
                    usersByCity.values().removeIf(map -> map.isEmpty());
                }
                return;
            }
        }
    }

    public NormalUser getNormalUser(String normalUsername) {
        for (Map<String, NormalUser> cityUsers : usersByCity.values()) {
            if (cityUsers.containsKey(normalUsername)) {
                return cityUsers.get(normalUsername);
            }
        }
        return null;
    }

    public Collection<NormalUser> getAllNormalUsers() {
        Map<String, NormalUser> allUsers = new HashMap<>();
        for (Map<String, NormalUser> cityUsers : usersByCity.values()) {
            allUsers.putAll(cityUsers);
        }
        return allUsers.values();
    }

    public Map<String, NormalUser> getUsersByCity(String city) {
        return usersByCity.getOrDefault(city, new HashMap<>());
    }

    public Collection<String> getAllCities() {
        return usersByCity.keySet();
    }
}