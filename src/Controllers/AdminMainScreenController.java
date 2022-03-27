package Controllers;

import Models.Auction;
import Models.DatabaseOfAuctions;
import Models.DatabaseOfUsers;
import Project.sample.Main;
import javafx.application.Platform;
import javafx.fxml.FXML;

import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import java.io.IOException;

public class AdminMainScreenController {

    @FXML
    private Label userNameLabel;
    @FXML
    private Label userIDLabel;
    @FXML
    private TextArea textArea;

    public AdminMainScreenController(){
        Platform.runLater(() -> displayData());
//        Platform.runLater(this::displayData);
    }

    private void displayData(){
        userNameLabel.setText(DatabaseOfUsers.currentUser.getUserName());
        userIDLabel.setText(Long.toString(DatabaseOfUsers.currentUser.getUserId()));
    }

    public void newWindow() throws IOException {
        Main main = new Main();
        main.openNewWindow("/GUI/AuctionCreator.fxml");
    }

    public void showAuctions() throws IOException, ClassNotFoundException{
//        DatabaseOfAuctions.loadObjects();
//        DatabaseOfAuctions.displayAuctions();
        for(Auction a : DatabaseOfAuctions.auctions){
            textArea.appendText("ID: " + a.auctionId + "\t Brand: " + a.car.brand + "\t Model: " + a.car.model + "\n");
            textArea.appendText("Price: " + a.car.price + "\t Year: " + a.car.year + "\n");
            textArea.appendText("\n");
        }
    }

    public void clear(){
        textArea.setText("");
    }

    public void logOut() throws IOException {
        DatabaseOfUsers.currentUser = null;

        // Resetting auction to prevent duplicates

        DatabaseOfAuctions.auctions.clear();

        // Loading first screen
        Main main = new Main();
        main.changeScene("/GUI/sample.fxml");
    }
}
