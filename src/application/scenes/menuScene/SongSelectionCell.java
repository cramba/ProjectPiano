package application.scenes.menuScene;

import business.Song;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class SongSelectionCell extends ListCell<Song> {

	private Label testLabel;
	private ImageView coverImageView;
	private ImageViewPane coverPane;

	@Override
	protected void updateItem(Song t, boolean empty) {

		super.updateItem(t, empty);

		if (t != null) {

			HBox box = new HBox();
			HBox laser = new HBox();

			coverImageView = new ImageView();
			coverPane = new ImageViewPane(coverImageView);
			coverImageView.setImage(new Image(getClass().getResourceAsStream("/laserschwert8.png"), 200, 50, false, false));

			laser.setPrefSize(200, 50);
			laser.setAlignment(Pos.BASELINE_LEFT);
			laser.getChildren().addAll(coverPane);

			box.setPrefHeight(50);
			testLabel = new Label();
			testLabel.setText(t.getTitle());
			box.setAlignment(Pos.CENTER_LEFT);

			box.getChildren().addAll(laser, testLabel);
			setGraphic(box);

		}
	}

}
