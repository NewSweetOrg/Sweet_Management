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
}
