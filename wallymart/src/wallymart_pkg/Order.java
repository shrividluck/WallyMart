package wallymart_pkg;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Writer;


public class Order {
	//orderID,orderItem,orderUser,orderCount,order
	int orderID;
	String orderItem;
	String orderUser;
	int orderCount;
	float orderTotal;
	static final String fileName = "DatabaseMock/OrderDB";

	Order(int oID, String oItem, String oUser,int oCount,float cost) {
		this.orderID = oID;
		this.orderItem = oItem;
		this.orderUser = oUser;
		this.orderCount = oCount;
		this.orderTotal = cost*this.orderCount;
	}

	Order() {
	}
// not sure, check/redo
	public void addOrderToOrderDB(Order or) {
		Writer orderDBWriter = null;
		try {
			orderDBWriter = new BufferedWriter(new FileWriter(fileName, true));
			orderDBWriter.append((CharSequence) or);
			orderDBWriter.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}


	@SuppressWarnings("resource")
	public static Order displayOrderDetails(int orderNumber) {
		//ArrayList<Order> res = new ArrayList<>();	
		BufferedReader br;
		String ln;


		try {
			br = new BufferedReader(new FileReader(fileName));
			while((ln = br.readLine()) != null) {
				if(ln.charAt(0) == '#') {
					continue;
				}
				String[] orderRow = ln.split(",");
				if(Integer.parseInt(orderRow[0]) == orderNumber) {
					return new Order(Integer.parseInt(orderRow[0]),orderRow[1],orderRow[2],Integer.parseInt(orderRow[3]),
							Float.parseFloat(orderRow[4]));

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
}
