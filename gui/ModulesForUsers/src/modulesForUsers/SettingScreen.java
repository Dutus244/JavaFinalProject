package modulesForUsers;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class SettingScreen extends JFrame implements ActionListener {
	JPanel panelMenuSetting;
	JPanel panelSetting;
	
	JButton buttonGeneral;
	JButton buttonActiveStatus;
	JButton buttonNotification;
	
	JLabel labelGeneral;
	public SettingScreen() {
		this.setTitle("SETTING");
        this.setSize(600,600);
        this.setLayout(new BorderLayout());
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setResizable(false);
        
        panelMenuSetting = new JPanel();
        panelMenuSetting.setPreferredSize(new Dimension(300,600 )); // Được sử dụng khi setSize đã có trong phần cha lớn.
        panelMenuSetting.setLayout(null);
        panelMenuSetting.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        
        buttonGeneral = new JButton("GENERAL");
        buttonGeneral.setBounds(0,0,300,50);
        buttonGeneral.addActionListener(this);
        
        panelMenuSetting.add(buttonGeneral);
        
        panelSetting = new JPanel();
        panelSetting.setPreferredSize(new Dimension(300,600 )); // Được sử dụng khi setSize đã có trong phần cha lớn.
        panelSetting.setLayout(null);
        panelSetting.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        
        labelGeneral = new JLabel("General");
        labelGeneral.setBounds(20,10,200,30);
        
        panelSetting.add(labelGeneral);
        
        add(panelMenuSetting, BorderLayout.WEST);
        add(panelSetting, BorderLayout.EAST);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
