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
import java.awt.Color;
import java.awt.Container;
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

public class FriendRequest extends JFrame implements ActionListener {
	
	Container container;
	JButton buttonChatMenu;
	JButton buttonFriendMenu;
	JButton buttonSettingMenu;
	JButton buttonUserMenu;
	JButton btnFriendRequest;
	JButton btnFriendList;
	JButton btnGroup;
	String username;
	Socket client;
	JPanel panelView;
	List<String> usernameRequest = new ArrayList<>();
	public FriendRequest(String Username, Socket s) {
		System.out.println("F request");
		this.username = Username;
		this.client = s;
		
		initialize(Username);
		new ReadFriendRequest().start();
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
	class ReadFriendRequest extends Thread{
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
					remove(panelView);
					usernameRequest.clear();
					rs = ((java.sql.Statement)stmt).executeQuery("SELECT UserName FROM users where UserID in (select UserID FROM sendrequestfriendlistfriendlist where AddFriendID =  '"+ username +"')");
					while(rs.next()) {
						usernameRequest.add(rs.getString("UserName"));
					}
					System.out.println("list" +usernameRequest.size());
					
					panelView = new JPanel();
			        panelView.setLayout(null);
			      
			        panelView.setPreferredSize(new Dimension(596,600));
			        
			        JPanel panelOptionTitle = new JPanel();
			        panelOptionTitle.setLayout(null);
			        panelOptionTitle.setBorder(new LineBorder(new Color(0, 0, 0)));
			        panelOptionTitle.setBounds(0, 0, 596, 80);
			        panelView.add(panelOptionTitle);
			        
			        JLabel lbOptionTitle = new JLabel("Friend's Request");
			        lbOptionTitle.setFont(new Font("Times New Roman", Font.BOLD, 30));
			        lbOptionTitle.setBounds(10, 11, 351, 60);
			        panelOptionTitle.add(lbOptionTitle);
			        
			        JPanel panelViewAdvance = new JPanel();
			        panelViewAdvance.setBounds(10, 94, 586, 41);
			        panelView.add(panelViewAdvance);
			        
			        
			        JLabel lbInvitation = new JLabel("Invitation("+usernameRequest.size()+")");
			        lbInvitation.setFont(new Font("Times New Roman", Font.PLAIN, 18));
			        panelViewAdvance.add(lbInvitation);
			        
			        JPanel panelListUser = new JPanel();
			        panelListUser.setLayout(null);
			        panelListUser.setBounds(0, 134, 582, 429);
			        panelView.add(panelListUser);
					
			        for(int i =0; i<usernameRequest.size();i++ ) {
			        	
			        	JPanel panelFriendInfo = new JPanel();
			            panelFriendInfo.setLayout(null);
			            panelFriendInfo.setBorder(new LineBorder(new Color(0, 0, 0)));
			            panelFriendInfo.setBounds(10, 11+ i*105, 576, 94);
			            panelListUser.add(panelFriendInfo);
			            
			            JLabel lbFriendUsername = new JLabel(usernameRequest.get(i));
			            lbFriendUsername.setFont(new Font("Times New Roman", Font.PLAIN, 20));
			            lbFriendUsername.setBounds(10, 21, 177, 49);
			            panelFriendInfo.add(lbFriendUsername);
			            
			            JButton btnAccept = new JButton("ACCEPT");
			            btnAccept.setFont(new Font("Tahoma", Font.BOLD, 16));
			            btnAccept.setBounds(430, 11, 136, 32);
			            panelFriendInfo.add(btnAccept);
			            
			            btnAccept.addActionListener(event -> AcceptHandle(event,lbFriendUsername.getText()));
			            JButton btnChat = new JButton("CHAT");
			            btnChat.setFont(new Font("Tahoma", Font.BOLD, 16));
			            btnChat.setBounds(430, 51, 136, 32);
			            panelFriendInfo.add(btnChat);
		
			        }
			        getContentPane().add(panelView, BorderLayout.EAST);
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
	private void AcceptHandle(ActionEvent e, String acceptUser) {
		System.out.println("acceptHandle, " + acceptUser);
		
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
//		try {
////			((java.sql.Statement)stmt).executeUpdate("delete from friendlist where UserID in (select UserID from Users where Username ='"+username+"') and FriendID in (select UserID from Users where Username ='"+acceptUser+"')");
//		} catch (SQLException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//		try {
////			((java.sql.Statement)stmt).executeUpdate("delete from friendlist where UserID in (select UserID from Users where Username ='"+acceptUser+"') and FriendID in (select UserID from Users where Username ='"+username+"')");
//		} catch (SQLException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
		
	}
	public void initialize(String Username) {
		this.setTitle("CHAT APPLICATION");
        this.setSize(1000,600);
        getContentPane().setLayout(new BorderLayout());
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
		//contentPane = new JPanel();
		//contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		//contentPane.setLayout(null);
		
		JPanel panelMenu = new JPanel();
		panelMenu.setPreferredSize(new Dimension(54,600 )); 
		panelMenu.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelMenu.setBounds(0, 0, 54, 563);
		//contentPane.add(panelMenu);
		panelMenu.setLayout(null);
		panelMenu.setBackground(new Color(220, 220, 220));
		
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
	    
	    panelMenu.add(buttonChatMenu);
        panelMenu.add(buttonFriendMenu);
        panelMenu.add(buttonSettingMenu);
        panelMenu.add(buttonUserMenu);
		
		JPanel panelOption = new JPanel();
		panelOption.setBorder(new LineBorder(new Color(0, 0, 0)));
		 panelOption.setPreferredSize(new Dimension(350,600)); 
		//contentPane.add(panelOption);
		panelOption.setLayout(null);
		
		JPanel panelTitle = new JPanel();
		panelTitle.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelTitle.setBounds(0, 0,349, 81);
		panelOption.add(panelTitle);
		panelTitle.setLayout(null);
		
		JLabel lbTitle = new JLabel("    FRIENDS");
		lbTitle.setFont(new Font("Times New Roman", Font.BOLD, 26));
		lbTitle.setBounds(0, 0, 330, 81);
		panelTitle.add(lbTitle);
		
		JPanel panelSearch = new JPanel();
		panelSearch.setBounds(0, 79, 327, 63);
		panelOption.add(panelSearch);
		panelSearch.setLayout(null);
		
		JTextField txtSearch = new JTextField();
		txtSearch.setBounds(10, 11, 202, 34);
		panelSearch.add(txtSearch);
		txtSearch.setColumns(10);
		
		JButton btnSearch = new JButton("SEARCH");
		btnSearch.setBounds(222, 11, 108, 39);
		panelSearch.add(btnSearch);
		btnSearch.setFont(new Font("Times New Roman", Font.BOLD, 18));
		
		btnFriendRequest = new JButton("Friend Request");
		btnFriendRequest.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		btnFriendRequest.setIcon(new ImageIcon("source/image/add-user.png"));
		
		btnFriendRequest.setBounds(0, 153, 327, 75);
		panelOption.add(btnFriendRequest);
		
		btnFriendList = new JButton("Friend List");
		btnFriendList.setIcon(new ImageIcon("source/image/friends.png"));
		btnFriendList.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		btnFriendList.setBounds(0, 257, 327, 75);
		panelOption.add(btnFriendList);
		btnFriendList.addActionListener(this);
		btnGroup = new JButton("Group");
		btnGroup.setIcon(new ImageIcon("source/image/group.png"));
		btnGroup.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		btnGroup.setBounds(0, 362, 327, 75);
		
		btnGroup.addActionListener(this);
		panelOption.add(btnGroup);
		
		panelView = new JPanel();
        panelView.setLayout(null);
      
        panelView.setPreferredSize(new Dimension(596,600));
        
        JPanel panelOptionTitle = new JPanel();
        panelOptionTitle.setLayout(null);
        panelOptionTitle.setBorder(new LineBorder(new Color(0, 0, 0)));
        panelOptionTitle.setBounds(0, 0, 596, 80);
        panelView.add(panelOptionTitle);
        
        JLabel lbOptionTitle = new JLabel("Friend's Request");
        lbOptionTitle.setFont(new Font("Times New Roman", Font.BOLD, 30));
        lbOptionTitle.setBounds(10, 11, 351, 60);
        panelOptionTitle.add(lbOptionTitle);
        
       
		
		getContentPane().add(panelMenu, BorderLayout.WEST);
        getContentPane().add(panelOption, BorderLayout.CENTER);
        getContentPane().add(panelView, BorderLayout.EAST);
	}

	public void actionPerformed(ActionEvent e) {
		isOpen=false;
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
		else if (e.getSource() == btnFriendList) {
            try{
            	isOpen = false;
                new FriendList(username, client);
                this.dispose();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
		}
		
		else if (e.getSource() == btnGroup) {
			isOpen = false;
		}
	}
}
