import java.util.Scanner;
import java.util.ArrayList;
import java.util.InputMismatchException;

public class Restaurant {
    private Scanner in = new Scanner(System.in);
	private String customerName;
	private double customerCashAmount = 0;
	private ArrayList<OrderQuantity> orders = new ArrayList<>();
	private int orderTotal = 0;
	private MenuItem[] menuItems = {
		    // Foods
		    new MenuItem("Choco lanay", 20),
		    new MenuItem("Assorted Bavarian", 15),
		    new MenuItem("Ron's Turon", 12),
		    new MenuItem("Chicken Pastil", 35),
		    // Drinks
		    new MenuItem("Mountain Dew", 35),
		    new MenuItem("Mineral Water", 25),
		    new MenuItem("Orange Juice", 20),
		    // Add-ons
		    new MenuItem("Gravy", 5),
		    new MenuItem("Lomboy", 15)	};
	
	// Constructor
	public Restaurant() {}
	
	// Start Program
	public void open() {
		boolean running = true;
		System.out.println("Hello!");
		Main.delay(500);
		Main.clear();
		System.out.println("Welcome to our Restaurant!");
		Main.delay(500);
			while (running) {
			    System.out.println("Enter your name: ");
			    System.out.print(">> ");
			    String name = in.nextLine();
			    if (name.matches("[a-z A-Z ]+")) {
			        this.customerName = name;
			        running = false;
			    } else {
			        printError("Please enter letters only! No numbers or special characters.");
			    }
			}
			running = true;
			while(running) {
				try {
					System.out.println("Enter your cash: ");
					System.out.print(">> ₱");
					double cash = in.nextDouble();
					if (cash < 0) {
                                            printError("Cash amount can't be negative!");
                                            continue;
                                        }
					this.customerCashAmount = cash;
					running = false;
				}
					catch(InputMismatchException e) {
						printError("Please Enter A Number!");
						in.nextLine();
					}
			}
			running = true;
			in.nextLine();
			while(running) {
				mainMenu();
				String choice = in.nextLine();	
			if (orders.size() < 1) {
				switch (choice) {
					case "1": 
						placeOrder();
						break;
					case "2": 
						browseMenu();
						break;
					case "3": 
						running = false;
						break;
					default:
						printError("Incorrect! Please Try Again.");
					}
			}
			else {
				switch (choice) {
					case "1": 
						placeOrder();
						break;
					case "2": 
						browseMenu();
						break;
					case "3": 
						viewOrder();
						break;		
					case "4": 
						generateBill();
						break;
					case "5":
						running = false;
						break;
					default:
						printError("Incorrect! Please Try Again.");
			}
		}
	}
}

// [0] Main Menu
public void mainMenu() {
	System.out.println("\nWelcome to our Restaurant! " + 
					   "\nCustomer: " + customerName +
					   "\nCustomer Cash Amount: ₱" + customerCashAmount);
	if (orders.isEmpty()) {
		System.out.println("\n1. Place Order");
		System.out.println("2. Browse Menu");
		System.out.println("3. Exit");
		System.out.print(">> ");
	}
	else {
		System.out.println("\n1. Place Order");
		System.out.println("2. Browse Menu");
		System.out.println("3. View Current Order");
		System.out.println("4. Generate Bill");
		System.out.println("5. Exit");
		System.out.print(">> ");
		}
	}
	
