package game.bean.cards.action;

import constants.CardConstants;

public class PinkOrOrangeRentCard extends RentOnPaymentPropertyActionCard {
	private int numberOfDifferentColoredSetsOnWhichPaymentCanBeAsked = CardConstants.PINK_OR_ORANGE_COLOR_RENT_CARD;
	public PinkOrOrangeRentCard() {
		setValueInMn(1);
	}
	@Override
	public int getNumberOfDifferentColoredSetsOnWhichPaymentCanBeAsked() {
		// TODO Auto-generated method stub
		return numberOfDifferentColoredSetsOnWhichPaymentCanBeAsked;
	}
}
