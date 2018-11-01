package game.bean.cards.property;

import constants.CardConstants;

public class PinkAndOrangeDualColorPropertyCard extends DualColorPropertyCard {
	final int typeOfColours = CardConstants.PINK_ORANGE_DUAL_COLOR_PROPERTY_CARD;

	@Override
	public int getTypeOfColor() {
		return typeOfCard;
	}
	public PinkAndOrangeDualColorPropertyCard() {
		setValueInMn(2);
	}
}
