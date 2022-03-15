package Project.MainScreen;

import Project.sample.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class MainScreenController {
    public MainScreenController(){

    }

    @FXML
    private Button logOutButton;

    public void logOut() throws IOException {
        Main main = new Main();
        main.changeScene("sample.fxml");
    }
}
