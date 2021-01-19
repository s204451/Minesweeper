package advanced;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;

public class MinesweeperMain extends Application {

	public static void main(String[] args) {
		launch();
	}

	// Aryan
	// Creates and displays a scene for the main menu
	@Override
	public void start(Stage primaryStage) throws Exception {

		primaryStage.setTitle("Minesweeper");
		primaryStage.setResizable(false);
		Image logo = new Image(getClass().getResource("Flag.png").toString());
		primaryStage.getIcons().add(logo);

		Parent menu = FXMLLoader.load(getClass().getResource("Menu.fxml"));
		Scene menuScene = new Scene(menu);
		
		primaryStage.setScene(menuScene);
		primaryStage.show();
	}
}