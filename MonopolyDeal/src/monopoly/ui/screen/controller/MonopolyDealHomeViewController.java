/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package monopoly.ui.screen.controller;

import com.sun.xml.internal.bind.v2.model.core.MaybeElement;
import constants.CardConstants;
import game.bean.cards.Card;
import game.bean.cards.PlayingCardForTransition;
import game.bean.cards.action.ActionCard;
import game.bean.cards.action.AnyColorRentActionCard;
import game.bean.cards.action.BirthdayPaymentActionCard;
import game.bean.cards.action.BlueOrGreenRentCard;
import game.bean.cards.action.DealBreakerActionCard;
import game.bean.cards.action.DebtCollectorActionCard;
import game.bean.cards.action.DoubleTheRentActionCard;
import game.bean.cards.action.ForcedDealActionCard;
import game.bean.cards.action.GreyOrBlackRentCard;
import game.bean.cards.action.HotelActionCard;
import game.bean.cards.action.HouseActionCard;
import game.bean.cards.action.JustSayNoActionCard;
import game.bean.cards.action.PassGoActionCard;
import game.bean.cards.action.PinkOrOrangeRentCard;
import game.bean.cards.action.RedOrYellowRentCard;
import game.bean.cards.action.SkyblueOrBrownRendCard;
import game.bean.cards.action.SlyDealActionCard;
import game.bean.cards.money.MoneyCard;
import game.bean.cards.property.BlackSingleColorPropertyCard;
import game.bean.cards.property.BlueSingleColorPropertyCard;
import game.bean.cards.property.BrownSingleColorPropertyCard;
import game.bean.cards.property.DualColorPropertyCard;
import game.bean.cards.property.GreenSingleColorPropertyCard;
import game.bean.cards.property.GreySingleColorPropertyCard;
import game.bean.cards.property.OrangeSingleColorPropertyCard;
import game.bean.cards.property.PinkSingleColorPropertyCard;
import game.bean.cards.property.PropertyCard;
import game.bean.cards.property.RedSingleColorPropertyCard;
import game.bean.cards.property.SingleColorPropertyCard;
import game.bean.cards.property.SkyblueSingleColorPropertyCard;
import game.bean.cards.property.YellowSingleColorPropertyCard;
import java.awt.Image;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.ImageIcon;
import monopoly.beans.GameDetail;
import monopoly.beans.GameState;
import monopoly.constants.MonopolyDealConstants;

/**
 *
 * @author Reena
 */
public class MonopolyDealHomeViewController {

    class NameForImage {

        private String nameOfImage;
        private ImageIcon image;

        /**
         * @return the nameOfImage
         */
        public String getNameOfImage() {
            return nameOfImage;
        }

        /**
         * @param nameOfImage the nameOfImage to set
         */
        public void setNameOfImage(String nameOfImage) {
            this.nameOfImage = nameOfImage;
        }

        /**
         * @return the image
         */
        public ImageIcon getImage() {
            return image;
        }

        /**
         * @param image the image to set
         */
        public void setImage(ImageIcon image) {
            this.image = image;
        }
    }
    private GameDetail gameDetail;
    private GameState gameState;
    private Map<String, ImageIcon> mapOfImageIconForAllCards;
    private List<NameForImage> listOfNameForImages;

    public MonopolyDealHomeViewController(GameDetail gameMoves, GameState gameState) {
        this.gameDetail = gameMoves;
        this.gameState = gameState;
        setUpGame();
    }

    private void setUpGame() {
        listOfNameForImages = new ArrayList<>();
        mapOfImageIconForAllCards = createMappingOfImageIconForAllCards();
    }

    private ImageIcon createImageIconForPlayingCardForTransition(String nameOfPlayingCardForTransition) {
        File imageFile = new File(MonopolyDealConstants.RELATIVE_PATH_OF_IMAGES + nameOfPlayingCardForTransition + MonopolyDealConstants.FILE_PNG_FORMAT);
        if (imageFile.exists()) {
            ImageIcon imageIcon = new ImageIcon(imageFile.getPath());
            return new ImageIcon(imageIcon.getImage().getScaledInstance(90, 45, Image.SCALE_SMOOTH));
        } else {
            System.out.println(nameOfPlayingCardForTransition + ".png image is not present.");
            return null;
        }


    }

