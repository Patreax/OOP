package Project.Main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * The start of the program
 */
public class Main extends Application {

    public static Stage stg;
    public static Main mainInstance = null;

    public Main() {
        mainInstance = this;
    }

    /**
     * @param primaryStage
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        stg = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("../../GUI/LogInScreen.fxml"));
        primaryStage.setTitle("EasyCarBid");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }

    public void changeScene(String fxml) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(fxml));
        Stage window = (Stage) stg.getScene().getWindow();
        window.setScene(new Scene(root, 600, 400));
    }

    public void openNewWindow(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxml));
        Parent root = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }

    /**
     * Main Function
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
    }
}
