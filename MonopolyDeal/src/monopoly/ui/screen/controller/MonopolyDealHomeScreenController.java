/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package monopoly.ui.screen.controller;

import java.awt.event.ActionEvent;
import monopoly.beans.GameDetail;
import monopoly.beans.GameState;

/**
 *
 * @author Reena
 */
public class MonopolyDealHomeScreenController {

    private GameDetail gameDetail;
    private GameState gameState;
    
    public void setUpGame(){
        System.out.println("set up game.");
    }
    public MonopolyDealHomeScreenController(GameDetail gameMoves, GameState gameState) {
        this.gameDetail = gameMoves;
        this.gameState  = gameState;
    }

    public void drawPileButtonAction(ActionEvent evt) {
        System.out.println("drawPileButtonAction");
    }

    public void passTurnToNextPlayerButtonAction(ActionEvent evt) {
        System.out.println("passTurnToNextPlayerButtonAction");
    }

    public void viewCardsInBankButtonAction(ActionEvent evt) {
        System.out.println("viewCardsInBankButtonAction");
    }

    public void showAllSetsButtonAction(ActionEvent evt) {
        System.out.println("showAllSetsButtonAction");
    }

    public void viewCardsInHandButtonAction(ActionEvent evt) {
        System.out.println("viewCardsInHandButtonAction");
    }

    public void showMyCardsButtonAction(ActionEvent evt) {
        System.out.println("showMyCardsButtonAction");
    }

    public void showOpponentCardsButtonAction(ActionEvent evt) {
        System.out.println("showOpponentCardsButtonAction");
    }

    public void playPileButtonAction(ActionEvent evt) {
        System.out.println("playPileButtonAction");
    }

    public void remindTurnToOpponentAction(ActionEvent evt) {
        System.out.println("remindTurnToOpponentAction");
    }

    /**
     * @return the gameMoves
     */
    public GameDetail getGameMoves() {
        return gameDetail;
    }

    /**
     * @return the gameState
     */
    public GameState getGameState() {
        return gameState;
    }

    /**
     * @param gameState the gameState to set
     */
    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

}
