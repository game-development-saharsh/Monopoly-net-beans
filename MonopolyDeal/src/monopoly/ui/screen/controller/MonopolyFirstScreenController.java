/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package monopoly.ui.screen.controller;

import com.google.gson.Gson;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import monopoly.beans.Game;
import monopoly.beans.GameDetail;
import monopoly.beans.GameState;
import monopoly.beans.Message;
import monopoly.beans.Player;
import monopoly.constants.MonopolyDealConstants;
import monopoly.ui.screens.MonopolyDealHomeScreen;
import monopoly.ui.screens.MonopolyFirstScreen;
import monopoly.url.connector.MonopolyApplicationURLConnector;

/**
 *
 * @author Reena
 */
public class MonopolyFirstScreenController {

    private String playerId = MonopolyDealConstants.EMPTY_STRING;
    private GameDetail gameMoves = null;
    private GameState gameState = null;
    public MonopolyFirstScreenController(MonopolyFirstScreen monopolyFirstScreen) {
    }

    public char joinButtonAction(String userNameEntered) {
        char joinResult = ' ';
        MonopolyApplicationURLConnector monopolyApplicationURLConnector = new MonopolyApplicationURLConnector();
        String urlComponent = MonopolyDealConstants.LOGIN_MODULE + MonopolyDealConstants.QUESTION_MARK + MonopolyDealConstants.USER_NAME + MonopolyDealConstants.EQUAL + userNameEntered;
        String responoseFromServer = monopolyApplicationURLConnector.callURL(urlComponent);
        if (responoseFromServer.equals(MonopolyDealConstants.EMPTY_STRING)) {
            return joinResult;
        }
        Gson gson = new Gson();
        Message messageFromServer = gson.fromJson(responoseFromServer, Message.class);
        if (messageFromServer.getMessageType().equalsIgnoreCase(MonopolyDealConstants.MESSAGE_TYPE_INFO)) {
            fetchPlayerIdFromAdditionalData(messageFromServer.getAdditionalStringMessage());
            joinResult = MonopolyDealConstants.INFO_FINDING_MATCH;
        } else if (messageFromServer.getMessageType().equalsIgnoreCase(MonopolyDealConstants.MESSAGE_TYPE_ERROR)) {
            joinResult = MonopolyDealConstants.ERROR_INVALID_USERNAME;
        }
        return joinResult;
    }

    public void OpenHomeScreen() {
        MonopolyDealHomeScreen monopolyDealHomeScreen = new MonopolyDealHomeScreen(gameMoves, gameState);
        monopolyDealHomeScreen.setVisible(true);
    }

    public boolean findAnOpponent() {
        boolean opponentFound = false;
        if (getPlayerId() == null || getPlayerId().equals("")) {
            return false;
        }
        long startTimeInMillis = Calendar.getInstance().getTimeInMillis();
        while (!opponentFound) {
            opponentFound = retryLogicToFindOpponent();
            try {
                Thread.sleep(5000);
            } catch (InterruptedException ex) {
                Logger.getLogger(MonopolyFirstScreenController.class.getName()).log(Level.SEVERE, null, ex);
            }
            long timeDifference = Calendar.getInstance().getTimeInMillis() - startTimeInMillis;
            if (timeDifference >= 600000) {//wait ten min
                break;
            }
        }
        return opponentFound;
    }

    private boolean retryLogicToFindOpponent() {
        boolean opponentFound;
        MonopolyApplicationURLConnector monopolyApplicationURLConnector = new MonopolyApplicationURLConnector();
        String urlComponent = MonopolyDealConstants.FIND_OPPONENT_MODULE + MonopolyDealConstants.QUESTION_MARK + MonopolyDealConstants.PLAYER_ID + MonopolyDealConstants.EQUAL + getPlayerId();
        String responoseFromServer = monopolyApplicationURLConnector.callURL(urlComponent);
        opponentFound = processFindingMatchResponseFromServer(responoseFromServer);
        return opponentFound;
    }

    private boolean processFindingMatchResponseFromServer(String responoseFromServer) {
        boolean isMatchFound = false;
        Gson gson = new Gson();
        Map<String, String> messageFromServer = gson.fromJson(responoseFromServer, Map.class);
        if (messageFromServer.containsKey(MonopolyDealConstants.OBJECT_TYPE)) {
            String objectType = messageFromServer.get(MonopolyDealConstants.OBJECT_TYPE);
            if (objectType.equalsIgnoreCase(MonopolyDealConstants.GAME_STATE_OBJECT_TYPE)) {
                System.out.println(responoseFromServer);
                gameState = gson.fromJson(responoseFromServer, GameState.class);
                setGameMoves(new GameDetail(gameState.getGame(), fetchMyPositionInGame(gameState.getGame())));
                isMatchFound = true;
            }
        }
        return isMatchFound;
    }

    private int fetchMyPositionInGame(Game game) {
        int myPositionInGame = -1;
        List<Player> listOfPlayers = game.getListOfPlayers();
        for (int i = 0; i < listOfPlayers.size(); i++) {
            if (listOfPlayers.get(i).getPlayerId() == Integer.parseInt(playerId)) {
                myPositionInGame = i;
                break;
            }
        }
        return myPositionInGame;
    }

    private void fetchPlayerIdFromAdditionalData(Map<String, String> additionalStringMessage) {
        setPlayerId(additionalStringMessage.get(MonopolyDealConstants.PLAYER_ID));
    }

    /**
     * @return the playerId
     */
    public String getPlayerId() {
        return playerId;
    }

    /**
     * @param playerId the playerId to set
     */
    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }

    /**
     * @return the gameMoves
     */
    public GameDetail getGameMoves() {
        return gameMoves;
    }

    /**
     * @param gameMoves the gameMoves to set
     */
    public void setGameMoves(GameDetail gameMoves) {
        this.gameMoves = gameMoves;
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