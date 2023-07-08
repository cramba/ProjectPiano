package application.scenes.welcomeScene;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

public class WelcomeScene extends StackPane {

	Label headline;
	Button startButton;

	public WelcomeScene() {

		headline = new Label();
		headline.setMinWidth(367);
		headline.setMinHeight(207);
		headline.setId("logo");
		headline.setTranslateY(-240);

		startButton = new Button();
		startButton.setId("startButton");
		startButton.getStyleClass().addAll("icon_button");
		this.getChildren().addAll(headline, startButton);

		this.getStylesheets().add(getClass().getResource("style.css").toExternalForm());

		this.setId("BorderPane");

	}

	public Label getHeadline() {
		return headline;
	}

	public Button getStartButton() {
		return startButton;
	}

}
