package advanced;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.List;

import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Tile extends StackPane {
	private int x, y;
	private boolean hasMine;
	private boolean isEmpty;
	private Text text;
	private ImageView mine;
	private ImageView flag;
	private Rectangle innerRect;
	private Rectangle outerRect;
	private List<Tile> neighbours;

	// Bjørn
	public Tile(int x, int y) {
		this.x = x;
		this.y = y;

		this.setPrefSize(20, 20);

		// Inner darker rectangle
		innerRect = new Rectangle(this.getPrefWidth() - 1, this.getPrefWidth() - 1);
		innerRect.setFill(Color.rgb(150, 150, 150));
		innerRect.setStroke(Color.BLACK);

		// Outer rectangle which hides the inside rectangle
		outerRect = new Rectangle(this.getPrefWidth() - 2, this.getPrefWidth() - 2);
		outerRect.setFill(Color.rgb(198, 198, 198));
		StackPane.setAlignment(outerRect, Pos.TOP_LEFT);

		this.getChildren().addAll(innerRect, outerRect);

		addFlag();

		this.setOnMouseClicked(e -> handleClicked(e));
	}

	// Bjørn
	private void handleClicked(MouseEvent e) {
		if (e.getButton() == MouseButton.PRIMARY) {
			// Place mines on first click
			if (!MinesweeperGame.hasPlacedMines()) {
				MinesweeperGame.placeMines(x, y);
				MinesweeperGame.addMineCount();
			}

			makeVisible();
			MinesweeperGame.checkForWin();
		} else if (e.getButton() == MouseButton.SECONDARY) {
			if (outerRect.isVisible()) {
				updateFlag();
			}
		}
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public boolean isEmpty() {
		return isEmpty;
	}

	public boolean hasMine() {
		return hasMine;
	}

	public boolean hasFlag() {
		return flag.isVisible();
	}

	public boolean isRectVisible() {
		return outerRect.isVisible();
	}

	public List<Tile> getNeighbours() {
		return neighbours;
	}

	// Mikkel
	public void addFlag() {
		// Have to use getResource in order for jar to work properly
		Image img = new Image(getClass().getResource("Flag.png").toString());
		flag = new ImageView();
		flag.setImage(img);

		flag.setVisible(false);
		this.getChildren().add(flag);
	}

	// Place/remove flag and update minecounter
	public void updateFlag() {
		flag.setVisible(!flag.isVisible());
		MinesweeperGame.countMinesLeft(flag.isVisible());
	}

	// Aryan
	public void displayWrongFlag() {
		// Draws 'X' when flag is placed wrong
		if (mine == null && flag.isVisible()) {
			Line line1 = new Line(3, 3, this.getWidth() - 5, this.getHeight() - 5);
			Line line2 = new Line(this.getWidth() - 5, 3, 3, this.getHeight() - 5);
			line1.setStroke(Color.RED);
			line2.setStroke(Color.RED);
			line1.setStrokeWidth(4);
			line2.setStrokeWidth(4);
			this.getChildren().addAll(line1, line2);
		}
	}

	// Bjørn
	// Opens a tile if it is not already
	public void makeVisible() {
		if (outerRect.isVisible() && !flag.isVisible()) {
			outerRect.setVisible(false);
			// If mine is pressed
			if (mine != null) {
				mine.setVisible(true);
				if (!MinesweeperGame.isGameOver()) {
					innerRect.setFill(Color.RED);
					MinesweeperGame.gameOver();
				}
			}
			// Open surrounding tiles if tile is blank
			if (isEmpty && !MinesweeperGame.isGameOver()) {
				MinesweeperGame.revealNonMines(x, y);
			}
			if (text != null)
				text.setVisible(true);
		}
	}

	// Mikkel
	public void addMine() {
		hasMine = true;

		Image img = new Image(getClass().getResource("Mine.png").toString());

		mine = new ImageView();
		mine.setImage(img);

		mine.setVisible(false);

		this.getChildren().add(mine);
	}

	// Jakob
	// Add text on innerRect and change color depending of number of neighbour mines
	public void setMinesNear(int minesNear) {
		if (minesNear > 0) {

			text = new Text("" + minesNear);

			this.getChildren().add(text);

			text.setFont(new Font("Segoe UI Black", 14));

			switch (minesNear) {
			case 1:
				text.setFill(Color.BLUE);
				break;
			case 2:
				text.setFill(Color.GREEN);
				break;
			case 3:
				text.setFill(Color.RED);
				break;
			case 4:
				text.setFill(Color.DARKBLUE);
				break;
			case 5:
				text.setFill(Color.DARKRED);
				break;
			case 6:
				text.setFill(Color.TEAL);
				break;
			case 7:
				text.setFill(Color.BLACK);
				break;
			case 8:
				text.setFill(Color.PURPLE);
				break;
			}

			text.setVisible(false);
		} else {
			isEmpty = true;
		}
	}

	public void setNeighbours(List<Tile> neighbours) {
		this.neighbours = neighbours;
	}

}
