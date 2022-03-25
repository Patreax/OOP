package Controllers;

import Models.DatabaseOfUsers;
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
    public Label userIDLabel;
    @FXML
    public Label userNameLabel;
    @FXML
    public Label userCurrencyLabel;

    public MainScreenController(){
        Platform.runLater(() -> displayData());
//        Platform.runLater(this::displayData);
    }

    public void displayData(){
        userCurrencyLabel.setText("1000");
        userNameLabel.setText(DatabaseOfUsers.currentUser.getUserName());
        userIDLabel.setText(Long.toString(DatabaseOfUsers.currentUser.getUserId()));
    }

    public void logOut() throws IOException {
        DatabaseOfUsers.currentUser = null;
        Main main = new Main();
        main.changeScene("/GUI/sample.fxml");
    }

    public void newWindow() throws IOException{
        Main main = new Main();
        main.openNewWindow("/GUI/AuctionCreator.fxml");
    }

}