    private String fetchNameForIcon(ImageIcon image) {
        String nameOfIcon = null;
        for (int i = 0; i < listOfNameForImages.size(); i++) {
            NameForImage nameForImage = listOfNameForImages.get(i);
            if (image.equals(nameForImage.getImage())) {
                nameOfIcon = nameForImage.getNameOfImage();
                break;
            }
        }
        return nameOfIcon;
    }

    private Map<String, ImageIcon> createMappingOfImageIconForAllCards() {
        String[] nameOfCards = CardConstants.NAMES_OF_CARDS;
        Map<String, ImageIcon> mapOfImageIcons = new HashMap<>();
        for (int i = 0; i < nameOfCards.length; i++) {
            String cardName = nameOfCards[i];
            ImageIcon iconForCard = createImageIconForPlayingCardForTransition(cardName);
            mapOfImageIcons.put(cardName, iconForCard);
            NameForImage nameForImage = new NameForImage();
            nameForImage.setNameOfImage(cardName);
            nameForImage.setImage(iconForCard);
            listOfNameForImages.add(nameForImage);
        }
        return mapOfImageIcons;
    }

    public int countOfCardsInDrawPile() {
        return gameState.getDrawPile().size();
    }

    public int countOfCardsInPlayPile() {
        return gameState.getPlayPile().size();
    }

    public int countOfCardsInOpponentBank() {
        if (gameDetail.getMyPositionInGame() == 0) {
            //I am player 1
            return gameState.getCardsInPlayerTwoBank().size();
        } else if (gameDetail.getMyPositionInGame() == 1) {
            //I am player 2
            return gameState.getCardsInPlayerOneBank().size();
        }
        return 0;
    }

    public int countOfCardsInOpponentHand() {
        if (gameDetail.getMyPositionInGame() == 0) {
            //I am player 1
            return gameState.getCardsInPlayerTwoHand().size();
        } else if (gameDetail.getMyPositionInGame() == 1) {
            //I am player 2
            return gameState.getCardsInPlayerOneHand().size();
        }
        return 0;
    }

    public int countOfCardsInOpponentSets() {
        List<List<PlayingCardForTransition>> setOfOponent = null;
        int count = 0;
        if (gameDetail.getMyPositionInGame() == 0) {
            //I am player 1
            setOfOponent = gameState.getSetOfPlayerTwo();
        } else if (gameDetail.getMyPositionInGame() == 1) {
            //I am player 2
            setOfOponent = gameState.getSetOfPlayerone();
        }
        for (int i = 0; i < setOfOponent.size(); i++) {
            List<PlayingCardForTransition> row = setOfOponent.get(i);
            for (int j = 0; j < row.size(); j++) {
                PlayingCardForTransition playingCardForTransition = row.get(j);
                if (!playingCardForTransition.getNameOfCard().equals(CardConstants.NAME_OF_DUMMY_PLAYING_CARD)) {
                    count++;
                }
            }
        }
        return count;
    }

    public Map<String, String> fetchDetailsOfSelectedCardForIcon(ImageIcon imageIcon) {
        Map<String, String> detailsOfSelectedCard = null;
        if (imageIcon != null) {
            String cardName = fetchNameForIcon(imageIcon);
            if (cardName != null) {
                Card card = CardConstants.fetchKindOfCardForName(cardName);
                return fetchDetailsOfSelectedCard(card);
            }
        }
        return detailsOfSelectedCard;
    }

    private Map<String, String> fetchDetailsOfSelectedCard(Card card) {
        int typeOfCard = card.getTypeOfCard();
        switch (typeOfCard) {
            case CardConstants.CARD_TYPE_PROPERTY:
                PropertyCard propertyCard = (PropertyCard) card;
                return fetchDetailsOfSelectedPropertyCard(propertyCard);

            case CardConstants.CARD_TYPE_MONEY:
                MoneyCard moneyCard = (MoneyCard) card;
                return fetchDetailsOfSelectedMoneyCard(moneyCard);

            case CardConstants.CARD_TYPE_ACTION:
                ActionCard actionCard = (ActionCard) card;
                return fetchDetailsOfSelectedActionCard(actionCard);

            default:
                return null;
        }
    }

