package itmo.p3108.model;

import java.io.Serial;
import java.io.Serializable;

public enum Place implements Serializable {
    EVEREST("Everest"), FOREST("Forest"), ROCK("Rock"), VILLAGE("Village"), KINGDOM("Kingdom");
    @Serial
    private static final long serialVersionUID = 498988351L;
    private final String placeName;

    Place(String placeName) {
        this.placeName = placeName;

    }

    public static Place getPlaceByName(String name) {
        for (Place place : Place.values()) {
            if (place.name().equalsIgnoreCase(name)) {
                return place;
            }
        }
        return null;

    }

    public String getPlaceName() {
        return placeName;
    }


}
