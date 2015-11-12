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
public class Board {
    
    private int nbrActionLeft;
    private Player self;
    private Player opponent;

    public Board() {
        this.nbrActionLeft = 30;
        this.self = new Player("W41K32");
        this.opponent = new Player(null);
    }

    public int getNbrActionLeft() {
        return nbrActionLeft;
    }

    public void setNbrActionLeft(int nbrActionLeft) {
        this.nbrActionLeft = nbrActionLeft;
    }

    public Player getSelf() {
        return self;
    }

    public void setSelf(Player self) {
        this.self = self;
    }

    public Player getOpponent() {
        return opponent;
    }

    public void setOpponent(Player opponent) {
        this.opponent = opponent;
    }
    
}