    private Map<String, String> fetchDetailsOfSelectedPropertyCard(PropertyCard propertyCard) {
        int numberOfColours = propertyCard.getNumberOfColours();
        switch (numberOfColours) {
            case CardConstants.SINGLE_COLOR_PROPERTY_CARD:
                SingleColorPropertyCard singleColorPropertyCard = (SingleColorPropertyCard) propertyCard;
                return fetchDetailsOfSingleColorPropertyCard(singleColorPropertyCard);
            case CardConstants.DUAL_COLOR_PROPERTY_CARD:
                DualColorPropertyCard dualColorPropertyCard = (DualColorPropertyCard) propertyCard;
                return fetchDetailsOfSelectedDualColorPropertyCard(dualColorPropertyCard);
            case CardConstants.ALL_COLOR_PROPERTY_CARD:
                return fetchDetailsOfSelectedAllColorPropertyCard();
            default:
                return null;
        }
    }

    private Map<String, String> fetchDetailsOfSingleColorPropertyCard(SingleColorPropertyCard singleColorPropertyCard) {
        Map<String, String> detailsOfSelectedSingleColorPropertyCard = new HashMap<>();
        int typeOfColor = singleColorPropertyCard.getTypeOfColor();
        switch (typeOfColor) {
            case CardConstants.BLACK_SINGLE_COLOR_PROPERTY_CARD:
                detailsOfSelectedSingleColorPropertyCard.put("topColor", "Black");
                detailsOfSelectedSingleColorPropertyCard.put("bottomColor", "Black");
                detailsOfSelectedSingleColorPropertyCard.put("name", "Black Property");
                break;
            case CardConstants.BLUE_SINGLE_COLOR_PROPERTY_CARD:
                detailsOfSelectedSingleColorPropertyCard.put("topColor", "Blue");
                detailsOfSelectedSingleColorPropertyCard.put("bottomColor", "Blue");
                detailsOfSelectedSingleColorPropertyCard.put("name", "Blue Property");
                break;
            case CardConstants.BROWN_SINGLE_COLOR_PROPERTY_CARD:
                detailsOfSelectedSingleColorPropertyCard.put("topColor", "Brown");
                detailsOfSelectedSingleColorPropertyCard.put("bottomColor", "Brown");
                detailsOfSelectedSingleColorPropertyCard.put("name", "Brown Property");
                break;
            case CardConstants.GREEN_SINGLE_COLOR_PROPERTY_CARD:
                detailsOfSelectedSingleColorPropertyCard.put("topColor", "Green");
                detailsOfSelectedSingleColorPropertyCard.put("bottomColor", "Green");
                detailsOfSelectedSingleColorPropertyCard.put("name", "Green Property");
                break;
            case CardConstants.GREY_SINGLE_COLOR_PROPERTY_CARD:
                detailsOfSelectedSingleColorPropertyCard.put("topColor", "Grey");
                detailsOfSelectedSingleColorPropertyCard.put("bottomColor", "Grey");
                detailsOfSelectedSingleColorPropertyCard.put("name", "Grey Property");
                break;
            case CardConstants.ORANGE_SINGLE_COLOR_PROPERTY_CARD:
                detailsOfSelectedSingleColorPropertyCard.put("topColor", "Orange");
                detailsOfSelectedSingleColorPropertyCard.put("bottomColor", "Orange");
                detailsOfSelectedSingleColorPropertyCard.put("name", "Orange Property");
                break;
            case CardConstants.PINK_SINGLE_COLOR_PROPERTY_CARD:
                detailsOfSelectedSingleColorPropertyCard.put("topColor", "Pink");
                detailsOfSelectedSingleColorPropertyCard.put("bottomColor", "Pink");
                detailsOfSelectedSingleColorPropertyCard.put("name", "Pink Property");
                break;
            case CardConstants.RED_SINGLE_COLOR_PROPERTY_CARD:
                detailsOfSelectedSingleColorPropertyCard.put("topColor", "Red");
                detailsOfSelectedSingleColorPropertyCard.put("bottomColor", "Red");
                detailsOfSelectedSingleColorPropertyCard.put("name", "Red Property");
                break;
            case CardConstants.SKYBLUE_SINGLE_COLOR_PROPERTY_CARD:
                detailsOfSelectedSingleColorPropertyCard.put("topColor", "SkyBlue");
                detailsOfSelectedSingleColorPropertyCard.put("bottomColor", "SkyBlue");
                detailsOfSelectedSingleColorPropertyCard.put("name", "Sky Blue Property");
                break;
            case CardConstants.YELLOW_SINGLE_COLOR_PROPERTY_CARD:
                detailsOfSelectedSingleColorPropertyCard.put("topColor", "Yellow");
                detailsOfSelectedSingleColorPropertyCard.put("bottomColor", "Yellow");
                detailsOfSelectedSingleColorPropertyCard.put("name", "Yellow Property");
                break;
            default:
                return null;
        }
        StringBuilder body = new StringBuilder("<html>");
        body.append("Value in mn : ").append(singleColorPropertyCard.getValueInMn());
        body.append("<br/>");
        body.append("cards for set: ").append(singleColorPropertyCard.getMinimumNumberOfCardsForSet());
        body.append("<br/>");
        for (int i = 1; i <= singleColorPropertyCard.getMinimumNumberOfCardsForSet(); i++) {
            body.append(i).append(" set: ").append(singleColorPropertyCard.getRentForSet()[i-1]);
            body.append("<br/>");
        }
        body.append("</html>");
        detailsOfSelectedSingleColorPropertyCard.put("body1", body.toString());
        detailsOfSelectedSingleColorPropertyCard.put("body2", body.toString());
        return detailsOfSelectedSingleColorPropertyCard;
    }

