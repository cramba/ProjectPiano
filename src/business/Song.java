package business;

public class Song {

	private int[][] song;
	private int number;
	private String title;

	public Song(String title, int number, int[][] song) {
		this.title = title;
		this.number = number;
		this.song = song;
	}

	public int getNote(int i) {
		return song[0][i];
	}

	public int getPause(int i) {
		return song[1][i];
	}

	public String getTitle() {
		return title;
	}

	public int getNumber() {
		return number;
	}

	public int getSongSize() {
		return song[0].length;
	}
}
