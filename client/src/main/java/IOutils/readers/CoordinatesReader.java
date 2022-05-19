package IOutils.readers;

import IOutils.InputAndOutput;
import data.Coordinates;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CoordinatesReader {
    private final InputAndOutput inputAndOutput;

    public Coordinates readCoordinates() {
        Float x = readXCoordinate();
        int y = readYCoordinate();
        return new Coordinates(x, y);
    }

    public Float readXCoordinate() {
        while (true) {
            Float x = null;
            try {
                x = Float.parseFloat(inputAndOutput.readLine("Input x coordinate. Max field value: 146.").replace(",", "."));
            } catch (NumberFormatException e) {
                inputAndOutput.printLine("Wrong format of input! It should be a float number.");
            }
            if (x != null && x <= 146) return x;
            inputAndOutput.printLine("Wrong format of input! Max field value: 146. ");
            if (!inputAndOutput.isConsoleReading()) throw new NumberFormatException("Wrong format of X coordinate.");
        }
    }

    public int readYCoordinate() {
        while (true) {
            try {
                return Integer.parseInt(inputAndOutput.readLine("Input y coordinate. It must be integer."));
            } catch (NumberFormatException e) {
                if (!inputAndOutput.isConsoleReading())
                    throw new NumberFormatException("Wrong format of Y coordinate.");
                else inputAndOutput.printLine("Wrong format of input! It must be integer.");
            }
        }

    }
}
