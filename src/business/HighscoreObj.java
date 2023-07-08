package business;

import java.io.Serializable;

public class HighscoreObj implements Comparable<HighscoreObj>, Serializable {
	
	private static final long serialVersionUID = 7574377944523072271L;
	private String name;
	private int score;
	private String DIFFICULTY;

	public HighscoreObj(String name, String difficulty) {
		this.name = name;
		this.score = 0;
		this.DIFFICULTY = difficulty;
	}

	public String getPlayerName() {
		return name;
	}

	public String getDifficulty() {
		return DIFFICULTY;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {

		this.score = score;
	}

	public void setPlayerName(String playername) {

		this.name = playername;
	}

	public void setDifficulty(String difficulty) {

		this.DIFFICULTY = difficulty;
	}

	@Override
	public int compareTo(HighscoreObj o) {
		if (o.score > this.score) {
			return 1;
		} else {
			return -1;
		}

	}

}
