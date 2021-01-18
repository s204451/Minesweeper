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

    @Override
    public void start(Stage primaryStage) throws Exception {

        primaryStage.setTitle("Minesweeper");
        primaryStage.setResizable(false);
        Image logo = new Image("Flag.png");
        primaryStage.getIcons().add(logo);

        Parent mainRoot = FXMLLoader.load(getClass().getResource("Game.fxml"));
        Scene mainScene = new Scene(mainRoot);

        Parent mainMenu = FXMLLoader.load(getClass().getResource("Menu.fxml"));
        Scene mainMenuScene = new Scene(mainMenu);

        primaryStage.setScene(mainMenuScene);
        primaryStage.show();
    }
}