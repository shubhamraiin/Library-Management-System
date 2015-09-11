/*
 * This class defines a record set table model, used for displaying all the information from the queries 
 * in the table provided in  the Search tab and Check In tab. 
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

@SuppressWarnings("serial")
public class ResultSetTable extends JTable {
	
	gui g =new gui();

		
	@SuppressWarnings({ "rawtypes", "unused", "unchecked" })
	public static TableModel returnSearchData(String bookid,String title,String fullname,String firstname,String mi,String lastname)
	{
		ArrayList columnNames = new ArrayList();
		ArrayList data = new ArrayList();
		// Connect to an MySQL Database, run query, get result set
				String url = "jdbc:mysql://localhost:3306/library";
				String userid = "root";
				String password = "";
		
		
		String sql = "SELECT DISTINCT b.book_id AS 'Book Id',l.branch_id AS 'Branch Id', b.title AS 'Title', a.author AS 'Auhtor', c.no_of_copies AS 'No. of Copies',c.no_of_copies-(select count(*) from book_loans b where b.book_id = c.book_id and b.branch_id = c.branch_id and b.date_in is null) As 'Available copies'  FROM book b, books_authors a, library_branch l, book_copies c WHERE b.book_id = a.book_id and c.branch_id = l.branch_id and c.book_id = a.book_id and ( (a.book_id <> '' or 1=1 ) and (a.book_id like '%" + bookid + "%')) and ( (b.title <> '' or 1=1 ) and (b.title like '%"+ title +"%')) and ((( (a.author <> '' or 1=1 ) and (a.author like '%"+ fullname +"%'))) and ( ( (a.fname <> '' or 1=1 ) and (a.fname like '"+firstname +"%')) and( (a.minit <> '' or 1=1 ) and  (((a.minit like '%"+mi +"%') or (a.minit is null) )))and( (a.lname <> '' or 1=1 ) and (a.lname like '"+lastname +"%'))));";


		DefaultTableModel tableModel = new DefaultTableModel(){
			
			public boolean isCellEditable(int row, int column) {
			       return false;
			    }
		};
		
		
		String[] tableRow;
		tableModel.addColumn("Book Id");
		tableModel.addColumn("Branch Id");
		tableModel.addColumn("Title");
		tableModel.addColumn("Author");
		tableModel.addColumn("No of Copies");
		tableModel.addColumn("Available Copies");
		
		
		try (Connection connection = DriverManager.getConnection(url, userid,password);
				Statement stmt = connection.createStatement();
				ResultSet rs = stmt.executeQuery(sql)) {
			ResultSetMetaData md = rs.getMetaData();
			int columns = md.getColumnCount();

			// Get column names
			for (int i = 1; i <= columns; i++) {
				columnNames.add(md.getColumnName(i));
			}

			// Get row data
			while (rs.next()) {
				ArrayList row = new ArrayList(columns);
				 tableRow = new String[6];
				
				tableRow[0] = rs.getString(1);
				tableRow[1] = rs.getString(2);
				tableRow[2] = rs.getString(3);
				tableRow[3] = rs.getString(4);
				tableRow[4] = rs.getString(5);
				tableRow[5] = rs.getString(6);
				tableModel.addRow(tableRow);
 
				
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		return tableModel;
	}
	
	
	@SuppressWarnings({ "unused", "unchecked", "rawtypes" })
	public static TableModel checkinSearch(String cibkid,String cicardno,String cifname,String cilname)
	{
		
		
	
		
		
		
		ArrayList columnNames = new ArrayList();
		ArrayList data = new ArrayList();
		// Connect to an MySQL Database, run query, get result set
				String url = "jdbc:mysql://localhost:3306/library";
				String userid = "root";
				String password = "";
		
		
			String sql = "SELECT DISTINCT b.book_id AS 'Book Id', l.branch_id AS 'Branch Id', d.card_no AS 'Card No',b.title AS 'Title', a.author AS 'Auhtor' FROM book b, books_authors a, library_branch l, book_copies c,borrowers d,book_loans e WHERE b.book_id = a.book_id and c.branch_id = l.branch_id and c.book_id = a.book_id and d.card_no = e.card_no and e.book_id = b.book_id and e.branch_id = l.branch_id and( (a.book_id <> '' or 1=1 ) and (a.book_id like '%" + cibkid + "%')) and( (d.card_no <> '' or 1=1 ) and (d.card_no like '%" + cicardno + "%')) and( ( (d.fname <> '' or 1=1 ) and (d.fname like '%" + cifname +"%')) and (d.lname <> '' or 1=1 ) and (d.lname like '%"+ cilname + "%'));";

		DefaultTableModel tableModel = new DefaultTableModel(){
			
			public boolean isCellEditable(int row, int column) {
			       return false;
			    }
		};
		
		String[] tableRow;
		tableModel.addColumn("Book Id");
		tableModel.addColumn("Branch Id");
		tableModel.addColumn("Card No");
		tableModel.addColumn("Title");
		tableModel.addColumn("Author");
		
	
		try (Connection connection = DriverManager.getConnection(url, userid,password);
				Statement stmt = connection.createStatement();
				ResultSet rs = stmt.executeQuery(sql)) {
			ResultSetMetaData md = rs.getMetaData();
			int columns = md.getColumnCount();

			// Get column names
			for (int i = 1; i <= columns; i++) {
				columnNames.add(md.getColumnName(i));
			}

			// Get row data
			while (rs.next()) {
				ArrayList row = new ArrayList(columns);
				 tableRow = new String[6];
				
				tableRow[0] = rs.getString(1);
				tableRow[1] = rs.getString(2);
				tableRow[2] = rs.getString(3);
				tableRow[3] = rs.getString(4);
				tableRow[4] = rs.getString(5);
				tableModel.addRow(tableRow);
 
				
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		return tableModel;
	

}
}



