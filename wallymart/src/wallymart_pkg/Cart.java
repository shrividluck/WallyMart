package wallymart_pkg;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

public class Cart {
	
	//#username,itemID, itemName, itemPrice, itemCat, itemCount
	String usrnm;
	int itemID;
	String itemNm;
	Float itemCost;
	String itemCat;
	int itemCount;
	
	static final String fileName = "DatabaseMock/CartDB";
	Cart(String u,int id,String iNm,float cost,String catgry,int count) {
		this.usrnm = u;
		this.itemID = id;
		this.itemNm = iNm;
		this.itemCost = cost;
		this.itemCat = catgry;
		this.itemCount = count;
	}
	Cart() {
		
	}


	//method to add items to cart
	public static void addItemToCart(Item item, int count,String username) {
		String line;
		try {
			BufferedReader br = new BufferedReader(new FileReader(fileName));
			while((line = br.readLine()) != null) {
				if(line.charAt(0) == '#') {
					continue;
				}
				String[] cartRow = line.split(",");				    
			}
			br.close();
			if(item.itemAvailableCount >= count) {
				BufferedWriter bw = new BufferedWriter(new FileWriter(fileName, true));
				bw.append(username);
				bw.append(Integer.toString(item.itemID));bw.append(",");
				bw.append(item.itemName);bw.append(",");
				bw.append(Float.toString(item.itemPrice));bw.append(",");
				bw.append(item.itemCategory);bw.append(",");
				bw.append(Integer.toString(count));	            
				bw.close();
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}	
	}


	//method to print items in cart
	public static void displayCart(String username) {
		int numOfRowsInCart = 0;
		String line;
		try {
			BufferedReader br = new BufferedReader(new FileReader(fileName));
			while((line = br.readLine()) != null) {
				if(line.charAt(0) == '#') {
					continue;
				}
				String[] cartRow = line.split(",");	
				if(cartRow[0].equals(username)) {
					numOfRowsInCart++;
					System.out.println(cartRow); // if username matches, print the row
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}	
		if(numOfRowsInCart == 0) {
			System.out.println(username + " Your cart is empty!!");
		}
	}
}	
	
