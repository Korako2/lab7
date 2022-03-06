package collection;

public enum EyeColor {
    RED("красный"),
    WHITE("белый"),
    BROWN("коричневый");
    private String color;

    EyeColor( String color) {
        this.color=color;
    }

    public String getColor() {
        return color;
    }
}

