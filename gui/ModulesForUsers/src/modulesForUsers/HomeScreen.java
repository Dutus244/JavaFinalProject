package modulesForUsers;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;

import javax.swing.*;

public class HomeScreen extends JFrame implements ActionListener{
	Container container;
	
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
	
	public HomeScreen() {
		this.setTitle("CHAT APPLICATION");
        this.setSize(1000,600);
        this.setLayout(new BorderLayout());
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        
        Font font1 = new Font("Times New Roman", Font.BOLD,25);
        Font font2 = new Font("Times New Roman", Font.PLAIN,15);
        Font font3 = new Font("Times New Roman", Font.PLAIN,10);
        Font font4 = new Font("Times New Roman", Font.BOLD,15);
        
        panelMenu = new JPanel();
        panelMenu.setPreferredSize(new Dimension(54,600 )); // Được sử dụng khi setSize đã có trong phần cha lớn.
        panelMenu.setLayout(null);
        panelMenu.setBorder(BorderFactory.createLineBorder(Color.BLACK));
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
        
        panelList = new JPanel();
        panelList.setPreferredSize(new Dimension(350,600)); // Được sử dụng khi setSize đã có trong phần cha lớn.
        panelList.setLayout(null);
        panelList.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        
        labelList = new JLabel("CHATS");
        labelList.setBounds(20,10,100,50);
        labelList.setFont(font1);
        
        Icon iconNewChat = new ImageIcon("source/image/iconNewChat.png");
        buttonNewChat = new JButton(iconNewChat);
        buttonNewChat.setBounds(270,10,50,50);
        buttonNewChat.setFocusable(false);
        buttonNewChat.addActionListener(this);
        buttonNewChat.setOpaque(false);
        buttonNewChat.setContentAreaFilled(false);
        buttonNewChat.setBorderPainted(false);
        
        labelListFriendsOnline = new JLabel("ONLINE USER");
        labelListFriendsOnline.setBounds(10,55,100,40);
        labelListFriendsOnline.setFont(font2);
        
        panelListFriendsOnline = new JPanel();
        panelListFriendsOnline.setLayout(new GridLayout(1,100));
        
        for (int i = 0; i < 10; i++) {
        	Icon avatar = new ImageIcon("source/image/iconUserMenu.png");
        	Image image = ((ImageIcon) avatar).getImage(); // transform it 
        	Image newimg = image.getScaledInstance(24, 24,  java.awt.Image.SCALE_SMOOTH);
        	avatar = new ImageIcon(newimg);
        	JButton onlineUser = new JButton("user", avatar);
        	onlineUser.setFont(font3);
        	onlineUser.setPreferredSize(new Dimension(60,60));
        	onlineUser.setHorizontalTextPosition(JButton.CENTER);
        	onlineUser.setVerticalTextPosition(JButton.BOTTOM);
        	panelListFriendsOnline.add(onlineUser);
        }
        
        scrollPaneListFriendOnline = new JScrollPane();
        scrollPaneListFriendOnline.setPreferredSize(new Dimension(350,80));
        scrollPaneListFriendOnline.setViewportView(panelListFriendsOnline);
        scrollPaneListFriendOnline.setBounds(0,90,350,80);
        
        textFieldSearch = new JTextField("");
        textFieldSearch.setBounds(10,180,200,30);
        
        buttonSearch = new JButton("SEAERCH");
        buttonSearch.setBounds(220,180,110,30);
        buttonSearch.setFocusable(false);
        buttonSearch.addActionListener(this);
        
        panelListGroupChat = new JPanel();
        panelListGroupChat.setLayout(new GridLayout(20,1));
        
        for (int i = 0; i < 20; i++) {
        	Icon avatar = new ImageIcon("source/image/iconUserMenu.png");
        	Image image = ((ImageIcon) avatar).getImage(); // transform it 
        	Image newimg = image.getScaledInstance(48, 48,  java.awt.Image.SCALE_SMOOTH);
        	avatar = new ImageIcon(newimg);
        	JLabel avatarLabel = new JLabel(avatar);
        	avatarLabel.setBounds(0,10,50,50);
        	
        	JLabel labelGroupChatName = new JLabel("Group");
        	labelGroupChatName.setBounds(60,10,200,15);
        	labelGroupChatName.setFont(font4);
        	
        	JLabel labelGroupOnChat = new JLabel("You send a message");
        	labelGroupOnChat.setBounds(60,35,200,20);
        	labelGroupOnChat.setFont(font2);
        	
        	JPanel panelGroupChat = new JPanel();
        	panelGroupChat.setPreferredSize(new Dimension(300,80));
        	panelGroupChat.setLayout(null);
        	panelGroupChat.add(avatarLabel);
        	panelGroupChat.add(labelGroupChatName);
        	panelGroupChat.add(labelGroupOnChat);
        	
        	JButton groupChat = new JButton();
        	groupChat.add(panelGroupChat,JButton.CENTER);
        	groupChat.setPreferredSize(new Dimension(300,80));
        	groupChat.setOpaque(false);
        	groupChat.setContentAreaFilled(false);
        	panelListGroupChat.add(groupChat);
        }
        
        scrollPaneListGroupChat = new JScrollPane();
        scrollPaneListGroupChat.setPreferredSize(new Dimension(340,400));
        scrollPaneListGroupChat.setViewportView(panelListGroupChat);
        scrollPaneListGroupChat.setBounds(0,220,340,350);
        
        panelList.add(labelList);
        panelList.add(buttonNewChat);
        panelList.add(labelListFriendsOnline);
        panelList.add(scrollPaneListFriendOnline);
        panelList.add(textFieldSearch);
        panelList.add(buttonSearch);
        panelList.add(scrollPaneListGroupChat);
        
        panelChat = new JPanel();
        panelChat.setPreferredSize(new Dimension(596,600)); // Được sử dụng khi setSize đã có trong phần cha lớn.
        panelChat.setLayout(null);
        panelChat.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        
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
    	
    	labelGroupName = new JLabel("Group");
    	labelGroupName.setFont(font4);
    	labelGroupName.setBounds(60,10,200,20);
    	
    	labelGroupStatus = new JLabel("Active 23m ago");
    	labelGroupStatus.setFont(font2);
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
        panelGroupChat.setLayout(new GridLayout(20,1));
        
        for (int i = 0; i < 20; i++) {
        	JPanel panelMessage = new JPanel();
        	panelMessage.setPreferredSize(new Dimension(576,60));
        	panelMessage.setLayout(null);
        	
        	Icon avatarMessage = new ImageIcon("source/image/iconUserMenu.png");
        	Image imageMessage = ((ImageIcon) avatarMessage).getImage(); // transform it 
        	Image newimgMessage = imageMessage.getScaledInstance(24, 24,  java.awt.Image.SCALE_SMOOTH);
        	avatarMessage = new ImageIcon(newimgMessage);
        	
        	JLabel avatarLabel = new JLabel(avatar);
        	if (i%2==0) {
        		avatarLabel.setBounds(0,10,40,40);
        	}
        	else {
        		avatarLabel.setBounds(536,10,40,40);
        	}
        	
        	JLabel labelMessage = new JLabel("New message from user");
        	if (i%2==0) {
        		labelMessage.setBounds(50,10,200,40);
        	}
        	else {
        		labelMessage.setBounds(386,10,200,40);
        	}
        	panelMessage.add(avatarLabel);
        	panelMessage.add(labelMessage);
        	panelGroupChat.add(panelMessage);
        }
        scrollPaneGroupChat = new JScrollPane();
        scrollPaneGroupChat.setPreferredSize(new Dimension(340,460));
        scrollPaneGroupChat.setViewportView(panelGroupChat);
        scrollPaneGroupChat.setBounds(0,60,596,460);
        
        panelInputChat = new JPanel();
        panelInputChat.setLayout(null);
        
        textFieldInputChat = new JTextField("");
        textFieldInputChat.setBounds(0,20,450,30);
        
        buttonSend = new JButton("SEND");
        buttonSend.setBounds(460,20,120,30);
        buttonSend.setFocusable(false);
        buttonSend.addActionListener(this);
        
        panelInputChat.add(textFieldInputChat);
        panelInputChat.add(buttonSend);
        panelInputChat.setBounds(0,510,596,80);
        
    	panelChat.add(panelGroupName);
    	panelChat.add(scrollPaneGroupChat);
    	panelChat.add(panelInputChat);
    
        add(panelMenu, BorderLayout.WEST);
        add(panelList, BorderLayout.CENTER);
        add(panelChat, BorderLayout.EAST);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == buttonChatMenu) {
			
		}
		else if (e.getSource() == buttonFriendMenu) {
			this.dispose();
			try{
                new FriendListScreen();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
		}
		else if (e.getSource() == buttonSettingMenu) {
			try{
                new SettingScreen();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
		}
		else if (e.getSource() == buttonUserMenu) {
            try{
                new UserSettingScreen();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
		}
		else if (e.getSource() == buttonNewChat) {
			
		}
		else if (e.getSource() == buttonSearch) {
			
		}
		else if (e.getSource() == buttonGroupOption) {
			
		}
		else if (e.getSource() == buttonSend) {
			
		}
	}
	
}
