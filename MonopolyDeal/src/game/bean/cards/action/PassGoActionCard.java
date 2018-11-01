package game.bean.cards.action;

import constants.CardConstants;

public class PassGoActionCard extends ActionCard {
	private int typeOfAction = CardConstants.DRAW_ACTION_CARD;

	public PassGoActionCard() {
		setValueInMn(1);
	}
	@Override
	public int getTypeOfAction() {
		return typeOfAction;
	}
}
