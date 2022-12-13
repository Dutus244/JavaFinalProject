package admin_view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import db.Connect_DB;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class MenuScreen extends JFrame {
	private static MenuScreen frame;
	
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new MenuScreen();
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MenuScreen() {
		Connect_DB db = Connect_DB.getInstance();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel headerLabel = new JLabel("ADMIN");
		headerLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		headerLabel.setBounds(450, 25, 74, 34);
		contentPane.add(headerLabel);
		
		JButton userListButton = new JButton("User List");
		userListButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserListScreen.main();
				frame.dispose();
			}
		});
		userListButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		userListButton.setBounds(325, 100, 325, 74);
		contentPane.add(userListButton);
		
		JButton loginListButton = new JButton("Login List");
		loginListButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginListScreen.main();
				frame.dispose();
			}
		});
		loginListButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		loginListButton.setBounds(325, 216, 325, 74);
		contentPane.add(loginListButton);
		
		JButton groupChatListButton = new JButton("Chat Group List");
		groupChatListButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GroupChatListScreen.main();
				frame.dispose();
			}
		});
		groupChatListButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		groupChatListButton.setBounds(325, 330, 325, 74);
		contentPane.add(groupChatListButton);
		
		JButton exitButton = new JButton("Exit");
		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				db.close();
				System.exit(0);
			}
		});
		exitButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		exitButton.setBounds(325, 444, 325, 74);
		contentPane.add(exitButton);
		
		// Close db when close window by X
		addWindowListener(new java.awt.event.WindowAdapter() {
		    @Override
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		    	db.close();
		    }
		});
	}
}
