package Controllers;

import GUI.InfoScreen;
import Models.Auctions.AbsoluteAuction;
import Models.Auctions.Auction;
import Models.Auctions.SealedBidAuction;
import Models.Cars.Car;
import Models.Databases.DatabaseOfAuctions;
import Models.Databases.DatabaseOfUsers;
import Models.MainScreenInterface;
import Models.Users.Customer;
import Models.Users.PremiumUser;
import Project.sample.Main;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

/**
 * The <code>MainScreenController</code> class acts as a controller for the MainScreen.fxml file
 * It is responsible for offering {@link Models.Users.StandardUser} and {@link PremiumUser} methods designed for them
 * It allows them to display auctions, wishList a particular auction, open garage and wallet assigned to them and place a bid to specific auction
 */
public class MainScreenController extends MainScreen implements MainScreenInterface {

    public static MainScreenController mainScreenControllerInstance;

    @FXML
    private Label userIDLabel;
    @FXML
    private Label userNameLabel;
    @FXML
    public Label userCurrencyLabel;
    @FXML
    public Label timeLabel;
    @FXML
    public TextArea textArea;
    @FXML
    private TextField auctionIdField;
    @FXML
    private TextField auctionAmountField;
    @FXML
    private Button placeBidButton;
    @FXML
    private Button addToWishListButton;

    public MainScreenController() {
        mainScreenControllerInstance = this;
        final InfoScreen[] popUp = new InfoScreen[1];

//        Platform.runLater(this::displayData);
        Platform.runLater(() -> {
            displayData();
            createEventHandlers();
        });

//        Platform.runLater(() -> {//Creating the mouse event handler
//            EventHandler<MouseEvent> eventHandler = new EventHandler<MouseEvent>() {
//                @Override
//                public void handle(MouseEvent e) {
//                        if(e.isSecondaryButtonDown()){
//                            popUp[0] = new InfoScreen("Place Bid", "Choose auction by ID and choose amount to bid");
//                        }
//
//
//                }
//            };
//            EventHandler<MouseEvent> escape = new EventHandler<MouseEvent>() {
//                @Override
//                public void handle(MouseEvent e) {
//                    popUp[0].close();
//                }
//            };
//            //Registering the event filter
//            placeBidButton.addEventFilter(MouseEvent.MOUSE_PRESSED, eventHandler);
//            placeBidButton.addEventFilter(MouseEvent.MOUSE_RELEASED, escape);
//
////            placeBidButton.setOnMouseClicked(event -> {
////                if(event.getButton() == MouseButton.SECONDARY){
////                    popUp[0] = new AboutScreen();
////                }
////            });
//
//        });

    }

//    private static MainScreenController single_instance = null;
//    public static MainScreenController getInstance(){     // Singleton
//        if (single_instance == null)
//            single_instance = new MainScreenController();
//
//        return single_instance;
//    }

    /**
     * Creates event handlers for selected buttons
     */
    private void createEventHandlers() {
        final InfoScreen[] popUp = new InfoScreen[2];
        popUp[0] = new InfoScreen("Place Bid", "Choose auction by ID and choose your amount to bid");
        popUp[1] = new InfoScreen("Add to WishList", "Choose auction by ID and add it to your wishList");

        EventHandler<MouseEvent> placedBidsEventHandler = e -> {
            if (e.isSecondaryButtonDown())
                popUp[0].show();
        };
        EventHandler<MouseEvent> placedBidsEscape = e -> {
            if (popUp[0].isShowing())
                popUp[0].close();
        };


        EventHandler<MouseEvent> wishListEventHandler = e -> {
            if (e.isSecondaryButtonDown())
                popUp[1].show();
        };
        EventHandler<MouseEvent> wishListEscape = e -> {
            if (popUp[1].isShowing())
                popUp[1].close();
        };

        //Registering the event filter
        placeBidButton.addEventFilter(MouseEvent.MOUSE_PRESSED, placedBidsEventHandler);
        placeBidButton.addEventFilter(MouseEvent.MOUSE_RELEASED, placedBidsEscape);
        addToWishListButton.addEventFilter(MouseEvent.MOUSE_PRESSED, wishListEventHandler);
        addToWishListButton.addEventFilter(MouseEvent.MOUSE_RELEASED, wishListEscape);
    }

    /**
     * Displays data about current user
     */
    public void displayData() {
        Customer currentCustomer = (Customer) DatabaseOfUsers.currentUser;
        userCurrencyLabel.setText(Double.toString(currentCustomer.getWallet().getBids()));
        userNameLabel.setText(DatabaseOfUsers.currentUser.getUserName());
        userIDLabel.setText(Long.toString(DatabaseOfUsers.currentUser.getUserId()));
    }

    /**
     * Saves user and auction data and logs out the current user
     *
     * @throws IOException
     */
    public void logOut() throws IOException {
//        // Saving all the data
//        Serializator serializator = new Serializator();
//        serializator.saveData(DatabaseOfUsers.registeredUsers, DatabaseOfUsers.userData);
//        serializator.saveData(DatabaseOfAuctions.auctions, DatabaseOfAuctions.auctionData);
//        serializator.saveData(DatabaseOfWallets.wallets, DatabaseOfWallets.walletData);
//        serializator.saveData(DatabaseOfGarages.garages, DatabaseOfGarages.garageData);
//        // Setting current user to null
//        DatabaseOfUsers.currentUser = null;
//        // Clearing the user and auction database
//        DatabaseOfUsers.registeredUsers.clear();
//        DatabaseOfAuctions.auctions.clear();
//        DatabaseOfWallets.wallets.clear();
//        DatabaseOfGarages.garages.clear();
//
//        // Loading first screen
//        Main main = new Main();
//        main.changeScene("/GUI/sample.fxml");

        MainScreenInterface.super.logOut(DatabaseOfUsers.currentUser);
    }

