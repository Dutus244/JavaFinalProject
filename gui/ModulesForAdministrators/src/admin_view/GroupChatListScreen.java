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
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class GroupChatListScreen extends JFrame {
	private static GroupChatListScreen frame;

	private JPanel contentPane;
	private JTable table;
	private JTable table_2;
	private JTable table_1;

	/**
	 * Launch the application.
	 */
	public static void main() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new GroupChatListScreen();
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
	public GroupChatListScreen() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null},
				{null},
				{null},
				{null},
				{null},
				{null},
				{null},
				{null},
				{null},
				{null},
				{null},
				{null},
				{null},
				{null},
				{null},
				{null},
				{null},
				{null},
				{null},
				{null},
				{null},
				{null},
				{null},
				{null},
				{null},
				{null},
				{null},
				{null},
				{null},
				{null},
				{null},
				{null},
				{null},
				{null},
				{null},
				{null},
				{null},
				{null},
				{null},
				{null},
				{null},
			},
			new String[] {
				"T\u00EAn nh\u00F3m"
			}
		));
		table.setBounds(10, 144, 483, 387);
		contentPane.add(table);
		
		table_2 = new JTable();
		table_2.setModel(new DefaultTableModel(
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
			},
			new String[] {
				"New column", "New column", "New column"
			}
		));
		table_2.setBounds(503, 366, 473, 165);
		contentPane.add(table_2);
		
		JLabel lblNewLabel = new JLabel("Danh sách nhóm");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 26));
		lblNewLabel.setBounds(10, 50, 212, 37);
		contentPane.add(lblNewLabel);
		
		JLabel lblDanhSchAdmin = new JLabel("Danh sách admin");
		lblDanhSchAdmin.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblDanhSchAdmin.setBounds(503, 97, 212, 37);
		contentPane.add(lblDanhSchAdmin);
		
		JLabel lblDanhSchMember = new JLabel("Danh sách member");
		lblDanhSchMember.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblDanhSchMember.setBounds(503, 319, 212, 37);
		contentPane.add(lblDanhSchMember);
		
		table_1 = new JTable();
		table_1.setModel(new DefaultTableModel(
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
			},
			new String[] {
				"New column", "New column", "New column"
			}
		));
		table_1.setBounds(503, 144, 473, 165);
		contentPane.add(table_1);
		
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 20));
		comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"Theo tên", "Theo thời gian lập"}));
		comboBox.setBounds(10, 97, 187, 37);
		contentPane.add(comboBox);
		
		JButton btnNewButton = new JButton("Làm mới");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton.setBounds(378, 97, 115, 37);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Làm mới");
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton_1.setBounds(861, 97, 115, 37);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Làm mới");
		btnNewButton_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton_2.setBounds(861, 319, 115, 37);
		contentPane.add(btnNewButton_2);
		
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
