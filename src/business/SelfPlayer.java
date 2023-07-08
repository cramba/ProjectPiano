package business;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

public class SelfPlayer {

	// private int aktSong = 0;
	// private SimpleIntegerProperty aktSong;
	private SimpleObjectProperty<Song> aktSongProp;
	private SimpleStringProperty speechText;
	private PianoNoteSet noteSet;
	private SongManager songList;
	private SimpleIntegerProperty aktNote;
	private SimpleBooleanProperty computerIsPlaying;
	private States state;
	private int outerProgress;
	private int innerProgress;
	private SimpleIntegerProperty scoreProp;
	private SimpleObjectProperty<HighscoreObj> playerHighscoreProp;
	private SimpleObjectProperty<ArrayList> arrayListProp;

	private SimpleBooleanProperty gameLoseProp;
	private SimpleBooleanProperty gameWinProp;
	private SimpleStringProperty playerNameProp;
	private SimpleStringProperty difficultyProp;
	private SimpleBooleanProperty backButtonClickedProp;
	private SimpleIntegerProperty markedNoteProp;
	private SimpleBooleanProperty showNotes;

	public SelfPlayer() {
		speechText = new SimpleStringProperty();
		aktNote = new SimpleIntegerProperty();
		noteSet = new PianoNoteSet("pianoNotes");
		scoreProp = new SimpleIntegerProperty(0);
		songList = new SongManager();
		aktSongProp = new SimpleObjectProperty();
		aktSongProp.setValue(songList.getSong(0));
		playerHighscoreProp = new SimpleObjectProperty<HighscoreObj>();
		gameLoseProp = new SimpleBooleanProperty(false);
		gameWinProp = new SimpleBooleanProperty(false);
		state = States.NO_GAME_RUNNING;
		playerNameProp = new SimpleStringProperty();
		difficultyProp = new SimpleStringProperty();
		arrayListProp = new SimpleObjectProperty<ArrayList>();
		backButtonClickedProp = new SimpleBooleanProperty(false);
		markedNoteProp = new SimpleIntegerProperty();
		showNotes = new SimpleBooleanProperty(true);

		computerIsPlaying = new SimpleBooleanProperty(false);

	}

	public void keyPressed(int key) {
		switch (state) {
		case COMPUTER_IS_PLAYING:
			speechText.setValue("Warte noch, bis ich fertig bin mit spielen.");
			break;
		case PLAYER_IS_PLAYING:
			if (key == aktSongProp.getValue().getNote(innerProgress)) {
				markedNoteProp
						.setValue(songList.getSong(aktSongProp.getValue().getNumber()).getNote(innerProgress) + 100);
				scoreProp.setValue(scoreProp.getValue() + 1);
				speechText.setValue("Richtig!");
				nextStep();
				

			} else {
				speechText.setValue("Oh nein! Das war leider die falsche Taste. Das Spiel ist vorbei");
				exitGame();
				gameLoseProp.setValue(true);
			}

			break;

		case NO_GAME_RUNNING:
			speechText.setValue("Um das Spiel zu starten, drücke auf Start.");
			break;
		default:
			speechText.setValue("Ich fange Fehler ab");
		}
	}

	public void newGame() {
		if (state == States.NO_GAME_RUNNING) {
			outerProgress = 3;
			innerProgress = 0;
			autoPlay();
			speechText.setValue("Ich spiele dir das Lied mal vor.");
		}
	}

	public void exitGame() {
		innerProgress = 0;
		outerProgress = 3;
		state = States.NO_GAME_RUNNING;
	}

	public void winGame() {
		speechText.setValue("Du hast Gewonnen!");
		exitGame();
		gameWinProp.setValue(true);
	}

	public void nextStep() {
		// prüfe ob Spiel gewonnen
		if (innerProgress == aktSongProp.getValue().getSongSize() - 1) {
			winGame();
			// prüfe ob ende von phase erreicht it
		} else if (innerProgress == outerProgress) {
			if ((outerProgress + 4) >= aktSongProp.getValue().getSongSize()) {

				outerProgress = aktSongProp.getValue().getSongSize() - 1;
				innerProgress = 0;
			} else {

				outerProgress += 4;
				innerProgress = 0;
			}
			speechText.setValue("Du bist an der reihe");
			autoPlay(outerProgress);
			

		} else {
			innerProgress++;
			if (difficultyProp.get().equals("easy")) {
				markedNoteProp.setValue(songList.getSong(aktSongProp.getValue().getNumber()).getNote(innerProgress));
			}

		}
	}

	// note setzen immer bei next step oder selfPlayer

