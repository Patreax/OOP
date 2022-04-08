package Controllers;

import Models.Auctions.AbsoluteAuction;
import Models.Auctions.AuctionType;
import Models.Auctions.SealedBidAuction;
import Models.Cars.Car;
import Models.Cars.ElectricCar;
import Models.Cars.HybridCar;
import Models.Cars.StandardCar;
import Models.Exceptions.NoSelectedBoxException;
import Models.Exceptions.TooManySelectedBoxesException;
import Models.Users.Admin;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;


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
    @FXML
    private CheckBox absoluteAuctionCheckBox;
    @FXML
    private CheckBox sealedBidAuctionCheckBox;

    ArrayList<CheckBox> arrayOfCheckBoxes = new ArrayList<>();

    public void create() throws IOException {

        try{
            checkBoxes();

            AuctionType auctionType;
            Car newCar;
            // Deciding whether the car is sta
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
            // Deciding the type of auction
            if(absoluteAuctionCheckBox.isSelected())
                auctionType = new AbsoluteAuction();
            else if (sealedBidAuctionCheckBox.isSelected())
                auctionType = new SealedBidAuction();
            else
                throw new NoSelectedBoxException();

            // Marking the auction as premium
            if(premium.isSelected())
                Admin.createAuction(newCar, true, auctionType);
            else
                Admin.createAuction(newCar, false, auctionType);
            errorMessage.setText("Auction created");
        } catch (NumberFormatException e){
            errorMessage.setText("Enter valid data");
        } catch (TooManySelectedBoxesException e){
            errorMessage.setText("Select only one box");
        } catch (NoSelectedBoxException e){
            errorMessage.setText("Select one box");
        }

        Stage stage = (Stage) createAuctionButton.getScene().getWindow();
        stage.close();
    }

    private void checkBoxes() throws TooManySelectedBoxesException, NoSelectedBoxException{
        // Checking the checkBoxes and throwing exceptions if needed
        arrayOfCheckBoxes.add(standardCarBox);
        arrayOfCheckBoxes.add(electricCarBox);
        arrayOfCheckBoxes.add(hybridCarBox);

        int checkedBoxes = 0;
        for(CheckBox box : arrayOfCheckBoxes){
            if(box.isSelected())
                checkedBoxes++;
        }

        if(checkedBoxes >= 2)
            throw new TooManySelectedBoxesException();

        if(checkedBoxes == 0)
            throw new NoSelectedBoxException();
    }

    public void back(ActionEvent actionEvent){  // closing the window
        Node n = (Node) actionEvent.getSource();
        Stage stage = (Stage) n.getScene().getWindow();
        stage.close();
    }
}
