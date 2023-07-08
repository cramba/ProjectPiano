package business;

import de.hsrm.mi.eibo.simpleplayer.SimpleAudioPlayer;
import de.hsrm.mi.eibo.simpleplayer.SimpleMinim;

public class PianoPlayer {
	private SimpleMinim minim;
	private SimpleAudioPlayer audioPlayer;
	private PianoNote note;
	private SongManager songList;

	public PianoPlayer(PianoNote note) {
		this.note = note;
		minim = new SimpleMinim();
		audioPlayer = minim.loadMP3File(note.getFile());
		songList = new SongManager();
	}

	public void play() {

		new Thread() {
			public void run() {
				audioPlayer.rewind();
				audioPlayer.play();
				audioPlayer.rewind();

				interrupt();
			}
		}.start();

	}

}