    /**
     * Displays all available auctions
     * If the current user is instance of {@link Models.Users.StandardUser}, then he can not see premium auctions
     * Different data is displayed, depending on the type of the auction
     */
    public void showAuctions() {                    // Showing auctions for different types of users
        textArea.setText("");
        boolean premiumUser = DatabaseOfUsers.currentUser instanceof PremiumUser;
        for (Auction a : DatabaseOfAuctions.auctions) {
            if (a.isPremium && !premiumUser)
                continue;
            if (a.auctionType instanceof AbsoluteAuction) {
                textArea.appendText("Absolute Auction:\n");
                textArea.appendText("Last bid: " + a.highestBid + "\n");
            } else if (a.auctionType instanceof SealedBidAuction)
                textArea.appendText("Sealed Bid Auction:\n");
            textArea.appendText("ID: " + a.getAuctionId() + "\t Brand: " + a.car.getBrand() + "\t Model: " + a.car.getModel() + "\n");
            textArea.appendText("Price: " + a.car.getPrice() + "\t Year: " + a.car.getYear() + "\t Bids: " + a.getNumberOfBids() + "/" + a.getMaxBids() + "\n");
            textArea.appendText("\n");
        }

    }

    /**
     * Checks whether the auction ID is available adn if the placed amount is above 0
     * Then calls placeBid() method situated in {@link Customer} class.
     */
    public void placeBidToAuction() {
        String message = "";

        try {
            boolean isPresent = false;                              // Checking if ID is available
            for (Auction auction : DatabaseOfAuctions.auctions) {
                if (Integer.parseInt(auctionIdField.getText()) == auction.getAuctionId()) {
                    isPresent = true;
                }
            }
            if (!isPresent) {
                textArea.appendText("Auction with given ID does not exist\n");
                return;
            }

            if (Double.parseDouble(auctionAmountField.getText()) <= 0) {  // Checking if placed amount is above 0
                textArea.appendText("You must bid more than 0\n");
                return;
            }

            Customer currentUser = (Customer) DatabaseOfUsers.currentUser;
            message = currentUser.placeBid(Integer.parseInt(auctionIdField.getText()), Double.parseDouble(auctionAmountField.getText())); // placing bid
        } catch (NumberFormatException e) {
            textArea.appendText("Invalid input\n");
        } catch (ClassCastException f) {
            textArea.appendText("Cast not working\n");
        }

//        textArea.setText("");
        showAuctions();
        textArea.appendText(message + "\n");
    }

    /**
     * Displays cars owned by current user
     *
     * @see Models.PersonalGarage
     */
    public void showCars() {
        textArea.setText("");
        Customer currentUser = (Customer) DatabaseOfUsers.currentUser;
        if (currentUser.getGarage().getCars().isEmpty())
            textArea.appendText("You do not own any cars at the moment\n");
        for (Car car : currentUser.getGarage().getCars()) {
            textArea.appendText(car.getBrand() + " " + car.getModel() + "\n");
        }
    }

    public void addToWishList() {
        // este poriesit duplikaty
        Customer currentCustomer = (Customer) DatabaseOfUsers.currentUser;
        auctionIdField.setOpacity(1);

        try {
            Long.parseLong(auctionIdField.getText());
        } catch (NumberFormatException e) {
            clear();
            textArea.appendText("Wrong ID format\n");
            auctionIdField.setOpacity(0.5);
            return;
        }

        for (Object object : currentCustomer.getWishList().wishList) {
            if (object instanceof Auction) {
                Auction auction = (Auction) object;

                if (Long.parseLong(auctionIdField.getText()) == auction.getAuctionId()) {
                    clear();
                    textArea.appendText("This auction is already in wishlist\n");
//                System.out.println("duplikat");
                    return;                                                                               // duplikat
                }

            }
        }
        for (Auction a : DatabaseOfAuctions.auctions) {
            if (Long.parseLong(auctionIdField.getText()) == a.getAuctionId()) {
                a.setNumberOfWishLists(a.getNumberOfWishLists() + 1);
                currentCustomer.getWishList().wishList.add(a);
                textArea.appendText("Auction added to wishlist\n");
                return;
//                System.out.println("Auction added");
//                for(Auction au : currentUser.getWishList().wishListAuctions){             // len vypis
//                    textArea.appendText(au.car.getBrand() + au.car.getModel());
//                }
            }
        }
        textArea.appendText("Auction with given ID does not exist");
    }

    public void showWishList() {
        Customer currentCustomer = (Customer) DatabaseOfUsers.currentUser;
        clear();
        for (Object object : currentCustomer.getWishList().wishList) {
            if (object instanceof Auction) {
                Auction auction = (Auction) object;

                textArea.appendText(auction.car.getBrand() + " " + auction.car.getModel() + "\n");
//            System.out.println(auction.car.getBrand() + auction.car.getModel() + "\n");
            }
        }

    }

    public void clear() {
        textArea.setText("");
    }

    public void openWallet() throws IOException {
        Main main = new Main();
        main.openNewWindow("/GUI/Wallet.fxml");
    }

}
