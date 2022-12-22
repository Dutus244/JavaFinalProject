package modulesforusers;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.*;
import java.awt.Font;

@SuppressWarnings("serial")
public class UpdatePasswordScreen1 extends JFrame implements ActionListener {
	JPanel panelForgotPassword;
	JLabel labelForgotPassword;
	
	JLabel labelEmail;
	JTextField textFieldEmail;
	JButton buttonSendEmail;
	
	JLabel labelOTP;
	JTextField textFieldOTP;
	
	JButton buttonForgotPassword;
	JButton buttonClose;
	
	Connection conn = null;
	java.sql.Statement stmt;
	
	String OTP = null;
	String email = "";
	
	public UpdatePasswordScreen1(String email) {
		initialize(email);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == buttonSendEmail) {
			ResultSet rs;
			email = textFieldEmail.getText();
			if (email.isEmpty()) {
				JOptionPane.showMessageDialog(this,"Please input email to send OTP", "Attention",JOptionPane.ERROR_MESSAGE);
                return;
			}
			if (!SignupScreen.isValidEmailAddress(email)) {
				JOptionPane.showMessageDialog(this,"Invalid email", "Attention",JOptionPane.ERROR_MESSAGE);
                return;
			}
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				conn = DriverManager.getConnection(Main.DB_URL, Main.USER, Main.PASS);
			} catch (SQLException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			try {
				stmt = conn.createStatement();
			} catch (SQLException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			try {
				rs = ((java.sql.Statement)stmt).executeQuery("select count(*) as total from users where Email = '" + email + "'");
				rs.next();
				if (rs.getInt("total") == 0) {
					JOptionPane.showMessageDialog(this,"This enmail does not exist", "Attention",JOptionPane.ERROR_MESSAGE);
	                return;
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			OTP = SignupScreen.generateRandomPassword(6);
			String header = "YOUR PASSWORD OF CHAT APPLICATION";
			String message = "Your OTP to reset password is " + OTP;
			new SendEmail(email,header,message);
			JOptionPane.showMessageDialog(null, "Send email successful");
		}
		else if (e.getSource() == buttonForgotPassword) {
			if (OTP == null) {
				JOptionPane.showMessageDialog(this,"Please send email to receive OTP", "Attention",JOptionPane.ERROR_MESSAGE);
                return;
			}
			String inputOTP = textFieldOTP.getText();
			if (inputOTP.isEmpty()) {
				JOptionPane.showMessageDialog(this,"Please input OTP", "Attention",JOptionPane.ERROR_MESSAGE);
                return;
			}
			if (!inputOTP.equals(OTP)) {
				JOptionPane.showMessageDialog(this,"Incorrect OTP", "Attention",JOptionPane.ERROR_MESSAGE);
                return;
			}
			if (conn != null) {
				try {
					stmt.close();
					conn.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			this.dispose();
            try{
                new UpdatePasswordScreen2(email);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
		}
		else if (e.getSource() == buttonClose) {
			if (conn != null) {
				try {
					stmt.close();
					conn.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			this.dispose();
		}
	}

	private void initialize(String email) {
		this.setTitle("UPDATE PASSWORD");
        this.setSize(500,500);
        getContentPane().setLayout(new BorderLayout());
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setResizable(false);
        
        addWindowListener(new WindowAdapter() {
        	@Override
        	public void windowClosing(WindowEvent e) {
        		if(conn != null) {
        			try {
        				stmt.close();
						conn.close();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
        		}
        	}
        });
        
        panelForgotPassword = new JPanel();
        panelForgotPassword.setPreferredSize(new Dimension(500,500));
        panelForgotPassword.setLayout(null);
        
        labelForgotPassword = new JLabel("UPDATE PASSWORD", SwingConstants.CENTER);
        labelForgotPassword.setFont(Main.fontBiggestBold);
        labelForgotPassword.setBounds(0,50,500,40);
        
        labelEmail = new JLabel("YOUR EMAIL");
        labelEmail.setFont(Main.fontSmallBoldItalic);
        labelEmail.setBounds(100,120,300,40);
        
        textFieldEmail = new JTextField();
        textFieldEmail.setText(email);
        textFieldEmail.setEditable(false);
        textFieldEmail.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        textFieldEmail.setBounds(100,160,260,40);
        
        Icon imageIconSendEmail= new ImageIcon("source/image/sendemail.png");
    	Image imageSendEmail = ((ImageIcon) imageIconSendEmail).getImage();
    	Image newimgSendEmail = imageSendEmail.getScaledInstance(40, 40,  java.awt.Image.SCALE_SMOOTH);
    	imageIconSendEmail = new ImageIcon(newimgSendEmail);
        buttonSendEmail = new JButton(imageIconSendEmail);
        buttonSendEmail.addActionListener(this);
        buttonSendEmail.setFocusable(false);
        buttonSendEmail.setOpaque(false);
        buttonSendEmail.setContentAreaFilled(false);
        buttonSendEmail.setBounds(360,160,40,40);
        
        labelOTP = new JLabel("INPUT OTP");
        labelOTP.setFont(Main.fontSmallBoldItalic);
        labelOTP.setBounds(100,210,300,40);
        
        textFieldOTP = new JTextField();
        textFieldOTP.setFont(Main.fontSmall);
        textFieldOTP.setBounds(100,250,300,40);
        
        buttonForgotPassword = new JButton("CONFIRM OTP");
        buttonForgotPassword.setFont(Main.fontBigBold);
        buttonForgotPassword.setFocusable(false);
        buttonForgotPassword.addActionListener(this);
        buttonForgotPassword.setBounds(100,350,300,40);
        
        buttonClose = new JButton("CLOSE");
        buttonClose.setFont(Main.fontBigBold);
        buttonClose.setFocusable(false);
        buttonClose.addActionListener(this);
        buttonClose.setBounds(100,410,300,40);
        
        panelForgotPassword.add(labelForgotPassword);
        panelForgotPassword.add(labelEmail);
        panelForgotPassword.add(textFieldEmail);
        panelForgotPassword.add(buttonSendEmail);
        panelForgotPassword.add(labelOTP);
        panelForgotPassword.add(textFieldOTP);
        panelForgotPassword.add(buttonForgotPassword);
        panelForgotPassword.add(buttonClose);
        
        getContentPane().add(panelForgotPassword);
	}
}
