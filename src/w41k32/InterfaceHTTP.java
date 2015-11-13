/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package w41k32;

import com.google.gson.Gson;
import java.io.*;
import java.net.*;

/**
 *
 * @author Cédric
 */
public class InterfaceHTTP {

    private String nomEquipe;
    private String motDePasse;
    private String idEquipe;
    private String idPartie;
    private int level;

    public InterfaceHTTP(boolean practice, int level, String nomEquipe, String motDePasse) throws Exception {
        this.nomEquipe = nomEquipe;
        this.motDePasse = motDePasse;
        this.level = level;
        idEquipe = getHTTP("http://www.battlearena.io/battle-ws/duel/player/getIdEquipe/" + nomEquipe + "/" + motDePasse);
        if (practice == true) {
            idPartie = getHTTP("http://www.battlearena.io/battle-ws/duel/practice/new/" + level + "/" + idEquipe);
        } else {
            idPartie = getHTTP("http://www.battlearena.io//battle-ws/duel/versus/next/" + idEquipe);
        }
    }

    public static String getHTTP(String urlToRead) throws Exception {
        StringBuilder result = new StringBuilder();
        URL url = new URL(urlToRead);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        BufferedReader read = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String line;
        while ((line = read.readLine()) != null) {
            result.append(line);
        }
        read.close();
        connection.disconnect();
        return result.toString();
    }
    
    public String getStatus() throws Exception{
        return getHTTP("http://www.battlearena.io/battle-ws/duel/game/status/"+idPartie+"/"+idEquipe);
    }
    
    public Board getBoard() throws Exception{
        String json = getHTTP("http://www.battlearena.io/battle-ws/duel/game/board/"+idPartie+"?format=JSON");
        Gson gson = new Gson(); // Or use new GsonBuilder().create();
        Board board = gson.fromJson(json, Board.class); // deserializes json into target2
        return board;
    }
    
    public String getMove() throws Exception{
        return getHTTP("http://www.battlearena.io/battle-ws/duel/game/getlastmove/"+idPartie+"/"+idEquipe);
    }
    
    public String makeMove(String move) throws Exception{
        return getHTTP("http://www.battlearena.io/battle-ws/duel/game/play/"+idPartie+"/"+idEquipe+"/"+move);
    }
    
    public String getNomAdversaire() throws Exception{
        return getHTTP("http://www.battlearena.io/battle-ws/duel/game/opponent/"+idPartie+"/"+idEquipe);
    }

}
