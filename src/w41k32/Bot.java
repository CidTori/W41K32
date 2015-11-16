/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package w41k32;

import com.google.gson.Gson;

/**
 *
 * @author Cédric
 */
public class Bot {
    
    private final String nomEquipe;
    private final String motDePasse;
    private String idEquipe;
    private String idPartie;
    private int level;
    private Board board;
    private String status;
    private String answer;
    private String move;

    public Bot(String nomEquipe, String motDePasse) throws Exception {
        this.nomEquipe = nomEquipe;
        this.motDePasse = motDePasse;
        this.connection();
    }

    public String getIdEquipe() {
        return idEquipe;
    }

    public void setIdEquipe(String idEquipe) {
        this.idEquipe = idEquipe;
    }

    public String getIdPartie() {
        return idPartie;
    }

    public void setIdPartie(String idPartie) {
        this.idPartie = idPartie;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    private boolean ping() throws Exception {
        return InterfaceHTTP2.ping().equals("pong");
    }
    
    private void connection() throws Exception { // getIdEquipe
        this.idEquipe = InterfaceHTTP2.player_getIdEquipe(this.nomEquipe,this.motDePasse);
    }
    
    private void beginVersus() throws Exception { // nextGame()
        String idPartie = InterfaceHTTP2.versus_next(this.idEquipe);
        while (idPartie.equals("NA")) {
            Thread.sleep(1000);
            idPartie = InterfaceHTTP2.versus_next(this.idEquipe);
        }
        this.idPartie = idPartie;
        this.board = new Board(this.nomEquipe,this.getOpponentName());
        this.updateBoard();
    }
    
    private void beginPractice(int level) throws Exception { // newGame()
        String idPartie = InterfaceHTTP2.practice_new(Integer.toString(level),this.idEquipe);
        while (idPartie.equals("NA")) {
            Thread.sleep(1000);
            idPartie = InterfaceHTTP2.versus_next(this.idEquipe);
        }
        this.idPartie = idPartie;
        this.board = new Board(this.nomEquipe,this.getOpponentName());
        this.updateBoard();
    }
    
    private void updateStatus() throws Exception { // getStatus()
        String status = InterfaceHTTP2.game_status(this.idPartie,this.idEquipe);
        this.status = status;
    }
    
    private void endGame() {
        
    }
    
    private void updateBoard() throws Exception { // getBoard()
        String format = "JSON";
        String game_board = InterfaceHTTP2.game_board(this.idPartie, format);
        game_board = game_board.replaceAll("(\")(player[12])(\":\\{\"name\":\""+this.board.getSelf().getName()+"\")","$1self$3");
        game_board = game_board.replaceAll("(\")(player[12])(\":\\{\"name\":\""+this.board.getOpponent().getName()+"\")","$1opponent$3");
        
        Gson gson = new Gson(); // Or use new GsonBuilder().create();
        Board board = gson.fromJson(game_board, Board.class); // deserializes json into target2
        
        this.board.setNbrActionLeft(board.getNbrActionLeft());
        
        this.board.getSelf().setHealth(board.getSelf().getHealth());
        this.board.getSelf().setBullet(board.getSelf().getBullet());
        this.board.getSelf().setShield(board.getSelf().getShield());
        this.board.getSelf().setFocused(board.getSelf().isFocused());
        this.board.getSelf().setLastMove(this.move);
        
        this.board.getOpponent().setHealth(board.getOpponent().getHealth());
        this.board.getOpponent().setBullet(board.getOpponent().getBullet());
        this.board.getOpponent().setShield(board.getOpponent().getShield());
        this.board.getOpponent().setFocused(board.getOpponent().isFocused());
        this.board.getOpponent().setLastMove(InterfaceHTTP2.game_getLastMove(this.idPartie,this.idEquipe));
    }
    
    private void makeMove(String move) throws Exception {
        this.answer = InterfaceHTTP2.game_play(this.idPartie,this.idEquipe,move);
        this.move = move;
    }
    
    private void reload() throws Exception {
        this.makeMove("RELOAD");
        System.out.println("Je recharge.");
    }
    
    private void cover() throws Exception {
        this.makeMove("COVER");
        System.out.println("Je me met à couvert.");
    }
    
    private void aim() throws Exception {
        this.makeMove("AIM");
        System.out.println("Je vise.");
    }
    
    private void shoot() throws Exception {
        this.makeMove("SHOOT");
        System.out.println("Je tire.");
    }
    
    private String getOpponentName() throws Exception {
        return InterfaceHTTP2.game_opponent(this.idPartie,this.idEquipe);
    }
    
    public void play() throws Exception {
        if (this.board.getSelf().getBullet()==0) {
            System.out.println("Plus de balles dans le chargeur !");
            reload();
        } else {
            shoot();
        }
    }
    
    public void practice(int level) throws Exception {
        this.beginPractice(level);
        game_loop:
        while (true) {
            //System.out.println(this.board.toString());
            this.updateStatus();
            while (this.status.equals("CANTPLAY")) {
                Thread.sleep(1000);
                this.updateStatus();
            }
            this.updateBoard();
            System.out.println(this.board.toString());
            switch (this.status) {
                case "VICTORY" :
                    System.out.println("Victoire !");
                    //this.endGame();
                    break game_loop;
                case "DEFEAT" :
                    System.out.println("Défaite...");
                    //this.endGame();
                    break game_loop;
                case "CANCELLED" :
                    System.err.println("Partie annulée !");
                    //this.endGame();
                    break game_loop;
                case "CANPLAY" :
                    this.play();
                    switch (this.answer) {
                        case "FORBIDDEN" :
                            System.err.println("Ce coup est interdit !");
                            break;
                        case "NOTYET" :
                            System.err.println("Ce n'est pas encore mon tour !");
                            break;
                        case "GAMEOVER" :
                            System.err.println("La partie est terminé !");
                            break;
                        case "OK" :
                            System.out.println("Ce coup est accepté.");
                            break;
                        default :
                            System.err.println("Réponse non prévue !");
                    }
                    break;
                default :
                    System.err.println("Statut non prévu !");
            }
        }
    }
}
