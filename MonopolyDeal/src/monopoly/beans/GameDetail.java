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
    final private Game game;
    private int myPositionInGame;
    private int currentTurn;
    
    public GameDetail(Game game, int myPositionInGame){
        this.game=game;
        this.myPositionInGame=myPositionInGame;
    }
    
}
