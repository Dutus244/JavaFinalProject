package modulesForUsers;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import javax.swing.JLabel;
import com.jgoodies.forms.factories.DefaultComponentFactory;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RegisterScreen extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JTextField txtUsername;
	private JTextField txtEmail;
	private JTextField txtName;
	private JTextField txtAddress;
	private JTextField txtBirthday;
	
	JButton btnSignup;
	JButton btnSignin;
	public RegisterScreen() {
		this.setTitle("REGISTER");
        this.setSize(1000,600);
        this.setLayout(new BorderLayout());
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		contentPane.setLayout(new GridLayout(0, 2, 0, 0));
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblTittle = DefaultComponentFactory.getInstance().createLabel("SIGN UP");
		lblTittle.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 40));
		lblTittle.setBounds(66, 25, 278, 65);
		panel.add(lblTittle);
		
		JLabel lblusername = DefaultComponentFactory.getInstance().createLabel("Enter your username:");
		lblusername.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 20));
		lblusername.setBounds(66, 102, 294, 24);
		panel.add(lblusername);
		
		txtUsername = new JTextField();
		txtUsername.setFont(new Font("Arial", Font.PLAIN, 16));
		txtUsername.setBounds(66, 126, 294, 30);
		panel.add(txtUsername);
		txtUsername.setColumns(10);
		
		JLabel lblEmail = DefaultComponentFactory.getInstance().createLabel("Enter your email:");
		lblEmail.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 20));
		lblEmail.setBounds(66, 167, 294, 24);
		panel.add(lblEmail);
		
		txtEmail = new JTextField();
		txtEmail.setFont(new Font("Arial", Font.PLAIN, 16));
		txtEmail.setBounds(66, 189, 294, 36);
		panel.add(txtEmail);
		txtEmail.setColumns(10);
		
		btnSignup = new JButton("SIGN UP");
		btnSignup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnSignup.setBackground(new Color(255, 255, 255));
		btnSignup.setFont(new Font("Arial", Font.BOLD, 20));
		btnSignup.setBounds(125, 437, 158, 36);
		btnSignup.addActionListener(this);
		panel.add(btnSignup);
		
		JLabel lbNoti = DefaultComponentFactory.getInstance().createLabel("(Receive password by Email)");
		lbNoti.setHorizontalAlignment(SwingConstants.CENTER);
		lbNoti.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 13));
		lbNoti.setBounds(66, 533, 294, 14);
		panel.add(lbNoti);
		
		btnSignin = new JButton("SIGN IN");
		btnSignin.setBackground(new Color(255, 255, 255));
		btnSignin.setFont(new Font("Arial", Font.BOLD, 20));
		btnSignin.setBounds(125, 486, 158, 36);
		btnSignin.addActionListener(this);
		panel.add(btnSignin);
		
		JLabel lbName = DefaultComponentFactory.getInstance().createLabel("Enter your name:");
		lbName.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 20));
		lbName.setBounds(66, 234, 294, 30);
		panel.add(lbName);
		
		txtName = new JTextField();
		txtName.setFont(new Font("Arial", Font.PLAIN, 16));
		txtName.setBounds(66, 259, 294, 30);
		panel.add(txtName);
		txtName.setColumns(10);
		
		JLabel lbAddress = DefaultComponentFactory.getInstance().createLabel("Enter your address:");
		lbAddress.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 20));
		lbAddress.setBounds(66, 300, 299, 24);
		panel.add(lbAddress);
		
		txtAddress = new JTextField();
		txtAddress.setFont(new Font("Arial", Font.PLAIN, 16));
		txtAddress.setBounds(66, 325, 294, 30);
		panel.add(txtAddress);
		txtAddress.setColumns(10);
		
		JLabel lbBirthday = DefaultComponentFactory.getInstance().createLabel("Enter your birthday:");
		lbBirthday.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 20));
		lbBirthday.setBounds(66, 365, 294, 30);
		panel.add(lbBirthday);
		
		txtBirthday = new JTextField();
		txtBirthday.setFont(new Font("Arial", Font.PLAIN, 16));
		txtBirthday.setBounds(66, 390, 294, 36);
		panel.add(txtBirthday);
		txtBirthday.setColumns(10);
		
		JPanel panel_Image = new JPanel();
		panel_Image.setBackground(new Color(255, 255, 255));
		contentPane.add(panel_Image);
		panel_Image.setLayout(new BorderLayout(0, 0));
		
		JLabel Image_joinus = DefaultComponentFactory.getInstance().createLabel("");
		Image_joinus.setIcon(new ImageIcon("source/Image/welcome.png"));
		Image_joinus.setSize(20,500);
		panel_Image.add(Image_joinus, BorderLayout.CENTER);
		add(contentPane);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == btnSignup) {
			this.dispose();
	        try{
	            new LoginScreen();
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }
		}
		else if (e.getSource() == btnSignin) {
			this.dispose();
	        try{
	            new LoginScreen();
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }
		}
	}
}
