package application.scenes.menuScene;

import application.Main;
import application.ViewController;
import application.uiControls.menuScene.BottomViewController;
import application.uiControls.menuScene.HighscoreViewController;
import business.HighscoreObj;
import business.SelfPlayer;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;

public class MenuScene extends BorderPane {

	Label headline;
	ListView songSelection;
	ViewController songSelectionController;
	ViewController bottomViewController;
	ViewController rightViewController;
	ViewController topViewController;
	Button start;
	Button settings;
	private Main main;
	private SelfPlayer selfPlayer;

	public MenuScene(Main main, SelfPlayer selfPlayer) {
		this.main = main;
		this.selfPlayer = selfPlayer;

		// center
		songSelectionController = new SongSelectionController(selfPlayer);
		this.setCenter(songSelectionController.getRootView());

		// top
		topViewController = new TopViewController(main, selfPlayer,
				(SongSelectionView) songSelectionController.getRootView());
		this.setTop(topViewController.getRootView());

		// right
		rightViewController = new HighscoreViewController();
		// this.setRight(rightViewController.getRootView());

		// bottom
		bottomViewController = new BottomViewController(main, (SongSelectionView) songSelectionController.getRootView(),
				selfPlayer);
		this.setBottom(bottomViewController.getRootView());

		this.getStylesheets().add(getClass().getResource("style.css").toExternalForm());

		this.setId("BorderPane");

	}

}
