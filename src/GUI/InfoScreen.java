package GUI;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;


public class InfoScreen extends Stage {
    private Label information = new Label();

    public InfoScreen(String title, String info) {
        super();

        setTitle(title);

        FlowPane pane = new FlowPane();

        pane.getChildren().add(information);
        this.information.setText(info);

        setScene(new Scene(pane, 300, 100));
    }
}
