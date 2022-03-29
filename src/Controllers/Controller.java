package Controllers;

import Models.*;
import Project.sample.Main;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import javax.xml.crypto.Data;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Scanner;


public class Controller {

    DatabaseOfUsers database = new DatabaseOfUsers();
    File userData = database.userData;

    //  MainScreenController mainController = new MainScreenController();

    public Controller(){

    }

    @FXML
    private TextField userNameField;
    @FXML
    private PasswordField userPasswordField;
    @FXML
    private Label errorMessage;
    @FXML
    private Button logInButton;
    @FXML
    private Button registerButton;

    public void logIn() throws IOException, ClassNotFoundException{
//        DatabaseOfAuctions.loadObjects();                                                                 // TOTO TOTO TOTO TOTO TOTO TOTO
        checkData(userNameField.getText(), userPasswordField.getText());
        //mainController.currentUser = new User(userNameField.getText(), userPasswordField.getText());

    }

    public void register() throws IOException, ClassNotFoundException{
        if(checkDuplicates(userNameField.getText())){

            if(userNameField.getText().contains("Admin")){                                          // ADMIN
                Admin newUser = new Admin(userNameField.getText(), userPasswordField.getText());
                database.storeObject(newUser);
                errorMessage.setText("User has been registered");
                return;
            }
            if(userNameField.getText().contains("Premium")){                                        // PREMIUM
                PremiumUser newUser = new PremiumUser(userNameField.getText(), userPasswordField.getText());
                database.storeObject(newUser);
                errorMessage.setText("User has been registered");
                return;
            }

            StandardUser newUser = new StandardUser(userNameField.getText(), userPasswordField.getText());
//            database.storeData(newUser.getUserId(), userNameField.getText(), userPasswordField.getText());
//            database.registeredUsers.add(newUser);
            database.storeObject(newUser);
            errorMessage.setText("User has been registered");
        }
    }

    private void checkData(String userName, String userPassWord) throws IOException, ClassNotFoundException{
        database.loadObjects();
        if(database.registeredUsers.size() == 0){             // Toto treba prepisat
            errorMessage.setText("User does not exist");
            return;
        }


        ObjectInputStream in = new ObjectInputStream(new FileInputStream(userData));
        if(!database.registeredUsers.isEmpty()){
            for(User user : (ArrayList<User>)in.readObject()){
                // Admin
                if(user instanceof Admin && user.getUserName().equals(userName) && user.getPassword().equals(userPassWord)){
                    DatabaseOfUsers.currentUser = user;
                    DatabaseOfAuctions.loadObjects();
                    Main main = new Main();
                    main.changeScene("/GUI/AdminMainScreen.fxml");
                    return;
                }
                if(user instanceof PremiumUser && user.getUserName().equals(userName) && user.getPassword().equals(userPassWord)){
                    DatabaseOfUsers.currentUser = user;
                    DatabaseOfAuctions.loadObjects();
                    Main main = new Main();
                    main.changeScene("/GUI/MainScreen.fxml");
                    return;
                }


//                if(user.getUserName().equals("Patrik") && user.getPassWord().equals(userPassWord)){
//                    DatabaseOfUsers.currentUser = user;
//                    Main main = new Main();
//                    main.changeScene("/GUI/AdminMainScreen.fxml");
//                    return;
//                }

                if(user.getUserName().equals(userName) && user.getPassword().equals(userPassWord)){

                    //MainScreenController controller = new MainScreenController();
                    //controller.currentUser = new User("Test", "Test");

//                    DatabaseOfUsers.currentUser = new User("Test", "Test");
                    DatabaseOfUsers.currentUser = user;
                    DatabaseOfAuctions.loadObjects();
//                    controller.displayData();
                    in.close();
                    Main main = new Main();
                    main.changeScene("/GUI/MainScreen.fxml");
//                    controller.displayData();
                    return;
                }
            }
        }
        in.close();
        errorMessage.setText("Name or password is incorrect");




//        //File userData = database.userData;
//        try{
//            Scanner reader = new Scanner(userData);
//            while (reader.hasNextLine()) {
//                String[] data = reader.nextLine().split(" ");
//                String name = data[1];
//                String passWord = data[2];
//
//                passWord.replaceAll("\n", "");
//                if(userName.equals(name) && userPassWord.equals(passWord)){
//                    //System.out.println("User with given name has been found");
//                    // Load new Scene
//                    Main main = new Main();
//                    MainScreenController controller = new MainScreenController();
//                    controller.currentUser = new User("Test", "Test");
//
//                    main.changeScene("/GUI/MainScreen.fxml");
////                    controller.displayData();
////                    changeScene("MainScreen.fxml");
//                }
//            }
//            errorMessage.setText("Name or password is incorrect");
//            reader.close();
//        } catch (IOException e){
//            System.out.println("Error has occurred");
//        }

    }

    public boolean checkDuplicates(String userName) throws IOException, ClassNotFoundException{
        database.loadObjects();
        if(!database.registeredUsers.isEmpty()){
            for(User user : database.registeredUsers){
                if(user.getUserName().equals(userName)){
                    errorMessage.setText("Name is already taken");
                    return false;
                }
            }
        }


//        //File userData = database.userData;
//        Scanner reader = new Scanner(userData);
//        while (reader.hasNextLine()){
//            String[] data = reader.nextLine().split(" ");
//            String name = data[1];
//            if(userName.equals(name)){
//                errorMessage.setText("Name is already taken");
//                return false;
//            }
//        }
        return true;
    }

}
