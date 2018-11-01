package game.bean.cards.property;

import constants.CardConstants;

public class BlueSingleColorPropertyCard extends SingleColorPropertyCard {
	final int typeOfColours = CardConstants.BLUE_SINGLE_COLOR_PROPERTY_CARD;
	final int minimumNumberOfCardsForSet;
	final int[] rentForSet;
	
	public BlueSingleColorPropertyCard() {
		setValueInMn(4);
		minimumNumberOfCardsForSet =2;
		rentForSet = new int[2];
		rentForSet[0] = 3;
		rentForSet[1] = 8;
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
