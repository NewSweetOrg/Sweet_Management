package services;

 

import  models.Product;
import  models.Store;

import java.util.ArrayList;
import java.util.List;

public class StoreService {
    private List<Store> stores;

    public StoreService() {
        this.stores = new ArrayList<>();
    }

    public void addStore(Store store) {
        stores.add(store);
    }

    public List<Store> getAllStores() {
        return new ArrayList<>(stores);
    }

    public Store getStoreByName(String name) {
        for (Store store : stores) {
            if (store.getName().equals(name)) {
                return store;
            }
        }
        return null; // Or throw an exception if the store is not found
    }

    public void addSalesData(String storeName, Product product, int quantity) {
        Store store = getStoreByName(storeName);
        if (store != null) {
            store.addSalesData(product, quantity);
        }
    }
}
