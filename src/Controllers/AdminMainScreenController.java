package Controllers;

import GUI.AboutScreen;
import GUI.InfoScreen;
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
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;

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
    @FXML
    private Button createAuctionButton;
    @FXML
    private Button showAuctionsButton;
    @FXML
    private Button showStatisticButton;
    @FXML
    private Button showNewsButton;
    @FXML
    private Button wishListStatisticsButton;

    public AdminMainScreenController() {
//        Platform.runLater(this::displayData);
        Platform.runLater(() -> {
            displayData();
            createEventHandlers();
        });
    }

    /**
     * Creates event handlers for selected buttons
     */
    private void createEventHandlers() {
        final InfoScreen[] popUp = new InfoScreen[5];
        popUp[0] = new InfoScreen("Create Auction", "Insert information and type of the auction");
        popUp[1] = new InfoScreen("Show Auctions", "Shows all available auctions");
        popUp[2] = new InfoScreen("Statistics", "Shows number of users and auctions");
        popUp[3] = new InfoScreen("Show News", "Shows information about sold auctions");
        popUp[4] = new InfoScreen("WishList Statistics", "Shows TOP 3 wishlisted auctions");

        EventHandler<MouseEvent> createAuctionEventHandler = e -> {
            if (e.isSecondaryButtonDown())
                popUp[0].show();
        };
        EventHandler<MouseEvent> createAuctionEscape = e -> {
            if (popUp[0].isShowing())
                popUp[0].close();
        };


        EventHandler<MouseEvent> showAuctionsEventHandler = e -> {
            if (e.isSecondaryButtonDown())
                popUp[1].show();
        };
        EventHandler<MouseEvent> showAuctionsEscape = e -> {
            if (popUp[1].isShowing())
                popUp[1].close();
        };


        EventHandler<MouseEvent> statisticsEventHandler = e -> {
            if (e.isSecondaryButtonDown())
                popUp[2].show();
        };
        EventHandler<MouseEvent> statisticsEscape = e -> {
            if (popUp[2].isShowing())
                popUp[2].close();
        };


        EventHandler<MouseEvent> showNewsEventHandler = e -> {
            if (e.isSecondaryButtonDown())
                popUp[3].show();
        };
        EventHandler<MouseEvent> showNewsEscape = e -> {
            if (popUp[3].isShowing())
                popUp[3].close();
        };


        EventHandler<MouseEvent> wishListStatisticsEventHandler = e -> {
            if (e.isSecondaryButtonDown())
                popUp[4].show();
        };
        EventHandler<MouseEvent> wishListStatisticsEscape = e -> {
            if (popUp[4].isShowing())
                popUp[4].close();
        };


        //Registering the event filter
        createAuctionButton.addEventFilter(MouseEvent.MOUSE_PRESSED, createAuctionEventHandler);
        createAuctionButton.addEventFilter(MouseEvent.MOUSE_RELEASED, createAuctionEscape);

        showAuctionsButton.addEventFilter(MouseEvent.MOUSE_PRESSED, showAuctionsEventHandler);
        showAuctionsButton.addEventFilter(MouseEvent.MOUSE_RELEASED, showAuctionsEscape);

        showStatisticButton.addEventFilter(MouseEvent.MOUSE_PRESSED, statisticsEventHandler);
        showStatisticButton.addEventFilter(MouseEvent.MOUSE_RELEASED, statisticsEscape);

        showNewsButton.addEventFilter(MouseEvent.MOUSE_PRESSED, showNewsEventHandler);
        showNewsButton.addEventFilter(MouseEvent.MOUSE_RELEASED, showNewsEscape);

        wishListStatisticsButton.addEventFilter(MouseEvent.MOUSE_PRESSED, wishListStatisticsEventHandler);
        wishListStatisticsButton.addEventFilter(MouseEvent.MOUSE_RELEASED, wishListStatisticsEscape);
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

    public void wishListStatistics() {
        int firstNumber = 0;
        int secondNumber = 0;
        int thirdNumber = 0;

        Auction first = null;
        Auction second = null;
        Auction third = null;

        for (Auction auction : DatabaseOfAuctions.auctions) {
            if (auction.getNumberOfWishLists() > firstNumber) {
                thirdNumber = secondNumber;
                third = second;
                secondNumber = firstNumber;
                second = first;
                firstNumber = auction.getNumberOfWishLists();
                first = auction;
            } else if (auction.getNumberOfWishLists() > secondNumber) {
                thirdNumber = secondNumber;
                third = second;
                secondNumber = firstNumber;
                second = first;
            } else if (auction.getNumberOfWishLists() > thirdNumber) {
                thirdNumber = secondNumber;
                third = second;
            }
        }

        if (first != null)
            textArea.appendText("TOP 1: " + first.car.getBrand() + " " + first.car.getModel() + " with number of wishlists: " + first.getNumberOfWishLists() + "\n");
        if (second != null)
            textArea.appendText("TOP 2: " + second.car.getBrand() + " " + second.car.getModel() + " with number of wishlists: " + second.getNumberOfWishLists() + "\n");
        if (third != null)
            textArea.appendText("TOP 3: " + third.car.getBrand() + " " + third.car.getModel() + " with number of wishlists: " + third.getNumberOfWishLists() + "\n");

    }


    public void showAboutScreen() {
        new AboutScreen();
    }
}
