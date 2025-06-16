import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.HashMap;

public class InventoryManager {
    private ObservableList<Product> products = FXCollections.observableArrayList();
    private HashMap<String, Product> productsByCode = new HashMap<>();
    private HashMap<String, ObservableList<Product>> productsByName = new HashMap<>();
    private int productCounter = 0;

    public void addProduct(String name, double price, int quantity, String category) {
        String code = getNextProductCode();
        Product product = new Product(code, name, quantity, price);
        product.setCategory(category);
        addProduct(product);
    }

    public void addProduct(Product product) {
        products.add(product);
        productsByCode.put(product.getCode(), product);

        String nameKey = product.getName().toLowerCase();
        productsByName.computeIfAbsent(nameKey, k -> FXCollections.observableArrayList()).add(product);
    }

    public String getNextProductCode() {
        productCounter++;
        return String.format("PRD%07d", productCounter);
    }

    // O(1) search by code
    public Product searchByCode(String code) {
        return productsByCode.get(code);
    }

    // O(1) search by name, returns ObservableList for GUI use
    public ObservableList<Product> searchByName(String name) {
        return productsByName.getOrDefault(name.toLowerCase(), FXCollections.observableArrayList());
    }

    // Sorting methods
    public void sortByName() {
        FXCollections.sort(products, (p1, p2) -> p1.getName().compareToIgnoreCase(p2.getName()));
    }

    public void sortByCode() {
        FXCollections.sort(products, (p1, p2) -> p1.getCode().compareTo(p2.getCode()));
    }

    public ObservableList<Product> getAllProducts() {
        return products;
    }

    public boolean removeProductByCode(String code) {
        Product product = productsByCode.remove(code);
        if (product == null) {
            return false;
        }

        // Remove from main list
        products.remove(product);

        // Remove from name map
        String nameKey = product.getName().toLowerCase();
        ObservableList<Product> list = productsByName.get(nameKey);
        if (list != null) {
            list.remove(product);
            if (list.isEmpty()) {
                productsByName.remove(nameKey);
            }
        }

        return true;
    }
}
