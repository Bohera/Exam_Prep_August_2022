package spaceStation.repositories;

import spaceStation.models.astronauts.Astronaut;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class AstronautRepository implements Repository<Astronaut>{

    private Collection<Astronaut> astronauts;

    public AstronautRepository() {
        astronauts = new ArrayList<>();
    }

    @Override
    public Collection<Astronaut> getModels() {
        return Collections.unmodifiableCollection(astronauts);
    }

    @Override
    public void add(Astronaut astronaut) {
        astronauts.add(astronaut);
    }

    @Override
    public boolean remove(Astronaut astronaut) {
        return astronauts.remove(astronaut);
    }

    @Override
    public Astronaut findByName(String name) {
        return astronauts.stream().filter(a -> a.getName().equals(name)).findFirst().orElse(null);
    }
}
