package wallymart_pkg;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;





public class User {

	/* Name, email, password, boolean authenticated, boolean admin
	 * setName, getName 
	 */
	String id;
	String name;
	String email;
	String password;
	boolean authenticated;
	boolean isAdmin;
	static final String fileName = "DatabaseMock/UserDB";


	User(String n, String eml, String pwd) {
		this.name = n;
		this.email = eml;
		this.password = pwd;	
	}

	User(String n, String eml, String pwd, boolean isAdmin) {
		this.name = n;
		this.email = eml;
		this.password = pwd;
		this.isAdmin = isAdmin;
	}

	User() {

	}

	public void addUserToDB(User u) {
		String line;
		int last_used_id = 0;

		try {
			BufferedReader br = new BufferedReader(new FileReader(fileName));
			while((line = br.readLine()) != null) {
				if(line.charAt(0) == '#') {
					continue;
				}
				String[] userRow = line.split(",");	
				last_used_id = Integer.parseInt(userRow[0]);
			}
			br.close();
			BufferedWriter bw = new BufferedWriter(new FileWriter(fileName, true));
			bw.append('\n');
			bw.append(Integer.toString(++last_used_id));bw.append(",");
			bw.append(u.name);bw.append(",");
			bw.append(u.password);bw.append(",");
			bw.append(u.email);bw.append(",");	
			bw.append("False");bw.append(",");
			bw.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}	
	}

	public static User getUserFromDB(String nm) { // read from the file object

		BufferedReader reader;
		String line;
		String name = null;
		String email = null;
		String password = null;
		Boolean authenticated = false;
		try {
			reader = new BufferedReader(new FileReader(fileName));
			while((line = reader.readLine()) != null) {
				if(line.charAt(0) == '#') {
					continue;
				}
				String[] userRow = line.split(",");
				if(userRow[1].equals(nm)) {
					//System.out.println("Supplied username : "+ nm + "Found userName: " + userRow[1]);
					System.out.println("Found User!!");
					name = userRow[1];
					password = userRow[2];
					email = userRow[3];
					reader.close();
					break;
				}
				//System.out.println(line);
			}
			reader.close();
		}
		catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		if(name == null) {
			System.out.println("User not found :( !!");
			return null;
		}
		return new User(name, email, password, authenticated);

	}

	public boolean isAuthenticated() {
		return this.authenticated;
	}

	public void setAuthenticated(boolean authenticated) {
		this.authenticated = authenticated;
	}


	public boolean isAdmin() {
		return this.isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
}
