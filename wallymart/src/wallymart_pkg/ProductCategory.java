package wallymart_pkg;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class ProductCategory {

	static final String fileName = "DatabaseMock/ProductCategoriesDB";
	public int catID; 
	public String catName;

	ProductCategory(int cID, String cName) {
		this.catID = cID;
		this.catName = cName;
	}
	ProductCategory() {

	}
	//method to iterate over categories

	public static ArrayList<ProductCategory> displayCategories() {
		//open file
		// read line by line
		// add to arraylist and return

		// read from file, if user exists, else, return exception
		// open a file

		//File user_file = new File(fileName);
		//Read line by line from file
		ArrayList<ProductCategory> res = new ArrayList<>();		
		BufferedReader br;
		String ln;

		try {
			br = new BufferedReader(new FileReader(fileName));
			while((ln = br.readLine()) != null) {
				if(ln.charAt(0) == '#') {
					continue;
				}
				String[] catRow = ln.split(",");
				//ProductCategory temp = new ProductCategory(Integer.parseInt(catRow[0]),catRow[1]);
				res.add(new ProductCategory(Integer.parseInt(catRow[0]),catRow[1]));
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
