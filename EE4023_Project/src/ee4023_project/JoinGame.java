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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

/**
 *
 * @author tjbow
 */
public class JoinGame extends JFrame 
{
    int userID;
    JFrame mainMenu;
    JFrame menu2;
    JScrollPane sp;
    JTable games;
    JButton joinGame, refresh;
    JTextField gameToJoin;
    JPanel p;
    
    public JoinGame(int userID)
    {
        this.userID = userID;
        createTable();
        sp = new JScrollPane(games); 
        joinGame = new JButton("Join Game");
        refresh = new JButton("Refresh");
        gameToJoin = new JTextField();
        
        sp.setAlignmentX(Component.CENTER_ALIGNMENT);
        joinGame.setAlignmentX(Component.CENTER_ALIGNMENT);
        refresh.setAlignmentX(Component.CENTER_ALIGNMENT);
        gameToJoin.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        joinGame.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
                {
                    int gid = Integer.parseInt(gameToJoin.getText());
                    joinGame(userID, gid);
                    Game2 newGame = new Game2(gid, userID);
                    newGame.show();
                    mainMenu.hide();
                    menu2.hide();
                }
        });
        
        refresh.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
                {
                    JoinGame jg = new JoinGame(userID);
                    jg.show();
                    mainMenu.hide();
                    menu2.hide();
                }
        });
        
        p = new JPanel();
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        
        p.add(Box.createRigidArea(new Dimension(0,25)));
        p.add(gameToJoin);
        p.add(Box.createRigidArea(new Dimension(0,5)));
        p.add(joinGame);
        p.add(Box.createRigidArea(new Dimension(0,5)));
        p.add(refresh);
        p.add(Box.createRigidArea(new Dimension(0,5)));
        
        mainMenu = new JFrame("Available Games");
        menu2 = new JFrame();
        menu2.add(p);
        menu2.setSize(200,200);
        mainMenu.add(sp);
        mainMenu.setSize(300,300);
    }
    
    @Override
    public void show()
    {
        mainMenu.show();
        menu2.show();
    }
    
    public void createTable()
    {
        String availGames = showOpenGames();
        
        if(availGames.equals("ERROR-NOGAMES"))
        {
            games = new JTable();
            System.out.println("1");
        }
        else if(availGames.equals("ERROR-DB"))
        {
            games = new JTable();
            System.out.println("2");
        }
        else
        {
            System.out.println("3");
            String [] columnNames = {"Games ID", "Player Name"};
            String [] temp = availGames.split("\n");
            String [][] list = new String [temp.length][3];
            
            for(int i = 0; i < temp.length; i++)
                list[i] = temp[i].split(",");
            
            String [][] data = new String [temp.length][2];
            
            for(int i = 0; i < temp.length; i++)
                for(int j = 0; j < 2; j++)
                    data[i][j] = list[i][j];
            
            games = new JTable(data, columnNames);
        }
    }

    private static String showOpenGames() {
        ttt.james.server.TTTWebService_Service service = new ttt.james.server.TTTWebService_Service();
        ttt.james.server.TTTWebService port = service.getTTTWebServicePort();
        return port.showOpenGames();
    }

    private static String joinGame(int uid, int gid) {
        ttt.james.server.TTTWebService_Service service = new ttt.james.server.TTTWebService_Service();
        ttt.james.server.TTTWebService port = service.getTTTWebServicePort();
        return port.joinGame(uid, gid);
    }
}
