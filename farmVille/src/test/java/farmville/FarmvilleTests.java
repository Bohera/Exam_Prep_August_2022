package farmville;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class FarmvilleTests {

    private Farm farm;
    private static final String FARM_NAME = "City animals";
    private static final int FARM_CAPACITY = 4;
    private List<Animal> animals;

    @Before
    public void setUp() {
        animals = new ArrayList<>();
        Animal animal0 = new Animal("Dog", 50.00);
        Animal animal1 = new Animal("Cat", 40.00);
        Animal animal2 = new Animal("Bird", 30.00);
        Animal animal3 = new Animal("Mouse", 20.00);

        animals.add(animal0);
        animals.add(animal1);
        animals.add(animal2);
        animals.add(animal3);

        farm = new Farm(FARM_NAME, FARM_CAPACITY);
        farm.add(animals.get(0));
        farm.add(animals.get(1));
        farm.add(animals.get(2));
    }

    @Test
    public void test_GetCount_ShouldReturnAnimalsInFarmCount() {
        assertEquals(animals.size() - 1, farm.getCount());
    }

    @Test
    public void test_GetName_ShouldReturnNameOfFarm() {
        assertEquals(FARM_NAME, farm.getName());
    }

    @Test
    public void test_getCapacity_ShouldReturnFarmCapacity() {
        assertEquals(FARM_CAPACITY, farm.getCapacity());
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_Add_ShouldThrowIfFarmCapacityIsFull() {
        farm.add(animals.get(3));
        Animal animal4 = new Animal("test", 1.00);

        farm.add(animal4);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_Add_ShouldThrowIfAnimalAlreadyExists() {
        farm.add(animals.get(0));
    }

    @Test
    public void test_Add_ShouldAddAnimalToFarm() {
        farm.add(animals.get(3));
        assertEquals(animals.size(), farm.getCount());
    }

    @Test
    public void test_Remove_ShouldRemoveThisAnimalType_ReturnsTrue() {
        assertTrue(farm.remove(animals.get(0).getType()));
    }

    @Test
    public void test_Remove_ShouldRemoveThisAnimalType_ReturnsFalse() {
        assertFalse(farm.remove("test"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_SetCapacity_ShouldThrowIfCapacityIsNegative() {
        Farm farm = new Farm("Test Farm", -15);
    }

    @Test(expected = NullPointerException.class)
    public void test_SetName_ShouldThrowIfNameIsNull() {
        Farm farm = new Farm(null, 15);
    }

}
