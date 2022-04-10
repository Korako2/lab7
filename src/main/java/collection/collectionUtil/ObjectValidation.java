package collection.collectionUtil;

import collection.Coordinates;
import collection.Location;
import collection.MusicBand;
import collection.Person;

/**
 * Класс для валидации новых элементов коллекции.
 */
public class ObjectValidation {
    /**
     * Метод для проверки полей объекта {@link MusicBand}.
     *
     * @param musicBand объект коллекции {@link MusicBand}.
     * @return true если поля объекта валидны, иначе false.
     */
    public boolean checkObject(MusicBand musicBand) {
        return (musicBand.getId() > 0 && musicBand.getName() != null && musicBand.getCoordinates() != null &&
                musicBand.getCreationDate() != null &&
                musicBand.getAlbumsCount() > 0 && musicBand.getDescription() != null &&
                musicBand.getGenre() != null && musicBand.getFrontMan() != null &&
                checkCoordinates(musicBand.getCoordinates()) && checkPerson(musicBand.getFrontMan()));
    }

    /**
     * Метод для проверки полей объекта {@link Coordinates}.
     *
     * @param coordinates новый объект {@link Coordinates}.
     * @return true если поля объекта валидны, иначе false.
     */
    private boolean checkCoordinates(Coordinates coordinates) {
        return coordinates.getX() <= 146 && coordinates.getX() != null;
    }

    /**
     * Метод для проверки полей объекта {@link Person}.
     *
     * @param person новый объект {@link Person}.
     * @return true если поля объекта валидны, иначе false.
     */
    private boolean checkPerson(Person person) {
        return (person.getName() != null &&
                person.getHairColor() != null && person.getNationality() != null && checkLocation(person.getLocation()));
    }

    /**
     * Метод для проверки полей объекта {@link Location}.
     *
     * @param location новый объект {@link Location}.
     * @return true если поля объекта валидны, иначе false.
     */
    private boolean checkLocation(Location location) {
        return location.getX() != null && location.getZ() != null;
    }
}
