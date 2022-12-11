package admin_view;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import db.Connect_DB;

import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.ActionEvent;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.Vector;
import java.awt.Dimension;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class UserListScreen extends JFrame {
	private static UserListScreen frame;

	private JPanel contentPane;
	private JScrollPane scrollPane;
	private static JTable table;
	private DefaultTableModel tableModel;
	
	
	private JTextField searchTextField;
	private JButton btnSearch;
	private JButton btnRefresh;
	
	Vector<Vector<Object>> data;
	String filter = "CreateTime";
	String order = "asc";

	/**
	 * Launch the application.
	 */
	public static void main() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new UserListScreen();
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
	public UserListScreen() {
		Connect_DB db = Connect_DB.getInstance();
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1000, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 100, 964, 450);
		contentPane.add(scrollPane);
		
		// Data to be displayed in the JTable
        try {
			data = db.getAllUser(filter, order);
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
//		table.setModel(new DefaultTableModel(
//			new Object[][] {
//			},
//			new String[] {
//				"Username", "Fullname", "Address", "Date of Birth", "Sex", "Email", "Date created"
//			}
//		) {
//			boolean[] columnEditables = new boolean[] {
//				false, false, false, false, false, false, false
//			};
//			public boolean isCellEditable(int row, int column) {
//				return columnEditables[column];
//			}
//		});
		table.getColumnModel().getColumn(3).setPreferredWidth(15);
		table.getColumnModel().getColumn(4).setPreferredWidth(15);
		
		JPopupMenu popupMenu = new JPopupMenu();
		popupMenu.setPopupSize(new Dimension(120, 200));
		addPopup(table, popupMenu);
		
		JMenuItem updateMenuItem = new JMenuItem("Cập nhật");
		updateMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Cập nhật");
				UpdateUserScreen.main();
			}
		});
		popupMenu.add(updateMenuItem);
		
		JMenuItem deleteMenuItem = new JMenuItem("Xóa");
		deleteMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Xóa");
				int choice = JOptionPane.showConfirmDialog(frame,
						"Bạn có chắc muốn xóa người dùng này",
						"Cảnh báo",
						JOptionPane.OK_CANCEL_OPTION,
                        JOptionPane.ERROR_MESSAGE);
				if (choice == JOptionPane.OK_OPTION) {
					System.out.println("OK");
				} else if (choice == JOptionPane.CANCEL_OPTION) {
					System.out.println("Cancel");
				}
			}
		});
		popupMenu.add(deleteMenuItem);
		
		JMenuItem banMenuItem = new JMenuItem("Khóa");
		banMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Khóa");
				System.out.println("Xóa");
				int choice = JOptionPane.showConfirmDialog(frame,
						"Bạn có chắc muốn khóa người dùng này",
						"Cảnh báo",
						JOptionPane.OK_CANCEL_OPTION,
                        JOptionPane.ERROR_MESSAGE);
				if (choice == JOptionPane.OK_OPTION) {
					System.out.println("OK");
				} else if (choice == JOptionPane.CANCEL_OPTION) {
					System.out.println("Cancel");
				}
			}
		});
		popupMenu.add(banMenuItem);
		
		JMenuItem loginHistoryMenuItem = new JMenuItem("Lịch sử đăng nhập");
		loginHistoryMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Lịch sử đăng nhập");
				frame.dispose();
				LoginHistoryScreen.main();
			}
		});
		popupMenu.add(loginHistoryMenuItem);
		
		JMenuItem friendListMenuItem = new JMenuItem("Danh sách bạn bè");
		friendListMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Danh sách bạn bè");
				frame.dispose();
				FriendListScreen.main();
			}
		});
		popupMenu.add(friendListMenuItem);
		
		JLabel headerLabel = new JLabel("DANH SÁCH NGƯỜI DÙNG");
		headerLabel.setHorizontalAlignment(SwingConstants.CENTER);
		headerLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		headerLabel.setBounds(26, 11, 948, 25);
		contentPane.add(headerLabel);
		
		JButton btnAdd = new JButton("Thêm");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Tạo cửa sổ mới để thêm
				AddUserScreen.main();
			}
		});
		btnAdd.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnAdd.setBounds(375, 50, 90, 30);
		contentPane.add(btnAdd);
		
		JComboBox<String> filterComboBox = new JComboBox<String>();
		filterComboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"Sắp xếp theo Date created", "Sắp xếp theo Username", "Sắp xếp theo Fullname"}));
		filterComboBox.setBounds(10, 50, 175, 30);
		filterComboBox.addItemListener(new ItemListener() {
		    @Override
		    public void itemStateChanged(ItemEvent e) {
		        if(e.getStateChange() == ItemEvent.SELECTED) {
		        	// Cập nhật lại modelTable
		        	String filterSelect = filterComboBox.getSelectedItem().toString();
		        	data.clear();
		        	tableModel.setRowCount(0);
		        	
		        	if (filterSelect.equals("Sắp xếp theo Date created")) {
		        		filter = "CreateTime";
		        	} else if (filterSelect.equals("Sắp xếp theo Username")) {
		        		filter = "UserName";
		        	} else if (filterSelect.equals("Sắp xếp theo Fullname")) {
		        		filter = "FullName";
		        	}
		        	
		        	try {
						data = db.getAllUser(filter, order);
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
		
		JComboBox<String> orderComboBox = new JComboBox<String>();
		orderComboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"Sắp xếp tăng dần", "Sắp xếp giảm dần"}));
		orderComboBox.setBounds(215, 50, 130, 30);
		orderComboBox.addItemListener(new ItemListener() {
		    @Override
		    public void itemStateChanged(ItemEvent e) {
		        if(e.getStateChange() == ItemEvent.SELECTED) {
		        	// Cập nhật lại modelTable
		        	// Cập nhật lại modelTable
		        	String orderSelect = orderComboBox.getSelectedItem().toString();
		        	data.clear();
		        	tableModel.setRowCount(0);
		        	
		        	if (orderSelect.equals("Sắp xếp tăng dần")) {
		        		order = "asc";
		        	} else if (orderSelect.equals("Sắp xếp giảm dần")) {
		        		order = "desc";
		        	}
		        	
		        	try {
						data = db.getAllUser(filter, order);
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
		contentPane.add(orderComboBox);
		
		btnSearch = new JButton("Tìm");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Tìm: " + searchTextField.getText());
			}
		});
		btnSearch.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnSearch.setBounds(884, 50, 90, 30);
		contentPane.add(btnSearch);
		
		searchTextField = new JTextField();
		searchTextField.setBounds(615, 50, 240, 30);
		contentPane.add(searchTextField);
		searchTextField.setColumns(10);
		
		btnRefresh = new JButton("Tải lại");
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Tải lại");
			}
		});
		btnRefresh.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnRefresh.setBounds(884, 10, 90, 30);
		contentPane.add(btnRefresh);
		
		JButton btnBack = new JButton("Quay lại");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				MenuScreen.main();
			}
		});
		btnBack.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnBack.setBounds(10, 10, 90, 30);
		contentPane.add(btnBack);
		
		JComboBox<String> searchComboBox = new JComboBox<String>();
		searchComboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"Username", "Fullname"}));
		searchComboBox.setBounds(490, 50, 100, 30);
		searchComboBox.addItemListener(new ItemListener() {
		    @Override
		    public void itemStateChanged(ItemEvent e) {
		        if(e.getStateChange() == ItemEvent.SELECTED) {
		        	// Cập nhật lại modelTable
		            System.out.println(searchComboBox.getSelectedItem());
		        }
		    }
		});
		contentPane.add(searchComboBox);
		
		// Close db when close window by X
		addWindowListener(new java.awt.event.WindowAdapter() {
		    @Override
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		    	db.close();
		    }
		});
	}
	
	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				int row = table.getSelectedRow();
				if (e.isPopupTrigger() && row != -1) {
					for (int i = 0; i < table.getColumnCount(); i++)
						System.out.println(table.getValueAt(row, i));
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
	
}
