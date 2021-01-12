
public final class MinesweeperGame {
	private static int gridWidth;
	private static int gridHeight;
	private static int nMines;
	private static boolean gameOver;
	private static MinesweeperMain main;

	public static void constructGame(int gridWidth_, int gridHeight_, int nMines_) {
		gridWidth = gridWidth_;
		gridHeight = gridHeight_;
		nMines = nMines_;
	}

	public static void setMain(MinesweeperMain main_) {
		main = main_;
	}

	public static boolean isGameOver() {
		return gameOver;
	}

	public static int getGridWidth() {
		return gridWidth;
	}

	public static int getGridHeight() {
		return gridHeight;
	}

	public static Tile[][] makeTiles() {
		// Initialize tiles
		Tile[][] tiles = new Tile[gridHeight][gridWidth];
		for (int i = 0; i < tiles.length; i++) {
			for (int j = 0; j < tiles[i].length; j++) {
				tiles[i][j] = new Tile();
			}
		}

		// Place mines
		for (int i = 0; i < nMines; i++) {
			int x = (int) (Math.random() * (gridWidth));
			int y = (int) (Math.random() * (gridHeight));
			if (!tiles[y][x].hasMine()) {
				tiles[y][x].addMine();
			} else {
				i--;
			}
		}

		return tiles;
	}

	public static void addMineCount(Tile[][] tiles) {
		for (int y = 0; y < tiles.length; y++) {
			for (int x = 0; x < tiles[y].length; x++) {
				if (!tiles[y][x].hasMine()) {

					int count = 0;
					for (int i = -1; i <= 1; i++) {
						for (int j = -1; j <= 1; j++) {
							if (!(i == 0 && j == 0)) {
								int currentX = x + i;
								int currentY = y + j;
								if (currentX >= 0 && currentX < gridWidth && currentY >= 0 && currentY < gridHeight) {
									if (tiles[currentY][currentX].hasMine())
										count++;
								}
							}
						}
					}
					tiles[y][x].setMinesNear(count);
				}
			}
		}
	}

	public static void checkForWin() {
		if (!gameOver) {
			Tile[][] tiles = main.getTiles();
			int counter = 0;
			for (int i = 0; i < tiles.length; i++) {
				for (int j = 0; j < tiles[i].length; j++) {
					if (tiles[i][j].isRectVisible() && !tiles[i][j].hasMine()) {
						counter++;
					}
				}
			}
			if (counter == 0) {
				main.displayWonText();
				main.stopGame();
			}
		}

	}

	public static void gameOver() {
		gameOver = true;

		Tile[][] tiles = main.getTiles();
		for (int i = 0; i < tiles.length; i++) {
			for (int j = 0; j < tiles[i].length; j++) {
				tiles[i][j].makeVisible();
				tiles[i][j].displayWrongFlag();

			}
		}
		main.stopGame();
	}

}
