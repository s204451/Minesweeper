import javafx.geometry.Pos;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Tile extends StackPane {
	private int minesNear;
	private boolean hasMine;
	private boolean hasFlag;
	private Text text;
	private Ellipse mine;
	private Ellipse flag;
	private Rectangle border;
	private Rectangle rect;

	public Tile() {
		this.setPrefSize(20, 20);

		border = new Rectangle(this.getPrefWidth() - 1, this.getPrefWidth() - 1);
		border.setFill(Color.rgb(150, 150, 150));
		border.setStroke(Color.BLACK);

		rect = new Rectangle(this.getPrefWidth() - 2, this.getPrefWidth() - 2);
		rect.setFill(Color.rgb(198, 198, 198));
		StackPane.setAlignment(rect, Pos.TOP_LEFT);

		this.getChildren().addAll(border, rect);

		addFlag();

		this.setOnMouseClicked(e -> handleClicked(e));
	}

	private void handleClicked(javafx.scene.input.MouseEvent e) {
		if (e.getButton() == MouseButton.PRIMARY) {
			makeVisible();
		} else if (e.getButton() == MouseButton.SECONDARY) {
			if (rect.isVisible()) {
				flag.setVisible(!flag.isVisible());
			}
		}
	}

	public boolean hasMine() {
		return hasMine;
	}
	
	
	public boolean isRectVisible() {
		return rect.isVisible();
	}

	public void addFlag() {
		flag = new Ellipse(this.getPrefWidth() * 0.5, this.getPrefHeight() * 0.5, this.getPrefWidth() * 0.25,
				this.getPrefHeight() * 0.45);
		flag.setFill(Color.rgb(0, 143, 15));
		flag.setVisible(false);
		this.getChildren().add(flag);
		hasFlag = true;

	}
	
	public void wrongFlag() {
		if(mine == null && hasFlag){
			flag.setFill(Color.RED);
		}
	}

	public void makeVisible() {
		if (rect.isVisible() && !flag.isVisible()) {
			rect.setVisible(false);
			if (mine != null) {
				mine.setVisible(true);
				if (!MinesweeperGame.gameOver) {
					border.setFill(Color.RED);
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
		this.minesNear = minesNear;

		if (minesNear > 0) {
			DropShadow ds = new DropShadow();
			ds.setOffsetY(3.0f);
			ds.setColor(Color.color(0.4f, 0.4f, 0.4f));

			text = new Text("" + minesNear);

			text.setEffect(ds);

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
