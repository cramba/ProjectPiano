package application.uiControls.menuScene;

import business.HighscoreObj;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class HighscoreViewCell extends ListCell<HighscoreObj>  {

private Label playerName;
private Label score;
private Label diff;

	@Override
	protected void updateItem(HighscoreObj t, boolean empty) {

		super.updateItem(t, empty);

		if (t != null) {
			
			VBox box = new VBox();
			box.setPrefSize(100, 70);
			playerName = new Label();
			score = new Label();
			diff = new Label();
			box.setSpacing(6);
			
			playerName.setText(t.getPlayerName());
			score.setText(String.valueOf(t.getScore()));
			diff.setText(t.getDifficulty());
			
			
			box.getChildren().addAll(playerName,score, diff);
			setGraphic(box);

		}

	}

}



