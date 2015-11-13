/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package w41k32;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 *
 * @author Cédric
 */
public class InterfaceHTTP2 {
    
    public InterfaceHTTP2() {
        
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
    
    public static String ping() throws Exception {
        return InterfaceHTTP2.getHTTP("http://www.battlearena.io/battle-ws/duel/ping");
    }
    
    public static String player_getIdEquipe(String nomEquipe,String motDePasse) throws Exception {
        return InterfaceHTTP2.getHTTP("http://www.battlearena.io/battle-ws/duel/player/getIdEquipe/" + nomEquipe + "/" + motDePasse);
    }
    
    public static String practice_new(String level,String idEquipe) throws Exception {
        return InterfaceHTTP2.getHTTP("http://www.battlearena.io/battle-ws/duel/practice/new/" + level + "/" + idEquipe);
    }
    
    public static String versus_next(String idEquipe) throws Exception {
        return InterfaceHTTP2.getHTTP("http://www.battlearena.io//battle-ws/duel/versus/next/" + idEquipe);
    }
    
    public static String game_status(String idPartie,String idEquipe) throws Exception {
        return InterfaceHTTP2.getHTTP("http://www.battlearena.io/battle-ws/duel/game/status/"+idPartie+"/"+idEquipe);
    }
    
    public static String game_board(String idPartie,String format) throws Exception{
        return InterfaceHTTP2.getHTTP("http://www.battlearena.io/battle-ws/duel/game/board/"+idPartie+"?format="+format);
    }
    
    public static String game_getLastMove(String idPartie, String idEquipe) throws Exception{
        return InterfaceHTTP2.getHTTP("http://www.battlearena.io/battle-ws/duel/game/getlastmove/"+idPartie+"/"+idEquipe);
    }
    
    public static String game_play(String idPartie,String idEquipe,String move) throws Exception{
        return InterfaceHTTP2.getHTTP("http://www.battlearena.io/battle-ws/duel/game/play/"+idPartie+"/"+idEquipe+"/"+move);
    }
    
    public static String game_opponent(String idPartie,String idEquipe) throws Exception{
        return InterfaceHTTP2.getHTTP("http://www.battlearena.io/battle-ws/duel/game/opponent/"+idPartie+"/"+idEquipe);
    }

}
