package services;

import models.Store;
import models.NormalUser;
import models.Products;

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

        report.put("totalStores", stores.size());
        report.put("totalProfits", totalProfits);
        report.put("averageProfits", stores.isEmpty() ? 0 : totalProfits / stores.size());

        return report;
    }


    public Map<String, List<Products>> generateBestSellingProductsReport() {
        return new HashMap<>();
    }


    public Map<String, Integer> generateUserStatisticsByCity(Collection<NormalUser> allUsers) {
        Map<String, Integer> cityUserCount = new HashMap<>();

        for (NormalUser user : allUsers) {
            String city = user.getCity();
            cityUserCount.put(city, cityUserCount.getOrDefault(city, 0) + 1);
        }

        return cityUserCount;
    }
}
