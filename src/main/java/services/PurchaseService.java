package services;

import models.NormalUser;
import models.Products;
import models.Store;

import java.util.HashMap;
import java.util.Map;

public class PurchaseService {


    private Map<String, String> orders = new HashMap<>();


    public boolean purchaseDessertById(NormalUser user, int productId, ProductService productService, StoreService storeService) {
        Products product = productService.getProductById(productId);
        if (product == null) {
            return false;
        }

        Store store = storeService.getStoreById(product.getStoreOrSupplierId());
        if (store == null) {
            return false;
        }


        store.setSales(store.getSales() + product.getPrice());
        product.setTotalSold(product.getTotalSold() + 1);


        String orderId = user.getId() + "-" + productId;

        orders.put(orderId, "delivered");

        return true;
    }

}

