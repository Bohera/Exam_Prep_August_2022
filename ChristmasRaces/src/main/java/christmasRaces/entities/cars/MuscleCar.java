package christmasRaces.entities.cars;

import static christmasRaces.common.ExceptionMessages.INVALID_HORSE_POWER;

public class MuscleCar extends BaseCar{

    private static final double MUSCLE_CAR_CUBIC_CENTIMETERS = 5000.00;
    private static final int MUSCLE_CAR_MIN_HORSEPOWER = 400;
    private static final int MUSCLE_CAR_MAX_HORSEPOWER = 600;

    public MuscleCar(String model, int horsePower) {
        super(model, checkHorsePowerValidity(horsePower), MUSCLE_CAR_CUBIC_CENTIMETERS);
    }

    private static int checkHorsePowerValidity(int horsePower) {
        if(horsePower < MUSCLE_CAR_MIN_HORSEPOWER || horsePower > MUSCLE_CAR_MAX_HORSEPOWER) {
            throw new IllegalArgumentException(String.format(INVALID_HORSE_POWER, horsePower));
        }
        return horsePower;
    }
}
