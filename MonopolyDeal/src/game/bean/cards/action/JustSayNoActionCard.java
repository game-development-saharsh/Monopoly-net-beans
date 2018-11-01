package game.bean.cards.action;


import constants.CardConstants;

public class JustSayNoActionCard extends ActionCard {
	private int typeOfAction = CardConstants.CEASE_ACTION_CARD;
	public JustSayNoActionCard() {
		setValueInMn(4);
	}
	@Override
	public int getTypeOfAction() {
		return typeOfAction;
	}
}
