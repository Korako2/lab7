package collection;

public class Location {
    private final Float x; //Поле не может быть null
    private final int y;
    private final Long z; //Поле не может быть null

    public Location(Float x, int y, Long z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Float getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Long getZ() {
        return z;
    }
}
