package collection;

public class Coordinates {
    private final Float x; //Максимальное значение поля: 146, Поле не может быть null.
    private final int y;

    public Coordinates(Float x, int y) {
        this.x = x;
        this.y = y;
    }

    public Float getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
