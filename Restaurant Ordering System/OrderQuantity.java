package schoolProjectes;

public class OrderQuantity {
	 private MenuItem item;
	 private int quantity;
	   
	 public OrderQuantity (MenuItem item, int quantity) {
		 this.item = item;
		 this.quantity = quantity;
	    }
	    
	 public MenuItem getItem() {
	     return item;
	    }
	    
	 public int getQuantity() {
	     return quantity;
	    }
	    
	 public void incrementQuantity() {
	     this.quantity++;
	    }
	 
	 public void decreaseQuantity(int amount) {
		    if (amount > 0 && amount <= quantity) {
		        quantity -= amount;
		    }
		}
	    
	 public int getTotalPrice() {
	     return item.getPrice() * quantity;
	    }
	}
