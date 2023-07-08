package application.uiControls.menuScene;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class BottomView extends VBox{
	
	private Button easyButton, mediumButton, hardButton, playButton;
	
	private TextField playerNameInput;
	
	public BottomView() {
		HBox topRow = new HBox();
		VBox uiElements = new VBox();
		
		playerNameInput = new TextField("");
	
		easyButton = new Button();
		easyButton.setId("easyButtonSelected");
		easyButton.getStyleClass().addAll("icon_button_difficulty");
		mediumButton = new Button();
		mediumButton.setId("mediumButton");
		mediumButton.getStyleClass().addAll("icon_button_difficulty");
		hardButton = new Button();
		hardButton.setId("hardButton");
		hardButton.getStyleClass().addAll("icon_button_difficulty");
		playButton = new Button();
		playButton.setId("playButton");
		playButton.getStyleClass().addAll("icon_button");

		topRow.setAlignment(Pos.CENTER);
		topRow.setPadding(new Insets(30));
		topRow.setSpacing(10);
		topRow.getChildren().addAll(easyButton, mediumButton, hardButton);
		playerNameInput.setMaxWidth(300);
		uiElements.getChildren().addAll(playerNameInput, topRow, playButton);
		uiElements.setAlignment(Pos.CENTER);
		
		this.getStylesheets().add(getClass().getResource("style.css").toExternalForm());	
		
		this.getChildren().addAll(uiElements);
		
	}
	
	public Button getEasyButton() {
		return easyButton;
	}
	
	public Button getMediumButton() {
		return mediumButton;
	}
	
	public Button getHardButton() {
		return hardButton;
	}
	
	public Button getPlayButton() {
		return playButton;
	}
	
	public TextField getInputField() {
		return playerNameInput;
	}
	
	
}
