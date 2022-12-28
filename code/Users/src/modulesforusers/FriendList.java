package modulesforusers;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.JButton;
import javax.swing.Icon;
import javax.swing.JLabel;
import com.jgoodies.forms.factories.DefaultComponentFactory;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.border.LineBorder;
import javax.swing.ImageIcon;

public class FriendList extends JFrame implements ActionListener {
	JPanel panel_6;
	JLabel lblNewJgoodiesLabel_numberFriend;
	private JPanel contentPane;
	private JTextField textField;
//	boolean ThreadContinue = true;
	JPanel panelList;
	JButton buttonChatMenu;
	JButton buttonFriendMenu;
	JButton buttonSettingMenu;
	JButton buttonUserMenu;
	JButton btnFriendRequest;
	JButton btnFriendList;
	JButton btnGroup;
	String username;
	Socket client;
	JPanel panel_10;
	List<String> usernameList = new ArrayList<>();
	public FriendList(String Username, Socket s) {
		this.username = Username;
		this.client = s;
		
		initialize(Username);
		new ReadFriendList().start();
	}
//	class ReadDatabase extends Thread{
//		public void run() {
//			while(ThreadContinue) {
//				
//			}
//		} 
//	}
	Connection conn = null;
	java.sql.Statement stmt;
	ResultSet rs;
	Connection connd = null;
	java.sql.Statement stmtd;
	ResultSet rsd;
	boolean isOpen = true;
	class ReadFriendList extends Thread{
		public void run() {
			while(isOpen) {
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					conn = DriverManager.getConnection(Main.DB_URL, Main.USER, Main.PASS);
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				try {
					stmt = conn.createStatement();
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				

				try {
					
					usernameList.clear();
					rs = ((java.sql.Statement)stmt).executeQuery("SELECT UserName FROM users where UserID in (SELECT FriendID from friendlist where UserID in (Select UserID FROM users where UserName = '"+ username +"'))");
				
					while(rs.next()) {
						usernameList.add(rs.getString("UserName"));
					}
					System.out.println("list" +usernameList.size());
					
					JPanel panel_9 = new JPanel();
					panel_9.setBounds(10, 94, 586, 41);
					panel_6.add(panel_9);
					JLabel lblNewJgoodiesLabel_13 = DefaultComponentFactory.getInstance().createLabel("Friend("+usernameList.size()+")");
					lblNewJgoodiesLabel_13.setFont(new Font("Times New Roman", Font.PLAIN, 18));
					panel_9.add(lblNewJgoodiesLabel_13);
//					
					panel_10 = new JPanel();
					panel_10.setBounds(0, 134, 596, 438);
					panel_6.add(panel_10);
					panel_10.setLayout(null);
					
					for(int i =0; i<usernameList.size();i++ ) {
						
						String friendusername = usernameList.get(i);
						JPanel panel_11 = new JPanel();
						panel_11.setBorder(new LineBorder(new Color(0, 0, 0)));
						panel_11.setBounds(10, 11 + i*105 , 576, 94);
						panel_10.add(panel_11);
						panel_11.setLayout(null);
						
						JLabel lblNewJgoodiesLabel_14 = DefaultComponentFactory.getInstance().createLabel("");
						lblNewJgoodiesLabel_14.setIcon(new ImageIcon("D:\\eclipse-workspace\\ui\\Source\\Image\\iconUserMenu.png"));
						lblNewJgoodiesLabel_14.setBounds(10, 11, 96, 72);
						panel_11.add(lblNewJgoodiesLabel_14);
						
						JLabel lblNewJgoodiesLabel_15 = DefaultComponentFactory.getInstance().createLabel(usernameList.get(i));
						lblNewJgoodiesLabel_15.setFont(new Font("Times New Roman", Font.PLAIN, 20));
						lblNewJgoodiesLabel_15.setBounds(77, 22, 177, 49);
						panel_11.add(lblNewJgoodiesLabel_15);
						
						JButton btnNewButton_1 = new JButton("UNFRIEND");
						btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 16));
						btnNewButton_1.setBounds(430, 11, 136, 29);
						panel_11.add(btnNewButton_1);
						
//						btnNewButton_1.addActionListener(event -> UnfriendHandle(event,friendusername));
						JButton btnNewButton_2 = new JButton("CHAT");
						btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 16));
						btnNewButton_2.setBounds(430, 51, 136, 32);
						panel_11.add(btnNewButton_2);
					}
					
					
					
