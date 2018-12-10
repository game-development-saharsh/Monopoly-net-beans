/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package monopoly.beans;

/**
 *
 * @author Reena
 */
public class GameDetail {
    private Game game;
    private int myPositionInGame;
    
    public GameDetail(Game game, int myPositionInGame){
        this.game=game;
        this.myPositionInGame=myPositionInGame;
    }

    /**
     * @return the myPositionInGame
     */
    public int getMyPositionInGame() {
        return myPositionInGame;
    }

    /**
     * @param myPositionInGame the myPositionInGame to set
     */
    public void setMyPositionInGame(int myPositionInGame) {
        this.myPositionInGame = myPositionInGame;
    }

    /**
     * @param game the game to set
     */
    public void setGame(Game game) {
        this.game = game;
    }
    
}
