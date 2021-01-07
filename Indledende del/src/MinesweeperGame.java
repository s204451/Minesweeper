
public class MinesweeperGame {
	public static int gridWidth;
	public static int gridHeight;
	public static int nMines;

	public MinesweeperGame(int gridWidth, int gridHeight, int nMines) {
		this.gridWidth = gridWidth;
		this.gridHeight = gridHeight;
		this.nMines = nMines;
	}

	public static int getGridWidth() {
		return gridWidth;
	}

	public static int getGridHeight() {
		return gridHeight;
	}

	public static int getnMines() {
		return nMines;
	}
	
	
	public Tile[][] makeTiles() {
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

	public void countMines(Tile[][] tiles, int x, int y) {
		if (tiles[y][x].hasMine()) return;
		
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
