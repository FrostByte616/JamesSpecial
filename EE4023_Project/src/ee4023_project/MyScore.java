/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ee4023_project;

import javax.swing.JFrame;
import javax.swing.JTable;

/**
 *
 * @author tjbow
 */
public class MyScore extends JFrame
{
    int userId;
    JFrame mainMenu;
    JTable myScores;
    
    public MyScore(int userID)
    {
        
    }
    
    @Override
    public void show()
    {
        mainMenu.show();
    }
}
