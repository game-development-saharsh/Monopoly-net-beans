/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package monopoly.beans;

import game.bean.cards.PlayingCardForTransition;
import java.util.ArrayList;
import java.util.List;
import monopoly.constants.MonopolyDealConstants;

public class GameState {
	private Game game;
	private List<PlayingCardForTransition> drawPile;
	private List<PlayingCardForTransition> playPile;
	private String customMessage;

	private List<PlayingCardForTransition> cardsInPlayerOneBank;
	private List<PlayingCardForTransition> cardsInPlayerOneHand;
	private List<List<PlayingCardForTransition>> setOfPlayerone;
	
	private List<PlayingCardForTransition> cardsInPlayerTwoBank;
	private List<PlayingCardForTransition> cardsInPlayerTwoHand;
	private List<List<PlayingCardForTransition>> setOfPlayerTwo;
	
	private String objectType = MonopolyDealConstants.GAME_STATE_OBJECT_TYPE;
	
	public GameState(Game game) {
		this.game = game;
                this.drawPile = new ArrayList<>();
                this.playPile = new ArrayList<>();
		cardsInPlayerOneBank = new ArrayList<>();
		cardsInPlayerOneHand = new ArrayList<>();
		setOfPlayerone = new ArrayList<>();
		
		cardsInPlayerTwoBank = new ArrayList<>();
		cardsInPlayerTwoHand = new ArrayList<>();
		setOfPlayerTwo = new ArrayList<>();
	}
	
	public Game getGame() {
		return game;
	}
	public void setGame(Game game) {
		this.game = game;
	}
	
	public String getCustomMessage() {
		return customMessage;
	}
	public void setCustomMessage(String customMessage) {
		this.customMessage = customMessage;
	}
	
	public String getObjectType() {
		return objectType;
	}
	public void setObjectType(String objectType) {
		this.objectType = objectType;
	}
	public List<PlayingCardForTransition> getDrawPile() {
		return drawPile;
	}
	public void setDrawPile(List<PlayingCardForTransition> drawPile) {
		this.drawPile = drawPile;
	}
	public List<PlayingCardForTransition> getPlayPile() {
		return playPile;
	}
	public void setPlayPile(List<PlayingCardForTransition> playPile) {
		this.playPile = playPile;
	}
	public List<PlayingCardForTransition> getCardsInPlayerOneBank() {
		return cardsInPlayerOneBank;
	}
	public void setCardsInPlayerOneBank(List<PlayingCardForTransition> cardsInPlayerOneBank) {
		this.cardsInPlayerOneBank = cardsInPlayerOneBank;
	}
	public List<PlayingCardForTransition> getCardsInPlayerOneHand() {
		return cardsInPlayerOneHand;
	}
	public void setCardsInPlayerOneHand(List<PlayingCardForTransition> cardsInPlayerOneHand) {
		this.cardsInPlayerOneHand = cardsInPlayerOneHand;
	}
	public List<List<PlayingCardForTransition>> getSetOfPlayerone() {
		return setOfPlayerone;
	}
	public void setSetOfPlayerone(List<List<PlayingCardForTransition>> setOfPlayerone) {
		this.setOfPlayerone = setOfPlayerone;
	}
	public List<PlayingCardForTransition> getCardsInPlayerTwoBank() {
		return cardsInPlayerTwoBank;
	}
	public void setCardsInPlayerTwoBank(List<PlayingCardForTransition> cardsInPlayerTwoBank) {
		this.cardsInPlayerTwoBank = cardsInPlayerTwoBank;
	}
	public List<PlayingCardForTransition> getCardsInPlayerTwoHand() {
		return cardsInPlayerTwoHand;
	}
	public void setCardsInPlayerTwoHand(List<PlayingCardForTransition> cardsInPlayerTwoHand) {
		this.cardsInPlayerTwoHand = cardsInPlayerTwoHand;
	}
	public List<List<PlayingCardForTransition>> getSetOfPlayerTwo() {
		return setOfPlayerTwo;
	}
	public void setSetOfPlayerTwo(List<List<PlayingCardForTransition>> setOfPlayerTwo) {
		this.setOfPlayerTwo = setOfPlayerTwo;
	}
}
