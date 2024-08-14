package SweetMang;
//tsts
import models.*;
import services.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
public class Main {
    private static NormalUser user; 
    
    private static ProductService productService = new ProductService();
    private static ProductService_Sup ProductService_Sup = new ProductService_Sup();
 
    private static int fID;  
 
    private static List<NormalUser> userList = new ArrayList<>();
    private static List<Store> storeList = new ArrayList<>();
    private static List<Supplier> supplierList = new ArrayList<>();
    private static List<Products> productList = new ArrayList<>();
    private static List<Feedback> feedbackList = new ArrayList<>();
    private static Map<Integer, List<Message>> messageBox = new HashMap<>();
   
 
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    while (true) {
        // Step 1: Choose to log in or create an account
        System.out.println("Welcome to the Sweet Management System!");
        System.out.println("1. Log in");
        System.out.println("2. Create an account");
        System.out.println("3. Exit");
        System.out.print("Choose an option (1, 2, or 3): ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        if (choice == 3) {
            System.out.println("Thank you for using the Sweet Management System. Goodbye!");
            break;
        }

        if (choice == 2) {
            // Step 2: Account Creation
            System.out.println("Create an account:");
            
            System.out.print("Enter your name: ");
            String name = scanner.nextLine();
            
            System.out.print("Enter your password: ");
            String password = scanner.nextLine();
            
            System.out.print("Enter your city: ");
            String city = scanner.nextLine();
            
            System.out.print("Enter your phone number: ");
            String phone = scanner.nextLine();
         
            System.out.println("Select your role:");
            System.out.println("1. Admin");
            System.out.println("2. StoreOwner");
            System.out.println("3. Supplier");
            System.out.println("4. NormalUser");
            System.out.print("Enter the number corresponding to your role: ");
            int roleChoice = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            
            String role = "";
            switch (roleChoice) {
                case 1:
                    role = "Admin";
                    break;
                case 2:
                    role = "StoreOwner";
                    break;
                case 3:
                    role = "Supplier";
                    break;
                case 4:
                    role = "NormalUser";
                    break;
                default:
                    System.out.println("Invalid role choice.");
                    continue;
            }
            
            NormalUser newUser = new NormalUser(userList.size(), name, password, city, phone,  role);
            userList.add(newUser);
            System.out.println("\nAccount created successfully!");
        }

        if (choice == 1 || choice == 2) {
            // Step 3: Login
            boolean loginSuccess = false;
            
            while (!loginSuccess) {
                System.out.println("\nPlease log in.");
                System.out.print("Enter your name: ");
                String loginName = scanner.nextLine();
                
                System.out.print("Enter your password: ");
                String loginPassword = scanner.nextLine();
                
                System.out.println("Select your role:");
                System.out.println("1. Admin");
                System.out.println("2. StoreOwner");
                System.out.println("3. Supplier");
                System.out.println("4. NormalUser");
                System.out.print("Enter the number corresponding to your role: ");
                int loginRoleChoice = scanner.nextInt();
                scanner.nextLine(); // Consume  newline
                
                String loginRole = "";
                switch (loginRoleChoice) {
                    case 1:
                        loginRole = "Admin";
                        break;
                    case 2:
                        loginRole = "StoreOwner";
                        break;
                    case 3:
                        loginRole = "Supplier";
                        break;
                    case 4:
                        loginRole = "NormalUser";
                        break;
                    default:
                        System.out.println("Invalid role choice.");
                        continue;
                }

                // Verify login details
                for (NormalUser u : userList) {
                    if (loginName.equals(u.getName()) && loginPassword.equals(u.getPassword()) && loginRole.equals(u.getRole())) {
                        loginSuccess = true;
                        user = u; // Set the current user
                        System.out.println("Login successful!");

                        // Direct the user based on their role
                        switch (loginRole.toLowerCase()) {
                            case "admin":
                                adminDashboard();
                                break;
                            case "storeowner":
                                storeOwnerDashboard();
                                break;
                            case "supplier":
                                supplierDashboard();
                                break;
                            case "normaluser":
                                normalUserDashboard(user);
                                break;
                        }
                        break; // Exit the for loop once a match is found
                    }
                }
                
                if (!loginSuccess) {
                    System.out.println("Invalid login details. Please try again.");
                }
            }
        }
    }

