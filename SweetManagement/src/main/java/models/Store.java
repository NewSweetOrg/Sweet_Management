package models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Store {
    private String name;
    private double totalSales;
    private Map<Product, Integer> salesData;

    public Store(String name, double initialSales) {
        this.name = name;
        this.totalSales = initialSales;
        this.salesData = new HashMap<>();
    }

    public String getName() {
        return name;
    }

    public double getTotalSales() {
        return totalSales;
    }

    public void addSalesData(Product product, int quantity) {
        salesData.put(product, salesData.getOrDefault(product, 0) + quantity);
        totalSales += product.getPrice() * quantity;
    }

    public Map<Product, Integer> getSalesData() {
        return new HashMap<>(salesData);
    }

    public List<Product> getBestSellingProducts() {
        List<Product> bestSellingProducts = new ArrayList<>();
        int maxSales = 0;
        for (Map.Entry<Product, Integer> entry : salesData.entrySet()) {
            if (entry.getValue() > maxSales) {
                bestSellingProducts.clear();
                bestSellingProducts.add(entry.getKey());
                maxSales = entry.getValue();
            } else if (entry.getValue() == maxSales) {
                bestSellingProducts.add(entry.getKey());
            }
        }
        return bestSellingProducts;
    }

    public double calculateTotalProfits() {
        double costOfGoodsSold = calculateCostOfGoodsSold(); // حساب تكلفة البضائع المباعة
        return totalSales - costOfGoodsSold;
    }

    private double calculateCostOfGoodsSold() {
        double cost = 0.0;
        for (Map.Entry<Product, Integer> entry : salesData.entrySet()) {
            cost += entry.getKey().getCost() * entry.getValue(); // افترض أن هناك دالة getCost() في الكائن Product
        }
        return cost;
    }
}
