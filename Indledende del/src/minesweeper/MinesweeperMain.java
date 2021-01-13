package src.minesweeper;

import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class MinesweeperMain extends Application {

	private StackPane root;
	private GridPane gPane;

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
	public void start(Stage primaryStage) {
		
		MinesweeperGame.setMain(this);

		primaryStage.setTitle("Minesweeper");
		primaryStage.setResizable(false);

		root = new StackPane();
		gPane = new GridPane();
		int canvasWidth = 20 * MinesweeperGame.getGridWidth();
		int canvasHeight = 20 * MinesweeperGame.getGridHeight();
		gPane.setPrefSize(canvasWidth, canvasHeight);
		
		root.getChildren().add(gPane);

		addTiles();

		Scene scene = new Scene(root, canvasWidth, canvasHeight);
		primaryStage.setScene(scene);

		primaryStage.show();
	}
	
	public Tile[][] getTiles() {
		Tile[][] tiles = new Tile[MinesweeperGame.getGridHeight()][MinesweeperGame.getGridWidth()];
		for (Node node : gPane.getChildren()) {
			int y = GridPane.getRowIndex(node);
			int x = GridPane.getColumnIndex(node);
			
			tiles[y][x] = (Tile) node;
		}
		return tiles;
	}

	public void addTiles() {
		Tile[][] tiles = MinesweeperGame.makeTiles();

		MinesweeperGame.addMineCount(tiles);

		// Add tiles to GridPane
		for (int i = 0; i < tiles.length; i++) {
			for (int j = 0; j < tiles[i].length; j++) {
				gPane.add(tiles[i][j], j, i);
			}
		}
	}
	
	// Validates arguments and constructs game
	public static void createGame(String[] args) throws IllegalArgumentException {
		int width, height, mines;
		
		try {
			if (args.length != 3) {
				throw new IllegalArgumentException("Arguments must be on format: width (4 to 100), height (4 to 100), mines (1 to area - 1).");
			}

			width = Integer.parseInt(args[0]);
			height = Integer.parseInt(args[1]);
			mines = Integer.parseInt(args[2]);
			if (width < 4 || width > 100 || height < 4 || height > 100 || mines < 1 || mines >= width * height) {
				throw new IllegalArgumentException("Arguments must be on format: width (4 to 100), height (4 to 100), mines (1 to area - 1).");
			}
			
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException("Arguments must be integers!");
		}
		
		MinesweeperGame.constructGame(width, height, mines);
	}
	
	public void stopGame() {
		root.setDisable(true);
	}
	
	public void displayWonText(){
		Text wonText = new Text("You Won!");
		wonText.setFont(new Font("Impact", MinesweeperGame.getGridWidth() * 4));
		wonText.setFill(Color.RED);
		root.getChildren().add(wonText);
	}

}
