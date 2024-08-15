package SweetMang;

import models.*;
import services.*;

import java.util.*;
import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    private static final Logger LOGGER = Logger.getLogger(Main.class.getName());

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

        Logger rootLogger = Logger.getLogger("");
        Handler[] handlers = rootLogger.getHandlers();
        if (handlers[0] instanceof ConsoleHandler) {
            rootLogger.removeHandler(handlers[0]);
        }

        ConsoleHandler handler = new ConsoleHandler();
        handler.setFormatter(new SimpleFormatter());

        LOGGER.addHandler(handler);

        while (true) {
            // Step 1: Choose to log in or create an account
            LOGGER.info("Welcome to the Sweet Management System!");
            LOGGER.info("1. Log in");
            LOGGER.info("2. Create an account");
            LOGGER.info("3. Exit");
            LOGGER.info("Choose an option (1, 2, or 3): ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            if (choice == 3) {
                LOGGER.info("Thank you for using the Sweet Management System. Goodbye!");
                break;
            }

            if (choice == 2) {
                // Step 2: Account Creation
                LOGGER.info("Create an account:");

                LOGGER.info("Enter your name: ");
                String name = scanner.nextLine();

                LOGGER.info("Enter your password: ");
                String password = scanner.nextLine();

                LOGGER.info("Enter your city: ");
                String city = scanner.nextLine();

                LOGGER.info("Enter your phone number: ");
                String phone = scanner.nextLine();

                LOGGER.info("Select your role:");
                LOGGER.info("1. Admin");
                LOGGER.info("2. StoreOwner");
                LOGGER.info("3. Supplier");
                LOGGER.info("4. NormalUser");
                LOGGER.info("Enter the number corresponding to your role: ");
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
                        LOGGER.warning("Invalid role choice.");
                        continue;
                }

                NormalUser newUser = new NormalUser(userList.size(), name, password, city, phone, role);
                userList.add(newUser);
                LOGGER.info("Account created successfully!");
            }

            if (choice == 1 || choice == 2) {
                // Step 3: Login
                boolean loginSuccess = false;

                while (!loginSuccess) {
                    LOGGER.info("Please log in.");
                    LOGGER.info("Enter your name: ");
                    String loginName = scanner.nextLine();

                    LOGGER.info("Enter your password: ");
                    String loginPassword = scanner.nextLine();

                    LOGGER.info("Select your role:");
                    LOGGER.info("1. Admin");
                    LOGGER.info("2. StoreOwner");
                    LOGGER.info("3. Supplier");
                    LOGGER.info("4. NormalUser");
                    LOGGER.info("Enter the number corresponding to your role: ");
                    int loginRoleChoice = scanner.nextInt();
                    scanner.nextLine(); // Consume newline

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
                            LOGGER.warning("Invalid role choice.");
                            continue;
                    }

                    // Verify login details
                    for (NormalUser u : userList) {
                        if (loginName.equals(u.getName()) && loginPassword.equals(u.getPassword()) && loginRole.equals(u.getRole())) {
                            loginSuccess = true;
                            user = u; // Set the current user
                            LOGGER.info("Login successful!");

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
                        LOGGER.warning("Invalid login details. Please try again.");
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
            LOGGER.info("==============================");
            LOGGER.info("   Admin Dashboard");
            LOGGER.info("==============================");
            LOGGER.info("1. Manage Users");
            LOGGER.info("2. Generate Financial Reports");
            LOGGER.info("3. View Best-Selling Products");
            LOGGER.info("4. Sort Users by City");
            LOGGER.info("5. Manage Content(Posts) and Feedback");
            LOGGER.info("6. Log Out");
            LOGGER.info("Choose an option: ");

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
                    LOGGER.info("Logging out...");
                    break;
                default:
                    LOGGER.warning("Invalid choice. Please try again.");
            }
        }
    }

    private static void manageUsersadmin() {
        Scanner scanner = new Scanner(System.in);
        boolean back = false;

        while (!back) {
            LOGGER.info("User Management");
            LOGGER.info("1. View All Users");
            LOGGER.info("2. Add User");
            LOGGER.info("3. Update User");
            LOGGER.info("4. Delete User");
            LOGGER.info("5. Back to Admin Dashboard");
            LOGGER.info("Choose an option: ");

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
                    LOGGER.warning("Invalid choice.");
            }
        }
    }

    private static void viewAllUsers() {
        LOGGER.info("All Users:");
        for (NormalUser user : userList) {
            LOGGER.info(user.toString());
        }
    }

    private static void addUser() {
        Scanner scanner = new Scanner(System.in);
        LOGGER.info("Add New User");

        LOGGER.info("Enter name: ");
        String name = scanner.nextLine();

        LOGGER.info("Enter password: ");
        String password = scanner.nextLine();

        LOGGER.info("Enter city: ");
        String city = scanner.nextLine();

        LOGGER.info("Enter phone: ");
        String phone = scanner.nextLine();


        LOGGER.info("Select role (1. Admin, 2. StoreOwner, 3. Supplier, 4. NormalUser): ");
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
                LOGGER.warning("Invalid role. Setting as NormalUser.");
                role = "NormalUser";
        }

        NormalUser newUser = new NormalUser(userList.size(), name, password, city, phone, role);
        userList.add(newUser);
        LOGGER.info("User added successfully.");
    }

    private static void updateUser() {
        Scanner scanner = new Scanner(System.in);
        LOGGER.info("Enter the ID of the user to update: ");
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
            LOGGER.warning("User not found.");
            return;
        }

        LOGGER.info("Current user details: " + userToUpdate);
        LOGGER.info("Enter new details (press enter to keep current value):");

        LOGGER.info("New name: ");
        String name = scanner.nextLine();
        if (!name.isEmpty()) userToUpdate.setName(name);

        LOGGER.info("New password: ");
        String password = scanner.nextLine();
        if (!password.isEmpty()) userToUpdate.setPassword(password);

        LOGGER.info("New city: ");
        String city = scanner.nextLine();
        if (!city.isEmpty()) userToUpdate.setCity(city);

        LOGGER.info("New phone: ");
        String phone = scanner.nextLine();
        if (!phone.isEmpty()) userToUpdate.setPhone(phone);

        LOGGER.info("User updated successfully.");
    }

    private static void deleteUser() {
        Scanner scanner = new Scanner(System.in);
        LOGGER.info("Enter the ID of the user to delete: ");
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
            LOGGER.warning("User not found.");
            return;
        }

        userList.remove(userToDelete);
        LOGGER.info("User deleted successfully.");
    }

    private static void generateFinancialReports() {
        LOGGER.info("Financial Reports");

        // Report for Stores
        LOGGER.info("Store Reports:");
        for (Store store : storeList) {
            double totalSales = 0;
            double totalProfit = 0;
            for (Products product : productList) {
                if (product.getStoreId() == store.getId()) {
                    totalSales += product.getPrice() * product.getTotalSold();
                    totalProfit += (product.getPrice() - product.getPrice()) * product.getTotalSold();
                }
            }
            LOGGER.info("Store: " + store.getName());
            LOGGER.info("Total Sales: $" + totalSales);
            LOGGER.info("Total Profit: $" + totalProfit);
            LOGGER.info("-------------------------");
        }

        // Report for Suppliers
        LOGGER.info("Supplier Reports:");
        for (Supplier supplier : supplierList) {
            double totalSales = 0;
            for (Products product : productList) {
                if (product.getStoreOrSupplierId() == supplier.getId()) {
                    totalSales += product.getPrice() * product.getTotalSold();
                }
            }
            LOGGER.info("Supplier: " + supplier.getName());
            LOGGER.info("Total Sales: $" + totalSales);
            LOGGER.info("-------------------------");
        }
    }

    private static void viewBestSellingProducts() {
        LOGGER.info("Best-Selling Products");

        // Sort products by total sold
        Collections.sort(productList, (p1, p2) -> Integer.compare(p2.getTotalSold(), p1.getTotalSold()));

        // Display top 5 best-selling products
        LOGGER.info("Top 5 Best-Selling Products:");
        for (int i = 0; i < Math.min(5, productList.size()); i++) {
            Products product = productList.get(i);
            LOGGER.info((i + 1) + ". " + product.getName() + " - Total Sold: " + product.getTotalSold());
        }
    }

    private static void sortUsersByCity() {
        LOGGER.info("Users Sorted by City");
        Map<String, List<NormalUser>> usersByCity = new HashMap<>();

        for (NormalUser user : userList) {
            usersByCity.computeIfAbsent(user.getCity(), k -> new ArrayList<>()).add(user);
        }

        for (Map.Entry<String, List<NormalUser>> entry : usersByCity.entrySet()) {
            LOGGER.info("City: " + entry.getKey());
            for (NormalUser user : entry.getValue()) {
                LOGGER.info("  " + user.getName() + " - " + user.getRole());
            }
            LOGGER.info("-------------------------");
        }
    }

    private static void manageContentAndFeedback() {
        Scanner scanner = new Scanner(System.in);
        boolean back = false;

        while (!back) {
            LOGGER.info("Content and Feedback Management");
            LOGGER.info("1. Manage Store Posts");
            LOGGER.info("2. Manage User Feedback");
            LOGGER.info("3. Back to Admin Dashboard");
            LOGGER.info("Choose an option: ");

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
                    LOGGER.warning("Invalid choice.");
            }
        }
    }

    private static void manageStorePosts() {
        Scanner scanner = new Scanner(System.in);
        PostService postService = new PostService(); // Assuming you have a PostService instance

        boolean exit = false;
        while (!exit) {
            LOGGER.info("==============================");
            LOGGER.info("   Manage Content (Posts) and Feedback");
            LOGGER.info("==============================");
            LOGGER.info("1. View All Posts");
            LOGGER.info("2. Add a New Post");
            LOGGER.info("3. Delete a Post");
            LOGGER.info("4. Go Back");
            LOGGER.info("Choose an option: ");

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
                    LOGGER.warning("Invalid choice. Please try again.");
            }
        }
    }

    private static void addPost(Scanner scanner, PostService postService) {
        LOGGER.info("Enter post title: ");
        String title = scanner.nextLine();

        LOGGER.info("Enter post tag: ");
        String tag = scanner.nextLine();

        LOGGER.info("Enter post description: ");
        String description = scanner.nextLine();

        int id = postService.getPosts().size() + 1; // Generate a new ID based on the number of existing posts
        Posts newPost = new Posts(id, 0, title, tag, description); // Set normalUserId as 0 or adjust accordingly
        postService.addPost(newPost);

        LOGGER.info("Post added successfully.");
    }

    private static void deletePost(Scanner scanner, PostService postService) {
        LOGGER.info("Enter the ID of the post to delete: ");
        int postId = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        List<Posts> posts = postService.getPosts();
        boolean postFound = false;
        for (Posts post : posts) {
            if (post.getId() == postId) {
                posts.remove(post);
                LOGGER.info("Post deleted successfully.");
                postFound = true;
                break;
            }
        }

        if (!postFound) {
            LOGGER.warning("Post not found.");
        }
    }

    private static void manageUserFeedback() {
        Scanner scanner = new Scanner(System.in);
        LOGGER.info("User Feedback:");
        for (Feedback feedback : feedbackList) {
            LOGGER.info(feedback.toString());
            LOGGER.info("1. Keep  2. Remove");
            int action = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            if (action == 2) {
                feedbackList.remove(feedback);
                LOGGER.info("Feedback removed.");
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
            LOGGER.info("==============================");
            LOGGER.info("   Store Owner Dashboard");
            LOGGER.info("==============================");
            LOGGER.info("1. Manage Products");
            LOGGER.info("2. Monitor Sales and Profits");
            LOGGER.info("3. Manage Orders");
            LOGGER.info("4. Manage Account Information");
            LOGGER.info("5. View Product Feedback");
            LOGGER.info("6. Communication Center");
            LOGGER.info("7. Create a Post");
            LOGGER.info("8. Log Out");
            LOGGER.info("Enter your choice: ");
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
                    LOGGER.info("Logging out...");
                    logout = true;
                    main(null);
                    break;
                default:
                    LOGGER.warning("Invalid choice.");
                    break;
            }
        }
    }

    // Method to create a post
    private static void createPost(PostService postService, NormalUser user) {
        Scanner scanner = new Scanner(System.in);

        LOGGER.info("Enter post title: ");
        String title = scanner.nextLine();

        LOGGER.info("Enter post tag: ");
        String tag = scanner.nextLine();

        LOGGER.info("Enter post description: ");
        String description = scanner.nextLine();

        Posts post = new Posts(user.getId(), user.getId(), title, tag, description);
        postService.addPost(post);

        LOGGER.info("Post created successfully!");
    }

    /////////////////////////////////////

    //////// Suplier ///////////////
    public static void supplierDashboard() {
        Scanner scanner = new Scanner(System.in);
        boolean logout = false;

        while (!logout) {
            LOGGER.info("==============================");
            LOGGER.info("   Supplier Dashboard");
            LOGGER.info("==============================");
            LOGGER.info("1. Manage Products");
            LOGGER.info("2. Manage Orders");
            LOGGER.info("3. Manage Account Information");
            LOGGER.info("4. Communication Center");
            LOGGER.info("5. Log Out");
            LOGGER.info("Enter your choice: ");
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
                    LOGGER.info("Logging out...");
                    logout = true;
                    main(null);
                    break;
                default:
                    LOGGER.warning("Invalid choice.");
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
            LOGGER.info("==============================");
            LOGGER.info("   User Dashboard");
            LOGGER.info("==============================");
            LOGGER.info("1. Manage Account");
            LOGGER.info("2. Explore and Buy Sweets");
            LOGGER.info("3. Communication Center");
            LOGGER.info("4. View Posts");
            LOGGER.info("5. Log Out");
            LOGGER.info("Enter your choice: ");
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
                    LOGGER.info("Logging out...");
                    logout = true;
                    main(null);
                    break;
                default:
                    LOGGER.warning("Invalid choice.");
                    break;
            }
        }
    }

    private static void viewPosts(PostService postService) {
        List<Posts> posts = postService.getPosts();

        if (posts.isEmpty()) {
            LOGGER.info("No posts available.");
        } else {
            LOGGER.info("Available Posts:");
            int index = 1;
            for (Posts post : posts) {
                LOGGER.info(index + "-");
                LOGGER.info(" Title: " + post.getTitle());
                LOGGER.info(" Tag: " + post.getTag());
                LOGGER.info(" Description: " + post.getDescription());
                LOGGER.info("");
                index++;
            }
        }
    }

    public static void manageProducts() {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            LOGGER.info("==============================");
            LOGGER.info("   Manage Products");
            LOGGER.info("==============================");
            LOGGER.info("Choose an option:");
            LOGGER.info("1. Add a new product");
            LOGGER.info("2. Update an existing product");
            LOGGER.info("3. Remove a product");
            LOGGER.info("4. View all products");
            LOGGER.info("5. Back to Store Owner Dashboard");
            LOGGER.info("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    LOGGER.info("Enter product name: ");
                    String name = scanner.nextLine();
                    LOGGER.info("Enter product price: ");
                    int price = scanner.nextInt();
                    LOGGER.info("Enter total sold: ");
                    int totalSold = scanner.nextInt();
                    LOGGER.info("Enter the dietary Info : ");
                    String dietaryInfo = scanner.nextLine();
                    scanner.nextLine();// Consume newline

                    // Assume storeId is 1 for this example
                    int storeId = 1;
                    Products product = new Products(0, storeId, name, price, totalSold, dietaryInfo);
                    productService.addProduct(product);

                    LOGGER.info("Product added ");
                    break;

                case 2:
                    LOGGER.info("Enter product ID to update: ");
                    int id = scanner.nextInt();
                    scanner.nextLine(); // Consume newline

                    Products existingProduct = productService.getProductById(id);
                    if (existingProduct == null) {
                        LOGGER.warning("Product not found.");
                        break;
                    }

                    LOGGER.info("Enter new name (leave empty to keep current): ");
                    String newName = scanner.nextLine();
                    if (!newName.isEmpty()) existingProduct.setName(newName);

                    LOGGER.info("Enter new price (leave empty to keep current): ");
                    String priceInput = scanner.nextLine();
                    if (!priceInput.isEmpty()) existingProduct.setPrice(Integer.parseInt(priceInput));

                    LOGGER.info("Enter new total sold (leave empty to keep current): ");
                    String totalSoldInput = scanner.nextLine();
                    if (!totalSoldInput.isEmpty()) existingProduct.setTotalSold(Integer.parseInt(totalSoldInput));

                    LOGGER.info("Enter dietary Info (leave empty to keep current): ");
                    String dietary_Info = scanner.nextLine();
                    if (!dietary_Info.isEmpty()) existingProduct.setDietaryInfo(dietary_Info);

                    productService.updateProduct(id, existingProduct);
                    LOGGER.info("Product updated.");
                    break;

                case 3:
                    LOGGER.info("Enter product ID to remove: ");
                    int removeId = scanner.nextInt();
                    scanner.nextLine(); // Consume newline

                    productService.removeProduct(removeId);
                    LOGGER.info("Product removed.");
                    break;

                case 4:
                    LOGGER.info("All Products:");
                    productService.printAllProducts();
                    break;

                case 5:
                    running = false;
                    storeOwnerDashboard();
                    break;

                default:
                    LOGGER.warning("Invalid choice. Please try again.");
            }
        }
        scanner.close();
    }

    public static void manageSupProducts() {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            LOGGER.info("==============================");
            LOGGER.info("   Manage Products");
            LOGGER.info("==============================");
            LOGGER.info("Choose an option:");
            LOGGER.info("1. Add a new product");
            LOGGER.info("2. Update an existing product");
            LOGGER.info("3. Remove a product");
            LOGGER.info("4. View all products");
            LOGGER.info("5. Back to Store Owner Dashboard");
            LOGGER.info("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    LOGGER.info("Enter product name: ");
                    String name = scanner.nextLine();
                    LOGGER.info("Enter product price: ");
                    int price = scanner.nextInt();
                    LOGGER.info("Enter total sold: ");
                    int totalSold = scanner.nextInt();
                    scanner.nextLine();

                    // Assume storeId is 1 for this example

                    Products_Sup product = new Products_Sup(0, name, price, totalSold);
                    ProductService_Sup.addProduct(product);

                    LOGGER.info("Product added ");
                    break;

                case 2:
                    LOGGER.info("Enter product number to update: ");
                    int id = scanner.nextInt();
                    scanner.nextLine(); // Consume newline

                    Products_Sup existingProduct = ProductService_Sup.getProductById(id);
                    if (existingProduct == null) {
                        LOGGER.warning("Product not found.");
                        break;
                    }

                    LOGGER.info("Enter new name (leave empty to keep current): ");
                    String newName = scanner.nextLine();
                    if (!newName.isEmpty()) existingProduct.setName(newName);

                    LOGGER.info("Enter new price (leave empty to keep current): ");
                    String priceInput = scanner.nextLine();
                    if (!priceInput.isEmpty()) existingProduct.setPrice(Integer.parseInt(priceInput));

                    LOGGER.info("Enter new total sold (leave empty to keep current): ");
                    String totalSoldInput = scanner.nextLine();
                    if (!totalSoldInput.isEmpty()) existingProduct.setTotalSold(Integer.parseInt(totalSoldInput));

                    ProductService_Sup.updateProduct(id, existingProduct);
                    LOGGER.info("Product updated.");
                    break;

                case 3:
                    LOGGER.info("Enter product ID to remove: ");
                    int removeId = scanner.nextInt();
                    scanner.nextLine(); // Consume newline

                    ProductService_Sup.removeProduct(removeId);
                    LOGGER.info("Product removed.");
                    break;

                case 4:
                    LOGGER.info("All Products:");
                    ProductService_Sup.printAllProducts_Sup();
                    break;

                case 5:
                    running = false;
                    supplierDashboard();
                    break;

                default:
                    LOGGER.warning("Invalid choice. Please try again.");
            }
        }
        scanner.close();
    }

    // Admin specific functionalities
    public static void manageUsers() {
        LOGGER.info("Implementing manageUsers() functionality...");
    }

    public static void monitorProfitsAndReports() {
        LOGGER.info("Implementing monitorProfitsAndReports() functionality...");
    }

    public static void manageContent() {
        LOGGER.info("Implementing manageContent() functionality...");
    }

    // Store Owner specific functionalities
    public static void monitorSalesAndProfits() {
        LOGGER.info("==============================");
        LOGGER.info("   Monitor Sales and Profits");
        LOGGER.info("==============================");

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

        LOGGER.info("Total Sales: $" + totalSales);
        LOGGER.info("Total Profit: $" + totalProfit);

        LOGGER.info("\nProduct Details:");
        for (Products product : allProducts) {
            LOGGER.info(product.toString());
        }
    }

    public static void manageOrders() {
        Scanner scanner = new Scanner(System.in);
        OrderService orderService = new OrderService(); // Assume this is initialized and used properly

        boolean running = true;

        while (running) {
            LOGGER.info("==============================");
            LOGGER.info("   Manage Orders");
            LOGGER.info("==============================");
            LOGGER.info("Choose an option:");
            LOGGER.info("1. View all orders");
            LOGGER.info("2. Update order status");
            LOGGER.info("3. Back to Previous Menu");
            LOGGER.info("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    List<Order> orders = orderService.getAllOrders();
                    LOGGER.info("All Orders:");
                    for (Order order : orders) {
                        LOGGER.info(order.toString());
                    }
                    if (orders == null) {
                        LOGGER.warning("The Orders is Empity... ");
                    }
                    break;

                case 2:
                    LOGGER.info("Enter the order ID to update: ");
                    int orderId = scanner.nextInt();
                    scanner.nextLine(); // Consume newline

                    LOGGER.info("Enter the new status (e.g., 'Pending', 'Shipped', 'Delivered'): ");
                    String newStatus = scanner.nextLine();

                    orderService.updateOrderStatus(orderId, newStatus);
                    LOGGER.info("Order status updated.");
                    break;

                case 3:
                    running = false;
                    supplierDashboard();
                    break;

                default:
                    LOGGER.warning("Invalid choice. Please try again.");
            }
        }
    }

    public static void communicateWithUsersAndSuppliers() {
        Scanner scanner = new Scanner(System.in);
        UserService userService = new UserService(); // Assuming you have an instance of UserService
        SupplierService supplierService = new SupplierService(); // Assuming you have an instance of SupplierService

        boolean exit = false;

        while (!exit) {
            LOGGER.info("==============================");
            LOGGER.info("   Communicate with Users and Suppliers");
            LOGGER.info("==============================");
            LOGGER.info("Choose an option:");
            LOGGER.info("1. Send a message to a User");
            LOGGER.info("2. Send a message to a Supplier");
            LOGGER.info("3. View received messages");
            LOGGER.info("4. Back to Store Owner Dashboard");
            LOGGER.info("Enter your choice: ");

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
                    storeOwnerDashboard();
                    break;
                default:
                    LOGGER.warning("Invalid choice. Please try again.");
                    break;
            }
        }
    }

    private static void sendMessageToNormalUser(Scanner scanner, UserService userService) {
        // Implementation here...
    }

    private static void sendMessageToSupplier(Scanner scanner, SupplierService supplierService) {
        LOGGER.info("Enter the ID of the Supplier: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        Supplier supplier = supplierService.getSupplierById(id);

        if (supplier != null) {
            LOGGER.info("Enter your message: ");
            String message = scanner.nextLine();
            supplier.getMessage().add(message);
            LOGGER.info("Message sent to Supplier ID " + id);
        } else {
            LOGGER.warning("Supplier not found.");
        }
    }

    private static void sendMessageToOwner(Scanner scanner, StoreService supplierService) {
        LOGGER.info("Enter the ID of the Owner: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        Store supplie = StoreService.getStoreById(id);

        if (supplie != null) {
            LOGGER.info("Enter your message: ");
            String message = scanner.nextLine();
            supplie.getMessage().add(message);
            LOGGER.info("Message sent to Owner ID " + id);
        } else {
            LOGGER.warning("Owner not found.");
        }
    }

    private static void viewReceivedMessages(Scanner scanner, UserService userService, SupplierService supplierService) {
        LOGGER.info("Choose the type of user to view messages:");
        LOGGER.info("1. NormalUser");
        LOGGER.info("2. Supplier");
        LOGGER.info("Enter your choice: ");

        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        switch (choice) {
            case 1:
                LOGGER.info("Enter the username of the NormalUser: ");
                String username = scanner.nextLine();
                NormalUser user = userService.getNormalUser(username);

                if (user != null) {
                    LOGGER.info("Messages for " + username + ":");
                    for (String message : user.getMessage()) {
                        LOGGER.info("- " + message);
                    }
                } else {
                    LOGGER.warning("NormalUser not found.");
                }
                break;
            case 2:
                LOGGER.info("Enter the ID of the Supplier: ");
                int id = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                Supplier supplier = supplierService.getSupplierById(id);

                if (supplier != null) {
                    LOGGER.info("Messages for Supplier ID " + id + ":");
                    for (String message : supplier.getMessage()) {
                        LOGGER.info("- " + message);
                    }
                } else {
                    LOGGER.warning("Supplier not found.");
                }
                break;
            default:
                LOGGER.warning("Invalid choice.");
                break;
        }
    }

    private static void viewReceivedMessagesSub(Scanner scanner, UserService userService, StoreService supplierService) {
        LOGGER.info("Choose the type of user to view messages:");
        LOGGER.info("1. NormalUser");
        LOGGER.info("2. Supplier");
        LOGGER.info("Enter your choice: ");

        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        switch (choice) {
            case 1:
                LOGGER.info("Enter the username of the NormalUser: ");
                String username = scanner.nextLine();
                NormalUser user = userService.getNormalUser(username);

                if (user != null) {
                    LOGGER.info("Messages for " + username + ":");
                    for (String message : user.getMessage()) {
                        LOGGER.info("- " + message);
                    }
                } else {
                    LOGGER.warning("NormalUser not found.");
                }
                break;
            case 2:
                LOGGER.info("Enter the ID of the Supplier: ");
                int id = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                Store supplier = supplierService.getStoreById(id);

                if (supplier != null) {
                    LOGGER.info("Messages for Supplier ID " + id + ":");
                    for (String message : supplier.getMessage()) {
                        LOGGER.info("- " + message);
                    }
                } else {
                    LOGGER.warning("Supplier not found.");
                }
                break;
            default:
                LOGGER.warning("Invalid choice.");
                break;
        }
    }

    public static void manageAccountInformation() {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            LOGGER.info("==============================");
            LOGGER.info("   Manage Account Information");
            LOGGER.info("==============================");
            LOGGER.info("Choose an option:");
            LOGGER.info("1. View Account Information");
            LOGGER.info("2. Update Account Information");
            LOGGER.info("3. Back to Previous Menu");
            LOGGER.info("Enter your choice: ");

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
                    LOGGER.warning("Invalid choice. Please try again.");
            }
        }
    }

    private static void viewAccountInformation() {
        // Assuming `user` is a class variable holding the logged-in user's information
        if (user != null) {
            LOGGER.info("Account Information:");
            LOGGER.info("Name: " + user.getName());
            LOGGER.info("Password: " + user.getPassword()); // You might want to mask this
            LOGGER.info("City: " + user.getCity());
            LOGGER.info("Phone Number: " + user.getPhone());
            LOGGER.info("Role: " + user.getRole());
        } else {
            LOGGER.warning("No user is logged in.");
        }
    }

    private static void updateAccountInformation() {
        if (user != null) {
            Scanner scanner = new Scanner(System.in);

            LOGGER.info("Update Account Information:");

            LOGGER.info("Enter new name (leave empty to keep current): ");
            String newName = scanner.nextLine();
            if (!newName.isEmpty()) {
                user.setName(newName);
            }

            LOGGER.info("Enter new password (leave empty to keep current): ");
            String newPassword = scanner.nextLine();
            if (!newPassword.isEmpty()) {
                user.setPassword(newPassword);
            }

            LOGGER.info("Enter new city (leave empty to keep current): ");
            String newCity = scanner.nextLine();
            if (!newCity.isEmpty()) {
                user.setCity(newCity);
            }

            LOGGER.info("Enter new phone number (leave empty to keep current): ");
            String newPhone = scanner.nextLine();
            if (!newPhone.isEmpty()) {
                user.setPhone(newPhone);
            }

            LOGGER.info("Account information updated successfully.");
        } else {
            LOGGER.warning("No user is logged in.");
        }
    }

    // Supplier specific functionalities
    public static void communicateWithUsersAndStoreOwners() {
        Scanner scanner = new Scanner(System.in);
        UserService userService = new UserService(); // Assuming you have an instance of UserService
        StoreService supplierService = new StoreService(); // Assuming you have an instance of SupplierService

        boolean exit = false;

        while (!exit) {
            LOGGER.info("==============================");
            LOGGER.info("   Communicate with Users and Suppliers");
            LOGGER.info("==============================");
            LOGGER.info("Choose an option:");
            LOGGER.info("1. Send a message to a User");
            LOGGER.info("2. Send a message to a OwnerStore");
            LOGGER.info("3. View received messages");
            LOGGER.info("4. Back to Supplier Dashboard");
            LOGGER.info("Enter your choice: ");

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
                    supplierDashboard();
                    break;
                default:
                    LOGGER.warning("Invalid choice. Please try again.");
                    break;
            }
        }
    }

    // Normal User specific functionalities
    public static void manageAccount(NormalUser user) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            LOGGER.info("==============================");
            LOGGER.info("   Manage Account");
            LOGGER.info("==============================");
            LOGGER.info("Choose an option:");
            LOGGER.info("1. View Account Information");
            LOGGER.info("2. Update Account Information");
            LOGGER.info("3. Back to User Dashboard"); // Updated option
            LOGGER.info("Enter your choice: ");

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
                    LOGGER.warning("Invalid choice. Please try again.");
                    break;
            }
        }
    }

    private static void viewAccountInformation(NormalUser user) {
        LOGGER.info("Account Information:");
        LOGGER.info("Name: " + user.getName());
        LOGGER.info("Password: " + user.getPassword()); // You might want to mask this
        LOGGER.info("City: " + user.getCity());
        LOGGER.info("Phone Number: " + user.getPhone());
        LOGGER.info("Role: " + user.getRole());
    }

    private static void updateAccountInformation(NormalUser user) {
        Scanner scanner = new Scanner(System.in);

        LOGGER.info("Update Account Information:");

        LOGGER.info("Enter new name (leave empty to keep current): ");
        String newName = scanner.nextLine();
        if (!newName.isEmpty()) {
            user.setName(newName);
        }

        System.out.print("Enter new password (leave empty to keep current): ");
        String newPassword = scanner.nextLine();
        if (!newPassword.isEmpty()) {
            user.setPassword(newPassword);
        }

        LOGGER.info("Enter new city (leave empty to keep current): ");
        String newCity = scanner.nextLine();
        if (!newCity.isEmpty()) {
            user.setCity(newCity);
        }

        LOGGER.info("Enter new phone number (leave empty to keep current): ");
        String newPhone = scanner.nextLine();
        if (!newPhone.isEmpty()) {
            user.setPhone(newPhone);
        }

        LOGGER.info("Account information updated successfully.");
    }

    public static void exploreAndBuySweets() {
        Scanner scanner = new Scanner(System.in);
        LOGGER.info("==============================");
        LOGGER.info("   Explore and Buy Sweets");
        LOGGER.info("==============================");

        boolean shopping = true;

        while (shopping) {
            LOGGER.info("Choose an option:");
            LOGGER.info("1. Browse all dessert recipes");
            LOGGER.info("2. Search for a dessert recipe");
            LOGGER.info("3. Filter recipes by dietary needs or allergies");
            LOGGER.info("4. Purchase desserts");
            LOGGER.info("5. Go back to the User Dashboard");
            LOGGER.info("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    // Browse all dessert recipes
                    productService.printAllProducts();
                    break;

                case 2:
                    // Search for a dessert recipe
                    LOGGER.info("Enter the name of the dessert you want to search for: ");
                    String searchQuery = scanner.nextLine();
                    productService.searchProductsByName(searchQuery);
                    break;

                case 3:
                    // Filter recipes by dietary needs or allergies
                    LOGGER.info("Enter the dietary need or allergy to filter by (e.g., gluten-free, nut-free): ");
                    String filter = scanner.nextLine();
                    productService.filterProductsByDietaryNeeds(filter);
                    break;

                case 4:
                    // Purchase desserts
                    LOGGER.info("Enter the number of the dessert you want to purchase: ");
                    int productId = scanner.nextInt();
                    scanner.nextLine(); // Consume newline left-over
                    fID = productId;
                    Products product = productService.getProductById(productId);

                    if (product != null) {
                        int price = product.getPrice();

                        // Apply discounts based on price
                        if (price > 100 && price < 149) {
                            price *= 0.90;
                            LOGGER.info("A 10% discount has been applied!");
                        } else if (price >= 150 && price <= 199) {
                            price *= 0.80;
                            LOGGER.info("A 20% discount has been applied!");
                        } else if (price >= 200) {
                            price *= 0.70;
                            LOGGER.info("A 30% discount has been applied!");
                        }

                        LOGGER.info("Purchased " + product.getName() + " for $" + price);

                        // Send email notification
                        String recipient = "amer23102002@gmail.com";
                        String subject = "Purchase Confirmation";
                        String content = "Thank you for your purchase!\n\nProduct: " + product.getName() +
                                "\nPrice: $" + price + "\n\nBest regards,\nYour Store";
                        EmailService.sendEmail(recipient, subject, content);

                        LOGGER.info("Please provide feedback for " + product.getName() + ": ");
                        String feedbackInput = scanner.nextLine();
                        LinkedList<String> feedbackText = new LinkedList<>();
                        feedbackText.add(feedbackInput);

                        Feedback feedback = new Feedback(user.getName(), fID, feedbackText);
                        feedbackList.add(feedback);

                        LOGGER.info("Thank you for your feedback!");

                    } else {
                        LOGGER.warning("Product not found.");
                    }
                    break;

                case 5:
                    shopping = false;
                    break;

                default:
                    LOGGER.warning("Invalid choice. Please try again.");
                    break;
            }
        }
    }


    //////////////////


    public static void communicationCenter(NormalUser currentUser) {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            LOGGER.info("==============================");
            LOGGER.info("   Communication Center");
            LOGGER.info("==============================");
            LOGGER.info("1. Send a message");
            LOGGER.info("2. View received messages");
            LOGGER.info("3. Back to Dashboard");
            LOGGER.info("Enter your choice: ");

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
                    LOGGER.warning("Invalid choice. Please try again.");
                    break;
            }
        }
    }

    private static void sendMessage(Scanner scanner, NormalUser sender) {
        LOGGER.info("Select recipient type:");
        LOGGER.info("1. User");
        LOGGER.info("2. Supplier");
        LOGGER.info("3. StoreOwner");
        LOGGER.info("Enter your choice: ");
        int recipientType = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        LOGGER.info("Enter recipient name:");
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
            LOGGER.warning("Recipient not found.");
            return;
        }

        LOGGER.info("Enter your message: ");
        String messageContent = scanner.nextLine();

        Message message = new Message(sender.getId(), sender.getName(), sender.getRole(), messageContent);

        if (!messageBox.containsKey(recipient.getId())) {
            messageBox.put(recipient.getId(), new ArrayList<>());
        }
        messageBox.get(recipient.getId()).add(message);

        LOGGER.info("Message sent successfully to " + recipient.getName() + ".");
    }

    private static void viewReceivedMessages(NormalUser receiver) {
        List<Message> messages = messageBox.get(receiver.getId());
        if (messages == null || messages.isEmpty()) {
            LOGGER.info("You have no messages.");
        } else {
            LOGGER.info("Your messages:");
            for (Message message : messages) {
                LOGGER.info("From: " + message.getSenderName() + " (" + message.getSenderRole() + ")");
                LOGGER.info("Message: " + message.getContent());
                LOGGER.info("--------------------");
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

        public int getSenderId() {
            return senderId;
        }

        public String getSenderName() {
            return senderName;
        }

        public String getSenderRole() {
            return senderRole;
        }

        public String getContent() {
            return content;
        }
    }

    /////////////////////////////

    public static void viewProductFeedback() {
        Scanner scanner = new Scanner(System.in);
        LOGGER.info("Enter the product number to view feedback: ");
        int productId = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        boolean feedbackFound = false;
        for (Feedback feedback : feedbackList) {
            if (feedback.getProductId() == productId) {
                LOGGER.info(feedback.toString());
                feedbackFound = true;
            }
        }

        if (!feedbackFound) {
            LOGGER.warning("No feedback found for this product.");
        }
    }
}
