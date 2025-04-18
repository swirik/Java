package schoolProjectes;


public class Main {
	
	public static void delay(int milliseconds) {
	    try {
	        Thread.sleep(milliseconds);
	    } catch (InterruptedException e) {
	        System.out.println("Delay interrupted");
	    }
	}
	 public static void clear() { 
	     try { 
	          if (System.getProperty("os.name").contains("Windows")) { 
	              new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor(); 
	          } else { 
	              new ProcessBuilder("clear").inheritIO().start().waitFor(); 
	          } 
	      } catch (Exception e) { 
	          e.printStackTrace(); 
	      } 
	  } 
	 	 
	public static void main (String[] args) {
		Restaurant restaurant = new Restaurant();
		restaurant.open();
	}
}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
