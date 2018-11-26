/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ee4023_project;

import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 *
 * @author tjbow
 */
public class Leaderboard extends JFrame
{
   int userId, playerCount;
   boolean addable = true;
   ArrayList<String> players = new ArrayList<String>();
   String [] columnNames = {"Player Name", "Wins", "Losses", "Draws"};
   String [][] data, list2;
   JFrame mainMenu;
   JScrollPane sp;
   JTable leaderboard;
   
   public Leaderboard()
   {
       populateBoard();
       fillBoard();
       leaderboard = new JTable(data, columnNames);
       sp = new JScrollPane(leaderboard); 
       mainMenu = new JFrame("Leaderboard");
       mainMenu.add(sp);
       mainMenu.setSize(350,350);
   }
   
   @Override
   public void show()
   {
       mainMenu.show();
   }
   
   public void populateBoard()
   {
       System.out.println("Populate called");
       String[] list = leagueTable().split("\n");
       list2 = new String[list.length][5];
       for (int i = 0; i < list.length; i++)
           list2[i]=list[i].split(",");
       
       System.out.println("Populate step1");
       for(int x = 0; x < list.length; x++)
       {
           if(!players.contains(list2[x][1]))
               players.add(list2[x][1]);
           if(!players.contains(list2[x][2]))
               players.add(list2[x][2]);
       }
       
       System.out.println(players);

       System.out.println("Populate step3");
       int newsize = players.size();
       System.out.println(newsize);
       data = new String[newsize][4];
       
       for(int i = 0; i < data.length; i++)
       {
           data[i][0]=players.get(i);
           data[i][1]="0";
           data[i][2]="0";
           data[i][3]="0";
       }
       System.out.println("Populate worked");
   }
   
   public void fillBoard()
   {
       for(int i = 0; i < list2.length;i++)
       {
           if(Integer.parseInt(list2[i][3])==1)
           {
               for(int j = 0; j < data.length; j++)
               {
                   if(list2[i][1].equals(data[j][0]))
                   {
                       int temp = Integer.parseInt(data[j][1]);
                       temp++;
                       data[j][1] = temp + "";
                   }
                   
                   if(list2[i][2].equals(data[j][0]))
                   {
                       int temp = Integer.parseInt(data[j][2]);
                       temp++;
                       data[j][2] = temp + "";
                   }
               }
           }
           else if(Integer.parseInt(list2[i][3])==2)
           {
               for(int j = 0; j < data.length; j++)
               {
                   if(list2[i][2].equals(data[j][0]))
                   {
                       int temp = Integer.parseInt(data[j][2]);
                       temp++;
                       data[j][2] = temp + "";
                   }
                   
                   if(list2[i][1].equals(data[j][0]))
                   {
                       int temp = Integer.parseInt(data[j][1]);
                       temp++;
                       data[j][1] = temp + "";
                   }
               }
           }
           else
           {
               for(int j = 0; j < data.length; j++)
               {
                   if(list2[i][1].equals(data[j][0]))
                   {
                       int temp = Integer.parseInt(data[j][3]);
                       temp++;
                       data[j][3] = temp + "";
                   }
                   
                   if(list2[i][2].equals(data[j][0]))
                   {
                       int temp = Integer.parseInt(data[j][3]);
                       temp++;
                       data[j][3] = temp + "";
                   }
               }
           }
       }
   }

    private static String leagueTable() {
        ttt.james.server.TTTWebService_Service service = new ttt.james.server.TTTWebService_Service();
        ttt.james.server.TTTWebService port = service.getTTTWebServicePort();
        return port.leagueTable();
    }
}
