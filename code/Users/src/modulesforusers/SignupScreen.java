package modulesforusers;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.swing.*;

import org.mindrot.jbcrypt.BCrypt;

import com.toedter.calendar.JDateChooser;
import java.sql.*;


public class SignupScreen extends JFrame implements ActionListener {
	JPanel panelSignup;
	JLabel labelSignup;
	JLabel pictureSignup;
	
	JLabel labelUserName;
	JTextField textFieldUserName;
	
	JLabel labelFullName;
	JTextField textFieldFullName;
	
	JLabel labelAddress;
	JTextField textFieldAddress;
	
	JLabel labelSex;
	JComboBox comboBoxSex;
	
	JLabel labelDateOfBirth;
	JDateChooser dateChooserDateOfBirth;
	
	JLabel labelEmail;
	JTextField textFieldEmail;
	
	JButton buttonLogin;
	JButton buttonSignup;
	
	Connection conn = null;
	java.sql.Statement stmt;
	
	Date date;
	SimpleDateFormat sdf;
	
	public SignupScreen(Connection connection) {
		conn = connection;
		
		this.setTitle("SIGN UP");
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
        
        panelSignup = new JPanel();
        panelSignup.setPreferredSize(new Dimension(1000,600));
        panelSignup.setLayout(null);
        
        Icon imageIconSignup = new ImageIcon("source/image/welcome.png");
    	Image imageSignup = ((ImageIcon) imageIconSignup).getImage();
    	Image newimgSignup = imageSignup.getScaledInstance(300, 600,  java.awt.Image.SCALE_SMOOTH);
    	imageIconSignup = new ImageIcon(newimgSignup);
        pictureSignup = new JLabel();
        pictureSignup.setIcon(imageIconSignup);
        pictureSignup.setBounds(700,0,300,600);
        
        labelSignup = new JLabel("SIGN UP", SwingConstants.CENTER);
        labelSignup.setFont(Main.fontBiggestBold);
        labelSignup.setBounds(25,50,650,40);
        
        labelUserName = new JLabel("USERNAME");
        labelUserName.setFont(Main.fontSmallBoldItalic);
        labelUserName.setBounds(25,120,300,40);
        
        textFieldUserName = new JTextField();
        textFieldUserName.setFont(Main.fontSmall);
        textFieldUserName.setBounds(25,160,300,40);
        
        labelFullName = new JLabel("FULLNAME");
        labelFullName.setFont(Main.fontSmallBoldItalic);
        labelFullName.setBounds(375,120,300,40);
        
        textFieldFullName = new JTextField();
        textFieldFullName.setFont(Main.fontSmall);
        textFieldFullName.setBounds(375,160,300,40);
        
        labelAddress = new JLabel("ADDRESS");
        labelAddress.setFont(Main.fontSmallBoldItalic);
        labelAddress.setBounds(25,210,300,40);
        
        textFieldAddress = new JTextField();
        textFieldAddress.setFont(Main.fontSmall);
        textFieldAddress.setBounds(25,250,300,40);
        
        labelDateOfBirth = new JLabel("DATE OF BIRTH");
        labelDateOfBirth.setFont(Main.fontSmallBoldItalic);
        labelDateOfBirth.setBounds(375,210,300,40);
        
        dateChooserDateOfBirth = new JDateChooser();
        dateChooserDateOfBirth.setBounds(375, 250, 300, 40);
        dateChooserDateOfBirth.setDateFormatString("dd/MM/yyyy");
        
        labelSex = new JLabel("SEX");
        labelSex.setFont(Main.fontSmallBoldItalic);
        labelSex.setBounds(25,300,300,40);
        
        String[] sex = {"Male", "Female"};
        
        comboBoxSex = new JComboBox(sex);
        comboBoxSex.setBounds(25,340,300,40);
        
        labelEmail = new JLabel("EMAIL");
        labelEmail.setFont(Main.fontSmallBoldItalic);
        labelEmail.setBounds(375,300,300,40);
        
        textFieldEmail = new JTextField();
        textFieldEmail.setFont(Main.fontSmall);
        textFieldEmail.setBounds(375,340,300,40);
        
        buttonSignup = new JButton("SIGN UP");
        buttonSignup.setFont(Main.fontBigBold);
        buttonSignup.setFocusable(false);
        buttonSignup.addActionListener(this);
        buttonSignup.setBounds(50,440,250,40);
        
        buttonLogin = new JButton("LOGIN");
        buttonLogin.setFont(Main.fontBigBold);
        buttonLogin.setFocusable(false);
        buttonLogin.addActionListener(this);
        buttonLogin.setBounds(400,440,250,40);
        
        panelSignup.add(pictureSignup);
        panelSignup.add(labelSignup);
        panelSignup.add(labelUserName);
        panelSignup.add(textFieldUserName);
        panelSignup.add(labelFullName);
        panelSignup.add(textFieldFullName);
        panelSignup.add(labelAddress);
        panelSignup.add(textFieldAddress);
        panelSignup.add(labelDateOfBirth);
        panelSignup.add(dateChooserDateOfBirth);
        panelSignup.add(labelSex);
        panelSignup.add(comboBoxSex);
        panelSignup.add(labelEmail);
        panelSignup.add(textFieldEmail);
        panelSignup.add(buttonLogin);
        panelSignup.add(buttonSignup);
        
        add(panelSignup);
	}
	
