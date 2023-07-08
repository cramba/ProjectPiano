package business;

import java.util.ArrayList;

public class SongManager {
	
	private ArrayList<Song> songList;
	private int[][]song1 = {{0,   1,   2,   3,   4,   4,   5,   5,   5,   5,   4,   5,   5,   5,   5,   4,   3,   3,   3,   3,   2,   2,   4,   4,   4,   4,    0},
							{400, 400, 400, 400, 600, 600, 350, 350, 350, 350, 700, 350, 350, 350, 350, 700, 350, 350, 350, 350, 700, 700, 350, 350, 350, 350, 500},
							{6, 5, 5,6, 5}};
	
	private int[][]song2 = {{10,  11,  12,  13},
							{400, 400, 400, 400}};
	
	private int[][]song3 = {{5,   8,   5,   8,   5,   8,   5,   13,  5,   5,   13,  5,   4,   12,  4,   12,  3,   1,   5,   8,   5,   8,   5,   8,   5,   13,  5,   4,   12,  4,   7,   14,  5,   4,   5,   8,   5,   8,   5,   8,   5,   13,  5,   7,   7,   5,   4,   3,   1},
							{250, 250, 250, 250, 200, 200, 300, 250, 200, 200, 250, 250, 250, 250, 350, 350, 350, 350, 350, 350, 350, 350, 350, 350, 350, 350, 350, 350, 350, 350, 350, 350, 350, 350, 350, 350, 350, 350, 350, 350, 350, 350, 350, 350, 350, 350, 350, 350, 350}};
	
	private int[][]song4 = {{5,   8,   5,   8,   5,   8,   5,   13,  5,   13,   5,  4,   12,   4,  3,   1},
							{350, 350, 350, 350, 250, 300, 350, 300, 250, 250, 300, 250, 250, 300, 400, 350}};
	
	public SongManager() {
		songList = new ArrayList<Song>();
		
		
		songList.add(new Song("Alle meien Entchen", songList.size(),song1));
		songList.add(new Song("SongTest", songList.size(), song2));
		songList.add(new Song("Cantina Band", songList.size(), song3));
		songList.add(new Song("Cantina Short", songList.size(), song4));
	}
	
	
	public Song getSong(int i) {
		return songList.get(i);
	}
	
	public ArrayList<Song> getSongList(){
		return songList;
	}

}
