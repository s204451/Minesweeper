import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;

public class Tile extends Pane {
	private int minesNear;
	private boolean hasMine;
	private boolean isVisible;

	public Tile() {
		this.hasMine = hasMine;
		isVisible = false;
		
		this.setPrefSize(80, 80);
		
		Rectangle border = new Rectangle(this.getPrefWidth(), this.getPrefHeight());
		border.widthProperty().bind(this.widthProperty());
		border.heightProperty().bind(this.heightProperty());
		border.setFill(null);
		border.setStroke(Color.BLACK);
		
		this.getChildren().add(border);
		
		this.setOnMouseClicked(e -> MinesweeperController.tileClicked());
	}
	
	public boolean hasMine(){
		return hasMine;
	}
	
	public void addMine() {
		hasMine = true;
		
		Ellipse mine = new Ellipse(this.getPrefWidth() * 0.5, this.getPrefHeight() * 0.5, this.getPrefWidth() * 0.3, this.getPrefHeight() * 0.3);
		this.getChildren().add(mine);
	}
	
	public void setMinesNear(int minesNear) {
		this.minesNear = minesNear;
	}
}
