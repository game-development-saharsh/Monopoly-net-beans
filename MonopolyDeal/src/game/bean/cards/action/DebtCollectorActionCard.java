package game.bean.cards.action;

import constants.CardConstants;

public class DebtCollectorActionCard extends PaymentActionCard {

	private int typeOfMakePayment = CardConstants.CASH_PAYMENT_ACTION_CARD;
	public DebtCollectorActionCard() {
		setValueInMn(3);
	}
	@Override
	public int getTypeOfMakePayment() {
		// TODO Auto-generated method stub
		return typeOfMakePayment;
	}
}
