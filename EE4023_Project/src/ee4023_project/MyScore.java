/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ee4023_project;

import java.awt.Dimension;
import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;

/**
 *
 * @author tjbow
 */
public class MyScore extends JFrame
{
    int userID;
    String username;
    String list2[][];
    int wins=0, losses=0, draws=0;
    JFrame mainMenu;
    JLabel win, loss, draw;
    
    public MyScore(int userID, String username)
    {
        this.userID = userID;
        this.username = username;
        populateScores();
        win = new JLabel("Wins: " + wins);
        loss = new JLabel("Losses: " + losses);
        draw = new JLabel("Draws: " + draws);
        JPanel p = new JPanel();
        
        p.add(Box.createRigidArea(new Dimension(0,50)));
        p.add(win);
        p.add(Box.createRigidArea(new Dimension(0,5)));
        p.add(loss);
        p.add(Box.createRigidArea(new Dimension(0,5)));
        p.add(draw);
        p.add(Box.createRigidArea(new Dimension(0,5)));
        
        mainMenu = new JFrame("My Score");
        mainMenu.add(p);
        mainMenu.setSize(300,100);
        
    }
    
    public void populateScores()
    {
       System.out.println("Populate called");
       String[] list = leagueTable().split("\n");
       list2 = new String[list.length][5];
       for (int i = 0; i < list.length; i++)
           list2[i]=list[i].split(",");
       
       for(int x = 0; x < list2.length; x++)
       {
           if(list2[x][1].equals(username))
           {
               switch(list2[x][4])
               {
                   case "1": wins++ ; break;
                   case "2": losses++ ; break;
                   case "3": draws++ ; break;   
               }
           }
           else if(list2[x][2].equals(username))
           {
               switch(list2[x][4])
               {
                   case "1": losses++ ; break;
                   case "2": wins++ ; break;
                   case "3": draws++ ; break;   
               }
           }
       }
    }
    
    @Override
    public void show()
    {
        mainMenu.show();
    }

    private static String leagueTable() {
        ttt.james.server.TTTWebService_Service service = new ttt.james.server.TTTWebService_Service();
        ttt.james.server.TTTWebService port = service.getTTTWebServicePort();
        return port.leagueTable();
    }
}
