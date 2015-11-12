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
    
    private String nomEquipe;
    private String motDePasse;
    private String idEquipe;
    private String idPartie;
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
    
    private boolean ping() {
        return this.http.ping().equals("pong");
    }
    
    private void connection() {
        this.idEquipe = this.http.player_getIdEquipe(this.nomEquipe,this.motDePasse);
    }
    
    private void versus() {
        this.idPartie = this.http.versus_next(this.idEquipe);
    }
    
    private void practice(int level) {
        this.idPartie = this.http.practice_new(level,this.idEquipe);
    }
    
    
    
}
