package game.bean.cards.property;

import constants.CardConstants;

public abstract class DualColorPropertyCard extends PropertyCard{
	int numberOfColors = CardConstants.DUAL_COLOR_PROPERTY_CARD;
	@Override
	public int getNumberOfColours() {
		// TODO Auto-generated method stub
		return numberOfColors;
	}
	public abstract int getTypeOfColor();
}
