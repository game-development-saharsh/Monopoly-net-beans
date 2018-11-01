package game.bean.cards.action;

import constants.CardConstants;

public abstract class PaymentActionCard extends ActionCard {
	private int typeOfAction = CardConstants.MAKE_PAY_ACTION_CARD;
	@Override
	public int getTypeOfAction() {
		return typeOfAction;
	}
	public abstract int getTypeOfMakePayment();
	
}
