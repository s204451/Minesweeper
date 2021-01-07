
public class MinesweeperGame {
	private int gridWidth;
	private int gridHeight;
	private int nMines;

	public MinesweeperGame() {
		this.gridWidth = 10;
		this.gridHeight = 10;
		this.nMines = 10;
	}

	public MinesweeperGame(int gridWidth, int gridHeight, int nMines) {
		this.gridWidth = gridWidth;
		this.gridHeight = gridHeight;
		this.nMines = nMines;
	}

	public int getGridWidth() {
		return gridWidth;
	}

	public int getGridHeight() {
		return gridHeight;
	}

	public int getnMines() {
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
			if (!tiles[x][y].hasMine()) {
				tiles[x][y].addMine();
			} else {
				i--;
			}
		}
		
		return tiles;
	}

	public void countMines(Tile[][] tiles, int x, int y) {
		if (tiles[x][y].hasMine()) return;
		
		int count = 0;
		for (int i = -1; i <= 1; i++) {
			for (int j = -1; j <= 1; j++) {
				if (!(i == 0 && j == 0)) {
					int currentX = x + i;
					int currentY = y + j;
					if (currentX >= 0 && currentX < gridWidth && currentY >= 0 && currentY < gridHeight) {
						if (tiles[currentX][currentY].hasMine())
							count++;
					}
				}
			}
		}
		tiles[x][y].setMinesNear(count);
	}
	

}
