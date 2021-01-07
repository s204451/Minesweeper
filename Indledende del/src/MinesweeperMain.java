import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

//Example: java MinesweeperMain 10 10 20

public class MinesweeperMain extends Application {

	private GridPane gPane;
	private static MinesweeperGame game;

	public static void main(String[] args) {
		try {
			game = getGame(args);
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
		launch();
	}

	@Override
	public void start(Stage primaryStage) throws Exception {

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
				gPane.add(tiles[i][j], i, j);
			}
		}
	}

	public static MinesweeperGame getGame(String[] args) throws IllegalArgumentException {
		MinesweeperGame game = null;
		int width = Integer.parseInt(args[0]);
		int height = Integer.parseInt(args[1]);
		int mines = Integer.parseInt(args[2]);
		
		try {
			if (width < 0 && height < 0 && mines <= 0) {
				throw new IllegalArgumentException("Arguments must be positive integers");
			}
			} catch (NumberFormatException e) {
				throw new IllegalArgumentException("Arguments must be positive integers");
			}
		game = new MinesweeperGame(width, height, mines);
		return game;
	}

}
