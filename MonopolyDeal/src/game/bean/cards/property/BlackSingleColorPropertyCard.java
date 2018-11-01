package game.bean.cards.property;

import constants.CardConstants;

public class BlackSingleColorPropertyCard extends SingleColorPropertyCard {
	final int typeOfColours = CardConstants.BLACK_SINGLE_COLOR_PROPERTY_CARD;
	final int minimumNumberOfCardsForSet;
	final int[] rentForSet;
	
	public BlackSingleColorPropertyCard() {
		setValueInMn(2);
		minimumNumberOfCardsForSet =4;
		rentForSet = new int[4];
		rentForSet[0] = 1;
		rentForSet[1] = 2;
		rentForSet[2] = 3;
		rentForSet[3] = 4;
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
