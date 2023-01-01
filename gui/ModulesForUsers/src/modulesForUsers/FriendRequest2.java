package modulesForUsers;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.JLabel;
import com.jgoodies.forms.factories.DefaultComponentFactory;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;

public class FriendRequest2 extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField textField;
	
	JButton buttonChatMenu;
	JButton buttonFriendMenu;
	JButton buttonSettingMenu;
	JButton buttonUserMenu;

	public FriendRequest2() {
		this.setTitle("CHAT APPLICATION");
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

	    JPanel panelMenu = new JPanel();
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
		panelTitle.setBounds(0, 0, 349, 81);
		panelOption.add(panelTitle);
		panelTitle.setLayout(null);
		
		JLabel lbTitle = new JLabel("    FRIENDS");
		lbTitle.setFont(new Font("Times New Roman", Font.BOLD, 26));
		lbTitle.setBounds(0, 0, 342, 81);
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

		
		JButton btnFriendRequest = new JButton("Friend Request");
		btnFriendRequest.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		btnFriendRequest.setIcon(new ImageIcon("D:\\eclipse-workspace\\ui\\Source\\Image\\add-user.png"));
		btnFriendRequest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnFriendRequest.setBounds(0, 153, 327, 75);
		panel_1.add(btnFriendRequest);
		
		JButton btnFriendList = new JButton("Friend List");
		btnFriendList.setIcon(new ImageIcon("D:\\eclipse-workspace\\ui\\Source\\Image\\friends.png"));
		btnFriendList.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		btnFriendList.setBounds(0, 257, 327, 75);
		panel_1.add(btnFriendList);
		
		JButton btnGroup = new JButton("Group");
		btnGroup.setIcon(new ImageIcon("D:\\eclipse-workspace\\ui\\Source\\Image\\group.png"));
		btnGroup.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		btnGroup.setBounds(0, 362, 327, 75);

		panel_1.add(btnGroup);
		
		JPanel panel_6 = new JPanel();
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
		
		JLabel lblNewJgoodiesLabel_12 = DefaultComponentFactory.getInstance().createLabel("Friend's Request");
		lblNewJgoodiesLabel_12.setFont(new Font("Times New Roman", Font.BOLD, 30));
		lblNewJgoodiesLabel_12.setBounds(101, 11, 260, 60);
		panel_8.add(lblNewJgoodiesLabel_12);
		
		JPanel panel_9 = new JPanel();
		panel_9.setBounds(10, 94, 586, 41);
		panel_6.add(panel_9);
		
		JLabel lblNewJgoodiesLabel_13 = DefaultComponentFactory.getInstance().createLabel("Invitation(50)");
		lblNewJgoodiesLabel_13.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		panel_9.add(lblNewJgoodiesLabel_13);
		
		JPanel panel_10 = new JPanel();
		panel_10.setBounds(0, 134, 596, 438);
		panel_6.add(panel_10);
		panel_10.setLayout(null);
		
		JPanel panel_11 = new JPanel();
		panel_11.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_11.setBounds(10, 11, 576, 94);
		panel_10.add(panel_11);
		panel_11.setLayout(null);
		
		JLabel lblNewJgoodiesLabel_14 = DefaultComponentFactory.getInstance().createLabel("");
		lblNewJgoodiesLabel_14.setIcon(new ImageIcon("D:\\eclipse-workspace\\ui\\Source\\Image\\iconUserMenu.png"));
		lblNewJgoodiesLabel_14.setBounds(10, 11, 57, 72);
		panel_11.add(lblNewJgoodiesLabel_14);
		
		JLabel lblNewJgoodiesLabel_15 = DefaultComponentFactory.getInstance().createLabel("User's Name");
		lblNewJgoodiesLabel_15.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNewJgoodiesLabel_15.setBounds(77, 22, 177, 49);
		panel_11.add(lblNewJgoodiesLabel_15);
		
		JButton btnNewButton_1 = new JButton("ACCEPT");
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnNewButton_1.setBounds(430, 11, 136, 29);
		panel_11.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("CHAT");
		btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnNewButton_2.setBounds(430, 51, 136, 32);
		panel_11.add(btnNewButton_2);
		
		JPanel panel_12 = new JPanel();
		panel_12.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_12.setBounds(10, 116, 576, 94);
		panel_10.add(panel_12);
		panel_12.setLayout(null);
		
		JLabel lblNewJgoodiesLabel_16 = DefaultComponentFactory.getInstance().createLabel("");
		lblNewJgoodiesLabel_16.setIcon(new ImageIcon("D:\\eclipse-workspace\\ui\\Source\\Image\\iconUserMenu.png"));
		lblNewJgoodiesLabel_16.setBounds(10, 11, 48, 72);
		panel_12.add(lblNewJgoodiesLabel_16);
		
		JLabel lblNewJgoodiesLabel_17 = DefaultComponentFactory.getInstance().createLabel("User's Name");
		lblNewJgoodiesLabel_17.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNewJgoodiesLabel_17.setBounds(78, 11, 110, 72);
		panel_12.add(lblNewJgoodiesLabel_17);
		
		JButton btnNewButton_3 = new JButton("ACCEPT ");
		btnNewButton_3.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnNewButton_3.setBounds(434, 11, 132, 29);
		panel_12.add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("CHAT");
		btnNewButton_4.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnNewButton_4.setBounds(434, 51, 132, 32);
		panel_12.add(btnNewButton_4);
		
		JPanel panel_13 = new JPanel();
		panel_13.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_13.setBounds(10, 221, 576, 94);
		panel_10.add(panel_13);
		panel_13.setLayout(null);
		
		JLabel lblNewJgoodiesLabel_18 = DefaultComponentFactory.getInstance().createLabel("");
		lblNewJgoodiesLabel_18.setIcon(new ImageIcon("D:\\eclipse-workspace\\ui\\Source\\Image\\iconUserMenu.png"));
		lblNewJgoodiesLabel_18.setBounds(10, 11, 48, 72);
		panel_13.add(lblNewJgoodiesLabel_18);
		
		JLabel lblNewJgoodiesLabel_19 = DefaultComponentFactory.getInstance().createLabel("User's Name");
		lblNewJgoodiesLabel_19.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNewJgoodiesLabel_19.setBounds(78, 0, 121, 94);
		panel_13.add(lblNewJgoodiesLabel_19);
		
		JButton btnNewButton_5 = new JButton("ACCEPT");
		btnNewButton_5.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnNewButton_5.setBounds(435, 11, 131, 29);
		panel_13.add(btnNewButton_5);
		
		JButton btnNewButton_6 = new JButton("CHAT");
		btnNewButton_6.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnNewButton_6.setBounds(435, 51, 131, 32);
		panel_13.add(btnNewButton_6);
		
		JPanel panel_14 = new JPanel();
		panel_14.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_14.setBounds(10, 326, 576, 101);
		panel_10.add(panel_14);
		panel_14.setLayout(null);
		
		JLabel lblNewJgoodiesLabel_20 = DefaultComponentFactory.getInstance().createLabel("");
		lblNewJgoodiesLabel_20.setIcon(new ImageIcon("D:\\eclipse-workspace\\ui\\Source\\Image\\iconUserMenu.png"));
		lblNewJgoodiesLabel_20.setBounds(10, 11, 48, 79);
		panel_14.add(lblNewJgoodiesLabel_20);
		
		JLabel lblNewJgoodiesLabel_21 = DefaultComponentFactory.getInstance().createLabel("User's Name");
		lblNewJgoodiesLabel_21.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNewJgoodiesLabel_21.setBounds(80, 0, 139, 101);
		panel_14.add(lblNewJgoodiesLabel_21);
		
		JButton btnNewButton_7 = new JButton("ACCEPT");
		btnNewButton_7.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnNewButton_7.setBounds(437, 11, 129, 29);
		panel_14.add(btnNewButton_7);
		
		JButton btnNewButton_8 = new JButton("CHAT");
		btnNewButton_8.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnNewButton_8.setBounds(437, 51, 129, 29);
		panel_14.add(btnNewButton_8);
		
		getContentPane().add(contentPane);

		panelOption.add(btnGroup);
		
		
        JPanel panelView = new JPanel();
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
        
        JLabel lbInvitation = new JLabel("Invitation(50)");
        lbInvitation.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        panelViewAdvance.add(lbInvitation);
        
        JPanel panelListUser = new JPanel();
        panelListUser.setLayout(null);
        panelListUser.setBounds(0, 134, 582, 429);
        panelView.add(panelListUser);
        
        JPanel panelFriendInfo = new JPanel();
        panelFriendInfo.setLayout(null);
        panelFriendInfo.setBorder(new LineBorder(new Color(0, 0, 0)));
        panelFriendInfo.setBounds(10, 11, 576, 94);
        panelListUser.add(panelFriendInfo);
        
        JLabel lbFriendUsername = new JLabel("User's Name");
        lbFriendUsername.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lbFriendUsername.setBounds(10, 21, 177, 49);
        panelFriendInfo.add(lbFriendUsername);
        
        JButton btnAccept = new JButton("ACCEPT");
        btnAccept.setFont(new Font("Tahoma", Font.BOLD, 16));
        btnAccept.setBounds(430, 11, 136, 32);
        panelFriendInfo.add(btnAccept);
        
        JButton btnChat = new JButton("CHAT");
        btnChat.setFont(new Font("Tahoma", Font.BOLD, 16));
        btnChat.setBounds(430, 51, 136, 32);
        panelFriendInfo.add(btnChat);
        
        
        getContentPane().add(panelMenu, BorderLayout.WEST);
        getContentPane().add(panelOption, BorderLayout.CENTER);
        getContentPane().add(panelView, BorderLayout.EAST);

//		getContentPane().add(contentPane);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == buttonChatMenu) {
			this.dispose();
			try{
                new HomeScreen();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
		}
		else if (e.getSource() == buttonFriendMenu) {
			
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
	}
}
