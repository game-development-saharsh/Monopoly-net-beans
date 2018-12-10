package game.bean.cards.property;

import constants.CardConstants;

public class RedAndYellowDualColorPropertyCard extends DualColorPropertyCard {
	final int typeOfColours = CardConstants.RED_YELLOW_DUAL_COLOR_PROPERTY_CARD;

	@Override
	public int getTypeOfColor() {
		return typeOfColours;
	}
	public RedAndYellowDualColorPropertyCard() {
		setValueInMn(3);
	}
}
