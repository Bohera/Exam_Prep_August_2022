package spaceStation.repositories;

import spaceStation.models.planets.Planet;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class PlanetRepository implements Repository<Planet>{

    private Collection<Planet> planets;

    public PlanetRepository() {
        planets = new ArrayList<>();
    }

    @Override
    public Collection<Planet> getModels() {
        return Collections.unmodifiableCollection(planets);
    }

    @Override
    public void add(Planet planet) {
        planets.add(planet);
    }

    @Override
    public boolean remove(Planet planet) {
        return planets.remove(planet);
    }

    @Override
    public Planet findByName(String name) {
        return planets.stream().filter(p -> p.getName().equals(name)).findFirst().orElse(null);
    }
}
