package Controllers;

import Models.*;
import Project.sample.Main;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
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


        Serializator serializator = new Serializator();
        serializator.saveData(DatabaseOfAuctions.auctions, DatabaseOfAuctions.auctionData);
        serializator.saveData(DatabaseOfUsers.registeredUsers, DatabaseOfUsers.userData);

//        ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(DatabaseOfAuctions.auctionData));
//        output.writeObject(DatabaseOfAuctions.auctions);
//        output.close();

        // Resetting auction to prevent duplicates
//        DatabaseOfAuctions.auctions.removeAll(DatabaseOfAuctions.auctions);
        DatabaseOfAuctions.auctions.clear();
        DatabaseOfUsers.registeredUsers.clear();                // Toto som spravil pri reflexii
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
        Boolean premiumUser = DatabaseOfUsers.currentUser instanceof PremiumUser;
        for(Auction a : DatabaseOfAuctions.auctions){
            if (a.isPremium && !premiumUser)
                continue;
            textArea.appendText("ID: " + a.auctionId + "\t Brand: " + a.car.brand + "\t Model: " + a.car.model + "\n");
            textArea.appendText("Price: " + a.car.price + "\t Year: " + a.car.year + "\t Bids: " +a.getNumberOfBids() +"/" + a.getMaxBids() + "\n");
            textArea.appendText("\n");
        }
    }

    public void placeBidToAuction() throws IOException, ClassNotFoundException{


        try{
//            StandardUser currentUser = (StandardUser) DatabaseOfUsers.currentUser;
            Customer currentUser = (Customer) DatabaseOfUsers.currentUser;
            currentUser.placeBid(Integer.parseInt(auctionIdField.getText()), Double.parseDouble(auctionAmountField.getText()));
        } catch (NumberFormatException e){
            textArea.appendText("Invalid input\n");
        } catch (ClassCastException f){
            textArea.appendText("Cast not working\n");
        }

        textArea.setText("");
        showAuctions();
//        System.out.println("Customer");
//        System.out.println(DatabaseOfUsers.currentUser instanceof Customer);
//        System.out.println("Standard User");
//        System.out.println(DatabaseOfUsers.currentUser instanceof StandardUser);
//        System.out.println("User");
//        System.out.println(DatabaseOfUsers.currentUser instanceof User);
//        System.out.println("Admin");
//        System.out.println(DatabaseOfUsers.currentUser instanceof Admin);

    }

    public void showCars(){
//        textArea.appendText("Cars\n");
//        StandardUser currentUser = (StandardUser) DatabaseOfUsers.currentUser;
        Customer currentUser = (Customer) DatabaseOfUsers.currentUser;
        for(Car car : currentUser.getGarage().getCars()){
            textArea.appendText(car.brand + car.model + "\n");
        }
    }

    public void clear(){
        textArea.setText("");
//        auctionButton.setVisible(false);
    }

    public void openWallet() throws IOException{
        Main main = new Main();
        main.openNewWindow("/GUI/Wallet.fxml");
    }

}
