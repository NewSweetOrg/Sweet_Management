package services;

import models.Products;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ProductService {
    public List<Products> products = new LinkedList<>();
   
    private int nextId = 1; // To generate unique product IDs

    public ProductService() {
        // Add three products to the list
        addProduct(new Products(0, 1, "Chocolate Cake", 10, 50 ,"suger-free"));
        addProduct(new Products(0, 2, "Product B", 200, 150 , "gluten-free"));
      
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
                break;
            }
        }
    }

 

    public void printAllProducts() {
        if (products.isEmpty()) {
            System.out.println("No products available.");
        } else {
            for (Products product : products) {
                System.out.println(product); // Assuming the Products class has a meaningful toString() method
            }
        }
    }
    
  

    public void removeProduct(int id) {
        products.removeIf(product -> product.getId() == id);
    }
 
	  public void searchProductsByName(String searchQuery) {
	    List<Products> matchingProducts = new ArrayList<>();

	    for (Products product : products) {
	        if (product.getName().toLowerCase().contains(searchQuery.toLowerCase())) {
	            matchingProducts.add(product);
	        }
	    }

	    if (matchingProducts.isEmpty()) {
	        System.out.println("No products found matching the search query.");
	    } else {
	        System.out.println("Search Results:");
	        for (Products product : matchingProducts) {
	            System.out.println("ID: " + product.getId() + ", Name: " + product.getName() + ", Price: $" + product.getPrice());
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
		        System.out.println("No products found matching the dietary need or allergy.");
		    } else {
		        System.out.println("Filtered Products:");
		        for (Products product : filteredProducts) {
		            System.out.println("ID: " + product.getId() + ", Name: " + product.getName() + ", Price: $" + product.getPrice());
		        }
		    }
		}

	public List<Products> getProductsFromStores(StoreService storeService) {
		// TODO Auto-generated method stub
		return null;
	}

 

	   
}