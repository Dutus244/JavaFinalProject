package admin_view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
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
		
		JButton userListButton = new JButton("Danh sách người dùng");
		userListButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				UserListScreen.main();
			}
		});
		userListButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		userListButton.setBounds(325, 100, 325, 74);
		contentPane.add(userListButton);
		
		JButton loginListButton = new JButton("Danh sách đăng nhập");
		loginListButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Danh sách đăng nhập");
				frame.dispose();
				LoginListScreen.main();
			}
		});
		loginListButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		loginListButton.setBounds(325, 216, 325, 74);
		contentPane.add(loginListButton);
		
		JButton groupChatListButton = new JButton("Danh sách nhóm chat");
		groupChatListButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Danh sách nhóm chat");
				frame.dispose();
				GroupChatListScreen.main();
			}
		});
		groupChatListButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		groupChatListButton.setBounds(325, 330, 325, 74);
		contentPane.add(groupChatListButton);
		
		JButton exitButton = new JButton("Thoát");
		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		exitButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		exitButton.setBounds(325, 444, 325, 74);
		contentPane.add(exitButton);
	}
}
