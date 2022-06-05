package IOutils.readers;

import IOutils.InputAndOutput;
import data.Coordinates;
import lombok.RequiredArgsConstructor;
import java.util.function.Function;

@RequiredArgsConstructor
public class CoordinatesReader {
    private final InputAndOutput inputAndOutput;

    public Coordinates readCoordinates() {
        Float x = readXCoordinate();
        int y = readYCoordinate();
        return new Coordinates(x, y);
    }

    public Float readXCoordinate() {
        Function<String, Float> mapper = n -> Float.parseFloat(inputAndOutput.readLine(n).replace(",", "."));
        Function<Float, Boolean> condition = n -> (n != null && n <= 146);
        return inputAndOutput.readLineAs("Input float x coordinate. Max field value: 146: ", mapper, condition);
    }

    public int readYCoordinate() {
        Function<String, Integer> mapper = n -> Integer.parseInt(inputAndOutput.readLine(n));
        Function<Integer, Boolean> condition = n -> (true);
        return inputAndOutput.readLineAs("Input y coordinate. It must be integer: ", mapper, condition);
    }
}
