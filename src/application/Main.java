package application;

import java.util.HashMap;
import java.util.Map;

import application.enums.Scenes;
import application.scenes.gameScene.GameSceneController;
import application.scenes.menuScene.MenuSceneController;
import application.scenes.welcomeScene.WelcomeSceneController;
import business.SelfPlayer;
import business.SongManager;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class Main extends Application {

	// WelcomeScene welcomeScene;
	// MenuScene menuScene;

	private BorderPane root;
	private Scene scene;

	private Stage primaryStage;
	private Map<Scenes, Pane> scenes;
	private SelfPlayer selfPlayer;
	private SongManager songManager;

	@Override
	public void init() {

		selfPlayer = new SelfPlayer();

		scenes = new HashMap<>();
		ViewController controller;

		controller = new WelcomeSceneController(this);
		scenes.put(Scenes.WELCOME_SCENE, controller.getRootView());

		controller = new MenuSceneController(this, selfPlayer);
		scenes.put(Scenes.MENU_SCENE, controller.getRootView());

		controller = new GameSceneController(this, selfPlayer);
		scenes.put(Scenes.GAME_SCENE, controller.getRootView());

	}

	@Override
	public void start(Stage primaryStage) {
		try {
			this.primaryStage = primaryStage;
			primaryStage.initStyle(StageStyle.UTILITY);
			root = new BorderPane();
			scene = new Scene(root, 1920, 1080);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

			primaryStage.setScene(scene);

			switchScene(Scenes.WELCOME_SCENE);

			primaryStage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void stop() {
		System.exit(0);
	}

	public void switchScene(Scenes toScene) {
		Scene scene = primaryStage.getScene();

		if (scenes.containsKey(toScene)) {
			scene.setRoot(scenes.get(toScene));
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
