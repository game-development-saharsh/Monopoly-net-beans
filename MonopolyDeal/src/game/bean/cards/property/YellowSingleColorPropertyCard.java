package game.bean.cards.property;

import constants.CardConstants;

public class YellowSingleColorPropertyCard extends SingleColorPropertyCard {
	final int typeOfColours = CardConstants.YELLOW_SINGLE_COLOR_PROPERTY_CARD;
	final int minimumNumberOfCardsForSet;
	final int[] rentForSet;
	
	public YellowSingleColorPropertyCard() {
		setValueInMn(3);
		minimumNumberOfCardsForSet =3;
		rentForSet = new int[3];
		rentForSet[0] = 2;
		rentForSet[1] = 4;
		rentForSet[2] = 6;
	}
	
	@Override
	public int getTypeOfColor() {
		return typeOfColours;
	}
	
	@Override
	public int getMinimumNumberOfCardsForSet() {
		// TODO Auto-generated method stub
		return minimumNumberOfCardsForSet;
	}

	@Override
	public int[] getRentForSet() {
		// TODO Auto-generated method stub
		return rentForSet;
	}
}
