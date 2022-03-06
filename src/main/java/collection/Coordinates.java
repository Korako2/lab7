package collection;

public class Coordinates {
    private Float x; //Максимальное значение поля: 146, Поле не может быть null
    private int y;

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

    public void setX(Float x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
}
