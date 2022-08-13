package garage;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class GarageTests {

    private List<Car> cars = new ArrayList<>();
    private Garage garage = new Garage();

    @Before
    public void setUp() {


        Car car = new Car("Trabant", 100, 1_000.00);
        Car car1 = new Car("Toyota", 120, 1_200.00);
        Car car2 = new Car("Toyota", 150, 20_000.00);
        Car car3 = new Car("Renault", 200, 40_000.00);
        Car car4 = new Car("Mercedes", 220, 100_000.00);

        cars.add(car);
        cars.add(car1);
        cars.add(car2);
        cars.add(car3);
        cars.add(car4);

    }

    @Test
    public void test_getCount() {
        int expectedCount = 0;
        int actualCount = garage.getCount();

        assertEquals(expectedCount, actualCount);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_addCar_ShouldThrowIfCarIsNull() {
        Car carNull = null;
        garage.addCar(carNull);
    }

    @Test
    public void test_addCar() {
        fillGarageWithCars();

        int expectedCarsCount = cars.size();
        int actualCarsCount = garage.getCount();

        assertEquals(expectedCarsCount, actualCarsCount);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void test_getCars_ShouldThrowException_WhenModified() {
        garage.getCars().add(cars.get(3));
    }

    @Test
    public void test_getCars_ShouldReturn_ListOfCars() {
        fillGarageWithCars();

        Car expectedCar = cars.get(2);
        Car actualCar = garage.getCars().get(2);

        assertEquals(expectedCar, actualCar);
    }

    @Test
    public void test_findAllCarsWithMaxSpeedAbove() {
        fillGarageWithCars();

        List<Car> allCarsWithMaxSpeedAbove = garage.findAllCarsWithMaxSpeedAbove(140);

        assertEquals(3, allCarsWithMaxSpeedAbove.size());
    }

    @Test
    public void test_getTheMostExpensiveCar() {
        fillGarageWithCars();

        Car expectedMostExpensiveCar = cars.get(4);
        Car actualMostExpensiveCar = garage.getTheMostExpensiveCar();


        assertEquals(expectedMostExpensiveCar, actualMostExpensiveCar);
    }

    @Test
    public void test_findAllCarsByBrand() {
        fillGarageWithCars();

        List<Car> listOfToyotaCars = garage.findAllCarsByBrand("Toyota");

        assertEquals("Toyota", listOfToyotaCars.get(1).getBrand());
    }

    private void fillGarageWithCars() {
        garage.addCar(cars.get(0));
        garage.addCar(cars.get(1));
        garage.addCar(cars.get(2));
        garage.addCar(cars.get(3));
        garage.addCar(cars.get(4));
    }

}