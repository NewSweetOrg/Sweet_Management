package services;

import models.Products;
import java.util.LinkedList;
import java.util.List;
import java.util.Iterator;


public class ProductService {
    private List<Products> products = new LinkedList<>();

    public void addProduct(Products product) {
        products.add(product);
    }

    public Products getProductById(int id) {
        for (Products product : products) {
            if (product.getId() == id) {
                return product;
            }
        }
        return null;
    }

    public List<Products> getProductsFromStores(StoreService storeService) {
        List<Products> results = new LinkedList<>();
        for (Products product : products) {
            if (storeService.getStoreById(product.getStoreOrSupplierId()) != null) {
                results.add(product);
            }
        }
        return results;
    }

    public void updateProduct(int id, Products updatedProduct) {
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getId() == id) {
                products.set(i, updatedProduct);
                break;
            }
        }
    }

    public void removeProduct(int id) {
        Iterator<Products> iterator = products.iterator();
        while (iterator.hasNext()) {
            Products product = iterator.next();
            if (product.getId() == id) {
                iterator.remove();  // Remove the product when it is found
                break;
            }
        }
    }
}
