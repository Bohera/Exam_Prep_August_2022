package aquarium;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class AquariumTests {
    private Aquarium aquarium;
    @Before
    public void setUp() {
         aquarium = new Aquarium("Legena", 5);
    }


    @Test(expected = NullPointerException.class)
    public void test_setName_ShouldThrowIf_NameIsNull() {
        Aquarium aquariumWithNullName = new Aquarium(null, 15);
    }

    @Test
    public void test_getName() {
        String expectedName = "Legena";
        String actualName = aquarium.getName();

        assertEquals(expectedName, actualName);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_setCapacity_ShouldThrowIf_CapacityIsNegative() {
        Aquarium aquariumWithNegativeCapacity = new Aquarium("test", -9);
    }

    @Test
    public void test_getCapacity() {

        int expectedCapacity = 5;
        int actualCapacity = aquarium.getCapacity();

        assertEquals(expectedCapacity, actualCapacity);
    }

    @Test
    public void test_getCount_ShouldReturnFishCountInAquarium() {
        Fish fish = new Fish("Malka");
        Fish fish1 = new Fish("Golqma");

        aquarium.add(fish);
        aquarium.add(fish1);

        int expectedCount = 2;
        int actualCount = aquarium.getCount();

        assertEquals(expectedCount, actualCount);

    }

    @Test(expected = IllegalArgumentException.class)
    public void test_add_ShouldThrowIfCapacityIsFull() {
        Fish fish = new Fish("Malka");
        Fish fish1 = new Fish("Golqma");
        Fish fish2 = new Fish("Sredna");
        Fish fish3 = new Fish("Zlatna");
        Fish fish4 = new Fish("NeZlatna");

        aquarium.add(fish);
        aquarium.add(fish1);
        aquarium.add(fish2);
        aquarium.add(fish3);
        aquarium.add(fish4);

        Fish fish5 = new Fish("OneMore");
        aquarium.add(fish5);
    }


    @Test
    public void test_add() {
        Fish fish = new Fish("Malka");
        aquarium.add(fish);

        int expectedFishCount = 1;
        int actualFishCount = aquarium.getCount();

        assertEquals(expectedFishCount, actualFishCount);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_remove_ShouldThrowIfNameDoesNotExists() {
        aquarium.remove("Kafqva");
    }

    @Test
    public void test_remove_ShouldRemoveFishFromAquarium() {
        Fish fish = new Fish("Malka");
        Fish fish1 = new Fish("Golqma");
        Fish fish2 = new Fish("Sredna");
        Fish fish3 = new Fish("Zlatna");
        Fish fish4 = new Fish("NeZlatna");

        aquarium.add(fish);
        aquarium.add(fish1);
        aquarium.add(fish2);
        aquarium.add(fish3);
        aquarium.add(fish4);

        aquarium.remove("Golqma");

        int expectedFishCount = 4;
        int actualFishCount = aquarium.getCount();

        assertEquals(expectedFishCount, actualFishCount);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_sellFish_ShouldThrowIfNameDoesNotExists() {
        aquarium.sellFish("Kafqva");
    }

    @Test
    public void test_sellFish_ShouldMakeItNotAvailable() {
        Fish fish = new Fish("Malka");
        Fish fish1 = new Fish("Golqma");
        Fish fish2 = new Fish("Sredna");

        aquarium.add(fish);
        aquarium.add(fish1);
        aquarium.add(fish2);

        aquarium.sellFish("Sredna");

        assertFalse(fish2.isAvailable());
    }

    @Test
    public void test_report_ShouldReturnExactMessage() {
        Fish fish = new Fish("Malka");
        Fish fish1 = new Fish("Golqma");
        Fish fish2 = new Fish("Sredna");

        aquarium.add(fish);
        aquarium.add(fish1);
        aquarium.add(fish2);
        //"Fish available at %s: %s"

        String expectedMessage = String.format("Fish available at %s: %s", "Legena" , fish.getName() + ", " + fish1.getName() + ", " + fish2.getName());
        String actualMessage = aquarium.report();

        assertEquals(expectedMessage, actualMessage);

    }
}

