package modulesforusers;

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
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;

public class FriendRequest extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField textField;
	
	JButton buttonChatMenu;
	JButton buttonFriendMenu;
	JButton buttonSettingMenu;
	JButton buttonUserMenu;

	public FriendRequest() {
		this.setTitle("CHAT APPLICATION");
        this.setSize(1000,600);
        this.setLayout(new BorderLayout());
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
		lblNewJgoodiesLabel_4.setBounds(0, 0, 340, 81);
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
		
		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_4.setBounds(0, 153, 327, 81);
		panel_1.add(panel_4);
		panel_4.setLayout(null);
		
		JLabel lblNewJgoodiesLabel_5 = DefaultComponentFactory.getInstance().createLabel("");
		lblNewJgoodiesLabel_5.setIcon(new ImageIcon("D:\\eclipse-workspace\\ui\\Source\\Image\\add-user.png"));
		lblNewJgoodiesLabel_5.setBounds(10, 0, 64, 84);
		panel_4.add(lblNewJgoodiesLabel_5);
		
		JLabel lblNewJgoodiesLabel_6 = DefaultComponentFactory.getInstance().createLabel("Friend Request");
		lblNewJgoodiesLabel_6.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		lblNewJgoodiesLabel_6.setBounds(119, 11, 228, 47);
		panel_4.add(lblNewJgoodiesLabel_6);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_5.setBounds(0, 251, 327, 81);
		panel_1.add(panel_5);
		panel_5.setLayout(null);
		
		JLabel lblNewJgoodiesLabel_7 = DefaultComponentFactory.getInstance().createLabel("");
		lblNewJgoodiesLabel_7.setIcon(new ImageIcon("D:\\eclipse-workspace\\ui\\Source\\Image\\friends.png"));
		lblNewJgoodiesLabel_7.setBounds(10, 0, 96, 81);
		panel_5.add(lblNewJgoodiesLabel_7);
		
		JLabel lblNewJgoodiesLabel_8 = DefaultComponentFactory.getInstance().createLabel("My Friend");
		lblNewJgoodiesLabel_8.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		lblNewJgoodiesLabel_8.setBounds(116, 0, 214, 81);
		panel_5.add(lblNewJgoodiesLabel_8);
		
		JPanel panel_7 = new JPanel();
		panel_7.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_7.setBounds(0, 343, 327, 81);
		panel_1.add(panel_7);
		panel_7.setLayout(null);
		
		JLabel lblNewJgoodiesLabel_9 = DefaultComponentFactory.getInstance().createLabel("GROUP");
		lblNewJgoodiesLabel_9.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		lblNewJgoodiesLabel_9.setBounds(122, 0, 208, 81);
		panel_7.add(lblNewJgoodiesLabel_9);
		
		JLabel lblNewJgoodiesLabel_10 = DefaultComponentFactory.getInstance().createLabel("");
		lblNewJgoodiesLabel_10.setIcon(new ImageIcon("D:\\eclipse-workspace\\ui\\Source\\Image\\group.png"));
		lblNewJgoodiesLabel_10.setBounds(10, 0, 96, 81);
		panel_7.add(lblNewJgoodiesLabel_10);
		
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
		lblNewJgoodiesLabel_14.setBounds(10, 11, 96, 72);
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
		lblNewJgoodiesLabel_16.setBounds(10, 11, 96, 72);
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
		lblNewJgoodiesLabel_18.setBounds(10, 11, 96, 72);
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
		lblNewJgoodiesLabel_20.setBounds(10, 11, 96, 79);
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
		
		add(contentPane);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
//		if (e.getSource() == buttonChatMenu) {
//			this.dispose();
//			try{
//                new HomeScreen();
//            } catch (Exception ex) {
//                ex.printStackTrace();
//            }
//		}
//		else if (e.getSource() == buttonFriendMenu) {
//			
//		}
//		else if (e.getSource() == buttonSettingMenu) {
//			try{
//                new SettingScreen();
//            } catch (Exception ex) {
//                ex.printStackTrace();
//            }
//		}
//		else if (e.getSource() == buttonUserMenu) {
//            try{
//                new UserSettingScreen();
//            } catch (Exception ex) {
//                ex.printStackTrace();
//            }
//		}
	}
}
