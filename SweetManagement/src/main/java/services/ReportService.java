package services;

 

import  models.Store;
import models.User;
import  models.Product;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReportService {

    public Map<String, Object> generateFinancialReport(List<Store> stores) {
        Map<String, Object> report = new HashMap<>();
        double totalProfits = 0.0;

        for (Store store : stores) {
            totalProfits += store.calculateTotalProfits();
        }

        report.put("totalProfits", totalProfits);
        return report;
    }

    public Map<String, List<Product>> generateBestSellingProductsReport(List<Store> stores) {
        Map<String, List<Product>> bestSellingProductsReport = new HashMap<>();

        for (Store store : stores) {
            bestSellingProductsReport.put(store.getName(), store.getBestSellingProducts());
        }

        return bestSellingProductsReport;
    }

    public Map<String, Integer> generateUserStatisticsByCity(Collection<User> allUsers) {
        Map<String, Integer> cityUserCount = new HashMap<>();

        for (User user : allUsers) {
            String city = user.getCity();  // Assuming User class has a getCity() method
            cityUserCount.put(city, cityUserCount.getOrDefault(city, 0) + 1);
        }

        return cityUserCount;
    }
}
