package catHouse.core;

import catHouse.entities.cat.Cat;
import catHouse.entities.cat.LonghairCat;
import catHouse.entities.cat.ShorthairCat;
import catHouse.entities.houses.House;
import catHouse.entities.houses.LongHouse;
import catHouse.entities.houses.ShortHouse;
import catHouse.entities.toys.Ball;
import catHouse.entities.toys.Mouse;
import catHouse.entities.toys.Toy;
import catHouse.repositories.ToyRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

import static catHouse.common.ConstantMessages.*;
import static catHouse.common.ExceptionMessages.*;

public class ControllerImpl implements Controller{

    private ToyRepository toys;
    private Collection<House> houses;

    public ControllerImpl() {
        toys = new ToyRepository();
        houses = new ArrayList<>();
    }

    @Override
    public String addHouse(String type, String name) {
        if(type.equals("ShortHouse")) {
            House house = new ShortHouse(name);
            houses.add(house);
        } else if(type.equals("LongHouse")) {
            House house = new LongHouse(name);
            houses.add(house);
        } else {
            throw new NullPointerException(INVALID_HOUSE_TYPE);
        }
        return String.format(SUCCESSFULLY_ADDED_HOUSE_TYPE, type);
    }

    @Override
    public String buyToy(String type) {
        if(type.equals("Ball")) {
            Toy toy = new Ball();
            toys.buyToy(toy);
        } else if(type.equals("Mouse")) {
            Toy toy = new Mouse();
            toys.buyToy(toy);
        } else {
            throw new IllegalArgumentException(INVALID_TOY_TYPE);
        }

        return String.format(SUCCESSFULLY_ADDED_TOY_TYPE, type);
    }

    @Override
    public String toyForHouse(String houseName, String toyType) {
        Toy toy = toys.findFirst(toyType);
        if(toy == null) {
            throw new IllegalArgumentException(String.format(NO_TOY_FOUND, toyType));
        }
        House house = getHouse(houseName);
        house.buyToy(toy);
        toys.removeToy(toy);

        return String.format(SUCCESSFULLY_ADDED_TOY_IN_HOUSE, toyType, houseName);
    }

    @Override
    public String addCat(String houseName, String catType, String catName, String catBreed, double price) {

        Cat cat = null;

        boolean isSuitableHouse = false;

        if(catType.equals("ShorthairCat")) {
            if(getHouse(houseName).getClass().getSimpleName().equals("ShortHouse")) {
                isSuitableHouse = true;
            }
            cat = new ShorthairCat(catName, catBreed, price);
        } else if(catType.equals("LonghairCat")) {
            if(getHouse(houseName).getClass().getSimpleName().equals("LongHouse")) {
                isSuitableHouse = true;
            }
            cat = new LonghairCat(catName, catBreed, price);
        } else {
            throw new IllegalArgumentException(INVALID_CAT_TYPE);
        }

        if(isSuitableHouse) {
            getHouse(houseName).addCat(cat);
            return String.format(SUCCESSFULLY_ADDED_CAT_IN_HOUSE, catType, houseName);
        }

        return UNSUITABLE_HOUSE;
    }

    @Override
    public String feedingCat(String houseName) {
        getHouse(houseName).feeding();
        return String.format(FEEDING_CAT, getHouse(houseName).getCats().size());
    }

    @Override
    public String sumOfAll(String houseName) {
        double sumCatsPrices = getHouse(houseName)
                .getCats()
                .stream()
                .mapToDouble(Cat::getPrice)
                .sum();
        double sumToysPrices = getHouse(houseName)
                .getToys()
                .stream()
                .mapToDouble(Toy::getPrice)
                .sum();

        return String.format(VALUE_HOUSE, houseName, sumCatsPrices + sumToysPrices);

    }

    @Override
    public String getStatistics() {
        return houses.stream().map(House::getStatistics).collect(Collectors.joining(System.lineSeparator()));
    }

    private House getHouse(String houseName) {
        return houses.stream().filter(h -> h.getName().equals(houseName)).findFirst().orElse(null);
    }

}
