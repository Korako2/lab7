package data;

import java.io.Serializable;

public enum MusicGenre implements Serializable {
    JAZZ("Джаз"),
    POST_ROCK("Пост-рок"),
    PUNK_ROCK("Пост-рок");
    private static final long serialVersionUID = 1L;
    private final String music;

    MusicGenre(String music) {
        this.music = music;
    }

    public String getMusic() {
        return music;
    }

}
