
import javafx.beans.property.*;

public class Product {
    private final StringProperty code = new SimpleStringProperty();
    private final StringProperty name = new SimpleStringProperty();
    private final IntegerProperty quantity = new SimpleIntegerProperty();
    private final DoubleProperty price = new SimpleDoubleProperty();
    private final StringProperty category = new SimpleStringProperty();

    public Product(String code, String name, int quantity, double price) {
        this.code.set(code);
        this.name.set(name);
        this.quantity.set(quantity);
        this.price.set(price);
    }

    // JavaFX Property methods
    public StringProperty codeProperty() { return code; }
    public StringProperty nameProperty() { return name; }
    public IntegerProperty quantityProperty() { return quantity; }
    public DoubleProperty priceProperty() { return price; }
    public StringProperty categoryProperty() { return category; }

    // Getters and setters
    public String getCode() { return code.get(); }
    public void setCode(String code) { this.code.set(code); }

    public String getName() { return name.get(); }
    public void setName(String name) { this.name.set(name); }

    public int getQuantity() { return quantity.get(); }
    public void setQuantity(int quantity) { this.quantity.set(quantity); }

    public double getPrice() { return price.get(); }
    public void setPrice(double price) { this.price.set(price); }

    public String getCategory() { return category.get(); }
    public void setCategory(String category) { this.category.set(category); }

    public String getDisplayInfo() {
        return String.format("Code: %s | Name: %s | Qty: %d | Price: %.2f | Category: %s",
                getCode(), getName(), getQuantity(), getPrice(), getCategory());
    }
}
