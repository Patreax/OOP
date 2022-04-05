package Controllers;

import Models.*;
import Models.Auctions.Auction;
import Models.Cars.Car;
import Models.Users.Customer;
import Models.Users.PremiumUser;
import Project.sample.Main;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.IOException;

public class MainScreenController {

    @FXML
    private Label userIDLabel;
    @FXML
    private Label userNameLabel;
    @FXML
    private Label userCurrencyLabel;
    @FXML
    public TextArea textArea;
    @FXML
    private TextField auctionIdField;
    @FXML
    private TextField auctionAmountField;

    public MainScreenController(){
//        Platform.runLater(() -> displayData());
        Platform.runLater(this::displayData);
    }

    private void displayData(){
        userCurrencyLabel.setText("1000");
        userNameLabel.setText(DatabaseOfUsers.currentUser.getUserName());
        userIDLabel.setText(Long.toString(DatabaseOfUsers.currentUser.getUserId()));
    }

    public void logOut() throws IOException {
        // Setting current user to null
        DatabaseOfUsers.currentUser = null;
        // Saving all the data
        Serializator serializator = new Serializator();
        serializator.saveData(DatabaseOfAuctions.auctions, DatabaseOfAuctions.auctionData);
        serializator.saveData(DatabaseOfUsers.registeredUsers, DatabaseOfUsers.userData);
        // Clearing the user and auction database
        DatabaseOfAuctions.auctions.clear();
        DatabaseOfUsers.registeredUsers.clear();

        // Loading first screen
        Main main = new Main();
        main.changeScene("/GUI/sample.fxml");
    }

    public void showAuctions() {                    // Showing auctions for different types of users
        boolean premiumUser = DatabaseOfUsers.currentUser instanceof PremiumUser;
        for(Auction a : DatabaseOfAuctions.auctions){
            if (a.isPremium && !premiumUser)
                continue;
            textArea.appendText("ID: " + a.getAuctionId() + "\t Brand: " + a.car.brand + "\t Model: " + a.car.model + "\n");
            textArea.appendText("Price: " + a.car.price + "\t Year: " + a.car.year + "\t Bids: " +a.getNumberOfBids() +"/" + a.getMaxBids() + "\n");
            textArea.appendText("\n");
        }
    }

    public void placeBidToAuction() {
        try{
            boolean isPresent = false;                              // Checking if ID is available
            for(Auction auction : DatabaseOfAuctions.auctions){
                if(Integer.parseInt(auctionIdField.getText()) == auction.getAuctionId()){
                    isPresent = true;
                }
            }
            if(!isPresent){
                textArea.appendText("Auction with given ID does not exist\n");
                return;
            }

            if(Double.parseDouble(auctionAmountField.getText()) <= 0){  // Checking if placed amount is above 0
                textArea.appendText("You must bid more than 0\n");
                return;
            }

            Customer currentUser = (Customer) DatabaseOfUsers.currentUser;
            currentUser.placeBid(Integer.parseInt(auctionIdField.getText()), Double.parseDouble(auctionAmountField.getText())); // placing bid
        } catch (NumberFormatException e){
            textArea.appendText("Invalid input\n");
        } catch (ClassCastException f){
            textArea.appendText("Cast not working\n");
        }

        textArea.setText("");
        showAuctions();
    }

    public void showCars(){
        Customer currentUser = (Customer) DatabaseOfUsers.currentUser;
        for(Car car : currentUser.getGarage().getCars()){
            textArea.appendText(car.brand + car.model + "\n");
        }
    }

    public void clear(){
        textArea.setText("");
    }

    public void openWallet() throws IOException{
        Main main = new Main();
        main.openNewWindow("/GUI/Wallet.fxml");
    }

}
