package ee4023_project;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.AbstractButton;
/**
 *
 * @author Michael
 */
public class Game2 extends JFrame
{
    int pID, gID;
    String [] plays;
    JFrame gameMenu;
    int numMoves = 0;
    JButton x0y0, x0y1, x0y2, x1y0, x1y1, x1y2, x2y0, x2y1, x2y2;
    JPanel g;    
    
        private static String checkSquare(int x, int y, int gid) {
        ttt.james.server.TTTWebService_Service service = new ttt.james.server.TTTWebService_Service();
        ttt.james.server.TTTWebService port = service.getTTTWebServicePort();
        return port.checkSquare(x, y, gid);
    }

    private static String checkWin(int gid) {
        ttt.james.server.TTTWebService_Service service = new ttt.james.server.TTTWebService_Service();
        ttt.james.server.TTTWebService port = service.getTTTWebServicePort();
        return port.checkWin(gid);
    }

    private static String getBoard(int gid) {
        ttt.james.server.TTTWebService_Service service = new ttt.james.server.TTTWebService_Service();
        ttt.james.server.TTTWebService port = service.getTTTWebServicePort();
        return port.getBoard(gid);
    }

    private static String getGameState(int gid) {
        ttt.james.server.TTTWebService_Service service = new ttt.james.server.TTTWebService_Service();
        ttt.james.server.TTTWebService port = service.getTTTWebServicePort();
        return port.getGameState(gid);
    }

    private static String setGameState(int gid, int gstate) {
        ttt.james.server.TTTWebService_Service service = new ttt.james.server.TTTWebService_Service();
        ttt.james.server.TTTWebService port = service.getTTTWebServicePort();
        return port.setGameState(gid, gstate);
    }

    private static String takeSquare(int x, int y, int gid, int pid) {
        ttt.james.server.TTTWebService_Service service = new ttt.james.server.TTTWebService_Service();
        ttt.james.server.TTTWebService port = service.getTTTWebServicePort();
        return port.takeSquare(x, y, gid, pid);
    }
    
    public int playedLast(int gameID)
    {
        String boardGettable = getBoard(gameID);
        if (boardGettable.equals("ERROR-NOMOVES"))
        {
            //handle error
            return 0;
        }else if (boardGettable.equals("ERROR-DB"))
        {
            //handle error
            return 0;
        }else{
        plays = getBoard(gameID).split("\n");
        String[][] playsSplit = new String[plays.length][];
        for (int i = 0; i < plays.length; i++)
            playsSplit[i]=plays[i].split(",");
        int lastPlayedID = Integer.parseInt(playsSplit[playsSplit.length-1][0]);
        return lastPlayedID;  
        }
    }
    
    public int playedFirst(int gameID)
    {
        String boardGettable = getBoard(gameID);
        if (boardGettable.equals("ERROR-NOMOVES"))
        {
            return 0;
        }else if (boardGettable.equals("ERROR-DB"))
        {
            //handle error
            return -1;
        }else{
        plays = getBoard(gameID).split("\n");
        String[][] playsSplit = new String[plays.length][];
        for (int i = 0; i < plays.length; i++)
            playsSplit[i]=plays[i].split(",");
        int lastPlayedID = Integer.parseInt(playsSplit[0][0]);
        return lastPlayedID;  
        }
    }

