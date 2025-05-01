package schoolProjectes;

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
		    new MenuItem("Beef Steak", 265),
		    new MenuItem("Pasta", 185),
		    new MenuItem("Caesar Salad", 350),
		    new MenuItem("Fried Chicken", 200),
		    // Drinks
		    new MenuItem("Purified Water", 50),
		    new MenuItem("Soda", 95),
		    new MenuItem("Orange Juice", 80),
		    // Add-ons
		    new MenuItem("Gravy", 20),
		    new MenuItem("Rice", 35)	};
		    
	// Start Program
	public void open() {
		Main.clear();
		boolean running = true;
		System.out.println("Hello!");
		Main.delay(1000);
		Main.clear();
		System.out.println("Welcome to our Restaurant!");
		Main.delay(500);
			while (running) {
			    System.out.println("Enter your name: ");
			    System.out.print(">> ");
			    String name = in.nextLine().trim();
			    if (name.trim().isEmpty()) {
			        printError("Name cannot be empty or only spaces!");
			    } else if (!name.matches("[a-zA-Z]+")) {
			        printError("Please enter letters only! No spaces, numbers, or special characters.");
			    } else {
			        this.customerName = name;
			        running = false;
			    }
			}
			running = true;
			while(running) {
				try {
					System.out.println("Enter your cash: ");
					System.out.print(">> ₱");
					double cash = in.nextDouble();
					if (cash <= 0) {
						printError("You can't buy food with that money");
						continue;
                     }
					if (cash > 1000000) {
					    printError("Please enter a smaller amount.");
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
				String input = in.nextLine();	
			if (orders.isEmpty()) {
				switch (input) {
					case "1": order();						
						break;
					case "2": 
						System.out.println("╔══════════════════════════════╗");
						System.out.println("   Thank you for dining with  ");
						System.out.println("          us, " + customerName + "!         ");
						System.out.println("╚══════════════════════════════╝");
						Main.delay(1000);
						running = false;
						break;
					default: printError("Incorrect! Please Try Again.");
					}
			}
			else {
				switch (input) {
					case "1": order();
						break;
					case "2": viewOrder();
						break;
					case "3": generateBill();
						break;		
					case "4": 
						if (confirmExit()) {
						System.out.println("╔══════════════════════════════╗");
						System.out.println("   Thank you for dining with  ");
						System.out.println("          us, " + customerName + "!");
						System.out.println("╚══════════════════════════════╝");
						Main.delay(1000);
						running = false;
						}
						break;
					default: printError("Incorrect! Please Try Again.");					
				}
			}
		}
	}

	// [0] Main Menu Display method
	public void mainMenu() {
		Main.clear();
		System.out.println("Welcome to our Restaurant! " + 
						   "\nCustomer: " + customerName +
						   "\nCash Balance: ₱" + customerCashAmount);
		if (orders.isEmpty()) {
			System.out.println("\n[1] Order");
			System.out.println("[2] Exit");
			System.out.print(">> ");
		} else {		
			System.out.println("\n[1] Add Order");
			System.out.println("[2] View Current Order");
			System.out.println("[3] Generate Bill");
			System.out.println("[4] Exit");
			System.out.print(">> ");
		}
	}
	// [1] Order Method
	public void order() {
	    Main.clear();
	    boolean running = true;
	    while (running) {
	        Main.clear();
	        System.out.println("╔══════════════════════════════╗");
	        System.out.println("      Welcome to Our Menu        ");
	        System.out.println("╚══════════════════════════════╝");
	        System.out.println("[1] 🍽️ Foods");
	        System.out.println("[2] 🥛 Drinks");
	        System.out.println("[3] ➕ Add-ons");
	        System.out.println("[4] Quit");
	        System.out.print(">> ");
	        String input = in.nextLine().trim();
	        switch (input) {
	            case "1": showMenuandPlaceOrder("Foods", 0, 4); // Display Foods category
	                   break;
	            case "2": showMenuandPlaceOrder("Drinks", 4, 7); // Display Drinks category
	                   break;
	            case "3": showMenuandPlaceOrder("Add-ons", 7, 9); // Display Add-ons category
	                   break;
	            case "4": return;
	            default: printError("Incorrect! Please try again.");
	        }
	    }
	}

	// [2] Show Menu and Place Order Method
	public void showMenuandPlaceOrder(String category, int start, int end) {
	    Main.clear();
	    boolean running = true;
	    while (running) {
	        Main.clear();
	        System.out.println("Welcome to the " + category + " Menu!");
	        // Display menu items with CATEGORY-SPECIFIC numbering (1, 2, 3...)
	        for (int i = start; i < end; i++) {
	            System.out.println("[" + (i - start + 1) + "] " + menuItems[i].getName() + " - ₱" + menuItems[i].getPrice());
	        }
	        System.out.println("\nInput number to Order");
	        System.out.println("[Q] - Go Back");
	        System.out.print(">> ");
	        String input = in.nextLine().trim();
	        if (input.equalsIgnoreCase("Q")) {
	            return;
	        } else {
	            try {
	                int localIndex = Integer.parseInt(input) - 1;  
	                
	                if (localIndex >= 0 && localIndex < (end - start)) {
	                    int menuIndex = start + localIndex;
	                    boolean itemExists = false;
	                    if (orderTotal + menuItems[menuIndex].getPrice() > customerCashAmount) {
                                    printError("You cannot afford this item.");
                                    break;
                                }
	                    for (OrderQuantity orderItem : orders) {
	                        if (orderItem.getItem() == menuItems[menuIndex]) {
	                            orderItem.incrementQuantity(); // Increase quantity
	                            orderTotal += menuItems[menuIndex].getPrice();
	                            itemExists = true;
	                            System.out.println("Added another " + menuItems[menuIndex].getName());
	                            Main.delay(500);
	                            break;
	                        }
	                    }
	                    if (!itemExists) {	                        
	                        // If item isn't already in order, add it
	                        orders.add(new OrderQuantity(menuItems[menuIndex], 1));
	                        orderTotal += menuItems[menuIndex].getPrice();
	                        System.out.println("Added " + menuItems[menuIndex].getName());
	                        Main.delay(500);
	                    }
	                } else {
	                    printError("Invalid selection! Please try again.");
	                }
	            } catch (NumberFormatException e) {
	                printError("Invalid input! Please enter a number.");
	            }
	        }
	    }
	}

	// [3] View Order Method
	public void viewOrder() {
		Main.clear();
		while(true) {
			Main.clear();
		    System.out.println("╔══════════════════════════════╗");
	        System.out.println("       Current Order Details      ");
	        System.out.println("╚══════════════════════════════╝");
			System.out.println("Name: " + customerName);
		    System.out.println("Orders:\n");
		    System.out.printf("%-3s %-20s %-10s %-10s%n", "#", "Item", "Qty", "Total"); // Tabular Format for better display
		    for (int i = 0; i < orders.size(); i++) {
		        OrderQuantity order = orders.get(i);
		        System.out.printf("%-3d %-20s %-10d ₱%-10d%n", 
		            (i + 1), 
		            order.getItem().getName(), 
		            order.getQuantity(), 
		            order.getTotalPrice()
		        );
		    }

		    System.out.println("\n[1] Go Back");
		    System.out.println("[2] Remove Orders");
		    System.out.println("[3] Remove All Orders");
		    System.out.print(">> ");
		    String input = in.nextLine().trim();
		    	if (input.equalsIgnoreCase("1")) { 
		    		return; 
		    	}
		    	else if (input.equalsIgnoreCase("2")) {
		    		removeOrder();
		    		break;
		    	} 
		        else if (input.equalsIgnoreCase("3")) {
                    clearAllOrders();
                    return;
                } 
		    	else { printError("Incorrect! Please Try Again."); }	
		}
	}
	
	// [4] Generate Bill Method
	public void generateBill() {
		Main.clear();
		System.out.print("Generating");
			for(int i = 1; i < 4; i++) {		
				Main.delay(500);
				System.out.print(".");
			}
		boolean running = true;
			while (running) {
				Main.clear();
			    System.out.println("╔══════════════════════════════╗");
	            System.out.println("         Payment Details          ");
	            System.out.println("╚══════════════════════════════╝");
				System.out.println("Customer Name: " + customerName);
				System.out.println("Customer Cash Amount: ₱" + customerCashAmount);
				System.out.println("Total bill: ₱" + orderTotal);
				System.out.println("Press [1] to go back to Main Menu");
				System.out.println("Press [2] to pay bill");
				System.out.print(">> ");
		        String input = in.nextLine().trim().toUpperCase();
		        	if (input.equalsIgnoreCase("1")) { 
		        		return; 
		        	}		        	
		        	else if (input.equalsIgnoreCase("2")) {      		
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
										if (confirm.equalsIgnoreCase("Q")) { 
											return; 
										}		
										else { 
											printError("Incorrect! Please Try Again."); 
										}			    	
					        	}
			        	}      		
				 }
		   }
	}	
	
	// [5] Remove Order Method 
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
		if (input.equalsIgnoreCase("Q")) { return; }	
			try {
				int orderIndex = Integer.parseInt(input) - 1; // Convert String to integer and decrease 1 to match menu index
					if (orderIndex >= 0 && orderIndex < orders.size()) {
				         OrderQuantity orderToRemove = orders.get(orderIndex);
				         if (orderToRemove.getQuantity() > 1) { 	  // If order is more than 1, program will ask for user to input how many orders to remove
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
						                     } 
						                     else {
						                         orderToRemove.decreaseQuantity(quantityToRemove);
						                         System.out.println("Removed " + quantityToRemove + " from your order!");
						                     }
						                 } 
						                 else { 
						                	 printError("Invalid quantity! Please try again."); 
						                 }
						                 
					             } catch (NumberFormatException e) { printError("Invalid input! Please enter a number."); }
				         }        
				         else { 									   // if order is only one the program will automatically remove the order
				             orderTotal -= orderToRemove.getTotalPrice();
				             orders.remove(orderIndex);
				             System.out.println("Order removed!");
				         } 
				         Main.delay(500);
				     } 
					else {
				    	 printError("Invalid order number! Please try again."); 
				       }	     
			 } catch (NumberFormatException e) {  printError("Invalid input! Please enter a number."); }   
	}
	   // [6] Remove All Orders Method 
	   public void clearAllOrders() {
	        System.out.println("Are you sure you want to clear all orders? (Y/N)");
	        System.out.print(">> ");
	        String input = in.nextLine().trim();
	        if (input.equalsIgnoreCase("Y")) {
	            orders.clear();
	            orderTotal = 0;
	            System.out.println("All orders have been cleared.");
	            Main.delay(1000);
	        }
	    }
	
	// [7] Error Printing Method
    public void printError(String message) {
	   System.out.println("❌ " + message);
       Main.delay(500); 
	   Main.clear();
   }
    // [8] Confirm Exit if order is not empty Method
    public boolean confirmExit() {
        if (!orders.isEmpty()) {
            System.out.println("You have unpaid items in your cart.");
            System.out.println("Are you sure you want to exit? (Y/N)");
            System.out.print(">> ");
            String input = in.nextLine().trim();
            return input.equalsIgnoreCase("Y");
        }
        return true;
    }
}