    private Map<String, String> fetchDetailsOfSelectedDualColorPropertyCard(DualColorPropertyCard dualColorPropertyCard) {
        Map<String, String> detailsOfSelectedDualColorPropertyCard = new HashMap<>();
        int typeOfColor = dualColorPropertyCard.getTypeOfColor();
        SingleColorPropertyCard detailsOfFirstColor;
        SingleColorPropertyCard detailsOfSecondColor;
        switch (typeOfColor) {
            case CardConstants.GREEN_BLACK_DUAL_COLOR_PROPERTY_CARD:
                detailsOfSelectedDualColorPropertyCard.put(CardConstants.TOP_COLOR, "Green");
                detailsOfFirstColor = new GreenSingleColorPropertyCard();
                detailsOfSelectedDualColorPropertyCard.put(CardConstants.BOTTOM_COLOR, "Black");
                detailsOfSecondColor = new BlackSingleColorPropertyCard();
                detailsOfSelectedDualColorPropertyCard.put("name", "Green/Black Property");
                break;
            case CardConstants.GREEN_BLUE_DUAL_COLOR_PROPERTY_CARD:
                detailsOfSelectedDualColorPropertyCard.put(CardConstants.TOP_COLOR, "Green");
                detailsOfFirstColor = new GreenSingleColorPropertyCard();
                detailsOfSelectedDualColorPropertyCard.put(CardConstants.BOTTOM_COLOR, "Blue");
                detailsOfSecondColor = new BlueSingleColorPropertyCard();
                detailsOfSelectedDualColorPropertyCard.put("name", "Green/Blue Property");
                break;
            case CardConstants.GREY_BLACK_DUAL_COLOR_PROPERTY_CARD:
                detailsOfSelectedDualColorPropertyCard.put(CardConstants.TOP_COLOR, "Grey");
                detailsOfFirstColor = new GreySingleColorPropertyCard();
                detailsOfSelectedDualColorPropertyCard.put(CardConstants.BOTTOM_COLOR, "Black");
                detailsOfSecondColor = new BlackSingleColorPropertyCard();
                detailsOfSelectedDualColorPropertyCard.put("name", "Green/Black Property");
                break;
            case CardConstants.PINK_ORANGE_DUAL_COLOR_PROPERTY_CARD:
                detailsOfSelectedDualColorPropertyCard.put(CardConstants.TOP_COLOR, "Pink");
                detailsOfFirstColor = new PinkSingleColorPropertyCard();
                detailsOfSelectedDualColorPropertyCard.put(CardConstants.BOTTOM_COLOR, "Orange");
                detailsOfSecondColor = new OrangeSingleColorPropertyCard();
                detailsOfSelectedDualColorPropertyCard.put("name", "Pink/Orange Property");
                break;
            case CardConstants.RED_YELLOW_DUAL_COLOR_PROPERTY_CARD:
                detailsOfSelectedDualColorPropertyCard.put(CardConstants.TOP_COLOR, "Red");
                detailsOfFirstColor = new RedSingleColorPropertyCard();
                detailsOfSelectedDualColorPropertyCard.put(CardConstants.BOTTOM_COLOR, "Yellow");
                detailsOfSecondColor = new YellowSingleColorPropertyCard();
                detailsOfSelectedDualColorPropertyCard.put("name", "Red/Yellow Property");
                break;
            case CardConstants.SKYBLUE_BROWN_DUAL_COLOR_PROPERTY_CARD:
                detailsOfSelectedDualColorPropertyCard.put(CardConstants.TOP_COLOR, "SkyBlue");
                detailsOfFirstColor = new SkyblueSingleColorPropertyCard();
                detailsOfSelectedDualColorPropertyCard.put(CardConstants.BOTTOM_COLOR, "Brown");
                detailsOfSecondColor = new BrownSingleColorPropertyCard();
                detailsOfSelectedDualColorPropertyCard.put("name", "Sky Blue/Brown Property");
                break;
            case CardConstants.SKYBLUE_BLACK_DUAL_COLOR_PROPERTY_CARD:
                detailsOfSelectedDualColorPropertyCard.put(CardConstants.TOP_COLOR, "SkyBlue");
                detailsOfFirstColor = new SkyblueSingleColorPropertyCard();
                detailsOfSelectedDualColorPropertyCard.put(CardConstants.BOTTOM_COLOR, "Black");
                detailsOfSecondColor = new BlackSingleColorPropertyCard();
                detailsOfSelectedDualColorPropertyCard.put("name", "Sky Blue/Black Property");
                break;
            default:
                return null;
        }
        StringBuilder body = new StringBuilder("<html>");
        body.append("Value in mn : ").append(dualColorPropertyCard.getValueInMn());
        body.append("<br/>");
        body.append("cards for set: ").append(detailsOfFirstColor.getMinimumNumberOfCardsForSet());
        body.append("<br/>");
        for (int i = 1; i <= detailsOfFirstColor.getMinimumNumberOfCardsForSet(); i++) {
            body.append(i).append(" set: ").append(detailsOfFirstColor.getRentForSet()[i-1]);
            body.append("<br/>");
        }
        body.append("</html>");
        detailsOfSelectedDualColorPropertyCard.put("body1", body.toString());

        StringBuilder body1 = new StringBuilder("<html>");
        body1.append("Value in mn : ").append(dualColorPropertyCard.getValueInMn());
        body1.append("<br/>");
        body1.append("cards for set: ").append(detailsOfSecondColor.getMinimumNumberOfCardsForSet());
        body1.append("<br/>");
        for (int i = 1; i <= detailsOfSecondColor.getMinimumNumberOfCardsForSet(); i++) {
            body1.append(i).append(" set: ").append(detailsOfSecondColor.getRentForSet()[i-1]);
            body1.append("<br/>");
        }
        detailsOfSelectedDualColorPropertyCard.put("body2", body1.toString());
        return detailsOfSelectedDualColorPropertyCard;
    }

