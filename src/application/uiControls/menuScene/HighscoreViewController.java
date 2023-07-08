package application.uiControls.menuScene;

import application.ViewController;
import business.HighscoreObj;
import javafx.event.EventHandler;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;

public class HighscoreViewController extends ViewController{
	
	private ListView<HighscoreObj> songList;
	
	public HighscoreViewController() {
		HighscoreView view = new HighscoreView();
		songList = view.getListViewHighscore();
		
		rootView = view;
		
		initialize();
	}

	@Override
	public void initialize() {
		
		songList.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent click) {
				
				if (click.getClickCount() == 2) {
				
				}
			}
		});

		
		
		
		
		
		// TODO Auto-generated method stub
		
	}

}
