package game.bean.cards.action;

import constants.CardConstants;

public abstract class ValueAdditionOnPropertyActionCard extends ActionCard {
	private int typeOfAction = CardConstants.VALUE_ADDITION_ACTION_CARD;
	@Override
	public int getTypeOfAction() {
		// TODO Auto-generated method stub
		return typeOfAction;
	}

}
