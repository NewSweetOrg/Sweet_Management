package services;

import models.Products;
import java.util.LinkedList;
import java.util.List;

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
}
