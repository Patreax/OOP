package sample;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
//import javafx.scene.control.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class Controller {

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

    public void logIn(){
        System.out.println("User has been logged in");

    }
    public void register(){
        System.out.println("User has been registered");
    }

}
