package admin_view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;

import db.Connect_DB;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.Vector;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class GroupChatListScreen extends JFrame {
	private static GroupChatListScreen frame;

	private JPanel contentPane;
	
	private JTable table;
	private DefaultTableModel tableModel;
	
	private JTable table_1;
	private DefaultTableModel tableModel_1;
	
	private JTable table_2;
	private DefaultTableModel tableModel_2;
	
	String groupid = "";
	
	Vector<Vector<Object>> data;
	String filter = "CreateTime";
	String order = "asc";
	
	Vector<Vector<Object>> data_1 = new Vector<Vector<Object>>();
	
	Vector<Vector<Object>> data_2 = new Vector<Vector<Object>>();

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
		Connect_DB db = Connect_DB.getInstance();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 144, 483, 387);
		contentPane.add(scrollPane);
		
		try {
			data = db.getAllGroupChat(filter, order);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		table = new JTable();
		scrollPane.setViewportView(table);
		String[] columnNames = { "Nhóm ID", "Tên nhóm", "Ngày lập" };
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
               //all cells false
               return false;
            }
        };
        for (int i = 0; i < data.size(); i++) {
        	tableModel.addRow(data.get(i));
        }
        table.setModel(tableModel);
        
		table.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		table.addMouseListener(new MouseListener() {
	        @Override
	        public void mouseReleased(MouseEvent e) {
	        }
	        @Override
	        public void mousePressed(MouseEvent e) {
	            groupid = (String) table.getValueAt(table.getSelectedRow() , 0);
	            System.out.println(groupid);
	            
	            data_1.clear();
	        	tableModel_1.setRowCount(0);
	        	data_2.clear();
	        	tableModel_2.setRowCount(0);
	            
	            //
	        	
	        	try {
					data_1 = db.getGroupChatAdmin(groupid, filter, order);
		            data_2 = db.getGroupChatMember(groupid, filter, order);
					
		            for (int i = 0; i < data_1.size(); i++) {
		            	tableModel_1.addRow(data_1.get(i));
		            }
		            for (int i = 0; i < data_2.size(); i++) {
		            	tableModel_2.addRow(data_2.get(i));
		            }
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	            
	        }
	        @Override
	        public void mouseExited(MouseEvent e) {
	        }
	        @Override
	        public void mouseEntered(MouseEvent e) {
	        }
	        @Override
	        public void mouseClicked(MouseEvent e) {
	        }
	    });
		table.setEnabled(false);
		
		//
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(503, 144, 473, 165);
		contentPane.add(scrollPane_1);
		
		table_1 = new JTable();
		scrollPane_1.setViewportView(table_1);
		String[] columnNames_1 = { "Username", "Fullname" };
        tableModel_1 = new DefaultTableModel(columnNames_1, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
               //all cells false
               return false;
            }
        };
        for (int i = 0; i < data_1.size(); i++) {
        	tableModel_1.addRow(data_1.get(i));
        }
        table_1.setModel(tableModel_1);
        
		table_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		table_1.setEnabled(false);
		
		//
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(503, 366, 473, 165);
		contentPane.add(scrollPane_2);
		
		table_2 = new JTable();
		scrollPane_2.setViewportView(table_2);
		String[] columnNames_2 = { "Username", "Fullname" };
        tableModel_2 = new DefaultTableModel(columnNames_2, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
               //all cells false
               return false;
            }
        };
        for (int i = 0; i < data_2.size(); i++) {
        	tableModel_2.addRow(data_2.get(i));
        }
        table_2.setModel(tableModel_2);
        
		table_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		table_2.setEnabled(false);
		
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
		
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 20));
		comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"Theo tên", "Theo thời gian lập"}));
		comboBox.setBounds(10, 97, 187, 37);
		contentPane.add(comboBox);
		
		JButton btnNewButton = new JButton("Làm mới");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton.setBounds(378, 97, 115, 37);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
	        	data.clear();
	        	tableModel.setRowCount(0);
	        	data_1.clear();
	        	tableModel_1.setRowCount(0);
	        	data_2.clear();
	        	tableModel_2.setRowCount(0);
	        	
	        	try {
	    			data = db.getAllGroupChat(filter, order);
		            for (int i = 0; i < data.size(); i++) {
		            	tableModel.addRow(data.get(i));
		            }
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		contentPane.add(btnNewButton);
		
		
		
		JButton btnQuayLi = new JButton("Quay lại");
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
