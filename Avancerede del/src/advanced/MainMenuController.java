package advanced;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainMenuController implements Initializable {
    @FXML private Button easyButton;
    @FXML private Button mediumButton;
    @FXML private Button hardButton;
    @FXML private Button playButton;
    @FXML private Label widthLabel;
    @FXML private Label heightLabel;
    @FXML private Label minesLabel;
    @FXML private Label warningLabel;
    @FXML private TextField widthTextField;
    @FXML private TextField heightTextField;
    @FXML private TextField mineTextField;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        warningLabel.setVisible(false);
    }

    public void setEasyMode(MouseEvent mouseEvent) {
        if (mouseEvent.getButton() == MouseButton.PRIMARY) {
            widthTextField.setText("10");
            heightTextField.setText("10");
            mineTextField.setText("10");
        }
    }

    public void setMediumMode(MouseEvent mouseEvent) {
        if (mouseEvent.getButton() == MouseButton.PRIMARY) {
            widthTextField.setText("16");
            heightTextField.setText("16");
            mineTextField.setText("40");
        }
    }

    public void setHardMode(MouseEvent mouseEvent) {
        if (mouseEvent.getButton() == MouseButton.PRIMARY) {
            widthTextField.setText("30");
            heightTextField.setText("16");
            mineTextField.setText("99");
        }
    }

    public void playGame(MouseEvent mouseEvent) throws IOException {

        if (mouseEvent.getButton() == MouseButton.PRIMARY) {
            Parent mainRoot = FXMLLoader.load(getClass().getResource("MainGame.fxml"));
            Scene mainScene = new Scene(mainRoot);

            Stage window = (Stage) ((Node)mouseEvent.getSource()).getScene().getWindow();
            window.setScene(mainScene);
            window.show();
        }
    }
}
