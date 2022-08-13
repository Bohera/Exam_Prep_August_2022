package restaurant.entities.tables;

import restaurant.entities.drinks.interfaces.Beverages;
import restaurant.entities.healthyFoods.interfaces.HealthyFood;
import restaurant.entities.tables.interfaces.Table;

import java.util.ArrayList;
import java.util.Collection;

import static restaurant.common.ExceptionMessages.INVALID_NUMBER_OF_PEOPLE;
import static restaurant.common.ExceptionMessages.INVALID_TABLE_SIZE;

public abstract class BaseTable implements Table {

    private Collection<HealthyFood> healthyFood;
    private Collection<Beverages> beverages;
    private int number;
    private int size;
    private int numberOfPeople;
    private double pricePerPerson;
    private boolean isReservedTable;
    private double allPeople;

    public BaseTable(int number, int size, double pricePerPerson) {
        this.number = number;
        setSize(size);
        this.pricePerPerson = pricePerPerson;

        healthyFood = new ArrayList<>();
        beverages = new ArrayList<>();

        this.numberOfPeople = 0;
        this.isReservedTable = false;
        this.allPeople = 0;
    }

    private void setSize(int size) {
        if(size <= 0) {
            throw new IllegalArgumentException(INVALID_TABLE_SIZE);
        }
        this.size = size;
    }


    @Override
    public int getTableNumber() {
        return number;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public int numberOfPeople() {
        return numberOfPeople;
    }

    @Override
    public double pricePerPerson() {
        return pricePerPerson;
    }

    @Override
    public boolean isReservedTable() {
        return isReservedTable;
    }

    @Override
    public double allPeople() {
        allPeople = pricePerPerson * numberOfPeople;
        return allPeople;
    }

    @Override
    public void reserve(int numberOfPeople) {
        if(numberOfPeople <= 0) {
            throw new IllegalArgumentException(INVALID_NUMBER_OF_PEOPLE);
        }
        this.numberOfPeople = numberOfPeople;
        this.isReservedTable = true;
    }

    @Override
    public void orderHealthy(HealthyFood food) {
        this.healthyFood.add(food);
    }

    @Override
    public void orderBeverages(Beverages beverages) {
        this.beverages.add(beverages);
    }

    @Override
    public double bill() {
        double sumHealthyFoodPrice = healthyFood.stream().mapToDouble(HealthyFood::getPrice).sum();
        double sumBeveragesPrice = beverages.stream().mapToDouble(Beverages::getPrice).sum();
        return sumHealthyFoodPrice + sumBeveragesPrice + allPeople();
    }

    @Override
    public void clear() {
        healthyFood.clear();
        beverages.clear();
        numberOfPeople = 0;
        isReservedTable = false;
        allPeople = 0;
    }

    @Override
    public String tableInformation() {

        //"Table - {table number}
        //Size - {table size}
        //Type - {table type}
        //All price - {price per person for the current table}"

        return String.format("Table - %d", number) + System.lineSeparator() +
                String.format("Size - %d", size) + System.lineSeparator() +
                String.format("Type - %s", this.getClass().getSimpleName()) + System.lineSeparator() +
                String.format("All price - %.2f", this.pricePerPerson);

    }
}
