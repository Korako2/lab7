package data;

import java.io.Serializable;

public enum MusicGenre implements Serializable {
    JAZZ("JAZZ"),
    POST_ROCK("POST_ROCK"),
    PUNK_ROCK("PUNK_ROCK");
    private static final long serialVersionUID = 1L;
    private final String music;

    MusicGenre(String music) {
        this.music = music;
    }

    public String getMusic() {
        return music;
    }

}
