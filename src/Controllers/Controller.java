package Controllers;

import Models.DatabaseOfUsers;
import Models.User;
import Project.sample.Main;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
        checkData(userNameField.getText(), userPasswordField.getText());
        //mainController.currentUser = new User(userNameField.getText(), userPasswordField.getText());
    }

    public void register() throws IOException, ClassNotFoundException{
        if(checkDuplicates(userNameField.getText())){
            User newUser = new User(userNameField.getText(), userPasswordField.getText());
//            database.storeData(newUser.getUserId(), userNameField.getText(), userPasswordField.getText());
//            database.registeredUsers.add(newUser);
            database.storeObject(newUser);
            errorMessage.setText("User has been registered");
        }
    }

    private void checkData(String userName, String userPassWord) throws IOException, ClassNotFoundException{

        if(database.registeredUsers.isEmpty()){
            errorMessage.setText("User does not exist");
            return;
        }


        ObjectInputStream in = new ObjectInputStream(new FileInputStream(userData));
        if(!database.registeredUsers.isEmpty()){
            for(User user : (ArrayList<User>)in.readObject()){
                if(user.getUserName().equals(userName) && user.getPassWord().equals(userPassWord)){
                    Main main = new Main();
                    main.changeScene("/GUI/MainScreen.fxml");
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
