//package modulesforusers;
//
//import java.awt.EventQueue;
//
//import javax.swing.JFrame;
//import javax.swing.JPanel;
//import javax.swing.border.EmptyBorder;
//import java.awt.BorderLayout;
//import javax.swing.JTextField;
//import java.awt.Font;
//import javax.swing.JButton;
//import javax.swing.Icon;
//import javax.swing.JLabel;
//import com.jgoodies.forms.factories.DefaultComponentFactory;
//import java.awt.Color;
//import java.awt.Dimension;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.awt.event.WindowAdapter;
//import java.awt.event.WindowEvent;
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.net.Socket;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.List;
//
//import javax.swing.border.LineBorder;
//import javax.swing.ImageIcon;
//
//public class FriendRequest extends JFrame implements ActionListener {
//	JPanel panel_6;
//	JLabel lblNewJgoodiesLabel_numberInvitation;
//	private JPanel contentPane;
//	private JTextField textField;
////	boolean ThreadContinue = true;
//	JPanel panelRequest;
//	JButton buttonChatMenu;
//	JButton buttonFriendMenu;
//	JButton buttonSettingMenu;
//	JButton buttonUserMenu;
//	JButton btnFriendRequest;
//	JButton btnFriendList;
//	JButton btnGroup;
//	String username;
//	Socket client;
//	List<String> usernameRequest = new ArrayList<>();
//	public FriendRequest(String Username, Socket s) {
//		this.username = Username;
//		this.client = s;
//		
//		initialize(Username);
//		new ReadFriendRequest().start();
//	}
////	class ReadDatabase extends Thread{
////		public void run() {
////			while(ThreadContinue) {
////				
////			}
////		} 
////	}
//	Connection conn = null;
//	java.sql.Statement stmt;
//	ResultSet rs;
//	boolean isOpen = true;
//	class ReadFriendRequest extends Thread{
//		public void run() {
//			while(isOpen) {
//				try {
//					Class.forName("com.mysql.cj.jdbc.Driver");
//				} catch (ClassNotFoundException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}
//				try {
//					conn = DriverManager.getConnection(Main.DB_URL, Main.USER, Main.PASS);
//				} catch (SQLException e2) {
//					// TODO Auto-generated catch block
//					e2.printStackTrace();
//				}
//				try {
//					stmt = conn.createStatement();
//				} catch (SQLException e2) {
//					// TODO Auto-generated catch block
//					e2.printStackTrace();
//				}
//				
//
//				try {
//					usernameRequest.clear();
//					rs = ((java.sql.Statement)stmt).executeQuery("SELECT UserName FROM users where UserID in (select UserID FROM sendrequestfriendlistfriendlist where AddFriendID =  '"+ username +"')");
//				
//					while(rs.next()) {
//						usernameRequest.add(rs.getString("UserName"));
//					}
//					System.out.println(usernameRequest.size());
//					
//					JPanel panel_9 = new JPanel();
//					panel_9.setBounds(10, 94, 586, 41);
//					panel_6.add(panel_9);
//					JLabel lblNewJgoodiesLabel_13 = DefaultComponentFactory.getInstance().createLabel("Invitation("+usernameRequest.size()+")");
//					lblNewJgoodiesLabel_13.setFont(new Font("Times New Roman", Font.PLAIN, 18));
//					panel_9.add(lblNewJgoodiesLabel_13);
//					
////					for(int i =0; i<usernameRequest.size();i++ ) {
////						
////					}
//					validate();
//				} catch (SQLException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				try {
//					Thread.sleep(5000);
//				} catch (InterruptedException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//
//			}
//			
//		}
//	}
//	public void initialize(String Username) {
//		this.setTitle("FRIEND OPTION -" + Username);
//        this.setSize(1000,600);
//        getContentPane().setLayout(new BorderLayout());
//        this.setLocationRelativeTo(null);
//        this.setVisible(true);
//        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        this.setResizable(false);
//        
//		contentPane = new JPanel();
//		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
//
//		contentPane.setLayout(null);
//		
//		JPanel panel = new JPanel();
//		panel.setPreferredSize(new Dimension(54, 600));
//		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
//		panel.setBounds(0, 0, 54, 600);
//		contentPane.add(panel);
//		panel.setLayout(null);
//		panel.setBackground(new Color(220, 220, 220));
//		
//		Icon iconChatMenu = new ImageIcon("source/image/iconChatMenu.png");
//	    buttonChatMenu = new JButton(iconChatMenu);
//	    buttonChatMenu.setBounds(2,2,50,50);
//	    buttonChatMenu.setFocusable(false);
//	    buttonChatMenu.addActionListener(this);
//	    buttonChatMenu.setOpaque(false);
//	    buttonChatMenu.setContentAreaFilled(false);
//	      
//	    Icon iconFriendMenu = new ImageIcon("source/image/iconFriendMenu.png");
//	    buttonFriendMenu = new JButton(iconFriendMenu);
//	    buttonFriendMenu.setBounds(2,54,50,50);
//	    buttonFriendMenu.setFocusable(false);
//	    buttonFriendMenu.addActionListener(this);
//	    buttonFriendMenu.setOpaque(false);
//	    buttonFriendMenu.setContentAreaFilled(false);
//	        
//	    Icon iconSettingMenu = new ImageIcon("source/image/iconSettingMenu.png");
//	    buttonSettingMenu = new JButton(iconSettingMenu);
//	    buttonSettingMenu.setBounds(2,108,50,50);
//	    buttonSettingMenu.setFocusable(false);
//	    buttonSettingMenu.addActionListener(this);
//	    buttonSettingMenu.setOpaque(false);
//	    buttonSettingMenu.setContentAreaFilled(false);
//	        
//	    Icon iconUserMenu = new ImageIcon("source/image/iconUserMenu.png");
//	    buttonUserMenu = new JButton(iconUserMenu);
//	    buttonUserMenu.setBounds(2,162,50,50);
//	    buttonUserMenu.setFocusable(false);
//	    buttonUserMenu.addActionListener(this);
//	    buttonUserMenu.setOpaque(false);
//	    buttonUserMenu.setContentAreaFilled(false);
//	    
//	    panel.add(buttonChatMenu);
//        panel.add(buttonFriendMenu);
//        panel.add(buttonSettingMenu);
//        panel.add(buttonUserMenu);
//		
//		JPanel panel_1 = new JPanel();
//		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
//		panel_1.setBounds(54, 0, 337, 708);
//		contentPane.add(panel_1);
//		panel_1.setLayout(null);
//		
//		JPanel panel_3 = new JPanel();
//		panel_3.setBorder(new LineBorder(new Color(0, 0, 0)));
//		panel_3.setBounds(0, 0, 340, 81);
//		panel_1.add(panel_3);
//		panel_3.setLayout(null);
//		
//		JLabel lblNewJgoodiesLabel_4 = DefaultComponentFactory.getInstance().createLabel("    FRIENDS");
//		lblNewJgoodiesLabel_4.setFont(new Font("Times New Roman", Font.BOLD, 26));
//		lblNewJgoodiesLabel_4.setBounds(0, 0, 330, 81);
//		panel_3.add(lblNewJgoodiesLabel_4);
//		
//		JPanel panel_2 = new JPanel();
//		panel_2.setBounds(0, 79, 327, 63);
//		panel_1.add(panel_2);
//		panel_2.setLayout(null);
//		
//		textField = new JTextField();
//		textField.setBounds(10, 11, 202, 34);
//		panel_2.add(textField);
//		textField.setColumns(10);
//		
//		JButton btnNewButton = new JButton("SEARCH");
//		btnNewButton.setBounds(222, 11, 108, 39);
//		panel_2.add(btnNewButton);
//		btnNewButton.setFont(new Font("Times New Roman", Font.BOLD, 18));
//		
//		btnFriendRequest = new JButton("Friend Request");
//		btnFriendRequest.setFont(new Font("Times New Roman", Font.PLAIN, 24));
//		btnFriendRequest.setIcon(new ImageIcon("D:\\eclipse-workspace\\ui\\Source\\Image\\add-user.png"));
//		
//		btnFriendRequest.setBounds(0, 153, 327, 75);
//		panel_1.add(btnFriendRequest);
//		
//		btnFriendList = new JButton("Friend List");
//		btnFriendList.setIcon(new ImageIcon("D:\\eclipse-workspace\\ui\\Source\\Image\\friends.png"));
//		btnFriendList.setFont(new Font("Times New Roman", Font.PLAIN, 24));
//		btnFriendList.setBounds(0, 257, 327, 75);
//		btnFriendList.addActionListener(this);
//		panel_1.add(btnFriendList);
//		
//		btnGroup = new JButton("Group");
//		btnGroup.setIcon(new ImageIcon("D:\\eclipse-workspace\\ui\\Source\\Image\\group.png"));
//		btnGroup.setFont(new Font("Times New Roman", Font.PLAIN, 24));
//		btnGroup.setBounds(0, 362, 327, 75);
//		panel_1.add(btnGroup);
//		 
//		panel_6 = new JPanel();
//		panel_6.setBounds(391, 0, 596, 721);
//		contentPane.add(panel_6);
//		panel_6.setLayout(null);
//		
//		JPanel panel_8 = new JPanel();
//		panel_8.setBorder(new LineBorder(new Color(0, 0, 0)));
//		panel_8.setBounds(0, 0, 596, 80);
//		panel_6.add(panel_8);
//		panel_8.setLayout(null);
//		
//		JLabel lblNewJgoodiesLabel_11 = DefaultComponentFactory.getInstance().createLabel("");
//		lblNewJgoodiesLabel_11.setIcon(new ImageIcon("D:\\eclipse-workspace\\ui\\Source\\Image\\friends.png"));
//		lblNewJgoodiesLabel_11.setBounds(24, 0, 89, 82);
//		panel_8.add(lblNewJgoodiesLabel_11);
//		JLabel lblNewJgoodiesLabel_12 = DefaultComponentFactory.getInstance().createLabel("Friend's Request");
//		lblNewJgoodiesLabel_12.setFont(new Font("Times New Roman", Font.BOLD, 30));
//		lblNewJgoodiesLabel_12.setBounds(101, 11, 260, 60);
//		panel_8.add(lblNewJgoodiesLabel_12);
//		
//		
//		
//		getContentPane().add(contentPane);
//		
//		
//		
//		
//		
//	}
//	
//	@Override
//	public void actionPerformed(ActionEvent e) {
//		// TODO Auto-generated method stub
//		if (e.getSource() == buttonChatMenu) {
//			isOpen = false;
//			this.dispose();
//            try{
//                new HomeScreen(username, client);
//            } catch (Exception ex) {
//                ex.printStackTrace();
//            }
//		}
//		else if (e.getSource() == buttonFriendMenu) {
//			isOpen = false;
//		}
//		else if (e.getSource() == buttonSettingMenu) {
//			try{
//				isOpen = false;
////                new SettingScreen();
//            } catch (Exception ex) {
//                ex.printStackTrace();
//            }
//		}
//		else if (e.getSource() == buttonUserMenu) {
//            try{
//            	isOpen = false;
////                new UserSettingScreen();
//            } catch (Exception ex) {
//                ex.printStackTrace();
//            }
//		}
//		else if (e.getSource() == btnFriendList) {
//            try{
//            	isOpen = false;
//                new FriendList(username, client);
//                this.dispose();
//            } catch (Exception ex) {
//                ex.printStackTrace();
//            }
//		}
//		
//		else if (e.getSource() == btnGroup) {
//			isOpen = false;
//		}
//		
//	}
//}
