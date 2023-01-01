package modulesforusers;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.MessageFormat;
import java.util.Vector;
import java.awt.event.ActionEvent;

public class DetailGroupChatScreen extends JFrame {

	JLayeredPane contentPane;
	
	JTextField tf;

	private JTable adminTable;
	private DefaultTableModel adminTableModel;
	
	private JTable memberTable;
	private DefaultTableModel memberTableModel;
	
	String userid = "a73080ac-501d-4a6c-8e1f-1c65335376a1";
	String groupid = "455ab4a0-2e39-1111-83b9-a28f4d2f73d7";
	String groupName = "";
	
	Vector<Vector<Object>> data_1 = new Vector<Vector<Object>>();
	Vector<Vector<Object>> data_2 = new Vector<Vector<Object>>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DetailGroupChatScreen frame = new DetailGroupChatScreen();
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
	public DetailGroupChatScreen() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(400,530);
		
		contentPane = new JLayeredPane();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLocation(0, 0);
		contentPane.setLayout(null);
		
		setContentPane(contentPane);
		
		//group image
		JButton btnNewButton = new JButton("New button");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setBounds(175, 25, 50, 50);
		contentPane.add(btnNewButton, JLayeredPane.DEFAULT_LAYER);
		
		//group name
		JLabel lbl_1 = new JLabel(groupName, SwingConstants.CENTER);
		lbl_1.setBounds(50, 85, 300, 15);
		contentPane.add(lbl_1, JLayeredPane.DEFAULT_LAYER);
		
		tf = new JTextField(15);
		tf.setBounds(125, 85, 150, 15);
		
		JButton btnConfirm = new JButton("New button");
		btnConfirm.setBounds(280, 85, 15, 15);
		
		JButton btnCancel = new JButton("New button");
		btnCancel.setBounds(300, 85, 15, 15);
		
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tf.setVisible(false);
				btnConfirm.setVisible(false);
				btnCancel.setVisible(false);
				
