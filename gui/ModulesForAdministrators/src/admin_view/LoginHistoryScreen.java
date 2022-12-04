package admin_view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JTable;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LoginHistoryScreen extends JFrame {
	private static LoginHistoryScreen frame;

	private JPanel contentPane;
	private JTable table;
	private JTable table_2;
	private JTable table_1;
	private JTable table_3;

	/**
	 * Launch the application.
	 */
	public static void main() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new LoginHistoryScreen();
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
	public LoginHistoryScreen() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(100, 100, 800, 400);
		contentPane.add(scrollPane);
		
		table_3 = new JTable();
		scrollPane.setViewportView(table_3);
		table_3.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
			},
			new String[] {
				"Gi\u1EDD", "Ng\u00E0y", "T\u00ECnh tr\u1EA1ng"
			}
		));
		table_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		table_3.setEnabled(false);
		
		JButton btnQuayLi = new JButton("Quay lại");
		btnQuayLi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				UserListScreen.main();
			}
		});
		btnQuayLi.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnQuayLi.setBounds(10, 10, 115, 37);
		contentPane.add(btnQuayLi);
		
		JButton btnNewButton = new JButton("Làm mới");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton.setBounds(785, 53, 115, 37);
		contentPane.add(btnNewButton);
		
		
	}
}
