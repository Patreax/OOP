package Controllers;

import Models.Cars.Car;
import Models.Databases.DatabaseOfUsers;
import Models.Users.Customer;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * The <code>GarageController</code> class act as a controller for the Garage.fxml file
 * It is responsible for showing all cars owned by current user and displaying information about these cars
 * It is working with {@link Models.PersonalGarage} assigned to each object deriving from {@link Customer} class
 */
public class GarageController {

    @FXML
    private TextArea textArea;
    @FXML
    private TextField carIdField;
    @FXML
    private Label userIdLabel;
    @FXML
    private Label userNameLabel;
    @FXML
    private Label numberOfCarsLabel;

    public GarageController() {
        Platform.runLater(() -> {
            displayData();
            numberOfCarsLabel.setText(String.valueOf(countCars()));
        });
    }

    /**
     * Displays data about current user
     */
    private void displayData() {
        Customer currentCustomer = (Customer) DatabaseOfUsers.currentUser;
        userNameLabel.setText(DatabaseOfUsers.currentUser.getUserName());
        userIdLabel.setText(Long.toString(DatabaseOfUsers.currentUser.getUserId()));
    }

    /**
     * Displays all the current user's cars
     */
    public void showCars() {
        textArea.setText("");
        Customer currentUser = (Customer) DatabaseOfUsers.currentUser;
        if (currentUser.getGarage().getCars().isEmpty())
            textArea.appendText("You do not own any cars at the moment\n");
        int i = 1;
        for (Car car : currentUser.getGarage().getCars()) {
            textArea.appendText(i + ": " + car.getBrand() + " " + car.getModel() + "\n");
            i++;
        }
    }

    /**
     * Displays all the information about given car
     */
    public void info() {
        int index = Integer.parseInt(carIdField.getText());
        if (index <= 0) {
            textArea.setText("Car with index of " + index + " does not exist");
            return;
        }
        int i = 1;
        Customer currentUser = (Customer) DatabaseOfUsers.currentUser;
        for (Car car : currentUser.getGarage().getCars()) {
            if (i == index) {
                textArea.setText("");
                textArea.appendText("Car Brand: " + car.getBrand() + "\n");
                textArea.appendText("Car Model: " + car.getModel() + "\n");
                textArea.appendText("Car Year: " + car.getYear() + "\n");
                textArea.appendText("Car kilometers: " + car.getKilometers() + " km \n");
                textArea.appendText("Car Engine: " + car.getEngine() + "L \n");
                textArea.appendText("Engine Power: " + car.getPower() + "KW \n");
                textArea.appendText("Car Fuel: " + car.getFuel() + "\n");
                textArea.appendText("Car transmission: " + car.getTransmission() + "\n");
                textArea.appendText("Car Color: " + car.getColor() + "\n");
                return;
            }
            i++;
        }
        textArea.setText("Car with given number does not exist");
    }

    /**
     * Closes current window
     *
     * @param actionEvent
     */
    public void close(ActionEvent actionEvent) {      // Closing the window
        Node n = (Node) actionEvent.getSource();
        Stage stage = (Stage) n.getScene().getWindow();
        stage.close();
    }

    /**
     * Counts all the current user's cars
     *
     * @return
     */
    private int countCars() {
        int numberOfCars = 0;
        Customer currentUser = (Customer) DatabaseOfUsers.currentUser;
        for (Car car : currentUser.getGarage().getCars()) {
            numberOfCars++;
        }
        return numberOfCars;
    }

}