	public static String generateRandomPassword(int len)
    {
        final String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
 
        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder();
 
        for (int i = 0; i < len; i++)
        {
            int randomIndex = random.nextInt(chars.length());
            sb.append(chars.charAt(randomIndex));
        }
        
        return sb.toString();
    }
	
	public static boolean isValidEmailAddress(String email) {
		   boolean result = true;
		   try {
		      InternetAddress emailAddr = new InternetAddress(email);
		      emailAddr.validate();
		   } catch (AddressException ex) {
		      result = false;
		   }
		   return result;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == buttonSignup) {
			try {
				stmt = conn.createStatement();
			} catch (SQLException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			String username = textFieldUserName.getText();
			String fullname = textFieldFullName.getText();
			String address = textFieldAddress.getText();
			String dateOfBirth = "";
			date =  dateChooserDateOfBirth.getDate();
			sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
			if (date != null) {
				dateOfBirth = sdf.format(date);
			}
			String sex = comboBoxSex.getSelectedItem().toString();
			String email = textFieldEmail.getText();
			ResultSet rs;
			
			if (username.isEmpty()) {
				JOptionPane.showMessageDialog(this,"Please input username", "Attention",JOptionPane.ERROR_MESSAGE);
                return;
			}
			if (fullname.isEmpty()) {
				JOptionPane.showMessageDialog(this,"Please input fullname", "Attention",JOptionPane.ERROR_MESSAGE);
                return;
			}
			if (address.isEmpty()) {
				JOptionPane.showMessageDialog(this,"Please input address", "Attention",JOptionPane.ERROR_MESSAGE);
                return;
			}
			if (dateOfBirth.isEmpty()) {
				JOptionPane.showMessageDialog(this,"Please choose date of birth", "Attention",JOptionPane.ERROR_MESSAGE);
                return;
			}
			if (sex.isEmpty()) {
				JOptionPane.showMessageDialog(this,"Please choose sex", "Attention",JOptionPane.ERROR_MESSAGE);
                return;
			}
			if (email.isEmpty()) {
				JOptionPane.showMessageDialog(this,"Please input email", "Attention",JOptionPane.ERROR_MESSAGE);
                return;
			}
			if (!isValidEmailAddress(email)) {
				JOptionPane.showMessageDialog(this,"Invalid email", "Attention",JOptionPane.ERROR_MESSAGE);
                return;
			}
			
			try {
				rs = ((java.sql.Statement)stmt).executeQuery("select count(*) as total from users where UserName = '" + username + "'");
				rs.next();
				if (rs.getInt("total") == 1) {
					JOptionPane.showMessageDialog(this,"This username already exist", "Attention",JOptionPane.ERROR_MESSAGE);
	                return;
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				rs = ((java.sql.Statement)stmt).executeQuery("select count(*) as total from users where Email = '" + email + "'");
				rs.next();
				if (rs.getInt("total") == 1) {
					JOptionPane.showMessageDialog(this,"This email already exist", "Attention",JOptionPane.ERROR_MESSAGE);
	                return;
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			String userID = UUID.randomUUID().toString();
			String password = generateRandomPassword(8);
			String header = "YOUR PASSWORD OF CHAT APPLICATION";
			String message = "Hello " + username + ", your password is " + password;
			new SendEmail(email,header,message);
			String hash = BCrypt.hashpw(password, BCrypt.gensalt(12));
			
			String createTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date());
			try {
                conn.setAutoCommit(false);
                stmt = conn.createStatement();
                String sql = "insert into users values('" + userID + "','" + username + "','" + fullname + "','" + address + "','" + dateOfBirth + "','" + sex + "','" + email + "','" + hash + "','" + createTime + "',false,'')";
                stmt.executeUpdate(sql);
                conn.commit();
                JOptionPane.showMessageDialog(null, "Sign up successfully");
            }
            catch (SQLException ae){
            	JOptionPane.showMessageDialog(this,"Unable to insert", "Attention",JOptionPane.ERROR_MESSAGE);
            }
			this.dispose();
            try{
                new LoginScreen(conn);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
		}
		else if (e.getSource() == buttonLogin) {
			this.dispose();
            try{
                new LoginScreen(conn);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
		}
	}
}
