package application.scenes.menuScene;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;

import application.uiControls.menuScene.HighscoreViewCell;
import business.HighscoreObj;
import business.SelfPlayer;
import business.Song;
import business.SongManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

public class SongSelectionView extends VBox {

	private SongManager songManager;
	private SelfPlayer selfPlayer;

	private ArrayList<Song> trackList;
	private ObservableList<Song> content;
	private ListView<Song> trackView;

	private ArrayList<HighscoreObj> tracklistHighscore;
	private ObservableList<HighscoreObj> contentHighscore;
	private ListView<HighscoreObj> trackViewHighscore;

	private Label highScoreLabel;
	private Label songSelectionLabel;
	private Label nameInput;
	private TextField playerNameInput;
	private HBox input;
	private Button submitPlayerName;

	public SongSelectionView(SelfPlayer selfPlayer) {

		this.selfPlayer = selfPlayer;

		songManager = selfPlayer.getSongManager();

		trackList = songManager.getSongList();

		content = FXCollections.observableArrayList();
		content.clear();
		content.setAll(trackList);

		trackView = new ListView<>();
		trackView.setItems(content);
		trackView.setEditable(true);
		trackView.setFocusTraversable(false);

		trackView.setCellFactory(

				new Callback<ListView<Song>, ListCell<Song>>() {
					@Override
					public ListCell<Song> call(ListView<Song> v) {
						return new SongSelectionCell();
					}
				});

		tracklistHighscore = new ArrayList<HighscoreObj>();

		deserialize();

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
					public ListCell<HighscoreObj> call(ListView<HighscoreObj> v) {
						return new HighscoreViewCell();
					}
				});

		trackViewHighscore.setId("ListView");

		highScoreLabel = new Label("HIGHSCORE");
		highScoreLabel.getStyleClass().addAll("header");
		songSelectionLabel = new Label("SONGSELECTION");
		songSelectionLabel.getStyleClass().addAll("header");
		nameInput = new Label("Spielername");

		input = new HBox();
		playerNameInput = new TextField("Spielernamen eingeben");
		playerNameInput.setMaxWidth(300);
		input.setAlignment(Pos.CENTER);

		this.getStylesheets().add(getClass().getResource("songSelectionStyle.css").toExternalForm());
		this.setAlignment(Pos.CENTER);
		trackViewHighscore.setMaxWidth(1000);
		trackViewHighscore.setMaxHeight(200);
		trackView.setMaxWidth(1000);
		this.getChildren().addAll(highScoreLabel, trackViewHighscore, songSelectionLabel, trackView);
	}

	public ListView<Song> getListView() {
		return this.trackView;
	}

	public void getSongsHighscore() {

		for (int i = 1; i <= 10; i++) {

			tracklistHighscore.add(new HighscoreObj("TestScore ", "testdifficulty"));

		}

	}

	public ListView<HighscoreObj> getListViewHighscore() {
		return this.trackViewHighscore;
	}

	public void setHighScoreView(HighscoreObj o) {
		tracklistHighscore.add(o);
		Collections.sort(tracklistHighscore);

		serialize();

		contentHighscore = FXCollections.observableArrayList();
		contentHighscore.clear();
		contentHighscore.setAll(tracklistHighscore);

		trackViewHighscore.setItems(contentHighscore);
		trackViewHighscore.setEditable(true);
		trackViewHighscore.setFocusTraversable(false);

		trackViewHighscore.setCellFactory(

				new Callback<ListView<HighscoreObj>, ListCell<HighscoreObj>>() {
					@Override
					public ListCell<HighscoreObj> call(ListView<HighscoreObj> v) {
						return new HighscoreViewCell();
					}
				});
	}

	public void serialize() {
		try (ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("highscoreObjects.ser"))) {

			for (int i = 0; i < tracklistHighscore.size(); i++) {
				os.writeObject(tracklistHighscore.get(i));

			}
			os.close();
		} catch (IOException ex) {

			ex.printStackTrace();
		}
	}

	public void deserialize() {
		try (ObjectInputStream is = new ObjectInputStream(new FileInputStream("highscoreObjects.ser"))) {

			while (is.available() >= 0) {

				try {
					tracklistHighscore.add((HighscoreObj) is.readObject());
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
			is.close();
		} catch (IOException ex) {

		}

	}

	public ArrayList<HighscoreObj> getHighscore() {
		return tracklistHighscore;
	}

	public Button getSubmitButton() {
		return this.submitPlayerName;
	}

	public TextField getPlayerNameInput() {
		return playerNameInput;
	}

}
