package petStore;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class PetStoreTests {

    private static final String SPECIE = "Dog";
    private static final int MAX_WEIGHT = 30;
    private static final double PRICE = 2500.00;

    private PetStore petStore;
    private Animal animal;

    @Before
    public void setUp() {
        this.petStore = new PetStore();
        this.animal = new Animal(SPECIE, MAX_WEIGHT, PRICE);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void test_GetAnimals_ShouldReturn_UnmodifiableList() {
        List<Animal> animals = petStore.getAnimals();
        animals.remove(1);
    }

    @Test
    public void test_getCount_ShouldReturnCorrectSize() {
        assertEquals(0, petStore.getCount());
        petStore.addAnimal(animal);
        assertEquals(1, petStore.getCount());
    }

    @Test
    public void test_findAllAnimalsWithMaxKilograms_ShouldReturn_EmptyList_WhenNoSuchAnimals() {
        petStore.addAnimal(animal);
        List<Animal> animals = petStore.findAllAnimalsWithMaxKilograms(MAX_WEIGHT + 10);

        assertTrue(animals.isEmpty());
    }

    @Test
    public void test_findAllAnimalsWithMaxKilograms_ShouldReturn_OnlyThoseHeavier() {
        petStore.addAnimal(animal);
        petStore.addAnimal(new Animal("Test", MAX_WEIGHT - 2, PRICE));
        List<Animal> animals = petStore.findAllAnimalsWithMaxKilograms(MAX_WEIGHT - 1);

        assertEquals(1, animals.size());
        assertEquals(animal.getSpecie(), animals.get(0).getSpecie());
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_AddAnimal_ShouldThrow_WhenAnimalIsNull() {
        petStore.addAnimal(null);
    }

    @Test
    public void test_AddAnimal_ShouldIncreaseCount() {
        petStore.addAnimal(animal);
        assertEquals(1, petStore.getCount());
    }

    @Test
    public void test_GetMostExpensiveAnimal_ShouldReturnNullWhenEmpty() {
        Animal animal = petStore.getTheMostExpensiveAnimal();
        assertNull(animal);
    }

    @Test
    public void test_GetMostExpensiveAnimal_ShouldReturnMostExpensiveOne() {
        petStore.addAnimal(animal);
        petStore.addAnimal(new Animal(SPECIE, MAX_WEIGHT, PRICE - 10));

        Animal actualAnimal = petStore.getTheMostExpensiveAnimal();
        assertEquals(animal.getPrice(), actualAnimal.getPrice(), 0.00);
    }

    @Test
    public void test_findAllAnimalBySpecie_ShouldReturnEmptyListWhenNoAnimals() {
        List<Animal> animals = petStore.findAllAnimalBySpecie(SPECIE);
        assertTrue(animals.isEmpty());
    }

    @Test
    public void test_findAllAnimalBySpecie_ShouldReturnTheRequired_SPECIE() {
        petStore.addAnimal(animal);
        petStore.addAnimal(new Animal("Goat", MAX_WEIGHT, PRICE));
        List<Animal> animals = petStore.findAllAnimalBySpecie(SPECIE);
        assertEquals(1, animals.size());
        assertEquals(SPECIE, animals.get(0).getSpecie());
    }
}

