package game.bean.cards.action;


import constants.CardConstants;

public class BirthdayPaymentActionCard extends PaymentActionCard {
	private int typeOfMakePayment = CardConstants.CASH_PAYMENT_ACTION_CARD;
	public BirthdayPaymentActionCard() {
		setValueInMn(2);
	}
	@Override
	public int getTypeOfMakePayment() {
		// TODO Auto-generated method stub
		return typeOfMakePayment;
	}
}
