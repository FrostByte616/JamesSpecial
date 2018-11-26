/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ee4023_project;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author tjbow
 */
public class LoginMenu extends JFrame 
{
    JFrame mainMenu;
    JTextField username, password;
    JLabel un, pw;
    JButton loginB;
    JPanel p;
    
    public LoginMenu()
    {
        mainMenu = new JFrame("Login");
        un = new JLabel("Username:");
        username = new JTextField("");
        pw = new JLabel("Password:");
        password = new JTextField("");
        loginB = new JButton("Login");
        
        username.setAlignmentX(Component.CENTER_ALIGNMENT);
        password.setAlignmentX(Component.CENTER_ALIGNMENT);
        loginB.setAlignmentX(Component.CENTER_ALIGNMENT);
        un.setAlignmentX(Component.CENTER_ALIGNMENT);
        pw.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        loginB.addActionListener(new ActionListener() 
        { 
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                String user = username.getText();
                String pass = password.getText();    	
                int userID = login(user, pass);
                System.out.println(userID);
                if(userID == -1 || userID == 0)
                {
                    RegisterMenu registerMenu = new RegisterMenu();
                    registerMenu.show();
                    mainMenu.hide();
                }
                else
                {
                    MainMenu newMenu = new MainMenu(userID, user);
                    newMenu.show();
                    mainMenu.hide();
                }
                //if userID = 0, user not in db -> handle that somehow
            } 
        });
        
        p = new JPanel();
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        
        p.add(Box.createRigidArea(new Dimension(0,20)));
        p.add(un);
        p.add(username);
        p.add(Box.createRigidArea(new Dimension(0,5)));
        p.add(pw);
        p.add(password);
        p.add(Box.createRigidArea(new Dimension(0,5)));
        p.add(loginB);
        p.add(Box.createRigidArea(new Dimension(0,5)));
        p.setBackground(Color.white);
        mainMenu.add(p);
        mainMenu.setSize(200,200);
    }
    
    @Override
    public void show()
    {
        mainMenu.show();
    }
    
    @Override
    public void hide()
    {
        mainMenu.hide();
    }

    private static int login(java.lang.String username, java.lang.String password) {
        ttt.james.server.TTTWebService_Service service = new ttt.james.server.TTTWebService_Service();
        ttt.james.server.TTTWebService port = service.getTTTWebServicePort();
        return port.login(username, password);
    }
}
