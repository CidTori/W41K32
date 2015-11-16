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
public class Player {
    
    private String name;
    private int health;
    private int bullet;
    private int shield;
    private boolean focused;
    private String lastMove;

    public Player(String name) {
        this.name = name;
        //this.health = 10;
        //this.bullet = 0;
        //this.shield = 7;
        //this.focused = false;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        int difference = this.health-health;
        if (difference>0) {
            if (difference == 1) {
                System.out.println(this.name+" a perdu une vie.");
            } else {
                System.out.println(this.name+" a perdu "+difference+" vies.");
            }
        } else if (difference<0) {
            if (difference == -1) {
                System.err.println(this.name+" a gagné une vie !");
            } else {
                System.err.println(this.name+" a gagné "+(-difference)+" vies !");
            }
        }
        this.health = health;
    }

    public int getBullet() {
        return bullet;
    }

    public void setBullet(int bullet) {
        int difference = this.bullet-bullet;
        if (difference>0) {
            if (difference == 1) {
                System.out.println(this.name+" a utilisé une balle.");
            } else {
                System.err.println(this.name+" a utilisé "+difference+" balles !");
            }
        } else if (difference<0) {
            if (difference == -1) {
                System.out.println(this.name+" a rechargé une balle.");
            } else {
                System.err.println(this.name+" a rechargé "+(-difference)+" balles !");
            }
        }
        this.bullet = bullet;
    }

    public int getShield() {
        return shield;
    }

    public void setShield(int shield) {
        int difference = this.shield-shield;
        if (difference>0) {
            if (difference == 1) {
                System.out.println(this.name+" a utilisé une défense.");
            } else {
                System.err.println(this.name+" a utilisé "+difference+" défenses !");
            }
        } else if (difference<0) {
            if (difference == -1) {
                System.err.println(this.name+" a récupéré une défense !");
            } else {
                System.err.println(this.name+" a recupéré "+(-difference)+" défenses !");
            }
        }
        this.shield = shield;
    }

    public boolean isFocused() {
        return focused;
    }

    public void setFocused(boolean focused) {
        boolean difference = this.focused!=focused;
        if (difference) {
            if (this.focused) {
                System.out.println(this.name+" ne vise plus.");
            } else {
                System.out.println(this.name+" vise.");
            }
        }
        this.focused = focused;
    }

    public String getLastMove() {
        return lastMove;
    }

    public void setLastMove(String lastMove) {
        this.lastMove = lastMove;
        if (this.lastMove!=null) {
            switch (this.lastMove) {
                case "RELOAD" :
                    System.out.println(this.name+" a rechargé.");
                    break;
                case "COVER" :
                    System.out.println(this.name+" était à couvert.");
                    break;
                case "AIM" :
                    System.out.println(this.name+" vise.");
                    break;
                case "SHOOT" :
                    System.out.println(this.name+" a tiré.");
                    break;
                default :
                    System.err.println(this.name+" a effectué un coup non prévu !");
            }
        }
    }
    
    @Override
    public String toString() {
        String string = ""
                + "Nom : "+this.name+"\n"
                + "Vies : "+this.health+"\n"
                + "Balles : "+this.bullet+"\n"
                + "Boucliers : "+this.shield+"\n"
                + "Vise : "+this.focused+"\n"
                + "Dernier coup : "+this.lastMove;
        return string;
    }
}
