package spaceStation.models.mission;

import spaceStation.models.astronauts.Astronaut;
import spaceStation.models.planets.Planet;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MissionImpl implements Mission {

    @Override
    public void explore(Planet planet, Collection<Astronaut> astronauts) {
        List<String> items = new ArrayList<>();
        items.addAll(planet.getItems());
        for (Astronaut astronaut : astronauts) {
                while (!items.isEmpty()) {
                        astronaut.getBag().getItems().add(items.get(0));
                        planet.getItems().remove(items.get(0));
                        items.remove(0);
                        astronaut.breath();
                        if (!astronaut.canBreath()) {
                            break;
                        }

                }
        }
    }
}
