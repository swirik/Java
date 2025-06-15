import java.util.Scanner;

public class Inventory {
    private Scanner scanner = new Scanner(System.in);

    public Inventory() {
        System.out.print("Hello!");
        Main.delay(100);
        System.out.print("Welcome to my Inventory Management System App!");
        Main.delay(100);
        System.out.print("Press Enter to continue");
        String prompt = scanner.nextLine();
    }

    public void Menu() {
        boolean running = true;
        while(running) {
            System.out.println("1. Add Product");
            System.out.println("2. Remove Product");
            System.out.println("3. Search Product");
            System.out.println("4. View Catalogue");
            System.out.println("5. Exit");
            String input = scanner.nextLine();
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
        boolean running = true;
        String input = scanner.nextLine().trim();
        while (running) {
            if(input.equalsIgnoreCase("Q")) {
                return;
            }
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

    };

    // Print Error Function
    public void printError(String message) {
        System.out.println("❌ " + message);
        Main.delay(500);
        Main.clear();
    }
}