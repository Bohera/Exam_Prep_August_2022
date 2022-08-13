package christmasRaces.entities.cars;

import static christmasRaces.common.ExceptionMessages.INVALID_HORSE_POWER;

public class SportsCar extends BaseCar{

    private static final double SPORTS_CAR_CUBIC_CENTIMETERS = 3000.00;
    private static final int SPORTS_CAR_MIN_HORSEPOWER = 250;
    private static final int SPORTS_CAR_MAX_HORSEPOWER = 450;

    public SportsCar(String model, int horsePower) {
        super(model, checkHorsePowerValidity(horsePower), SPORTS_CAR_CUBIC_CENTIMETERS);
    }

    private static int checkHorsePowerValidity(int horsePower) {
        if(horsePower < SPORTS_CAR_MIN_HORSEPOWER || horsePower > SPORTS_CAR_MAX_HORSEPOWER) {
            throw new IllegalArgumentException(String.format(INVALID_HORSE_POWER, horsePower));
        }
        return horsePower;
    }
}