    private Map<String, String> fetchDetailsOfSelectedAllColorPropertyCard() {
        Map<String, String> detailsOfSelectedAllColorPropertyCard = new HashMap<>();
        detailsOfSelectedAllColorPropertyCard.put(CardConstants.TOP_COLOR, "White");
        detailsOfSelectedAllColorPropertyCard.put(CardConstants.BOTTOM_COLOR, "White");
        detailsOfSelectedAllColorPropertyCard.put("name", "Property Wild Card");
        detailsOfSelectedAllColorPropertyCard.put("body1", "");
        detailsOfSelectedAllColorPropertyCard.put("body2", "");
        return detailsOfSelectedAllColorPropertyCard;
    }

    private Map<String, String> fetchDetailsOfSelectedActionCard(ActionCard actionCard) {
        Map<String, String> detailsOfSelectedActionCard = new HashMap<>();
        if (actionCard instanceof SlyDealActionCard) {
            detailsOfSelectedActionCard.put("name", "Sly Deal");
            detailsOfSelectedActionCard.put(CardConstants.TOP_COLOR, "White");
            detailsOfSelectedActionCard.put(CardConstants.BOTTOM_COLOR, "White");
            detailsOfSelectedActionCard.put("body1", actionCard.getValueInMn() + " Mn");
            detailsOfSelectedActionCard.put("body2", actionCard.getValueInMn() + " Mn");
        } else if (actionCard instanceof PassGoActionCard) {
            detailsOfSelectedActionCard.put("name", "Pass Go");
            detailsOfSelectedActionCard.put(CardConstants.TOP_COLOR, "White");
            detailsOfSelectedActionCard.put(CardConstants.BOTTOM_COLOR, "White");
            detailsOfSelectedActionCard.put("body1", actionCard.getValueInMn() + " Mn");
            detailsOfSelectedActionCard.put("body2", actionCard.getValueInMn() + " Mn");
        } else if (actionCard instanceof JustSayNoActionCard) {
            detailsOfSelectedActionCard.put("name", "Just Say No!");
            detailsOfSelectedActionCard.put(CardConstants.TOP_COLOR, "White");
            detailsOfSelectedActionCard.put(CardConstants.BOTTOM_COLOR, "White");
            detailsOfSelectedActionCard.put("body1", actionCard.getValueInMn() + " Mn");
            detailsOfSelectedActionCard.put("body2", actionCard.getValueInMn() + " Mn");
        } else if (actionCard instanceof HouseActionCard) {
            detailsOfSelectedActionCard.put("name", "House");
            detailsOfSelectedActionCard.put(CardConstants.TOP_COLOR, "White");
            detailsOfSelectedActionCard.put(CardConstants.BOTTOM_COLOR, "White");
            detailsOfSelectedActionCard.put("body1", actionCard.getValueInMn() + " Mn");
            detailsOfSelectedActionCard.put("body2", actionCard.getValueInMn() + " Mn");
        } else if (actionCard instanceof HotelActionCard) {
            detailsOfSelectedActionCard.put("name", "Hotel");
            detailsOfSelectedActionCard.put(CardConstants.TOP_COLOR, "White");
            detailsOfSelectedActionCard.put(CardConstants.BOTTOM_COLOR, "White");
            detailsOfSelectedActionCard.put("body1", actionCard.getValueInMn() + " Mn");
            detailsOfSelectedActionCard.put("body2", actionCard.getValueInMn() + " Mn");
        } else if (actionCard instanceof BirthdayPaymentActionCard) {
            detailsOfSelectedActionCard.put("name", "It's My Birthday!");
            detailsOfSelectedActionCard.put(CardConstants.TOP_COLOR, "White");
            detailsOfSelectedActionCard.put(CardConstants.BOTTOM_COLOR, "White");
            detailsOfSelectedActionCard.put("body1", actionCard.getValueInMn() + " Mn");
            detailsOfSelectedActionCard.put("body2", actionCard.getValueInMn() + " Mn");
        } else if (actionCard instanceof AnyColorRentActionCard) {
            detailsOfSelectedActionCard.put("name", "Any Color Rent");
            detailsOfSelectedActionCard.put(CardConstants.TOP_COLOR, "White");
            detailsOfSelectedActionCard.put(CardConstants.BOTTOM_COLOR, "White");
            detailsOfSelectedActionCard.put("body1", actionCard.getValueInMn() + " Mn");
            detailsOfSelectedActionCard.put("body2", actionCard.getValueInMn() + " Mn");
        } else if (actionCard instanceof DealBreakerActionCard) {
            detailsOfSelectedActionCard.put("name", "Deal Breaker");
            detailsOfSelectedActionCard.put(CardConstants.TOP_COLOR, "White");
            detailsOfSelectedActionCard.put(CardConstants.BOTTOM_COLOR, "White");
            detailsOfSelectedActionCard.put("body1", actionCard.getValueInMn() + " Mn");
            detailsOfSelectedActionCard.put("body2", actionCard.getValueInMn() + " Mn");
        } else if (actionCard instanceof DebtCollectorActionCard) {
            detailsOfSelectedActionCard.put("name", "Debt Collector");
            detailsOfSelectedActionCard.put(CardConstants.TOP_COLOR, "White");
            detailsOfSelectedActionCard.put(CardConstants.BOTTOM_COLOR, "White");
            detailsOfSelectedActionCard.put("body1", actionCard.getValueInMn() + " Mn");
            detailsOfSelectedActionCard.put("body2", actionCard.getValueInMn() + " Mn");
        } else if (actionCard instanceof DoubleTheRentActionCard) {
            detailsOfSelectedActionCard.put("name", "Double The Rent");
            detailsOfSelectedActionCard.put(CardConstants.TOP_COLOR, "White");
            detailsOfSelectedActionCard.put(CardConstants.BOTTOM_COLOR, "White");
            detailsOfSelectedActionCard.put("body1", actionCard.getValueInMn() + " Mn");
            detailsOfSelectedActionCard.put("body2", actionCard.getValueInMn() + " Mn");
        } else if (actionCard instanceof ForcedDealActionCard) {
            detailsOfSelectedActionCard.put("name", "Forced Deal");
            detailsOfSelectedActionCard.put(CardConstants.TOP_COLOR, "White");
            detailsOfSelectedActionCard.put(CardConstants.BOTTOM_COLOR, "White");
            detailsOfSelectedActionCard.put("body1", actionCard.getValueInMn() + " Mn");
            detailsOfSelectedActionCard.put("body2", actionCard.getValueInMn() + " Mn");
        } else if (actionCard instanceof BlueOrGreenRentCard) {
            detailsOfSelectedActionCard.put(CardConstants.TOP_COLOR, "Blue");
            detailsOfSelectedActionCard.put(CardConstants.BOTTOM_COLOR, "Green");
            detailsOfSelectedActionCard.put("body1", actionCard.getValueInMn() + " Mn");
            detailsOfSelectedActionCard.put("body2", actionCard.getValueInMn() + " Mn");
            detailsOfSelectedActionCard.put("name", "Blue/Green Rent");
        } else if (actionCard instanceof GreyOrBlackRentCard) {
            detailsOfSelectedActionCard.put(CardConstants.TOP_COLOR, "Grey");
            detailsOfSelectedActionCard.put(CardConstants.BOTTOM_COLOR, "Black");
            detailsOfSelectedActionCard.put("body1", actionCard.getValueInMn() + " Mn");
            detailsOfSelectedActionCard.put("body2", actionCard.getValueInMn() + " Mn");
            detailsOfSelectedActionCard.put("name", "Grey/Black Rent");
        } else if (actionCard instanceof PinkOrOrangeRentCard) {
            detailsOfSelectedActionCard.put(CardConstants.TOP_COLOR, "Pink");
            detailsOfSelectedActionCard.put(CardConstants.BOTTOM_COLOR, "Orange");
            detailsOfSelectedActionCard.put("body1", actionCard.getValueInMn() + " Mn");
            detailsOfSelectedActionCard.put("body2", actionCard.getValueInMn() + " Mn");
            detailsOfSelectedActionCard.put("name", "Pink/Orange Rent");
        } else if (actionCard instanceof RedOrYellowRentCard) {
            detailsOfSelectedActionCard.put(CardConstants.TOP_COLOR, "Red");
            detailsOfSelectedActionCard.put(CardConstants.BOTTOM_COLOR, "Yellow");
            detailsOfSelectedActionCard.put("body1", actionCard.getValueInMn() + " Mn");
            detailsOfSelectedActionCard.put("body2", actionCard.getValueInMn() + " Mn");
            detailsOfSelectedActionCard.put("name", "Red/Yellow Rent");
        } else if (actionCard instanceof SkyblueOrBrownRendCard) {
            detailsOfSelectedActionCard.put(CardConstants.TOP_COLOR, "SkyBlue");
            detailsOfSelectedActionCard.put(CardConstants.BOTTOM_COLOR, "Brown");
            detailsOfSelectedActionCard.put("body1", actionCard.getValueInMn() + " Mn");
            detailsOfSelectedActionCard.put("body2", actionCard.getValueInMn() + " Mn");
            detailsOfSelectedActionCard.put("name", "SkyBlue/Brown Rent");
        }
        return detailsOfSelectedActionCard;
    }

