package Project.sample;

import javafx.fxml.FXML;
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


public class Controller {

    DatabaseOfUsers database = new DatabaseOfUsers();

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

    public void register(){
        User newUser = new User(userNameField.getText(), userPasswordField.getText());
        System.out.println("User ID: " + newUser.userId);
        database.storeData(userNameField.getText(), userPasswordField.getText());
        database.registeredUsers.add(newUser);
        for(User user : database.registeredUsers){
            System.out.println(user.userName);
            System.out.println(user.userId);
        }
        System.out.println("User has been registered");
    }

    private void checkData(String userName, String userPassWord){
        File userData = database.userData;
        try{
            Scanner reader = new Scanner(userData);
            while (reader.hasNextLine()) {
                String[] data = reader.nextLine().split(" ");
                String name = data[0];
                String passWord = data[1];
                System.out.println(name);
                System.out.println(passWord);
                System.out.println(userName);
                System.out.println(userPassWord);

//                userName.replaceAll("\n", "");
//                userPassWord.replaceAll("\n", "");
//                name.replaceAll("\n", "");
                passWord.replaceAll("\n", "");
                if(userName.equals(name) && userPassWord.equals(passWord)){
                    System.out.println("User with given name has been found");
                    // Load new Scene
                    Main main = new Main();
                    main.changeScene("MainScreen.fxml");
                }
            }
        } catch (IOException e){
            System.out.println("Error has occurred");
        }

    }

}
