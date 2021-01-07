import javafx.geometry.Pos;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class Tile extends StackPane {
	private int minesNear;
	private boolean hasMine;
	private boolean isVisible;
	private boolean flagged;
	private Text text;
	private Ellipse mine;

	public Tile() {
		isVisible = false;

		this.setPrefSize(80, 80);

		Rectangle border = new Rectangle(this.getPrefWidth(), this.getPrefWidth());
//		border.widthProperty().bind(this.getPrefWidth());
//		border.heightProperty().bind(this.getPrefWidth());
		border.setFill(null);
		border.setStroke(Color.BLACK);

		this.getChildren().add(border);

		this.setOnMouseClicked(e -> handleClicked(e));
	}

	private void handleClicked(javafx.scene.input.MouseEvent e) {
		if (e.getButton() == MouseButton.PRIMARY) {
			makeVisible();
		} else if (e.getButton() == MouseButton.SECONDARY) {
			flagged = !flagged;
		}

	}

	public boolean hasMine() {
		return hasMine;
	}
	
	public void makeVisible() {
		isVisible = true;
		if (mine != null) mine.setVisible(true);
		if (text != null) text.setVisible(true);
	}

	public void addMine() {
		hasMine = true;

		mine = new Ellipse(this.getPrefWidth() * 0.5, this.getPrefHeight() * 0.5, this.getPrefWidth() * 0.3,
				this.getPrefHeight() * 0.3);
//		mine.centerXProperty().bind(this.widthProperty().multiply(0.5));
//		mine.centerYProperty().bind(this.heightProperty().multiply(0.5));
//		mine.radiusXProperty().bind(this.widthProperty().multiply(0.3));
//		mine.radiusYProperty().bind(this.heightProperty().multiply(0.3));
		
		mine.setVisible(false);
		
		this.getChildren().add(mine);
	}

	public void setMinesNear(int minesNear) {
		this.minesNear = minesNear;
		text = new Text();
//		text.setTextAlignment(TextAlignment.CENTER);
		if (minesNear > 0) {
			text.setText("" + minesNear);
		}
//		text.setX(this.getPrefWidth() * 0.5);
//		text.setY(this.getPrefHeight() * 0.5);
//		StackPane.setAlignment(text, Pos.CENTER);
		text.setStyle("-fx-font-size: 24");
		text.setVisible(false);
		
		this.getChildren().add(text);
	}
}
