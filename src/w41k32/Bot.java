/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package w41k32;

/**
 *
 * @author Cédric
 */
public class Bot {
    
    private Board board;
    private InterfaceHTTP http;

    public Bot() {
        this.board = new Board();
        this.http = new InterfaceHTTP();
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public InterfaceHTTP getHttp() {
        return http;
    }

    public void setHttp(InterfaceHTTP http) {
        this.http = http;
    }
    
}
