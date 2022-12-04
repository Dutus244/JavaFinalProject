package modulesForUsers;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import com.jgoodies.forms.factories.DefaultComponentFactory;

import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Label;
import java.awt.Font;
import javax.swing.BoxLayout;
import java.awt.Dimension;
import java.awt.Rectangle;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LoginScreen extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	
	JButton btnNewButton;
	JButton btnNewButton_1;
	
	public LoginScreen() {
		this.setTitle("LOGIN");
        this.setSize(1000,600);
        this.setLayout(new BorderLayout());
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(1, 0, 0, 0));
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 99, 100));
		contentPane.add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewJgoodiesLabel = DefaultComponentFactory.getInstance().createLabel("");
		lblNewJgoodiesLabel.setBackground(new Color(206, 232, 245));
		lblNewJgoodiesLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewJgoodiesLabel.setIcon(new ImageIcon("source/Image/welcomeback.png"));
		panel.add(lblNewJgoodiesLabel, BorderLayout.CENTER);
		
		JPanel panel_1 = new JPanel();
		panel_1.setVerifyInputWhenFocusTarget(false);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewJgoodiesLabel_1 = DefaultComponentFactory.getInstance().createLabel("LOGIN");
		lblNewJgoodiesLabel_1.setVerticalAlignment(SwingConstants.BOTTOM);
		lblNewJgoodiesLabel_1.setBounds(new Rectangle(10, 0, 0, 0));
		lblNewJgoodiesLabel_1.setBounds(32, 74, 461, 80);
		lblNewJgoodiesLabel_1.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 45));
		panel_1.add(lblNewJgoodiesLabel_1);
		
		JLabel lblNewJgoodiesLabel_2 = DefaultComponentFactory.getInstance().createLabel("USER'S NAME");
		lblNewJgoodiesLabel_2.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 20));
		lblNewJgoodiesLabel_2.setBounds(32, 178, 444, 24);
		panel_1.add(lblNewJgoodiesLabel_2);
		
		textField = new JTextField();
		textField.setFont(new Font("Arial", Font.PLAIN, 18));
		textField.setHorizontalAlignment(SwingConstants.LEFT);
		textField.setBounds(32, 203, 383, 33);
		panel_1.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewJgoodiesLabel_3 = DefaultComponentFactory.getInstance().createLabel("PASSWORD");
		lblNewJgoodiesLabel_3.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 20));
		lblNewJgoodiesLabel_3.setBounds(32, 260, 449, 40);
		panel_1.add(lblNewJgoodiesLabel_3);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("Arial", Font.PLAIN, 18));
		textField_1.setBounds(32, 295, 383, 33);
		panel_1.add(textField_1);
		textField_1.setColumns(10);
		
		btnNewButton = new JButton("SIGN IN");
		btnNewButton.setBackground(new Color(255, 255, 255));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setFont(new Font("Arial", Font.BOLD, 20));
		btnNewButton.setBounds(32, 375, 383, 40);
		btnNewButton.addActionListener(this);
		panel_1.add(btnNewButton);
		
		btnNewButton_1 = new JButton("SIGN UP");
		btnNewButton_1.setBackground(new Color(255, 255, 255));
		btnNewButton_1.setFont(new Font("Arial", Font.BOLD, 20));
		btnNewButton_1.setBounds(32, 425, 383, 40);
		btnNewButton_1.addActionListener(this);
		panel_1.add(btnNewButton_1);
		add(panel_1);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == btnNewButton) {
			this.dispose();
	        try{
	            new HomeScreen();
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }
		}
		else if (e.getSource() == btnNewButton_1) {
			this.dispose();
	        try{
	            new RegisterScreen();
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }
		}
	}
}
