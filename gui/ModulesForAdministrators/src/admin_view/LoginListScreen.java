package admin_view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import java.awt.Font;
import javax.swing.table.DefaultTableModel;

import db.Connect_DB;

import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.SQLException;
import java.util.Vector;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class LoginListScreen extends JFrame {
	private static LoginListScreen frame;

	private JPanel contentPane;
	private JTable table;
	private DefaultTableModel tableModel;

	private JTextField searchTextField;
	private JButton btnSearch;

	Vector<Vector<Object>> data;
	String filter = "LoginTime";
	String order = "asc";
	String criteria = "Username";
	String keyword = "";

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
		Connect_DB db = Connect_DB.getInstance();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(100, 100, 800, 400);
		contentPane.add(scrollPane);

		try {
			data = db.getLogInList(filter, order);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		table = new JTable();
		scrollPane.setViewportView(table);
		String[] columnNames = { "Time", "Username", "Full name" };
		tableModel = new DefaultTableModel(columnNames, 0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				// all cells false
				return false;
			}
		};
		for (int i = 0; i < data.size(); i++) {
			tableModel.addRow(data.get(i));
		}
		table.setModel(tableModel);

		table.setFont(new Font("Tahoma", Font.PLAIN, 14));

		table.setEnabled(false);

		JComboBox<String> criteriaComboBox = new JComboBox<String>();
		criteriaComboBox.setFont(new Font("Tahoma", Font.PLAIN, 16));
		criteriaComboBox.setModel(new DefaultComboBoxModel<String>(new String[] { "By Username", "By Fullname" }));
		criteriaComboBox.setBounds(115, 60, 176, 30);
		criteriaComboBox.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					String criteriaSelect = criteriaComboBox.getSelectedItem().toString();

					if (criteriaSelect.equals("By Username")) {
						criteria = "UserName";
					} else if (criteriaSelect.equals("By Fullname")) {
						criteria = "FullName";
					}
				}
			}
		});
		contentPane.add(criteriaComboBox);

		searchTextField = new JTextField();
		searchTextField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		searchTextField.setBounds(301, 60, 447, 30);
		contentPane.add(searchTextField);
		searchTextField.setColumns(10);

		btnSearch = new JButton("Search");
		btnSearch.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnSearch.setBounds(758, 60, 123, 30);
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				keyword = searchTextField.getText();
				if (keyword.equals("") == false) {
					data.clear();
					tableModel.setRowCount(0);

					try {
						data = db.searchUserInLogInList(criteria, keyword, filter, order);
						for (int i = 0; i < data.size(); i++) {
							tableModel.addRow(data.get(i));
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		contentPane.add(btnSearch);

		JButton btnQuayLi = new JButton("Back");
		btnQuayLi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MenuScreen.main();
				frame.dispose();
			}
		});
		btnQuayLi.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnQuayLi.setBounds(10, 10, 115, 37);
		contentPane.add(btnQuayLi);

		// Close db when close window by X
		addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosing(java.awt.event.WindowEvent windowEvent) {
				db.close();
			}
		});
	}
}
