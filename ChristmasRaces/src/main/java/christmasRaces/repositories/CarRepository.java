package christmasRaces.repositories;

import christmasRaces.entities.cars.Car;
import christmasRaces.repositories.interfaces.Repository;

import java.util.*;

import static christmasRaces.common.ExceptionMessages.CAR_EXISTS;

public class CarRepository implements Repository<Car> {

    private Map<String, Car> models = new LinkedHashMap<>();


    @Override
    public Car getByName(String name) {
        return models.get(name);
    }

    @Override
    public Collection<Car> getAll() {
        return Collections.unmodifiableCollection(models.values());
    }

    @Override
    public void add(Car model) {
        models.put(model.getModel(), model);
    }

    @Override
    public boolean remove(Car model) {
        return models.remove(model.getModel()) != null;
    }
}
