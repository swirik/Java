public class Product {
    // Core attributes
    private String code;
    private String name;
    private double price;
    private int quantity;
    private String category;
    private String description;



    // Constructors
    public Product(String code, String name, double price) {
        this.code = code;
        this.name = name;
        this.price = price;
        this.quantity = 0;
        this.category = "General";
        this.description = "";
    }

    public Product(String code, String name, double price, String category) {
        this(code, name, price);
        this.quantity = quantity;
        this.category = category;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void addStock(int amount) {
        if (amount > 0) {
            this.quantity += amount;
        }
    }

    public boolean removeStock(int amount) {
        if (amount > 0 && this.quantity >= amount) {
            this.quantity -= amount;
            return true;
        }
        return false;
    }

    public boolean isInStock() {
        return quantity > 0;
    }

    public double getTotalValue() {
        return price * quantity;
    }

    // Utility methods
    @Override
    public String toString() {
        return String.format("code='%s', name='%s', price=%.2f, quantity=%d, category='%s'",
                code, name, price, quantity, category);
    }

    public String getDisplayInfo() {
        return String.format("Code: %s | Name: %s | Price: ₱%.2f | Stock: %d | Category: %s",
                code, name, price, quantity, category);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Product product = (Product) obj;
        return code.equals(product.code);
    }

    @Override
    public int hashCode() {
        return code.hashCode();
    }
}