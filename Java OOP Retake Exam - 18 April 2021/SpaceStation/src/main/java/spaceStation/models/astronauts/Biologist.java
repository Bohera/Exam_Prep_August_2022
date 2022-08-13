package spaceStation.models.astronauts;

public class Biologist extends BaseAstronaut{

    private static final double BIOLOGIST_INITIAL_OXYGEN = 70.00;

    public Biologist(String name) {
        super(name, BIOLOGIST_INITIAL_OXYGEN);
    }

    @Override
    public void breath() {
        if(getOxygen() >= 5.00) {
            setOxygen(getOxygen() - 5.00);
        } else {
            setOxygen(0);
        }
    }
}