    private Map<String, String> fetchDetailsOfSelectedMoneyCard(MoneyCard moneyCard) {
        Map<String, String> detailsOfSelectedMoneyCard = new HashMap<>();
        detailsOfSelectedMoneyCard.put(CardConstants.TOP_COLOR, "White");
        detailsOfSelectedMoneyCard.put(CardConstants.BOTTOM_COLOR, "White");
        detailsOfSelectedMoneyCard.put("name", " ");
        detailsOfSelectedMoneyCard.put("body1", moneyCard.getValueInMn() + " Mn");
        detailsOfSelectedMoneyCard.put("body2", moneyCard.getValueInMn() + " Mn");
        return detailsOfSelectedMoneyCard;
    }

    public String fetchCustomMessage() {
        return gameState.getCustomMessage();
    }

    public List<ImageIcon> prepareContentOfMyBankTable() {
        List<ImageIcon> contentOfMyBank = new ArrayList<>();
        List<PlayingCardForTransition> cardsInBank = null;
        if (gameDetail.getMyPositionInGame() == 0) {
            //I am player 1
            cardsInBank = gameState.getCardsInPlayerOneBank();
        } else if (gameDetail.getMyPositionInGame() == 1) {
            //I am player 2
            cardsInBank = gameState.getCardsInPlayerTwoBank();
        }
        for (int j = 0; j < cardsInBank.size(); j++) {
            PlayingCardForTransition playingCardForTransition = cardsInBank.get(j);
            //add suitable icon
            contentOfMyBank.add(mapOfImageIconForAllCards.get(playingCardForTransition.getNameOfCard()));
        }
        return contentOfMyBank;
    }

