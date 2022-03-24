package Controllers;

import Models.DatabaseOfUsers;
import Models.User;
import Project.sample.Main;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;

public class MainScreenController {

    @FXML
    private Button logOutButton;
    @FXML
    private Button showDataButton;
    @FXML
    private Label userIDLabel;
    @FXML
    public Label userNameLabel;

    public MainScreenController(){
        System.out.println("Controller created");
//        displayData();
//        this.userNameLabel.setText(DatabaseOfUsers.currentUser.getUserName());
        Platform.runLater(() -> userNameLabel.setText(DatabaseOfUsers.currentUser.getUserName()));
    }

    public void displayData(){
        //MainScreenController controller = new MainScreenController();
        //System.out.println("Meno: " + this.currentUser.getUserName());
//        userNameLabel.setText(this.currentUser.userName);
//        userNameLabel.setText(this.currentUser.userName);
//        userIDLabel.setText(this.currentUser.passWord);

        userNameLabel.setText(DatabaseOfUsers.currentUser.getUserName());

//        Platform.runLater(() -> userNameLabel.setText(currentUser.getUserName()));
    }



    public void logOut() throws IOException {
        Main main = new Main();
        main.changeScene("/GUI/sample.fxml");
    }
}
