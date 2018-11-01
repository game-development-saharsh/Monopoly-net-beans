package game.bean.cards.action;


import constants.CardConstants;

public class AnyColorRentActionCard extends RentOnPaymentPropertyActionCard {
	private int numberOfDifferentColoredSetsOnWhichPaymentCanBeAsked = CardConstants.ANY_COLOR_RENT_CARD;
	public AnyColorRentActionCard() {
		setValueInMn(3);
	}
	@Override
	public int getNumberOfDifferentColoredSetsOnWhichPaymentCanBeAsked() {
		// TODO Auto-generated method stub
		return numberOfDifferentColoredSetsOnWhichPaymentCanBeAsked;
	}
}
