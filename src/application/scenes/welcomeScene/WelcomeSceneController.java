package application.scenes.welcomeScene;

import application.enums.Scenes;
import application.Main;
import application.ViewController;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class WelcomeSceneController extends ViewController {

	private Main main;
	private Label headline;
	private Button startButton;

	public WelcomeSceneController(Main main) {
		this.main = main;

		WelcomeScene view = new WelcomeScene();

		headline = view.getHeadline();
		startButton = view.getStartButton();

		rootView = view;

		initialize();
	}

	@Override
	public void initialize() {

		startButton.addEventHandler(ActionEvent.ACTION, event -> {
			main.switchScene(Scenes.MENU_SCENE);
		});
		// TODO Auto-generated method stub

	}

}
