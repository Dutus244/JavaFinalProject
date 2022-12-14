package modulesforusers;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.*;

import org.mindrot.jbcrypt.BCrypt;

public class LoginScreen extends JFrame implements ActionListener {
	JPanel panelLogin;
	JLabel labelLogin;
	JLabel pictureLogin;
	
	JLabel labelUserName;
	JTextField textFieldUserName;
	
	JLabel labelPassword;
	JPasswordField passwordFieldPassword;
	JButton buttonHideShowPassword;
	boolean hidePassword = true;
	
	JButton buttonLogin;
	JButton buttonSignup;
	JButton buttonForgotPassword;
	
	Connection conn = null;
	java.sql.Statement stmt;
	static Socket client = null;
	
	public LoginScreen() {
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
		else if (e.getSource() == buttonForgotPassword) {
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
                new ForgotPasswordScreen1();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
		}
		else if (e.getSource() == buttonLogin) {
			String username = textFieldUserName.getText();
			String password = new String(passwordFieldPassword.getPassword());
			ResultSet rs;
			if (username.isEmpty()) {
				JOptionPane.showMessageDialog(this,"Please input username", "Attention",JOptionPane.ERROR_MESSAGE);
                return;
			}
			if (password.isEmpty()) {
				JOptionPane.showMessageDialog(this,"Please input password", "Attention",JOptionPane.ERROR_MESSAGE);
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
				rs = ((java.sql.Statement)stmt).executeQuery("select count(*) as total from users where UserName = '" + username + "'");
				rs.next();
				if (rs.getInt("total") == 0) {
					JOptionPane.showMessageDialog(this,"This username does not exist", "Attention",JOptionPane.ERROR_MESSAGE);
	                return;
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				rs = ((java.sql.Statement)stmt).executeQuery("select Pass from users where UserName = '" + username + "'");
				rs.next();
				String checkPassword = rs.getString("Pass");
				boolean valuate = BCrypt.checkpw(password, checkPassword);
				if (!valuate) {
					JOptionPane.showMessageDialog(this,"Incorrect password", "Attention",JOptionPane.ERROR_MESSAGE);
	                return;
				}
				else {
					rs = ((java.sql.Statement)stmt).executeQuery("select LockAccount from users where UserName = '" + username + "'");
					rs.next();
					String checkLockAccount = rs.getString("LockAccount");
					if (checkLockAccount.equals("1")) {
						JOptionPane.showMessageDialog(this,"Your account has been locked", "Attention",JOptionPane.ERROR_MESSAGE);
		                return;
					}
					try {
		                conn.setAutoCommit(false);
		                String sql = "update activestatus set OnlineStatus = true where UserID = (select UserID from users where UserName = '"+ username + "')";
		                stmt.executeUpdate(sql);
		                conn.commit();
		            }
		            catch (SQLException ae){
		            	JOptionPane.showMessageDialog(this,"Unable to insert", "Attention",JOptionPane.ERROR_MESSAGE);
		            }
					JOptionPane.showMessageDialog(null, "Login successfully");
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
		                new HomeScreen(username);
		            } catch (Exception ex) {
		                ex.printStackTrace();
		            }
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		else if (e.getSource() == buttonSignup) {
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
                new SignupScreen();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
		}
	}

	private void initialize() {
		this.setTitle("LOGIN");
        this.setSize(1000,600);
        this.setLayout(new BorderLayout());
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
						System.exit(0);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
        		}
        		else {
        			System.exit(0);
        		}
        	}
        });
        
		panelLogin = new JPanel();
        panelLogin.setPreferredSize(new Dimension(1000,600));
        panelLogin.setLayout(null);
        
        Icon imageIconLogin = new ImageIcon("source/image/welcomeback.png");
    	Image imageLogin = ((ImageIcon) imageIconLogin).getImage();
    	Image newimgLogin = imageLogin.getScaledInstance(500, 600,  java.awt.Image.SCALE_SMOOTH);
    	imageIconLogin = new ImageIcon(newimgLogin);
        pictureLogin = new JLabel();
        pictureLogin.setIcon(imageIconLogin);
        pictureLogin.setBounds(0,0,500,600);
        
        labelLogin = new JLabel("LOGIN", SwingConstants.CENTER);
        labelLogin.setFont(Main.fontBiggestBold);
        labelLogin.setBounds(600,70,300,40);
        
        labelUserName = new JLabel("USERNAME");
        labelUserName.setFont(Main.fontSmallBoldItalic);
        labelUserName.setBounds(600,170,300,40);
        
        textFieldUserName = new JTextField();
        textFieldUserName.setFont(Main.fontSmall);
        textFieldUserName.setBounds(600,210,300,40);
        
        labelPassword = new JLabel("PASSWORD");
        labelPassword.setFont(Main.fontSmallBoldItalic);
        labelPassword.setBounds(600,270,300,40);
        
        passwordFieldPassword = new JPasswordField();
        passwordFieldPassword.setFont(Main.fontSmall);
        passwordFieldPassword.setEchoChar('*');
        passwordFieldPassword.setBounds(600,310,260,40);
        
        Icon imageIconHideShowPassword = new ImageIcon("source/image/hide.png");
    	Image imageHideShowPassword = ((ImageIcon) imageIconHideShowPassword).getImage();
    	Image newimgHideShowPassword = imageHideShowPassword.getScaledInstance(40, 40,  java.awt.Image.SCALE_SMOOTH);
    	imageIconHideShowPassword = new ImageIcon(newimgHideShowPassword);
        buttonHideShowPassword = new JButton(imageIconHideShowPassword);
        buttonHideShowPassword.addActionListener(this);
        buttonHideShowPassword.setFocusable(false);
        buttonHideShowPassword.setOpaque(false);
        buttonHideShowPassword.setContentAreaFilled(false);
        buttonHideShowPassword.setBounds(860,310,40,40);
        
        buttonForgotPassword =  new JButton("Forgot password?");
        buttonForgotPassword.setFont(Main.fontSmallestItalic);
        buttonForgotPassword.setForeground(Color.black);
        buttonForgotPassword.setFocusable(false);
        buttonForgotPassword.addActionListener(this);
        buttonForgotPassword.setOpaque(false);
        buttonForgotPassword.setContentAreaFilled(false);
        buttonForgotPassword.setBorderPainted(false);
        buttonForgotPassword.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
            	buttonForgotPassword.setForeground(Color.BLUE);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
            	buttonForgotPassword.setForeground(Color.black);
            }
        });
        buttonForgotPassword.setBounds(770,360,150,20);
        
        buttonLogin = new JButton("LOGIN");
        buttonLogin.setFont(Main.fontBigBold);
        buttonLogin.setFocusable(false);
        buttonLogin.addActionListener(this);
        buttonLogin.setBounds(600,410,300,40);
        
        buttonSignup = new JButton("SIGN UP");
        buttonSignup.setFont(Main.fontBigBold);
        buttonSignup.setFocusable(false);
        buttonSignup.addActionListener(this);
        buttonSignup.setBounds(600,470,300,40);
        
        panelLogin.add(pictureLogin);
        panelLogin.add(labelLogin);
        panelLogin.add(labelUserName);
        panelLogin.add(textFieldUserName);
        panelLogin.add(labelPassword);
        panelLogin.add(passwordFieldPassword);
        panelLogin.add(buttonHideShowPassword);
        panelLogin.add(buttonForgotPassword);
        panelLogin.add(buttonLogin);
        panelLogin.add(buttonSignup);
        
        add(panelLogin);
	}
}
