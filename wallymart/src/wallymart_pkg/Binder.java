package wallymart_pkg;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Binder {


	public boolean authenticateUser(String username, String password) {
		User usr = User.getUserFromDB(username);
		if(usr == null) {
			System.out.println("User not found");
			return false;
		}
		if(usr.name.equals(username) && usr.password.equals(password)) {
			usr.authenticated = true;
			return true;
		}
		return false;
	}


	public void signUpUser(String n, String eml, String pwd) {
		//call addUserToDB method from User class
		User u = new User(n,eml,pwd);
		u.addUserToDB(u);
	}

	public void populateCart(String uname, int itemID, int count) {
		// have to call addItemsToCart from Cart class
		Item i = Item.getItemFromDB(itemID);
		Cart.addItemToCart(i, count, uname);
	}

	public void viewCart(String usrnm) {
		Cart.displayCart(usrnm);
	}

	public void populateWishList(String usr, int iId) {
		// have to call addToWL method from WishList class
		Item i = Item.getItemFromDB(iId);
		Wishlist.addItemToWL(i, usr);
	}
	public void viewWishList(String unm) {
		Wishlist.displayWL(unm);
	}

	// method to call displayCategories() of ProductCategory class
	public void getProductCategories() {
		ArrayList<ProductCategory> pc = ProductCategory.displayCategories();
		System.out.println("Product Categories in WallyMart:");
		for(ProductCategory p : pc) {
			System.out.println(p.catID +" "+ p.catName);
		}
		System.out.println();
		System.out.println();
		Scanner sc = new Scanner(System.in);
		String Category = "Apparel";
		while(true) {
			System.out.println("Please enter catID to see items in category or 0 to exit to Main Menu:");

			switch(sc.nextInt()){
			case 0 : // sc.close();
                     return;			
			case 1 : Category = "Grocery"; break;
			case 2 : Category = "Tech"; break;
			case 3 : Category = "Apparel"; break;
			case 4 : Category = "Misc"; break;
			}
			ArrayList<Item> items = Item.showItemsInCategories(Category);
			System.out.println("Items in " + " " + Category + "in WallyMart are:");
			for(Item item: items) {
				System.out.println(item.itemID + " " + item.itemName + " $" + item.itemPrice);
			}
			System.out.println();
			System.out.println();			
		}
		
	}
	// method to get order details from Order class
	public void getOrderInfo(int orderId) {
		Order order_details = Order.displayOrderDetails(orderId);
		if(order_details != null) {
			System.out.println("Order_details : " + "Order_ID : " + " " + order_details.orderID + " " + "Order_Item : "+ order_details.orderItem + 
					" " +"Order_Username : " + order_details.orderUser + " " + "Order_Count : " + order_details.orderCount + " " +"Order_Total_Cost : " + order_details.orderTotal);			
		} else {
			System.out.println("Order details not found!");
		}
	}


	public static void main(String[] args) {

		Binder bd = new Binder();
		Scanner sc = new Scanner(System.in);
		Boolean login = false;
		int input = 0;
		String user = null;

		while(!login) {

			System.out.println("Please type 0 to Signup");
			System.out.println("Please type 1 to Login");
			System.out.println("Please type 8 to Exit Wallymart app");
			input = sc.nextInt();

			switch(input) {
			case 0 : System.out.println("Entered 0 : Signup");
			System.out.println("Enter name");
			String name = sc.next();
			System.out.println("Enter email");
			String email = sc.next();
			System.out.println("Enter password");
			String pwd = sc.next();
			bd.signUpUser(name,email,pwd);
			System.out.println("SignUp successful!!");
			break;

			case 1 : System.out.println("Entered 1 : Login");
			System.out.println("Enter username");
			String nm = sc.next();
			System.out.println("Enter password");
			String p = sc.next();
			if(bd.authenticateUser(nm, p)) {
				System.out.println(nm + " logged in successfuly!!");
				login = true;
				user = nm;
			} else {
				System.out.println("Wrong username or password please try again!");
			}
			break;

			case 8:  System.out.println("Entered 8 : Exit Wallymart app");
			System.exit(0);
			break; 
			}
		}

		while(true) {
			System.out.println("Please type 2 to Display Categories");
			System.out.println("Please type 3 to Display items");
			System.out.println("Please type 4 to Add item to cart");
			System.out.println("Please type 5 to Purchase item");
			System.out.println("Please type 6 to Logout");
			System.out.println("Please type 7 to View Order Details");
			System.out.println("Please type 8 to Exit Wallymart app");
			System.out.println("Please type 9 to Add item to cart");
			System.out.println("Please type 10 to View cart");
			System.out.println("Please type 11 to Add item to Wishlist");
			System.out.println("Please type 12 to View Wishlist");
//			try {
//				TimeUnit.SECONDS.sleep(5);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
			//sc.nextLine();
			input = sc.nextInt();
   
			switch(input) {
			case 1 : System.out.println("Entered 1 : Login");
			System.out.println("Enter username");
			String uname = sc.next();
			System.out.println("Enter password");
			String pwd = sc.next();
			if(bd.authenticateUser(uname, pwd)) {
				System.out.println("User login successful!!");
			} else {
				System.out.println("Wrong username or password please try again!");
			}
			break;

			case 2 : System.out.println("Entered 2 : Display Categories");	
			// send this to another binder method
			bd.getProductCategories();			
			break;
			
			case 3 : System.out.println("Entered 3 : Display all items in WallyMart");
			//display all items in all categories
			Item.showAllItemsInAllCategories();
			break;
			
			case 4:  System.out.println("Entered 4 : Add item to cart");
			System.out.println("Enter item ID to add to cart");
			int itemID = sc.nextInt();
			System.out.println("Enter number of items to add to cart");
			int count = sc.nextInt();			
			bd.populateCart(user, itemID, count);
			break;
			
			case 5:  System.out.println("Entered 5 : Purchase item");
			break;
			
			case 6:  System.out.println("Entered 6 : Logout");
			//call routine to logout -- usr db and update authenticated to false
			// user = null;
			break;   
			
			case 7:  System.out.println("Entered 7 : View Order Details");
			System.out.println("Enter order ID");
			int orderId = sc.nextInt();
			bd.getOrderInfo(orderId);
			break;
			
			case 8:  System.out.println("Entered 8 : Exit Wallymart app");
			System.exit(0);
			
						
			case 10: System.out.println("Entered 10 : Display cart");
			bd.viewCart(user);
			break;
			
			case 11: System.out.println("Entered 11 : Add item to WishList");
			System.out.println("Enter item ID");
			int item = sc.nextInt(); 
			bd.populateWishList(user, item);
			break;
			
			case 12: System.out.println("Entered 12 : View WishList");
			bd.viewWishList(user);
			sc.close(); break;
			
			default: break;
			}

		}

	}

}
