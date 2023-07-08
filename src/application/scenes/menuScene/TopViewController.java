package application.scenes.menuScene;


import application.Main;
import application.ViewController;
import application.enums.Scenes;
import business.SelfPlayer;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;

public class TopViewController extends ViewController {

	Button backButton;
	Main main;
	SelfPlayer selfPlayer;
	SongSelectionView songSelection;

	public TopViewController(Main main, SelfPlayer selfPlayer, SongSelectionView songSelection) {
		TopView view = new TopView();
		this.main = main;
		rootView = view;
		backButton = view.getBackButton();
		this.selfPlayer = selfPlayer;
		this.songSelection = songSelection;

		initialize();
	}

	@SuppressWarnings("unchecked")
	@Override
	public void initialize() {
		// TODO Auto-generated method stub

		backButton.addEventHandler(ActionEvent.ACTION, event -> {

			main.switchScene(Scenes.WELCOME_SCENE);

		});

	}

}
