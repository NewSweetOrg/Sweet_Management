package services;

import models.ProductsSup;

import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProductServiceSup {
    private static final Logger LOGGER = Logger.getLogger(ProductServiceSup.class.getName());
    private List<ProductsSup> productsSup = new LinkedList<>();
    private int nextId = 1;

    public ProductServiceSup() {
        addProduct(new ProductsSup(0, 1, "Chocolate Cake", 100, 50, "sugar-free"));
        addProduct(new ProductsSup(0, 2, "Cake", 200, 150, "gluten-free"));
    }

    public int addProduct(ProductsSup product) {
        product.setId(nextId++);
        productsSup.add(product);
        return product.getId();
    }

    public ProductsSup getProductById(int id) {
        for (ProductsSup product : productsSup) {
            if (product.getId() == id) {
                return product;
            }
        }
        return null;
    }

    public void updateProduct(int id, ProductsSup updatedProduct) {
        for (int i = 0; i < productsSup.size(); i++) {
            if (productsSup.get(i).getId() == id) {
                productsSup.set(i, updatedProduct);
                if (LOGGER.isLoggable(Level.INFO)) {
                    LOGGER.info(String.format("Product with ID %d updated successfully.", id));
                }
                return;
            }
        }
        if (LOGGER.isLoggable(Level.WARNING)) {
            LOGGER.warning(String.format("Product with ID %d not found.", id));
        }
    }

    public void printAllProductsSup() {
        if (productsSup.isEmpty()) {
            if (LOGGER.isLoggable(Level.INFO)) {
                LOGGER.info("No Products_Sup available.");
            }
        } else {
            for (ProductsSup product : productsSup) {
                if (LOGGER.isLoggable(Level.INFO)) {
                    LOGGER.info(product.toString());
                }
            }
        }
    }

    public void removeProduct(int id) {
        boolean removed = productsSup.removeIf(product -> product.getId() == id);
        if (removed) {
            if (LOGGER.isLoggable(Level.INFO)) {
                LOGGER.info(String.format("Product with ID %d removed successfully.", id));
            }
        } else {
            if (LOGGER.isLoggable(Level.WARNING)) {
                LOGGER.warning(String.format("Product with ID %d not found.", id));
            }
        }
    }
}
