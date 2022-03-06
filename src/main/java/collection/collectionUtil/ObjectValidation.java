package collection.collectionUtil;

import collection.Coordinates;
import collection.Location;
import collection.MusicBand;
import collection.Person;

public class ObjectValidation {
    public boolean checkObject(MusicBand musicBand) {
        return (musicBand.getId()>0 && musicBand.getName() != null && musicBand.getCoordinates() !=null &&
                musicBand.getCreationDate() != null && musicBand.getNumberOfParticipants() != null &&
                musicBand.getAlbumsCount() > 0 && musicBand.getDescription() !=null &&
                musicBand.getGenre() != null && musicBand.getFrontMan() != null &&
                checkCoordinates(musicBand.getCoordinates()) && checkPerson(musicBand.getFrontMan()));
    }

    private boolean checkCoordinates(Coordinates coordinates) {
        return coordinates.getX()<=146 && coordinates.getX() != null;
    }

    private boolean checkPerson(Person person) {
        return (person.getName() != null && person.getHeight() != null && person.getEyeColor() != null &&
                person.getHairColor() != null && person.getNationality() != null && checkLocation(person.getLocation()));
    }

    private boolean checkLocation(Location location) {
        return location.getX() != null && location.getZ() != null;
    }
}
