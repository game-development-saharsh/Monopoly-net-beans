package game.bean.cards.money;

import constants.CardConstants;
import game.bean.cards.Card;

public abstract class MoneyCard extends Card{
	private int typeOfCards = CardConstants.CARD_TYPE_MONEY;
	public int getTypeOfCard() {
		return typeOfCards;
	}
}
