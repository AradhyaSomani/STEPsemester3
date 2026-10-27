import java.util.Arrays;

/** Playlist that hands out copies of its songs, never the internal array. */
public class Playlist {

    private final String[] songs;
    private int songCount;

    public Playlist(int maxSongs) {
        if (maxSongs <= 0) {
            throw new IllegalArgumentException("Maximum size must be positive.");
        }
        this.songs = new String[maxSongs];
    }

    public void addSong(String title) {
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Song title cannot be empty.");
        }
        if (songCount == songs.length) {
            System.out.println("Playlist full: cannot add " + title);
            return;
        }
        songs[songCount++] = title;
    }

    public String[] getSongs() {
        return Arrays.copyOf(songs, songCount);       // safe copy
    }

    public int getSongCount() {
        return songCount;
    }

    public static void main(String[] args) {
        Playlist p = new Playlist(10);
        p.addSong("Song A");
        p.addSong("Song B");
        String[] copy = p.getSongs();
        copy[0] = "Hacked";
        System.out.println("copy[0] = " + copy[0]);
        System.out.println("p.getSongs()[0] = " + p.getSongs()[0]);
        System.out.println("getSongCount() = " + p.getSongCount());
        System.out.println("songs = " + Arrays.toString(p.getSongs()));
    }
}
