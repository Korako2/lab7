package collection;

public class Location {
    private Float x; //Поле не может быть null
    private int y;
    private Long z; //Поле не может быть null

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

    public void setX(Float x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setZ(Long z) {
        this.z = z;
    }
}
