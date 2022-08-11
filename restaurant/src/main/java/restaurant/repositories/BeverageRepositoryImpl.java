package restaurant.repositories;

import restaurant.entities.drinks.interfaces.Beverages;
import restaurant.repositories.interfaces.BeverageRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class BeverageRepositoryImpl implements BeverageRepository<Beverages> {

    private Collection<Beverages> beverages;

    public BeverageRepositoryImpl() {
        beverages = new ArrayList<>();
    }

    @Override
    public Beverages beverageByName(String drinkName, String drinkBrand) {
        Beverages neededBeverages = null;
        for(Beverages beverages : this.beverages) {
            if(beverages.getName().equals(drinkName) && beverages.getBrand().equals(drinkBrand)) {
                neededBeverages = beverages;
            }
        }
        return neededBeverages;
    }

    @Override
    public Collection<Beverages> getAllEntities() {
        return Collections.unmodifiableCollection(beverages);
    }

    @Override
    public void add(Beverages beverages) {
        this.beverages.add(beverages);
    }
}
