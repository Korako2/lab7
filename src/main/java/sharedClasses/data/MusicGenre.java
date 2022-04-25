package sharedClasses.data;

import java.io.Serializable;

public enum MusicGenre implements Serializable {
    JAZZ("Джаз"),
    POST_ROCK("Пост-рок"),
    PUNK_ROCK("Пост-рок");
    private final String music;

    MusicGenre(String music) {
        this.music = music;
    }

    public String getMusic() {
        return music;
    }

}
