package game.bean.cards.property;

import constants.CardConstants;

public class SkyblueAndBrownDualColorPropertyCard extends DualColorPropertyCard {
	final int typeOfColours = CardConstants.SKYBLUE_BROWN_DUAL_COLOR_PROPERTY_CARD;

	@Override
	public int getTypeOfColor() {
		return typeOfColours;
	}
	public SkyblueAndBrownDualColorPropertyCard() {
		setValueInMn(1);
	}
}
