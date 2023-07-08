package application.scenes.menuScene;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class TopView extends VBox {

	private HBox box;
	private HBox box2;
	private Label title;
	private Button backButton;

	public TopView() {
		title = new Label();
		title.setId("title");
		title.setMinWidth(367);
		title.setMinHeight(207);

		backButton = new Button();
		backButton.getStyleClass().addAll("icon_button_small");
		backButton.setId("backButton");

		box = new HBox();
		box.setPrefWidth(800);
		box.getChildren().addAll(title);

		box2 = new HBox();
		box2.getChildren().addAll(backButton);

		box.setAlignment(Pos.CENTER);

		this.getStylesheets().add(getClass().getResource("topViewStyle.css").toExternalForm());
		this.getChildren().addAll(box2, box);
	}

	public Button getBackButton() {

		return backButton;
	}

}
