package IOutils.readers;

import IOutils.InputAndOutput;
import lombok.RequiredArgsConstructor;
import data.Location;

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
        while (true) {
            try {
                return Float.parseFloat(inputAndOutput.readLine("Input x coordinate of location:").replace(",", "."));
            } catch (NumberFormatException e) {
                inputAndOutput.printLine("Wrong format of input! It should be a float number.");
            }
            if (!inputAndOutput.isShowMessages())
                throw new NumberFormatException("Wrong format of X coordinate of location.");
        }
    }

    public int readYCoordinateOfLocation() {
        while (true) {
            try {
                return Integer.parseInt(inputAndOutput.readLine("Input y coordinate of location:"));
            } catch (NumberFormatException e) {
                inputAndOutput.printLine("Wrong format of input! It should be a float number.");
            }
            if (!inputAndOutput.isShowMessages())
                throw new NumberFormatException("Wrong format of Y coordinate of location.");
        }
    }

    public Long readZCoordinateOfLocation() {
        while (true) {
            try {
                return Long.parseLong(inputAndOutput.readLine("Input z coordinate of location:"));
            } catch (NumberFormatException e) {
                inputAndOutput.printLine("Wrong format of input! It should be a Long number.");
            }
            if (!inputAndOutput.isShowMessages())
                throw new NumberFormatException("Wrong format of Z coordinate of location.");
        }
    }
}
