package Controllers;

import GUI.AboutScreen;
import Models.*;
import Models.Auctions.Auction;
import Models.Cars.ElectricCar;
import Models.Cars.HybridCar;
import Models.Cars.StandardCar;
import Models.Databases.DatabaseOfAuctions;
import Models.Databases.DatabaseOfUsers;
import Models.Users.Admin;
import Models.Users.PremiumUser;
import Models.Users.StandardUser;
import Models.Users.User;
import Project.sample.Main;
import javafx.application.Platform;
import javafx.fxml.FXML;

import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import java.io.IOException;

public class AdminMainScreenController {

    @FXML
    private Label userNameLabel;
    @FXML
    private Label userIDLabel;
    @FXML
    private TextArea textArea;

    public AdminMainScreenController(){
        Platform.runLater(this::displayData);
    }

    private void displayData(){         // Displaying Admin data
        userNameLabel.setText(DatabaseOfUsers.currentUser.getUserName());
        userIDLabel.setText(Long.toString(DatabaseOfUsers.currentUser.getUserId()));
    }

    public void newWindow() throws IOException {
        Main main = new Main();
        main.openNewWindow("/GUI/AuctionCreator.fxml");
    }

    public void showAuctions() {
        // Going through all the auction via iterator
        new Thread(() -> {
            for(Auction a : DatabaseOfAuctions.auctions){
            textArea.appendText("ID: " + a.getAuctionId() + "\t Brand: " + a.car.brand + "\t Model: " + a.car.model + "\n");
            textArea.appendText("Price: " + a.car.price + "\t Year: " + a.car.year + "\t Bids: " +a.getNumberOfBids() +"/" + a.getMaxBids() + "\n");
            textArea.appendText("\n");
        }}).start();

    }

    public void calculateStatistics(){
        textArea.appendText("Admin: " + countUsers(Admin.class) + "\n");
        textArea.appendText("Premium User: " + countUsers(PremiumUser.class) + "\n");
        textArea.appendText("Standard User: " + countUsers(StandardUser.class) + "\n");

        textArea.appendText("Standard Car: " + countAuctions(StandardCar.class) + "\n");
        textArea.appendText("Electric Car: " + countAuctions(ElectricCar.class) + "\n");
        textArea.appendText("Hybrid Car: " + countAuctions(HybridCar.class) + "\n");
    }

    public void showStatistics(){                           // Reflexia
//        new Thread(this::calculateStatistics).start();
        new Thread(() -> {textArea.appendText("Admin: " + countUsers(Admin.class) + "\n");
            textArea.appendText("Premium User: " + countUsers(PremiumUser.class) + "\n");
            textArea.appendText("Standard User: " + countUsers(StandardUser.class) + "\n");

            textArea.appendText("Standard Car: " + countAuctions(StandardCar.class) + "\n");
            textArea.appendText("Electric Car: " + countAuctions(ElectricCar.class) + "\n");
            textArea.appendText("Hybrid Car: " + countAuctions(HybridCar.class) + "\n");}).start();
    }

    private int countUsers(Class Type){
        int number = 0;

        for (User user : DatabaseOfUsers.registeredUsers){
            if (Type.isInstance(user))
                number++;
        }
        return number;
    }
    private int countAuctions(Class Type){
        int number = 0;

        for (Auction auction : DatabaseOfAuctions.auctions){
            if (Type.isInstance(auction.car))
                number++;
        }
        return number;
    }

    public void clear(){
        textArea.setText("");
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

    public void showNews(){         // Part of observer
//        Admin observer = (Admin) DatabaseOfUsers.currentUser;
//        for (String message : observer.news){
//            textArea.appendText(message + "\n");
//            System.out.println(message);
//        }

        for(String message : Admin.news){
            textArea.appendText(message + "\n");
            System.out.println(message);
        }
    }

    public void showAboutScreen(){
        new AboutScreen();
    }
}
