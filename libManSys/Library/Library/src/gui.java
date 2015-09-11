/*
 * Database Design Project
 * Shubham Rai
 * Net id: scr130130
 * 
 */

//This class contains the whole interface.
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JScrollPane;
import javax.swing.JTable;
//import javax.swing.JOptionPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JRadioButton;


@SuppressWarnings("serial")
public class gui extends JFrame {

	public JPanel contentPane;
	public JPanel panel;
	public static JTextField bookid;
	public static JTextField title;
	public static JTextField fullname;
	public static JTextField firstname;
	public static JTextField mi;
	public static JTextField lastname;
	public static JTable table;
	//public DefaultTableModel mytable;
	public static JTextField cobkid;
	public static JTextField cobrid;
	public static JTextField cocn;
	public static JTextField fname;
	public static JTextField lname;
	public static JTextField address;
	public static JTextField phone;
	public static JTextField cibkid;
	public static JTextField cicardno;
	public static JTextField cifname;
	public static JTextField cilname;
	public static JTable table_1;
	public JRadioButton rdbtnPerson;
	public JRadioButton rdbtnOrganization;
	// final ResultSetTableModel model = new ResultSetTableModel();
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					gui frame = new gui();
					frame.setVisible(true);
					
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public gui() {
					
		super("Library Management System");
		
		
		
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 622, 481);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 11, 561, 420);
		contentPane.add(tabbedPane);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Search", null, panel, null);
		panel.setLayout(null);
		
		JLabel lblBookId = new JLabel("Book ID");
		lblBookId.setHorizontalAlignment(SwingConstants.RIGHT);
		lblBookId.setBounds(28, 11, 46, 14);
		panel.add(lblBookId);
		
		bookid = new JTextField();
		bookid.setBounds(84, 8, 142, 20);
		panel.add(bookid);
		bookid.setColumns(10);
		
		JLabel lblTitle = new JLabel("Title");
		lblTitle.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTitle.setBounds(245, 11, 46, 14);
		panel.add(lblTitle);
		
		title = new JTextField();
		title.setBounds(301, 8, 162, 20);
		panel.add(title);
		title.setColumns(10);
		
		JLabel lblAuthorDetails = new JLabel("Search Author by: ");
		lblAuthorDetails.setHorizontalAlignment(SwingConstants.LEFT);
		lblAuthorDetails.setBounds(28, 39, 108, 14);
		panel.add(lblAuthorDetails);
		
		JLabel lblFullName = new JLabel("Full Name");
		lblFullName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblFullName.setBounds(10, 64, 64, 14);
		panel.add(lblFullName);
		
		fullname = new JTextField();
		fullname.setBounds(84, 61, 169, 20);
		panel.add(fullname);
		fullname.setColumns(10);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(20, 99, 472, 2);
		panel.add(separator);
		
		JLabel lblFirstName = new JLabel("First Name");
		lblFirstName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblFirstName.setBounds(10, 118, 67, 14);
		panel.add(lblFirstName);
		
		firstname = new JTextField();
		firstname.setBounds(84, 115, 86, 20);
		panel.add(firstname);
		firstname.setColumns(10);
		
		JLabel lblMi = new JLabel("M.I");
		lblMi.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMi.setBounds(183, 118, 33, 14);
		panel.add(lblMi);
		
		mi = new JTextField();
		mi.setBounds(226, 115, 33, 20);
		panel.add(mi);
		mi.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Last Name");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setBounds(272, 118, 67, 14);
		panel.add(lblNewLabel);
		
		lastname = new JTextField();
		lastname.setBounds(349, 115, 114, 20);
		panel.add(lastname);
		lastname.setColumns(10);
		
		JButton btnNewButton = new JButton("Search");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String bookid = gui.bookid.getText();
				String title = gui.title.getText();
				String fullname = gui.fullname.getText();
				String firstname = gui.firstname.getText();
				String mi = gui.mi.getText();
				String lastname = gui.lastname.getText();
				
				TableModel resultTable = ResultSetTable.returnSearchData(bookid,title,fullname,firstname,mi,lastname);
				
				table.setModel(resultTable);				
											