				if(groupName.equals(tf.getText()) == false )
					try {
						changeGroupName(groupid, tf.getText());
						groupName = getGroupName(groupid);
						lbl_1.setText(groupName);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			}
		});
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tf.setVisible(false);
				btnConfirm.setVisible(false);
				btnCancel.setVisible(false);
			}
		});
		
		
		contentPane.add(tf, JLayeredPane.PALETTE_LAYER);
		contentPane.add(btnConfirm, JLayeredPane.PALETTE_LAYER);
		contentPane.add(btnCancel, JLayeredPane.PALETTE_LAYER);
		
		tf.setVisible(false);
		btnConfirm.setVisible(false);
		btnCancel.setVisible(false);
		
		JButton btnChangeName = new JButton("Change name");
		btnChangeName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tf.setVisible(true);
				btnConfirm.setVisible(true);
				btnCancel.setVisible(true);
			}
		});
		btnChangeName.setBounds(140, 105, 120, 20);
		contentPane.add(btnChangeName, JLayeredPane.DEFAULT_LAYER);
		
		//admin
		JLabel lbl_2 = new JLabel("Admin list:");
		lbl_2.setBounds(100, 130, 100, 15);
		contentPane.add(lbl_2, JLayeredPane.DEFAULT_LAYER);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(100, 150, 200, 100);
		scrollPane_1.setViewportView(adminTable);
		String[] columnNames_1 = { "Username"};
		adminTableModel = new DefaultTableModel(columnNames_1, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
               //all cells false
               return false;
            }
        };
        
		adminTable = new JTable();
		adminTable.setModel(adminTableModel);
		
		contentPane.add(scrollPane_1, JLayeredPane.DEFAULT_LAYER);
		
		//member
		JLabel lbl_3 = new JLabel("Member list:");
		lbl_3.setBounds(100, 260, 100, 15);
		contentPane.add(lbl_3);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(100, 280, 200, 200);
		scrollPane_2.setViewportView(adminTable);
		String[] columnNames_2 = { "Username" };
		memberTableModel = new DefaultTableModel(columnNames_2, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
               //all cells false
               return false;
            }
        };
        
        memberTable = new JTable();
        memberTable.setModel(memberTableModel);
		
		contentPane.add(scrollPane_2, JLayeredPane.DEFAULT_LAYER);
		
		try {
			groupName = getGroupName(groupid);
			lbl_1.setText(groupName);
			tf.setText(groupName);
			data_1 = getGroupChatAdmin(groupid);
			data_2 = getGroupChatMember(groupid);
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
	}
	private static String getGroupName(String groupID) throws SQLException{
		// Connect DB
		Connection conn = null;
		Statement stmt = null;
		try {
			conn = DriverManager.getConnection(Main.DB_URL, Main.USER, Main.PASS);
		} catch(SQLException se){
			//Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e1) {
			// TODO: handle exception
			e1.printStackTrace();
		}
		
		stmt = conn.createStatement();
		String sql = "Select * from inbox "
				+ "where inbox.TypeInbox = 'group' "
				+ "and inbox.InboxID ='" + groupID + "' ";

		ResultSet rs = stmt.executeQuery(sql); rs.next();
		String data = rs.getString("InboxName");
		try {
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			conn.close();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		return data;
	}
	private static void changeGroupName(String groupID, String newName) throws SQLException{
		// Connect DB
		Connection conn = null;
		Statement stmt = null;
		try {
			conn = DriverManager.getConnection(Main.DB_URL, Main.USER, Main.PASS);
		} catch(SQLException se){
			//Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e1) {
			// TODO: handle exception
			e1.printStackTrace();
		}
		
		stmt = conn.createStatement();
		String sql = MessageFormat.format("update inbox "
				+ "set InboxName = \"{0}\""
				+ "where InboxID = \"{1}\"", 
				newName, groupID);

		stmt.executeUpdate(sql);

		try {
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			conn.close();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	private static Vector<Vector<Object>> getGroupChatAdmin(String groupID) throws SQLException{
		// Connect DB
		Connection conn = null;
		Statement stmt = null;
		try {
			conn = DriverManager.getConnection(Main.DB_URL, Main.USER, Main.PASS);
		} catch(SQLException se){
			//Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e1) {
			// TODO: handle exception
			e1.printStackTrace();
		}
		
		stmt = conn.createStatement();
		String sql = "select * from users "
				+ "join admingroup on users.UserID = admingroup.UserID "
				+ "join inbox on admingroup.InboxID = inbox.InboxID "
				+ "and inbox.TypeInbox = 'group' "
				+ "and inbox.InboxID ='" + groupID + "' ";
		
		ResultSet rs = stmt.executeQuery(sql);
		Vector<Vector<Object>> data = new Vector<Vector<Object>>();
		while (rs.next()) { 
			Vector<Object> row = new Vector<Object>();
			row.add(rs.getString("UserName"));
			
			data.add(row);
		}

		try {
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			conn.close();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		return data;
	}
	private static Vector<Vector<Object>> getGroupChatMember(String groupID) throws SQLException{
		// Connect DB
		Connection conn = null;
		Statement stmt = null;
		try {
			conn = DriverManager.getConnection(Main.DB_URL, Main.USER, Main.PASS);
		} catch(SQLException se){
			//Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e1) {
			// TODO: handle exception
			e1.printStackTrace();
		}
		
		stmt = conn.createStatement();
		String sql = "select * from users "
				+ "join inboxparticipants on users.UserID = inboxparticipants.UserID "
				+ "join inbox on inboxparticipants.InboxID = inbox.InboxID "
				+ "and inbox.TypeInbox = 'group' "
				+ "and inbox.InboxID ='" + groupID + "' ";
		
		ResultSet rs = stmt.executeQuery(sql);
		Vector<Vector<Object>> data = new Vector<Vector<Object>>();
		while (rs.next()) { 
			Vector<Object> row = new Vector<Object>();
			row.add(rs.getString("UserName"));
			
			data.add(row);
		}

		try {
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			conn.close();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		return data;
	}
}
