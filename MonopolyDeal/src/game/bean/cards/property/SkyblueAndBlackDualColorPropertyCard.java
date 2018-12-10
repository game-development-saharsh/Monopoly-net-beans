package game.bean.cards.property;

import constants.CardConstants;

public class SkyblueAndBlackDualColorPropertyCard extends DualColorPropertyCard {

	final int typeOfColours = CardConstants.SKYBLUE_BLACK_DUAL_COLOR_PROPERTY_CARD;

	@Override
	public int getTypeOfColor() {
		return typeOfColours;
	}
	public SkyblueAndBlackDualColorPropertyCard() {
		setValueInMn(4);
	}
}
