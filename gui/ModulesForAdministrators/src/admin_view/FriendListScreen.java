package admin_view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuItem;
import javax.swing.JTable;
import java.awt.Font;
import javax.swing.table.DefaultTableModel;

import db.Connect_DB;

import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.Vector;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class FriendListScreen extends JFrame {
	private static FriendListScreen frame;

	private JPanel contentPane;
	private JTable table;
	private DefaultTableModel tableModel;
	
	private JTextField searchTextField;
	private JButton btnSearch;
	
	Vector<Vector<Object>> data;
	String filter = "UserName";
	String order = "asc";
	String criteria = "Username";
	String keyword = "";

	/**
	 * Launch the application.
	 */
	public static void main() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new FriendListScreen();
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
	public FriendListScreen() {
		Connect_DB db = Connect_DB.getInstance();
		
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
			data = db.getAllUser(filter, order);
			//data = db.getUserFriendList(userid, filter, order);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		table = new JTable();
		scrollPane.setViewportView(table);
		String[] columnNames = { "Username", "Fullname", "Address", "Date of Birth", "Sex", "Email", "Date created" };
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
        
		table.getColumnModel().getColumn(3).setPreferredWidth(40);
		table.getColumnModel().getColumn(4).setPreferredWidth(20);
		table.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		table.setEnabled(false);
		
		JPopupMenu popupMenu = new JPopupMenu();
		addPopup(table, popupMenu);
		
		JMenuItem mntmNewMenuItem_7 = new JMenuItem("New menu item");
		popupMenu.add(mntmNewMenuItem_7);
		
		JMenuItem mntmNewMenuItem_12 = new JMenuItem("New menu item");
		popupMenu.add(mntmNewMenuItem_12);
		
		JComboBox<String> filterComboBox = new JComboBox<String>();
		filterComboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"By Username", "By Fullname"}));
		filterComboBox.setFont(new Font("Tahoma", Font.PLAIN, 16));
		filterComboBox.setBounds(115, 60, 176, 30);
		filterComboBox.addItemListener(new ItemListener() {
		    @Override
		    public void itemStateChanged(ItemEvent e) {
		        if(e.getStateChange() == ItemEvent.SELECTED) {
		        	// Cập nhật lại modelTable
		        	String filterSelect = filterComboBox.getSelectedItem().toString();
		        	data.clear();
		        	tableModel.setRowCount(0);
		        	
		        	if (filterSelect.equals("By Username")) {
		        		filter = "UserName";
		        	} else if (filterSelect.equals("By Fullname")) {
		        		filter = "FullName";
		        	}
		        	
		        	try {
						data = db.searchUser(keyword, criteria, filter, order);
						//data = db.getUserFriendList(userid, filter, order);
						
			            for (int i = 0; i < data.size(); i++) {
			            	tableModel.addRow(data.get(i));
			            }
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
		        }
		    }
		});
		contentPane.add(filterComboBox);
		
		searchTextField = new JTextField();
		searchTextField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		searchTextField.setColumns(10);
		searchTextField.setBounds(301, 60, 447, 30);
		contentPane.add(searchTextField);
		
		btnSearch = new JButton("Tìm");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
	        	data.clear();
	        	tableModel.setRowCount(0);
	        	keyword = searchTextField.getText();
	        	
	        	try {
					data = db.searchUser(keyword, criteria, filter, order);
					//data = db.searchUserInUserFriendList(userid, criteria, keyword, filter, order);
		            for (int i = 0; i < data.size(); i++) {
		            	tableModel.addRow(data.get(i));
		            }
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnSearch.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnSearch.setBounds(758, 60, 123, 30);
		contentPane.add(btnSearch);
		
		JButton btnQuayLi = new JButton("Quay lại");
		btnQuayLi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				UserListScreen.main();
			}
		});
		btnQuayLi.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnQuayLi.setBounds(10, 10, 115, 37);
		contentPane.add(btnQuayLi);
		
		
	}
	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}
