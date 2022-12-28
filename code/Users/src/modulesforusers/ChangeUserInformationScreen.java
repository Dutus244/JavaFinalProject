package modulesforusers;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.MessageFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import javax.swing.*;

import com.toedter.calendar.JDateChooser;

@SuppressWarnings("serial")
public class ChangeUserInformationScreen extends JFrame implements ActionListener {
	Container container;
	
	JPanel panelChangeUserInformation;
	
	JButton buttonAvatar;
	JLabel labelName;
	JTextField textFieldName;
	JLabel labelAddress;
	JTextField textFieldAddress;
	JLabel labelBirthDay;
	JDateChooser dateChooserBirthDay;
	JLabel labelSex;
	JComboBox<String> comboBoxSex;
	JLabel labelEmail;
	JTextField textFieldEmail;
	
	JButton buttonUpdate;
	JButton buttonBack;
	
	String username;
	
	java.util.Date date;
	public ChangeUserInformationScreen(String username, Vector<String> info) {
		this.username = username;
		
		this.setTitle("CHANGE INFORMATION");
        this.setSize(400,450);
        getContentPane().setLayout(new BorderLayout());
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setResizable(false);
        
        panelChangeUserInformation = new JPanel();
        panelChangeUserInformation.setPreferredSize(new Dimension(400,450 )); // Được sử dụng khi setSize đã có trong phần cha lớn.
        panelChangeUserInformation.setLayout(null);
        
        Icon avatar = new ImageIcon("source/image/iconUserMenu.png");
    	Image image = ((ImageIcon) avatar).getImage(); // transform it 
    	Image newimg = image.getScaledInstance(48, 48,  java.awt.Image.SCALE_SMOOTH);
    	avatar = new ImageIcon(newimg);
    	buttonAvatar = new JButton(avatar);
    	buttonAvatar.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    		}
    	});
    	buttonAvatar.setBounds(167,20,50,50);
    	
//    	Font font1 = new Font("Times New Roman", Font.BOLD,25);
        Font font2 = new Font("Times New Roman", Font.PLAIN,15);
//        Font font3 = new Font("Times New Roman", Font.PLAIN,10);
        Font font4 = new Font("Times New Roman", Font.BOLD,15);
    	
    	labelName = new JLabel("NAME");
    	labelName.setFont(font4);
    	labelName.setBounds(20,120,100,40);
    	
    	textFieldName = new JTextField(info.get(0));
    	textFieldName.setFont(font2);
    	textFieldName.setBounds(110,120,250,30);
    	
    	labelAddress = new JLabel("ADDRESS");
    	labelAddress.setFont(font4);
    	labelAddress.setBounds(20,160,100,40);
    	
    	textFieldAddress = new JTextField(info.get(1));
    	textFieldAddress.setFont(font2);
    	textFieldAddress.setBounds(110,160,250,30);
    	
    	labelBirthDay = new JLabel("BIRTHDAY");
    	labelBirthDay.setFont(font4);
    	labelBirthDay.setBounds(20,200,100,40);
    	
    	dateChooserBirthDay = new JDateChooser();
    	dateChooserBirthDay.setFont(font2);
    	dateChooserBirthDay.setBounds(110,200,250,30);
    	
    	try {
			date = new SimpleDateFormat("yyyy-MM-dd").parse(info.get(2));
			dateChooserBirthDay.setDateFormatString("dd/MM/yyyy");
			dateChooserBirthDay.setDate(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
    	
    	labelSex = new JLabel("SEX");
    	labelSex.setFont(font4);
    	labelSex.setBounds(20,240,100,40);
    	
    	String[] sex = {"MALE", "FEMALE"};
    	
    	comboBoxSex = new JComboBox<String>(sex);
    	comboBoxSex.setModel(new DefaultComboBoxModel<String>(new String[] {"Male", "Female"}));
    	comboBoxSex.setSelectedItem(info.get(3));
    	comboBoxSex.setFont(font2);
    	comboBoxSex.setBounds(110,240,250,30);
    	
    	labelEmail = new JLabel("EMAIL");
    	labelEmail.setFont(font4);
    	labelEmail.setBounds(20,280,100,40);
    	
    	textFieldEmail = new JTextField(info.get(4));
    	textFieldEmail.setFont(font2);
    	textFieldEmail.setBounds(110,280,250,30);
    	
    	buttonUpdate = new JButton("UPDATE");
        buttonUpdate.setBounds(203,335,150,50);
        buttonUpdate.setFocusable(false);
        buttonUpdate.addActionListener(this);
        
        buttonBack = new JButton("CLOSE");
        buttonBack.setBounds(26,335,150,50);
        buttonBack.setFocusable(false);
        buttonBack.addActionListener(this);
    	
        panelChangeUserInformation.add(buttonAvatar);
        panelChangeUserInformation.add(labelName);
        panelChangeUserInformation.add(textFieldName);
        panelChangeUserInformation.add(labelAddress);
        panelChangeUserInformation.add(textFieldAddress);
        panelChangeUserInformation.add(labelBirthDay);
        panelChangeUserInformation.add(dateChooserBirthDay);
        panelChangeUserInformation.add(labelSex);
        panelChangeUserInformation.add(comboBoxSex);
        panelChangeUserInformation.add(labelEmail);
        panelChangeUserInformation.add(textFieldEmail);
        panelChangeUserInformation.add(buttonUpdate);
        panelChangeUserInformation.add(buttonBack);
    	getContentPane().add(panelChangeUserInformation);
    	
    	JLabel usernameLabel = new JLabel(username);
    	usernameLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
    	usernameLabel.setHorizontalAlignment(SwingConstants.CENTER);
    	usernameLabel.setBounds(0, 80, 384, 28);
    	panelChangeUserInformation.add(usernameLabel);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == buttonUpdate) {
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");  
			Date dob = dateChooserBirthDay.getDate();
			
			String name = textFieldName.getText();
			String addr = textFieldAddress.getText();
			String dobString = dateFormat.format(dob);
			String sex = comboBoxSex.getSelectedItem().toString();
			String email = textFieldEmail.getText();
			
			// Connect DB
			Connection conn = null;
			Statement stmt = null;
			try {
				conn = DriverManager.getConnection(Main.DB_URL, Main.USER, Main.PASS);
			} catch(SQLException se){
				//Handle errors for JDBC
				se.printStackTrace();
			} catch (Exception e1) {
				// TODO: handle exception
				e1.printStackTrace();
			}
			try {
				stmt = conn.createStatement();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			String sql = MessageFormat.format("update users "
					+ "set fullname = \"{0}\", address = \"{1}\", dob = \"{2}\", sex = \"{3}\", email = \"{4}\" "
					+ "where username = \"{5}\"", 
					name, addr, dobString, sex, email, username);
			
			try {
				stmt.executeUpdate(sql);
				JOptionPane.showMessageDialog(this,
		                "Update successfully",
		                "Message",
		                JOptionPane.INFORMATION_MESSAGE);
			} catch (SQLException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			
			try {
				stmt.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				conn.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			
		}
		else if (e.getSource() == buttonBack) {
			dispose();
		}
	}
}