//				bookSearch frame = new bookSearch();
//				frame.pack();
//				frame.setVisible(true);
//				  
				
			}
		});
		btnNewButton.setBounds(84, 163, 89, 23);
		panel.add(btnNewButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 197, 536, 184);
		panel.add(scrollPane);
		
		table =new JTable();
		//table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		

		scrollPane.setColumnHeaderView(table);
		scrollPane.setViewportView(table);

	
		JButton btnNewButton_1 = new JButton("Reset");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 bookid.setText("");
				 title.setText("");
				 fullname.setText("");
				 firstname.setText("");
				 mi.setText("");
				 lastname.setText("");
				
				
				
			}
		});
		btnNewButton_1.setBounds(374, 161, 89, 23);
		panel.add(btnNewButton_1);
		
		JRadioButton Person = new JRadioButton("Person");
		Person.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				firstname.setEnabled(true);
				mi.setEnabled(true);
				lastname.setEnabled(true);
				
			}
		});
		Person.setBounds(144, 35, 109, 23);
		panel.add(Person);
		
		JRadioButton Organization = new JRadioButton("Organization");
		Organization.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				firstname.setEnabled(false);
				mi.setEnabled(false);
				lastname.setEnabled(false);
				
				
			}
		});
		Organization.setBounds(272, 32, 109, 23);
		panel.add(Organization);
		
		ButtonGroup group = new ButtonGroup();
		group.add(Person);
		group.add(Organization);
		
		
		
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Check Out", null, panel_1, null);
		panel_1.setLayout(null);
		
		JLabel lblbookId = new JLabel("Book ID");
		lblbookId.setHorizontalAlignment(SwingConstants.RIGHT);
		lblbookId.setBounds(10, 32, 61, 14);
		panel_1.add(lblbookId);
		
		cobkid = new JTextField();
		cobkid.setBounds(93, 29, 101, 20);
		panel_1.add(cobkid);
		cobkid.setColumns(10);
		
		JLabel lblBranchId = new JLabel("Branch ID");
		lblBranchId.setHorizontalAlignment(SwingConstants.RIGHT);
		lblBranchId.setBounds(0, 84, 76, 14);
		panel_1.add(lblBranchId);
		
		cobrid = new JTextField();
		cobrid.setBounds(93, 81, 101, 20);
		panel_1.add(cobrid);
		cobrid.setColumns(10);
		
		JLabel lblCardNo = new JLabel("Card No");
		lblCardNo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCardNo.setBounds(26, 141, 46, 14);
		panel_1.add(lblCardNo);
		
		cocn = new JTextField();
		cocn.setBounds(93, 138, 101, 20);
		panel_1.add(cocn);
		cocn.setColumns(10);
		
		JButton btnCheckOut = new JButton("Check Out");
		btnCheckOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				@SuppressWarnings("unused")
				checkout co = new checkout();
			}
		});
		btnCheckOut.setBounds(91, 197, 103, 23);
		panel_1.add(btnCheckOut);
		
		JButton btnReset_2 = new JButton("Reset");
		btnReset_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cobrid.setText("");
				cobrid.setText("");
				cocn.setText("");
				
			}
		});
		btnReset_2.setBounds(251, 197, 89, 23);
		panel_1.add(btnReset_2);
		
		JPanel panel_3 = new JPanel();
		tabbedPane.addTab("Check In", null, panel_3, null);
		panel_3.setLayout(null);
		
		JLabel lblbookid = new JLabel("Book ID");
		lblbookid.setHorizontalAlignment(SwingConstants.RIGHT);
		lblbookid.setBounds(10, 25, 61, 14);
		panel_3.add(lblbookid);
		
		cibkid = new JTextField();
		cibkid.setColumns(10);
		cibkid.setBounds(81, 22, 86, 20);
		panel_3.add(cibkid);
		
		JLabel lblCardNo_1 = new JLabel("Card No");
		lblCardNo_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCardNo_1.setBounds(276, 25, 61, 14);
		panel_3.add(lblCardNo_1);
		
		cicardno = new JTextField();
		cicardno.setColumns(10);
		cicardno.setBounds(357, 22, 86, 20);
		panel_3.add(cicardno);
		
		JLabel lblcifname = new JLabel("First Name");
		lblcifname.setHorizontalAlignment(SwingConstants.RIGHT);
		lblcifname.setBounds(0, 71, 73, 14);
		panel_3.add(lblcifname);
		
		cifname = new JTextField();
		cifname.setColumns(10);
		cifname.setBounds(81, 68, 86, 20);
		panel_3.add(cifname);
		
		JLabel lblcilname = new JLabel("Last Name");
		lblcilname.setHorizontalAlignment(SwingConstants.RIGHT);
		lblcilname.setBounds(261, 71, 76, 14);
		panel_3.add(lblcilname);
		
		cilname = new JTextField();
		cilname.setColumns(10);
		cilname.setBounds(357, 68, 86, 20);
		panel_3.add(cilname);
		
		JButton button = new JButton("Search");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				/*
				find.pack();
				find.setVisible(true);
				*/
				if (cibkid.equals("") || cicardno.equals("")|| cifname.equals("")|| cilname.equals("")) {

					JOptionPane.showMessageDialog(null,"Please enter any  field! \n Book id,Card no, First Name,Last Name");

				}else
				{
			
				
				String cibkid = gui.cibkid.getText();
		        String cicardno = gui.cicardno.getText();
	         	String cifname = gui.cifname.getText();
		        String cilname = gui.cilname.getText();
		
				
				TableModel rst = ResultSetTable.checkinSearch(cibkid,cicardno,cifname,cilname);
				
				table_1.setModel(rst);	
				
				
				}
				
				
			}
		});
		button.setBounds(95, 116, 89, 23);
		panel_3.add(button);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		
		scrollPane_1.setBounds(10, 159, 536, 166);
		panel_3.add(scrollPane_1);
		
		table_1 = new JTable();
		table_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) 
			{
				
			}
		});
		
		//table_1.addMouseListener(null);
		
		scrollPane_1.setColumnHeaderView(table_1);
		scrollPane_1.setViewportView(table_1);
		
	
		JButton btnCheckIn = new JButton("Check In");
		btnCheckIn.addActionListener(new ActionListener() {
			@SuppressWarnings("unused")
			public void actionPerformed(ActionEvent arg0) {
											
				//checkIn ck = new checkIn();
				
				String url = "jdbc:mysql://localhost:3306/library";
				String userid = "root";
				String password = "";
				
				//gui g = new gui();
				
				int selRow = table_1.getSelectedRow();
				//int selCol = table_1.getSelectedColumn();
				if (cibkid.equals("") || cicardno.equals("")|| cifname.equals("")|| cilname.equals("")) {

					JOptionPane.showMessageDialog(null,"Please enter any  field! \n Book id,Card no, First Name,Last Name");

				}
					
				
				
				if (selRow != -1) 
				{
					String bkid = (String) table_1.getValueAt(selRow, 0);
					String brid = (String) table_1.getValueAt(selRow, 1);
					String cdno = (String) table_1.getValueAt(selRow, 2);
				
					String sql = "UPDATE book_loans SET date_in=CURDATE() WHERE book_id LIKE '%"+bkid+"%' AND branch_id LIKE '%"+ brid +"%' AND card_no LIKE '%"+cdno+"%';";
				 
				try {
					    Connection connection = DriverManager.getConnection(url, userid,password);
						Statement stmt = connection.createStatement();
						int rd = stmt.executeUpdate(sql); 
						System.out.println("Book Returned");
						JOptionPane.showMessageDialog(null,"Book Returned","Update",JOptionPane.INFORMATION_MESSAGE);
					
					
				
						((DefaultTableModel) table_1.getModel()).removeRow(selRow);
					
				} catch (SQLException e) {
					System.out.println(e.getMessage());
				}

				
				}
				else
				{
					JOptionPane.showMessageDialog(null,"Select a row from Table","Error",JOptionPane.ERROR_MESSAGE);
					
				}
				
			}
				
				
			
		});
		btnCheckIn.setBounds(95, 344, 89, 23);
		panel_3.add(btnCheckIn);
		
		JButton btnReset_1 = new JButton("Reset");
		btnReset_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
			cibkid.setText("");
			cicardno.setText("");
			cifname.setText("");
			cilname.setText("");
			
			
			}
		});
		btnReset_1.setBounds(344, 116, 89, 23);
		panel_3.add(btnReset_1);
		
		JLabel lblNewLabel_2 = new JLabel("Borrowers Details");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(172, 46, 110, 14);
		panel_3.add(lblNewLabel_2);
		
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("Add User", null, panel_2, null);
		panel_2.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("First Name");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setBounds(10, 33, 69, 14);
		panel_2.add(lblNewLabel_1);
		
		fname = new JTextField();
		fname.setBounds(89, 30, 103, 20);
		panel_2.add(fname);
		fname.setColumns(10);
		
		JLabel lblLastName_1 = new JLabel("Last Name");
		lblLastName_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblLastName_1.setBounds(195, 33, 69, 14);
		panel_2.add(lblLastName_1);
		
		lname = new JTextField();
		lname.setBounds(274, 30, 103, 20);
		panel_2.add(lname);
		lname.setColumns(10);
		
		JLabel lblAddress = new JLabel("Address");
		lblAddress.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAddress.setBounds(21, 72, 58, 14);
		panel_2.add(lblAddress);
		
		address = new JTextField();
		address.setBounds(89, 69, 288, 20);
		panel_2.add(address);
		address.setColumns(10);
		
		JLabel lblPhone = new JLabel("Phone ");
		lblPhone.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPhone.setBounds(33, 114, 46, 14);
		panel_2.add(lblPhone);
		
		final JLabel lblmessage = new JLabel("");
		lblmessage.setBounds(89, 245, 288, 14);
		panel_2.add(lblmessage);
		
		phone = new JTextField();
		phone.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				
				char c = arg0.getKeyChar();
				if (!(Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || c == KeyEvent.VK_DELETE)) {
					getToolkit().beep();
					arg0.consume();
					lblmessage.setText("Enter Digits Only! ");
				
				}
			}
		});
		phone.setBounds(89, 111, 103, 20);
		panel_2.add(phone);
		phone.setColumns(10);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if ((!fname.getText().equalsIgnoreCase(""))
						&& !(lname.getText().equalsIgnoreCase(""))
						&& !(address.getText().equalsIgnoreCase(""))
						&& !(phone.getText().equalsIgnoreCase(""))
						)
				{
					@SuppressWarnings("unused")
					add user = new add();
				}
				
				else	
					{
					JOptionPane.showMessageDialog(null,"Please enter all the mandatory fields! \n First Name,Last Name,Address");
					
					}
				
				
			}
		});
		btnAdd.setBounds(89, 167, 103, 23);
		panel_2.add(btnAdd);
		
		JButton btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 fname.setText("");
				 lname.setText("");
				 address.setText("");
				 phone.setText("");
				 lblmessage.setText("");

				
			}
		});
		btnReset.setBounds(274, 167, 103, 23);
		panel_2.add(btnReset);
		
		
	}
}


