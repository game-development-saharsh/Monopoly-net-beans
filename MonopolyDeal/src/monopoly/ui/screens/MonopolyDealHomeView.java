/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package monopoly.ui.screens;

/**
 *
 * @author Reena
 */
import constants.CardConstants;
import game.bean.cards.PlayingCardForTransition;
import java.awt.Color;
import java.awt.Image;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.swing.ImageIcon;
import javax.swing.table.DefaultTableModel;
import monopoly.beans.Game;
import monopoly.beans.GameDetail;
import monopoly.beans.GameState;
import monopoly.constants.MonopolyDealConstants;
import monopoly.ui.screen.controller.MonopolyDealHomeViewController;

public final class MonopolyDealHomeView extends javax.swing.JFrame {

    private GameDetail gameDetail;
    private GameState gameState;
    MonopolyDealHomeViewController monopolyDealHomeViewController;

    /**
     * @return the gameDetail
     */
    public GameDetail getGameDetail() {
        return gameDetail;
    }

    /**
     * @param gameDetail the gameDetail to set
     */
    public void setGameDetail(GameDetail gameDetail) {
        this.gameDetail = gameDetail;
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

    class CellSelected {

        private int x;
        private int y;

        /**
         * @return the x
         */
        public int getX() {
            return x;
        }

        /**
         * @param x the x to set
         */
        public void setX(int x) {
            this.x = x;
        }

        /**
         * @return the y
         */
        public int getY() {
            return y;
        }

        /**
         * @param y the y to set
         */
        public void setY(int y) {
            this.y = y;
        }
    }
    private List<ImageIcon> listForCardsInHandTable;
    private CellSelected cellSelectedInCardsInHandTable;
    private List<ImageIcon> listForCardsInMyBankTable;
    private CellSelected cellSelectedInCardsInMyBankTable;
    private List<List<ImageIcon>> listForCardsInMySetsTable;
    private List<List<ImageIcon>> listForCardsInOpponentSetsTable;
    private CellSelected cellSelectedInCardsInMySetsTable;
    private CellSelected cellSelectedInCardsInOpponentSetsTable;
    ImageIcon blankIcon = null;
//    int rowsInCardsInMySetsTable = 8;
//    int columnsInCardsInMySetsTable = 12;
    int maxLengthOfCardsAssumed = 12;
    final String DIRECTION_LEFT = "left";
    final String DIRECTION_RIGHT = "right";
    final String SINGLE_SPACE = " ";
    private List<String> messagesForProgressTable = new ArrayList<>();
    String contentToDisplayInFlipViewSelectedCard = null;

    public MonopolyDealHomeView(GameDetail gameDetail, GameState gameState) {
        this();
        this.gameDetail = gameDetail;
        this.gameState = gameState;
        System.out.println("position: " + gameDetail.getMyPositionInGame());
        this.monopolyDealHomeViewController = new MonopolyDealHomeViewController(gameDetail, gameState);
        File blankFile = new File(".\\src\\images\\blank.PNG");
        ImageIcon blankFileIcon = new ImageIcon(blankFile.getPath());
        blankIcon = new ImageIcon(blankFileIcon.getImage().getScaledInstance(130, 50, Image.SCALE_SMOOTH));
        cardsInHandTable.setRowHeight(50);
        cardsInMyBankTable.setRowHeight(50);
        cardsInMySetsTable.setRowHeight(50);

        //<editor-fold defaultstate="collapsed" desc="comment">
        cellSelectedInCardsInHandTable = new CellSelected();
        cellSelectedInCardsInMyBankTable = new CellSelected();
        cellSelectedInCardsInMySetsTable = new CellSelected();
        cellSelectedInCardsInOpponentSetsTable = new CellSelected();
        initializeView();
    }

    /**
     * Creates new form MonopolyDealHomeView
     */
    public MonopolyDealHomeView() {
        initComponents();

    }

    private void resetCardInViewSelectedArea() {
        topSectionOfViewSelectedCardLabel.setBackground(Color.WHITE);
        bottomSectionOfViewSelectedCardLabel.setBackground(Color.WHITE);
        nameOfViewSelectedCardLabel.setText(SINGLE_SPACE);
        bodyOfViewSelectedCardLabel.setText(SINGLE_SPACE);
    }

    private void showSelectedCardInViewSelectedArea(ImageIcon imageIcon) {
        Map<String, String> detailsOfcard = monopolyDealHomeViewController.fetchDetailsOfSelectedCardForIcon(imageIcon);
        String topColor = detailsOfcard.get(CardConstants.TOP_COLOR);
        setTopPanelOfViewSelectedSection(topColor);
        String bottomColor = detailsOfcard.get(CardConstants.BOTTOM_COLOR);
        setBottomPanelOfViewSelectedSection(bottomColor);
        String nameOfCard = detailsOfcard.get("name");
        setNameOfViewSelectedSection(nameOfCard);
        String bodyOfCardForTopColor = detailsOfcard.get("body1");
        setBodyOfViewSelectedSection(bodyOfCardForTopColor);
        String bodyOfCardForBottomColor = detailsOfcard.get("body2");
        contentToDisplayInFlipViewSelectedCard = bodyOfCardForBottomColor;
    }

    private Color colorForString(String colorName) {
        if(colorName.equalsIgnoreCase("Black")){
            return Color.BLACK;
        } else if(colorName.equalsIgnoreCase("Blue")){
            return new Color(51, 51, 204);
        } else if(colorName.equalsIgnoreCase("Brown")){
            return new Color(102, 51, 51);
        } else if(colorName.equalsIgnoreCase("Green")){
            return new Color(51, 153, 51);
        } else if(colorName.equalsIgnoreCase("Grey")){
            return Color.GRAY;
        } else if(colorName.equalsIgnoreCase("Orange")){
            return new Color(255, 153, 51);
        } else if(colorName.equalsIgnoreCase("Pink")){
            return Color.PINK;
        } else if(colorName.equalsIgnoreCase("Red")){
            return Color.RED;
        } else if(colorName.equalsIgnoreCase("SkyBlue")){
            return new Color(0, 204, 255);
        } else if(colorName.equalsIgnoreCase("Yellow")){
            return Color.YELLOW;
        } else if(colorName.equalsIgnoreCase("white")){
            return Color.WHITE;
        }
        return null;
    }

    private void setTopPanelOfViewSelectedSection(String color) {
        topSectionOfViewSelectedCardLabel.setBackground(colorForString(color));
    }

    private void setBottomPanelOfViewSelectedSection(String color) {
        bottomSectionOfViewSelectedCardLabel.setBackground(colorForString(color));
    }

    private void setNameOfViewSelectedSection(String nameOfCard) {
        nameOfViewSelectedCardLabel.setText(nameOfCard);
    }

    private void setBodyOfViewSelectedSection(String bodyOfCard) {
        bodyOfViewSelectedCardLabel.setText(bodyOfCard);
    }

    private void updateMessagesAndOtherTextFields() {
        countOfCardsInMyHandLabel.setText(listForCardsInHandTable.size() + MonopolyDealConstants.EMPTY_STRING);
        countOfCardsInMySetsLabel.setText(fetchCountOfCardsInMySetsTable());
        countOfCardsInMyBankLabel.setText(listForCardsInMyBankTable.size() + MonopolyDealConstants.EMPTY_STRING);
        countOfCardsInDrawPileLabel.setText(fetchCountOfCardsInDrawPile());
        countOfCardsInPlayPileLabel.setText(fetchCountOfCardsInPlayPile());
        countOfCardsInOpponentBankLabel.setText(fetchCountOfCardsInOpponentBank());
        countOfCardsInOpponentHandLabel.setText(fetchCountOfCardsInOpponentHand());
        countOfCardsInOpponentSetsLabel.setText(fetchCountOfCardsInOpponentSets());
        //update game progress
        updateGameProgressTable();
    }

    private void updateGameProgressTable() {
        String currentMessage = monopolyDealHomeViewController.fetchCustomMessage();
        int messageSize = messagesForProgressTable.size();
        if (messageSize == 0) {
            //add first message
            messagesForProgressTable.add(currentMessage);
            //populate message table
            populateGameProgressTable();
        } else {
            //compare with last message and add if not present
            String lastMessage = messagesForProgressTable.get(messageSize - 1);
            if (!lastMessage.equals(currentMessage)) {
                messagesForProgressTable.add(currentMessage);
                //clear custom message table
                clearGameProgressTable();
                //populate custom message table
                populateGameProgressTable();
            }
        }
    }

    public void populateGameProgressTable() {
        DefaultTableModel defaultTableModel = (DefaultTableModel) gameProgressTable.getModel();
        Object[] objList = new Object[1];
        for (int i = messagesForProgressTable.size() - 1; i >= 0; i--) {
            objList[0] = messagesForProgressTable.get(i);
            defaultTableModel.addRow(objList);
        }
    }

    public void clearGameProgressTable() {
        DefaultTableModel defaultTableModel = (DefaultTableModel) gameProgressTable.getModel();
        if (messagesForProgressTable != null) {
            for (int i = messagesForProgressTable.size() - 1; i >= 0; i--) {
                defaultTableModel.removeRow(i);
            }
        }
    }

    private String fetchCountOfCardsInDrawPile() {
        return monopolyDealHomeViewController.countOfCardsInDrawPile() + MonopolyDealConstants.EMPTY_STRING;
    }

    private String fetchCountOfCardsInPlayPile() {
        return monopolyDealHomeViewController.countOfCardsInPlayPile() + MonopolyDealConstants.EMPTY_STRING;
    }

    private String fetchCountOfCardsInOpponentBank() {
        return monopolyDealHomeViewController.countOfCardsInOpponentBank() + MonopolyDealConstants.EMPTY_STRING;
    }

    private String fetchCountOfCardsInOpponentSets() {
        return monopolyDealHomeViewController.countOfCardsInOpponentSets() + MonopolyDealConstants.EMPTY_STRING;
    }

    private String fetchCountOfCardsInOpponentHand() {
        return monopolyDealHomeViewController.countOfCardsInOpponentHand() + MonopolyDealConstants.EMPTY_STRING;
    }

    private String fetchCountOfCardsInMySetsTable() {
        int count = 0;
        for (int i = 0; i < listForCardsInMySetsTable.size(); i++) {
            List<ImageIcon> row = listForCardsInMySetsTable.get(i);
            for (int j = 0; j < row.size(); j++) {
                ImageIcon imageIcon = row.get(j);
                if (!imageIcon.equals(blankIcon)) {
                    count++;
                }
            }
        }
        return count + "";
    }

    public void initializeView() {
        fillListForCardsInMySetsTable();
        fillListForCardsInHandTable();
        fillListForCardsInMyBankTable();
        fillListForCardsInOpponentSetTable();

        populateCardsInHandTable();
        populateCardsInMySetsTable();
        populateCardsInMyBankTable();
        populateCardsInOpponentSetsTable();

        captureMouseEventOnCardsInHandTable();
        captureMouseEventOnCardsInMyBankTable();
        captureMouseEventOnCardsInMySetsTable();
        updateMessagesAndOtherTextFields();
        resetCardInViewSelectedArea();
    }

    public void fillListForCardsInMyBankTable() {
        listForCardsInMyBankTable = monopolyDealHomeViewController.prepareContentOfMyBankTable();
    }

    public void fillListForCardsInOpponentSetTable() {
        listForCardsInOpponentSetsTable = monopolyDealHomeViewController.prepareContentOfOpponentSets(blankIcon);
    }

    public void fillListForCardsInMySetsTable() {
        listForCardsInMySetsTable = monopolyDealHomeViewController.prepareContentOfMySets(blankIcon);
    }

    public void fillListForCardsInHandTable() {
//        File blackPropertyfile = new File(".\\src\\images\\Black_Property_Card.png");
//        ImageIcon blackPropertyfileIcon = new ImageIcon(blackPropertyfile.getPath());
//
//        File whitefile = new File(".\\src\\images\\white.png");
//        ImageIcon whitefileIcon = new ImageIcon(whitefile.getPath());
//
//        for (int i = 0; i < 11; i++) {
//            if (i % 2 == 0) {
//                ImageIcon icon = new ImageIcon(blackPropertyfileIcon.getImage().getScaledInstance(90, 40, Image.SCALE_SMOOTH));
//                listForCardsInHandTable.add(icon);
//            } else {
//                ImageIcon icon = new ImageIcon(whitefileIcon.getImage().getScaledInstance(90, 50, Image.SCALE_SMOOTH));
//                listForCardsInHandTable.add(icon);
//            }
//        }
        listForCardsInHandTable = monopolyDealHomeViewController.prepareContentOfMyHand();
    }

    public void populateCardsInHandTable() {
        DefaultTableModel defaultTableModel = (DefaultTableModel) cardsInHandTable.getModel();
        Object[] objList = new Object[10];
        for (int i = 0; i < listForCardsInHandTable.size(); i++) {
            objList[0] = listForCardsInHandTable.get(i);
            defaultTableModel.addRow(objList);
        }
    }

    public void populateCardsInMySetsTable() {
        DefaultTableModel defaultTableModel = (DefaultTableModel) cardsInMySetsTable.getModel();
        Object[] objList = new Object[maxLengthOfCardsAssumed];
        for (int i = 0; i < listForCardsInMySetsTable.size(); i++) {
            List<ImageIcon> rowAtIndexI = listForCardsInMySetsTable.get(i);
            for (int j = 0; j < rowAtIndexI.size(); j++) {
                ImageIcon icon = rowAtIndexI.get(j);
                objList[j] = icon;
            }
            defaultTableModel.addRow(objList);
        }
    }

    public void populateCardsInOpponentSetsTable() {
        DefaultTableModel defaultTableModel = (DefaultTableModel) cardsInOpponentSetsTable.getModel();
        Object[] objList = new Object[maxLengthOfCardsAssumed];
        for (int i = 0; i < listForCardsInOpponentSetsTable.size(); i++) {
            List<ImageIcon> rowAtIndexI = listForCardsInOpponentSetsTable.get(i);
            for (int j = 0; j < rowAtIndexI.size(); j++) {
                ImageIcon icon = rowAtIndexI.get(j);
                objList[j] = icon;
            }
            defaultTableModel.addRow(objList);
        }
    }

    public void captureMouseEventOnCardsInHandTable() {
        cardsInHandTable.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int row = cardsInHandTable.rowAtPoint(evt.getPoint());
                int col = cardsInHandTable.columnAtPoint(evt.getPoint());
                if (row >= 0 && col >= 0) {
                    selectionChanged();
                    cellSelectedInCardsInHandTable.setX(row);
                    ImageIcon imageIcon = listForCardsInHandTable.get(row);
                    showSelectedCardInViewSelectedArea(imageIcon);
                }
            }
        });
    }

    public void captureMouseEventOnCardsInMyBankTable() {
        cardsInMyBankTable.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int row = cardsInMyBankTable.rowAtPoint(evt.getPoint());
                int col = cardsInMyBankTable.columnAtPoint(evt.getPoint());
                if (row >= 0 && col >= 0) {
                    selectionChanged();
                    cellSelectedInCardsInMyBankTable.setX(row);
                    ImageIcon imageIcon = listForCardsInMyBankTable.get(row);
                    showSelectedCardInViewSelectedArea(imageIcon);
                }
            }
        });
    }

    public void captureMouseEventOnCardsInMySetsTable() {
        cardsInMySetsTable.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int row = cardsInMySetsTable.rowAtPoint(evt.getPoint());
                int col = cardsInMySetsTable.columnAtPoint(evt.getPoint());
                if (row >= 0 && col >= 0) {
                    selectionChanged();
                    cellSelectedInCardsInMySetsTable.setX(row);
                    cellSelectedInCardsInMySetsTable.setY(col);

                    if (!listForCardsInMySetsTable.get(row).get(col).equals(blankIcon)) {
                        ImageIcon imageIcon = listForCardsInMySetsTable.get(row).get(col);
                        showSelectedCardInViewSelectedArea(imageIcon);
                    }

                    if (col == (listForCardsInMySetsTable.get(0).size() - 1)) {
                        moveRightButton.setEnabled(false);
                        moveLeftButton.setEnabled(true);
                    } else if (col == 0) {
                        moveRightButton.setEnabled(true);
                        moveLeftButton.setEnabled(false);
                    } else {
                        moveLeftButton.setEnabled(true);
                        moveRightButton.setEnabled(true);
                    }
                }
            }
        });
    }

    public void captureMouseEventOnCardsInOpponentSetsTable() {
        cardsInOpponentSetsTable.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int row = cardsInOpponentSetsTable.rowAtPoint(evt.getPoint());
                int col = cardsInOpponentSetsTable.columnAtPoint(evt.getPoint());
                if (row >= 0 && col >= 0) {
                    selectionChanged();
                    cellSelectedInCardsInOpponentSetsTable.setX(row);
                    cellSelectedInCardsInOpponentSetsTable.setY(col);
                    if (!listForCardsInOpponentSetsTable.get(row).get(col).equals(blankIcon)) {
                        ImageIcon imageIcon = listForCardsInOpponentSetsTable.get(row).get(col);
                        showSelectedCardInViewSelectedArea(imageIcon);
                    }
                }
            }
        });
    }

    public void selectionChanged() {
        cellSelectedInCardsInHandTable.setX(-1);
        cellSelectedInCardsInHandTable.setY(-1);
        cellSelectedInCardsInMyBankTable.setX(-1);
        cellSelectedInCardsInMyBankTable.setY(-1);
        cellSelectedInCardsInMySetsTable.setX(-1);
        cellSelectedInCardsInMySetsTable.setY(-1);
        moveLeftButton.setEnabled(false);
        moveRightButton.setEnabled(false);
        //reset card selected section
        resetCardInViewSelectedArea();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        countOfCardsInOpponentSetsLabel = new javax.swing.JLabel();
        countOfCardsInOpponentHandLabel = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        countOfCardsInOpponentBankLabel = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        Object[][] rows6 = null;
        String [] columns6 = {"","","","","","","",""};

        DefaultTableModel model6 = new DefaultTableModel(rows6,columns6){
            @Override
            public Class<?> getColumnClass(int column){
                return ImageIcon.class;
            }

            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        };
        cardsInOpponentSetsTable = new javax.swing.JTable(model6);
        jPanel3 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        countOfCardsInMySetsLabel = new javax.swing.JLabel();
        jScrollPane8 = new javax.swing.JScrollPane();
        Object[][] rows2 = null;
        String [] columns2 = {"","","","","","","",""};

        DefaultTableModel model2 = new DefaultTableModel(rows2,columns2){
            @Override
            public Class<?> getColumnClass(int column){
                return ImageIcon.class;
            }

            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        };
        cardsInMySetsTable = new javax.swing.JTable(model2);
        moveRightButton = new javax.swing.JButton();
        moveLeftButton = new javax.swing.JButton();
        okayButton = new javax.swing.JButton();
        passToOpponentFromMySetsButton = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jScrollPane11 = new javax.swing.JScrollPane();
        Object[][] rows5 = null;
        String [] columns5 = {""};

        DefaultTableModel model5 = new DefaultTableModel(rows5,columns5){
            @Override
            public Class<?> getColumnClass(int column){
                return ImageIcon.class;
            }
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return false;
            }
        };
        transferedToOpponent = new javax.swing.JTable(model5);
        jLabel16 = new javax.swing.JLabel();
        jScrollPane12 = new javax.swing.JScrollPane();
        Object[][] rows7 = null;
        String [] columns7 = {""};

        DefaultTableModel model7 = new DefaultTableModel(rows7,columns7){
            @Override
            public Class<?> getColumnClass(int column){
                return ImageIcon.class;
            }
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return false;
            }
        };
        transferedToOpponent1 = new javax.swing.JTable(model7);
        jPanel7 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        topSectionOfViewSelectedCardLabel = new javax.swing.JPanel();
        bottomSectionOfViewSelectedCardLabel = new javax.swing.JPanel();
        nameOfViewSelectedCardLabel = new javax.swing.JLabel();
        bodyOfViewSelectedCardLabel = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        comentsTextField = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        performActionTable = new javax.swing.JTable();
        performActionButton = new javax.swing.JButton();
        sendCommentButton = new javax.swing.JButton();
        jLabel29 = new javax.swing.JLabel();
        resetForPreviousStateButton = new javax.swing.JButton();
        flipSelectedCardButton = new javax.swing.JToggleButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        countOfCardsInMyHandLabel = new javax.swing.JLabel();
        moveToMyBankButton = new javax.swing.JButton();
        moveToMySetsButton = new javax.swing.JButton();
        moveToPlayPileButton = new javax.swing.JButton();
        discardToDrawPileButton = new javax.swing.JButton();
        jButton16 = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        Object[][] rows3 = null;
        String [] columns3 = {""};

        DefaultTableModel model3 = new DefaultTableModel(rows3,columns3){
            @Override
            public Class<?> getColumnClass(int column){
                return ImageIcon.class;
            }
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return false;
            }
        };
        cardsInHandTable = new javax.swing.JTable(model3);
        jPanel5 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        countOfCardsInMyBankLabel = new javax.swing.JLabel();
        passToOpponentBankButton = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        Object[][] rows = null;
        String [] columns = {""};

        DefaultTableModel model = new DefaultTableModel(rows,columns){
            @Override
            public Class<?> getColumnClass(int column){
                return ImageIcon.class;
            }
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return false;
            }
        };
        cardsInMyBankTable = new javax.swing.JTable(model);
        jPanel9 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        countOfCardsInPlayPileLabel = new javax.swing.JLabel();
        jScrollPane10 = new javax.swing.JScrollPane();
        Object[][] rows4 = null;
        String [] columns4 = {""};

        DefaultTableModel model4 = new DefaultTableModel(rows4,columns4){
            @Override
            public Class<?> getColumnClass(int column){
                return ImageIcon.class;
            }
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return false;
            }
        };
        playPileTable = new javax.swing.JTable(model4);
        jPanel8 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        gameProgressTable = new javax.swing.JTable();
        jPanel10 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        countOfCardsInDrawPileLabel = new javax.swing.JLabel();
        drawFiveCardsButton = new javax.swing.JButton();
        drawTwoCardsButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1836, 837));
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(204, 255, 255));

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel10.setText("Cards in opponent's Set");

        jLabel17.setText("Count of cards in opponent's set");

        countOfCardsInOpponentSetsLabel.setText("jLabel5");

        countOfCardsInOpponentHandLabel.setText("jLabel5");

        jLabel19.setText("Count of cards in opponent's hand");

        jLabel21.setText("Count of cards in opponent's bank");

        countOfCardsInOpponentBankLabel.setText("jLabel5");

        /*
        cardsInOpponentSetsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "", "", "", "", "", "", "", ""
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        */
        cardsInOpponentSetsTable.setColumnSelectionAllowed(true);
        cardsInOpponentSetsTable.getTableHeader().setReorderingAllowed(false);
        jScrollPane7.setViewportView(cardsInOpponentSetsTable);
        cardsInOpponentSetsTable.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        cardsInOpponentSetsTable.getColumnModel().getColumn(0).setResizable(false);
        cardsInOpponentSetsTable.getColumnModel().getColumn(1).setResizable(false);
        cardsInOpponentSetsTable.getColumnModel().getColumn(2).setResizable(false);
        cardsInOpponentSetsTable.getColumnModel().getColumn(3).setResizable(false);
        cardsInOpponentSetsTable.getColumnModel().getColumn(4).setResizable(false);
        cardsInOpponentSetsTable.getColumnModel().getColumn(5).setResizable(false);
        cardsInOpponentSetsTable.getColumnModel().getColumn(6).setResizable(false);
        cardsInOpponentSetsTable.getColumnModel().getColumn(7).setResizable(false);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane7)
                        .addContainerGap())
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(countOfCardsInOpponentSetsLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(countOfCardsInOpponentHandLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(105, 105, 105)
                        .addComponent(jLabel21)
                        .addGap(18, 18, 18)
                        .addComponent(countOfCardsInOpponentBankLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 249, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(countOfCardsInOpponentSetsLabel)
                    .addComponent(countOfCardsInOpponentBankLabel)
                    .addComponent(jLabel21)
                    .addComponent(countOfCardsInOpponentHandLabel)
                    .addComponent(jLabel19))
                .addContainerGap())
        );

        jPanel3.setBackground(new java.awt.Color(204, 204, 255));

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel11.setText("Cards in my Sets");

        jLabel23.setText("Count of cards in my sets");

        countOfCardsInMySetsLabel.setText("jLabel5");

        /*
        cardsInMySetsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "", "", "", "", "", "", "", ""
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        */
        cardsInMySetsTable.setColumnSelectionAllowed(true);
        cardsInMySetsTable.getTableHeader().setReorderingAllowed(false);
        jScrollPane8.setViewportView(cardsInMySetsTable);
        cardsInMySetsTable.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        cardsInMySetsTable.getColumnModel().getColumn(0).setResizable(false);
        cardsInMySetsTable.getColumnModel().getColumn(1).setResizable(false);
        cardsInMySetsTable.getColumnModel().getColumn(2).setResizable(false);
        cardsInMySetsTable.getColumnModel().getColumn(3).setResizable(false);
        cardsInMySetsTable.getColumnModel().getColumn(4).setResizable(false);
        cardsInMySetsTable.getColumnModel().getColumn(5).setResizable(false);
        cardsInMySetsTable.getColumnModel().getColumn(6).setResizable(false);
        cardsInMySetsTable.getColumnModel().getColumn(7).setResizable(false);

        moveRightButton.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        moveRightButton.setText("-->");
        moveRightButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                moveRightButtonActionPerformed(evt);
            }
        });

        moveLeftButton.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        moveLeftButton.setText("<--");
        moveLeftButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                moveLeftButtonActionPerformed(evt);
            }
        });

        okayButton.setText("OK");
        okayButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okayButtonActionPerformed(evt);
            }
        });

        passToOpponentFromMySetsButton.setText("Pass to opponent");
        passToOpponentFromMySetsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                passToOpponentFromMySetsButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane8, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(countOfCardsInMySetsLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(55, 55, 55)
                                .addComponent(moveLeftButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(okayButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(moveRightButton)
                                .addGap(18, 18, 18)
                                .addComponent(passToOpponentFromMySetsButton, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 253, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel23)
                            .addComponent(countOfCardsInMySetsLabel)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(okayButton)
                            .addComponent(moveRightButton)
                            .addComponent(moveLeftButton)
                            .addComponent(passToOpponentFromMySetsButton))))
                .addContainerGap())
        );

        jPanel6.setBackground(new java.awt.Color(255, 204, 204));

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel13.setText("Transfer to Opponent");

        /*
        transferedToOpponent.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                ""
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        */
        jScrollPane11.setViewportView(transferedToOpponent);
        transferedToOpponent.getColumnModel().getColumn(0).setResizable(false);

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel16.setText("Recieved from Opponent");

        /*
        transferedToOpponent1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                ""
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        */
        jScrollPane12.setViewportView(transferedToOpponent1);
        transferedToOpponent1.getColumnModel().getColumn(0).setResizable(false);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 372, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 372, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(37, Short.MAX_VALUE))
        );

        jPanel7.setBackground(new java.awt.Color(204, 255, 204));

        jPanel12.setBackground(new java.awt.Color(255, 255, 255));
        jPanel12.setBorder(javax.swing.BorderFactory.createBevelBorder(0, new java.awt.Color(0, 0, 0), new java.awt.Color(0, 0, 0), new java.awt.Color(0, 0, 0), new java.awt.Color(0, 0, 0)));

        topSectionOfViewSelectedCardLabel.setBackground(new java.awt.Color(255, 51, 204));

        javax.swing.GroupLayout topSectionOfViewSelectedCardLabelLayout = new javax.swing.GroupLayout(topSectionOfViewSelectedCardLabel);
        topSectionOfViewSelectedCardLabel.setLayout(topSectionOfViewSelectedCardLabelLayout);
        topSectionOfViewSelectedCardLabelLayout.setHorizontalGroup(
            topSectionOfViewSelectedCardLabelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        topSectionOfViewSelectedCardLabelLayout.setVerticalGroup(
            topSectionOfViewSelectedCardLabelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );

        bottomSectionOfViewSelectedCardLabel.setBackground(new java.awt.Color(255, 51, 204));

        javax.swing.GroupLayout bottomSectionOfViewSelectedCardLabelLayout = new javax.swing.GroupLayout(bottomSectionOfViewSelectedCardLabel);
        bottomSectionOfViewSelectedCardLabel.setLayout(bottomSectionOfViewSelectedCardLabelLayout);
        bottomSectionOfViewSelectedCardLabelLayout.setHorizontalGroup(
            bottomSectionOfViewSelectedCardLabelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        bottomSectionOfViewSelectedCardLabelLayout.setVerticalGroup(
            bottomSectionOfViewSelectedCardLabelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );

        nameOfViewSelectedCardLabel.setBackground(new java.awt.Color(255, 255, 255));
        nameOfViewSelectedCardLabel.setText(" ");

        bodyOfViewSelectedCardLabel.setBackground(new java.awt.Color(255, 255, 255));
        bodyOfViewSelectedCardLabel.setText(" ");

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(topSectionOfViewSelectedCardLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(bottomSectionOfViewSelectedCardLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(nameOfViewSelectedCardLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
                    .addComponent(bodyOfViewSelectedCardLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addComponent(topSectionOfViewSelectedCardLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(nameOfViewSelectedCardLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bodyOfViewSelectedCardLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 171, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(bottomSectionOfViewSelectedCardLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel8.setText("View Selected Card");

        comentsTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comentsTextFieldActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel12.setText("Perform Action");

        performActionTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                ""
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        performActionTable.setColumnSelectionAllowed(true);
        performActionTable.getTableHeader().setReorderingAllowed(false);
        jScrollPane6.setViewportView(performActionTable);
        performActionTable.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        performActionTable.getColumnModel().getColumn(0).setResizable(false);

        performActionButton.setText("Done");
        performActionButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                performActionButtonActionPerformed(evt);
            }
        });

        sendCommentButton.setText("Send");
        sendCommentButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sendCommentButtonActionPerformed(evt);
            }
        });

        jLabel29.setText("Comments");

        resetForPreviousStateButton.setText("Reset");
        resetForPreviousStateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetForPreviousStateButtonActionPerformed(evt);
            }
        });

        flipSelectedCardButton.setText("Flip selected card");
        flipSelectedCardButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                flipSelectedCardButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 179, Short.MAX_VALUE)
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(82, 82, 82))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(comentsTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(sendCommentButton)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel7Layout.createSequentialGroup()
                                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(63, 63, 63)
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                    .addComponent(performActionButton, javax.swing.GroupLayout.DEFAULT_SIZE, 123, Short.MAX_VALUE)))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGap(33, 33, 33)
                                .addComponent(flipSelectedCardButton, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(resetForPreviousStateButton, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(performActionButton)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(resetForPreviousStateButton)
                    .addComponent(flipSelectedCardButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comentsTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sendCommentButton)
                    .addComponent(jLabel29))
                .addGap(18, 18, 18))
        );

        jPanel4.setBackground(new java.awt.Color(0, 204, 204));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel9.setText("Cards in my Hand");

        jLabel15.setText("Count of cards in my hand");

        countOfCardsInMyHandLabel.setText("jLabel5");

        moveToMyBankButton.setText("Move to bank");
        moveToMyBankButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                moveToMyBankButtonActionPerformed(evt);
            }
        });

        moveToMySetsButton.setText("Move to my sets");
        moveToMySetsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                moveToMySetsButtonActionPerformed(evt);
            }
        });

        moveToPlayPileButton.setText("Move to play pile");
        moveToPlayPileButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                moveToPlayPileButtonActionPerformed(evt);
            }
        });

        discardToDrawPileButton.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        discardToDrawPileButton.setText("Discard to draw pile");
        discardToDrawPileButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                discardToDrawPileButtonActionPerformed(evt);
            }
        });

        jButton16.setText("Extra");
        jButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton16ActionPerformed(evt);
            }
        });

        /*
        cardsInHandTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                ""
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        */
        cardsInHandTable.getTableHeader().setReorderingAllowed(false);
        jScrollPane4.setViewportView(cardsInHandTable);
        cardsInHandTable.getColumnModel().getColumn(0).setResizable(false);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(countOfCardsInMyHandLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(moveToMyBankButton, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(discardToDrawPileButton, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton16, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(moveToMySetsButton, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(moveToPlayPileButton, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(78, 78, 78))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(98, 98, 98)
                        .addComponent(moveToMySetsButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(moveToMyBankButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(moveToPlayPileButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(discardToDrawPileButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton16))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(countOfCardsInMyHandLabel))
                .addGap(18, 18, 18))
        );

        jPanel5.setBackground(new java.awt.Color(255, 102, 51));

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel14.setText("Cards in my Bank");

        jLabel25.setText("Count of cards in my bank");

        countOfCardsInMyBankLabel.setText("jLabel5");

        passToOpponentBankButton.setText("<html>pass to<br/>opponent</html>");
        passToOpponentBankButton.setToolTipText("");
        passToOpponentBankButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                passToOpponentBankButtonActionPerformed(evt);
            }
        });

        /*
        cardsInMyBankTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                ""
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        */
        cardsInMyBankTable.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(cardsInMyBankTable);
        cardsInMyBankTable.getColumnModel().getColumn(0).setResizable(false);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(countOfCardsInMyBankLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(102, 102, 102)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(47, 47, 47)
                .addComponent(passToOpponentBankButton, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(42, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel14)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 161, Short.MAX_VALUE)
                        .addComponent(passToOpponentBankButton, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(153, 153, 153))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel25)
                    .addComponent(countOfCardsInMyBankLabel))
                .addContainerGap())
        );

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setText("Play Pile");

        jLabel6.setText("Count of cards in play pile");

        countOfCardsInPlayPileLabel.setText("jLabel5");

        /*
        playPileTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                ""
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        */
        jScrollPane10.setViewportView(playPileTable);
        playPileTable.getColumnModel().getColumn(0).setResizable(false);

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50))
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(countOfCardsInPlayPileLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(92, 92, 92)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(countOfCardsInPlayPileLabel))
                .addContainerGap())
        );

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setText("Game progress");

        gameProgressTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                ""
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        gameProgressTable.getTableHeader().setReorderingAllowed(false);
        jScrollPane3.setViewportView(gameProgressTable);
        gameProgressTable.getColumnModel().getColumn(0).setResizable(false);

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(200, 200, 200)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 466, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setText("Draw Pile");

        jLabel4.setText("Count of cards in draw pile");

        countOfCardsInDrawPileLabel.setText("jLabel5");

        drawFiveCardsButton.setText("<html>Draw <br/>5 cards</html>");
        drawFiveCardsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                drawFiveCardsButtonActionPerformed(evt);
            }
        });

        drawTwoCardsButton.setText("<html>Draw <br/>2 cards</html>");
        drawTwoCardsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                drawTwoCardsButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(93, 93, 93))
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(countOfCardsInDrawPileLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(drawFiveCardsButton, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(drawTwoCardsButton, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(drawFiveCardsButton, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(drawTwoCardsButton, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(47, 47, 47)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(countOfCardsInDrawPileLabel))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void drawTwoCardsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_drawTwoCardsButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_drawTwoCardsButtonActionPerformed

    private void comentsTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comentsTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comentsTextFieldActionPerformed

    private void drawFiveCardsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_drawFiveCardsButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_drawFiveCardsButtonActionPerformed

    private void moveLeftButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_moveLeftButtonActionPerformed
        // TODO add your handling code here:
        if (listForCardsInMySetsTable.get(cellSelectedInCardsInMySetsTable.getX()).get(cellSelectedInCardsInMySetsTable.getY()).equals(blankIcon)) {
            return;
        }
        if (listForCardsInMySetsTable != null && cellSelectedInCardsInMySetsTable.getY() >= 0 && cellSelectedInCardsInMySetsTable.getY() < listForCardsInMySetsTable.get(0).size()) {
            clearCardsInMySetsTable();
            modifyTheListOfCardsInMySetsTable(DIRECTION_LEFT);
            populateCardsInMySetsTable();
        }
    }//GEN-LAST:event_moveLeftButtonActionPerformed
    public void modifyTheListOfCardsInMySetsTable(String direction) {
        if (listForCardsInMySetsTable != null && cellSelectedInCardsInMySetsTable.getX() > -1 && cellSelectedInCardsInMySetsTable.getY() > -1) {
            int numberOfRows = listForCardsInMySetsTable.size();
            int sizeOfEachRow = listForCardsInMySetsTable.get(0).size();
            if (direction.equals(DIRECTION_LEFT)) {
                if (cellSelectedInCardsInMySetsTable.getY() > 0 && cellSelectedInCardsInMySetsTable.getY() < sizeOfEachRow) {
                    System.out.println("inside left current index row: " + cellSelectedInCardsInMySetsTable.getX() + ", " + cellSelectedInCardsInMySetsTable.getY());
                    for (int i = 0; i < numberOfRows; i++) {
                        if (listForCardsInMySetsTable.get(i).get(cellSelectedInCardsInMySetsTable.getY() - 1).equals(blankIcon)) {

                            ImageIcon imageIcon = listForCardsInMySetsTable.get(cellSelectedInCardsInMySetsTable.getX()).get(cellSelectedInCardsInMySetsTable.getY());
                            listForCardsInMySetsTable.get(i).remove(cellSelectedInCardsInMySetsTable.getY() - 1);
                            listForCardsInMySetsTable.get(i).add(cellSelectedInCardsInMySetsTable.getY() - 1, imageIcon);
                            listForCardsInMySetsTable.get(cellSelectedInCardsInMySetsTable.getX()).remove(cellSelectedInCardsInMySetsTable.getY());
                            if (cellSelectedInCardsInMySetsTable.getY() == sizeOfEachRow - 1) {
                                listForCardsInMySetsTable.get(cellSelectedInCardsInMySetsTable.getX()).add(blankIcon);
                            } else {
                                listForCardsInMySetsTable.get(cellSelectedInCardsInMySetsTable.getX()).add(cellSelectedInCardsInMySetsTable.getY(), blankIcon);

                            }
                            selectionChanged();
                            break;
                        }
                    }
                }
            } else if (direction.equals(DIRECTION_RIGHT)) {
                if (cellSelectedInCardsInMySetsTable.getY() >= 0 && cellSelectedInCardsInMySetsTable.getY() <= sizeOfEachRow - 2) {
                    for (int i = 0; i < numberOfRows; i++) {
                        if (listForCardsInMySetsTable.get(i).get(cellSelectedInCardsInMySetsTable.getY() + 1).equals(blankIcon)) {

                            ImageIcon imageIcon = listForCardsInMySetsTable.get(cellSelectedInCardsInMySetsTable.getX()).get(cellSelectedInCardsInMySetsTable.getY());
                            listForCardsInMySetsTable.get(i).remove(cellSelectedInCardsInMySetsTable.getY() + 1);
                            if ((cellSelectedInCardsInMySetsTable.getY() + 1) == sizeOfEachRow) {
                                listForCardsInMySetsTable.get(i).add(imageIcon);
                            } else {
                                listForCardsInMySetsTable.get(i).add(cellSelectedInCardsInMySetsTable.getY() + 1, imageIcon);
                            }
                            listForCardsInMySetsTable.get(cellSelectedInCardsInMySetsTable.getX()).remove(cellSelectedInCardsInMySetsTable.getY());
                            listForCardsInMySetsTable.get(cellSelectedInCardsInMySetsTable.getX()).add(cellSelectedInCardsInMySetsTable.getY(), blankIcon);
                            selectionChanged();
                            break;
                        }
                    }
                }
            }
            reAdjustCardsInMySetsTable();
        }
    }

    public void reAdjustCardsInMySetsTable() {
        for (int i = 1; i < listForCardsInMySetsTable.size(); i++) {
            List<ImageIcon> row = listForCardsInMySetsTable.get(i);
            for (int j = 0; j < row.size(); j++) {
                if (listForCardsInMySetsTable.get(i - 1).get(j).equals(blankIcon) && !listForCardsInMySetsTable.get(i).get(j).equals(blankIcon)) {
                    ImageIcon imageIcon = listForCardsInMySetsTable.get(i).get(j);
                    listForCardsInMySetsTable.get(i - 1).remove(j);
                    listForCardsInMySetsTable.get(i - 1).add(j, imageIcon);
                    listForCardsInMySetsTable.get(i).remove(j);
                    listForCardsInMySetsTable.get(i).add(j, blankIcon);
                }
            }
        }
    }

    private void okayButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okayButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_okayButtonActionPerformed

    private void moveRightButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_moveRightButtonActionPerformed
        // TODO add your handling code here:
        if (listForCardsInMySetsTable.get(cellSelectedInCardsInMySetsTable.getX()).get(cellSelectedInCardsInMySetsTable.getY()).equals(blankIcon)) {
            return;
        }
        if (listForCardsInMySetsTable != null && cellSelectedInCardsInMySetsTable.getY() >= 0 && cellSelectedInCardsInMySetsTable.getY() < listForCardsInMySetsTable.get(0).size()) {
            clearCardsInMySetsTable();
            modifyTheListOfCardsInMySetsTable(DIRECTION_RIGHT);
            populateCardsInMySetsTable();
        }
    }//GEN-LAST:event_moveRightButtonActionPerformed

    private void performActionButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_performActionButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_performActionButtonActionPerformed

    private void passToOpponentBankButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_passToOpponentBankButtonActionPerformed
        // TODO add your handling code here: -- Saharsh : incomplete code
        if (listForCardsInMyBankTable != null && listForCardsInMyBankTable.size() > 0 && cellSelectedInCardsInMyBankTable.getX() > -1) {
            clearCardsInMyBankTable();
            listForCardsInMyBankTable.remove(cellSelectedInCardsInMyBankTable.getX());
            cellSelectedInCardsInMyBankTable.setX(-1);
            populateCardsInMyBankTable();
        }
    }//GEN-LAST:event_passToOpponentBankButtonActionPerformed

    private void passToOpponentFromMySetsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_passToOpponentFromMySetsButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_passToOpponentFromMySetsButtonActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton10ActionPerformed

    private void moveToMySetsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_moveToMySetsButtonActionPerformed
        // TODO add your handling code here:
        if (listForCardsInHandTable != null && listForCardsInHandTable.size() > 0 && cellSelectedInCardsInHandTable.getX() > -1) {
            clearCardsInHandTable();
            clearCardsInMySetsTable();
            ImageIcon imageIconContent = listForCardsInHandTable.get(cellSelectedInCardsInHandTable.getX());
            addANewContentToTheListOfCardsInMySetsTable(imageIconContent);
            listForCardsInHandTable.remove(cellSelectedInCardsInHandTable.getX());
            cellSelectedInCardsInHandTable.setX(-1);
            populateCardsInHandTable();
            populateCardsInMySetsTable();
        }
        updateMessagesAndOtherTextFields();
    }//GEN-LAST:event_moveToMySetsButtonActionPerformed
    public void clearCardsInMySetsTable() {
        DefaultTableModel defaultTableModel = (DefaultTableModel) cardsInMySetsTable.getModel();
        if (listForCardsInMySetsTable != null) {
            for (int i = listForCardsInMySetsTable.size() - 1; i >= 0; i--) {
                defaultTableModel.removeRow(i);
            }
        }
    }

    public void addANewContentToTheListOfCardsInMySetsTable(ImageIcon imageIconContent) {
        if (listForCardsInMySetsTable != null) {
            int numberOfRows = listForCardsInMySetsTable.size();
            int sizeOfEachRow = listForCardsInMySetsTable.get(0).size();
            for (int i = 0; i < numberOfRows; i++) {
                if (listForCardsInMySetsTable.get(i).get(sizeOfEachRow - 1).equals(blankIcon)) {
                    listForCardsInMySetsTable.get(i).remove(sizeOfEachRow - 1);
                    listForCardsInMySetsTable.get(i).add(sizeOfEachRow - 1, imageIconContent);
                    break;
                }
            }
        }
    }
    private void moveToPlayPileButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_moveToPlayPileButtonActionPerformed
        // TODO add your handling code here: -- Saharsh : incomplete code
        if (listForCardsInHandTable != null && listForCardsInHandTable.size() > 0 && cellSelectedInCardsInHandTable.getX() > -1) {
            clearCardsInHandTable();
            listForCardsInHandTable.remove(cellSelectedInCardsInHandTable.getX());
            cellSelectedInCardsInHandTable.setX(-1);
            populateCardsInHandTable();
        }
        updateMessagesAndOtherTextFields();//refresh screen
    }//GEN-LAST:event_moveToPlayPileButtonActionPerformed

    private void jButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton16ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton16ActionPerformed

    private void sendCommentButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sendCommentButtonActionPerformed
        // TODO add your handling code here: -- Saharsh : need to write this code
    }//GEN-LAST:event_sendCommentButtonActionPerformed

    private void moveToMyBankButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_moveToMyBankButtonActionPerformed
        // TODO add your handling code here:
        if (listForCardsInHandTable != null && listForCardsInHandTable.size() > 0 && cellSelectedInCardsInHandTable.getX() > -1) {

            ImageIcon imageIcon = listForCardsInHandTable.get(cellSelectedInCardsInHandTable.getX());
            clearCardsInHandTable();
            clearCardsInMyBankTable();
            listForCardsInMyBankTable.add(imageIcon);
            listForCardsInHandTable.remove(cellSelectedInCardsInHandTable.getX());
            cellSelectedInCardsInHandTable.setX(-1);
            populateCardsInHandTable();
            populateCardsInMyBankTable();
        }
        updateMessagesAndOtherTextFields();
    }//GEN-LAST:event_moveToMyBankButtonActionPerformed

    private void resetForPreviousStateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetForPreviousStateButtonActionPerformed
        // TODO add your handling code here:
        selectionChanged();
        clearCardsInHandTable();
        clearCardsInMyBankTable();
        clearCardsInMySetsTable();
        initializeView();
    }//GEN-LAST:event_resetForPreviousStateButtonActionPerformed

    private void discardToDrawPileButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_discardToDrawPileButtonActionPerformed
        // TODO add your handling code here:
        updateMessagesAndOtherTextFields();//refresh screen
    }//GEN-LAST:event_discardToDrawPileButtonActionPerformed

    private void flipSelectedCardButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_flipSelectedCardButtonActionPerformed
        // TODO add your handling code here:
        Color topColor = topSectionOfViewSelectedCardLabel.getBackground();
        Color bottomColor = bottomSectionOfViewSelectedCardLabel.getBackground();
        topSectionOfViewSelectedCardLabel.setBackground(bottomColor);
        bottomSectionOfViewSelectedCardLabel.setBackground(topColor);
        String textPresentAtBody = bodyOfViewSelectedCardLabel.getText();
        bodyOfViewSelectedCardLabel.setText(contentToDisplayInFlipViewSelectedCard);
        contentToDisplayInFlipViewSelectedCard=textPresentAtBody;
    }//GEN-LAST:event_flipSelectedCardButtonActionPerformed
    public void clearCardsInHandTable() {
        DefaultTableModel defaultTableModel = (DefaultTableModel) cardsInHandTable.getModel();
        if (listForCardsInHandTable != null && listForCardsInHandTable.size() > 0) {
            for (int i = listForCardsInHandTable.size() - 1; i >= 0; i--) {
                defaultTableModel.removeRow(i);
            }
        }
    }

    public void clearCardsInMyBankTable() {
        DefaultTableModel defaultTableModel = (DefaultTableModel) cardsInMyBankTable.getModel();
        if (listForCardsInMyBankTable != null && listForCardsInMyBankTable.size() > 0) {
            for (int i = listForCardsInMyBankTable.size() - 1; i >= 0; i--) {
                defaultTableModel.removeRow(i);
            }
        }
    }

    public void populateCardsInMyBankTable() {
        DefaultTableModel defaultTableModel = (DefaultTableModel) cardsInMyBankTable.getModel();
        Object[] objList = new Object[10];
        for (int i = 0; i < listForCardsInMyBankTable.size(); i++) {
            ImageIcon icon = listForCardsInMyBankTable.get(i);
            objList[0] = icon;
            defaultTableModel.addRow(objList);
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MonopolyDealHomeView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                Game game = new Game();
                GameState gameState = new GameState(game);
                GameDetail gameDetail = new GameDetail(game, 0);

                for (int i = 0; i < CardConstants.NAMES_OF_CARDS.length; i++) {
                    PlayingCardForTransition playingCardForTransition = new PlayingCardForTransition();
                    playingCardForTransition.setNameOfCard(CardConstants.NAMES_OF_CARDS[i]);
                    gameState.getCardsInPlayerOneHand().add(playingCardForTransition);
                }
                new MonopolyDealHomeView(gameDetail, gameState).setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel bodyOfViewSelectedCardLabel;
    private javax.swing.JPanel bottomSectionOfViewSelectedCardLabel;
    private javax.swing.JTable cardsInHandTable;
    private javax.swing.JTable cardsInMyBankTable;
    private javax.swing.JTable cardsInMySetsTable;
    private javax.swing.JTable cardsInOpponentSetsTable;
    private javax.swing.JTextField comentsTextField;
    private javax.swing.JLabel countOfCardsInDrawPileLabel;
    private javax.swing.JLabel countOfCardsInMyBankLabel;
    private javax.swing.JLabel countOfCardsInMyHandLabel;
    private javax.swing.JLabel countOfCardsInMySetsLabel;
    private javax.swing.JLabel countOfCardsInOpponentBankLabel;
    private javax.swing.JLabel countOfCardsInOpponentHandLabel;
    private javax.swing.JLabel countOfCardsInOpponentSetsLabel;
    private javax.swing.JLabel countOfCardsInPlayPileLabel;
    private javax.swing.JButton discardToDrawPileButton;
    private javax.swing.JButton drawFiveCardsButton;
    private javax.swing.JButton drawTwoCardsButton;
    private javax.swing.JToggleButton flipSelectedCardButton;
    private javax.swing.JTable gameProgressTable;
    private javax.swing.JButton jButton16;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JButton moveLeftButton;
    private javax.swing.JButton moveRightButton;
    private javax.swing.JButton moveToMyBankButton;
    private javax.swing.JButton moveToMySetsButton;
    private javax.swing.JButton moveToPlayPileButton;
    private javax.swing.JLabel nameOfViewSelectedCardLabel;
    private javax.swing.JButton okayButton;
    private javax.swing.JButton passToOpponentBankButton;
    private javax.swing.JButton passToOpponentFromMySetsButton;
    private javax.swing.JButton performActionButton;
    private javax.swing.JTable performActionTable;
    private javax.swing.JTable playPileTable;
    private javax.swing.JButton resetForPreviousStateButton;
    private javax.swing.JButton sendCommentButton;
    private javax.swing.JPanel topSectionOfViewSelectedCardLabel;
    private javax.swing.JTable transferedToOpponent;
    private javax.swing.JTable transferedToOpponent1;
    // End of variables declaration//GEN-END:variables
}
