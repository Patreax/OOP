package Controllers;

import Models.*;
import Models.Auctions.AuctionManager;
import Models.Users.Admin;
import Models.Users.PremiumUser;
import Models.Users.StandardUser;
import Models.Users.User;
import Project.sample.Main;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;


public class Controller {

    @FXML
    private TextField userNameField;
    @FXML
    private PasswordField userPasswordField;
    @FXML
    private Label errorMessage;


    public Controller() throws IOException, ClassNotFoundException{     // Loading the database af users and auctions
        DatabaseOfUsers.loadObjects();
        DatabaseOfAuctions.loadObjects();
    }

    public void logIn() throws IOException, ClassNotFoundException{
        checkData(userNameField.getText(), userPasswordField.getText());
    }

    public void register() throws IOException, ClassNotFoundException{

        AuctionManager auctionManager = AuctionManager.getInstance();
        // Checking for empty spaces
        if(userNameField.getText().equals("") || userNameField.getText().equals(" ") || userPasswordField.getText().equals("") || userPasswordField.getText().equals(" ")){
            errorMessage.setText("Incorrect user data format");
            return;
        }
        // Checking for duplicates
        if(checkDuplicates(userNameField.getText())){
            if(userNameField.getText().contains("Admin")){                                          // Registering ADMIN
                Admin newUser = new Admin(userNameField.getText(), userPasswordField.getText());
                DatabaseOfUsers.storeObject(newUser);
                errorMessage.setText("User has been registered");
                return;
            }
            if(userNameField.getText().contains("Premium")){                                        // Registering PREMIUM User
                PremiumUser newUser = new PremiumUser(userNameField.getText(), userPasswordField.getText());
                DatabaseOfUsers.storeObject(newUser);
                errorMessage.setText("User has been registered");
                return;
            }

            StandardUser newUser = new StandardUser(userNameField.getText(), userPasswordField.getText());  // registering STANDARD User

            DatabaseOfUsers.storeObject(newUser);
            errorMessage.setText("User has been registered");
        }
    }

    private void checkData(String userName, String userPassWord) throws IOException, ClassNotFoundException{
        if (DatabaseOfUsers.registeredUsers.size() == 0){             // Checking for empty array of registered users
            errorMessage.setText("User does not exist");
            return;
        }

        ObjectInputStream in = new ObjectInputStream(new FileInputStream(DatabaseOfUsers.userData));
        if(!DatabaseOfUsers.registeredUsers.isEmpty()){
            for(User user : (ArrayList<User>)in.readObject()){
                // Admin
                if(user instanceof Admin && user.getUserName().equals(userName) && user.getPassword().equals(userPassWord)){
                    DatabaseOfUsers.currentUser = user;
                    Main main = new Main();
                    main.changeScene("/GUI/AdminMainScreen.fxml");
                    return;
                }
                // Premium
                if(user instanceof PremiumUser && user.getUserName().equals(userName) && user.getPassword().equals(userPassWord)){
                    DatabaseOfUsers.currentUser = user;
                    Main main = new Main();
                    main.changeScene("/GUI/MainScreen.fxml");
                    return;
                }

                if(user.getUserName().equals(userName) && user.getPassword().equals(userPassWord)){
                    DatabaseOfUsers.currentUser = user;
                    in.close();
                    Main main = new Main();
                    main.changeScene("/GUI/MainScreen.fxml");
                    return;
                }
            }
        }
        in.close();
        errorMessage.setText("Name or password is incorrect");
    }

    public boolean checkDuplicates(String userName) {       // Checking for duplicates
        if(!DatabaseOfUsers.registeredUsers.isEmpty()){
            for(User user : DatabaseOfUsers.registeredUsers){
                if(user.getUserName().equals(userName)){
                    errorMessage.setText("Name is already taken");
                    return false;
                }
            }
        }

        return true;
    }
}
