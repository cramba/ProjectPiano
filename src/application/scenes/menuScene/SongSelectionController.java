package application.scenes.menuScene;

import java.util.Collections;

import application.ViewController;
import business.SelfPlayer;
import business.Song;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;

public class SongSelectionController extends ViewController {

	private SelfPlayer selfPlayer;
	private ListView<Song> songList;
	private SongSelectionView view;

	public SongSelectionController(SelfPlayer selfPlayer) {

		this.selfPlayer = selfPlayer;
		view = new SongSelectionView(selfPlayer);
		songList = view.getListView();
		rootView = view;

		initialize();
	}

	@Override
	public void initialize() {
		songList.getSelectionModel().select(0);
		songList.getFocusModel().focus(0);

		songList.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent click) {
				int index;
				Song selectedSong;
				if (click.getClickCount() == 1) {
					selectedSong = songList.getSelectionModel().getSelectedItem();
					selfPlayer.getAktSongProp().setValue(selectedSong);
				}
			}
		});

		selfPlayer.backButtonClickedProp().addListener(new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {

				if (newValue.booleanValue() == true) {
					if (selfPlayer.arrayListProp().getValue() != null) {
						Collections.sort(selfPlayer.arrayListProp().getValue());
						view.setHighScoreView((business.HighscoreObj) selfPlayer.arrayListProp().getValue().get(0));
						selfPlayer.backButtonClickedProp().setValue(false);
					}
				}
			}
		});
	}
}
