package services;

import models.Products;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProductService {
    private static final Logger LOGGER = Logger.getLogger(ProductService.class.getName());
    public List<Products> products = new LinkedList<>();
    private int nextId = 1; // To generate unique product IDs

    public ProductService() {
        // Add three products to the list
        addProduct(new Products(0, 1, "Chocolate Cake", 10, 50, "sugar-free"));
        addProduct(new Products(0, 2, "Product B", 200, 150, "gluten-free"));
    }

    public int addProduct(Products product) {
        product.setId(nextId++); // Set the product ID and increment for next product
        products.add(product);
        return product.getId(); // Return the newly assigned ID
    }

    public Products getProductById(int id) {
        for (Products product : products) {
            if (product.getId() == id) {
                return product;
            }
        }
        return null;
    }

    public void updateProduct(int id, Products updatedProduct) {
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getId() == id) {
                products.set(i, updatedProduct);
                LOGGER.info(String.format("Product with ID %d updated successfully.", id));
                return;
            }
        }
        LOGGER.warning(String.format("Product with ID %d not found.", id));
    }

    public void printAllProducts() {
        if (products.isEmpty()) {
            LOGGER.info("No products available.");
        } else {
            for (Products product : products) {
                LOGGER.info(product.toString()); // Assuming the Products class has a meaningful toString() method
            }
        }
    }

    public void removeProduct(int id) {
        boolean removed = products.removeIf(product -> product.getId() == id);
        if (removed) {
            LOGGER.info(String.format("Product with ID %d removed successfully.", id));
        } else {
            LOGGER.warning(String.format("Product with ID %d not found.", id));
        }
    }

    public void searchProductsByName(String searchQuery) {
        List<Products> matchingProducts = new ArrayList<>();
        for (Products product : products) {
            if (product.getName().toLowerCase().contains(searchQuery.toLowerCase())) {
                matchingProducts.add(product);
            }
        }

        if (matchingProducts.isEmpty()) {
            LOGGER.info("No products found matching the search query.");
        } else {
            LOGGER.info("Search Results:");
            for (Products product : matchingProducts) {
                LOGGER.info(String.format("ID: %d, Name: %s, Price: $%d", product.getId(), product.getName(), product.getPrice()));
            }
        }
    }

    public void filterProductsByDietaryNeeds(String filter) {
        List<Products> filteredProducts = new ArrayList<>();
        for (Products product : products) {
            if (product.getDietaryInfo().toLowerCase().contains(filter.toLowerCase())) {
                filteredProducts.add(product);
            }
        }

        if (filteredProducts.isEmpty()) {
            LOGGER.info("No products found matching the dietary need or allergy.");
        } else {
            LOGGER.info("Filtered Products:");
            for (Products product : filteredProducts) {
                LOGGER.info(String.format("ID: %d, Name: %s, Price: $%d", product.getId(), product.getName(), product.getPrice()));
            }
        }
    }

    public List<Products> getProductsFromStores(StoreService storeService) {
        // Return an empty list instead of null
        return new ArrayList<>();
    }
}
