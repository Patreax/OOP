package Controllers;

import Models.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;


public class AuctionCreator {

    @FXML
    private TextField brand;
    @FXML
    private TextField model;
    @FXML
    private TextField price;
    @FXML
    private TextField year;
    @FXML
    private TextField kilometers;
    @FXML
    private TextField fuel;
    @FXML
    private TextField engine;
    @FXML
    private TextField power;
    @FXML
    private TextField transmission;
    @FXML
    private TextField color;
    @FXML
    private Label errorMessage;
    @FXML
    private Button createAuctionButton;
    @FXML
    private Button backButton;
    @FXML
    private CheckBox premium;
    @FXML
    private CheckBox standardCarBox;
    @FXML
    private CheckBox electricCarBox;
    @FXML
    private CheckBox hybridCarBox;


    CheckBox[] arrayOfCheckBoxes = {standardCarBox, electricCarBox, hybridCarBox};

    public void create() throws IOException {
//        if(brand.getText().equals("") || model.getText().equals("") || price.getText().equals("") || year.getText().equals("") || kilometers.getText().equals("") || fuel.getText().equals("") || engine.getText().equals("") || power.getText().equals("") || transmission.getText().equals("") || color.getText().equals("")){
//            errorMessage.setText("Fill in all the blank spaces");
////            System.out.println("Problem");
//            return;
//        }
//        System.out.println("Ide to");
//        System.out.println(brand.getText() + "\t" + model.getText() + "\t" + price.getText() + "\t" + year.getText() + "\t" + kilometers.getText() + "\t" +
//                fuel.getText() + "\t" + engine.getText() + "\t" + power.getText() +
//                "\t" + transmission.getText() + "\t" + color.getText());

        try{
            Car newCar;
            if(standardCarBox.isSelected()){
                newCar = new StandardCar(brand.getText(), model.getText(), Double.parseDouble(price.getText()), Integer.parseInt(year.getText()));
                System.out.println("Standard");
            }
            else if (electricCarBox.isSelected()){
                newCar = new ElectricCar(brand.getText(), model.getText(), Double.parseDouble(price.getText()), Integer.parseInt(year.getText()));
                System.out.println("Electric");
            }
            else{
                newCar = new HybridCar(brand.getText(), model.getText(), Double.parseDouble(price.getText()), Integer.parseInt(year.getText()));
                System.out.println("Hybrid");
            }


//            Car newCar = new Car(brand.getText(), model.getText(), Double.parseDouble(price.getText()), Integer.parseInt(year.getText()));
            if(premium.isSelected())
                Admin.createAuction(newCar, true);
            else
                Admin.createAuction(newCar, false);
            errorMessage.setText("Auction created");
        } catch (NumberFormatException e){
            errorMessage.setText("Enter valid data");
        }

    }

    public void back(ActionEvent actionEvent){
        Node n = (Node) actionEvent.getSource();
        Stage stage = (Stage) n.getScene().getWindow();
        stage.close();
    }
}
