package Controllers;

import GUI.AboutScreen;
import Models.Auctions.Auction;
import Models.Cars.ElectricCar;
import Models.Cars.HybridCar;
import Models.Cars.StandardCar;
import Models.Databases.DatabaseOfAuctions;
import Models.Databases.DatabaseOfUsers;
import Models.MainScreenInterface;
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

/**
 * The <code>AdminMainScreenController</code> acts as a controller for AdminMainScreen.fxml file.
 * It is responsible for accessing the main text area in said UI and for executing methods designed for the admin
 *
 * @see Admin
 */
public class AdminMainScreenController extends MainScreen implements MainScreenInterface {

    @FXML
    private Label userNameLabel;
    @FXML
    private Label userIDLabel;
    @FXML
    private TextArea textArea;

    public AdminMainScreenController() {
        Platform.runLater(this::displayData);
    }

    /**
     * Displays username and user ID
     */
    public void displayData() {         // Displaying Admin data
        userNameLabel.setText(DatabaseOfUsers.currentUser.getUserName());
        userIDLabel.setText(Long.toString(DatabaseOfUsers.currentUser.getUserId()));
    }

    public void newWindow() throws IOException {
        Main main = new Main();
        main.openNewWindow("/GUI/AuctionCreator.fxml");
    }

    /**
     * Displays all the current auctions
     */
    public void showAuctions() {
        // Going through all the auction via iterator
        clear();
        new Thread(() -> {
            for (Auction a : DatabaseOfAuctions.auctions) {
                textArea.appendText("ID: " + a.getAuctionId() + "\t Brand: " + a.car.getBrand() + "\t Model: " + a.car.getModel() + "\n");
                textArea.appendText("Price: " + a.car.getPrice() + "\t Year: " + a.car.getYear() + "\t Bids: " + a.getNumberOfBids() + "/" + a.getMaxBids() + "\n");
                textArea.appendText("\n");
            }
        }).start();

    }

    public void calculateStatistics() {
        textArea.appendText("Admin: " + countUsers(Admin.class) + "\n");
        textArea.appendText("Premium User: " + countUsers(PremiumUser.class) + "\n");
        textArea.appendText("Standard User: " + countUsers(StandardUser.class) + "\n");

        textArea.appendText("Standard Car: " + countAuctions(StandardCar.class) + "\n");
        textArea.appendText("Electric Car: " + countAuctions(ElectricCar.class) + "\n");
        textArea.appendText("Hybrid Car: " + countAuctions(HybridCar.class) + "\n");
    }

    /**
     * Displays number of users and auctions and their subclasses
     */
    public void showStatistics() {                           // Reflexia
//        new Thread(this::calculateStatistics).start();
        clear();
        new Thread(() -> {
            textArea.appendText("Admin: " + countUsers(Admin.class) + "\n");
            textArea.appendText("Premium User: " + countUsers(PremiumUser.class) + "\n");
            textArea.appendText("Standard User: " + countUsers(StandardUser.class) + "\n");

            textArea.appendText("Standard Car: " + countAuctions(StandardCar.class) + "\n");
            textArea.appendText("Electric Car: " + countAuctions(ElectricCar.class) + "\n");
            textArea.appendText("Hybrid Car: " + countAuctions(HybridCar.class) + "\n");
        }).start();
    }

    /**
     * Counts the occurrences of objects derived from {@link User} class
     *
     * @param Type
     * @return number of occurrences
     */
    private int countUsers(Class Type) {
        int number = 0;

        for (User user : DatabaseOfUsers.registeredUsers) {
            if (Type.isInstance(user))
                number++;
        }
        return number;
    }

    /**
     * Counts the occurrences of objects derived from {@link Auction} class
     *
     * @param Type
     * @return number  of occurrences
     */
    private int countAuctions(Class Type) {
        int number = 0;

        for (Auction auction : DatabaseOfAuctions.auctions) {
            if (Type.isInstance(auction.car))
                number++;
        }
        return number;
    }

    public void clear() {
        textArea.setText("");
    }

    /**
     * Saves user and auction data and logs out the current user
     *
     * @throws IOException
     */
    public void logOut() throws IOException {
//        // Setting current user to null
//        DatabaseOfUsers.currentUser = null;
//        // Saving all the data
//        Serializator serializator = new Serializator();
//        serializator.saveData(DatabaseOfAuctions.auctions, DatabaseOfAuctions.auctionData);
//        serializator.saveData(DatabaseOfUsers.registeredUsers, DatabaseOfUsers.userData);
//        // Clearing the user and auction database
//        DatabaseOfAuctions.auctions.clear();
//        DatabaseOfUsers.registeredUsers.clear();
//        DatabaseOfWallets.wallets.clear();
//        DatabaseOfGarages.garages.clear();
//
//        // Loading first screen
//        Main main = new Main();
//        main.changeScene("/GUI/sample.fxml");

        MainScreenInterface.super.logOut(DatabaseOfUsers.currentUser);
    }

    /**
     * Displays news about sold auctions
     */
    public void showNews() {         // Part of observer
        clear();
        Admin admin = (Admin) DatabaseOfUsers.currentUser;
        for (String message : admin.news) {
            textArea.appendText(message + "\n");
        }
    }

    public void showAboutScreen() {
        new AboutScreen();
    }
}
