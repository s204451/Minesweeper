import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class MinesweeperController {
	Tile[][] tiles;
	MinesweeperModel model;
	
	public MinesweeperController(MinesweeperModel model) {
		this.model = model;

	}

	public static Object tileClicked() {
		return null;
	}
	
//	public void Neighbours(Tile tiles){
//		for (int i = 0; i < this.tiles.length; i++) {
//			for (int j = 0; j < this.tiles.length; j++) {
//				tiles.setOnAction(new EventHandler <ActionEvent>(){
//					
//					@Override
//					public void handle(ActionEvent arg0){
//						tiles.setText("" + MinesweeperModel.neighbourMines(i, j));
//					}
//					
//				});
//			}
//		}
//	}
	
	
}
