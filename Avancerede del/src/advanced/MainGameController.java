package advanced;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainGameController implements Initializable {

    MinesweeperMain main;

    @FXML private StackPane sPane;
    @FXML private Label mineCounter;
    @FXML private Button menuButton;
    @FXML private Button restartButton;
    private GridPane grid;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        MinesweeperGame.setController(this);

        startNewGame();
    }

    public Tile[][] getTiles() {
        Tile[][] tiles = new Tile[MinesweeperGame.getGridHeight()][MinesweeperGame.getGridWidth()];
        for (Node node : grid.getChildren()) {
            int y = GridPane.getRowIndex(node);
            int x = GridPane.getColumnIndex(node);

            tiles[y][x] = (Tile) node;
        }
        return tiles;
    }

    public void stopGame() {
        sPane.setDisable(true);
    }

    public void displayWonText() {
        Text wonText = new Text("You Won!");
        wonText.setFont(new Font("Impact", MinesweeperGame.getGridWidth() * 4));
        wonText.setFill(Color.RED);
        sPane.getChildren().add(wonText);
    }

    public void handleRestartButton(MouseEvent mouseEvent) {
        if (mouseEvent.getButton() == MouseButton.PRIMARY) {
            startNewGame();
        }

    }

    public void goBackToMenuButton(MouseEvent mouseEvent) throws IOException {
        if (mouseEvent.getButton() == MouseButton.PRIMARY) {
            Parent mainMenu = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
            Scene mainMenuScene = new Scene(mainMenu);

            Stage window = (Stage) ((Node)mouseEvent.getSource()).getScene().getWindow();
            window.setScene(mainMenuScene);
            window.show();
        }
    }

    public void startNewGame() {
        sPane.setDisable(false);

        grid = new GridPane();

        Tile[][] tiles = MinesweeperGame.makeTiles();

        // Add tiles to GridPane
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles[i].length; j++) {
                grid.add(tiles[i][j], j, i);
            }
        }
        sPane.getChildren().add(grid);

		MinesweeperGame.restartGame();
        updateCounter(MinesweeperGame.getNMines());
    }

	public void updateCounter(int minesLeft) {
		mineCounter.setText("MINES LEFT: " + minesLeft);
	}
}
