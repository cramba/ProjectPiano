package application.scenes.gameScene;

import java.io.File;
import java.util.ArrayList;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;

public class GameScene extends BorderPane {

	private ArrayList<Button> whiteKeys;
	int xCordinate;
	int yCordinate;
	private Button key;
	private Button startButton;
	private Button backButton;
	private Label speechlabel;
	private Label r2d2;
	private Label scoreBoard;
	private Label score;

	public GameScene() {
		StackPane keyboard = new StackPane();

		whiteKeys = new ArrayList<Button>();
		xCordinate = -400;
		yCordinate = 100;

		startButton = new Button();
		startButton.setId("startButton");
		startButton.getStyleClass().addAll("icon_button");
		startButton.setTranslateX(-260);
		startButton.setTranslateY(-100);
		backButton = new Button();
		backButton.getStyleClass().addAll("icon_button_small");
		backButton.setId("backButton");
		r2d2 = new Label();
		r2d2.setId("r2d2_off");
		r2d2.setMinWidth(139);
		r2d2.setMinHeight(174);
		r2d2.setTranslateY(-100);
		scoreBoard = new Label();
		scoreBoard.setId("score_Board");
		scoreBoard.setMinWidth(195);
		scoreBoard.setMinHeight(46);
		scoreBoard.setTranslateX(470);
		score = new Label("0");
		score.setId("score");
		score.setTranslateX(525);



		speechlabel = new Label(
				"	  Hallo, lass uns ein bisschen Klavier spielen! \n	  drücke zum starten auf den Knopf");
		speechlabel.setTranslateX(-120);
		speechlabel.setTranslateY(-240);
		speechlabel.setId("speech");
		speechlabel.setMinWidth(350);
		speechlabel.setMinHeight(120);

		keyboard.getChildren().addAll(r2d2, scoreBoard, score, startButton, backButton, speechlabel);

		keyboard.setId("stackPane");
		this.setId("borderPane");

		for (int i = 0; i < 10; i++) {
			key = new Button();
			key.setId("key" + i);
			key.getStyleClass().addAll("white_icon_button");

			xCordinate += 70;

			key.setTranslateX(xCordinate);
			key.setTranslateY(yCordinate);

			whiteKeys.add(key);
			keyboard.getChildren().addAll(whiteKeys.get(i));

		}

		xCordinate = -365;
		yCordinate = 40;

		for (int i = 10; i < 17; i++) {

			key = new Button();
			key.setId("key" + i);
			key.getStyleClass().addAll("black_icon_button");


			if (i == 12 || i == 15) {

				xCordinate = xCordinate + 140;

				key.setTranslateX(xCordinate);
				key.setTranslateY(yCordinate);

			} else {

				xCordinate = xCordinate + 70;

				key.setTranslateX(xCordinate);
				key.setTranslateY(yCordinate);

			}

			whiteKeys.add(key);

			keyboard.getChildren().addAll(whiteKeys.get(i));

			this.setCenter(keyboard);
			this.setTop(backButton);

		}

		this.getStylesheets().add(getClass().getResource("style.css").toExternalForm());

	}

	public ArrayList<Button> getWhiteKeys() {
		return whiteKeys;
	}

	public Button getStartButton() {
		return startButton;
	}

	public Button getBackButton() {
		return backButton;
	}

	public Label getR2d2() {
		return r2d2;
	}

	public Label getSpeechLabel() {

		return speechlabel;
	}

	public Label getScoreBoard() {
		return scoreBoard;
	}

	public Label getScore() {
		return score;
	}

}
