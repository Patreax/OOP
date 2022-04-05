package GUI;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;


public class AboutScreen extends Stage {
    private Label label = new Label("Thank you for using EasyCarBid\t\t\t");
    private Button backButton = new Button("Back");

    public AboutScreen() {
        super();

        setTitle("About");

        FlowPane pane = new FlowPane();

        pane.getChildren().add(label);
        pane.getChildren().add(backButton);

        backButton.setOnAction(e -> close());

        setScene(new Scene(pane, 500, 300));
        show();
    }
}
