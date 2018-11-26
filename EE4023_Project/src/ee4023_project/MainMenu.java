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
public class MainMenu extends JFrame 
{
    int userID;
    JFrame mainMenu;
    JButton score, leaderboard, createGame, joinGame;
    //JTable availGames;
    JLabel gameName, availGames;
    JTextField gameToJoin;
    JPanel p;
    
    public MainMenu(int userID)
    {
        this.userID = userID;
        mainMenu = new JFrame("Main Menu");
        score = new JButton("Score");
        leaderboard = new JButton("Leaderboard");
        createGame = new JButton("Create Game");
        joinGame = new JButton("Join Game");
        gameName = new JLabel("Enter the Game ID you want to join:");
        gameToJoin = new JTextField();
        availGames = new JLabel(showOpenGames());
        
        score.setAlignmentX(Component.CENTER_ALIGNMENT);
        leaderboard.setAlignmentX(Component.CENTER_ALIGNMENT);
        createGame.setAlignmentX(Component.CENTER_ALIGNMENT);
        joinGame.setAlignmentX(Component.CENTER_ALIGNMENT);
        gameName.setAlignmentX(Component.CENTER_ALIGNMENT);
        gameToJoin.setAlignmentX(Component.CENTER_ALIGNMENT);
        availGames.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        createGame.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
                {
                    newGame(userID);
                }
        });
        
        joinGame.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
                {
                    int gID = Integer.parseInt(gameToJoin.getText());
                    joinGame(userID, gID);
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
                    MyScore s = new MyScore(userID);
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
        p.add(availGames);
        p.add(Box.createRigidArea(new Dimension(0,5)));
        p.add(gameName);
        p.add(Box.createRigidArea(new Dimension(0,5)));
        p.add(gameToJoin);
        p.add(Box.createRigidArea(new Dimension(0,5)));
        p.add(joinGame);
        p.add(Box.createRigidArea(new Dimension(0,5)));
        mainMenu.add(p);
        mainMenu.setSize(300,300);
        
        
    }
    
    @Override
    public void show()
    {
        mainMenu.show();
    }

    private static String showOpenGames() {
        ttt.james.server.TTTWebService_Service service = new ttt.james.server.TTTWebService_Service();
        ttt.james.server.TTTWebService port = service.getTTTWebServicePort();
        return port.showOpenGames();
    }

    private static String newGame(int uid) {
        ttt.james.server.TTTWebService_Service service = new ttt.james.server.TTTWebService_Service();
        ttt.james.server.TTTWebService port = service.getTTTWebServicePort();
        return port.newGame(uid);
    }

    private static String joinGame(int uid, int gid) {
        ttt.james.server.TTTWebService_Service service = new ttt.james.server.TTTWebService_Service();
        ttt.james.server.TTTWebService port = service.getTTTWebServicePort();
        return port.joinGame(uid, gid);
    }
}
