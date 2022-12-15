package modulesforusers;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.*;

public class HomeScreen  extends JFrame implements ActionListener{
	Container containerHome;
	String username;
	
	JPanel panelMenu;
	
	JButton buttonChatMenu;
	JButton buttonFriendMenu;
	JButton buttonSettingMenu;
	JButton buttonUserMenu;
	
	JPanel panelList;
	
	JLabel labelList;
	JButton buttonNewChat;
	JLabel labelListFriendsOnline;
	JPanel panelListFriendsOnline;
	JScrollPane scrollPaneListFriendOnline;
	JTextField textFieldSearch;
	JButton buttonSearch;
	JPanel panelListGroupChat;
	JScrollPane scrollPaneListGroupChat;
	
	JPanel panelChat;
	JPanel panelGroupName;
	JLabel labelGroupName;
	JLabel labelGroupAvatar;
	JLabel labelGroupStatus;
	JButton buttonGroupOption;
	JPanel panelGroupChat;
	JScrollPane scrollPaneGroupChat;
	JPanel panelInputChat;
	JTextField textFieldInputChat;
	JButton buttonSend;
	
	Connection conn = null;
	java.sql.Statement stmt;
	ResultSet rs;
	List<String> userOnlineList = new ArrayList<>();
	
	public HomeScreen(String Username) {
		username = Username;
        initialize();
        
        new UpdatePanelList().start();
	}
	
