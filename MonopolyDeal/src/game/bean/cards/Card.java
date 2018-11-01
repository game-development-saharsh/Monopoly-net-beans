package game.bean.cards;

import constants.CardConstants;



public abstract class Card {
	private int cardId;
	private String nameOfCard;
	private int valueInMn;
	private int source;
	private int destination;
	private String objectType = CardConstants.CARD_OBJECT_TYPE;

	public int getCardId() {
		return cardId;
	}
	public void setCardId(int cardId) {
		this.cardId = cardId;
	}
	public int getValueInMn() {
		return valueInMn;
	}
	public void setValueInMn(int valueInMn) {
		this.valueInMn = valueInMn;
	}
	public int getSource() {
		return source;
	}
	public void setSource(int source) {
		this.source = source;
	}
	public int getDestination() {
		return destination;
	}
	public void setDestination(int destination) {
		this.destination = destination;
	}
	
	public String getObjectType() {
		return objectType;
	}
	public void setObjectType(String objectType) {
		this.objectType = objectType;
	}
	public String getNameOfCard() {
		return nameOfCard;
	}
	public void setNameOfCard(String nameOfCard) {
		this.nameOfCard = nameOfCard;
	}
	
	public abstract int getTypeOfCard();

}
