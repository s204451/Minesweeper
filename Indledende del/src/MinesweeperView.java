import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class MinesweeperView extends Application {

	private GridPane gPane;
	private MinesweeperModel model;

	public static void main(String[] args) {
		launch();
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		model = new MinesweeperModel(10, 10, 10);
		MinesweeperController controller = new MinesweeperController(model);

		primaryStage.setTitle("Minesweeper");

		gPane = new GridPane();
		gPane.setPrefSize(800, 800);

		addTiles();

		Scene scene = new Scene(gPane, 800, 800);
		primaryStage.setScene(scene);

		primaryStage.show();
	}

	public void addTiles() {
		Tile[][] tiles = model.makeTiles();

		for (int i = 0; i < tiles.length; i++) {
			for (int j = 0; j < tiles[i].length; j++) {
				model.countMines(tiles, i, j);
			}
		}
		
		// Add tiles to GridPane
		for (int i = 0; i < tiles.length; i++) {
			for (int j = 0; j < tiles[i].length; j++) {
				gPane.add(tiles[i][j], j, i);
			}
		}
	}
}
