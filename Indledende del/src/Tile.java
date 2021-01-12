import javafx.geometry.Pos;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Tile extends StackPane {
	private boolean hasMine;
	private boolean hasFlag;
	private Text text;
	private Ellipse mine;
	private Ellipse flag;
	private Rectangle innerRect;
	private Rectangle outerRect;

	public Tile() {
		this.setPrefSize(20, 20);

		innerRect = new Rectangle(this.getPrefWidth() - 1, this.getPrefWidth() - 1);
		innerRect.setFill(Color.rgb(150, 150, 150));
		innerRect.setStroke(Color.BLACK);

		outerRect = new Rectangle(this.getPrefWidth() - 2, this.getPrefWidth() - 2);
		outerRect.setFill(Color.rgb(198, 198, 198));
		StackPane.setAlignment(outerRect, Pos.TOP_LEFT);

		this.getChildren().addAll(innerRect, outerRect);

		addFlag();

		this.setOnMouseClicked(e -> handleClicked(e));
	}

	private void handleClicked(MouseEvent e) {
		if (e.getButton() == MouseButton.PRIMARY) {
			makeVisible();
			MinesweeperGame.checkForWin();
		} else if (e.getButton() == MouseButton.SECONDARY) {
			if (outerRect.isVisible()) {
				flag.setVisible(!flag.isVisible());
			}
		}
	}

	public boolean hasMine() {
		return hasMine;
	}
	
	
	public boolean isRectVisible() {
		return outerRect.isVisible();
	}

	public void addFlag() {
		flag = new Ellipse(this.getPrefWidth() * 0.5, this.getPrefHeight() * 0.5, this.getPrefWidth() * 0.25,
				this.getPrefHeight() * 0.45);
		flag.setFill(Color.rgb(0, 143, 15));
		flag.setVisible(false);
		this.getChildren().add(flag);
		hasFlag = true;

	}
	
	public void displayWrongFlag() {
		if(mine == null && hasFlag){
			flag.setFill(Color.RED);
		}
	}

	public void makeVisible() {
		if (outerRect.isVisible() && !flag.isVisible()) {
			outerRect.setVisible(false);
			if (mine != null) {
				mine.setVisible(true);
				if (!MinesweeperGame.isGameOver()) {
					innerRect.setFill(Color.RED);
					MinesweeperGame.gameOver();
				}
			}
			if (text != null)
				text.setVisible(true);
		}
	}

	public void addMine() {
		hasMine = true;

		mine = new Ellipse(this.getPrefWidth() * 0.5, this.getPrefHeight() * 0.5, this.getPrefWidth() * 0.3,
				this.getPrefHeight() * 0.3);

		mine.setVisible(false);

		this.getChildren().add(mine);
	}
	
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
		}
	}
	
}
