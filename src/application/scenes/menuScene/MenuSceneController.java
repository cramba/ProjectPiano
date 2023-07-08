package application.scenes.menuScene;

import application.Main;
import application.ViewController;
import business.SelfPlayer;

public class MenuSceneController extends ViewController{
	
	private Main main;
	private SelfPlayer selfPlayer;
	
	public MenuSceneController(Main main, SelfPlayer selfPlayer) {
		this.main = main;
		this.selfPlayer = selfPlayer;
		rootView = new MenuScene(main, selfPlayer);
	}

	@Override
	public void initialize() {
		// TODO Auto-generated method stub
		
	}

}
