package services;

import models.Products_Sup;


import java.util.LinkedList;
import java.util.List;

public class ProductService_Sup {
    private List<Products_Sup> Products_Sup = new LinkedList<>();
    private int nextId = 1; // To generate unique product IDs

    public ProductService_Sup() {
        // Add some initial Products_Sup to the list
        addProduct(new Products_Sup(0, 1, "Chocolate Cake", 100, 50, "sugar-free"));
        addProduct(new Products_Sup(0, 2, " Cake", 200, 150, "gluten-free"));
    }

    public int addProduct(Products_Sup product) {
        product.setId(nextId++); // Set the product ID and increment for the next product
        Products_Sup.add(product);
        return product.getId(); // Return the newly assigned ID
    }

    public Products_Sup getProductById(int id) {
        for (Products_Sup product : Products_Sup) {
            if (product.getId() == id) {
                return product;
            }
        }
        return null;
    }

    public void updateProduct(int id, Products_Sup updatedProduct) {
        for (int i = 0; i < Products_Sup.size(); i++) {
            if (Products_Sup.get(i).getId() == id) {
                Products_Sup.set(i, updatedProduct);
                break;
            }
        }
    }

    public void printAllProducts_Sup() {
        if (Products_Sup.isEmpty()) {
            System.out.println("No Products_Sup available.");
        } else {
            for (Products_Sup product : Products_Sup) {
                System.out.println(product); // Assuming the Products_Sup class has a meaningful toString() method
            }
        }
    }

    public void removeProduct(int id) {
        Products_Sup.removeIf(product -> product.getId() == id);
    }


}
