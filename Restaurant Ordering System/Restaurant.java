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
//		Main.clear();
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
			        System.out.println("Please enter letters only! No numbers or special characters.");
			        Main.delay(500);
			    }
			}
			running = true;
			while(running) {
				try {
					System.out.println("Enter your cash: ");
					System.out.print(">> ₱");
					double cash = in.nextDouble();
					this.customerCashAmount = cash;
					running = false;
				}
					catch(InputMismatchException e) {
						System.out.println("Please Enter A Number!");
						Main.delay(500);
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
						System.out.println("Incorrect. Please try again.");
						Main.delay(500);
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
						System.out.println("Incorrect. Please try again.");
						Main.delay(500);
			}
		}
	}
}

// [0] Main Menu
public void mainMenu() {
	System.out.println("\nWelcome to our Restaurant! " + 
					   "\nCustomer: " + customerName +
					   "\nCustomer Cash Amount: ₱" + customerCashAmount);
	if (orders.size() < 1) {
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
	    System.out.println("Enter your orders (Press [Q] to quit)");
	    boolean running = true;
	    int i = 1;
		    while(running) {
		        System.out.print("Order " + i + ": ");
		        String input = in.nextLine().trim().toUpperCase();
			        if (input.equals("Q")) {
			            running = false;
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
			                    System.out.println("No food selected. Please try again.");
				                }
			            } 
			            catch (NumberFormatException e) {
			                System.out.println("Incorrect. Please Try Again");
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
		            break; // Exit the browseMenu
		        }
	
		        switch (choice) {
		            case "1":
		                boolean inFoodMenu = true;
			                while (inFoodMenu) {
			                    System.out.println("\nWelcome to Food Menu! (Press [Q] to Go Back)");
				                    for (int i = 0; i < 4; i++) {
				                        System.out.println("[" + (i + 1) + "] " + menuItems[i] + " - ₱" + menuItems[i].getPrice());
				                    }
			                    System.out.print(">> ");
			                    String input = in.nextLine().trim();
				                    if (input.equalsIgnoreCase("Q")) {
				                    	inFoodMenu = false; 
				                    }
			                   
		                }
		                break;
	
		            case "2":
		                boolean inDrinksMenu = true;
			                while (inDrinksMenu) {
				                    System.out.println("\nWelcome to Drinks Menu! (Press [Q] to Go Back)");
					                    for (int i = 4; i < 7; i++) {
					                        System.out.println("[" + (i + 1) + "] " + menuItems[i] + " - ₱" + menuItems[i].getPrice());
					                    }
				                    System.out.print(">> ");
				                    String input = in.nextLine().trim();
					                    if (input.equalsIgnoreCase("Q")) {
					                        inDrinksMenu = false;
					                    }
		                }
		                break;
	
		            case "3":
		                boolean inAddOnsMenu = true;
			                while (inAddOnsMenu) {
				                    System.out.println("\nWelcome to Add-ons Menu! (Press [Q] to Go Back)");
					                    for (int i = 7; i < 9; i++) {
					                        System.out.println("[" + (i + 1) + "] " + menuItems[i] + " - ₱" + menuItems[i].getPrice());
					                    }
				                    System.out.print(">> ");
				                    String input = in.nextLine().trim();
					                    if (input.equalsIgnoreCase("Q")) {
					                        inAddOnsMenu = false;
					                    }
		                }
		                break;
	
		            default:
		                System.out.println("Incorrect. Please try again.");
		                Main.delay(500);
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
		    		mainMenu();
		    		break;
		    	}
		    	else if (choice.equalsIgnoreCase("R")) {
		    		removeOrder();
		    		break;
		    	}
		    	else {
		    		System.out.println("Incorrect! Please Try Again.");
		    		Main.delay(500);
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
			    			mainMenu();
			    			running = false;
			    			break;
			        	case "Y" :
			        		if (customerCashAmount >= orderTotal) {
			        			customerCashAmount -= orderTotal;
			        				System.out.println("Paid Successfully!");
			        				Main.delay(500);
			        				System.out.println("Remaining Balance: ₱" + customerCashAmount);
			        				Main.delay(500);
			        				
			        			boolean goBack = true;
				        			while (goBack) {
				        				System.out.println("Press [Q] to go back to Main Menu");
				        				String confirm = in.nextLine().trim();
				        					if (confirm.equalsIgnoreCase("Q")) {
				        						mainMenu();
				        						goBack = false;
				        						running = false;
				        						}
				        					else {
				        						System.out.println("Incorrect! Please Try Again.");
				        						}       	
				        					}
			        				}
			    			else if (customerCashAmount < orderTotal) {
								System.out.println("Insufficient Balance!");
								Main.delay(500);
								
								boolean goBack = true;
				        			while (goBack) {
					        			System.out.println("Remove some orders to fit your budget");
										System.out.println("Press [Q] to go back to Main Menu");
										String confirm = in.nextLine().trim();
											if (confirm.equalsIgnoreCase("Q")){
												mainMenu();
												goBack = false;
												running = false;
												}
											else {
												System.out.println("Incorrect! Please Try Again.");
												}       	
				        				}
			    			}
			        			break;
			        	default:
			        		System.out.println("Incorrect! Please Try Again.");
			        		Main.delay(500);
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
	                     System.out.println("Invalid quantity! Please try again.");
	                 }
	             } catch (NumberFormatException e) {
	                 System.out.println("Invalid input! Please enter a number.");
	             }
	         } else {
	             orderTotal -= orderToRemove.getTotalPrice();
	             orders.remove(orderIndex);
	             System.out.println("Order removed!");
	         }
	         
	         Main.delay(500);
	     } else {
	         System.out.println("Invalid order number! Please try again.");
	         Main.delay(500);
	     }
	 } catch (NumberFormatException e) {
	     System.out.println("Invalid input! Please enter a number.");
	     Main.delay(500);
	 	}
	}
}

