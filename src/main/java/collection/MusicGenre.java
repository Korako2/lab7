package collection;

public enum MusicGenre {
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
