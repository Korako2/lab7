package IOutils.readers;

import IOutils.InputAndOutput;
import data.*;

public class PersonReader {
    private final InputAndOutput inputAndOutput;
    private final LocationReader locationReader;

    public PersonReader(InputAndOutput inputAndOutput) {
        this.inputAndOutput = inputAndOutput;
        this.locationReader = new LocationReader(inputAndOutput);
    }

    public Person readPerson() {
        String name = readNameOfPerson();
        Double height = readHeight();
        EyeColor eyeColor = readEyeColor();
        HairColor hairColor = readHairColor();
        Country nationality = readNationality();
        Location location = locationReader.readLocation();
        return new Person(name, height, eyeColor, hairColor, nationality, location);
    }

    public Country readNationality() {
        while (true) {
            try {
                return Country.valueOf(inputAndOutput.readLine("Input country(GERMANY, INDIA, VATICAN):").toUpperCase());
            } catch (IllegalArgumentException e) {
                if (!inputAndOutput.isConsoleReading()) throw new NumberFormatException("Wrong format national city.");
                else inputAndOutput.printLine("Wrong format of input! Choose a country from the suggested list!");
            }
        }
    }

    public String readNameOfPerson() {
        while (true) {
            String name = inputAndOutput.readLine("Input name of person: ");
            if (name.equals("")) {
                if (!inputAndOutput.isConsoleReading()) throw new NumberFormatException("Wrong format of name.");
                else inputAndOutput.printLine("Wrong format of input! Name can't be a null.");
            } else return name;
        }
    }

    public HairColor readHairColor() {
        while (true) {
            try {
                return HairColor.valueOf(inputAndOutput.readLine("Input hair color(RED, BLACK, BLUE, ORANGE, WHITE):").toUpperCase());
            } catch (IllegalArgumentException e) {
                if (!inputAndOutput.isConsoleReading()) throw new NumberFormatException("Wrong format of hair color.");
                else inputAndOutput.printLine("Wrong format of input! Choose a hair color from the suggested list!");
            }
        }
    }

    public EyeColor readEyeColor() {
        while (true) {
            try {
                String line = inputAndOutput.readLine("Input eye color(RED, WHITE, BROWN):").toUpperCase();
                if (line.equals("")) return null;
                return EyeColor.valueOf(line);
            } catch (IllegalArgumentException e) {
                if (!inputAndOutput.isConsoleReading()) throw new NumberFormatException("Wrong format of eye color.");
                else inputAndOutput.printLine("Wrong format of input! Choose an eye color from the suggested list!");
            }
        }
    }

    public Double readHeight() {
        while (true) {
            try {
                String line = inputAndOutput.readLine("Input height:");
                if (line.equals("")) return null;
                Double height = Double.parseDouble(line.replace(",", "."));
                if (height > 0) return height;
                inputAndOutput.printLine("Height must be more than 0.");
            } catch (NumberFormatException e) {
                inputAndOutput.printLine("Wrong format of input! Height must be a number!");
            }
            if (!inputAndOutput.isConsoleReading()) throw new NumberFormatException("Wrong format of height.");
        }
    }
}
