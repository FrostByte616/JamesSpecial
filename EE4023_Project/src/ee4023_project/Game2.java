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
 * @author Michael
 */
public class Game2 extends JFrame
{
    JFrame gameMenu;
    JTextField moveRow, moveCol, rField, cField;
    JButton makeMove;
    JLabel rLabel, cLabel;
    int gameState = 0;
    int row, col;
    JPanel g;
    String[][] grid = new String[3][3];
    // X|O|X
    // -+-+-
    // O|X|O
    // -+-+-
    // X|O|X

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
    
    private void setMove()
    {
    	int rowInt = Integer.parseInt(moveRow.getText());
    	int colInt = Integer.parseInt(moveCol.getText());
        row = rowInt;
        col = colInt;
        
    }
    
    private void checkAll(int gameID, int playerID)
    {
        int squareTaken = Integer.parseInt(checkSquare(row, col, gameID));
             if (squareTaken == 0){
                 takeSquare(row, col, gameID, playerID);
                // redrawBoard(row, col);
             }
             String[] moves = getBoard(gameID).split("\n");
             if (moves.length > 4)
                 if((Integer.parseInt(checkWin(gameID)) == 1))
                 {
                         setGameState(gameID, 1);
                 }else if((Integer.parseInt(checkWin(gameID)) == 2))
                 {
                         setGameState(gameID, 2);
                 }else if((Integer.parseInt(checkWin(gameID)) == 3))
                 {
                         setGameState(gameID, 3);
                 }
    }
//    private void drawBoard()
//    {
//        
//    }
//    
//    private void redrawBoard(int drawR, int drawC)
//    {
//        
//    }
//    
//  
    @Override
    public void show()
    {
        gameMenu.show();
    }
    public Game2(int gID, int pID)
    {
        gameMenu = new JFrame("Tic-Tac-Toe");
        rLabel = new JLabel("Row:");
        rField = new JTextField("");
        cLabel = new JLabel("Column:");
        cField = new JTextField("");
        makeMove = new JButton("Make Move");
        
        makeMove.addActionListener(new ActionListener() { 
        @Override
        public void actionPerformed(ActionEvent e) 
        {
            setMove();
            //redrawBoard(row, col);
            checkAll(gID, pID);
        }
        });
        
        
        rField.setAlignmentX(Component.CENTER_ALIGNMENT);
        cField.setAlignmentX(Component.CENTER_ALIGNMENT);
        makeMove.setAlignmentX(Component.CENTER_ALIGNMENT);
        rLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        cLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        g = new JPanel();
        g.setLayout(new BoxLayout(g, BoxLayout.Y_AXIS));
        
        g.add(Box.createRigidArea(new Dimension(0,20)));
        g.add(rLabel);
        g.add(rField);
        g.add(Box.createRigidArea(new Dimension(0,5)));
        g.add(cLabel);
        g.add(cField);
        g.add(Box.createRigidArea(new Dimension(0,5)));
        g.add(makeMove);
        g.add(Box.createRigidArea(new Dimension(0,5)));
        g.setBackground(Color.white);
        gameMenu.add(g);
        gameMenu.setSize(200,200);
        
        for (int i = 0; i<3; i++)
        {
            for (int j = 0; j<3; j++)
                grid[i][j] = " ";
        }
        
        //int state = Integer.parseInt(getGameState(gID));
        //if (state !=-1){
        //drawBoard();
            /*while ((Integer.parseInt(getGameState(gID))) == 0)
            {
             //drawBoard();
             int squareTaken = Integer.parseInt(checkSquare(row, col, gID));
             if (squareTaken == 0){
                 takeSquare(row, col, gID, pID);
                 redrawBoard(row, col);
             }
             String[] moves = getBoard(gID).split("\n");
             if (moves.length > 4)
                 if((Integer.parseInt(checkWin(gID)) == 1))
                 {
                         setGameState(gID, 1);
                 }else if((Integer.parseInt(checkWin(gID)) == 2))
                 {
                         setGameState(gID, 2);
                 }else if((Integer.parseInt(checkWin(gID)) == 3))
                 {
                         setGameState(gID, 3);
                 }*/
            }        
          }
        
    