	// [1] Place Order
	public void placeOrder() {
	    System.out.println("\n========== MENU ==========");
            for (int i = 0; i < menuItems.length; i++) {
                System.out.println("[" + (i + 1) + "] " + menuItems[i].getName() + " - ₱" + menuItems[i].getPrice());
            }
            System.out.println("==========================");
            System.out.println("Enter your orders by number (Press [Q] to quit)");
	    boolean running = true;
	    int i = 1;
		    while(running) {
		        System.out.print("Order " + i + ": ");
		        String input = in.nextLine().trim().toUpperCase();
			        if (input.equals("Q")) {
			            return;
			        	} 
			        else {
			            try {
			                int menuIndex = Integer.parseInt(input) - 1;
				                if (menuIndex >= 0 && menuIndex < menuItems.length) {				        
				                    boolean itemExists = false;
					                    for (OrderQuantity orderItem : orders) {
					                        if (orderItem.getItem() == menuItems[menuIndex]) {
					                            orderItem.incrementQuantity();
					                            orderTotal += menuItems[menuIndex].getPrice();
					                            itemExists = true;
					                            System.out.println("Added another " + menuItems[menuIndex].getName());
					                            break;
					                        }
					                    }				                    				             
				                    if (!itemExists) {
				                        orders.add(new OrderQuantity(menuItems[menuIndex], 1));
				                        orderTotal += menuItems[menuIndex].getPrice();
				                        System.out.println("Added " + menuItems[menuIndex].getName());
				                    }
				                    i++;
				                } 
				                else {
            			                    printError("No food selected. Please try again.");
				                }
			            } 
			            catch (NumberFormatException e) {
			               printError("Incorrect! Please Try Again.");
			            	}
			        }
		    }
	}

	// [2] Browse Menu
	public void browseMenu() {
	    while (true) {
	        System.out.println("Check our catalogue of delicious foods (Press [Q] to Exit)");
	        System.out.println("1. Foods");
	        System.out.println("2. Drinks");
	        System.out.println("3. Add-ons");
	        System.out.print(">> ");
	        String choice = in.nextLine().trim();
		        if (choice.equalsIgnoreCase("Q")) {
		            return; // go back to main menu
		        }
	
		        switch (choice) {
		            case "1":
		               showMenuCategory("Foods", 0, 4); 
		               break;
	
		            case "2":
		               showMenuCategory("Drinks", 4, 7); 
        		       break;
	
		            case "3":
		               showMenuCategory("Add-ons", 7, 9); 
		               break;
	
		            default:
		              printError("Incorrect! Please Try Again.");
	        }
	    }
	}

	// [3] View Order
	public void viewOrder() {
		while(true) {
		    System.out.println("Name: " + customerName);
		        System.out.println("Orders:");
		        for (int i = 0; i < orders.size(); i++) {
		            OrderQuantity order = orders.get(i);
		            System.out.println("[" + (i + 1) + "] " + order.getQuantity() + "x " + 
		                              order.getItem().getName() + " - ₱" + order.getItem().getPrice() + 
		                              " each (Total: ₱" + order.getTotalPrice() + ")");
		        }
		    System.out.println("Press [Q] to go back to Main Menu");
		    System.out.println("Press [R] to remove orders");
		    System.out.print(">> ");
		    String choice = in.nextLine().trim();
		    	if (choice.equalsIgnoreCase("Q")) {
		    		return;
		    	}
		    	else if (choice.equalsIgnoreCase("R")) {
		    		removeOrder();
		    		break;
		    	}
		    	else {
		    		printError("Incorrect! Please Try Again.");
        	    	}
		}
	}
	
