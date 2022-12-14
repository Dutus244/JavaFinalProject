package modulesforusers;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import javax.swing.JScrollPane;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.MessageFormat;
import java.util.Vector;
import java.awt.event.ActionEvent;

public class DetailGroupChatScreen extends JFrame {
	private static DetailGroupChatScreen frame;
	
	static boolean isWait = true;
	
	int width = 400;
	int height = 530;
	
	JLayeredPane contentPane;
	
	JTextField tf;

	private JTable adminTable;
	private DefaultTableModel adminTableModel;
	
	private JTable memberTable;
	private DefaultTableModel memberTableModel;
	
	JPanel addMemberPane;
	
	private JTable addMemberTable;
	private DefaultTableModel addMemberTableModel;
	
	private static Connection conn = null;
	private static Statement stmt = null;
	
	String userid = "";
	String username = "";
	String groupid = "";
	static String groupname = "";
	boolean isadmin = false;
	
	Vector<Vector<Object>> data_1 = new Vector<Vector<Object>>();
	Vector<Vector<Object>> data_2 = new Vector<Vector<Object>>();
	Vector<Vector<Object>> data_3 = new Vector<Vector<Object>>();

	/**
	 * Launch the application.
	 */
	public static void main(String un) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					isWait = true;
					frame = new DetailGroupChatScreen(un);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
//		isWait = true;
//		frame = new DetailGroupChatScreen(gn, un);
//		frame.setVisible(true);
	}

	public static String getCurrentGroupName() {
		return groupname;
	}
	
	public static boolean iswait() {
		return isWait;
	}
	
	public DetailGroupChatScreen(String un) {
		connectDB();
		
		setSize(width, height);
		
		contentPane = new JLayeredPane();
		//contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLocation(0, 0);
		contentPane.setLayout(null);
		
		setContentPane(contentPane);
		
		Image image;
		Image newimg;
		
		//group image
		Icon iconGroup = new ImageIcon("source/image/createGroup.png");
		image = ((ImageIcon) iconGroup).getImage(); // transform it 
	    newimg = image.getScaledInstance(50, 50,  java.awt.Image.SCALE_SMOOTH);
	    iconGroup = new ImageIcon(newimg);
		JButton btnNewButton = new JButton(iconGroup);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setBounds(175, 25, 50, 50);
		contentPane.add(btnNewButton, JLayeredPane.DEFAULT_LAYER);
		
		//group name
		JLabel lbl_1 = new JLabel(groupname, SwingConstants.CENTER);
		lbl_1.setBounds(50, 85, 300, 15);
		contentPane.add(lbl_1, JLayeredPane.DEFAULT_LAYER);
		
		tf = new JTextField(15);
		tf.setBounds(125, 85, 150, 15);
		
		Icon iconConfirm = new ImageIcon("source/image/check.png");
		image = ((ImageIcon) iconConfirm).getImage(); // transform it 
	    newimg = image.getScaledInstance(15, 15,  java.awt.Image.SCALE_SMOOTH);
	    iconConfirm = new ImageIcon(newimg);
		JButton btnConfirm = new JButton(iconConfirm);
		btnConfirm.setBounds(280, 85, 15, 15);
		btnConfirm.setOpaque(false);
		btnConfirm.setContentAreaFilled(false);
		
		Icon iconCancel = new ImageIcon("source/image/x.png");
		image = ((ImageIcon) iconCancel).getImage(); // transform it 
	    newimg = image.getScaledInstance(15, 15,  java.awt.Image.SCALE_SMOOTH);
	    iconCancel = new ImageIcon(newimg);
		JButton btnCancel = new JButton(iconCancel);
		btnCancel.setBounds(300, 85, 15, 15);
		
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tf.setVisible(false);
				btnConfirm.setVisible(false);
				btnCancel.setVisible(false);
				
				if(groupname.equals(tf.getText()) == false )
					try {
						changeGroupName(groupid, tf.getText());
						groupname = getGroupName(groupid);
						HomeScreen.inboxCurrentlyOpen = groupname;
						setTitle(groupname); 
						lbl_1.setText(groupname);
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
		
		//admin table
		JLabel lbl_2 = new JLabel("Admin list:");
		lbl_2.setBounds(100, 130, 100, 15);
		contentPane.add(lbl_2, JLayeredPane.DEFAULT_LAYER);
		
		adminTable = new JTable();
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
        
		adminTable.setModel(adminTableModel);
		
		contentPane.add(scrollPane_1, JLayeredPane.DEFAULT_LAYER);
		
		//member table
		JLabel lbl_3 = new JLabel("Member list:");
		lbl_3.setBounds(100, 260, 100, 15);
		contentPane.add(lbl_3, JLayeredPane.DEFAULT_LAYER);
		
        memberTable = new JTable();
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(100, 280, 200, 200);
		scrollPane_2.setViewportView(memberTable);
		String[] columnNames_2 = { "Username" };
		memberTableModel = new DefaultTableModel(columnNames_2, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
               //all cells false
               return false;
            }
        };
        
        memberTable.setModel(memberTableModel);
		
		contentPane.add(scrollPane_2, JLayeredPane.DEFAULT_LAYER);
		
		Icon iconPopup = new ImageIcon("source/image/plus.png");
		image = ((ImageIcon) iconPopup).getImage(); // transform it 
	    newimg = image.getScaledInstance(15, 15,  java.awt.Image.SCALE_SMOOTH);
	    iconPopup = new ImageIcon(newimg);
		JButton btnPopupAddmember = new JButton(iconPopup);
		btnPopupAddmember.setBounds(285, 260, 15, 15);
		
		btnPopupAddmember.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addMemberPane.setVisible(true);
	            
	            try {
					data_3 = getAddList(groupid);
					for (int i = 0; i < data_3.size(); i++) {
		            	addMemberTableModel.addRow(data_3.get(i));
		            }
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		contentPane.add(btnPopupAddmember, JLayeredPane.DEFAULT_LAYER);
		
		//popup menu
		JPopupMenu popupMenu = new JPopupMenu();
		popupMenu.setPopupSize(new Dimension(200, 25));
		addPopup(memberTable, popupMenu);
		
//		JMenuItem sendFRMenuItem = new JMenuItem("Send friend request");
//		sendFRMenuItem.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				
//			}
//		});
//		popupMenu.add(sendFRMenuItem);
		
		JMenuItem removeMenuItem = new JMenuItem("Remove from group chat");
		removeMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = memberTable.getSelectedRow();
				String user = memberTable.getValueAt(row, 0).toString();
				String targetUserID;
				Boolean isTargetAdmin = false;
				try {
					targetUserID = getUserID(user);
					isTargetAdmin = isAdmin(groupid, targetUserID);
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				if(isadmin == false || isTargetAdmin == true ) {
					JOptionPane.showMessageDialog(frame,
		                "You don't have permission to remove this user",
		                "Message",
		                JOptionPane.INFORMATION_MESSAGE);
				}
				else {
					try {
						removeGroupChatMember(groupid, user);
						
						data_2.clear();
						memberTableModel.setRowCount(0);
						data_2 = getGroupChatMember(groupid, username);
						for (int i = 0; i < data_2.size(); i++) {
			            	memberTableModel.addRow(data_2.get(i));
			            }
						
						JOptionPane.showMessageDialog(frame,
				                "Remove successfully",
				                "Message",
				                JOptionPane.INFORMATION_MESSAGE);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		popupMenu.add(removeMenuItem);
		
		//add member pane
		
		addMemberPane = new JPanel();
		addMemberPane.setSize(366, 375);
		addMemberPane.setLocation(10, 105);
		addMemberPane.setBorder(BorderFactory.createLineBorder(Color.black));
		
		addMemberPane.setLayout(null);
		
		contentPane.add(addMemberPane, JLayeredPane.PALETTE_LAYER);
		
		JLabel lbl_4 = new JLabel("Choose person you want to add", SwingConstants.CENTER);
		lbl_4.setBounds(80, 30, 200, 15);
		addMemberPane.add(lbl_4);

		//add member table
		addMemberTable = new JTable();
		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(80, 65, 200, 250);
		scrollPane_3.setViewportView(addMemberTable);
		String[] columnNames_3 = { "Username" };
		addMemberTableModel = new DefaultTableModel(columnNames_3, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
               //all cells false
               return false;
            }
        };
        addMemberTable.setModel(addMemberTableModel);
        
        addMemberPane.add(scrollPane_3);
        
		JButton btnAddMember = new JButton("Add to group");
		btnAddMember.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//add to group
				int row = addMemberTable.getSelectedRow();
				String user = addMemberTable.getValueAt(row, 0).toString();
				if(row != -1) { 
					try {
						addMemberGroupChat(groupid, user);
					} catch (SQLException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					
					//refresh table 3
					data_3.clear();
					addMemberTableModel.setRowCount(0);
					try {
						data_3 = getAddList(groupid);
						for (int i = 0; i < data_3.size(); i++) {
			            	addMemberTableModel.addRow(data_3.get(i));
			            }
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		btnAddMember.setBounds(80, 345, 110, 20);
		addMemberPane.add(btnAddMember);
		
		JButton btnCancelAdd = new JButton("Cancel");
		btnCancelAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//clear table 3
				data_3.clear();
				addMemberTableModel.setRowCount(0);
				//set visible false 
				addMemberPane.setVisible(false);
				
				try {
					data_2.clear();
					memberTableModel.setRowCount(0);
					data_2 = getGroupChatMember(groupid, username);
					for (int i = 0; i < data_2.size(); i++) {
		            	memberTableModel.addRow(data_2.get(i));
		            }
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnCancelAdd.setBounds(200, 345, 80, 20);
		addMemberPane.add(btnCancelAdd);
		
		
		addMemberPane.setVisible(false);
		
		//refresh
		try {
			username = un;
			groupname = HomeScreen.inboxCurrentlyOpen;
			
			userid = getUserID(username);
			groupid = getGroupID(groupname);
			
			setTitle(groupname); 
			lbl_1.setText(groupname);
			tf.setText(groupname);
			
			isadmin = isAdmin(groupid, userid);
			
			data_1 = getGroupChatAdmin(groupid);
			data_2 = getGroupChatMember(groupid, username);
			for (int i = 0; i < data_1.size(); i++) {
				adminTableModel.addRow(data_1.get(i));
            }
            for (int i = 0; i < data_2.size(); i++) {
            	memberTableModel.addRow(data_2.get(i));
            }
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
	}

	private void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				int row = memberTable.getSelectedRow();
				String user = "";
				if(row != -1) {
					user = memberTable.getValueAt(row, 0).toString();
					if(user.indexOf(" (You)") != -1)
						user = user.substring(0, user.length() - 6);
				}	
				if (e.isPopupTrigger() && row != -1 && user.equals("") == false && user.equals(username) == false) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
	private static void connectDB() {
		try {
			conn = DriverManager.getConnection(Main.DB_URL, Main.USER, Main.PASS);
		} catch(SQLException se){
			//Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e1) {
			// TODO: handle exception
			e1.printStackTrace();
		}
	}
	private static String getUserName(String userID) throws SQLException{		
		stmt = conn.createStatement();
		String sql = "Select * from users "
				+ "where userID ='" + userID + "' ";

		ResultSet rs = stmt.executeQuery(sql); rs.next();
		String data = rs.getString("UserName");
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
		
		return data;
	}
	private static String getUserID(String userName) throws SQLException{		
		stmt = conn.createStatement();
		String sql = "Select * from users "
				+ "where userName ='" + userName + "' ";

		ResultSet rs = stmt.executeQuery(sql); rs.next();
		String data = rs.getString("UserID");
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
		
		return data;
	}
	private static String getGroupName(String groupID) throws SQLException{		
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
		
		return data;
	}
	private static String getGroupID(String groupName) throws SQLException{		
		stmt = conn.createStatement();
		String sql = "Select * from inbox "
				+ "where inbox.TypeInbox = 'group' "
				+ "and inbox.InboxName ='" + groupName + "' ";

		ResultSet rs = stmt.executeQuery(sql); rs.next();
		String data = rs.getString("InboxID");
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
		
		return data;
	}
	private static void changeGroupName(String groupID, String newName) throws SQLException{
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
	}
	private static Vector<Vector<Object>> getGroupChatAdmin(String groupID) throws SQLException{
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
		
		return data;
	}
	private static Vector<Vector<Object>> getGroupChatMember(String groupID, String userName) throws SQLException{
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
			if(userName.equals(rs.getString("UserName")) == true)
				row.add(rs.getString("UserName") + " (You)");
			else
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
		
		return data;
	}
	private static void addMemberGroupChat(String groupID, String user) throws SQLException{
		user = getUserID(user);
		stmt = conn.createStatement();
		String sql = MessageFormat.format("insert into inboxparticipants values "
				+ "(\"{0}\", \"{1}\")", 
				groupID, user);
		System.out.println(sql);
		stmt.executeUpdate(sql);

		try {
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private static Vector<Vector<Object>> getAddList(String groupID) throws SQLException{
		stmt = conn.createStatement();
		String sql = "select * from users u1 \r\n"
				+ "where u1.UserID not in \r\n"
				+ "(select u2.UserID from users u2 \r\n"
				+ "join inboxparticipants on u2.UserID = inboxparticipants.UserID \r\n"
				+ "join inbox on inboxparticipants.InboxID = inbox.InboxID \r\n"
				+ "and inbox.TypeInbox = 'group' \r\n"
				+ "and inbox.InboxID ='" + groupID + "') ";
		System.out.println(sql);
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
		
		return data;
	}
	private static boolean isAdmin(String groupID, String userID) throws SQLException {
		stmt = conn.createStatement();
		String sql = "select count(UserID) as nb from admingroup "
				+ "where InboxID = '"+ groupID +"' "
				+ "and UserID = '" + userID + "'";

		ResultSet rs = stmt.executeQuery(sql); rs.next();
		int data = rs.getInt("nb");
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
		return data > 0 ? true : false;
	}
	private static void removeGroupChatMember(String groupID, String user) throws SQLException {
		stmt = conn.createStatement();
		String sql = MessageFormat.format("delete from inboxparticipants "
				+ "where InboxID = \"{0}\"" 
				+ "and UserID = all(select UserID from users "
									+ "where UserName = \"{1}\")", 
				groupID, user);
		System.out.println(sql);
		stmt.executeUpdate(sql);

		try {
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
