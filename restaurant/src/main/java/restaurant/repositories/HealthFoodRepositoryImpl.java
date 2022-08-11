package restaurant.repositories;

import restaurant.entities.healthyFoods.interfaces.HealthyFood;
import restaurant.repositories.interfaces.HealthFoodRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class HealthFoodRepositoryImpl implements HealthFoodRepository<HealthyFood> {

    private Collection<HealthyFood> healthyFoods;

    public HealthFoodRepositoryImpl() {
        healthyFoods = new ArrayList<>();
    }

    @Override
    public HealthyFood foodByName(String name) {
        HealthyFood neededHealthyFood = null;
        for(HealthyFood healthyFood : healthyFoods) {
            if(healthyFood.getName().equals(name)) {
                neededHealthyFood = healthyFood;
            }
        }
        return neededHealthyFood;
    }

    @Override
    public Collection<HealthyFood> getAllEntities() {
        return Collections.unmodifiableCollection(healthyFoods);
    }

    @Override
    public void add(HealthyFood healthyFood) {
        healthyFoods.add(healthyFood);
    }
}
