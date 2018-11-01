package game.bean.cards.property;

import constants.CardConstants;

public class GreyAndBlackDualColorPropertyCard extends DualColorPropertyCard {

	final int typeOfColours = CardConstants.GREY_BLACK_DUAL_COLOR_PROPERTY_CARD;

	@Override
	public int getTypeOfColor() {
		return typeOfCard;
	}
	public GreyAndBlackDualColorPropertyCard() {
		setValueInMn(2);
	}
}
