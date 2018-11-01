package game.bean.cards.property;

import constants.CardConstants;

public class BrownSingleColorPropertyCard extends SingleColorPropertyCard {
	final int typeOfColours = CardConstants.BROWN_SINGLE_COLOR_PROPERTY_CARD;
	final int minimumNumberOfCardsForSet;
	final int[] rentForSet;
	
	public BrownSingleColorPropertyCard() {
		setValueInMn(1);
		minimumNumberOfCardsForSet =2;
		rentForSet = new int[2];
		rentForSet[0] = 1;
		rentForSet[1] = 2;
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
