package game.bean.cards.property;

import constants.CardConstants;

public class AllColorPropertyWildCard extends PropertyCard {
	int numberOfColors = CardConstants.ALL_COLOR_PROPERTY_CARD;

	@Override
	public int getNumberOfColours() {
		return numberOfColors;
	}
	
	public AllColorPropertyWildCard() {
		setValueInMn(0);
	}
}
