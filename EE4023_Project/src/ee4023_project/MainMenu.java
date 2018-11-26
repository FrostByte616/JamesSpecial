/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ee4023_project;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;

/**
 *
 * @author tjbow
 */
public class MainMenu extends JFrame implements Runnable
{
    int userID;
    String username;
    JFrame mainMenu;
    JButton score, leaderboard, createGame, joinGame;
    JPanel p;
    
    public MainMenu(int userID, String username)
    {
        this.userID = userID;
        this.username = username;
        mainMenu = new JFrame("Main Menu");
        score = new JButton("Score");
        leaderboard = new JButton("Leaderboard");
        createGame = new JButton("Create Game");
        joinGame = new JButton("Join Game");
        
        score.setAlignmentX(Component.CENTER_ALIGNMENT);
        leaderboard.setAlignmentX(Component.CENTER_ALIGNMENT);
        createGame.setAlignmentX(Component.CENTER_ALIGNMENT);
        joinGame.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        createGame.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
                {
                    int gid = Integer.parseInt(newGame(userID));
                    
                    Game2 newgame = new Game2(gid, userID);
                    //newgame.show();
                    mainMenu.hide();
                }
        });
        
        
        joinGame.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
                {
                    JoinGame jg = new JoinGame(userID);
                    jg.show();
                    mainMenu.hide();
                }
        });
        
        leaderboard.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
                {
                    Leaderboard l = new Leaderboard();
                    l.show();  
                }
        });
        
        score.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
                {
                    MyScore s = new MyScore(userID, username);
                    s.show();  
                }
        });
        
        p = new JPanel();
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        
        p.add(Box.createRigidArea(new Dimension(0,5)));
        p.add(score);
        p.add(Box.createRigidArea(new Dimension(0,5)));
        p.add(leaderboard);
        p.add(Box.createRigidArea(new Dimension(0,5)));
        p.add(createGame);
        p.add(Box.createRigidArea(new Dimension(0,5)));
        p.add(joinGame);
        p.add(Box.createRigidArea(new Dimension(0,5)));
        mainMenu.add(p);
        mainMenu.setSize(200,200);
        
        
    }
    
    @Override
    public void run()
    {
        
    }
    
    @Override
    public void show()
    {
        mainMenu.show();
    }
    
    private static String newGame(int uid) {
        ttt.james.server.TTTWebService_Service service = new ttt.james.server.TTTWebService_Service();
        ttt.james.server.TTTWebService port = service.getTTTWebServicePort();
        return port.newGame(uid);
    }
}
