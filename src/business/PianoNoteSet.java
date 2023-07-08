package business;

import java.util.ArrayList;

public class PianoNoteSet {

	private String title;
	private ArrayList<PianoPlayer> notePlayers = new ArrayList<PianoPlayer>();

	public PianoNoteSet(String title) {
		this.title = title;
		notePlayers.add(new PianoPlayer(new PianoNote("c4.mp3")));
		notePlayers.add(new PianoPlayer(new PianoNote("d4.mp3")));
		notePlayers.add(new PianoPlayer(new PianoNote("e4.mp3")));
		notePlayers.add(new PianoPlayer(new PianoNote("f4.mp3")));
		notePlayers.add(new PianoPlayer(new PianoNote("g4.mp3")));
		notePlayers.add(new PianoPlayer(new PianoNote("a5.mp3")));
		notePlayers.add(new PianoPlayer(new PianoNote("b5.mp3")));
		notePlayers.add(new PianoPlayer(new PianoNote("c5.mp3")));
		notePlayers.add(new PianoPlayer(new PianoNote("d5.mp3")));
		notePlayers.add(new PianoPlayer(new PianoNote("e5.mp3")));

		// Black Keys
		notePlayers.add(new PianoPlayer(new PianoNote("c-4.mp3")));
		notePlayers.add(new PianoPlayer(new PianoNote("d-4.mp3")));
		notePlayers.add(new PianoPlayer(new PianoNote("f-4.mp3")));
		notePlayers.add(new PianoPlayer(new PianoNote("g-4.mp3")));
		notePlayers.add(new PianoPlayer(new PianoNote("a-5.mp3")));
		notePlayers.add(new PianoPlayer(new PianoNote("c-5.mp3")));
		notePlayers.add(new PianoPlayer(new PianoNote("d-5.mp3")));

	}

	public ArrayList<PianoPlayer> getNotePlayers() {

		return notePlayers;
	}

}