    public List<ImageIcon> prepareContentOfMyHand() {
        List<ImageIcon> contentOfMyHand = new ArrayList<>();
        List<PlayingCardForTransition> cardsInHand = null;
        if (gameDetail.getMyPositionInGame() == 0) {
            //I am player 1
            cardsInHand = gameState.getCardsInPlayerOneHand();
        } else if (gameDetail.getMyPositionInGame() == 1) {
            //I am player 2
            cardsInHand = gameState.getCardsInPlayerTwoHand();
        }
        for (int j = 0; j < cardsInHand.size(); j++) {
            PlayingCardForTransition playingCardForTransition = cardsInHand.get(j);
            //add suitable icon
            contentOfMyHand.add(mapOfImageIconForAllCards.get(playingCardForTransition.getNameOfCard()));
        }
        return contentOfMyHand;
    }

    public List<List<ImageIcon>> prepareContentOfOpponentSets(ImageIcon blankIcon) {
        List<List<ImageIcon>> listForCardsInOpponentSetsTable = new ArrayList<>();
        List<List<PlayingCardForTransition>> setOfOpponent = null;
        System.out.println("position: " + gameDetail.getMyPositionInGame());
        if (gameDetail.getMyPositionInGame() == 0) {
            //I am player 1
            setOfOpponent = gameState.getSetOfPlayerTwo();
        } else if (gameDetail.getMyPositionInGame() == 1) {
            //I am player 2
            setOfOpponent = gameState.getSetOfPlayerone();
        }
        for (int i = 0; i < setOfOpponent.size(); i++) {
            List<PlayingCardForTransition> rowsInSet = setOfOpponent.get(i);
            List<ImageIcon> row = new ArrayList<>();
            for (int j = 0; j < rowsInSet.size(); j++) {
                PlayingCardForTransition playingCardForTransition = rowsInSet.get(j);
                if (playingCardForTransition.getNameOfCard().equals(CardConstants.NAME_OF_DUMMY_PLAYING_CARD)) {
                    row.add(blankIcon);
                } else {
                    //add suitable icon
                    row.add(mapOfImageIconForAllCards.get(playingCardForTransition.getNameOfCard()));
                }

            }
            listForCardsInOpponentSetsTable.add(row);
        }
        return listForCardsInOpponentSetsTable;
    }

