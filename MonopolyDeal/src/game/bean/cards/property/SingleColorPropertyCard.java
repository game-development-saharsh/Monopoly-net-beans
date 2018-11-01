package game.bean.cards.property;

import constants.CardConstants;

public abstract class SingleColorPropertyCard extends PropertyCard{
	
	final int numberOfColours = CardConstants.SINGLE_COLOR_PROPERTY_CARD;
	@Override
	public int getNumberOfColours() {
		return numberOfColours;
	}
	
	public abstract int getTypeOfColor();
	public abstract int getMinimumNumberOfCardsForSet();
	public abstract int[] getRentForSet();
}
