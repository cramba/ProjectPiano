package business;

import java.io.IOException;

import com.mpatric.mp3agic.ID3v2;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException;

public class PianoNote {

	private String note;
	private int length;
	private String soundFile;

	public PianoNote(String file) {
		try {
			Mp3File mp3file = new Mp3File(file);
			if (mp3file.hasId3v2Tag()) {
				ID3v2 id3v2Tag = mp3file.getId3v2Tag();
				note = id3v2Tag.getTitle();
				soundFile = file;
				length = (int) mp3file.getLengthInSeconds();
			} else {
			}
		} catch (UnsupportedTagException | InvalidDataException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getFile() {
		return soundFile;
	}

	public int getLength() {
		return length;
	}

	public String getNote() {
		return note;
	}

}
