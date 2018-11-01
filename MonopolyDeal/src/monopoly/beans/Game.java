/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package monopoly.beans;

import java.util.List;

/**
 *
 * @author Reena
 */
public class Game {

    private int gameId;
    private List<Player> listOfPlayers;
    private long gameStartedAt;
    private char gameStatus;
    private String objectType;

    public Game() {
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public List<Player> getListOfPlayers() {
        return listOfPlayers;
    }

    public void setListOfPlayers(List<Player> listOfPlayers) {
        this.listOfPlayers = listOfPlayers;
    }

    public long getGameStartedAt() {
        return gameStartedAt;
    }

    public void setGameStartedAt(long gameStartedAt) {
        this.gameStartedAt = gameStartedAt;
    }

    public char getGameStatus() {
        return gameStatus;
    }

    public void setGameStatus(char gameStatus) {
        this.gameStatus = gameStatus;
    }
     
    public String getObjectType() {
        return objectType;
    }
}
