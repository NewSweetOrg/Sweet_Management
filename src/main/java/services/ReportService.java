package services;

import models.Store;
import models.NormalUser;
import models.Products;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReportService {

    // Generates a financial report summarizing the total and average profits across all stores
    public Map<String, Object> generateFinancialReport(List<Store> stores) {
        Map<String, Object> report = new HashMap<>();
        double totalProfits = 0.0;

        for (Store store : stores) {
            totalProfits += store.calculateTotalProfits();  // Calculating the total profits for each store
        }

        report.put("totalStores", stores.size());
        report.put("totalProfits", totalProfits);
        report.put("averageProfits", stores.isEmpty() ? 0 : totalProfits / stores.size());

        return report;
    }

    // Generates a report listing the best-selling products for each store
    public Map<String, List<Products>> generateBestSellingProductsReport(List<Store> stores) {
        Map<String, List<Products>> bestSellingProductsReport = new HashMap<>();

        for (Store store : stores) {
     //       bestSellingProductsReport.put(store.getName(), store.getBestSellingProducts());  // Getting the best-selling products for each store
        }

        return bestSellingProductsReport;
    }

    // Generates a report on the number of users registered in each city
    public Map<String, Integer> generateUserStatisticsByCity(Collection<NormalUser> allUsers) {
        Map<String, Integer> cityUserCount = new HashMap<>();

        for (NormalUser user : allUsers) {
            String city = user.getCity();  // Assuming User class has a getCity() method
            cityUserCount.put(city, cityUserCount.getOrDefault(city, 0) + 1);
        }

        return cityUserCount;
    }
}
