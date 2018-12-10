package game.bean.cards.property;

import constants.CardConstants;

public class GreenAndBlueDualColorPropertyCard extends DualColorPropertyCard {
	final int typeOfColours = CardConstants.GREEN_BLUE_DUAL_COLOR_PROPERTY_CARD;

	@Override
	public int getTypeOfColor() {
		return typeOfColours;
	}
	public GreenAndBlueDualColorPropertyCard() {
		setValueInMn(4);
	}
}
