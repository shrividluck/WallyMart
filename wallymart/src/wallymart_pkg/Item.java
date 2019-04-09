package wallymart_pkg;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.util.ArrayList;

public class Item {
	//Four filenames --> one filename per category
	static final String fileNameApparel = "DatabaseMock/Apparel";
	static final String fileNameGrocery = "DatabaseMock/Grocery";
	static final String fileNameTech = "DatabaseMock/Tech";
	static final String fileNameMisc = "DatabaseMock/Misc";

	// Four methods to display Items
	int itemID;
	String itemName;
	float itemPrice;
	String itemCategory;	
	int itemAvailableCount;
	
	Item(int iID, String iName, float iPrice, String iCategory,int iCount) {
		this.itemID = iID;
		this.itemName = iName;
		this.itemPrice = iPrice;
		this.itemCategory = iCategory;
		this.itemAvailableCount = iCount;		
	}
	Item() {

	}
	public static Item getItemFromDB(int itemId) {
		// based on the itemID range, decide which db
		// open the db, search for the id by iterating line by line
		// once id is found, create new item based on the row and return the item.
		if(itemId >= 100 && itemId < 200) {
			return fetchFromDB(fileNameGrocery, itemId);
		}else if(itemId >= 200 && itemId < 300) {
			return fetchFromDB(fileNameTech, itemId);
		}else if(itemId >= 300 && itemId < 400) {
			return fetchFromDB(fileNameApparel, itemId);
		}else if(itemId >= 400 && itemId < 500) {
			return fetchFromDB(fileNameMisc, itemId);
		}else {
			System.out.println("Error! Invalid item id range!");
		}
		return null;    	
	}

	public static Item fetchFromDB(String fileDB, int itemId) {
		BufferedReader br;
		String ln;
		try {
			br = new BufferedReader(new FileReader(fileDB));
			while((ln = br.readLine()) != null) {
				if(ln.charAt(0) == '#') {
					continue;
				}
				String[] dbRow = ln.split(",");
				if(Integer.parseInt(dbRow[0]) == itemId) {
					//#itemID, itemName, itmePrice, itemCat, itemCount
					br.close();
					return new Item(Integer.parseInt(dbRow[0]),dbRow[1],Float.parseFloat(dbRow[2]),dbRow[3],Integer.parseInt(dbRow[4]));
				} 
			}
			br.close();
		}
		catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return null;

	}
	public int getItemID() {
		return itemID;
	}
	public void setItemID(int itemID) {
		this.itemID = itemID;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public float getItemPrice() {
		return itemPrice;
	}
	public void setItemPrice(float itemPrice) {
		this.itemPrice = itemPrice;
	}
	public String getItemCategory() {
		return itemCategory;
	}
	public void setItemCategory(String itemCategory) {
		this.itemCategory = itemCategory;
	}
	public int getItemAvailableCount() {
		return itemAvailableCount;
	}
	public void setItemAvailableCount(int itemCount) {
		this.itemAvailableCount = itemCount;
	}

	public static void updateItemCount(String itemCategory,String iName) {    	
		BufferedReader br;
		String ln;
		StringBuffer inputBuffer = new StringBuffer();
		try {
			br = new BufferedReader(new FileReader("DatabaseMock/"+itemCategory));
			while((ln = br.readLine()) != null) {
				if(ln.charAt(0) == '#') {
					continue;
				}
				String[] row = ln.split(",");
				if(row[1].equals(iName) && row[3].equals(itemCategory)) {
					int count = Integer.parseInt(row[4]);
					count--;
					row[4] = Integer.toString(count);
					ln = String.join(",", row);
				}
				inputBuffer.append(ln);
				inputBuffer.append("\n");
			}
			String res1 = inputBuffer.toString();
			br.close();
			FileOutputStream fileOut = new FileOutputStream("DatabaseMock/"+itemCategory);
			fileOut.write(res1.getBytes());
			fileOut.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// switch method to call items in categories

	public static ArrayList<Item> showItemsInCategories (String catName) {

		return displayItemsinCategory("DatabaseMock/"+ catName);		
	}

	public static ArrayList<Item> showAllItemsInAllCategories () {

		ArrayList<Item> db = displayItemsinCategory(fileNameApparel);
		db.addAll(displayItemsinCategory(fileNameGrocery));
		db.addAll(displayItemsinCategory(fileNameTech));
		db.addAll(displayItemsinCategory(fileNameMisc));
		return db;
	}


	public static ArrayList<Item>displayItemsinCategory(String fname){
		ArrayList<Item> res = new ArrayList<>();		
		BufferedReader br;
		String ln;

		try {
			br = new BufferedReader(new FileReader(fname));
			while((ln = br.readLine()) != null) {
				if(ln.charAt(0) == '#') {
					continue;
				}
				String[] apparelRow = ln.split(",");
				res.add(new Item(Integer.parseInt(apparelRow[0]),apparelRow[1],Float.parseFloat(apparelRow[2]),
						apparelRow[3], Integer.parseInt(apparelRow[4])));
			}
			br.close();
		}
		catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return res;
	}





}
