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

    public Board(String selfName,String opponentName) {
        this.self = new Player(selfName);
        this.opponent = new Player(opponentName);
    }

    public int getNbrActionLeft() {
        return nbrActionLeft;
    }

    public void setNbrActionLeft(int nbrActionLeft) {
        this.nbrActionLeft = nbrActionLeft;
        System.out.println("Il reste "+this.nbrActionLeft+" actions.");
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
    
    @Override
    public String toString() {
        String string = ""
                + "Nombre d'actions restantes : "+this.nbrActionLeft+"\n"
                + "Nous :\n"
                + this.self.toString()+"\n"
                + "Adversaire :\n"
                + this.opponent.toString();
        return string;
    }
    
}
