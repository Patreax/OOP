package Controllers;

import Models.Auction;
import Models.Customer;
import Models.DatabaseOfAuctions;
import Models.DatabaseOfUsers;
import Project.sample.Main;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.ArrayList;

public class MainScreenController {

    @FXML
    private Button auctionButton;
    @FXML
    private Button logOutButton;
    @FXML
    private Button showDataButton;
    @FXML
    private Button garageButton;
    @FXML
    private Button clearButton;
    @FXML
    private Label userIDLabel;
    @FXML
    private Label userNameLabel;
    @FXML
    private Label userCurrencyLabel;
    @FXML
    private TextArea textArea;
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
        DatabaseOfUsers.currentUser = null;

        // Resetting auction to prevent duplicates
//        DatabaseOfAuctions.auctions.removeAll(DatabaseOfAuctions.auctions);
        DatabaseOfAuctions.auctions.clear();
//        for(Auction a : DatabaseOfAuctions.auctions)
//            DatabaseOfAuctions.auctions.remove(a);

        // Loading first screen
        Main main = new Main();
        main.changeScene("/GUI/sample.fxml");
    }

    public void newWindow() throws IOException{
        Main main = new Main();
        main.openNewWindow("/GUI/AuctionCreator.fxml");
    }

    public void showAuctions() throws IOException, ClassNotFoundException{
//        DatabaseOfAuctions.loadObjects();
//        DatabaseOfAuctions.displayAuctions();
        for(Auction a : DatabaseOfAuctions.auctions){
            textArea.appendText("ID: " + a.auctionId + "\t Brand: " + a.car.brand + "\t Model: " + a.car.model + "\n");
            textArea.appendText("Price: " + a.car.price + "\t Year: " + a.car.year + "\t Bids: " +a.getNumberOfBids() +"/" + a.getMaxBids() + "\n");
            textArea.appendText("\n");
        }
    }

    public void placeBidToAuction(){
        System.out.println("Ide to");
//        Customer currentUser = (Customer) DatabaseOfUsers.currentUser;
        Customer currentUser = new Customer();
        currentUser.placeBid(Integer.parseInt(auctionIdField.getText()), Double.parseDouble(auctionAmountField.getText()));
    }

    public void showCars(){
        textArea.appendText("Cars\n");
    }

    public void clear(){
        textArea.setText("");
//        auctionButton.setVisible(false);
    }

}