    scanner.close();
}  
  
  public static void adminDashboard() {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("==============================");
            System.out.println("   Admin Dashboard");
            System.out.println("==============================");
            System.out.println("1. Manage Users");
            System.out.println("2. Generate Financial Reports");
            System.out.println("3. View Best-Selling Products");
            System.out.println("4. Sort Users by City");
            System.out.println("5. Manage Content(Posts) and Feedback");
            System.out.println("6. Log Out");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                	manageUsersadmin();
                    break;
                case 2:
                    generateFinancialReports();
                    break;
                case 3:
                    viewBestSellingProducts();
                    break;
                case 4:
                    sortUsersByCity();
                    break;
                case 5:
                    manageContentAndFeedback();
                    break;
                case 6:
                    exit = true;
                    System.out.println("Logging out...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void manageUsersadmin() {
        Scanner scanner = new Scanner(System.in);
        boolean back = false;

        while (!back) {
            System.out.println("User Management");
            System.out.println("1. View All Users");
            System.out.println("2. Add User");
            System.out.println("3. Update User");
            System.out.println("4. Delete User");
            System.out.println("5. Back to Admin Dashboard");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    viewAllUsers();
                    break;
                case 2:
                    addUser();
                    break;
                case 3:
                    updateUser();
                    break;
                case 4:
                    deleteUser();
                    break;
                case 5:
                    back = true;
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    private static void viewAllUsers() {
        System.out.println("All Users:");
        for (NormalUser user : userList) {
            System.out.println(user);
        }
    }

    private static void addUser() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Add New User");
        
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        
        System.out.print("Enter city: ");
        String city = scanner.nextLine();
        
        System.out.print("Enter phone: ");
        String phone = scanner.nextLine();
     
        
        System.out.println("Select role (1. Admin, 2. StoreOwner, 3. Supplier, 4. NormalUser): ");
        int roleChoice = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        
        String role = "";
        switch (roleChoice) {
            case 1: role = "Admin"; break;
            case 2: role = "StoreOwner"; break;
            case 3: role = "Supplier"; break;
            case 4: role = "NormalUser"; break;
            default: System.out.println("Invalid role. Setting as NormalUser."); role = "NormalUser";
        }
        
        NormalUser newUser = new NormalUser(userList.size(), name, password, city, phone,  role);
        userList.add(newUser);
        System.out.println("User added successfully.");
    }

    private static void updateUser() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the ID of the user to update: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        NormalUser userToUpdate = null;
        for (NormalUser user : userList) {
            if (user.getId() == id) {
                userToUpdate = user;
                break;
            }
        }

        if (userToUpdate == null) {
            System.out.println("User not found.");
            return;
        }

        System.out.println("Current user details: " + userToUpdate);
        System.out.println("Enter new details (press enter to keep current value):");

        System.out.print("New name: ");
        String name = scanner.nextLine();
        if (!name.isEmpty()) userToUpdate.setName(name);

        System.out.print("New password: ");
        String password = scanner.nextLine();
        if (!password.isEmpty()) userToUpdate.setPassword(password);

        System.out.print("New city: ");
        String city = scanner.nextLine();
        if (!city.isEmpty()) userToUpdate.setCity(city);

        System.out.print("New phone: ");
        String phone = scanner.nextLine();
        if (!phone.isEmpty()) userToUpdate.setPhone(phone);

        System.out.println("User updated successfully.");
    }

    private static void deleteUser() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the ID of the user to delete: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        NormalUser userToDelete = null;
        for (NormalUser user : userList) {
            if (user.getId() == id) {
                userToDelete = user;
                break;
            }
        }

        if (userToDelete == null) {
            System.out.println("User not found.");
            return;
        }

        userList.remove(userToDelete);
        System.out.println("User deleted successfully.");
    }

    private static void generateFinancialReports() {
        System.out.println("Financial Reports");
        
        // Report for Stores
        System.out.println("Store Reports:");
        for (Store store : storeList) {
            double totalSales = 0;
            double totalProfit = 0;
            for (Products product : productList) {
                if (product.getStoreId() == store.getId()) {
                    totalSales += product.getPrice() * product.getTotalSold();
                    totalProfit += (product.getPrice() - product.getPrice()) * product.getTotalSold();
                }
            }
            System.out.println("Store: " + store.getName());
            System.out.println("Total Sales: $" + totalSales);
            System.out.println("Total Profit: $" + totalProfit);
            System.out.println("-------------------------");
        }

        // Report for Suppliers
        System.out.println("Supplier Reports:");
        for (Supplier supplier : supplierList) {
            double totalSales = 0;
            for (Products product : productList) {
                if (product.getStoreOrSupplierId() == supplier.getId()) {
                    totalSales += product.getPrice() * product.getTotalSold();
                }
            }
            System.out.println("Supplier: " + supplier.getName());
            System.out.println("Total Sales: $" + totalSales);
            System.out.println("-------------------------");
        }
    }

    private static void viewBestSellingProducts() {
        System.out.println("Best-Selling Products");

        // Sort products by total sold
        Collections.sort(productList, (p1, p2) -> Integer.compare(p2.getTotalSold(), p1.getTotalSold()));

        // Display top 5 best-selling products
        System.out.println("Top 5 Best-Selling Products:");
        for (int i = 0; i < Math.min(5, productList.size()); i++) {
            Products product = productList.get(i);
            System.out.println((i+1) + ". " + product.getName() + " - Total Sold: " + product.getTotalSold());
        }
    }

    private static void sortUsersByCity() {
        System.out.println("Users Sorted by City");
        Map<String, List<NormalUser>> usersByCity = new HashMap<>();

        for (NormalUser user : userList) {
            usersByCity.computeIfAbsent(user.getCity(), k -> new ArrayList<>()).add(user);
        }

        for (Map.Entry<String, List<NormalUser>> entry : usersByCity.entrySet()) {
            System.out.println("City: " + entry.getKey());
            for (NormalUser user : entry.getValue()) {
                System.out.println("  " + user.getName() + " - " + user.getRole());
            }
            System.out.println("-------------------------");
        }
    }

    private static void manageContentAndFeedback() {
        Scanner scanner = new Scanner(System.in);
        boolean back = false;

        while (!back) {
            System.out.println("Content and Feedback Management");
            System.out.println("1. Manage Store Posts");
            System.out.println("2. Manage User Feedback");
            System.out.println("3. Back to Admin Dashboard");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    manageStorePosts();
                    break;
                case 2:
                    manageUserFeedback();
                    break;
                case 3:
                    back = true;
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    private static void manageStorePosts() {
    	  Scanner scanner = new Scanner(System.in);
    	    PostService postService = new PostService(); // Assuming you have a PostService instance
    	    
    	    boolean exit = false;
    	    while (!exit) {
    	        System.out.println("==============================");
    	        System.out.println("   Manage Content (Posts) and Feedback");
    	        System.out.println("==============================");
    	        System.out.println("1. View All Posts");
    	        System.out.println("2. Add a New Post");
    	        System.out.println("3. Delete a Post");
    	        System.out.println("4. Go Back");
    	        System.out.print("Choose an option: ");

    	        int choice = scanner.nextInt();
    	        scanner.nextLine(); // Consume newline

    	        switch (choice) {
    	            case 1:
    	                viewPosts(postService);
    	                break;
    	            case 2:
    	                addPost(scanner, postService);
    	                break;
    	            case 3:
    	                deletePost(scanner, postService);
    	                break;
    	            case 4:
    	            
    	                exit = true;
    	                break;
    	            default:
    	                System.out.println("Invalid choice. Please try again.");
    	        }
    	    }
    }

    private static void addPost(Scanner scanner, PostService postService) {
        System.out.print("Enter post title: ");
        String title = scanner.nextLine();

        System.out.print("Enter post tag: ");
        String tag = scanner.nextLine();

        System.out.print("Enter post description: ");
        String description = scanner.nextLine();

        int id = postService.getPosts().size() + 1; // Generate a new ID based on the number of existing posts
        Posts newPost = new Posts(id, 0, title, tag, description); // Set normalUserId as 0 or adjust accordingly
        postService.addPost(newPost);

        System.out.println("Post added successfully.");
    }

    private static void deletePost(Scanner scanner, PostService postService) {
        System.out.print("Enter the ID of the post to delete: ");
        int postId = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        List<Posts> posts = postService.getPosts();
        boolean postFound = false;
        for (Posts post : posts) {
            if (post.getId() == postId) {
                posts.remove(post);
                System.out.println("Post deleted successfully.");
                postFound = true;
                break;
            }
        }

        if (!postFound) {
            System.out.println("Post not found.");
        }
    }

    private static void manageUserFeedback() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("User Feedback:");
        for (Feedback feedback : feedbackList) {
            System.out.println(feedback);
            System.out.println("1. Keep  2. Remove");
            int action = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            if (action == 2) {
                feedbackList.remove(feedback);
                System.out.println("Feedback removed.");
            }
        }
    }
   

    ///////////////////////////////
    ///////////////////////////////
    ////////////////////////////
    
 public static void storeOwnerDashboard() {
    Scanner scanner = new Scanner(System.in);
    PostService postService = new PostService();
    boolean logout = false;

    while (!logout) {
        System.out.println("==============================");
        System.out.println("   Store Owner Dashboard");
        System.out.println("==============================");
        System.out.println("1. Manage Products");
        System.out.println("2. Monitor Sales and Profits");
        System.out.println("3. Manage Orders");
        System.out.println("4. Manage Account Information");
        System.out.println("5. View Product Feedback");
        System.out.println("6. Communication Center");
        System.out.println("7. Create a Post");
        System.out.println("8. Log Out");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        switch (choice) {
            case 1:
                manageProducts();
                break;
            case 2:
                monitorSalesAndProfits();
                break;
            case 3:
                manageOrders();
                break;
            case 4:
                manageAccountInformation();
                break;
            case 5:
                viewProductFeedback();
                break;
            case 6:
                communicationCenter(user);
                break;
            case 7:
                createPost(postService, user);
                break;
            case 8:
                System.out.println("Logging out...");
                logout = true;
                main(null);
                break;
            default:
                System.out.println("Invalid choice.");
                break;
        }
    }
}

// Method to create a post
private static void createPost(PostService postService, NormalUser user) {
    Scanner scanner = new Scanner(System.in);

    System.out.print("Enter post title: ");
    String title = scanner.nextLine();

    System.out.print("Enter post tag: ");
    String tag = scanner.nextLine();

    System.out.print("Enter post description: ");
    String description = scanner.nextLine();

    Posts post = new Posts(user.getId(), user.getId(), title, tag, description);
    postService.addPost(post);

    System.out.println("Post created successfully!");
}

    /////////////////////////////////////
   
    //////// Suplier ///////////////
     public static void supplierDashboard() {
        Scanner scanner = new Scanner(System.in);
        boolean logout = false;

        while (!logout) {
            System.out.println("==============================");
            System.out.println("   Supplier Dashboard");
            System.out.println("==============================");
            System.out.println("1. Manage Products");
            System.out.println("2. Manage Orders");
            System.out.println("3. Manage Account Information");
            System.out.println("4. Communication Center");
            System.out.println("5. Log Out");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    manageSupProducts();
                    break;
                case 2:
                    manageOrders();
                    break;
                case 3:
                    manageAccountInformation();
                    break;
                case 4:
                    communicationCenter(user);
                    break;
                case 5:
                    System.out.println("Logging out...");
                    logout = true;
                    main(null);
                    break;
                default:
                    System.out.println("Invalid choice.");
                    break;
            }
        }
    }  
    ///////////////////////////////////////////
    ////////////////////////////////
    
 public static void normalUserDashboard(NormalUser user) {
    Scanner scanner = new Scanner(System.in);
    PostService postService = new PostService();
    boolean logout = false;

    while (!logout) {
        System.out.println("==============================");
        System.out.println("   User Dashboard");
        System.out.println("==============================");
        System.out.println("1. Manage Account");
        System.out.println("2. Explore and Buy Sweets");
        System.out.println("3. Communication Center");
        System.out.println("4. View Posts");
        System.out.println("5. Log Out");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        switch (choice) {
            case 1:
                manageAccount(user);
                break;
            case 2:
                exploreAndBuySweets();
                break;
            case 3:
                communicationCenter(user);
                break;
            case 4:
                viewPosts(postService);
                break;
            case 5:
                System.out.println("Logging out...");
                logout = true;
                main(null);
                break;
            default:
                System.out.println("Invalid choice.");
                break;
        }
    }
}

 private static void viewPosts(PostService postService) {
	    List<Posts> posts = postService.getPosts();

	    if (posts.isEmpty()) {
	        System.out.println("No posts available.");
	    } else {
	        System.out.println("Available Posts:");
	        int index = 1;
	        for (Posts post : posts) {
	            System.out.println(index + "-");
	            System.out.println(" Title: " + post.getTitle());
	            System.out.println(" Tag: " + post.getTag());
	            System.out.println(" Description: " + post.getDescription());
	            System.out.println();
	            index++;
	        }
	    }
	}

    public static void manageProducts() {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("==============================");
            System.out.println("   Manage Products");
            System.out.println("==============================");
            System.out.println("Choose an option:");
            System.out.println("1. Add a new product");
            System.out.println("2. Update an existing product");
            System.out.println("3. Remove a product");
            System.out.println("4. View all products");
            System.out.println("5. Back to Store Owner Dashboard");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter product name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter product price: ");
                    int price = scanner.nextInt();
                    System.out.print("Enter total sold: ");
                    int totalSold = scanner.nextInt();
                    System.out.print("Enter the dietary Info : ");
                    String dietaryInfo = scanner.nextLine();
                    scanner.nextLine();// Consume newline

                    // Assume storeId is 1 for this example
                    int storeId = 1;
                    Products product = new Products(0, storeId, name, price, totalSold ,dietaryInfo);
                    productService.addProduct(product);
                   
                    System.out.println("Product added ");
                    break;

                case 2:
                    System.out.print("Enter product ID to update: ");
                    int id = scanner.nextInt();
                    scanner.nextLine(); // Consume newline

                    Products existingProduct = productService.getProductById(id);
                    if (existingProduct == null) {
                        System.out.println("Product not found.");
                        break;
                    }

                    System.out.print("Enter new name (leave empty to keep current): ");
                    String newName = scanner.nextLine();
                    if (!newName.isEmpty()) existingProduct.setName(newName);

                    System.out.print("Enter new price (leave empty to keep current): ");
                    String priceInput = scanner.nextLine();
                    if (!priceInput.isEmpty()) existingProduct.setPrice(Integer.parseInt(priceInput));

                    System.out.print("Enter new total sold (leave empty to keep current): ");
                    String totalSoldInput = scanner.nextLine();
                    if (!totalSoldInput.isEmpty()) existingProduct.setTotalSold(Integer.parseInt(totalSoldInput));
                   
                    System.out.print("Enter dietary Info (leave empty to keep current): ");
                    String dietary_Info = scanner.nextLine();
                    if (!dietary_Info.isEmpty()) existingProduct.setDietaryInfo(dietary_Info);

                    productService.updateProduct(id, existingProduct);
                    System.out.println("Product updated.");
                    break;

                case 3:
                    System.out.print("Enter product ID to remove: ");
                    int removeId = scanner.nextInt();
                    scanner.nextLine(); // Consume newline

                    productService.removeProduct(removeId);
                    System.out.println("Product removed.");
                    break;

                case 4:
                    System.out.println("All Products:");
                    
                        
                            productService.printAllProducts();
                         
                    
                    break;

                case 5:
                    running = false;
                    storeOwnerDashboard();
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
        scanner.close();
    }

 

 public static void manageSupProducts() {
     Scanner scanner = new Scanner(System.in);
     boolean running = true;

     while (running) {
         System.out.println("==============================");
         System.out.println("   Manage Products");
         System.out.println("==============================");
         System.out.println("Choose an option:");
         System.out.println("1. Add a new product");
         System.out.println("2. Update an existing product");
         System.out.println("3. Remove a product");
         System.out.println("4. View all products");
         System.out.println("5. Back to Store Owner Dashboard");
         System.out.print("Enter your choice: ");

         int choice = scanner.nextInt();
         scanner.nextLine(); // Consume newline

         switch (choice) {
             case 1:
                 System.out.print("Enter product name: ");
                 String name = scanner.nextLine();
                 System.out.print("Enter product price: ");
                 int price = scanner.nextInt();
                 System.out.print("Enter total sold: ");
                 int totalSold = scanner.nextInt();
                 scanner.nextLine(); 
 

                 // Assume storeId is 1 for this example
                
                 Products_Sup product = new Products_Sup(0, name, price, totalSold  );
                 ProductService_Sup.addProduct(product);
                
                 System.out.println("Product added ");
                 break;

             case 2:
                 System.out.print("Enter product number to update: ");
                 int id = scanner.nextInt();
                 scanner.nextLine(); // Consume newline

                 Products_Sup existingProduct = ProductService_Sup.getProductById(id);
                 if (existingProduct == null) {
                     System.out.println("Product not found.");
                     break;
                 }

                 System.out.print("Enter new name (leave empty to keep current): ");
                 String newName = scanner.nextLine();
                 if (!newName.isEmpty()) existingProduct.setName(newName);

                 System.out.print("Enter new price (leave empty to keep current): ");
                 String priceInput = scanner.nextLine();
                 if (!priceInput.isEmpty()) existingProduct.setPrice(Integer.parseInt(priceInput));

                 System.out.print("Enter new total sold (leave empty to keep current): ");
                 String totalSoldInput = scanner.nextLine();
                 if (!totalSoldInput.isEmpty()) existingProduct.setTotalSold(Integer.parseInt(totalSoldInput));

                 ProductService_Sup.updateProduct(id, existingProduct);
                 System.out.println("Product updated.");
                 break;

             case 3:
                 System.out.print("Enter product ID to remove: ");
                 int removeId = scanner.nextInt();
                 scanner.nextLine(); // Consume newline

                 ProductService_Sup.removeProduct(removeId);
                 System.out.println("Product removed.");
                 break;

             case 4:
                 System.out.println("All Products:");
                 
                     
                 ProductService_Sup.printAllProducts_Sup();
                      
                 
                 break;

             case 5:
                 running = false;
                 supplierDashboard();
                 break;

             default:
                 System.out.println("Invalid choice. Please try again.");
         }
     }
     scanner.close();
 }

    // Admin specific functionalities
    public static void manageUsers() {
        System.out.println("Implementing manageUsers() functionality...");
    }

    public static void monitorProfitsAndReports() {
        System.out.println("Implementing monitorProfitsAndReports() functionality...");
    }

    public static void manageContent() {
        System.out.println("Implementing manageContent() functionality...");
    }

    // Store Owner specific functionalities
    public static void monitorSalesAndProfits() {
        System.out.println("==============================");
        System.out.println("   Monitor Sales and Profits");
        System.out.println("==============================");

        // Calculate total sales and profits
        List<Products> allProducts = productService.products;
        int totalSales = 0;
        int totalProfit = 0;

        for (Products product : allProducts) {
            int productSales = product.getPrice() * product.getTotalSold();
            int productProfit = (int) (product.getPrice() - (product.getPrice() * (product.getTotalSold() > 100 ? 0.20 : 0))); // Assuming a 20% discount for products sold over 100 units
            
            totalSales += productSales;
            totalProfit += productProfit * product.getTotalSold();
        }

        System.out.println("Total Sales: $" + totalSales);
        System.out.println("Total Profit: $" + totalProfit);
        
        System.out.println("\nProduct Details:");
        for (Products product : allProducts) {
            System.out.println(product);
        }
    }

    public static void manageOrders() {
        Scanner scanner = new Scanner(System.in);
        OrderService orderService = new OrderService(); // Assume this is initialized and used properly

        boolean running = true;

        while (running) {
            System.out.println("==============================");
            System.out.println("   Manage Orders");
            System.out.println("==============================");
            System.out.println("Choose an option:");
            System.out.println("1. View all orders");
            System.out.println("2. Update order status");
            System.out.println("3. Back to Previous Menu");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    List<Order> orders = orderService.getAllOrders();
                    System.out.println("All Orders:");
                    for (Order order : orders) {
                        System.out.println(order);
                    }
                    if(orders == null) {
                    	 System.out.println("The Orders is Empity... ");
                    }
                    break;

                case 2:
                    System.out.print("Enter the order ID to update: ");
                    int orderId = scanner.nextInt();
                    scanner.nextLine(); // Consume newline

                    System.out.print("Enter the new status (e.g., 'Pending', 'Shipped', 'Delivered'): ");
                    String newStatus = scanner.nextLine();

                    orderService.updateOrderStatus(orderId, newStatus);
                    System.out.println("Order status updated.");
                    break;

                case 3:
                    running = false;
                    supplierDashboard();
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }


    public static void communicateWithUsersAndSuppliers() {
        Scanner scanner = new Scanner(System.in);
        UserService userService = new UserService(); // Assuming you have an instance of UserService
        SupplierService supplierService = new SupplierService(); // Assuming you have an instance of SupplierService

        boolean exit = false;
        
        while (!exit) {
            System.out.println("==============================");
            System.out.println("   Communicate with Users and Suppliers");
            System.out.println("==============================");
            System.out.println("Choose an option:");
            System.out.println("1. Send a message to a User");
            System.out.println("2. Send a message to a Supplier");
            System.out.println("3. View received messages");
            System.out.println("4. Back to Store Owner Dashboard");
            System.out.print("Enter your choice: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    sendMessageToNormalUser(scanner, userService);
                    break;
                case 2:
                    sendMessageToSupplier(scanner, supplierService);
                    break;
                case 3:
                    viewReceivedMessages(scanner, userService, supplierService);
                    break;
                case 4:
                    exit = true;
                    storeOwnerDashboard() ;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }

    private static void sendMessageToNormalUser(Scanner scanner, UserService userService) {
   
    }

    private static void sendMessageToSupplier(Scanner scanner, SupplierService supplierService) {
        System.out.print("Enter the ID of the Supplier: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        Supplier supplier = supplierService.getSupplierById(id);
        
        if (supplier != null) {
            System.out.print("Enter your message: ");
            String message = scanner.nextLine();
            supplier.getMessage().add(message);
            System.out.println("Message sent to Supplier ID " + id);
        } else {
            System.out.println("Supplier not found.");
        }
    }
    
    private static void sendMessageToOwner(Scanner scanner, StoreService supplierService) {
        System.out.print("Enter the ID of the Owner: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        Store supplie = StoreService.getStoreById(id);
 
        if (supplie != null) {
            System.out.print("Enter your message: ");
            String message = scanner.nextLine();
            supplie.getMessage().add(message);
            System.out.println("Message sent to Owner ID " + id);
        } else {
            System.out.println("Owner not found.");
        }
    }

    private static void viewReceivedMessages(Scanner scanner, UserService userService, SupplierService supplierService) {
        System.out.println("Choose the type of user to view messages:");
        System.out.println("1. NormalUser");
        System.out.println("2. Supplier");
        System.out.print("Enter your choice: ");
        
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        
        switch (choice) {
            case 1:
                System.out.print("Enter the username of the NormalUser: ");
                String username = scanner.nextLine();
                NormalUser user = userService.getNormalUser(username);
                
                if (user != null) {
                    System.out.println("Messages for " + username + ":");
                    for (String message : user.getMessage()) {
                        System.out.println("- " + message);
                    }
                } else {
                    System.out.println("NormalUser not found.");
                }
                break;
            case 2:
                System.out.print("Enter the ID of the Supplier: ");
                int id = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                Supplier supplier = supplierService.getSupplierById(id);
                
                if (supplier != null) {
                    System.out.println("Messages for Supplier ID " + id + ":");
                    for (String message : supplier.getMessage()) {
                        System.out.println("- " + message);
                    }
                } else {
                    System.out.println("Supplier not found.");
                }
                break;
            default:
                System.out.println("Invalid choice.");
                break;
        }
    }


    private static void viewReceivedMessagesSub(Scanner scanner, UserService userService, StoreService supplierService) {
        System.out.println("Choose the type of user to view messages:");
        System.out.println("1. NormalUser");
        System.out.println("2. Supplier");
        System.out.print("Enter your choice: ");
        
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        
        switch (choice) {
            case 1:
                System.out.print("Enter the username of the NormalUser: ");
                String username = scanner.nextLine();
                NormalUser user = userService.getNormalUser(username);
                
                if (user != null) {
                    System.out.println("Messages for " + username + ":");
                    for (String message : user.getMessage()) {
                        System.out.println("- " + message);
                    }
                } else {
                    System.out.println("NormalUser not found.");
                }
                break;
            case 2:
                System.out.print("Enter the ID of the Supplier: ");
                int id = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                Store supplier = supplierService.getStoreById(id);
                
                if (supplier != null) {
                    System.out.println("Messages for Supplier ID " + id + ":");
                    for (String message : supplier.getMessage()) {
                        System.out.println("- " + message);
                    }
                } else {
                    System.out.println("Supplier not found.");
                }
                break;
            default:
                System.out.println("Invalid choice.");
                break;
        }
    }


  

    public static void manageAccountInformation() {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("==============================");
            System.out.println("   Manage Account Information");
            System.out.println("==============================");
            System.out.println("Choose an option:");
            System.out.println("1. View Account Information");
            System.out.println("2. Update Account Information");
            System.out.println("3. Back to Previous Menu");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    viewAccountInformation();
                    break;

                case 2:
                    updateAccountInformation();
                    break;

                case 3:
                    running = false;
                    supplierDashboard();
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void viewAccountInformation() {
        // Assuming `user` is a class variable holding the logged-in user's information
        if (user != null) {
            System.out.println("Account Information:");
            System.out.println("Name: " + user.getName());
            System.out.println("Password: " + user.getPassword()); // You might want to mask this
            System.out.println("City: " + user.getCity());
            System.out.println("Phone Number: " + user.getPhone());
            System.out.println("Role: " + user.getRole());
        } else {
            System.out.println("No user is logged in.");
        }
    }

    private static void updateAccountInformation() {
        if (user != null) {
            Scanner scanner = new Scanner(System.in);

            System.out.println("Update Account Information:");

            System.out.print("Enter new name (leave empty to keep current): ");
            String newName = scanner.nextLine();
            if (!newName.isEmpty()) {
                user.setName(newName);
            }

            System.out.print("Enter new password (leave empty to keep current): ");
            String newPassword = scanner.nextLine();
            if (!newPassword.isEmpty()) {
                user.setPassword(newPassword);
            }

            System.out.print("Enter new city (leave empty to keep current): ");
            String newCity = scanner.nextLine();
            if (!newCity.isEmpty()) {
                user.setCity(newCity);
            }

            System.out.print("Enter new phone number (leave empty to keep current): ");
            String newPhone = scanner.nextLine();
            if (!newPhone.isEmpty()) {
                user.setPhone(newPhone);
            }

            System.out.println("Account information updated successfully.");
        } else {
            System.out.println("No user is logged in.");
        }
    }


    // Supplier specific functionalities
    public static void communicateWithUsersAndStoreOwners() {
    	   Scanner scanner = new Scanner(System.in);
           UserService userService = new UserService(); // Assuming you have an instance of UserService
           StoreService supplierService = new StoreService(); // Assuming you have an instance of SupplierService

           boolean exit = false;
           
           while (!exit) {
               System.out.println("==============================");
               System.out.println("   Communicate with Users and Suppliers");
               System.out.println("==============================");
               System.out.println("Choose an option:");
               System.out.println("1. Send a message to a User");
               System.out.println("2. Send a message to a OwnerStore");
               System.out.println("3. View received messages");
               System.out.println("4. Back to Supplier Dashboard");
               System.out.print("Enter your choice: ");
               
               int choice = scanner.nextInt();
               scanner.nextLine(); // Consume newline

               switch (choice) {
                   case 1:
                       sendMessageToNormalUser(scanner, userService);
                       break;
                   case 2:
                       sendMessageToOwner(scanner, supplierService);
                       break;
                   case 3:
                       viewReceivedMessagesSub(scanner, userService, supplierService);
                       break;
                   case 4:
                       exit = true;
                       supplierDashboard() ;
                       break;
                   default:
                       System.out.println("Invalid choice. Please try again.");
                       break;
               }
           } }

    // Normal User specific functionalities
    public static void manageAccount(NormalUser user) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("==============================");
            System.out.println("   Manage Account");
            System.out.println("==============================");
            System.out.println("Choose an option:");
            System.out.println("1. View Account Information");
            System.out.println("2. Update Account Information");
            System.out.println("3. Back to User Dashboard"); // Updated option
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    viewAccountInformation(user);
                    break;
                case 2:
                    updateAccountInformation(user);
                    break;
                case 3:
                    running = false; // Return to User Dashboard
                    normalUserDashboard(user);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }

    private static void viewAccountInformation(NormalUser user) {
        System.out.println("Account Information:");
        System.out.println("Name: " + user.getName());
        System.out.println("Password: " + user.getPassword()); // You might want to mask this
        System.out.println("City: " + user.getCity());
        System.out.println("Phone Number: " + user.getPhone());
        System.out.println("Role: " + user.getRole());
    }

    private static void updateAccountInformation(NormalUser user) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Update Account Information:");

        System.out.print("Enter new name (leave empty to keep current): ");
        String newName = scanner.nextLine();
        if (!newName.isEmpty()) {
            user.setName(newName);
        }

        System.out.print("Enter new password (leave empty to keep current): ");
        String newPassword = scanner.nextLine();
        if (!newPassword.isEmpty()) {
            user.setPassword(newPassword);
        }

        System.out.print("Enter new city (leave empty to keep current): ");
        String newCity = scanner.nextLine();
        if (!newCity.isEmpty()) {
            user.setCity(newCity);
        }

        System.out.print("Enter new phone number (leave empty to keep current): ");
        String newPhone = scanner.nextLine();
        if (!newPhone.isEmpty()) {
            user.setPhone(newPhone);
        }

        System.out.println("Account information updated successfully.");
    }
   public static void exploreAndBuySweets() {
    Scanner scanner = new Scanner(System.in);
    System.out.println("==============================");
    System.out.println("   Explore and Buy Sweets");
    System.out.println("==============================");

    boolean shopping = true;

    while (shopping) {
        System.out.println("Choose an option:");
        System.out.println("1. Browse all dessert recipes");
        System.out.println("2. Search for a dessert recipe");
        System.out.println("3. Filter recipes by dietary needs or allergies");
        System.out.println("4. Purchase desserts");
        System.out.println("5. Go back to the User Dashboard");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        switch (choice) {
            case 1:
                // Browse all dessert recipes
                productService.printAllProducts();
                break;

            case 2:
                // Search for a dessert recipe
                System.out.print("Enter the name of the dessert you want to search for: ");
                String searchQuery = scanner.nextLine();
                productService.searchProductsByName(searchQuery);
                break;

            case 3:
                // Filter recipes by dietary needs or allergies
                System.out.println("Enter the dietary need or allergy to filter by (e.g., gluten-free, nut-free): ");
                String filter = scanner.nextLine();
              productService.filterProductsByDietaryNeeds(filter);
                break;

            case 4:
                // Purchase desserts
                System.out.print("Enter the number of the dessert you want to purchase: ");
                int productId = scanner.nextInt();
                scanner.nextLine(); // Consume newline left-over
                fID = productId;
                Products product = productService.getProductById(productId);

                if (product != null) {
                    int price = product.getPrice();

                    // Apply discounts based on price
                    if (price > 100 && price < 149) {
                        price *= 0.90;
                        System.out.println("A 10% discount has been applied!");
                    } else if (price >= 150 && price <= 199) {
                        price *= 0.80;
                        System.out.println("A 20% discount has been applied!");
                    } else if (price >= 200) {
                        price *= 0.70;
                        System.out.println("A 30% discount has been applied!");
                    }

                    System.out.println("Purchased " + product.getName() + " for $" + price);

                    // Send email notification
                    String recipient = "amer23102002@gmail.com";
                    String subject = "Purchase Confirmation";
                    String content = "Thank you for your purchase!\n\nProduct: " + product.getName() +
                                     "\nPrice: $" + price + "\n\nBest regards,\nYour Store";
                    EmailService.sendEmail(recipient, subject, content);

                    System.out.print("Please provide feedback for " + product.getName() + ": ");
                    String feedbackInput = scanner.nextLine();
                    LinkedList<String> feedbackText = new LinkedList<>();
                    feedbackText.add(feedbackInput);

                    Feedback feedback = new Feedback(user.getName(), fID, feedbackText);
                    feedbackList.add(feedback);

                    System.out.println("Thank you for your feedback!");

                } else {
                    System.out.println("Product not found.");
                }
                break;



            case 5:
                shopping = false;
                break;

            default:
                System.out.println("Invalid choice. Please try again.");
                break;
        }
    }
}

 
   
   //////////////////
   
   
   
   public static void communicationCenter(NormalUser currentUser) {
       Scanner scanner = new Scanner(System.in);
       boolean exit = false;
       
       while (!exit) {
           System.out.println("==============================");
           System.out.println("   Communication Center");
           System.out.println("==============================");
           System.out.println("1. Send a message");
           System.out.println("2. View received messages");
           System.out.println("3. Back to Dashboard");
           System.out.print("Enter your choice: ");
           
           int choice = scanner.nextInt();
           scanner.nextLine(); // Consume newline

           switch (choice) {
               case 1:
                   sendMessage(scanner, currentUser);
                   break;
               case 2:
                   viewReceivedMessages(currentUser);
                   break;
               case 3:
                   exit = true;
                   break;
               default:
                   System.out.println("Invalid choice. Please try again.");
                   break;
           }
       }
   }
   
 private static void sendMessage(Scanner scanner, NormalUser sender) {
    System.out.println("Select recipient type:");
    System.out.println("1. User");
    System.out.println("2. Supplier");
    System.out.println("3. StoreOwner");
    System.out.print("Enter your choice: ");
    int recipientType = scanner.nextInt();
    scanner.nextLine(); // Consume newline

    System.out.println("Enter recipient name:");
    String recipientIdentifier = scanner.nextLine();

    NormalUser recipient = null;

    // Search for the recipient in the allUsers list by name
    for (NormalUser user : NormalUser.allUsers) {
        if (user.getName().equalsIgnoreCase(recipientIdentifier)) {
            recipient = user;
            break;
        }
    }

    if (recipient == null) {
        System.out.println("Recipient not found.");
        return;
    }

    System.out.print("Enter your message: ");
    String messageContent = scanner.nextLine();

    Message message = new Message(sender.getId(), sender.getName(), sender.getRole(), messageContent);

    if (!messageBox.containsKey(recipient.getId())) {
        messageBox.put(recipient.getId(), new ArrayList<>());
    }
    messageBox.get(recipient.getId()).add(message);

    System.out.println("Message sent successfully to " + recipient.getName() + ".");
}

   private static void viewReceivedMessages(NormalUser receiver) {
       List<Message> messages = messageBox.get(receiver.getId());
       if (messages == null || messages.isEmpty()) {
           System.out.println("You have no messages.");
       } else {
           System.out.println("Your messages:");
           for (Message message : messages) {
               System.out.println("From: " + message.getSenderName() + " (" + message.getSenderRole() + ")");
               System.out.println("Message: " + message.getContent());
               System.out.println("--------------------");
           }
       }
   }
   static class Message {
       private int senderId;
       private String senderName;
       private String senderRole;
       private String content;

       public Message(int senderId, String senderName, String senderRole, String content) {
           this.senderId = senderId;
           this.senderName = senderName;
           this.senderRole = senderRole;
           this.content = content;
       }

       
       public int getSenderId() { return senderId; }
       public String getSenderName() { return senderName; }
       public String getSenderRole() { return senderRole; }
       public String getContent() { return content; }
   }
   
 /////////////////////////////
   
    public static void viewProductFeedback() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the product number to view feedback: ");
        int productId = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        boolean feedbackFound = false;
        for (Feedback feedback : feedbackList) {
            if (feedback.getProductId() == productId) {
                System.out.println(feedback);
                feedbackFound = true;
            }
        }

        if (!feedbackFound) {
            System.out.println("No feedback found for this product.");
        }
    }
}
