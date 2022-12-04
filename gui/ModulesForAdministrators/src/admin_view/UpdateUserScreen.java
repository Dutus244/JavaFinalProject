package admin_view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JDateChooser;

@SuppressWarnings("serial")
public class UpdateUserScreen extends JFrame {
	private static UpdateUserScreen frame = null;

	private JPanel contentPane;
	private JTextField usernameTextField;
	private JTextField passTextField;
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
					frame = new UpdateUserScreen();
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
	public UpdateUserScreen() {
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
		
		JButton okButton = new JButton("OK");
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
				Date dob = dateChooser.getDate();
				
				System.out.println(usernameTextField.getText());
				System.out.println(passTextField.getText());
				System.out.println(fullnameTextField.getText());
				System.out.println(addrTextField.getText());
				if (dob != null)
					System.out.println(dateFormat.format(dob));
				System.out.println(sexComboBox.getSelectedItem());
				System.out.println(emailTextField.getText());
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
		
		passTextField = new JTextField();
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
		sexComboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"Male", "Female"}));
		sexComboBox.setBounds(130, 255, 220, 25);
		contentPane.add(sexComboBox);
		
		dateChooser = new JDateChooser();
		dateChooser.setDateFormatString("yyyy/MM/dd");
		dateChooser.setBounds(130, 215, 220, 25);
		contentPane.add(dateChooser);
	}
}
