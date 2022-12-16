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
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.Vector;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import client.ClientScreen.Read;
import modulesForUsers.FriendListScreen;
import modulesForUsers.SettingScreen;
import modulesForUsers.UserSettingScreen;

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
	
	JButton buttonNewChatSend;
	
	Connection conn = null;
	java.sql.Statement stmt;
	ResultSet rs;
	List<String> userOnlineList = new ArrayList<>();
	List<String> inboxList = new ArrayList<>();
	
	String[] stringFriendList;
	JList jListFriendList;
	
	InputStream clientIn;
	OutputStream clientOut;
	BufferedReader br;
	PrintWriter pw;
	Socket client;
	
	boolean stopRead = true;
	
	public HomeScreen(String Username, Socket s) {
		username = Username;
		client = s;
        initialize();
        try {
			clientIn = client.getInputStream();
			br = new BufferedReader(new
					InputStreamReader(clientIn));
			clientOut = client.getOutputStream();
			pw = new PrintWriter(clientOut, true);
			new Read().start(); // create a new thread for reading the messages
		} catch (Exception ex) {
			ex.printStackTrace();
		}
        
        new UpdatePanelList().start();
	}
	
	class Read extends Thread {
		@Override
		public void run() {
			while (stopRead) {
				try {
					String m = br.readLine(); 
				} catch (Exception e) {
					break;
				}
			}
		}
	}
	
	class UpdatePanelList extends Thread {
		@Override
		public void run() {
			while (true) {
				try {
					Thread.sleep(2000);
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
		        buttonNewChat.addActionListener(HomeScreen.this);
		        buttonNewChat.setContentAreaFilled(false);
		        
		        labelListFriendsOnline = new JLabel("ONLINE USERS");
		        labelListFriendsOnline.setBounds(10,55,200,40);
		        labelListFriendsOnline.setFont(Main.fontSmallestBold);
		        userOnlineList.clear();
		        int onlineFriend = 100;
		        int count = 0;
		        try {
					rs = ((java.sql.Statement)stmt).executeQuery("select users.UserName from activestatus left join users on users.userID = activestatus.userID where OnlineStatus = true and activestatus.UserID in (Select FriendID as UserID from friendlist where UserID = (select UserID from users where UserName = '"+ username + "'))");
					while (rs.next()) {
						userOnlineList.add(rs.getString("UserName"));
						count++;
					}
					onlineFriend = count;
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		        panelListFriendsOnline = new JPanel();
		        panelListFriendsOnline.setLayout(new GridLayout(1,onlineFriend));
		        for (int i = 0; i < onlineFriend; i++) {
		        	Icon avatar = new ImageIcon("source/image/iconUserMenu.png");
		        	Image image = ((ImageIcon) avatar).getImage(); // transform it 
		        	Image newimg = image.getScaledInstance(24, 24,  java.awt.Image.SCALE_SMOOTH);
		        	avatar = new ImageIcon(newimg);
		        	JButton onlineUser = new JButton(userOnlineList.get(i), avatar);
		        	onlineUser.setFont(Main.fontSmallest);
		        	onlineUser.setPreferredSize(new Dimension(60,60));
		        	onlineUser.setHorizontalTextPosition(JButton.CENTER);
		        	onlineUser.setVerticalTextPosition(JButton.BOTTOM);
		        	onlineUser.addActionListener(event -> processNewChat(event, onlineUser.getText()));
		        	panelListFriendsOnline.add(onlineUser);
		        }
		        
		        if (onlineFriend == 0) {
		        	scrollPaneListFriendOnline = new JScrollPane();
		            scrollPaneListFriendOnline.setPreferredSize(new Dimension(0,0));
		            scrollPaneListFriendOnline.setViewportView(panelListFriendsOnline);
		            scrollPaneListFriendOnline.setBounds(0,90,0,0);
		        }
		        else if (onlineFriend < 6) {
		        	 scrollPaneListFriendOnline = new JScrollPane();
		             scrollPaneListFriendOnline.setPreferredSize(new Dimension(60*onlineFriend+10,80));
		             scrollPaneListFriendOnline.setViewportView(panelListFriendsOnline);
		             scrollPaneListFriendOnline.setBounds(0,90,60*onlineFriend+10,80);
		        }
		        else {
		        	scrollPaneListFriendOnline = new JScrollPane();
		            scrollPaneListFriendOnline.setPreferredSize(new Dimension(350,80));
		            scrollPaneListFriendOnline.setViewportView(panelListFriendsOnline);
		            scrollPaneListFriendOnline.setBounds(0,90,350,80);
		        }
		        
		        Icon avatar = new ImageIcon("source/image/search.png");
		    	Image image = ((ImageIcon) avatar).getImage(); // transform it 
		    	Image newimg = image.getScaledInstance(24, 24,java.awt.Image.SCALE_SMOOTH);
		    	avatar = new ImageIcon(newimg);
		    	buttonSearch = new JButton("SEARCH", avatar);
		    	buttonSearch.setHorizontalAlignment(SwingConstants.LEFT);
		    	buttonSearch.setBounds(20,180,300,40);
		    	buttonSearch.setFocusable(false);
		    	buttonSearch.addActionListener(HomeScreen.this);
		    	
		    	inboxList.clear();
		    	int inbox = 100;
		        count = 0;
		        try {
					rs = ((java.sql.Statement)stmt).executeQuery("select distinct inboxName from inboxparticipants left join inbox on inbox.InboxID = inboxparticipants.InboxID where UserID = (select UserID from users where UserName = '"+ username + "')");
					while (rs.next()) {
						inboxList.add(rs.getString("inboxName"));
						count++;
					}
					inbox = count;
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		    
		        panelListGroupChat = new JPanel();
		        panelListGroupChat.setLayout(new GridLayout(inbox,1));
		        
		        for (int i = 0; i < inbox; i++) {
		        	Icon avatarinbox = new ImageIcon("source/image/iconUserMenu.png");
		        	Image imageinbox = ((ImageIcon) avatarinbox).getImage(); // transform it 
		        	Image newimginbox = imageinbox.getScaledInstance(48, 48,  java.awt.Image.SCALE_SMOOTH);
		        	avatarinbox = new ImageIcon(newimginbox);
		        	JLabel avatarLabel = new JLabel(avatarinbox);
		        	avatarLabel.setBounds(0,10,50,50);
		        	
		        	String inboxName = inboxList.get(i);
		        	String[] split = inboxName.split("&");
		        	if (split.length == 1) {
		        		
		        	}
		        	else {
		        		if (split[0].equals(username)) {
		        			inboxName = split[1];
		        		}
		        		else {
		        			inboxName = split[0];
		        		}
		        	}
		        	JLabel labelGroupChatName = new JLabel(inboxName);
		        	labelGroupChatName.setBounds(60,10,200,15);
		        	labelGroupChatName.setFont(Main.fontSmallestBold);
		        	
		        	JLabel labelGroupOnChat = new JLabel("You send a message");
		        	labelGroupOnChat.setBounds(60,35,200,20);
		        	labelGroupOnChat.setFont(Main.fontSmallest);
		        	
		        	JPanel panelGroupChat = new JPanel();
		        	panelGroupChat.setPreferredSize(new Dimension(300,80));
		        	panelGroupChat.setLayout(null);
		        	panelGroupChat.add(avatarLabel);
		        	panelGroupChat.add(labelGroupChatName);
		        	panelGroupChat.add(labelGroupOnChat);
		        	
		        	JButton groupChat = new JButton();
		        	groupChat.add(panelGroupChat,JButton.CENTER);
		        	groupChat.setPreferredSize(new Dimension(300,80));
		        	groupChat.addActionListener(event -> processOpenInbox(event, labelGroupChatName.getText()));
		        	groupChat.setOpaque(false);
		        	groupChat.setContentAreaFilled(false);
		        	panelListGroupChat.add(groupChat);
		        }
		        
		        if (inbox == 0) {
		        	scrollPaneListGroupChat = new JScrollPane();
			        scrollPaneListGroupChat.setPreferredSize(new Dimension(340,0));
			        scrollPaneListGroupChat.setViewportView(panelListGroupChat);
			        scrollPaneListGroupChat.setBounds(0,230,340,0);
		        }
		        else if (inbox < 5) {
		        	scrollPaneListGroupChat = new JScrollPane();
			        scrollPaneListGroupChat.setPreferredSize(new Dimension(340,80*inbox+10));
			        scrollPaneListGroupChat.setViewportView(panelListGroupChat);
			        scrollPaneListGroupChat.setBounds(0,230,340,80*inbox+10);
		        }
		        else {
		        	scrollPaneListGroupChat = new JScrollPane();
			        scrollPaneListGroupChat.setPreferredSize(new Dimension(340,370));
			        scrollPaneListGroupChat.setViewportView(panelListGroupChat);
			        scrollPaneListGroupChat.setBounds(0,230,340,370);
		        }
		        
		    	
		        panelList.add(labelList);
		        panelList.add(buttonNewChat);
		        panelList.add(labelListFriendsOnline);
		        panelList.add(scrollPaneListFriendOnline);
		        panelList.add(buttonSearch);
		        panelList.add(scrollPaneListGroupChat);
				add(panelList, BorderLayout.CENTER);
				validate();
			}
		}
	}
	
	private JTextField createTextField() {
        final JTextField field = new JTextField(15);
        field.getDocument().addDocumentListener(new DocumentListener(){
            @Override public void insertUpdate(DocumentEvent e) { filter(); }
            @Override public void removeUpdate(DocumentEvent e) { filter(); }
            @Override public void changedUpdate(DocumentEvent e) {}
            private void filter() {
                String filter = field.getText();
                filterModel((DefaultListModel<String>)jListFriendList.getModel(), filter);
            }
        });
        field.setBounds(20,90,556,40);
        return field;
    }

    private JList createJList() {
        JList list = new JList(createDefaultListModel());
        list.setVisibleRowCount(6);
        return list;
    }

    private ListModel<String> createDefaultListModel() {
        DefaultListModel<String> model = new DefaultListModel<>();
        for (String s : stringFriendList) {
            model.addElement(s);
        }
        return model;
    }

    public void filterModel(DefaultListModel<String> model, String filter) {
        for (String s : stringFriendList) {
            if (!s.startsWith(filter)) {
                if (model.contains(s)) {
                    model.removeElement(s);
                }
            } else {
                if (!model.contains(s)) {
                    model.addElement(s);
                }
            }
        }
    }
    
    private void processNewChat(ActionEvent ae, String friendUserName) {
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
			rs = ((java.sql.Statement)stmt).executeQuery("SELECT distinct InboxID from (SELECT * FROM inboxparticipants where UserID = (select UserID from users where UserName = '"+username+"')) as table1 inner join  (SELECT * FROM inboxparticipants where UserID = (select UserID from users where UserName = '"+friendUserName+"')) as table2 using(InboxID)");
			String inboxID;
			String inboxName;
			if (!rs.isBeforeFirst() ) {    
				inboxID = UUID.randomUUID().toString();
				inboxName = username + "&" + friendUserName;
				String createTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date());
				try {
	                conn.setAutoCommit(false);
	                String sql = "insert into inbox(InboxID,InboxName,TypeInbox,CreateTime) values ('"+inboxID+"','"+inboxName+"','individual','"+createTime+"')";
	                stmt.executeUpdate(sql);
	                sql = "insert into inboxparticipants values('"+inboxID+"',(select UserID from users where UserName = '"+username+"'))";
	                stmt.executeUpdate(sql);
	                sql = "insert into inboxparticipants values('"+inboxID+"',(select UserID from users where UserName = '"+friendUserName+"'))";
	                stmt.executeUpdate(sql);
	                conn.commit();
	            }
	            catch (SQLException ae1){
	            	JOptionPane.showMessageDialog(this,"Unable to insert", "Attention",JOptionPane.ERROR_MESSAGE);
	            }
			} 
			else {
				rs.next();
				inboxID = rs.getString("InboxID");
				
			}
			remove(panelChat);
			
			inboxName = friendUserName;
			
			panelChat = new JPanel();
	        panelChat.setPreferredSize(new Dimension(596,600)); // Được sử dụng khi setSize đã có trong phần cha lớn.
	        panelChat.setLayout(null);
	        panelChat.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	        
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
			
			panelGroupName = new JPanel();
		    panelGroupName.setPreferredSize(new Dimension(596,60));
		    panelGroupName.setLayout(null);
		    panelGroupName.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		        
		    Icon avatar = new ImageIcon("source/image/iconUserMenu.png");
		    Image image = ((ImageIcon) avatar).getImage(); // transform it 
		    Image newimg = image.getScaledInstance(48, 48,  java.awt.Image.SCALE_SMOOTH);
		    avatar = new ImageIcon(newimg);
		    labelGroupAvatar = new JLabel(avatar);
		    labelGroupAvatar.setBounds(10,10,40,40);
		     
		    labelGroupName = new JLabel(inboxName);
		    labelGroupName.setFont(Main.fontBigBold);
		    labelGroupName.setBounds(60,10,200,20);
		    
		    String status ="";
		    try {
				rs = ((java.sql.Statement)stmt).executeQuery("select OnlineStatus,OfflineTime from activestatus where UserID=(select UserID from users where UserName = '"+friendUserName+"')");
				rs.next();
				System.out.println(rs.getString("OnlineStatus"));
				if (rs.getString("OnlineStatus").equals("1")) {
					status = "Online";
				}
				else {
					status = "Offline";
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		    
		    labelGroupStatus = new JLabel(status);
	    	labelGroupStatus.setFont(Main.fontSmallest);
	    	labelGroupStatus.setBounds(60,35,200,20);
	    	
	    	Icon iconMore = new ImageIcon("source/image/iconMore.png");
	    	Image imageMore = ((ImageIcon) iconMore).getImage(); // transform it 
	    	Image newimgMore = imageMore.getScaledInstance(40, 40,  java.awt.Image.SCALE_SMOOTH);
	    	iconMore = new ImageIcon(newimgMore);
	        buttonGroupOption = new JButton(iconMore);
	        buttonGroupOption.setBounds(550,10,40,40);
	        buttonGroupOption.setFocusable(false);
	        buttonGroupOption.addActionListener(this);
	        buttonGroupOption.setOpaque(false);
	        buttonGroupOption.setContentAreaFilled(false);
	        
	        panelGroupName.add(labelGroupAvatar);
	    	panelGroupName.add(labelGroupName);
	    	panelGroupName.add(labelGroupStatus);
	    	panelGroupName.add(buttonGroupOption);
	    	panelGroupName.setBounds(0,0,596,60);
	    	
	    	panelGroupChat = new JPanel();
	    	scrollPaneGroupChat = new JScrollPane();
	        scrollPaneGroupChat.setPreferredSize(new Dimension(340,460));
	        scrollPaneGroupChat.setViewportView(panelGroupChat);
	        scrollPaneGroupChat.setBounds(0,60,596,440);
	        
	        panelInputChat = new JPanel();
	        panelInputChat.setLayout(null);
	        
	        textFieldInputChat = new JTextField("");
	        textFieldInputChat.setBounds(0,20,450,40);
	        
	        buttonSend = new JButton("SEND");
	        buttonSend.setBounds(460,20,120,40);
	        buttonSend.setFocusable(false);
	        buttonSend.addActionListener(this);
	        
	        panelInputChat.add(textFieldInputChat);
	        panelInputChat.add(buttonSend);
	        panelInputChat.setBounds(0,490,596,80);
	        
	        panelChat.add(panelGroupName);
	    	panelChat.add(scrollPaneGroupChat);
	    	panelChat.add(panelInputChat);
			
			add(panelChat, BorderLayout.EAST);
			validate();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    }
    
    private void processOpenInbox(ActionEvent ae, String inboxName) {
    	
    }
    
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == buttonChatMenu) {
			
		}
		else if (e.getSource() == buttonFriendMenu) {
			
		}
		else if (e.getSource() == buttonSettingMenu) {
			
		}
		else if (e.getSource() == buttonUserMenu) {
            
		}
		else if (e.getSource() == buttonNewChat) {
			remove(panelChat);
			
			panelChat = new JPanel();
	        panelChat.setPreferredSize(new Dimension(596,600)); // Được sử dụng khi setSize đã có trong phần cha lớn.
	        panelChat.setLayout(null);
	        panelChat.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	        
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
			
			List<String> friendList = new ArrayList<>();
			try {
				rs = ((java.sql.Statement)stmt).executeQuery("select users.UserName from friendlist left join users on users.userID = friendlist.FriendID where friendlist.userID = (select UserID from users where UserName = '"+ username + "')");
				while (rs.next()) {
					friendList.add(rs.getString("UserName"));
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			stringFriendList = friendList.toArray(new String[0]);
			jListFriendList = createJList();
			
			JLabel labelNewChat = new JLabel("NEW CHAT");
			labelNewChat.setBounds(20,10,200,50);
			labelNewChat.setFont(Main.fontBigBold);
			
			JLabel labelInputFriendName = new JLabel("PLEASE CHOOSE YOUR FRIEND NAME");
			labelInputFriendName.setFont(Main.fontSmallestBoldItalic);
			labelInputFriendName.setBounds(20,60,400,40);
			
			JScrollPane jScrollPaneListFriend = new JScrollPane(jListFriendList);
			jScrollPaneListFriend.setBounds(20,140,556,350);
			
			textFieldInputChat = new JTextField("");
	        textFieldInputChat.setBounds(20,510,430,40);
	        
	        buttonNewChatSend = new JButton("SEND");
	        buttonNewChatSend.setBounds(460,510,116,40);
	        buttonNewChatSend.setFocusable(false);
	        buttonNewChatSend.addActionListener(this);
	        
			panelChat.add(labelNewChat);
			panelChat.add(labelInputFriendName);
			panelChat.add(createTextField());
			panelChat.add(jScrollPaneListFriend);
			panelChat.add(textFieldInputChat);
			panelChat.add(buttonNewChatSend);
	        
			add(panelChat, BorderLayout.EAST);
			validate();
		}
		else if (e.getSource() == buttonNewChatSend) {
			if (jListFriendList.getSelectedValue() == null) {
				JOptionPane.showMessageDialog(this,"Please choose your friend username to send message", "Attention",JOptionPane.ERROR_MESSAGE);
                return;
			}
			String friendUserName = jListFriendList.getSelectedValue().toString();
			String message = textFieldInputChat.getText();
			if (message.isEmpty()) {
				JOptionPane.showMessageDialog(this,"Please input a message", "Attention",JOptionPane.ERROR_MESSAGE);
                return;
			}
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
				rs = ((java.sql.Statement)stmt).executeQuery("SELECT distinct InboxID from (SELECT * FROM inboxparticipants where UserID = (select UserID from users where UserName = '"+username+"')) as table1 inner join  (SELECT * FROM inboxparticipants where UserID = (select UserID from users where UserName = '"+friendUserName+"')) as table2 using(InboxID)");
				String inboxID;
				String inboxName;
				if (!rs.isBeforeFirst() ) {    
					inboxID = UUID.randomUUID().toString();
					inboxName = username + "&" + friendUserName;
					String createTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date());
					try {
		                conn.setAutoCommit(false);
		                String sql = "insert into inbox(InboxID,InboxName,TypeInbox,CreateTime) values ('"+inboxID+"','"+inboxName+"','individual','"+createTime+"')";
		                stmt.executeUpdate(sql);
		                sql = "insert into inboxparticipants values('"+inboxID+"',(select UserID from users where UserName = '"+username+"'))";
		                stmt.executeUpdate(sql);
		                sql = "insert into inboxparticipants values('"+inboxID+"',(select UserID from users where UserName = '"+friendUserName+"'))";
		                stmt.executeUpdate(sql);
		                conn.commit();
		            }
		            catch (SQLException ae){
		            	JOptionPane.showMessageDialog(this,"Unable to insert", "Attention",JOptionPane.ERROR_MESSAGE);
		            }
				} 
				else {
					rs.next();
					inboxID = rs.getString("InboxID");
					
				}
				remove(panelChat);
				
				inboxName = friendUserName;
				
				panelChat = new JPanel();
		        panelChat.setPreferredSize(new Dimension(596,600)); // Được sử dụng khi setSize đã có trong phần cha lớn.
		        panelChat.setLayout(null);
		        panelChat.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		        
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
				
				panelGroupName = new JPanel();
			    panelGroupName.setPreferredSize(new Dimension(596,60));
			    panelGroupName.setLayout(null);
			    panelGroupName.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			        
			    Icon avatar = new ImageIcon("source/image/iconUserMenu.png");
			    Image image = ((ImageIcon) avatar).getImage(); // transform it 
			    Image newimg = image.getScaledInstance(48, 48,  java.awt.Image.SCALE_SMOOTH);
			    avatar = new ImageIcon(newimg);
			    labelGroupAvatar = new JLabel(avatar);
			    labelGroupAvatar.setBounds(10,10,40,40);
			     
			    labelGroupName = new JLabel(inboxName);
			    labelGroupName.setFont(Main.fontBigBold);
			    labelGroupName.setBounds(60,10,200,20);
			    
			    String status ="";
			    try {
					rs = ((java.sql.Statement)stmt).executeQuery("select OnlineStatus,OfflineTime from activestatus where UserID=(select UserID from users where UserName = '"+friendUserName+"')");
					rs.next();
					if (rs.getString("OnlineStatus").equals("1")) {
						status = "Online";
					}
					else {
						status = "Offline";
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			    
			    labelGroupStatus = new JLabel(status);
		    	labelGroupStatus.setFont(Main.fontSmallest);
		    	labelGroupStatus.setBounds(60,35,200,20);
		    	
		    	Icon iconMore = new ImageIcon("source/image/iconMore.png");
		    	Image imageMore = ((ImageIcon) iconMore).getImage(); // transform it 
		    	Image newimgMore = imageMore.getScaledInstance(40, 40,  java.awt.Image.SCALE_SMOOTH);
		    	iconMore = new ImageIcon(newimgMore);
		        buttonGroupOption = new JButton(iconMore);
		        buttonGroupOption.setBounds(550,10,40,40);
		        buttonGroupOption.setFocusable(false);
		        buttonGroupOption.addActionListener(this);
		        buttonGroupOption.setOpaque(false);
		        buttonGroupOption.setContentAreaFilled(false);
		        
		        panelGroupName.add(labelGroupAvatar);
		    	panelGroupName.add(labelGroupName);
		    	panelGroupName.add(labelGroupStatus);
		    	panelGroupName.add(buttonGroupOption);
		    	panelGroupName.setBounds(0,0,596,60);
		    	
		    	panelGroupChat = new JPanel();
		    	scrollPaneGroupChat = new JScrollPane();
		        scrollPaneGroupChat.setPreferredSize(new Dimension(340,460));
		        scrollPaneGroupChat.setViewportView(panelGroupChat);
		        scrollPaneGroupChat.setBounds(0,60,596,440);
		        
		        panelInputChat = new JPanel();
		        panelInputChat.setLayout(null);
		        
		        textFieldInputChat = new JTextField("");
		        textFieldInputChat.setBounds(0,20,450,40);
		        
		        buttonSend = new JButton("SEND");
		        buttonSend.setBounds(460,20,120,40);
		        buttonSend.setFocusable(false);
		        buttonSend.addActionListener(this);
		        
		        panelInputChat.add(textFieldInputChat);
		        panelInputChat.add(buttonSend);
		        panelInputChat.setBounds(0,490,596,80);
		        
		        panelChat.add(panelGroupName);
		    	panelChat.add(scrollPaneGroupChat);
		    	panelChat.add(panelInputChat);
				
				add(panelChat, BorderLayout.EAST);
				validate();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
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
						if(client != null) {
		        			try {
								clientOut = client.getOutputStream();
								pw = new PrintWriter(clientOut, true);
								pw.println("`exit");
								pw.close();
				                br.close();
				                client.close();
				                stopRead = false;
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
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
        
        int onlineFriend = 100;
        int count = 0;
        try {
			rs = ((java.sql.Statement)stmt).executeQuery("select users.UserName from activestatus left join users on users.userID = activestatus.userID where OnlineStatus = true and activestatus.UserID in (Select FriendID as UserID from friendlist where UserID = (select UserID from users where UserName = '"+ username + "'))");
			while (rs.next()) {
				userOnlineList.add(rs.getString("UserName"));
				count++;
			}
			onlineFriend = count;
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        panelListFriendsOnline = new JPanel();
        panelListFriendsOnline.setLayout(new GridLayout(1,onlineFriend));
        
        for (int i = 0; i < onlineFriend; i++) {
        	Icon avatar = new ImageIcon("source/image/iconUserMenu.png");
        	Image image = ((ImageIcon) avatar).getImage(); // transform it 
        	Image newimg = image.getScaledInstance(24, 24,  java.awt.Image.SCALE_SMOOTH);
        	avatar = new ImageIcon(newimg);
        	JButton onlineUser = new JButton(userOnlineList.get(i), avatar);
        	onlineUser.setFont(Main.fontSmallest);
        	onlineUser.setPreferredSize(new Dimension(60,60));
        	onlineUser.setHorizontalTextPosition(JButton.CENTER);
        	onlineUser.setVerticalTextPosition(JButton.BOTTOM);
        	onlineUser.addActionListener(event -> processNewChat(event, onlineUser.getText()));
        	panelListFriendsOnline.add(onlineUser);
        }
        
        if (onlineFriend == 0) {
        	scrollPaneListFriendOnline = new JScrollPane();
            scrollPaneListFriendOnline.setPreferredSize(new Dimension(0,0));
            scrollPaneListFriendOnline.setViewportView(panelListFriendsOnline);
            scrollPaneListFriendOnline.setBounds(0,90,0,0);
        }
        else if (onlineFriend < 6) {
       	 scrollPaneListFriendOnline = new JScrollPane();
         scrollPaneListFriendOnline.setPreferredSize(new Dimension(60*onlineFriend+10,80));
         scrollPaneListFriendOnline.setViewportView(panelListFriendsOnline);
         scrollPaneListFriendOnline.setBounds(0,90,60*onlineFriend+10,80);
        }
        else {
        	scrollPaneListFriendOnline = new JScrollPane();
            scrollPaneListFriendOnline.setPreferredSize(new Dimension(350,80));
            scrollPaneListFriendOnline.setViewportView(panelListFriendsOnline);
            scrollPaneListFriendOnline.setBounds(0,90,350,80);
        }
        
        Icon avatar = new ImageIcon("source/image/search.png");
    	Image image = ((ImageIcon) avatar).getImage(); // transform it 
    	Image newimg = image.getScaledInstance(24, 24,java.awt.Image.SCALE_SMOOTH);
    	avatar = new ImageIcon(newimg);
    	buttonSearch = new JButton("SEARCH", avatar);
    	buttonSearch.setHorizontalAlignment(SwingConstants.LEFT);
    	buttonSearch.setBounds(20,180,300,40);
    	buttonSearch.setFocusable(false);
        buttonSearch.addActionListener(this);
        
        int inbox = 100;
        count = 0;
        try {
			rs = ((java.sql.Statement)stmt).executeQuery("select inboxName from inboxparticipants left join inbox on inbox.InboxID = inboxparticipants.InboxID where UserID = (select UserID from users where UserName = '"+ username + "')");
			while (rs.next()) {
				inboxList.add(rs.getString("inboxName"));
				count++;
			}
			inbox = count;
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    
        panelListGroupChat = new JPanel();
        panelListGroupChat.setLayout(new GridLayout(inbox,1));
        
        for (int i = 0; i < inbox; i++) {
        	Icon avatarinbox = new ImageIcon("source/image/iconUserMenu.png");
        	Image imageinbox = ((ImageIcon) avatarinbox).getImage(); // transform it 
        	Image newimginbox = imageinbox.getScaledInstance(48, 48,  java.awt.Image.SCALE_SMOOTH);
        	avatarinbox = new ImageIcon(newimginbox);
        	JLabel avatarLabel = new JLabel(avatarinbox);
        	avatarLabel.setBounds(0,10,50,50);
        	
        	String inboxName = inboxList.get(i);
        	String[] split = inboxName.split("&");
        	if (split.length == 1) {
        		
        	}
        	else {
        		if (split[0].equals(username)) {
        			inboxName = split[1];
        		}
        		else {
        			inboxName = split[0];
        		}
        	}
        	JLabel labelGroupChatName = new JLabel(inboxName);
        	labelGroupChatName.setBounds(60,10,200,15);
        	labelGroupChatName.setFont(Main.fontSmallestBold);
        	
        	JLabel labelGroupOnChat = new JLabel("You send a message");
        	labelGroupOnChat.setBounds(60,35,200,20);
        	labelGroupOnChat.setFont(Main.fontSmallest);
        	
        	JPanel panelGroupChat = new JPanel();
        	panelGroupChat.setPreferredSize(new Dimension(300,80));
        	panelGroupChat.setLayout(null);
        	panelGroupChat.add(avatarLabel);
        	panelGroupChat.add(labelGroupChatName);
        	panelGroupChat.add(labelGroupOnChat);
        	
        	JButton groupChat = new JButton();
        	groupChat.add(panelGroupChat,JButton.CENTER);
        	groupChat.setPreferredSize(new Dimension(300,80));
        	groupChat.addActionListener(event -> processOpenInbox(event, labelGroupChatName.getText()));
        	groupChat.setOpaque(false);
        	groupChat.setContentAreaFilled(false);
        	panelListGroupChat.add(groupChat);
        }
        
        if (inbox == 0) {
        	scrollPaneListGroupChat = new JScrollPane();
	        scrollPaneListGroupChat.setPreferredSize(new Dimension(340,0));
	        scrollPaneListGroupChat.setViewportView(panelListGroupChat);
	        scrollPaneListGroupChat.setBounds(0,230,340,0);
        }
        else if (inbox < 5) {
        	scrollPaneListGroupChat = new JScrollPane();
	        scrollPaneListGroupChat.setPreferredSize(new Dimension(340,80*inbox+10));
	        scrollPaneListGroupChat.setViewportView(panelListGroupChat);
	        scrollPaneListGroupChat.setBounds(0,230,340,80*inbox+10);
        }
        else {
        	scrollPaneListGroupChat = new JScrollPane();
	        scrollPaneListGroupChat.setPreferredSize(new Dimension(340,370));
	        scrollPaneListGroupChat.setViewportView(panelListGroupChat);
	        scrollPaneListGroupChat.setBounds(0,230,340,370);
        }
    	
        panelList.add(labelList);
        panelList.add(buttonNewChat);
        panelList.add(labelListFriendsOnline);
        panelList.add(scrollPaneListFriendOnline);
        panelList.add(buttonSearch);
        panelList.add(scrollPaneListGroupChat);
        
        panelChat = new JPanel();
        panelChat.setPreferredSize(new Dimension(596,600)); // Được sử dụng khi setSize đã có trong phần cha lớn.
        panelChat.setLayout(null);
        panelChat.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        
        add(panelMenu, BorderLayout.WEST);
        add(panelList, BorderLayout.CENTER);
        add(panelChat, BorderLayout.EAST);
	}
}
