package cats;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

public class HouseTests {

    private List<Cat> cats = new ArrayList<>();


    @Test
    public void test_GetName_Should_ReturnNameOfHouse() {
        House house = new House("HouseOnATree", 10);

        String expectedName = "HouseOnATree";
        String actualName = house.getName();

        assertEquals(expectedName, actualName);
    }

    @Test(expected = NullPointerException.class)
    public void test_SetName_ShouldThrowIfNameIsNull() {
        House house = new House(null, 10);
    }

    @Test
    public void test_GetCount_ShouldReturn_NumberOfCats() {
        House house = new House("HouseOnATree", 10);
        Cat cat = new Cat("Topcho");
        Cat cat1 = new Cat("Puhcho");

        house.addCat(cat);
        house.addCat(cat1);

        cats.add(cat);
        cats.add(cat1);

        assertEquals(cats.size(), house.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_AddCat_ShouldThrowIfCapacityIsFull() {
        House house = new House("HouseOnATree", 2);
        Cat cat = new Cat("Topcho");
        Cat cat1 = new Cat("Puhcho");
        Cat cat2 = new Cat("Chernio");

        house.addCat(cat);
        house.addCat(cat1);
        house.addCat(cat2);
    }

    @Test
    public void test_AddCat() {
        House house = new House("HouseOnATree", 2);
        Cat cat = new Cat("Topcho");
        Cat cat1 = new Cat("Puhcho");

        house.addCat(cat);
        house.addCat(cat1);

        cats.add(cat);
        cats.add(cat1);

        assertEquals(cats.size(), house.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_RemoveCat_ShouldThrowIfThereIsNoCatWithThisName() {
        House house = new House("HouseOnATree", 2);
        Cat cat = new Cat("Topcho");
        Cat cat1 = new Cat("Puhcho");

        house.addCat(cat);
        house.addCat(cat1);

        house.removeCat("Goshko");
    }

    @Test
    public void test_RemoveCat_ShouldRemoveCatWithGivenName() {
        House house = new House("HouseOnATree", 2);
        Cat cat = new Cat("Topcho");
        Cat cat1 = new Cat("Puhcho");

        house.addCat(cat);
        house.addCat(cat1);

        cats.add(cat);
        cats.add(cat1);

        house.removeCat(cat1.getName());
        cats.remove(1);
        assertEquals(cats.size(), house.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_CatForSale_ShouldThrowIfThereIsNoCatWithThisName() {
        House house = new House("HouseOnATree", 2);
        Cat cat = new Cat("Topcho");
        Cat cat1 = new Cat("Puhcho");

        house.addCat(cat);
        house.addCat(cat1);

        house.catForSale("Goshko");
    }

    @Test
    public void test_CatForSale_ShouldReturnCatThatIsNotHungry() {
        House house = new House("HouseOnATree", 2);
        Cat cat = new Cat("Topcho");
        Cat cat1 = new Cat("Puhcho");

        house.addCat(cat);
        house.addCat(cat1);

        assertFalse(house.catForSale(cat1.getName()).isHungry());
    }

    @Test
    public void test_Statistics() {
        House house = new House("HouseOnATree", 2);
        Cat cat = new Cat("Topcho");
        Cat cat1 = new Cat("Puhcho");

        house.addCat(cat);
        house.addCat(cat1);

        cats.add(cat);
        cats.add(cat1);

        String listOfNames = cats.stream().map(Cat::getName).collect(Collectors.joining(", "));

        String expectedResult = String.format("The cat %s is in the house %s!", listOfNames, house.getName());
        String actualResult = house.statistics();

        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void test_GetCapacity() {
        int houseCapacity = 2;
        House house = new House("HouseOnATree", houseCapacity);

        assertEquals(houseCapacity, house.getCapacity());
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_SetCapacity_ShouldThrowIfCapacityIsBelowZero() {
        int houseCapacity = -2;
        House house = new House("HouseOnATree", houseCapacity);
    }

}
