package services;

import models.Products;
import java.util.List;

public class SalesService {
    private List<Products> products; // Assumes this list is populated elsewhere in your application

    public SalesService(List<Products> products) {
        this.products = products;
    }

    public int calculateTotalSales() {
        int total = 0;
        for (Products product : products) {
            total += product.getPrice() * product.getTotalSold();
        }
        return total;
    }

    public Products getBestSellingProduct() {
        if (products.isEmpty()) {
            return null;
        }
        Products bestSeller = products.get(0);
        for (Products product : products) {
            if (product.getTotalSold() > bestSeller.getTotalSold()) {
                bestSeller = product;
            }
        }
        return bestSeller;
    }
}

