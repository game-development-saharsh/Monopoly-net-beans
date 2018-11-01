package game.bean.cards.action;

import constants.CardConstants;

public abstract class RentOnPaymentPropertyActionCard extends PaymentActionCard {
	private int typeOfMakePayment = CardConstants.RENT_ON_PROPERTY_ACTION_CARD;
	@Override
	public int getTypeOfMakePayment() {
		// TODO Auto-generated method stub
		return typeOfMakePayment;
	}
	public abstract int getNumberOfDifferentColoredSetsOnWhichPaymentCanBeAsked();
}
