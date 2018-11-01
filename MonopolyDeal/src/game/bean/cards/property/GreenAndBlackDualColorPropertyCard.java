package game.bean.cards.property;

import constants.CardConstants;

public class GreenAndBlackDualColorPropertyCard extends DualColorPropertyCard {
	final int typeOfColours = CardConstants.GREEN_BLACK_DUAL_COLOR_PROPERTY_CARD;

	@Override
	public int getTypeOfColor() {
		return typeOfCard;
	}
	public GreenAndBlackDualColorPropertyCard() {
		setValueInMn(4);
	}
}
