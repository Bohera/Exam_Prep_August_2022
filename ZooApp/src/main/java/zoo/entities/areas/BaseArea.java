package zoo.entities.areas;

import zoo.entities.animals.Animal;
import zoo.entities.foods.Food;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

import static zoo.common.ExceptionMessages.*;

public abstract class BaseArea implements Area{

    private String name;
    private int capacity;
    private Collection<Food> foods;
    private Collection<Animal> animals;

    protected BaseArea(String name, int capacity) {
        setName(name);
        setCapacity(capacity);
        setFoods();
        setAnimals();
    }

    private void setName(String name){
        if(name == null || name.trim().isEmpty()) {
            throw new NullPointerException(AREA_NAME_NULL_OR_EMPTY);
        }

        this.name = name;
    }

    private void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    private void setFoods() {
        this.foods = new ArrayList<>();
    }

    private void setAnimals() {
        this.animals = new ArrayList<>();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Collection<Animal> getAnimals() {
        return animals;
    }

    @Override
    public Collection<Food> getFoods() {
        return foods;
    }

    @Override
    public int sumCalories() {
        return foods.stream().mapToInt(Food::getCalories).sum();
    }

    @Override
    public void addAnimal(Animal animal) {
        if(animals.size() == capacity) {
            throw new IllegalStateException(NOT_ENOUGH_CAPACITY);
        }
        animals.add(animal);
    }

    @Override
    public void removeAnimal(Animal animal) {
        animals.remove(animal);
    }

    @Override
    public void addFood(Food food) {
        foods.add(food);
    }

    @Override
    public void feed() {
        animals.forEach(Animal::eat);
    }

    @Override
    public String getInfo() {

        String animalsOutput = animals.isEmpty()
                ? "none"
                : animals.stream().map(Animal::getName).collect(Collectors.joining(" "));

       return String.format("%s (%s):%n" +
                    "Animals: %s%n" +
                    "Foods: %d%n" +
                    "Calories: %d", name, this.getClass().getSimpleName(), animalsOutput, foods.size(), sumCalories());
    }
}
