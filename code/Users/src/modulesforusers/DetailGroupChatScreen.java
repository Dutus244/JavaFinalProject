package modulesforusers;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.awt.event.ActionEvent;

public class DetailGroupChatScreen extends JFrame {

	JPanel contentPane;

	private JTable adminTable;
	private DefaultTableModel adminTableModel;
	
	private JTable memberTable;
	private DefaultTableModel memberTableModel;
	
	String groupid = "";
	
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
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLocation(0, 0);
		contentPane.setLayout(null);
		
		setContentPane(contentPane);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setBounds(175, 25, 50, 50);
		contentPane.add(btnNewButton);
		
		JLabel lbl_1 = new JLabel("New labelllllllllllllllllllllll", SwingConstants.CENTER);
		lbl_1.setBounds(50, 85, 300, 15);
		contentPane.add(lbl_1);
		
		JLabel lbl_2 = new JLabel("Admin list:");
		lbl_2.setBounds(100, 120, 100, 15);
		contentPane.add(lbl_2);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(100, 140, 200, 100);
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
		
		contentPane.add(scrollPane_1);
		
		JLabel lbl_3 = new JLabel("Member list:");
		lbl_3.setBounds(100, 250, 100, 15);
		contentPane.add(lbl_3);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(100, 270, 200, 200);
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
		
		contentPane.add(scrollPane_2);
	}
	private static Object getGroupName(String groupID) throws SQLException{
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
		String sql = "";

		ResultSet rs = stmt.executeQuery(sql);

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
		
		return rs.getString("?????");
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
		String sql = "";

		ResultSet rs = stmt.executeQuery(sql);

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
	private static Vector<Vector<Object>> getAdminGroup(String groupID) throws SQLException{
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
		String sql = "";

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
	private static Vector<Vector<Object>> getMemberGroup(String groupID) throws SQLException{
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
		String sql = "";

		ResultSet rs = stmt.executeQuery(sql);
		Vector<Vector<Object>> data = new Vector<Vector<Object>>();
		while (rs.next()) { 
			Vector<Object> row = new Vector<Object>();
			row.add(rs.getString("UserName"));
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
