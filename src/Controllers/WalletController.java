package Controllers;

import Models.Users.Customer;
import Models.Databases.DatabaseOfUsers;
import Models.Users.StandardUser;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class WalletController {

    @FXML
    private Label currencyLabel;
    @FXML
    private Label bidsLabel;
    @FXML
    private Label errorLabel;
    @FXML
    private TextField exchangeField;
    @FXML
    private TextField addCurrencyField;


    public WalletController(){
        Platform.runLater(this::displayData);
    }

    private void displayData(){
        StandardUser currentCustomer = (StandardUser) DatabaseOfUsers.currentUser;
        this.currencyLabel.setText(Double.toString(currentCustomer.getWallet().getCurrency()));
        this.bidsLabel.setText(Double.toString(currentCustomer.getWallet().getBids()));
    }

    public void back(ActionEvent actionEvent){      // Closing the window
        Node n = (Node) actionEvent.getSource();
        Stage stage = (Stage) n.getScene().getWindow();
        stage.close();
    }

    public void exchangeCurrency(){
        Customer currentCustomer = (Customer) DatabaseOfUsers.currentUser;
        currentCustomer.getWallet().exchange(Double.parseDouble(exchangeField.getText()));
        displayData();
    }

    public void addCurrency(){
        Customer currentCustomer = (Customer) DatabaseOfUsers.currentUser;
        currentCustomer.getWallet().addCurrency(Double.parseDouble(addCurrencyField.getText()));
        displayData();
    }
}
