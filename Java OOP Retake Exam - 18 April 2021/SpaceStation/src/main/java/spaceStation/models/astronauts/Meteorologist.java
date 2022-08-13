package spaceStation.models.astronauts;

public class Meteorologist extends BaseAstronaut{

    private static final double METEOROLOGIST_INITIAL_OXYGEN = 90;

    public Meteorologist(String name) {
        super(name, METEOROLOGIST_INITIAL_OXYGEN);
    }
}