					validate();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}
	}
	private void UnfriendHandle(ActionEvent e, String unfriendUser) {
		System.out.println("UnfriendHandle, " + unfriendUser);
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			connd = DriverManager.getConnection(Main.DB_URL, Main.USER, Main.PASS);
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		try {
			stmtd = conn.createStatement();
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		try {
			((java.sql.Statement)stmt).executeUpdate("delete from friendlist where UserID in (select UserID from Users where Username ='"+username+"') and FriendID in (select UserID from Users where Username ='"+unfriendUser+"')");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			((java.sql.Statement)stmt).executeUpdate("delete from friendlist where UserID in (select UserID from Users where Username ='"+unfriendUser+"') and FriendID in (select UserID from Users where Username ='"+username+"')");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		panel_10.removeAll();
	}
	public void initialize(String Username) {
		this.setTitle("FRIEND OPTION -" + Username);
        this.setSize(1000,600);
        getContentPane().setLayout(new BorderLayout());
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(54, 600));
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(0, 0, 54, 600);
		contentPane.add(panel);
		panel.setLayout(null);
		panel.setBackground(new Color(220, 220, 220));
		
		Icon iconChatMenu = new ImageIcon("source/image/iconChatMenu.png");
	    buttonChatMenu = new JButton(iconChatMenu);
	    buttonChatMenu.setBounds(2,2,50,50);
	    buttonChatMenu.setFocusable(false);
	    buttonChatMenu.addActionListener(this);
	    buttonChatMenu.setOpaque(false);
	    buttonChatMenu.setContentAreaFilled(false);
	      
	    Icon iconFriendMenu = new ImageIcon("source/image/iconFriendMenu.png");
	    buttonFriendMenu = new JButton(iconFriendMenu);
	    buttonFriendMenu.setBounds(2,54,50,50);
	    buttonFriendMenu.setFocusable(false);
	    buttonFriendMenu.addActionListener(this);
	    buttonFriendMenu.setOpaque(false);
	    buttonFriendMenu.setContentAreaFilled(false);
	        
	    Icon iconSettingMenu = new ImageIcon("source/image/iconSettingMenu.png");
	    buttonSettingMenu = new JButton(iconSettingMenu);
	    buttonSettingMenu.setBounds(2,108,50,50);
	    buttonSettingMenu.setFocusable(false);
	    buttonSettingMenu.addActionListener(this);
	    buttonSettingMenu.setOpaque(false);
	    buttonSettingMenu.setContentAreaFilled(false);
	        
	    Icon iconUserMenu = new ImageIcon("source/image/iconUserMenu.png");
	    buttonUserMenu = new JButton(iconUserMenu);
	    buttonUserMenu.setBounds(2,162,50,50);
	    buttonUserMenu.setFocusable(false);
	    buttonUserMenu.addActionListener(this);
	    buttonUserMenu.setOpaque(false);
	    buttonUserMenu.setContentAreaFilled(false);
	    
	    panel.add(buttonChatMenu);
        panel.add(buttonFriendMenu);
        panel.add(buttonSettingMenu);
        panel.add(buttonUserMenu);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1.setBounds(54, 0, 337, 708);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_3.setBounds(0, 0, 340, 81);
		panel_1.add(panel_3);
		panel_3.setLayout(null);
		
		JLabel lblNewJgoodiesLabel_4 = DefaultComponentFactory.getInstance().createLabel("    FRIENDS");
		lblNewJgoodiesLabel_4.setFont(new Font("Times New Roman", Font.BOLD, 26));
		lblNewJgoodiesLabel_4.setBounds(0, 0, 330, 81);
		panel_3.add(lblNewJgoodiesLabel_4);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(0, 79, 327, 63);
		panel_1.add(panel_2);
		panel_2.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(10, 11, 202, 34);
		panel_2.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("SEARCH");
		btnNewButton.setBounds(222, 11, 108, 39);
		panel_2.add(btnNewButton);
		btnNewButton.setFont(new Font("Times New Roman", Font.BOLD, 18));
		
		btnFriendRequest = new JButton("Friend Request");
		btnFriendRequest.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		btnFriendRequest.setIcon(new ImageIcon("D:\\eclipse-workspace\\ui\\Source\\Image\\add-user.png"));
		
		btnFriendRequest.setBounds(0, 153, 327, 75);
		panel_1.add(btnFriendRequest);
		btnFriendRequest.addActionListener(this);
		btnFriendList = new JButton("Friend List");
		btnFriendList.setIcon(new ImageIcon("D:\\eclipse-workspace\\ui\\Source\\Image\\friends.png"));
		btnFriendList.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		btnFriendList.setBounds(0, 257, 327, 75);
		
		panel_1.add(btnFriendList);
		
		btnGroup = new JButton("Group");
		btnGroup.setIcon(new ImageIcon("D:\\eclipse-workspace\\ui\\Source\\Image\\group.png"));
		btnGroup.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		btnGroup.setBounds(0, 362, 327, 75);
		panel_1.add(btnGroup);
		 
		panel_6 = new JPanel();
		panel_6.setBounds(391, 0, 596, 721);
		contentPane.add(panel_6);
		panel_6.setLayout(null);
		
		JPanel panel_8 = new JPanel();
		panel_8.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_8.setBounds(0, 0, 596, 80);
		panel_6.add(panel_8);
		panel_8.setLayout(null);
		
		JLabel lblNewJgoodiesLabel_11 = DefaultComponentFactory.getInstance().createLabel("");
		lblNewJgoodiesLabel_11.setIcon(new ImageIcon("D:\\eclipse-workspace\\ui\\Source\\Image\\friends.png"));
		lblNewJgoodiesLabel_11.setBounds(24, 0, 89, 82);
		panel_8.add(lblNewJgoodiesLabel_11);
		JLabel lblNewJgoodiesLabel_12 = DefaultComponentFactory.getInstance().createLabel("Friend's List");
		lblNewJgoodiesLabel_12.setFont(new Font("Times New Roman", Font.BOLD, 30));
		lblNewJgoodiesLabel_12.setBounds(101, 11, 260, 60);
		panel_8.add(lblNewJgoodiesLabel_12);
		
		
		
		getContentPane().add(contentPane);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == buttonChatMenu) {
			this.dispose();
            try{
                new HomeScreen(username, client);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
		}
		else if (e.getSource() == buttonFriendMenu) {
			
		}
		else if (e.getSource() == buttonSettingMenu) {
			try{
//                new SettingScreen();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
		}
		else if (e.getSource() == buttonUserMenu) {
            try{
//                new UserSettingScreen();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
		}
		else if (e.getSource() == btnFriendRequest) {
            try{
                new FriendRequest(username, client);
                this.dispose();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
		}
		
		else if (e.getSource() == btnGroup) {
            
		}
		
	}
}
