package Controllers;

import Models.Auctions.AuctionManager;
import Models.Databases.*;
import Models.News;
import Models.PersonalGarage;
import Models.Users.Admin;
import Models.Users.PremiumUser;
import Models.Users.StandardUser;
import Models.Users.User;
import Models.Wallet;
import Models.WishList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

/**
 * The <code>Controller</code> class acts as a controller for LogInScreen.fxml file
 * It is responsible for registering and logging in the user
 */
public class Controller {

    @FXML
    private TextField userNameField;
    @FXML
    private PasswordField userPasswordField;
    @FXML
    private Label errorMessage;


    public Controller() throws IOException, ClassNotFoundException {     // Loading the database af users and auctions
        // Loads all the necessary data
        for (Database database : Database.databases) {
            database.loadObjects();
        }
    }

    /**
     * Checks the input data and logs in the user
     *
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public void logIn() throws IOException {
        checkData(userNameField.getText(), userPasswordField.getText());
    }

    /**
     * Takes data from input fields and creates new object of type {@link User} and stores it in the {@link DatabaseOfUsers}.
     * Also creates aggregated objects
     *
     * @throws IOException
     */
    public void register() throws IOException {

        AuctionManager auctionManager = AuctionManager.getInstance();
        // Checking for empty spaces
        if (userNameField.getText().equals("") || userNameField.getText().equals(" ") || userPasswordField.getText().equals("") || userPasswordField.getText().equals(" ")) {
            errorMessage.setText("Incorrect user data format");
            return;
        }
        // Checking for duplicates
        if (checkDuplicates(userNameField.getText())) {
            if (userNameField.getText().contains("Admin")) {                                          // Registering ADMIN
                Admin newUser = new Admin(userNameField.getText(), userPasswordField.getText());
                DatabaseOfUsers.storeObject(newUser);
                auctionManager.register(newUser);
                News news = new News();
                DatabaseOfNews.storeObject(news);
                errorMessage.setText("User has been registered");
                return;
            }
            if (userNameField.getText().contains("Premium")) {                                        // Registering PREMIUM User
                PremiumUser newUser = new PremiumUser(userNameField.getText(), userPasswordField.getText());
                Wallet wallet = new Wallet();
                PersonalGarage personalGarage = new PersonalGarage();
                WishList wishList = new WishList();
                DatabaseOfUsers.storeObject(newUser);
                DatabaseOfWallets.storeObject(wallet);
                DatabaseOfGarages.storeObject(personalGarage);
                DatabaseOfWishLists.storeObject(wishList);
                errorMessage.setText("User has been registered");
                return;
            }

            StandardUser newUser = new StandardUser(userNameField.getText(), userPasswordField.getText());  // registering STANDARD User
            Wallet wallet = new Wallet();
            PersonalGarage personalGarage = new PersonalGarage();
            WishList wishList = new WishList();
            DatabaseOfUsers.storeObject(newUser);
            DatabaseOfWallets.storeObject(wallet);
            DatabaseOfGarages.storeObject(personalGarage);
            DatabaseOfWishLists.storeObject(wishList);
            errorMessage.setText("User has been registered");
        }
    }

    /**
     * Checks data provided by user and if valid, logs in the user.
     *
     * @param userName
     * @param userPassWord
     * @throws IOException
     */
    private void checkData(String userName, String userPassWord) throws IOException {
        if (DatabaseOfUsers.registeredUsers.size() == 0) {             // Checking for empty array of registered users
            errorMessage.setText("User does not exist");
            return;
        }

        ObjectInputStream in = new ObjectInputStream(new FileInputStream(DatabaseOfUsers.userData));
        if (!DatabaseOfUsers.registeredUsers.isEmpty()) {
            for (User user : DatabaseOfUsers.registeredUsers) {
                if (user.getUserName().equals(userName) && user.getPassword().equals(userPassWord)) {
                    user.logIn(user);
                    in.close();
                    return;
                }
            }
        }
        in.close();
        errorMessage.setText("Name or password is incorrect");
    }

    /**
     * Checks whether the user with given username already exists
     *
     * @param userName
     * @return false if the user already exists. Otherwise returns true
     */
    public boolean checkDuplicates(String userName) {       // Checking for duplicates
        if (!DatabaseOfUsers.registeredUsers.isEmpty()) {
            for (User user : DatabaseOfUsers.registeredUsers) {
                if (user.getUserName().equals(userName)) {
                    errorMessage.setText("Name is already taken");
                    return false;
                }
            }
        }
        return true;
    }
}
