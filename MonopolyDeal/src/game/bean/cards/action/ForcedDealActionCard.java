package game.bean.cards.action;

import constants.CardConstants;

public class ForcedDealActionCard extends ActionCard {
	private int typeOfAction = CardConstants.SWAP_A_SINGLE_CARD_ACTION_CARD;
	public ForcedDealActionCard() {
		setValueInMn(3);
	}
	@Override
	public int getTypeOfAction() {
		return typeOfAction;
	}
}
