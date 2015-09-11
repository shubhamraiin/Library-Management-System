//Adding a user to the database

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class add {

	static Connection conn = null;

	public add() {

		// Initialize variables for fields by data type

		// int linect = 0;

		try {
			// Create a connection to the local MySQL server, with the "company"
			// database selected.
			// conn =
			// DriverManager.getConnection("jdbc:mysql://localhost:3306/company",
			// "root", "mypassword");
			// Create a connection to the local MySQL server, with the NO
			// database selected.
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/","root", "");

			// Create a SQL statement object and execute the query.
			Statement stmt = conn.createStatement();

			// Set the current database, if not already set in the getConnection
			// Execute a SQL statement
			stmt.execute("use library;");

			String fname = gui.fname.getText();
			String lname = gui.lname.getText();
			String address = gui.address.getText();
			String phone = gui.phone.getText();
			// String f, l, a;
			@SuppressWarnings("unused")
			int rd;
			// Execute a SQL query using SQL as a String object
			String query = new String();
			query = "SELECT fname, lname, address FROM borrowers WHERE fname='"
					+ fname + "' AND lname='" + lname + "' AND address='"
					+ address + "' ";

			ResultSet rs = stmt.executeQuery(query);
			//System.out.println(query); 
			//System.out.println(rs.first());

			if (rs.first()) {
				JOptionPane.showMessageDialog(null, "User already exists!",
						"Duplicate", JOptionPane.ERROR_MESSAGE);
			} else if (fname.isEmpty() || lname.isEmpty() || address.isEmpty()) {
				
				
				// hack to avoid entering empty strings at start of the run
			} else {
				rd = stmt
						.executeUpdate("INSERT INTO borrowers(fname, lname, address, phone) VALUES('"
								+ fname
								+ "', '"
								+ lname
								+ "', '"
								+ address
								+ "', '" + phone + "' );");
				System.out.println("Record Inserted!");
				
				
				
				JOptionPane.showMessageDialog(null, "User Added in the Database!","Update", JOptionPane.INFORMATION_MESSAGE);
				
				//gui.message.setText("User Added");
			}// dno = rs.getInt("dno");

			// Do something with the data

			// End while(rs.next())

			// Always close the recordset and connection.
			rs.close();

			conn.close();
			// System.out.println("Success!!");
		} catch (SQLException ex) {
			System.out.println("Error in connection: " + ex.getMessage());
		}
	}

	/*
 *
 */
	static void newln() {
		System.out.println();
	}

}