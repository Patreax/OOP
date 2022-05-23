package Controllers;

import GUI.AboutScreen;
import GUI.InfoScreen;
import Models.Auctions.Auction;
import Models.Auctions.AuctionManager;
import Models.Cars.ElectricCar;
import Models.Cars.HybridCar;
import Models.Cars.StandardCar;
import Models.Databases.DatabaseOfAuctions;
import Models.Databases.DatabaseOfUsers;
import Models.MainScreenInterface;
import Models.Observer;
import Models.Users.Admin;
import Models.Users.PremiumUser;
import Models.Users.StandardUser;
import Models.Users.User;
import Project.Main.Main;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

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
    @FXML
    private Button showHistoryButton;

    public AdminMainScreenController() {
        Platform.runLater(() -> {
            displayData();
            createEventHandlers();
        });
    }

    /**
     * Creates event handlers for selected buttons
     */
    private void createEventHandlers() {
        final InfoScreen[] popUp = new InfoScreen[6];
        popUp[0] = new InfoScreen("Create Auction", "Insert information and type of the auction");
        popUp[1] = new InfoScreen("Show Auctions", "Shows all available auctions");
        popUp[2] = new InfoScreen("Statistics", "Shows number of users and auctions");
        popUp[3] = new InfoScreen("Show News", "Shows information about sold auctions");
        popUp[4] = new InfoScreen("WishList Statistics", "Shows TOP 3 wishlisted auctions");
        popUp[5] = new InfoScreen("History", "Shows history of placed bids to the auctions");

        // Creating event handlers
        EventHandler<MouseEvent> createAuctionEventHandler = e -> {
            if (e.isSecondaryButtonDown()) {
                createAuctionButton.textFillProperty().setValue(Color.ROYALBLUE);
                popUp[0].show();
            }
        };
        EventHandler<MouseEvent> createAuctionEscape = e -> {
            if (popUp[0].isShowing()) {
                createAuctionButton.textFillProperty().setValue(Color.BLACK);
                popUp[0].close();
            }
        };
        EventHandler<MouseEvent> createAuctionColorChange = e -> {
            createAuctionButton.textFillProperty().setValue(Color.ROYALBLUE);
        };

        EventHandler<MouseEvent> createAuctionColorChangeExit = e -> {
            createAuctionButton.textFillProperty().setValue(Color.BLACK);
        };

        EventHandler<MouseEvent> showAuctionsEventHandler = e -> {
            if (e.isSecondaryButtonDown()) {
                showAuctionsButton.textFillProperty().setValue(Color.ROYALBLUE);
                popUp[1].show();
            }
        };
        EventHandler<MouseEvent> showAuctionsEscape = e -> {
            if (popUp[1].isShowing()) {
                showAuctionsButton.textFillProperty().setValue(Color.BLACK);
                popUp[1].close();
            }
        };
        EventHandler<MouseEvent> showAuctionsColorChange = e -> {
            showAuctionsButton.textFillProperty().setValue(Color.ROYALBLUE);
        };

        EventHandler<MouseEvent> showAuctionsColorChangeExit = e -> {
            showAuctionsButton.textFillProperty().setValue(Color.BLACK);
        };

        EventHandler<MouseEvent> statisticsEventHandler = e -> {
            if (e.isSecondaryButtonDown()) {
                showStatisticButton.textFillProperty().setValue(Color.ROYALBLUE);
                popUp[2].show();
            }
        };
        EventHandler<MouseEvent> statisticsEscape = e -> {
            if (popUp[2].isShowing()) {
                showStatisticButton.textFillProperty().setValue(Color.BLACK);
                popUp[2].close();
            }
        };
        EventHandler<MouseEvent> statisticsColorChange = e -> {
            showStatisticButton.textFillProperty().setValue(Color.ROYALBLUE);
        };

        EventHandler<MouseEvent> statisticsColorChangeExit = e -> {
            showStatisticButton.textFillProperty().setValue(Color.BLACK);
        };

        EventHandler<MouseEvent> showNewsEventHandler = e -> {
            if (e.isSecondaryButtonDown()) {
                showNewsButton.textFillProperty().setValue(Color.ROYALBLUE);
                popUp[3].show();
            }
        };
        EventHandler<MouseEvent> showNewsEscape = e -> {
            if (popUp[3].isShowing()) {
                showNewsButton.textFillProperty().setValue(Color.BLACK);
                popUp[3].close();
            }
        };
        EventHandler<MouseEvent> showNewsColorChange = e -> {
            showNewsButton.textFillProperty().setValue(Color.ROYALBLUE);
        };

        EventHandler<MouseEvent> showNewsColorChangeExit = e -> {
            showNewsButton.textFillProperty().setValue(Color.BLACK);
        };

        EventHandler<MouseEvent> wishListStatisticsEventHandler = e -> {
            if (e.isSecondaryButtonDown()) {
                wishListStatisticsButton.textFillProperty().setValue(Color.ROYALBLUE);
                popUp[4].show();
            }
        };
        EventHandler<MouseEvent> wishListStatisticsEscape = e -> {
            if (popUp[4].isShowing()) {
                wishListStatisticsButton.textFillProperty().setValue(Color.BLACK);
                popUp[4].close();
            }
        };
        EventHandler<MouseEvent> wishListStatisticsColorChange = e -> {
            wishListStatisticsButton.textFillProperty().setValue(Color.ROYALBLUE);
        };

        EventHandler<MouseEvent> wishListStatisticsColorChangeExit = e -> {
            wishListStatisticsButton.textFillProperty().setValue(Color.BLACK);
        };

        EventHandler<MouseEvent> historyEventHandler = e -> {
            if (e.isSecondaryButtonDown()) {
                showHistoryButton.textFillProperty().setValue(Color.ROYALBLUE);
                popUp[5].show();
            }
        };
        EventHandler<MouseEvent> historyEscape = e -> {
            if (popUp[5].isShowing()) {
                showHistoryButton.textFillProperty().setValue(Color.BLACK);
                popUp[5].close();
            }
        };
        EventHandler<MouseEvent> historyColorChange = e -> {
            showHistoryButton.textFillProperty().setValue(Color.ROYALBLUE);
        };

        EventHandler<MouseEvent> historyColorChangeExit = e -> {
            showHistoryButton.textFillProperty().setValue(Color.BLACK);
        };


        //Registering the event filter
        createAuctionButton.addEventFilter(MouseEvent.MOUSE_PRESSED, createAuctionEventHandler);
        createAuctionButton.addEventFilter(MouseEvent.MOUSE_RELEASED, createAuctionEscape);
        createAuctionButton.addEventFilter(MouseEvent.MOUSE_ENTERED_TARGET, createAuctionColorChange);
        createAuctionButton.addEventFilter(MouseEvent.MOUSE_EXITED_TARGET, createAuctionColorChangeExit);

        showAuctionsButton.addEventFilter(MouseEvent.MOUSE_PRESSED, showAuctionsEventHandler);
        showAuctionsButton.addEventFilter(MouseEvent.MOUSE_RELEASED, showAuctionsEscape);
        showAuctionsButton.addEventFilter(MouseEvent.MOUSE_ENTERED_TARGET, showAuctionsColorChange);
        showAuctionsButton.addEventFilter(MouseEvent.MOUSE_EXITED_TARGET, showAuctionsColorChangeExit);

        showStatisticButton.addEventFilter(MouseEvent.MOUSE_PRESSED, statisticsEventHandler);
        showStatisticButton.addEventFilter(MouseEvent.MOUSE_RELEASED, statisticsEscape);
        showStatisticButton.addEventFilter(MouseEvent.MOUSE_ENTERED_TARGET, statisticsColorChange);
        showStatisticButton.addEventFilter(MouseEvent.MOUSE_EXITED_TARGET, statisticsColorChangeExit);

        showNewsButton.addEventFilter(MouseEvent.MOUSE_PRESSED, showNewsEventHandler);
        showNewsButton.addEventFilter(MouseEvent.MOUSE_RELEASED, showNewsEscape);
        showNewsButton.addEventFilter(MouseEvent.MOUSE_ENTERED_TARGET, showNewsColorChange);
        showNewsButton.addEventFilter(MouseEvent.MOUSE_EXITED_TARGET, showNewsColorChangeExit);

        wishListStatisticsButton.addEventFilter(MouseEvent.MOUSE_PRESSED, wishListStatisticsEventHandler);
        wishListStatisticsButton.addEventFilter(MouseEvent.MOUSE_RELEASED, wishListStatisticsEscape);
        wishListStatisticsButton.addEventFilter(MouseEvent.MOUSE_ENTERED_TARGET, wishListStatisticsColorChange);
        wishListStatisticsButton.addEventFilter(MouseEvent.MOUSE_EXITED_TARGET, wishListStatisticsColorChangeExit);

        showHistoryButton.addEventFilter(MouseEvent.MOUSE_PRESSED, historyEventHandler);
        showHistoryButton.addEventFilter(MouseEvent.MOUSE_RELEASED, historyEscape);
        showHistoryButton.addEventFilter(MouseEvent.MOUSE_ENTERED_TARGET, historyColorChange);
        showHistoryButton.addEventFilter(MouseEvent.MOUSE_EXITED_TARGET, historyColorChangeExit);
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

    /**
     * Displays number of users and auctions and their subclasses
     */
    public void showStatistics() {                           // Reflexia
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
        MainScreenInterface.super.logOut(DatabaseOfUsers.currentUser);
    }

    /**
     * Displays news about sold auctions
     */
    public void showNews() {         // Part of observer
        clear();

        AuctionManager auctionManager = AuctionManager.getInstance();
        for (Observer observer : auctionManager.observers) {
            if (((Admin) observer).getUserId() == DatabaseOfUsers.currentUser.getUserId()) {
                for (String message : ((Admin) observer).getMessages()) {
                    textArea.appendText(message + "\n");
                }
            }
        }
    }

    /**
     * Displays TOP 3 wishlisted auctions by users
     */
    public void wishListStatistics() {
        clear();

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

    public void showHistory() throws IOException {
        clear();
        try {
            File history = new File("src/Project/Files/history.txt");
            Scanner myReader = new Scanner(history);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                textArea.appendText(data + "\n");
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            File history = new File("src/Project/Files/history.txt");
            history.createNewFile();
            e.printStackTrace();
        }
    }

    public void showAboutScreen() {
        new AboutScreen();
    }
}
