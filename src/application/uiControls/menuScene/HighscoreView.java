package application.uiControls.menuScene;

import java.util.ArrayList;

import business.HighscoreObj;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

public class HighscoreView extends VBox{
	
	
	
	
	private ArrayList<HighscoreObj> tracklistHighscore;
	private ObservableList<HighscoreObj> contentHighscore;
	private ListView<HighscoreObj> trackViewHighscore;
	private ArrayList<HighscoreObj> testHighscore;
	
	public HighscoreView() {
		
		tracklistHighscore = new ArrayList<HighscoreObj>();
		getSongsHighscore();
		
		
		contentHighscore = FXCollections.observableArrayList();
		contentHighscore.clear();
		contentHighscore.setAll(tracklistHighscore);

		trackViewHighscore = new ListView<>();
		trackViewHighscore.setItems(contentHighscore);
		trackViewHighscore.setEditable(true);
		trackViewHighscore.setFocusTraversable(false);
		
		trackViewHighscore.setCellFactory(

				new Callback<ListView<HighscoreObj>, ListCell<HighscoreObj>>() {
					@Override
					public ListCell<HighscoreObj>call(ListView<HighscoreObj> v) {
						return new HighscoreViewCell();
					}
				});
		this.getStylesheets().add(getClass().getResource("highscoreStyle.css").toExternalForm());
		this.setAlignment(Pos.CENTER);
		this.getChildren().addAll(trackViewHighscore);
		
		
		
		
		
		
		
	}
	
	
	
	public void getSongsHighscore() {
		testHighscore = new ArrayList<HighscoreObj>();
		
		for(int i = 1; i <= 10; i++) {
			
			tracklistHighscore.add(new HighscoreObj("TestScore ",  "testdifficulty"));			
		}
		
		
	
		
		for (int i = 0; i < testHighscore.size(); i++) {

			tracklistHighscore.add(testHighscore.get(i));

		}

	}
	
	
	public ListView<HighscoreObj> getListViewHighscore() {
		return this.trackViewHighscore;
	}

}