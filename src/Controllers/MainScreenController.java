package Controllers;

import GUI.InfoScreen;
import Models.Auctions.AbsoluteAuction;
import Models.Auctions.Auction;
import Models.Auctions.SealedBidAuction;
import Models.Databases.DatabaseOfAuctions;
import Models.Databases.DatabaseOfUsers;
import Models.MainScreenInterface;
import Models.Users.Customer;
import Models.Users.PremiumUser;
import Project.Main.Main;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

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

        Platform.runLater(() -> {
            displayData();
            createEventHandlers();
        });

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
        popUp[0] = new InfoScreen("Place Bid", "Choose auction by ID and your amount to bid");
        popUp[1] = new InfoScreen("Add to WishList", "Choose auction by ID and add it to your wishList");

        EventHandler<MouseEvent> placedBidsEventHandler = e -> {
            if (e.isSecondaryButtonDown()) {
                placeBidButton.textFillProperty().setValue(Color.ROYALBLUE);
                popUp[0].show();
                popUp[0].setX(placeBidButton.getLayoutX() + 550);
                popUp[0].setY(placeBidButton.getLayoutY() + 200);
            }

        };
        EventHandler<MouseEvent> placedBidsEscape = e -> {
            if (popUp[0].isShowing()) {
                placeBidButton.textFillProperty().setValue(Color.BLACK);
                popUp[0].close();
            }
        };

        EventHandler<MouseEvent> placedBidsColorChange = e -> {
            placeBidButton.textFillProperty().setValue(Color.ROYALBLUE);
        };

        EventHandler<MouseEvent> placedBidsColorChangeExit = e -> {
            placeBidButton.textFillProperty().setValue(Color.BLACK);
        };

        EventHandler<MouseEvent> wishListEventHandler = e -> {
            if (e.isSecondaryButtonDown()) {
                addToWishListButton.textFillProperty().setValue(Color.ROYALBLUE);
                popUp[1].show();
                popUp[1].setX(addToWishListButton.getLayoutX() + 570);
                popUp[1].setY(addToWishListButton.getLayoutY() + 175);
            }
        };
        EventHandler<MouseEvent> wishListEscape = e -> {
            if (popUp[1].isShowing()) {
                addToWishListButton.textFillProperty().setValue(Color.BLACK);
                popUp[1].close();
            }
        };

        EventHandler<MouseEvent> wishListColorChange = e -> {
            addToWishListButton.textFillProperty().setValue(Color.ROYALBLUE);
        };

        EventHandler<MouseEvent> wishListColorChangeExit = e -> {
            addToWishListButton.textFillProperty().setValue(Color.BLACK);
        };

        //Registering the event filter
        placeBidButton.addEventFilter(MouseEvent.MOUSE_PRESSED, placedBidsEventHandler);
        placeBidButton.addEventFilter(MouseEvent.MOUSE_RELEASED, placedBidsEscape);
        placeBidButton.addEventFilter(MouseEvent.MOUSE_ENTERED_TARGET, placedBidsColorChange);
        placeBidButton.addEventFilter(MouseEvent.MOUSE_EXITED_TARGET, placedBidsColorChangeExit);

        addToWishListButton.addEventFilter(MouseEvent.MOUSE_PRESSED, wishListEventHandler);
        addToWishListButton.addEventFilter(MouseEvent.MOUSE_RELEASED, wishListEscape);
        addToWishListButton.addEventFilter(MouseEvent.MOUSE_ENTERED_TARGET, wishListColorChange);
        addToWishListButton.addEventFilter(MouseEvent.MOUSE_EXITED_TARGET, wishListColorChangeExit);
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
    public void showCars() throws IOException {
        Main main = new Main();
        main.openNewWindow("/GUI/Garage.fxml");
    }

    /**
     * Adds selected auction to user's wishlist
     */
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

                if (Long.parseLong(auctionIdField.getText()) == auction.getAuctionId()) {           // Duplicate
                    clear();
                    textArea.appendText("This auction is already in wishlist\n");
                    return;
                }

            }
        }
        for (Auction a : DatabaseOfAuctions.auctions) {
            if (Long.parseLong(auctionIdField.getText()) == a.getAuctionId()) {
                a.setNumberOfWishLists(a.getNumberOfWishLists() + 1);
                currentCustomer.getWishList().wishList.add(a);
                textArea.appendText("Auction added to wishlist\n");
                return;
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
