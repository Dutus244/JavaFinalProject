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
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import org.mindrot.jbcrypt.BCrypt;

import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JTextFieldDateEditor;

import db.Connect_DB;

@SuppressWarnings("serial")
public class UpdateUserScreen extends JFrame {
	private static UpdateUserScreen frame = null;

	private JPanel contentPane;
	private JTextField usernameTextField;
	private JPasswordField passTextField;
	private JTextField fullnameTextField;
	private JTextField addrTextField;
	private JDateChooser dateChooser;
	private JTextField emailTextField;
	private JComboBox<String> sexComboBox;

	/**
	 * Launch the application.
	 */
	public static void main(String user) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new UpdateUserScreen(user);
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
	public UpdateUserScreen(String user) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 400, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel headerLabel = new JLabel("Cập nhật thông tin người dùng");
		headerLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		headerLabel.setBounds(75, 10, 250, 40);
		contentPane.add(headerLabel);
		
		JLabel usernameLabel = new JLabel("Username");
		usernameLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		usernameLabel.setBounds(30, 50, 84, 33);
		contentPane.add(usernameLabel);
		
		JLabel fullnameLabel = new JLabel("Fullname");
		fullnameLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		fullnameLabel.setBounds(30, 130, 84, 33);
		contentPane.add(fullnameLabel);
		
		JLabel addrLabel = new JLabel("Address");
		addrLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		addrLabel.setBounds(30, 170, 84, 33);
		contentPane.add(addrLabel);
		
		JLabel dobLabel = new JLabel("Date of Birth");
		dobLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		dobLabel.setBounds(30, 210, 84, 33);
		contentPane.add(dobLabel);
		
		JLabel sexLabel = new JLabel("Sex");
		sexLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		sexLabel.setBounds(30, 250, 84, 33);
		contentPane.add(sexLabel);
		
		JLabel emailLabel = new JLabel("Email");
		emailLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		emailLabel.setBounds(30, 290, 84, 33);
		contentPane.add(emailLabel);
		
		JLabel passLabel = new JLabel("Password");
		passLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		passLabel.setBounds(30, 90, 84, 33);
		contentPane.add(passLabel);
		
		JButton okButton = new JButton("Save");
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Vector<String> col = new Vector<String>();
				Vector<String> val = new Vector<String>();
				
				DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
				Date dob = dateChooser.getDate();
				
				String password = new String(passTextField.getPassword());
				String fullname = fullnameTextField.getText();
				String addr = addrTextField.getText();
				String sex = sexComboBox.getSelectedItem().toString();
				String email = emailTextField.getText();
				
				if (!password.equals("")) {
					String passwordHash = BCrypt.hashpw(password, BCrypt.gensalt(12));
					col.add("Pass");
					val.add(passwordHash);
				}
				if (!fullname.equals("")) {
					col.add("FullName");
					val.add(fullname);
				}
				if (!addr.equals("")) {
					col.add("Address");
					val.add(addr);
				}
				if (dob != null) {
					String dobString = dateFormat.format(dob);
					col.add("DOB");
					val.add(dobString);
				}
				if (!sex.equals("")) {
					col.add("Sex");
					val.add(sex);
				}
				if (!email.equals("")) {
					col.add("Email");
					val.add(email);
				}
				
				if (col.size() == 0)
					return;
				
				Connect_DB db = Connect_DB.getInstance();
				try {
					db.updateUser(user, col, val);
					JOptionPane.showMessageDialog(frame,
			                "Cập nhật thành công",
			                "Message",
			                JOptionPane.INFORMATION_MESSAGE);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		okButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		okButton.setBounds(73, 360, 90, 35);
		contentPane.add(okButton);
		
		JButton cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		cancelButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		cancelButton.setBounds(236, 360, 90, 35);
		contentPane.add(cancelButton);
		
		usernameTextField = new JTextField();
		usernameTextField.setBounds(130, 55, 220, 25);
		usernameTextField.setColumns(10);
		usernameTextField.setText(user);
		usernameTextField.setEditable(false);
		contentPane.add(usernameTextField);
		
		passTextField = new JPasswordField();
		passTextField.setColumns(10);
		passTextField.setBounds(130, 95, 220, 25);
		contentPane.add(passTextField);
		
		fullnameTextField = new JTextField();
		fullnameTextField.setColumns(10);
		fullnameTextField.setBounds(130, 135, 220, 25);
//		fullnameTextField.setText(fullname);
		contentPane.add(fullnameTextField);
		
		addrTextField = new JTextField();
		addrTextField.setColumns(10);
		addrTextField.setBounds(130, 175, 220, 25);
//		addrTextField.setText(addr);
		contentPane.add(addrTextField);
		
		emailTextField = new JTextField();
		emailTextField.setColumns(10);
		emailTextField.setBounds(130, 295, 220, 25);
//		emailTextField.setText(email);
		contentPane.add(emailTextField);
		
		sexComboBox = new JComboBox<String>();
		sexComboBox.setToolTipText("");
		sexComboBox.setFont(new Font("Tahoma", Font.PLAIN, 14));
		sexComboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"", "Nam", "Nữ"}));
		sexComboBox.setBounds(130, 255, 220, 25);
//		sexComboBox.getModel().setSelectedItem(sex);
		contentPane.add(sexComboBox);
		
		dateChooser = new JDateChooser();
		JTextFieldDateEditor editor = (JTextFieldDateEditor) dateChooser.getDateEditor();
		editor.setEditable(false);
		dateChooser.setDateFormatString("yyyy/MM/dd");
		dateChooser.setBounds(130, 215, 220, 25);
//		Date date;
//		try {
//			date = new SimpleDateFormat("yyyy-MM-dd").parse(dob);
//			dateChooser.setDate(date);
//		} catch (ParseException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
		contentPane.add(dateChooser);
		
		JLabel lblNewLabel = new JLabel("Chỉ cập nhật những dòng được điền hoặc chọn");
		lblNewLabel.setFont(new Font("Tahoma", Font.ITALIC, 12));
		lblNewLabel.setBounds(60, 335, 265, 14);
		contentPane.add(lblNewLabel);
	}
}
