package application.scenes.gameScene;

import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.TimeUnit;

import application.Main;
import application.ViewController;
import application.enums.Scenes;
import business.HighscoreObj;
import business.PianoNoteSet;
import business.PianoPlayer;
import business.SelfPlayer;
import javafx.application.Platform;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class GameSceneController extends ViewController {

	private ArrayList<Button> whiteKeys;
	private Button startButton;
	private Main main;
	private PianoNoteSet noteSet;
	private SelfPlayer selfPlayer;
	private Button backButton;
	private Label r2d2;
	private Label speechlabel;
	private Label score;
	private HighscoreObj playerHighscore;
	private ArrayList<HighscoreObj> playerHighscoreList;

	private class WhiteButtonHandler implements EventHandler<ActionEvent> {

		int i;
		
		public WhiteButtonHandler(int i) {
			this.i = i;
		}

		@Override
		public void handle(ActionEvent arg0) {
			noteSet.getNotePlayers().get(i).play();
			selfPlayer.keyPressed(i);
		}
	}

	public GameSceneController(Main main, SelfPlayer selfPlayer) {
		this.selfPlayer = selfPlayer;
		noteSet = selfPlayer.getNoteSet();
		GameScene view = new GameScene();
		this.main = main;
		rootView = new GameScene();
		startButton = view.getStartButton();
		backButton = view.getBackButton();
		whiteKeys = view.getWhiteKeys();
		r2d2 = view.getR2d2();
		speechlabel = view.getSpeechLabel();
		score = view.getScore();
		playerHighscoreList = new ArrayList<HighscoreObj>();

		rootView = view;
		initialize();

	}

	@Override
	public void initialize() {

		startButton.addEventHandler(ActionEvent.ACTION, event -> {
			selfPlayer.newGame();

			playerHighscore = new HighscoreObj(selfPlayer.playerNameProp().getValue(),
					selfPlayer.difficultyProp().getValue());
			selfPlayer.playerHighscoreProp().setValue(playerHighscore);
			playerHighscoreList.add(playerHighscore);
			selfPlayer.arrayListProp().setValue(playerHighscoreList);
		});

		backButton.addEventHandler(ActionEvent.ACTION, event -> {
			selfPlayer.exitGame();
			main.switchScene(Scenes.MENU_SCENE);
			selfPlayer.autoPlay(5);
			selfPlayer.backButtonClickedProp().setValue(true);
			resetKeyboard();

		});

		for (int i = 0; i < whiteKeys.size(); i++) {
			whiteKeys.get(i).addEventHandler(ActionEvent.ACTION, new WhiteButtonHandler(i));
		}

		selfPlayer.aktNoteProperty().addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {

				if (selfPlayer.showNotes().getValue()) {
					if (newValue.intValue() >= 80) {
						whiteKeys.get((newValue.intValue()) - 100).setId("key" + (newValue.intValue() - 100));
					} else {
						whiteKeys.get(newValue.intValue()).setId("keyPlayed" + newValue.intValue());
					}
				}

			}
		});

		selfPlayer.markedNoteProp().addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {

				if (newValue.intValue() >= 80) {
					whiteKeys.get((newValue.intValue()) - 100).setId("key" + (newValue.intValue() - 100));
				} else {
					whiteKeys.get(newValue.intValue()).setId("keyMarked" + newValue.intValue());
				}
			}
		});

		selfPlayer.computerIsPlaying().addListener(new ChangeListener<Boolean>() {

			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				if (newValue == true) {
					r2d2.setId("r2d2_on");
				} else {
					r2d2.setId("r2d2_off");
					//speechlabel.setText("        Jetzt bist du an der Reihe.");
				}
			}
		});

		selfPlayer.speechText().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

				speechlabel.setText("        " + newValue.toString());
			}
		});

		selfPlayer.gameLoseProp().addListener(new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				if (newValue) {
					for (int i = 0; i <= 16; i++) {
						whiteKeys.get(i).fire();
						whiteKeys.get(i).setId("keyLoose" + i);
					}
					selfPlayer.updateLooseProp();
				} else {
					resetKeyboard();
				}
			}
		});

		selfPlayer.gameWinProp().addListener(new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				if (newValue) {
					for (int i = 0; i <= 16; i++) {
						if (i == 0 || i == 2 || i == 7) {
							whiteKeys.get(i).fire();
						}
						whiteKeys.get(i).setId("keyWin" + i);
					}
					selfPlayer.updateWinProp();
				} else {
					resetKeyboard();
				}
			}
		});

		selfPlayer.scoreProp().addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {

				score.setText(newValue.toString());

			}

		});
	}

	public void resetKeyboard() {
		for (int i = 0; i <= 16; i++) {
			whiteKeys.get(i).setId("key" + i);
		}
	}

}
