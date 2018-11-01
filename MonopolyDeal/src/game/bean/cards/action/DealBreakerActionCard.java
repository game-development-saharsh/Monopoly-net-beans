package game.bean.cards.action;

import constants.CardConstants;

public class DealBreakerActionCard extends StealActionCard {
	int countOfCardsThatCanBeSnatched = CardConstants.STEAL_A_SET_ACTION_CARD;
	public DealBreakerActionCard() {
		// TODO Auto-generated constructor stub
		setValueInMn(5);
	}
	@Override
	public int getCountOfCardsThatCanBeSnatched() {
		// TODO Auto-generated method stub
		return 0;
	}
}
