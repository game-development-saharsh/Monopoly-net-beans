package game.bean.cards.action;

import constants.CardConstants;

public class SkyblueOrBrownRendCard extends RentOnPaymentPropertyActionCard {
	private int numberOfDifferentColoredSetsOnWhichPaymentCanBeAsked = CardConstants.SKYBLUE_OR_BROWN_COLOR_RENT_CARD;
	public SkyblueOrBrownRendCard() {
		setValueInMn(1);
	}
	@Override
	public int getNumberOfDifferentColoredSetsOnWhichPaymentCanBeAsked() {
		// TODO Auto-generated method stub
		return numberOfDifferentColoredSetsOnWhichPaymentCanBeAsked;
	}
}
