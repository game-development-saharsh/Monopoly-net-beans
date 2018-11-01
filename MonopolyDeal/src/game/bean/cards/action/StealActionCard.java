package game.bean.cards.action;

import constants.CardConstants;

public abstract class StealActionCard extends ActionCard {
	private int typeOfAction = CardConstants.STEAL_ACTION_CARD;
	@Override
	public int getTypeOfAction() {
		// TODO Auto-generated method stub
		return typeOfAction;
	}
	public abstract int getCountOfCardsThatCanBeSnatched();
}
