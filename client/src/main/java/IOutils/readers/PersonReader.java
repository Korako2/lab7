package IOutils.readers;

import IOutils.InputAndOutput;
import data.*;

import java.util.function.Function;

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
        Function<String, Country> mapper = n -> Country.valueOf(inputAndOutput.readLine(n).toUpperCase());
        Function<Country, Boolean> condition = n -> (true);
        return inputAndOutput.readLineAs("Input country(GERMANY, INDIA, VATICAN): ", mapper, condition);
    }

    public String readNameOfPerson() {
        Function<String, String> mapper = inputAndOutput::readLine;
        Function<String, Boolean> condition = n -> (!n.equals(""));
        return inputAndOutput.readLineAs("Input name of person. Name can't be a null: ", mapper, condition);
    }

    public HairColor readHairColor() {
        Function<String, HairColor> mapper = n -> HairColor.valueOf(inputAndOutput.readLine(n).toUpperCase());
        Function<HairColor, Boolean> condition = n -> (true);
        return inputAndOutput.readLineAs("Input hair color(RED, BLACK, BLUE, ORANGE, WHITE): ", mapper, condition);
    }

    public EyeColor readEyeColor() {
        Function<String, EyeColor> mapper = n -> EyeColor.valueOf(inputAndOutput.readLine(n).toUpperCase());
        Function<EyeColor, Boolean> condition = n -> (true);
        return inputAndOutput.readLineAs("Input eye color(RED, WHITE, BROWN): ", mapper, condition);
    }

    public Double readHeight() {
        Function<String, Double> mapper = n -> Double.parseDouble(inputAndOutput.readLine(n).toUpperCase());
        Function<Double, Boolean> condition = n -> (n > 0);
        return inputAndOutput.readLineAs("Input height. Height must be more than 0: ", mapper, condition);
    }
}
