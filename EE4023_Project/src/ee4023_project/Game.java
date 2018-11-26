/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ee4023_project;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

/**
 *
 * @author tjbow
 */
public class Game extends JFrame
{
    int numMoves;
    JFrame gameMenu;
    JTextField moveRow, moveCol;
    JButton makeMove;
    // X|O|X
    // -+-+-
    // O|X|O
    // -+-+-
    // X|O|X

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
}
