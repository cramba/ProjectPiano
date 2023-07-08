package application.uiControls.menuScene;

import java.util.ArrayList;
import java.util.Collections;

import application.Main;
import application.ViewController;
import application.enums.Scenes;
import application.scenes.menuScene.SongSelectionView;
import business.HighscoreObj;
import business.SelfPlayer;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class BottomViewController extends ViewController {

	private Button easyButton, mediumButton, hardButton, playButton, settingsButton;
	private Main main;
	private TextField playerInput;
	private HighscoreObj playerHighscore;
	private ArrayList<HighscoreObj> tracklistHighscore;
	private SongSelectionView songSelection;
	private SelfPlayer selfPlayer ; 
	private String difficulty = "";

	public BottomViewController(Main main, SongSelectionView songSelection, SelfPlayer selfPlayer) {
		this.main = main;
		this.songSelection = songSelection;
		this.selfPlayer = selfPlayer ; 
		BottomView view = new BottomView();
		easyButton = view.getEasyButton();
		mediumButton = view.getMediumButton();
		hardButton = view.getHardButton();
		playButton = view.getPlayButton();
		playerInput = view.getInputField();
		

		rootView = view;
		initialize();
	}

	@Override
	public void initialize() {

			

			playButton.addEventHandler(ActionEvent.ACTION, event -> {
			main.switchScene(Scenes.GAME_SCENE);
			
			if(playerInput.getText().equals("")) {
				
				
				selfPlayer.playerNameProp().setValue("defaultPlayer");

			}else {
				selfPlayer.playerNameProp().setValue(playerInput.getText());
			}
			
			if(difficulty.equals("")) {
				difficulty = "easy";
				selfPlayer.difficultyProp().setValue(difficulty);

			}else {
				selfPlayer.difficultyProp().setValue(difficulty);

			}

			
			if(selfPlayer.arrayListProp().getValue() != null){
				
				selfPlayer.arrayListProp().getValue().clear();
			}


			tracklistHighscore = songSelection.getHighscore();
			
			
		});
		// TODO Auto-generated method stub

		easyButton.addEventHandler(ActionEvent.ACTION, event -> {
			
			difficulty = "easy";

			easyButton.setId("easyButtonSelected");
			mediumButton.setId("mediumButton");
			hardButton.setId("hardButton");
		});

		mediumButton.addEventHandler(ActionEvent.ACTION, event -> {
			

			difficulty = "medium";

			easyButton.setId("easyButton");
			mediumButton.setId("mediumButtonSelected");
			hardButton.setId("hardButton");
		});

		hardButton.addEventHandler(ActionEvent.ACTION, event -> {
			
			difficulty = "hard";

			easyButton.setId("easyButton");
			mediumButton.setId("mediumButton");
			hardButton.setId("hardButtonSelected");
		});
		
		
		selfPlayer.gameLoseProp().addListener(new ChangeListener<Boolean>() {
			

			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				

				// playerHighscore = new HighscoreObj(playerInput.getText(), "test");
				// selfPlayer.playerHighscoreProp().setValue(playerHighscore);
				if(newValue.booleanValue() == true) {
					playerHighscore = selfPlayer.playerHighscoreProp().getValue();
					playerHighscore.setScore(selfPlayer.scoreProp().intValue());
					//songSelection.setHighScoreView(playerHighscore);


					// Collections.sort(selfPlayer.arrayListProp().getValue());
					// songSelection.setHighScoreView((HighscoreObj) selfPlayer.arrayListProp().getValue().get(0));


					// for(Object o : selfPlayer.arrayListProp().getValue()){
					// System.out.println(( ((HighscoreObj) o).getScore()));
				
				//  
			}
			}
		});
		selfPlayer.gameWinProp().addListener(new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				// TODO Auto-generated method stub
				
				if(newValue.booleanValue() == true) {
					playerHighscore = selfPlayer.playerHighscoreProp().getValue()  ;
					playerHighscore.setScore(selfPlayer.scoreProp().intValue());
				}
			}
			
		});


	}

}
