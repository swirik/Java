import java.util.ArrayList;
import java.util.HashMap;


class InventoryManager {
    private ArrayList<Product> products = new ArrayList<>();
    private HashMap<String, Product> productsByCode = new HashMap<>();
    private HashMap<String, ArrayList<Product>> productsByName = new HashMap<>();
    private int productCounter = 0;

    public void addProduct(String name, double price, String category) {
        String code = getNextProductCode();
        Product product = new Product(code, name, price, category);

        // Add to all data structures
        products.add(product);
        productsByCode.put(code, product);

        // Handle multiple products with same name
        productsByName.computeIfAbsent(name.toLowerCase(), k -> new ArrayList<>()).add(product);
    }

    public String getNextProductCode() {
        productCounter++;
        return String.format("PRD%07d", productCounter);
    }


    // O(1) search by code
    public Product searchByCode(String code) {
        return productsByCode.get(code);
    }

    // O(1) search by name
    public ArrayList<Product> searchByName(String name) {
        return productsByName.getOrDefault(name.toLowerCase(), new ArrayList<>());
    }

    // Easy sorting
    public void sortByName() {
        products.sort((p1, p2) -> p1.getName().compareToIgnoreCase(p2.getName()));
    }

    public void sortByCode() {
        products.sort((p1, p2) -> p1.getCode().compareTo(p2.getCode()));
    }

    public ArrayList<Product> getAllProducts() {
        return new ArrayList<>(products); // Defensive copy
    }

}