	class UpdatePanelList extends Thread {
		@Override
		public void run() {
			while (true) {
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				remove(panelList);
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					conn = DriverManager.getConnection(Main.DB_URL, Main.USER, Main.PASS);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					stmt = conn.createStatement();
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				panelList = new JPanel();
		        panelList.setPreferredSize(new Dimension(350,600)); // Được sử dụng khi setSize đã có trong phần cha lớn.
		        panelList.setLayout(null);
		        panelList.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		        
		        labelList = new JLabel("CHATS");
		        labelList.setBounds(20,10,200,50);
		        labelList.setFont(Main.fontBigBold);
		        
		        Icon iconNewChat = new ImageIcon("source/image/iconNewChat.png");
		        buttonNewChat = new JButton(iconNewChat);
		        buttonNewChat.setBounds(270,10,50,50);
		        buttonNewChat.setFocusable(false);
		        buttonNewChat.setOpaque(false);
		        buttonNewChat.setContentAreaFilled(false);
		        
		        labelListFriendsOnline = new JLabel("ONLINE USERS");
		        labelListFriendsOnline.setBounds(10,55,200,40);
		        labelListFriendsOnline.setFont(Main.fontSmallestBold);
		        userOnlineList.clear();
				int n = 100;
		        int count = 0;
		        try {
					rs = ((java.sql.Statement)stmt).executeQuery("select users.UserName from activestatus left join users on users.userID = activestatus.userID where OnlineStatus = true and activestatus.UserID in (Select FriendID as UserID from friendlist where UserID = (select UserID from users where UserName = '"+ username + "'))");
					while (rs.next()) {
						userOnlineList.add(rs.getString("UserName"));
						count++;
					}
					n = count;
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		        panelListFriendsOnline = new JPanel();
		        panelListFriendsOnline.setLayout(new GridLayout(1,n));
		        for (int i = 0; i < n; i++) {
		        	Icon avatar = new ImageIcon("source/image/iconUserMenu.png");
		        	Image image = ((ImageIcon) avatar).getImage(); // transform it 
		        	Image newimg = image.getScaledInstance(24, 24,  java.awt.Image.SCALE_SMOOTH);
		        	avatar = new ImageIcon(newimg);
		        	JButton onlineUser = new JButton(userOnlineList.get(i), avatar);
		        	onlineUser.setFont(Main.fontSmallest);
		        	onlineUser.setPreferredSize(new Dimension(60,60));
		        	onlineUser.setHorizontalTextPosition(JButton.CENTER);
		        	onlineUser.setVerticalTextPosition(JButton.BOTTOM);
		        	panelListFriendsOnline.add(onlineUser);
		        }
		        
		        if (n == 0) {
		        	scrollPaneListFriendOnline = new JScrollPane();
		            scrollPaneListFriendOnline.setPreferredSize(new Dimension(0,0));
		            scrollPaneListFriendOnline.setViewportView(panelListFriendsOnline);
		            scrollPaneListFriendOnline.setBounds(0,90,0,0);
		        }
		        else if (n < 6) {
		        	 scrollPaneListFriendOnline = new JScrollPane();
		             scrollPaneListFriendOnline.setPreferredSize(new Dimension(60*n+10,80));
		             scrollPaneListFriendOnline.setViewportView(panelListFriendsOnline);
		             scrollPaneListFriendOnline.setBounds(0,90,60*n+10,80);
		        }
		        else {
		        	scrollPaneListFriendOnline = new JScrollPane();
		            scrollPaneListFriendOnline.setPreferredSize(new Dimension(350,80));
		            scrollPaneListFriendOnline.setViewportView(panelListFriendsOnline);
		            scrollPaneListFriendOnline.setBounds(0,90,350,80);
		        }
		        
		        panelList.add(labelList);
		        panelList.add(buttonNewChat);
		        panelList.add(labelListFriendsOnline);
		        panelList.add(scrollPaneListFriendOnline);
				add(panelList, BorderLayout.CENTER);
				validate();
			}
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	private void initialize() { //here components of Swing App UI are initilized
		this.setTitle("HOME");
        this.setSize(1000,600);
        this.setLayout(new BorderLayout());
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setResizable(false);
        
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
        
        
        addWindowListener(new WindowAdapter() {
        	@Override
        	public void windowClosing(WindowEvent e) {
        		if(conn != null) {
        			try {
        				try {
        					String createTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date());
        					conn.setAutoCommit(false);
    		                String sql = "update activestatus set OnlineStatus = false,OfflineTime='"+createTime+"' where UserID = (select UserID from users where UserName = '"+ username + "')";
    		                stmt.executeUpdate(sql);
    		                conn.commit();
    		            }
    		            catch (SQLException ae){
    		            	
    		            }
        				stmt.close();
						conn.close();
						System.exit(0);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
        		}
        		else {
        			System.exit(0);
        		}
        	}
        });
        
        panelMenu = new JPanel();
        panelMenu.setPreferredSize(new Dimension(54,600 )); 
        panelMenu.setLayout(null);
        panelMenu.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        
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
        
        panelList = new JPanel();
        panelList.setPreferredSize(new Dimension(350,600)); // Được sử dụng khi setSize đã có trong phần cha lớn.
        panelList.setLayout(null);
        panelList.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        
        labelList = new JLabel("CHATS");
        labelList.setBounds(20,10,200,50);
        labelList.setFont(Main.fontBigBold);
        
        Icon iconNewChat = new ImageIcon("source/image/iconNewChat.png");
        buttonNewChat = new JButton(iconNewChat);
        buttonNewChat.setBounds(270,10,50,50);
        buttonNewChat.setFocusable(false);
        buttonNewChat.addActionListener(this);
        buttonNewChat.setOpaque(false);
        buttonNewChat.setContentAreaFilled(false);
        
        labelListFriendsOnline = new JLabel("ONLINE USERS");
        labelListFriendsOnline.setBounds(10,55,200,40);
        labelListFriendsOnline.setFont(Main.fontSmallestBold);
        
        int n = 100;
        int count = 0;
        try {
			rs = ((java.sql.Statement)stmt).executeQuery("select users.UserName from activestatus left join users on users.userID = activestatus.userID where OnlineStatus = true and activestatus.UserID in (Select FriendID as UserID from friendlist where UserID = (select UserID from users where UserName = '"+ username + "'))");
			while (rs.next()) {
				userOnlineList.add(rs.getString("UserName"));
				count++;
			}
			n = count;
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        panelListFriendsOnline = new JPanel();
        panelListFriendsOnline.setLayout(new GridLayout(1,n));
        
        for (int i = 0; i < n; i++) {
        	Icon avatar = new ImageIcon("source/image/iconUserMenu.png");
        	Image image = ((ImageIcon) avatar).getImage(); // transform it 
        	Image newimg = image.getScaledInstance(24, 24,  java.awt.Image.SCALE_SMOOTH);
        	avatar = new ImageIcon(newimg);
        	JButton onlineUser = new JButton(userOnlineList.get(i), avatar);
        	onlineUser.setFont(Main.fontSmallest);
        	onlineUser.setPreferredSize(new Dimension(60,60));
        	onlineUser.setHorizontalTextPosition(JButton.CENTER);
        	onlineUser.setVerticalTextPosition(JButton.BOTTOM);
        	panelListFriendsOnline.add(onlineUser);
        }
        
        if (n == 0) {
        	scrollPaneListFriendOnline = new JScrollPane();
            scrollPaneListFriendOnline.setPreferredSize(new Dimension(0,0));
            scrollPaneListFriendOnline.setViewportView(panelListFriendsOnline);
            scrollPaneListFriendOnline.setBounds(0,90,0,0);
        }
        else if (n < 6) {
       	 scrollPaneListFriendOnline = new JScrollPane();
         scrollPaneListFriendOnline.setPreferredSize(new Dimension(60*n+10,80));
         scrollPaneListFriendOnline.setViewportView(panelListFriendsOnline);
         scrollPaneListFriendOnline.setBounds(0,90,60*n+10,80);
        }
        else {
        	scrollPaneListFriendOnline = new JScrollPane();
            scrollPaneListFriendOnline.setPreferredSize(new Dimension(350,80));
            scrollPaneListFriendOnline.setViewportView(panelListFriendsOnline);
            scrollPaneListFriendOnline.setBounds(0,90,350,80);
        }
        
        panelList.add(labelList);
        panelList.add(buttonNewChat);
        panelList.add(labelListFriendsOnline);
        panelList.add(scrollPaneListFriendOnline);
        
        panelChat = new JPanel();
        panelChat.setPreferredSize(new Dimension(596,600)); // Được sử dụng khi setSize đã có trong phần cha lớn.
        panelChat.setLayout(null);
        panelChat.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        
        add(panelMenu, BorderLayout.WEST);
        add(panelList, BorderLayout.CENTER);
        add(panelChat, BorderLayout.EAST);
	}
}
