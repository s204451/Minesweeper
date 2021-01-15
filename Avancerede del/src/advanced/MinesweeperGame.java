package advanced;

import java.util.ArrayList;
import java.util.List;

public final class MinesweeperGame {
    private static int gridWidth;
    private static int gridHeight;
    private static int nMines;
    private static int minesLeft;
    private static boolean gameOver;
    private static boolean hasPlacedMines;
    private static MinesweeperMain main;
    private static MainGameController controller;

    public static void constructGame(int gridWidth, int gridHeight, int nMines) {
        MinesweeperGame.gridWidth = gridWidth;
        MinesweeperGame.gridHeight = gridHeight;
        MinesweeperGame.nMines = nMines;
    }

    public static void restartGame() {
        hasPlacedMines = false;
        gameOver = false;
        minesLeft = nMines;
    }

    public static void setMain(MinesweeperMain main) {
        MinesweeperGame.main = main;
    }

    public static void setController(MainGameController controller) {
        MinesweeperGame.controller = controller;
    }

    public static int getNMines() {
        return nMines;
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


    public static boolean hasPlacedMines() {
        return hasPlacedMines;
    }


    // Initialize tiles
    public static Tile[][] makeTiles() {
        Tile[][] tiles = new Tile[gridHeight][gridWidth];
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles[i].length; j++) {
                tiles[i][j] = new Tile(j, i);
            }
        }

        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles[i].length; j++) {
                setTileNeighbours(tiles, j, i);
            }
        }

        return tiles;
    }

    public static void placeMines(int pressedX, int pressedY) {
        Tile[][] tiles = controller.getTiles();

        // Place mines
        for (int n = 0; n < nMines; n++) {
            int x = (int) (Math.random() * (gridWidth));
            int y = (int) (Math.random() * (gridHeight));

            // All surrounding mines from first pressed tile can't have a mine
            if (!tiles[y][x].hasMine() && !(Math.abs(x - pressedX) <= 1 && Math.abs(y - pressedY) <= 1)) {
                tiles[y][x].addMine();
            } else {
                n--;
            }
        }

        hasPlacedMines = true;
    }

    public static void addMineCount() {
        Tile[][] tiles = controller.getTiles();

        for (int y = 0; y < tiles.length; y++) {
            for (int x = 0; x < tiles[y].length; x++) {
                if (!tiles[y][x].hasMine()) {

                    int count = 0;

                    List<Tile> neighbours = tiles[y][x].getNeighbours();
                    for (Tile neighbour : neighbours) {
                        if (neighbour.hasMine())
                            count++;
                    }
                    tiles[y][x].setMinesNear(count);
                }
            }
        }
    }

    public static void setTileNeighbours(Tile[][] tiles, int x, int y) {
        List<Tile> neighbours = new ArrayList<Tile>();

        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (!(i == 0 && j == 0)) {
                    int currentX = x + i;
                    int currentY = y + j;
                    if (currentX >= 0 && currentX < gridWidth && currentY >= 0 && currentY < gridHeight) {
                        neighbours.add(tiles[currentY][currentX]);
                    }
                }
            }
        }
        tiles[y][x].setNeighbours(neighbours);
    }

    public static void revealNonMines(int x, int y) {

        Tile[][] tiles = controller.getTiles();
        List<Tile> neighbours = tiles[y][x].getNeighbours();
        for (Tile neighbour : neighbours) {
            // If tile is not opened
            if (neighbour.isRectVisible()) {
                if (neighbour.hasFlag()) {
                    neighbour.updateFlag();
                }
                neighbour.makeVisible();
            }
        }
    }

    public static void countMinesLeft(boolean isFlagged) {
        minesLeft += isFlagged ? -1 : 1;
        controller.updateCounter(minesLeft);
    }

    public static void checkForWin() {
        if (!gameOver) {
            Tile[][] tiles = controller.getTiles();
            int counter = 0;
            for (int i = 0; i < tiles.length; i++) {
                for (int j = 0; j < tiles[i].length; j++) {
                    if (tiles[i][j].isRectVisible() && !tiles[i][j].hasMine()) {
                        counter++;
                    }
                }
            }
            if (counter == 0) {
                controller.displayWonText();
                controller.stopGame();
            }
        }

    }

    public static void gameOver() {
        gameOver = true;

        Tile[][] tiles = controller.getTiles();
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles[i].length; j++) {
                tiles[i][j].makeVisible();
                tiles[i][j].displayWrongFlag();

            }
        }
        controller.stopGame();
    }

}
