package admin_view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuItem;
import javax.swing.JTable;
import java.awt.Font;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class FriendListScreen extends JFrame {
	private static FriendListScreen frame;

	private JPanel contentPane;
	private JTable table;
	private JTable table_2;
	private JTable table_1;
	private JTable table_3;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new FriendListScreen();
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
	public FriendListScreen() {
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
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
			},
			new String[] {
				"T\u00EAn \u0111\u0103ng nh\u1EADp", "H\u1ECD t\u00EAn", "\u0110\u1ECBa ch\u1EC9", "Ng\u00E0y sinh", "Gi\u1EDBi t\u00EDnh ", "Email"
			}
		));
		table_3.getColumnModel().getColumn(3).setPreferredWidth(40);
		table_3.getColumnModel().getColumn(4).setPreferredWidth(20);
		table_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		table_3.setEnabled(false);
		
		JPopupMenu popupMenu = new JPopupMenu();
		addPopup(table_3, popupMenu);
		
		JMenuItem mntmNewMenuItem_7 = new JMenuItem("New menu item");
		popupMenu.add(mntmNewMenuItem_7);
		
		JMenuItem mntmNewMenuItem_12 = new JMenuItem("New menu item");
		popupMenu.add(mntmNewMenuItem_12);
		
		JComboBox<String> comboBox_1 = new JComboBox<String>();
		comboBox_1.setModel(new DefaultComboBoxModel<String>(new String[] {"Theo tên đăng nhập", "Theo họ tên"}));
		comboBox_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		comboBox_1.setBounds(115, 60, 176, 30);
		contentPane.add(comboBox_1);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textField_1.setColumns(10);
		textField_1.setBounds(301, 60, 447, 30);
		contentPane.add(textField_1);
		
		JButton btnNewButton_1 = new JButton("Tìm");
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton_1.setBounds(758, 60, 123, 30);
		contentPane.add(btnNewButton_1);
		
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
		
		
	}
	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}
