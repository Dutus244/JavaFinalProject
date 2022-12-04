package modulesForUsers;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class UserSettingScreen extends JFrame implements ActionListener {
	Container container;
	
	JPanel panelUser;
	
	JLabel labelAvatar;
	JLabel labelUsername;
	JLabel labelName;
	JLabel labelInfoName;
	JLabel labelAddress;
	JLabel labelInfoAddress;
	JLabel labelBirthDay;
	JLabel labelInfoBirthDay;
	JLabel labelSex;
	JLabel labelInfoSex;
	JLabel labelEmail;
	JLabel labelInfoEmail;
	
	JButton buttonChangeInformation;
	JButton buttonChangePassword;
	JButton buttonLogOut;
	
	public UserSettingScreen() {
		this.setTitle("USER SETTING");
        this.setSize(400,600);
        this.setLayout(new BorderLayout());
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setResizable(false);
        
        panelUser = new JPanel();
        panelUser.setPreferredSize(new Dimension(400,600 )); // Được sử dụng khi setSize đã có trong phần cha lớn.
        panelUser.setLayout(null);
        
        Icon avatar = new ImageIcon("source/image/iconUserMenu.png");
    	Image image = ((ImageIcon) avatar).getImage(); // transform it 
    	Image newimg = image.getScaledInstance(48, 48,  java.awt.Image.SCALE_SMOOTH);
    	avatar = new ImageIcon(newimg);
    	labelAvatar = new JLabel(avatar, JLabel.CENTER);
    	labelAvatar.setBounds(175,20,50,50);
    	
    	Font font1 = new Font("Times New Roman", Font.BOLD,25);
        Font font2 = new Font("Times New Roman", Font.PLAIN,15);
        Font font3 = new Font("Times New Roman", Font.PLAIN,10);
        Font font4 = new Font("Times New Roman", Font.BOLD,15);
    	
    	labelUsername = new JLabel("USERNAME", JLabel.CENTER);
    	labelUsername.setFont(font1);
    	labelUsername.setBounds(100,70,200,40);
    	
    	labelName = new JLabel("NAME");
    	labelName.setFont(font4);
    	labelName.setBounds(20,120,100,40);
    	
    	labelInfoName = new JLabel("DANG NGUYEN DUY");
    	labelInfoName.setFont(font2);
    	labelInfoName.setBounds(110,120,200,40);
    	
    	labelAddress = new JLabel("ADDRESS");
    	labelAddress.setFont(font4);
    	labelAddress.setBounds(20,160,100,40);
    	
    	labelInfoAddress = new JLabel("123 Nguyen Kiem");
    	labelInfoAddress.setFont(font2);
    	labelInfoAddress.setBounds(110,160,200,40);
    	
    	labelBirthDay = new JLabel("BIRTHDAY");
    	labelBirthDay.setFont(font4);
    	labelBirthDay.setBounds(20,200,100,40);
    	
    	labelInfoBirthDay = new JLabel("24/04/2002");
    	labelInfoBirthDay.setFont(font2);
    	labelInfoBirthDay.setBounds(110,200,200,40);
    	
    	labelSex = new JLabel("SEX");
    	labelSex.setFont(font4);
    	labelSex.setBounds(20,240,100,40);
    	
    	labelInfoSex = new JLabel("Male");
    	labelInfoSex.setFont(font2);
    	labelInfoSex.setBounds(110,240,200,40);
    	
    	labelEmail = new JLabel("EMAIL");
    	labelEmail.setFont(font4);
    	labelEmail.setBounds(20,280,100,40);
    	
    	labelInfoEmail = new JLabel("duynguyenth@gmail.com");
    	labelInfoEmail.setFont(font2);
    	labelInfoEmail.setBounds(110,280,200,40);
    	
    	buttonChangeInformation = new JButton("CHANGE INFORMATION");
    	buttonChangeInformation.setBounds(100,370,200,50);
        buttonChangeInformation.setFocusable(false);
        buttonChangeInformation.addActionListener(this);
        
        buttonChangePassword = new JButton("CHANGE PASSWORD");
    	buttonChangePassword.setBounds(100,430,200,50);
        buttonChangePassword.setFocusable(false);
        buttonChangePassword.addActionListener(this);
        
        buttonLogOut = new JButton("LOG OUT");
    	buttonLogOut.setBounds(100,490,200,50);
        buttonLogOut.setFocusable(false);
        buttonLogOut.addActionListener(this);
    	
    	panelUser.add(labelAvatar);
    	panelUser.add(labelUsername);
    	panelUser.add(labelName);
    	panelUser.add(labelInfoName);
    	panelUser.add(labelAddress);
    	panelUser.add(labelInfoAddress);
    	panelUser.add(labelBirthDay);
    	panelUser.add(labelInfoBirthDay);
    	panelUser.add(labelSex);
    	panelUser.add(labelInfoSex);
    	panelUser.add(labelEmail);
    	panelUser.add(labelInfoEmail);
    	panelUser.add(buttonChangeInformation);
    	panelUser.add(buttonChangePassword);
    	panelUser.add(buttonLogOut);
    	add(panelUser);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == buttonChangeInformation) {
			this.dispose();
            try{
                new ChangeUserInformationScreen();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
		}
		else if (e.getSource() == buttonChangePassword) {
			
		}
		else if (e.getSource() == buttonLogOut) {
			this.dispose();
			try{
                new LoginScreen();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
		}
	}

}
