import java.util.ArrayList;
import java.util.Scanner;
import java.util.HashMap;

public class Inventory {
    private Scanner scanner = new Scanner(System.in);
    private InventoryManager inventorymanager = new InventoryManager();
    private ArrayList<Product> products = new ArrayList<>();
    private HashMap<String, Product> productsByCode = new HashMap<>();
    private HashMap<String, ArrayList<Product>> productsByName = new HashMap<>();
    private int productCounter;

    public Inventory() {
        System.out.println("Hello!");
        Main.delay(100);
        System.out.println("Welcome to my Inventory Management System App!");
        Main.delay(100);
        System.out.print("Press Enter to continue");
        String prompt = scanner.nextLine();
        Menu();
    }

    public void Menu() {
        while(true) {
            System.out.println("1. Add Product");
            System.out.println("2. Remove Product");
            System.out.println("3. Search Product");
            System.out.println("4. View Catalogue");
            System.out.println("5. Exit");
            String input = scanner.nextLine().trim();
            switch(input) {
                case "1":
                    addProduct();
                    break;
                case "2":
                    removeProduct();
                    break;
                case "3":
                    searchProducts();
                    break;
                case "4":
                    viewCatalogue();
                    break;
                case "5":
                    return;
                default:
                    printError("Incorrect! Please try again.");
            }
        }
    }

    // Add Product Method
    public void addProduct() {
        while (true) {
            System.out.println("Enter product details:" +
                               "\nName: ");
            String input = scanner.nextLine().trim();
            if(input.equalsIgnoreCase("Q"))
                return;
            String productName = input;

            System.out.println("\nPrice: ");
            double productPrice = scanner.nextDouble();
            scanner.nextLine();

            System.out.println("\nCategory: ");
            String productCategory = scanner.nextLine();

            inventorymanager.addProduct(productName, productPrice, productCategory);
            System.out.println("Product added successfully!");
        }
    };

    // Remove Product Method
    public void removeProduct() {

    };

    // Search Products Method
    public void searchProducts() {

    };

    // View Catalogue Method
    public void viewCatalogue() {
        while (true) {
            System.out.println("View Products Sorted By:");
            System.out.println("1. Name");
            System.out.println("2. Code");
            System.out.println("Q. Back");

            String input = scanner.nextLine().trim();
            if (input.equalsIgnoreCase("q")) return;

            switch (input) {
                case "1":
                    inventorymanager.sortByName();
                    break;
                case "2":
                    inventorymanager.sortByCode();
                    break;
                default:
                    printError("Invalid input.");
                    continue;
            }

            ArrayList<Product> allProducts = inventorymanager.getAllProducts();
            if (allProducts.isEmpty()) {
                System.out.println("No products available.");
            } else {
                for (Product p : allProducts) {
                    System.out.println(p.getDisplayInfo());
                }
            }

            System.out.println("\nPress Enter to return...");
            scanner.nextLine();
            return;
        }
    }



    public String generateCode() {
        productCounter++;
        return String.format("PRD%07d", productCounter);
    };
    // Print Error Function
    public void printError(String message) {
        System.out.println("❌ " + message);
        Main.delay(500);
        Main.clear();
    };

}