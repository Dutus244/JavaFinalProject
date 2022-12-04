package modulesForUsers;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.*;

import com.toedter.calendar.JDateChooser;

public class ChangeUserInformationScreen extends JFrame implements ActionListener {
	Container container;
	
	JPanel panelChangeUserInformation;
	
	JButton buttonAvatar;
	JTextField textFieldUsername;
	JLabel labelName;
	JTextField textFieldName;
	JLabel labelAddress;
	JTextField textFieldAddress;
	JLabel labelBirthDay;
	JDateChooser dateChooserBirthDay;
	JLabel labelSex;
	JComboBox comboBoxSex;
	JLabel labelEmail;
	JTextField textFieldEmail;
	
	JButton buttonUpdate;
	JButton buttonBack;
	
	java.util.Date date;
	public ChangeUserInformationScreen() {
		this.setTitle("CHANGE INFORMATION");
        this.setSize(400,450);
        this.setLayout(new BorderLayout());
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
    	buttonAvatar.setBounds(175,20,50,50);
    	
    	Font font1 = new Font("Times New Roman", Font.BOLD,25);
        Font font2 = new Font("Times New Roman", Font.PLAIN,15);
        Font font3 = new Font("Times New Roman", Font.PLAIN,10);
        Font font4 = new Font("Times New Roman", Font.BOLD,15);
    	
        textFieldUsername = new JTextField("USERNAME", JTextField.CENTER);
        textFieldUsername.setFont(font1);
        textFieldUsername.setBounds(100,80,200,30);
    	
    	labelName = new JLabel("NAME");
    	labelName.setFont(font4);
    	labelName.setBounds(20,120,100,40);
    	
    	textFieldName = new JTextField("DANG NGUYEN DUY");
    	textFieldName.setFont(font2);
    	textFieldName.setBounds(110,120,200,30);
    	
    	labelAddress = new JLabel("ADDRESS");
    	labelAddress.setFont(font4);
    	labelAddress.setBounds(20,160,100,40);
    	
    	textFieldAddress = new JTextField("123 Nguyen Kiem");
    	textFieldAddress.setFont(font2);
    	textFieldAddress.setBounds(110,160,200,30);
    	
    	labelBirthDay = new JLabel("BIRTHDAY");
    	labelBirthDay.setFont(font4);
    	labelBirthDay.setBounds(20,200,100,40);
    	
    	dateChooserBirthDay = new JDateChooser();
    	dateChooserBirthDay.setFont(font2);
    	dateChooserBirthDay.setBounds(110,200,200,30);
    	
    	try {
			date = new SimpleDateFormat("yyyy-MM-dd").parse("2002-04-24");
			dateChooserBirthDay.setDate(date);
			dateChooserBirthDay.setDateFormatString("dd/MM/yyyy");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
    	
    	labelSex = new JLabel("SEX");
    	labelSex.setFont(font4);
    	labelSex.setBounds(20,240,100,40);
    	
    	String[] sex = {"MALE", "FEMALE"};
    	
    	comboBoxSex = new JComboBox(sex);
    	comboBoxSex.setFont(font2);
    	comboBoxSex.setBounds(110,240,200,30);
    	
    	labelEmail = new JLabel("EMAIL");
    	labelEmail.setFont(font4);
    	labelEmail.setBounds(20,280,100,40);
    	
    	textFieldEmail = new JTextField("duynguyenth@gmail.com");
    	textFieldEmail.setFont(font2);
    	textFieldEmail.setBounds(110,280,200,30);
    	
    	buttonUpdate = new JButton("UPDATE STUDENT");
        buttonUpdate.setBounds(230,330,150,50);
        buttonUpdate.setFocusable(false);
        buttonUpdate.addActionListener(this);
        
        buttonBack = new JButton("BACK TO HOME");
        buttonBack.setBounds(70,330,150,50);
        buttonBack.setFocusable(false);
        buttonBack.addActionListener(this);
    	
        panelChangeUserInformation.add(buttonAvatar);
        panelChangeUserInformation.add(textFieldUsername);
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
    	add(panelChangeUserInformation);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == buttonUpdate) {
			
		}
		else if (e.getSource() == buttonBack) {
			
		}
	}

}
