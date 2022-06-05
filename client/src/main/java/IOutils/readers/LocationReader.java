package IOutils.readers;

import IOutils.InputAndOutput;
import data.Location;
import lombok.RequiredArgsConstructor;

import java.util.function.Function;

@RequiredArgsConstructor
public class LocationReader {
    private final InputAndOutput inputAndOutput;

    public Location readLocation() {
        Float x = readXCoordinateOfLocation();
        int y = readYCoordinateOfLocation();
        Long z = readZCoordinateOfLocation();
        return new Location(x, y, z);
    }

    public Float readXCoordinateOfLocation() {
        Function<String, Float> mapper = n -> Float.parseFloat(inputAndOutput.readLine(n));
        Function<Float, Boolean> condition = n -> (true);
        return inputAndOutput.readLineAs("Input float x coordinate of location: ", mapper, condition);
    }

    public int readYCoordinateOfLocation() {
        Function<String, Integer> mapper = n -> Integer.parseInt(inputAndOutput.readLine(n));
        Function<Integer, Boolean> condition = n -> (true);
        return inputAndOutput.readLineAs("Input float y coordinate of location: ", mapper, condition);
    }

    public Long readZCoordinateOfLocation() {
        Function<String, Long> mapper = n -> Long.parseLong(inputAndOutput.readLine(n));
        Function<Long, Boolean> condition = n -> (true);
        return inputAndOutput.readLineAs("Input long x coordinate of location: ", mapper, condition);
    }
}