    public Game2(int gID, int pID)
    {
        this.pID = pID;
        this.gID = gID;
        gameMenu = new JFrame("Tic-Tac-Toe");
        x0y0 = new JButton("");
        x0y1 = new JButton("");
        x0y2 = new JButton("");
        x1y0 = new JButton("");
        x1y1 = new JButton("");
        x1y2 = new JButton("");
        x2y0 = new JButton("");
        x2y1 = new JButton("");
        x2y2 = new JButton("");
        
        x0y0.addActionListener(new ActionListener() { 
        @Override
        public void actionPerformed(ActionEvent e) 
        {
            if(pID != playedLast(gID))
            {
                System.out.println("0");
                if (!(checkSquare(0,0,gID).equals("ERROR-DB")))
                        {
                            System.out.println("1");
                            if((Integer.parseInt(checkSquare(0,0,gID))) == 0)
                                {
                                    System.out.println("2");
                                    if (pID == playedFirst(gID) || playedFirst(gID) == 0)
                                    {
                                        numMoves++;
                                        System.out.println("3");
                                        x0y0.setText("X");
                                        takeSquare(0,0, gID, pID);
                                        
                                            if(Integer.parseInt(getGameState(gID))!=-1 && Integer.parseInt(getGameState(gID))!=0)
                                            {
                                                setGameState(gID, Integer.parseInt(checkWin(gID)));
                                                gameMenu.hide();
                                            //else error handling
                                            }
                                        
                                    }else 
                                    {
                                        numMoves++;
                                        System.out.println("3");
                                        x0y0.setText("O");
                                        takeSquare(0,0, gID, pID);
                                        
                                            System.out.println("FUCKTHISGODDAMNIT");
                                            if(Integer.parseInt(checkWin(gID))!=-1 && Integer.parseInt(checkWin(gID))!=0)
                                            {
                                                setGameState(gID, Integer.parseInt(checkWin(gID)));
                                                gameMenu.hide();
                                            //else error handling
                                            }
                                        
                                    }
                                }
                        }
                
            }
            
        }//handle errors
        });
        x0y1.addActionListener(new ActionListener() { 
        @Override
        public void actionPerformed(ActionEvent e) 
        {
            if(pID != playedLast(gID))
            {
                System.out.println("0");
                if (!(checkSquare(0,1,gID).equals("ERROR-DB")))
                        {
                            System.out.println("1");
                            if((Integer.parseInt(checkSquare(0,1,gID))) == 0)
                                {
                                    System.out.println("2");
                                    if (pID == playedFirst(gID) || playedFirst(gID) == 0)
                                    {
                                        numMoves++;
                                        System.out.println("3");
                                        x0y1.setText("X");
                                        takeSquare(0,1, gID, pID);
                                    }else 
                                    {
                                        numMoves++;
                                        System.out.println("3");
                                        x0y1.setText("O");
                                        takeSquare(0,1, gID, pID);
                                    }
                                }
                        }

                    if(Integer.parseInt(getGameState(gID))!=-1 && Integer.parseInt(getGameState(gID))!=0)
                    {
                        setGameState(gID, Integer.parseInt(checkWin(gID)));
                        gameMenu.hide();
                    //else error handling
                    }
                
            }
            
        }//handle errors
        });
        x0y2.addActionListener(new ActionListener() { 
        @Override
        public void actionPerformed(ActionEvent e) 
        {
            if(pID != playedLast(gID))
            {
                System.out.println("0");
                if (!(checkSquare(0,2,gID).equals("ERROR-DB")))
                        {
                            System.out.println("1");
                            if((Integer.parseInt(checkSquare(0,2,gID))) == 0)
                                {
                                    System.out.println("2");
                                    if (pID == playedFirst(gID) || playedFirst(gID) == 0)
                                    {
                                        numMoves++;
                                        System.out.println("3");
                                        x0y2.setText("X");
                                        takeSquare(0,2, gID, pID);
                                    }else 
                                    {
                                        numMoves++;
                                        System.out.println("3");
                                        x0y2.setText("O");
                                        takeSquare(0,2, gID, pID);
                                    }
                                }
                        }

                    if(Integer.parseInt(checkWin(gID)) > 0)
                    {
                        setGameState(gID, Integer.parseInt(checkWin(gID)));
                        gameMenu.hide();
                    //else error handling
                    }
                
            }
            
        }//handle errors
        });
        x1y0.addActionListener(new ActionListener() { 
        @Override
        public void actionPerformed(ActionEvent e) 
        {
            if(pID != playedLast(gID))
            {
                System.out.println("0");
                if (!(checkSquare(1,0,gID).equals("ERROR-DB")))
                        {
                            System.out.println("1");
                            if((Integer.parseInt(checkSquare(1,0,gID))) == 0)
                                {
                                    System.out.println("2");
                                    if (pID == playedFirst(gID) || playedFirst(gID) == 0)
                                    {
                                        numMoves++;
                                        System.out.println("3");
                                        x1y0.setText("X");
                                        takeSquare(1,0, gID, pID);
                                    }else 
                                    {
                                        numMoves++;
                                        System.out.println("3");
                                        x1y0.setText("O");
                                        takeSquare(1,0, gID, pID);
                                    }
                                }
                        }

                    if(Integer.parseInt(getGameState(gID))!=-1 && Integer.parseInt(getGameState(gID))!=0)
                    {
                        setGameState(gID, Integer.parseInt(checkWin(gID)));
                        gameMenu.hide();
                    //else error handling
                    }
                
            }
            
        }//handle errors
        });
        x1y1.addActionListener(new ActionListener() { 
        @Override
        public void actionPerformed(ActionEvent e) 
        {
            if(pID != playedLast(gID))
            {
                System.out.println("0");
                if (!(checkSquare(1,1,gID).equals("ERROR-DB")))
                        {
                            System.out.println("1");
                            if((Integer.parseInt(checkSquare(1,1,gID))) == 0)
                                {
                                    System.out.println("2");
                                    if (pID == playedFirst(gID) || playedFirst(gID) == 0)
                                    {
                                        numMoves++;
                                        System.out.println("3");
                                        x1y1.setText("X");
                                        takeSquare(1,1, gID, pID);
                                    }else 
                                    {
                                        numMoves++;
                                        System.out.println("3");
                                        x1y1.setText("O");
                                        takeSquare(1,1, gID, pID);
                                    }
                                }
                        }

                    if(Integer.parseInt(getGameState(gID))!=-1 && Integer.parseInt(getGameState(gID))!=0)
                    {
                        setGameState(gID, Integer.parseInt(checkWin(gID)));
                        gameMenu.hide();
                    //else error handling
                    }
                
            }
            
        }//handle errors
        });
        x1y2.addActionListener(new ActionListener() { 
        @Override
        public void actionPerformed(ActionEvent e) 
        {
            if(pID != playedLast(gID))
            {
                System.out.println("0");
                if (!(checkSquare(1,2,gID).equals("ERROR-DB")))
                        {
                            System.out.println("1");
                            if((Integer.parseInt(checkSquare(1,2,gID))) == 0)
                                {
                                    System.out.println("2");
                                    if (pID == playedFirst(gID) || playedFirst(gID) == 0)
                                    {
                                        numMoves++;
                                        System.out.println("3");
                                        x1y2.setText("X");
                                        takeSquare(1,2, gID, pID);
                                    }else 
                                    {
                                        numMoves++;
                                        System.out.println("3");
                                        x1y2.setText("O");
                                        takeSquare(1,2, gID, pID);
                                    }
                                }
                        }

                    if(Integer.parseInt(getGameState(gID))!=-1 && Integer.parseInt(getGameState(gID))!=0)
                    {
                        setGameState(gID, Integer.parseInt(checkWin(gID)));
                        gameMenu.hide();
                    //else error handling
                    }
                
            }
            
        }//handle errors
        });
        x2y0.addActionListener(new ActionListener() { 
        @Override
        public void actionPerformed(ActionEvent e) 
        {
            if(pID != playedLast(gID))
            {
                System.out.println("0");
                if (!(checkSquare(2,0,gID).equals("ERROR-DB")))
                        {
                            System.out.println("1");
                            if((Integer.parseInt(checkSquare(2,0,gID))) == 0)
                                {
                                    System.out.println("2");
                                    if (pID == playedFirst(gID) || playedFirst(gID) == 0)
                                    {
                                        numMoves++;
                                        System.out.println("3");
                                        x2y0.setText("X");
                                        takeSquare(2,0, gID, pID);
                                    }else 
                                    {
                                        numMoves++;
                                        System.out.println("3");
                                        x2y0.setText("O");
                                        takeSquare(2,0, gID, pID);
                                    }
                                }
                        }

                    if(Integer.parseInt(getGameState(gID))!=-1 && Integer.parseInt(getGameState(gID))!=0)
                    {
                        setGameState(gID, Integer.parseInt(checkWin(gID)));
                        gameMenu.hide();
                    //else error handling
                    }
                
            }
            
        }//handle errors
        });
        x2y1.addActionListener(new ActionListener() { 
        @Override
        public void actionPerformed(ActionEvent e) 
        {
            if(pID != playedLast(gID))
            {
                System.out.println("0");
                if (!(checkSquare(2,1,gID).equals("ERROR-DB")))
                        {
                            System.out.println("1");
                            if((Integer.parseInt(checkSquare(2,1,gID))) == 0)
                                {
                                    System.out.println("2");
                                    if (pID == playedFirst(gID) || playedFirst(gID) == 0)
                                    {
                                        numMoves++;
                                        System.out.println("3");
                                        x2y1.setText("X");
                                        takeSquare(2,1, gID, pID);
                                    }else 
                                    {
                                        numMoves++;
                                        System.out.println("3");
                                        x2y1.setText("O");
                                        takeSquare(2,1, gID, pID);
                                    }
                                }
                        }

                    if(Integer.parseInt(getGameState(gID))!=-1 && Integer.parseInt(getGameState(gID))!=0)
                    {
                        setGameState(gID, Integer.parseInt(checkWin(gID)));
                        gameMenu.hide();
                    //else error handling
                    }
                
            }
            
        }//handle errors
        });
        x2y2.addActionListener(new ActionListener() { 
        @Override
        public void actionPerformed(ActionEvent e) 
        {
            if(pID != playedLast(gID))
            {
                System.out.println("0");
                if (!(checkSquare(2,2,gID).equals("ERROR-DB")))
                        {
                            System.out.println("1");
                            if((Integer.parseInt(checkSquare(2,2,gID))) == 0)
                                {
                                    System.out.println("2");
                                    if (pID == playedFirst(gID) || playedFirst(gID) == 0)
                                    {
                                        numMoves++;
                                        System.out.println("3");
                                        x2y2.setText("X");
                                        takeSquare(2,2, gID, pID);
                                    }else 
                                    {
                                        numMoves++;
                                        System.out.println("3");
                                        x2y2.setText("O");
                                        takeSquare(2,2, gID, pID);
                                    }
                                }
                        }
 
                    if(Integer.parseInt(getGameState(gID))!=-1 && Integer.parseInt(getGameState(gID))!=0)
                    {
                        setGameState(gID, Integer.parseInt(checkWin(gID)));
                        gameMenu.hide();
                    //else error handling
                    }
                
            }
            
        }//handle errors
        });
        
        g = new JPanel();
        g.setLayout(new GridLayout(3, 3));
        g.add(x0y0);
        g.add(x0y1);
        g.add(x0y2);
        g.add(x1y0);
        g.add(x1y1);
        g.add(x1y2);
        g.add(x2y0);
        g.add(x2y1);
        g.add(x2y2);
        g.setBackground(Color.white);
        gameMenu.add(g);
        gameMenu.setSize(200,200);
        gameMenu.show();
        run();
    }
    
