package admin_view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import java.awt.Font;
import javax.swing.table.DefaultTableModel;

import db.Connect_DB;

import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Vector;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class LoginHistoryScreen extends JFrame {
	private String userName = "";
	
	private static LoginHistoryScreen frame;

	private JPanel contentPane;
	private JTable table;
	private DefaultTableModel tableModel;
	
	private JButton btnRefresh;

	Vector<Vector<Object>> data;
	String filter = "LoginTime";
	String order = "asc";
	/**
	 * Launch the application.
	 */
	public static void main(String user) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new LoginHistoryScreen(user);
					frame.setLocationRelativeTo(null);
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
	public LoginHistoryScreen(String user) {
		Connect_DB db = Connect_DB.getInstance();
		userName = user;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(100, 100, 800, 400);
		contentPane.add(scrollPane);
		
		try {
			data = db.getLoginHistory(userName, filter, order);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		table = new JTable();
		scrollPane.setViewportView(table);
		String[] columnNames = { "Date", "Time" };
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
               //all cells false
               return false;
            }
        };
        for (int i = 0; i < data.size(); i++) {
        	tableModel.addRow(data.get(i));
        }
        table.setModel(tableModel);
        
		table.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		table.setEnabled(false);
		
		JButton btnQuayLi = new JButton("Quay lại");
		btnQuayLi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserListScreen.main();
				frame.dispose();
			}
		});
		btnQuayLi.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnQuayLi.setBounds(10, 10, 115, 37);
		contentPane.add(btnQuayLi);
		
		btnRefresh = new JButton("Làm mới");
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
	        	data.clear();
	        	tableModel.setRowCount(0);
	        	//get order
	        	
	        	try {
	    			data = db.getLoginHistory(userName, filter, order);
		            for (int i = 0; i < data.size(); i++) {
		            	tableModel.addRow(data.get(i));
		            }
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnRefresh.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnRefresh.setBounds(785, 53, 115, 37);
		contentPane.add(btnRefresh);
		
		// Close db when close window by X
		addWindowListener(new java.awt.event.WindowAdapter() {
		    @Override
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		    	db.close();
		    }
		});		
	}
}
