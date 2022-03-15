package Project.sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import Project.*;
//import javafx.scene.control.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import Project.MainScreen.*;
import javafx.stage.Stage;

import static Project.sample.Main.stg;


public class Controller {

    DatabaseOfUsers database = new DatabaseOfUsers();
    File userData = database.userData;

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

    public void logIn() throws IOException{
        checkData(userNameField.getText(), userPasswordField.getText());
    }

    public void register() throws IOException{
        if(checkDuplicates(userNameField.getText())){
            User newUser = new User(userNameField.getText(), userPasswordField.getText());
            System.out.println("User ID: " + newUser.userId);
            database.storeData(newUser.userId ,userNameField.getText(), userPasswordField.getText());
            database.registeredUsers.add(newUser);
            errorMessage.setText("User has been registered");
        }

    }

    private void checkData(String userName, String userPassWord){
        //File userData = database.userData;
        try{
            Scanner reader = new Scanner(userData);
            while (reader.hasNextLine()) {
                String[] data = reader.nextLine().split(" ");
                String name = data[1];
                String passWord = data[2];

                passWord.replaceAll("\n", "");
                if(userName.equals(name) && userPassWord.equals(passWord)){
                    System.out.println("User with given name has been found");
                    // Load new Scene
                    Main main = new Main();
                    main.changeScene("MainScreen.fxml");
//                    changeScene("MainScreen.fxml");
                }
            }
            errorMessage.setText("Name or password is incorrect");
            reader.close();
        } catch (IOException e){
            System.out.println("Error has occurred");
        }

    }

    public boolean checkDuplicates(String userName) throws IOException{
        //File userData = database.userData;
        Scanner reader = new Scanner(userData);
        while (reader.hasNextLine()){
            String[] data = reader.nextLine().split(" ");
            String name = data[1];
            if(userName.equals(name)){
                errorMessage.setText("Name is already taken");
                return false;
            }
        }
        return true;
    }

}
