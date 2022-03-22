package Controllers;

import Models.User;
import Project.sample.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;

public class MainScreenController {

    public User currentUser;

    public MainScreenController(){

    }


    @FXML
    private Button logOutButton;
    @FXML
    private Button showDataButton;
    @FXML
    private Label userIDLabel;
    @FXML
    public Label userNameLabel;


    public void displayData(){
        //MainScreenController controller = new MainScreenController();
        System.out.println("Meno: " + this.currentUser.getUserName());
//        userNameLabel.setText(this.currentUser.userName);
//        userNameLabel.setText(this.currentUser.userName);
//        userIDLabel.setText(this.currentUser.passWord);
    }



    public void logOut() throws IOException {
        Main main = new Main();
        main.changeScene("sample.fxml");
    }
}
