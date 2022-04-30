package Controllers;

import Models.Databases.DatabaseOfUsers;
import Models.Exceptions.TooMuchCurrencyException;
import Models.Users.Customer;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * The <code>WalletController</code> class act as a controller for the Wallet.fxml file
 * It is responsible for adding in the currency and exchanging them for bids (special currency)
 * It is working with {@link Models.Wallet} assigned to each object deriving from {@link Customer} class
 */
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


    public WalletController() {
        Platform.runLater(this::displayData);
    }

    /**
     * Displays data about current user's currency and bids
     */
    private void displayData() {
        Customer currentCustomer = (Customer) DatabaseOfUsers.currentUser;
        this.currencyLabel.setText(Double.toString(currentCustomer.getWallet().getCurrency()));
        this.bidsLabel.setText(Double.toString(currentCustomer.getWallet().getBids()));
    }

    /**
     * Closes the current window
     *
     * @param actionEvent
     */
    public void back(ActionEvent actionEvent) {      // Closing the window
        Node n = (Node) actionEvent.getSource();
        Stage stage = (Stage) n.getScene().getWindow();
        stage.close();
    }

    /**
     * Checks if the current user has enough currency and exchanges them afterwards
     */
    public void exchangeCurrency() {
        Customer currentCustomer = (Customer) DatabaseOfUsers.currentUser;
        errorLabel.setText("");

        try {
//            if (Double.parseDouble(exchangeField.getText()) > currentCustomer.getWallet().getCurrency()) {
//                errorLabel.setText("Not enough currency");
//                return;
//            }
            currentCustomer.getWallet().exchange(Double.parseDouble(exchangeField.getText()));
            displayData();
            MainScreenController.mainScreenControllerInstance.userCurrencyLabel.setText(Double.toString(currentCustomer.getWallet().getBids()));

        } catch (NumberFormatException e) {
            errorLabel.setText("Wrong format");
        } catch (TooMuchCurrencyException e) {
            errorLabel.setText("Not enough currency");
            System.out.println("TooMuchCurrencyException");
            exchangeField.setText(Double.toString(currentCustomer.getWallet().getCurrency()));
        }
    }

    /**
     * Add amount of currency the user desires to current currency count
     */
    public void addCurrency() {
        Customer currentCustomer = (Customer) DatabaseOfUsers.currentUser;
        currentCustomer.getWallet().addCurrency(Double.parseDouble(addCurrencyField.getText()));
        displayData();
    }
}
