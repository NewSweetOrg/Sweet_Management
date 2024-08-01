package services;

import models.NormalUser;
import models.Products;
import models.Store;

public class PurchaseService {

    public boolean purchaseDessertById(NormalUser user, int productId, ProductService productService, StoreService storeService) {
        Products product = productService.getProductById(productId);
        if (product == null) {
            return false; // Product not found
        }
        Store store = storeService.getStoreById(product.getStoreOrSupplierId());
        if (store == null) {
            return false; // Store not found
        }
        // Simulate purchase logic
        store.setSales(store.getSales() + product.getPrice());
        product.setTotalSold(product.getTotalSold() + product.getPrice());
        return true;
    }
}
