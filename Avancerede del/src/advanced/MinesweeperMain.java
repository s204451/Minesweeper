package advanced;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;


public class MinesweeperMain extends Application {

	public static void main(String[] args) {
		try {
			createGame(args);
			launch();
		} catch (IllegalArgumentException e) {
			System.out.println("\nFail: " + e.getMessage());
			System.exit(0);
		}
	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		MinesweeperGame.setMain(this);
		Parent mainRoot = FXMLLoader.load(getClass().getResource("MainGame.fxml"));

		primaryStage.setTitle("Minesweeper");
		primaryStage.setResizable(false);

		Scene mainScene = new Scene(mainRoot);

		primaryStage.setScene(mainScene);

		primaryStage.show();
	}

	// Validates arguments and constructs game
	public static void createGame(String[] args) throws IllegalArgumentException {
		int width, height, mines;
		
		try {
			if (args.length != 3) {
				throw new IllegalArgumentException("Arguments must be on format: width (4 to 100), height (4 to 100), mines (1 to area - 9).");
			}

			width = Integer.parseInt(args[0]);
			height = Integer.parseInt(args[1]);
			mines = Integer.parseInt(args[2]);
			if (width < 4 || width > 100 || height < 4 || height > 100 || mines < 1 || mines > width * height - 9) {
				throw new IllegalArgumentException("Arguments must be on format: width (4 to 100), height (4 to 100), mines (1 to area - 9).");
			}
			
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException("Arguments must be integers!");
		}
		
		MinesweeperGame.constructGame(width, height, mines);
	}

}