	// [4] Generate Bill
	public void generateBill() {
		System.out.print("Generating");
		for(int i = 1; i < 4; i++) {		
		Main.delay(500);
		System.out.print(".");
		}
		boolean running = true;
			while (running) {
				System.out.println("\nCustomer Name: " + customerName);
				System.out.println("Customer Cash Amount: ₱" + customerCashAmount);
				System.out.println("Total bill: ₱" + orderTotal);
				System.out.println("Press [Q] to go back to Main Menu");
				System.out.println("Press [Y] to pay bill");
				System.out.print(">> ");
		        String choice = in.nextLine().trim().toUpperCase();
			        switch(choice) {
			        	case "Q" :
			    			return;
			        	case "Y" :
			        		if (customerCashAmount >= orderTotal) {
			        			customerCashAmount -= orderTotal;
			        				System.out.println("Paid Successfully!");
			        				orders.clear();
			        				orderTotal = 0;
			        				Main.delay(500);
			        				System.out.println("Remaining Balance: ₱" + customerCashAmount);
			        				Main.delay(500);
			        				
			        			boolean goBack = true;
				        			while (goBack) {
				        				System.out.println("Press [Q] to go back to Main Menu");
				        				String confirm = in.nextLine().trim();
				        					if (confirm.equalsIgnoreCase("Q")) {
				        						return;
				        						}
				        					else {
				        						printError("Incorrect! Please Try Again.");
				        						}       	
				        					}
			        				}
			    			else if (customerCashAmount < orderTotal) {
								printError("Insufficient Balance!");
								
								boolean goBack = true;
				        			while (goBack) {
					        			System.out.println("Remove some orders to fit your budget");
										System.out.println("Press [Q] to go back to Main Menu");
										String confirm = in.nextLine().trim();
											if (confirm.equalsIgnoreCase("Q")){
												return;
												}
											else {
												printError("Incorrect! Please Try Again.");
												}       	
				        				}
			    			}
			        			break;
			        	default:
			        		printError("Incorrect! Please Try Again.");
			 }
		}
	}
	
// Method to remove an order
	public void removeOrder() {
	 System.out.println("\nSelect the order number to remove:");
	 for (int i = 0; i < orders.size(); i++) {
	     OrderQuantity order = orders.get(i);
	     System.out.println("[" + (i + 1) + "] " + order.getQuantity() + "x " + 
	                       order.getItem().getName() + " - ₱" + order.getTotalPrice());
	 }
	 System.out.println("[Q] Cancel");
	 System.out.print(">> ");
	 
	 String input = in.nextLine().trim();
	 
	 if (input.equalsIgnoreCase("Q")) {
	     return; 
	 }
	 
	 try {
	     int orderIndex = Integer.parseInt(input) - 1;
	     
	     if (orderIndex >= 0 && orderIndex < orders.size()) {
	         OrderQuantity orderToRemove = orders.get(orderIndex);
	         
	     
	         if (orderToRemove.getQuantity() > 1) {
	             System.out.println("Current quantity: " + orderToRemove.getQuantity());
	             System.out.println("How many would you like to remove? (1-" + orderToRemove.getQuantity() + ")");
	             System.out.print(">> ");
	             String quantityInput = in.nextLine().trim();
	             
	             try {
	                 int quantityToRemove = Integer.parseInt(quantityInput);
	                 
	                 if (quantityToRemove > 0 && quantityToRemove <= orderToRemove.getQuantity()) {
	                     int amountToReduce = orderToRemove.getItem().getPrice() * quantityToRemove;
	                     orderTotal -= amountToReduce;
	                     if (quantityToRemove == orderToRemove.getQuantity()) {
	                         orders.remove(orderIndex);
	                         System.out.println("Order removed completely!");
	                     } else {
	                         orderToRemove.decreaseQuantity(quantityToRemove);
	                         System.out.println("Removed " + quantityToRemove + " from your order!");
	                     }
	                 } else {
	                     printError("Invalid quantity! Please try again.");
	                 }
	             } catch (NumberFormatException e) {
	                 printError("Invalid input! Please enter a number.");
	             }
	         } else {
	             orderTotal -= orderToRemove.getTotalPrice();
	             orders.remove(orderIndex);
	             System.out.println("Order removed!");
	         }
	         
	         Main.delay(500);
	     } else {
	         printError("Invalid order number! Please try again.");
	     }
	 } catch (NumberFormatException e) {
        	 printError("Invalid input! Please enter a number.");
	 	}
	}
    	public void printError(String message) {
    	   System.out.println("❌ " + message);
           Main.delay(500); 
  	 }
  	 
  	public void showMenuCategory(String category, int start, int end) {
            boolean running = true;
            while (running) {
                System.out.println("\nWelcome to " + category + " Menu! (Press [Q] to Go Back)");
                for (int i = start; i < end; i++) {
                    System.out.println("[" + (i + 1) + "] " + menuItems[i] + " - ₱" + menuItems[i].getPrice());
                }
                System.out.print(">> ");
                String input = in.nextLine().trim();
                if (input.equalsIgnoreCase("Q")) {
                    running = false;
                }
            }
            
    }
 
}