    public List<List<ImageIcon>> prepareContentOfMySets(ImageIcon blankIcon) {
        List<List<ImageIcon>> listForCardsInMySetsTable = new ArrayList<>();
        List<List<PlayingCardForTransition>> setOfPlayer = null;
        System.out.println("position: " + gameDetail.getMyPositionInGame());
        if (gameDetail.getMyPositionInGame() == 0) {
            //I am player 1
            setOfPlayer = gameState.getSetOfPlayerone();
        } else if (gameDetail.getMyPositionInGame() == 1) {
            //I am player 2
            setOfPlayer = gameState.getSetOfPlayerTwo();
        }
        for (int i = 0; i < setOfPlayer.size(); i++) {
            List<PlayingCardForTransition> rowsInSet = setOfPlayer.get(i);
            List<ImageIcon> row = new ArrayList<>();
            for (int j = 0; j < rowsInSet.size(); j++) {
                PlayingCardForTransition playingCardForTransition = rowsInSet.get(j);
                if (playingCardForTransition.getNameOfCard().equals(CardConstants.NAME_OF_DUMMY_PLAYING_CARD)) {
                    row.add(blankIcon);
                } else {
                    //add suitable icon
                    row.add(mapOfImageIconForAllCards.get(playingCardForTransition.getNameOfCard()));
                }

            }
            listForCardsInMySetsTable.add(row);
        }
        return listForCardsInMySetsTable;
    }

    /**
     * @return the gameMoves
     */
    public GameDetail getGameMoves() {
        return gameDetail;
    }

    /**
     * @return the gameState
     */
    public GameState getGameState() {
        return gameState;
    }

    /**
     * @param gameState the gameState to set
     */
    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }
}
