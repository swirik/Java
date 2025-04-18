package schoolProjectes;

public class MenuItem {
	private String product;
	private int price;

	public MenuItem(String a, int b) {
		this.product = a;
		this.price = b;
	}
	// Name Getter
	public String getName() {
		return product; 
	}
	// Price Getter
    public int getPrice() {
    	return price; 
    }
    //toString Method
    public String toString() {
        return product;
    }
}

