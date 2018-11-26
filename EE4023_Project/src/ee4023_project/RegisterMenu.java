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
public class RegisterMenu extends JFrame
{
    int userID;
    JFrame mainMenu;
    JTextField username, password, name, surname;
    JLabel un, pw, n, sn;
    JButton registerB;
    JPanel p;
    
    public RegisterMenu()
    {
        mainMenu = new JFrame("Register");
        un = new JLabel("Username:");
        username = new JTextField("");
        pw = new JLabel("Password:");
        password = new JTextField("");
        n = new JLabel("Name:");
        name = new JTextField("");
        sn = new JLabel("Surname:");
        surname = new JTextField("");
        registerB = new JButton("Register");
        
        username.setAlignmentX(Component.CENTER_ALIGNMENT);
        password.setAlignmentX(Component.CENTER_ALIGNMENT);
        registerB.setAlignmentX(Component.CENTER_ALIGNMENT);
        un.setAlignmentX(Component.CENTER_ALIGNMENT);
        pw.setAlignmentX(Component.CENTER_ALIGNMENT);
        name.setAlignmentX(Component.CENTER_ALIGNMENT);
        surname.setAlignmentX(Component.CENTER_ALIGNMENT);
        n.setAlignmentX(Component.CENTER_ALIGNMENT);
        sn.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        registerB.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
                {
                    String user = username.getText();
                    String pass = password.getText();
                    String names = name.getText();
                    String snames = surname.getText();
                    
                    register(user, pass, names, snames);
                    userID = login(user, pass);
                    MainMenu newMenu = new MainMenu(userID, user);
                    newMenu.show();
                    mainMenu.hide();
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
        p.add(n);
        p.add(name);
        p.add(Box.createRigidArea(new Dimension(0,5)));
        p.add(sn);
        p.add(surname);
        p.add(Box.createRigidArea(new Dimension(0,5)));
        p.add(registerB);
        p.add(Box.createRigidArea(new Dimension(0,5)));
        p.setBackground(Color.white);
        mainMenu.add(p);
        mainMenu.setSize(200,300);
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

    private static String register(java.lang.String username, java.lang.String password, java.lang.String name, java.lang.String surname) {
        ttt.james.server.TTTWebService_Service service = new ttt.james.server.TTTWebService_Service();
        ttt.james.server.TTTWebService port = service.getTTTWebServicePort();
        return port.register(username, password, name, surname);
    }
    
    private static int login(java.lang.String username, java.lang.String password) {
        ttt.james.server.TTTWebService_Service service = new ttt.james.server.TTTWebService_Service();
        ttt.james.server.TTTWebService port = service.getTTTWebServicePort();
        return port.login(username, password);
    }
}
