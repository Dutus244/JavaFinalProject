package modulesforusers;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.JButton;
import javax.swing.BoxLayout;
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
	
	JButton btnAccept;
	JButton btnChat;
	String username;
	Socket client;
	JPanel panelView;
	JButton btnSearch;
	JTextField txtSearch;
	List<String> usernameList = new ArrayList<>();
	public FriendRequest(String Username, Socket s) {
		
		this.username = Username;
		this.client = s;
		initialize(Username);
		new ReadFriendRequest().start();
	}
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
					usernameList.clear();
					rs = ((java.sql.Statement)stmt).executeQuery("SELECT UserName FROM users where UserID in (select UserID FROM sendrequestfriendlistfriendlist where AddFriendID in (select UserID from users where UserName =  '"+username+"'))");
					while(rs.next()) {
						usernameList.add(rs.getString("UserName"));
					}
					//System.out.println("list" +usernameList.size());
					
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
			        
			        
			        JLabel lbFriendNumber = new JLabel("Invitation ("+usernameList.size()+")");
			        lbFriendNumber.setFont(new Font("Times New Roman", Font.PLAIN, 18));
			        panelViewAdvance.add(lbFriendNumber);
			        
			        JPanel panelListUser = new JPanel();
			        panelListUser.setLayout(new BoxLayout(panelListUser, BoxLayout.Y_AXIS));
			        panelListUser.setBounds(0, 140, 576, usernameList.size()*100);
//			        panelView.add(panelListUser);
					
			        for(int i =0; i<usernameList.size();i++ ) {
			        	
			        	JPanel panelFriendInfo = new JPanel();
			            panelFriendInfo.setLayout(null);
			            panelFriendInfo.setPreferredSize(new Dimension(576,100));
			            panelFriendInfo.setBorder(new LineBorder(new Color(0, 0, 0)));
			            panelFriendInfo.setBounds(10, 11+ i*105, 576, 94);
			            panelListUser.add(panelFriendInfo);
			            
			            JLabel lbFriendUsername = new JLabel(usernameList.get(i));
			            lbFriendUsername.setFont(new Font("Times New Roman", Font.PLAIN, 20));
			            lbFriendUsername.setBounds(10, 21, 177, 49);
			            panelFriendInfo.add(lbFriendUsername);
			            
			            btnAccept = new JButton("ACCEPT");
			            btnAccept.setFont(new Font("Tahoma", Font.BOLD, 16));
			            btnAccept.setBounds(430, 30, 136, 32);
			            panelFriendInfo.add(btnAccept);
			            
			            btnAccept.addActionListener(event -> AcceptHandle(event,lbFriendUsername.getText()));
			            
//			            btnChat = new JButton("CHAT");
//			            btnChat.setFont(new Font("Tahoma", Font.BOLD, 16));
//			            btnChat.setBounds(430, 51, 136, 32);
//			            panelFriendInfo.add(btnChat);
//			            btnChat.addActionListener(event -> ChatOpen(event,lbFriendUsername.getText()));
			        }
			        
			        if(usernameList.size()<=4 ) {
//			        	System.out.println("1");
			        	panelView.add(panelListUser);
			        }
			        else {
//			        	System.out.println("2");
			        	JScrollPane ScrollPane = new JScrollPane();
			        	ScrollPane.setPreferredSize(new Dimension(580,580));
			        	ScrollPane.setViewportView(panelListUser);
			        	ScrollPane.setBounds(0, 140, 580,430);
			        	JScrollBar vertical = ScrollPane.getVerticalScrollBar(); 
				    	vertical.setValue(vertical.getMaximum());
				    	
			        	panelView.add(ScrollPane);
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
	private void ChatOpen(ActionEvent e, String friendUser) {
		this.dispose();
		new HomeScreen(username, client, friendUser);
	}
	private void AcceptHandle(ActionEvent e, String AcceptUser) {
		//System.out.println("AcceptHandle, " + AcceptUser);
		
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
			((java.sql.Statement)stmt).executeUpdate("insert into friendlist (UserID,FriendID) values( (select UserID from users where UserName ='"+username+"'), (select UserID from users where UserName ='"+AcceptUser+"'))");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			((java.sql.Statement)stmt).executeUpdate("insert into friendlist (UserID,FriendID) values( (select UserID from users where UserName ='"+AcceptUser+"'), (select UserID from users where UserName ='"+username+"'))");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			((java.sql.Statement)stmt).executeUpdate("delete from sendrequestfriendlistfriendlist  where AddFriendID in (select UserID from users where UserName =  '"+ username +"') and UserID in  (select UserID from users where UserName =  '"+AcceptUser+"')");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}
	public void initialize(String Username) {
		this.setTitle("Friend Request");
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
	    buttonUserMenu.setBounds(2,108,50,50);
	    buttonUserMenu.setFocusable(false);
	    buttonUserMenu.addActionListener(this);
	    buttonUserMenu.setOpaque(false);
	    buttonUserMenu.setContentAreaFilled(false);
	    
	    panelMenu.add(buttonChatMenu);
        panelMenu.add(buttonFriendMenu);
        //panelMenu.add(buttonSettingMenu);
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
		
		txtSearch = new JTextField();
		txtSearch.setBounds(10, 11, 202, 34);
		panelSearch.add(txtSearch);
		txtSearch.setColumns(10);
		
		btnSearch = new JButton("SEARCH");
		btnSearch.setBounds(222, 11, 108, 39);
		panelSearch.add(btnSearch);
		btnSearch.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnSearch.addActionListener(this);
		
		btnFriendRequest = new JButton("Friend Request");
		btnFriendRequest.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		btnFriendRequest.setIcon(new ImageIcon("source/image/add-user.png"));
		btnFriendRequest.addActionListener(this);
		btnFriendRequest.setBounds(0, 153, 327, 75);
		panelOption.add(btnFriendRequest);
		
		btnFriendList = new JButton("Friend List");
		btnFriendList.setIcon(new ImageIcon("source/image/friends.png"));
		btnFriendList.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		btnFriendList.addActionListener(this);
		btnFriendList.setBounds(0, 257, 327, 75);
		panelOption.add(btnFriendList);
		
		
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
	@Override
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
		else if (e.getSource() == btnSearch) {
            try{
                new SearchFriendScreen(username, client,txtSearch.getText());
                this.dispose();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
		}
		
		else if (e.getSource() == btnFriendList) {
            try{
                new FriendList(username, client);
                this.dispose();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
		}
		
		
	}
}
