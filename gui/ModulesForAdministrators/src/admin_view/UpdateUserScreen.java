package admin_view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JTextFieldDateEditor;

import db.Connect_DB;

@SuppressWarnings("serial")
public class UpdateUserScreen extends JFrame {
	private static UpdateUserScreen frame = null;

	private JPanel contentPane;
	private JTextField usernameTextField;
	private JTextField fullnameTextField;
	private JTextField addrTextField;
	private JDateChooser dateChooser;
	private JTextField emailTextField;
	private JComboBox<String> sexComboBox;

	/**
	 * Launch the application.
	 */
	public static void main(Vector<String> info, UserListScreen ulFrame) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new UpdateUserScreen(info, ulFrame);
					frame.setResizable(false);
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
	public UpdateUserScreen(Vector<String> info, UserListScreen ulFrame) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 400, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel headerLabel = new JLabel("Update user's information");
		headerLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		headerLabel.setBounds(86, 10, 211, 40);
		contentPane.add(headerLabel);
		
		JLabel usernameLabel = new JLabel("Username");
		usernameLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		usernameLabel.setBounds(30, 50, 84, 33);
		contentPane.add(usernameLabel);
		
		JLabel fullnameLabel = new JLabel("Fullname");
		fullnameLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		fullnameLabel.setBounds(30, 90, 84, 33);
		contentPane.add(fullnameLabel);
		
		JLabel addrLabel = new JLabel("Address");
		addrLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		addrLabel.setBounds(30, 130, 84, 33);
		contentPane.add(addrLabel);
		
		JLabel dobLabel = new JLabel("Date of Birth");
		dobLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		dobLabel.setBounds(30, 170, 84, 33);
		contentPane.add(dobLabel);
		
		JLabel sexLabel = new JLabel("Sex");
		sexLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		sexLabel.setBounds(30, 210, 84, 33);
		contentPane.add(sexLabel);
		
		JLabel emailLabel = new JLabel("Email");
		emailLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		emailLabel.setBounds(30, 250, 84, 33);
		contentPane.add(emailLabel);
		
		JButton okButton = new JButton("Save");
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Vector<String> val = new Vector<String>();
				
				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				Date dob = dateChooser.getDate();
				
				val.add(fullnameTextField.getText());
				val.add(addrTextField.getText());
				val.add(dateFormat.format(dob));
				val.add(sexComboBox.getSelectedItem().toString());
				val.add(emailTextField.getText());
				
				Connect_DB db = Connect_DB.getInstance();
				try {
					db.updateUser(info.get(0), val);
					ulFrame.refresh();
					JOptionPane.showMessageDialog(frame,
			                "Update successfully",
			                "Message",
			                JOptionPane.INFORMATION_MESSAGE);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					if (e1.getMessage().indexOf("Email") != -1) {
						JOptionPane.showMessageDialog(frame,
				                "Email already exists",
				                "Error",
				                JOptionPane.ERROR_MESSAGE);
					}
//					e1.printStackTrace();
				}
			}
		});
		okButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		okButton.setBounds(73, 320, 90, 35);
		contentPane.add(okButton);
		
		JButton cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		cancelButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		cancelButton.setBounds(236, 320, 90, 35);
		contentPane.add(cancelButton);
		
		usernameTextField = new JTextField();
		usernameTextField.setBounds(130, 55, 220, 25);
		usernameTextField.setColumns(10);
		usernameTextField.setText(info.get(0));
		usernameTextField.setEditable(false);
		contentPane.add(usernameTextField);
		
		fullnameTextField = new JTextField();
		fullnameTextField.setColumns(10);
		fullnameTextField.setBounds(130, 95, 220, 25);
		fullnameTextField.setText(info.get(1));
		contentPane.add(fullnameTextField);
		
		addrTextField = new JTextField();
		addrTextField.setColumns(10);
		addrTextField.setBounds(130, 135, 220, 25);
		addrTextField.setText(info.get(2));
		contentPane.add(addrTextField);
		
		emailTextField = new JTextField();
		emailTextField.setColumns(10);
		emailTextField.setBounds(130, 255, 220, 25);
		emailTextField.setText(info.get(5));
		contentPane.add(emailTextField);
		
		sexComboBox = new JComboBox<String>();
		sexComboBox.setToolTipText("");
		sexComboBox.setFont(new Font("Tahoma", Font.PLAIN, 14));
		sexComboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"Male", "Female"}));
		sexComboBox.setBounds(130, 215, 220, 25);
		sexComboBox.getModel().setSelectedItem(info.get(4));
		contentPane.add(sexComboBox);
		
		dateChooser = new JDateChooser();
		JTextFieldDateEditor editor = (JTextFieldDateEditor) dateChooser.getDateEditor();
		editor.setEditable(false);
		dateChooser.setDateFormatString("yyyy/MM/dd");
		dateChooser.setBounds(130, 175, 220, 25);
		Date date;
		try {
			date = new SimpleDateFormat("yyyy-MM-dd").parse(info.get(3));
			dateChooser.setDate(date);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		contentPane.add(dateChooser);
	}
}
