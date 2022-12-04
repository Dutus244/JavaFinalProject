package admin_view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import java.awt.Font;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class LoginListScreen extends JFrame {
	private static LoginListScreen frame;

	private JPanel contentPane;
	private JTable table;
	private JTable table_2;
	private JTable table_1;
	private JTable table_3;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new LoginListScreen();
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
	public LoginListScreen() {
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
				"Th\u1EDDi gian", "T\u00EAn \u0111\u0103ng nh\u1EADp", "H\u1ECD t\u00EAn"
			}
		));
		table_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		table_3.setEnabled(false);
		
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 16));
		comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"Theo tên đăng nhập", "Theo họ tên"}));
		comboBox.setBounds(115, 60, 176, 30);
		contentPane.add(comboBox);
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textField.setBounds(301, 60, 447, 30);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("Tìm");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton.setBounds(758, 60, 123, 30);
		contentPane.add(btnNewButton);
		
		JButton btnQuayLi = new JButton("Quay lại");
		btnQuayLi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				MenuScreen.main();
			}
		});
		btnQuayLi.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnQuayLi.setBounds(10, 10, 115, 37);
		contentPane.add(btnQuayLi);
		
		
	}
}
