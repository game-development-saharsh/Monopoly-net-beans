package game.bean.cards.property;

import constants.CardConstants;
import game.bean.cards.Card;

public abstract class PropertyCard extends Card {
	
	final int typeOfCard = CardConstants.CARD_TYPE_PROPERTY;
	
	@Override
	public int getTypeOfCard() {
		return typeOfCard;
	}
	
	public abstract int getNumberOfColours();

}
