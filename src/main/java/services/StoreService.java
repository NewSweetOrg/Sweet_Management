package services;

import models.Store;
import java.util.LinkedList;
import java.util.List;

public class StoreService {
    private List<Store> stores = new LinkedList<>();

    public void addStore(Store store) {
        stores.add(store);
    }

    public Store getStoreById(int id) {
        for (Store store : stores) {
            if (store.getId() == id) {
                return store;
            }
        }
        return null;
    }

    public void updateStore(int id, String name, String number, String address) {
        Store store = getStoreById(id);
        if (store != null) {
            store.setName(name);
            store.setPhone(Integer.parseInt(number)); // Assuming phone number is stored as an int
            store.setAddress(address);
        }
    }
}