    public void run()
    { 
       if(Integer.parseInt(checkSquare(0,0,gID)) == 1 && (x0y0.getText().equals("")))
       {
           if(pID == playedFirst(gID))
                x0y0.setText("O");
           else x0y0.setText("X");
       }
       if(Integer.parseInt(checkSquare(0,1,gID)) == 1 && (x0y1.getText().equals("")))
       {
           if(pID == playedFirst(gID))
                x0y1.setText("O");
           else x0y1.setText("X");
       }
       if(Integer.parseInt(checkSquare(0,2,gID)) == 1 && (x0y2.getText().equals("")))
       {
           if(pID == playedFirst(gID))
                x0y2.setText("O");
           else x0y2.setText("X");
       }
       if(Integer.parseInt(checkSquare(1,0,gID)) == 1 && (x1y0.getText().equals("")))
       {
           if(pID == playedFirst(gID))
                x1y0.setText("O");
           else x1y0.setText("X");
       }
       if(Integer.parseInt(checkSquare(1,1,gID)) == 1 && (x1y1.getText().equals("")))
       {
           if(pID == playedFirst(gID))
                x1y1.setText("O");
           else x1y1.setText("X");
       }
       if(Integer.parseInt(checkSquare(1,2,gID)) == 1 && (x1y2.getText().equals("")))
       {
           if(pID == playedFirst(gID))
                x1y2.setText("O");
           else x1y2.setText("X");
       }
       if(Integer.parseInt(checkSquare(2,0,gID)) == 1 && (x2y0.getText().equals("")))
       {
           if(pID == playedFirst(gID))
                x2y0.setText("O");
           else x2y0.setText("X");
       }
       if(Integer.parseInt(checkSquare(2,1,gID)) == 1 && (x2y1.getText().equals("")))
       {
           if(pID == playedFirst(gID))
                x2y1.setText("O");
           else x2y1.setText("X");
       }
       if(Integer.parseInt(checkSquare(2,2,gID)) == 1 && (x2y2.getText().equals("")))
       {
           if(pID == playedFirst(gID))
                x2y2.setText("O");
           else x2y2.setText("X");
       }
    }
}