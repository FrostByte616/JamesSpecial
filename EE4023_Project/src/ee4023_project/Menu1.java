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
import javax.swing.JPanel;

/**
 *
 * @author tjbow
 */
public class Menu1 extends JFrame
{
    JFrame mainMenu;
    JButton login,register;
    JPanel p;
    
    public Menu1()
    {
        mainMenu = new JFrame("Main Menu");
        login = new JButton("Login");
        register = new JButton("Register");
        login.setAlignmentX(Component.CENTER_ALIGNMENT);
        register.setAlignmentX(Component.CENTER_ALIGNMENT);
        login.setAlignmentY(0.2f);
        register.setAlignmentY(0.6f);
        
        login.addActionListener(new ActionListener() 
        { 
            @Override
            public void actionPerformed(ActionEvent e) 
            { 
            LoginMenu loginMenu = new LoginMenu();
            loginMenu.show();
            mainMenu.hide();
            } 
        });
        
        register.addActionListener(new ActionListener() 
        { 
            @Override
            public void actionPerformed(ActionEvent e) 
            { 
            RegisterMenu registerMenu = new RegisterMenu();
            registerMenu.show();
            mainMenu.hide();
            } 
        });
        
        p = new JPanel();
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        
        p.add(Box.createRigidArea(new Dimension(0,40)));
        p.add(login);
        p.add(Box.createRigidArea(new Dimension(0,5)));
        p.add(register);
        p.setBackground(Color.white);
        mainMenu.add(p);
        mainMenu.setSize(200,200);
        //mainMenu.show();
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
}
