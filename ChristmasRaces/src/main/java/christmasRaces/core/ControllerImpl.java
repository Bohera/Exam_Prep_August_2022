package christmasRaces.core;

import christmasRaces.core.interfaces.Controller;
import christmasRaces.entities.cars.BaseCar;
import christmasRaces.entities.cars.Car;
import christmasRaces.entities.cars.MuscleCar;
import christmasRaces.entities.cars.SportsCar;
import christmasRaces.entities.drivers.Driver;
import christmasRaces.entities.drivers.DriverImpl;
import christmasRaces.entities.races.Race;
import christmasRaces.entities.races.RaceImpl;
import christmasRaces.repositories.interfaces.Repository;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static christmasRaces.common.ExceptionMessages.*;
import static christmasRaces.common.OutputMessages.*;

public class ControllerImpl implements Controller {

    private Repository<Driver> driverRepository;
    private Repository<Car> carRepository;
    private Repository<Race> raceRepository;


    public ControllerImpl(Repository<Driver> driverRepository, Repository<Car> carRepository, Repository<Race> raceRepository) {
        this.driverRepository = driverRepository;
        this.carRepository = carRepository;
        this.raceRepository = raceRepository;
    }

    @Override
    public String createDriver(String name) {
        if (driverRepository.getByName(name) != null){
            throw new IllegalArgumentException(String.format(DRIVER_EXISTS, name));
        }

        Driver driver = new DriverImpl(name);
        driverRepository.add(driver);
        return String.format(DRIVER_CREATED, name);
    }

    @Override
    public String createCar(String type, String model, int horsePower) {
        if(carRepository.getByName(model) != null) {
            throw new IllegalArgumentException(String.format(CAR_EXISTS, model));
        }

        if(type.equals("Muscle")) {
            Car car = new MuscleCar(model, horsePower);
            carRepository.add(car);
        } else if (type.equals("Sports")) {
            Car car = new SportsCar(model, horsePower);
            carRepository.add(car);
        }
        return String.format(CAR_CREATED, type + "Car", model);
    }

    @Override
    public String addCarToDriver(String driverName, String carModel) {
        if (driverRepository.getByName(driverName) == null) {
            throw new IllegalArgumentException(String.format(DRIVER_NOT_FOUND, driverName));
        }

        if (carRepository.getByName(carModel) == null) {
            throw new IllegalArgumentException(String.format(CAR_NOT_FOUND, carModel));
        }
        driverRepository.getByName(driverName).addCar(carRepository.getByName(carModel));

        return String.format(CAR_ADDED, driverName, carModel);
    }

    @Override
    public String addDriverToRace(String raceName, String driverName) {
        if (raceRepository.getByName(raceName) == null) {
            throw new IllegalArgumentException(String.format(RACE_NOT_FOUND, raceName));
        }

        if (driverRepository.getByName(driverName) == null) {
            throw new IllegalArgumentException(String.format(DRIVER_NOT_FOUND, driverName));
        }
        raceRepository.getByName(raceName).addDriver(driverRepository.getByName(driverName));
        return String.format(DRIVER_ADDED, driverName, raceName);
    }

    @Override
    public String startRace(String raceName) {
        if (raceRepository.getByName(raceName) == null) {
            throw new IllegalArgumentException(String.format(RACE_NOT_FOUND, raceName));
        }
        if (raceRepository.getByName(raceName).getDrivers().size() < 3) {
            throw new IllegalArgumentException(String.format(RACE_INVALID, raceName, 3));
        }

        int raceLaps = raceRepository.getByName(raceName).getLaps();

        List<Driver> sortedDrivers = raceRepository
                .getByName(raceName)
                .getDrivers()
                .stream()
                .sorted((d1, d2) -> Double.compare(d2.getCar().calculateRacePoints(raceLaps), d1.getCar().calculateRacePoints(raceLaps)))
                .collect(Collectors.toList());

        return String.format(DRIVER_FIRST_POSITION, sortedDrivers.get(0).getName(), raceName) + System.lineSeparator() +
               String.format(DRIVER_SECOND_POSITION, sortedDrivers.get(1).getName(), raceName) + System.lineSeparator() +
               String.format(DRIVER_THIRD_POSITION, sortedDrivers.get(2).getName(), raceName);

    }

    @Override
    public String createRace(String name, int laps) {
        if (raceRepository.getByName(name) != null) {
            throw new IllegalArgumentException(String.format(RACE_EXISTS, name));
        }
        Race race = new RaceImpl(name, laps);
        raceRepository.add(race);
        return String.format(RACE_CREATED, name);
    }
}
