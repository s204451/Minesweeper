package advanced;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MenuController implements Initializable {
    @FXML
    private TextField widthTextField;
    @FXML
    private TextField heightTextField;
    @FXML
    private TextField mineTextField;
    @FXML
    private Label widthWarning;
    @FXML
    private Label heightWarning;
    @FXML
    private Label minesWarning;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        widthWarning.setVisible(false);
        heightWarning.setVisible(false);
        minesWarning.setVisible(false);

        widthTextField.setTextFormatter(new TextFormatter<>(new IntegerStringConverter()));
        heightTextField.setTextFormatter(new TextFormatter<>(new IntegerStringConverter()));
        mineTextField.setTextFormatter(new TextFormatter<>(new IntegerStringConverter()));
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
            widthWarning.setVisible(false);
            heightWarning.setVisible(false);
            minesWarning.setVisible(false);

            int width = validateDimension(widthTextField, widthWarning, "Width");
            int height = validateDimension(heightTextField, heightWarning, "Height");

            if (width > 0 && height > 0) {
                int mines = validateMines(width, height);
                if (mines > 0) {

                    MinesweeperGame.constructGame(Integer.parseInt(widthTextField.getText()), Integer.parseInt(heightTextField.getText()), Integer.parseInt(mineTextField.getText()));

                    Parent mainRoot = FXMLLoader.load(getClass().getResource("Game.fxml"));
                    Scene mainScene = new Scene(mainRoot);

                    Stage window = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
                    window.setScene(mainScene);
                    window.show();
                }
            }
        }
    }

    public int validateDimension(TextField textField, Label warningLabel, String dim) {

        if (textField.getText().equals("")) {
            warningLabel.setText("*Insert " + dim + "!");
            warningLabel.setVisible(true);
            return 0;
        }
        int input = Integer.parseInt(textField.getText());

        if (input < 4 || input > 100) {
            warningLabel.setText("*" + dim + " must be between 4 - 100");
            warningLabel.setVisible(true);
            return 0;
        }
        return input;
    }

    public int validateMines(int width, int height) {
        if (mineTextField.getText().equals("")) {
            minesWarning.setText("*Insert number of mines!");
            minesWarning.setVisible(true);
            return 0;
        }
        int mines = Integer.parseInt(mineTextField.getText());

        if (mines < 1 || mines > width * height - 9) {
            minesWarning.setText("*Number of mines must be between 1 and " + (width * height - 9));
            minesWarning.setVisible(true);
            return 0;
        }
        return mines;
    }
}

