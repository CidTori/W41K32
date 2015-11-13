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

    public Bot(String nomEquipe, String motDePasse) throws Exception {
        this.nomEquipe = nomEquipe;
        this.motDePasse = motDePasse;
        this.connection();
        this.board = new Board();
<<<<<<< Updated upstream
=======
        this.nomEquipe = "test";
        this.motDePasse = "test";
        this.level = 1;
        this.http = new InterfaceHTTP(true, level, nomEquipe, motDePasse);
>>>>>>> Stashed changes
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

    private boolean ping() throws Exception {
        return InterfaceHTTP2.ping().equals("pong");
    }
    
    private void connection() throws Exception {
        this.idEquipe = InterfaceHTTP2.player_getIdEquipe(this.nomEquipe,this.motDePasse);
    }
<<<<<<< Updated upstream
    
    private void versus() throws Exception {
        String idPartie = InterfaceHTTP2.versus_next(this.idEquipe);
        while (idPartie.equals("NA")) {
            Thread.sleep(1000);
            idPartie = InterfaceHTTP2.versus_next(this.idEquipe);
        }
        this.idPartie = idPartie;
=======
    /*
    private boolean ping() {
        return this.http.ping().equals("pong");
>>>>>>> Stashed changes
    }
    
    private void practice(int level) throws Exception {
        String idPartie = InterfaceHTTP2.practice_new(Integer.toString(level),this.idEquipe);
        while (idPartie.equals("NA")) {
            Thread.sleep(1000);
            idPartie = InterfaceHTTP2.versus_next(this.idEquipe);
        }
        this.idPartie = idPartie;
    }
    
    private void updateStatus() throws Exception {
        String status = InterfaceHTTP2.game_status(this.idPartie,this.idEquipe);
        this.board.setStatus(status);
    }
    
    private void updateBoard() throws Exception {
        String format = "JSON";
        String board = InterfaceHTTP2.game_board(this.idPartie, format);
        if (format.equals("JSON")) {
            Gson gson = new Gson(); // Or use new GsonBuilder().create();
            this.board = gson.fromJson(board, Board.class); // deserializes json into target2
        }
    }
    
<<<<<<< Updated upstream
    private void updateOpponentLastMove() throws Exception {
        String lastMove = InterfaceHTTP2.game_getLastMove(this.idPartie,this.idEquipe);
        this.board.getOpponent().setLastMove(lastMove);
    }
    
    private void reload() throws Exception {
        String answer = InterfaceHTTP2.game_play(this.idPartie,this.idEquipe,"RELOAD");
        switch (answer) {
            case "OK" :
                break;
            case "FORBIDDEN" :
                break;
            case "NOTYET" :
                break;
            case "GAMEOVER" :
                break;
            default :
                
        }
    }
    
    private void updateOpponentName() throws Exception {
        String opponentName = InterfaceHTTP2.game_opponent(this.idPartie,this.idEquipe);
        this.board.getOpponent().setName(opponentName);
    }
=======
    */
>>>>>>> Stashed changes
    
    public void run() throws Exception {
        this.practice(1);
        this.updateOpponentName();
        this.updateBoard();
        System.out.println(this.board.toString());
    }
}
