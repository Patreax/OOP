package Controllers;

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
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.IOException;


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

    public MainScreenController() {
        mainScreenControllerInstance = this;

        Platform.runLater(this::displayData);

    }

//    private static MainScreenController single_instance = null;
//    public static MainScreenController getInstance(){     // Singleton
//        if (single_instance == null)
//            single_instance = new MainScreenController();
//
//        return single_instance;
//    }

    public void displayData() {
        Customer currentCustomer = (Customer) DatabaseOfUsers.currentUser;
        userCurrencyLabel.setText(Double.toString(currentCustomer.getWallet().getBids()));
        userNameLabel.setText(DatabaseOfUsers.currentUser.getUserName());
        userIDLabel.setText(Long.toString(DatabaseOfUsers.currentUser.getUserId()));
    }

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

    public void showCars() {
        textArea.setText("");
        Customer currentUser = (Customer) DatabaseOfUsers.currentUser;
        if(currentUser.getGarage().getCars().isEmpty())
            textArea.appendText("You do not own any cars at the moment\n");
        for (Car car : currentUser.getGarage().getCars()) {
            textArea.appendText(car.getBrand() + " " + car.getModel() + "\n");
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
