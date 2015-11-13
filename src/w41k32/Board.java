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
    
    private String status;
    private int nbrActionLeft;
    private Player self;
    private Player opponent;

    public Board() {
        //this.nbrActionLeft = 30;
        this.self = new Player("W41K32");
        this.opponent = new Player(null);
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
    
    @Override
    public String toString() {
        String string = ""
                + "Board :\n"
                + "Statut : "+this.status+"\n"
                + "Nombre d'actions restantes : "+this.nbrActionLeft+"\n"
                + "Nous :\n"
                + this.self.toString()+"\n"
                + "Adversaire :\n"
                + this.opponent.toString();
        return string;
    }
    
    public void player1(String name,int health, int bullet, int shield, boolean focused) {
        if (this.self.getName().equals(name)) {
            this.self.setHealth(health);
            this.self.setBullet(bullet);
            this.self.setShield(shield);
            this.self.setFocused(focused);
        } else {
            this.opponent.setName(name);
            this.opponent.setHealth(health);
            this.opponent.setBullet(bullet);
            this.opponent.setShield(shield);
            this.opponent.setFocused(focused);
        }
    }
    
    public void player2(String name,int health, int bullet, int shield, boolean focused) {
        if (this.self.getName().equals(name)) {
            this.self.setHealth(health);
            this.self.setBullet(bullet);
            this.self.setShield(shield);
            this.self.setFocused(focused);
        } else {
            this.opponent.setName(name);
            this.opponent.setHealth(health);
            this.opponent.setBullet(bullet);
            this.opponent.setShield(shield);
            this.opponent.setFocused(focused);
        }
    }
}
