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
import java.sql.SQLException;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;

import org.mindrot.jbcrypt.BCrypt;

@SuppressWarnings("serial")
public class UpdatePasswordScreen2  extends JFrame implements ActionListener {
	JPanel panelForgotPassword;
	JLabel labelForgotPassword;
	
	JLabel labelPassword;
	JPasswordField passwordFieldPassword;
	JButton buttonHideShowPassword;
	boolean hidePassword = true;
	
	JLabel labelRePassword;
	JPasswordField passwordFieldRePassword;
	JButton buttonHideShowRePassword;
	boolean hideRePassword = true;
	
	JButton buttonForgotPassword;
	JButton buttonClose;
	
	Connection conn = null;
	java.sql.Statement stmt;
	
	String email = "";
	
	public UpdatePasswordScreen2(String Email) {
		email = Email;
		initialize();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == buttonHideShowPassword) {
			if (hidePassword) {
				passwordFieldPassword.setEchoChar((char)0);
				hidePassword = false;
			}
			else {
				passwordFieldPassword.setEchoChar('*');
				hidePassword = true;
			}
		}
		else if (e.getSource() == buttonHideShowRePassword) {
			if (hideRePassword) {
				passwordFieldRePassword.setEchoChar((char)0);
				hideRePassword = false;
			}
			else {
				passwordFieldRePassword.setEchoChar('*');
				hideRePassword = true;
			}
		}
		else if (e.getSource() == buttonForgotPassword) {
			String password = new String(passwordFieldPassword.getPassword());
			String repassword = new String(passwordFieldRePassword.getPassword());
			if (password.isEmpty()) {
				JOptionPane.showMessageDialog(this,"Please input new password", "Attention",JOptionPane.ERROR_MESSAGE);
                return;
			}
			if (repassword.isEmpty()) {
				JOptionPane.showMessageDialog(this,"Please input new password again", "Attention",JOptionPane.ERROR_MESSAGE);
                return;
			}
			if (!password.equals(repassword)) {
				JOptionPane.showMessageDialog(this,"The re-entered password is different from the first password ", "Attention",JOptionPane.ERROR_MESSAGE);
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
			String hash = BCrypt.hashpw(password, BCrypt.gensalt(12));
			try {
                conn.setAutoCommit(false);
                stmt = conn.createStatement();
                String sql = "update users set Pass = '" + hash + "' where Email = '" + email + "'";
                stmt.executeUpdate(sql);
                conn.commit();
                JOptionPane.showMessageDialog(null, "Change password successful");
            }
            catch (SQLException ae){
            	JOptionPane.showMessageDialog(this,"Unable to insert", "Attention",JOptionPane.ERROR_MESSAGE);
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
	
	private void initialize() {
		this.setTitle("FORGOT PASSWORD");
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
        
        labelForgotPassword = new JLabel("FORGOT PASSWORD", SwingConstants.CENTER);
        labelForgotPassword.setFont(Main.fontBiggestBold);
        labelForgotPassword.setBounds(0,50,500,40);
        
        labelPassword = new JLabel("INPUT NEW PASSWORD");
        labelPassword.setFont(Main.fontSmallBoldItalic);
        labelPassword.setBounds(100,120,300,40);
        
        passwordFieldPassword = new JPasswordField();
        passwordFieldPassword.setFont(Main.fontSmall);
        passwordFieldPassword.setEchoChar('*');
        passwordFieldPassword.setBounds(100,160,260,40);
        
        Icon imageIconHideShowPassword = new ImageIcon("source/image/hide.png");
    	Image imageHideShowPassword = ((ImageIcon) imageIconHideShowPassword).getImage();
    	Image newimgHideShowPassword = imageHideShowPassword.getScaledInstance(40, 40,  java.awt.Image.SCALE_SMOOTH);
    	imageIconHideShowPassword = new ImageIcon(newimgHideShowPassword);
    	
        buttonHideShowPassword = new JButton(imageIconHideShowPassword);
        buttonHideShowPassword.addActionListener(this);
        buttonHideShowPassword.setFocusable(false);
        buttonHideShowPassword.setOpaque(false);
        buttonHideShowPassword.setContentAreaFilled(false);
        buttonHideShowPassword.setBounds(360,160,40,40);
        
        labelRePassword = new JLabel("INPUT NEW PASSWORD AGAIN");
        labelRePassword.setFont(Main.fontSmallBoldItalic);
        labelRePassword.setBounds(100,210,300,40);
        
        passwordFieldRePassword = new JPasswordField();
        passwordFieldRePassword.setFont(Main.fontSmall);
        passwordFieldRePassword.setEchoChar('*');
        passwordFieldRePassword.setBounds(100,250,260,40);
        
        buttonHideShowRePassword = new JButton(imageIconHideShowPassword);
        buttonHideShowRePassword.addActionListener(this);
        buttonHideShowRePassword.setFocusable(false);
        buttonHideShowRePassword.setOpaque(false);
        buttonHideShowRePassword.setContentAreaFilled(false);
        buttonHideShowRePassword.setBounds(360,250,40,40);
        
        buttonForgotPassword = new JButton("CHANGE PASSWORD");
        buttonForgotPassword.setFont(Main.fontBigBold);
        buttonForgotPassword.setFocusable(false);
        buttonForgotPassword.addActionListener(this);
        buttonForgotPassword.setBounds(50,350,400,40);
        
        buttonClose = new JButton("CLOSE");
        buttonClose.setFont(Main.fontBigBold);
        buttonClose.setFocusable(false);
        buttonClose.addActionListener(this);
        buttonClose.setBounds(50,410,400,40);
        
        panelForgotPassword.add(labelForgotPassword);
        panelForgotPassword.add(labelPassword);
        panelForgotPassword.add(passwordFieldPassword);
        panelForgotPassword.add(buttonHideShowPassword);
        panelForgotPassword.add(labelRePassword);
        panelForgotPassword.add(passwordFieldRePassword);
        panelForgotPassword.add(buttonHideShowRePassword);
        panelForgotPassword.add(buttonForgotPassword);
        panelForgotPassword.add(buttonClose);
        
        getContentPane().add(panelForgotPassword);
	}
}
