package admin_view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

import org.mindrot.jbcrypt.BCrypt;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.ActionEvent;
import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JTextFieldDateEditor;

import db.Connect_DB;

@SuppressWarnings("serial")
public class AddUserScreen extends JFrame {
	private static AddUserScreen frame = null;

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
	public static void main() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new AddUserScreen();
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
	public AddUserScreen() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 400, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel headerLabel = new JLabel("Thêm người dùng");
		headerLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		headerLabel.setBounds(125, 10, 150, 39);
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
		
		JButton okButton = new JButton("OK");
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
				Date dob = dateChooser.getDate();
				
				String username = usernameTextField.getText();
				String password = passTextField.getPassword().toString();
				String passwordHash = BCrypt.hashpw(password, BCrypt.gensalt(12));
				String fullname = fullnameTextField.getText();
				String addr = addrTextField.getText();
				String dobString = dob == null ? "" : dateFormat.format(dob);
				String sex = sexComboBox.getSelectedItem().toString();
				String email = emailTextField.getText();
				String dateCreated = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date());
				
				if (username.equals("") || password.equals("") || fullname.equals("") || addr.equals("") || dob == null ||
						sex.equals("") || email.equals("")) {
					JOptionPane.showMessageDialog(frame,
			                "Vui lòng điền đầy đủ thông tin",
			                "Error",
			                JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				Connect_DB db = Connect_DB.getInstance();
				try {
					db.addUser(username, passwordHash, fullname, addr, dobString, sex, email, dateCreated);
					JOptionPane.showMessageDialog(frame,
			                "Thêm user thành công",
			                "Message",
			                JOptionPane.INFORMATION_MESSAGE);
				} catch (SQLIntegrityConstraintViolationException e1) {
					// TODO: handle exception
					e1.printStackTrace();
//					System.out.println(e1.getMessage());
					JOptionPane.showMessageDialog(frame,
			                "Username đã tồn tại",
			                "Error",
			                JOptionPane.ERROR_MESSAGE);
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
			}
		});
		okButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		okButton.setBounds(73, 350, 90, 35);
		contentPane.add(okButton);
		
		JButton cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		cancelButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		cancelButton.setBounds(236, 350, 90, 35);
		contentPane.add(cancelButton);
		
		usernameTextField = new JTextField();
		usernameTextField.setBounds(130, 55, 220, 25);
		contentPane.add(usernameTextField);
		usernameTextField.setColumns(10);
		
		passTextField = new JPasswordField();
		passTextField.setColumns(10);
		passTextField.setBounds(130, 95, 220, 25);
		contentPane.add(passTextField);
		
		fullnameTextField = new JTextField();
		fullnameTextField.setColumns(10);
		fullnameTextField.setBounds(130, 135, 220, 25);
		contentPane.add(fullnameTextField);
		
		addrTextField = new JTextField();
		addrTextField.setColumns(10);
		addrTextField.setBounds(130, 175, 220, 25);
		contentPane.add(addrTextField);
		
		emailTextField = new JTextField();
		emailTextField.setColumns(10);
		emailTextField.setBounds(130, 295, 220, 25);
		contentPane.add(emailTextField);
		
		sexComboBox = new JComboBox<String>();
		sexComboBox.setToolTipText("");
		sexComboBox.setFont(new Font("Tahoma", Font.PLAIN, 14));
		sexComboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"Nam", "Nữ"}));
		sexComboBox.setBounds(130, 255, 220, 25);
		contentPane.add(sexComboBox);
		
		dateChooser = new JDateChooser();
		JTextFieldDateEditor editor = (JTextFieldDateEditor) dateChooser.getDateEditor();
		editor.setEditable(false);
		dateChooser.setDateFormatString("yyyy/MM/dd");
		dateChooser.setBounds(130, 215, 220, 25);
		contentPane.add(dateChooser);
	}
}
