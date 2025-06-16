import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.util.Scanner;
import java.util.HashMap;

public class Inventory {
    private Scanner scanner = new Scanner(System.in);
    private InventoryManager inventorymanager = new InventoryManager();
    private ObservableList<Product> products = FXCollections.observableArrayList();
    private HashMap<String, Product> productsByCode = new HashMap<>();
    private HashMap<String, ObservableList<Product>> productsByName = new HashMap<>();
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
            double productPrice;
            try {
                productPrice = Double.parseDouble(scanner.nextLine());
                if (productPrice < 0) throw new NumberFormatException();
            } catch (NumberFormatException e) {
                printError("Invalid price.");
                continue;
            }

            System.out.println("\nQuantity: ");
            int quantity = Integer.parseInt(scanner.nextLine());

            System.out.println("\nCategory: ");
            String productCategory = scanner.nextLine();

            inventorymanager.addProduct(productName, productPrice, quantity, productCategory);

        }
    };

    // Remove Product Method
    public void removeProduct() {
        System.out.print("Enter product code to remove (or Q to cancel): ");
        String code = scanner.nextLine().trim();

        if (code.equalsIgnoreCase("q")) return;

        Product product = inventorymanager.searchByCode(code);
        if (product == null) {
            System.out.println("❌ Product not found.");
            return;
        }

        System.out.println("⚠️ You are about to remove:");
        System.out.println(product.getDisplayInfo());
        System.out.print("Confirm removal? (Y/N): ");
        String confirm = scanner.nextLine().trim();

        if (confirm.equalsIgnoreCase("y")) {
            boolean success = inventorymanager.removeProductByCode(code);
            if (success) {
                System.out.println("✅ Product removed successfully.");
            } else {
                System.out.println("❌ Failed to remove product.");
            }
        } else {
            System.out.println("❎ Removal cancelled.");
        }
    }


    // Search Products Method
    public void searchProducts() {
        System.out.println("Search by:");
        System.out.println("1. Code:");
        System.out.println("2. Name:");
        System.out.println("Q. back");
        String input = scanner.nextLine();
        if(input.equalsIgnoreCase("q")) {
            return;
        }
        switch(input) {
            case "1":
                System.out.println("Enter product code: ");
                String code = scanner.nextLine();
                Product product = inventorymanager.searchByCode(code);
                if(product != null) {
                    System.out.println(product.getDisplayInfo());
                }
                else {
                    System.out.println("❌ No product found with that code.");
                }
                break;
            case "2":
                System.out.println("Enter product name: ");
                String name = scanner.nextLine();
                ObservableList<Product> matches = inventorymanager.searchByName(name);
                if(!matches.isEmpty()) {
                    for (Product p : matches) {
                        System.out.println(p.getDisplayInfo());
                    }
                }
                else {
                    System.out.println("❌ No product found with that code.");
                }
                break;
            default: printError("Invalid input.");
        }
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

            ObservableList<Product> allProducts = inventorymanager.getAllProducts();
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