package game.bean.cards.action;

import constants.CardConstants;

public class SlyDealActionCard extends StealActionCard {
	int countOfCardsThatCanBeSnatched = CardConstants.STEAL_A_SINGLE_CARD_ACTION_CARD;
	public SlyDealActionCard() {
		// TODO Auto-generated constructor stub
		setValueInMn(3);
	}
	@Override
	public int getCountOfCardsThatCanBeSnatched() {
		// TODO Auto-generated method stub
		return 0;
	}
}