	public void autoPlay() {
		aktNote.setValue(songList.getSong(aktSongProp.getValue().getNumber()).getNote(0) + 100);
		markedNoteProp.setValue(songList.getSong(aktSongProp.getValue().getNumber()).getNote(0) + 100);
		scoreProp.setValue(0);
		state = States.COMPUTER_IS_PLAYING;
		computerIsPlaying.setValue(true);
		showNotes.setValue(true);

		new Thread() {
			public void run() {

				if(!backButtonClickedProp.getValue()) {
					for (int i = 0; i < getAktSongProp().getValue().getSongSize(); i++) {
						if (state != States.NO_GAME_RUNNING) {
							aktNote.setValue(songList.getSong(aktSongProp.getValue().getNumber()).getNote(i));
							noteSet.getNotePlayers().get(aktNote.get()).play();
							try {
								TimeUnit.MILLISECONDS
										.sleep((songList.getSong(aktSongProp.getValue().getNumber()).getPause(i)));
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							aktNote.setValue((songList.getSong(aktSongProp.getValue().getNumber()).getNote(i)) + 100);
						}
					}
				}

				autoPlay(outerProgress);
				computerIsPlaying.setValue(false);
				interrupt();
			}
		}.start();

	}

	public void autoPlay(int k) {
		if (state != States.NO_GAME_RUNNING) {
			if (difficultyProp().getValue().equals("hard")) {
				showNotes.setValue(false);
			}
			
			new Thread() {
				public void run() {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException ignored) {
					}
					state = States.COMPUTER_IS_PLAYING;
					computerIsPlaying.setValue(true);
					
					if(!backButtonClickedProp.getValue()) {
						for (int i = 0; i <= k; i++) {
							if (state != States.NO_GAME_RUNNING) {
								if (k == getAktSongProp().getValue().getSongSize()) {
									aktNote.setValue(songList.getSong(aktSongProp.getValue().getNumber()).getNote(i) - 1);
									break;
								}
								aktNote.setValue(songList.getSong(aktSongProp.getValue().getNumber()).getNote(i));
								noteSet.getNotePlayers().get(aktNote.get()).play();
								try {
									TimeUnit.MILLISECONDS
											.sleep(songList.getSong(aktSongProp.getValue().getNumber()).getPause(i));
								} catch (InterruptedException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								aktNote.setValue((songList.getSong(aktSongProp.getValue().getNumber()).getNote(i)) + 100);
							}
						}
					}

					// zu spielende Note entweder hier anzeigen
					if (difficultyProp.get().equals("easy")) {
						markedNoteProp
								.setValue(songList.getSong(aktSongProp.getValue().getNumber()).getNote(innerProgress));
					}
					state = States.PLAYER_IS_PLAYING;
					computerIsPlaying.setValue(false);
					interrupt();
				}
			}.start();
		}
	}

	public void updateLooseProp() {
		new Thread() {
			public void run() {
				try {
					Thread.sleep(1000);
					gameLoseProp.setValue(false);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				interrupt();
			}
		}.start();
	}

	public void updateWinProp() {
		new Thread() {
			public void run() {
				try {
					Thread.sleep(1000);
					gameWinProp.setValue(false);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				interrupt();
			}
		}.start();
	}

	public PianoNoteSet getNoteSet() {
		return noteSet;
	}

	public SimpleIntegerProperty aktNoteProperty() {
		return aktNote;
	}

	public SimpleBooleanProperty computerIsPlaying() {
		return computerIsPlaying;
	}

	public boolean getComputerIsPlaying() {
		return computerIsPlaying.getValue();
	}

	public SongManager getSongManager() {

		return songList;
	}


	public SimpleObjectProperty<Song> getAktSongProp() {
		return aktSongProp;
	}

	public SimpleStringProperty speechText() {
		return speechText;
	}

	public SimpleIntegerProperty scoreProp() {
		return scoreProp;
	}

	public SimpleObjectProperty<HighscoreObj> playerHighscoreProp() {
		return playerHighscoreProp;
	}

	public SimpleBooleanProperty gameLoseProp() {
		return gameLoseProp;
	}

	public SimpleBooleanProperty gameWinProp() {
		return gameWinProp;
	}

	public SimpleStringProperty playerNameProp() {
		return playerNameProp;
	}

	public SimpleStringProperty difficultyProp() {
		return difficultyProp;
	}

	public SimpleObjectProperty<ArrayList> arrayListProp() {
		return arrayListProp;
	}

	public SimpleBooleanProperty backButtonClickedProp() {

		return backButtonClickedProp;
	}

	public SimpleIntegerProperty markedNoteProp() {
		return markedNoteProp;
	}

	public SimpleBooleanProperty showNotes() {
		return showNotes;
	}

}
