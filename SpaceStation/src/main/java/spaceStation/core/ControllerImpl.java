package spaceStation.core;

import spaceStation.models.astronauts.Astronaut;
import spaceStation.models.astronauts.Biologist;
import spaceStation.models.astronauts.Geodesist;
import spaceStation.models.astronauts.Meteorologist;
import spaceStation.models.mission.Mission;
import spaceStation.models.mission.MissionImpl;
import spaceStation.models.planets.Planet;
import spaceStation.models.planets.PlanetImpl;
import spaceStation.repositories.AstronautRepository;
import spaceStation.repositories.PlanetRepository;

import java.util.List;
import java.util.stream.Collectors;

import static spaceStation.common.ConstantMessages.*;
import static spaceStation.common.ExceptionMessages.*;

public class ControllerImpl implements Controller {

    private AstronautRepository astronautRepository;
    private PlanetRepository planetRepository;
    private int exploredPlanetsCount = 0;

    public ControllerImpl() {
        astronautRepository = new AstronautRepository();
        planetRepository = new PlanetRepository();
    }

    @Override
    public String addAstronaut(String type, String astronautName) {
        Astronaut astronaut = null;

        switch (type){
            case "Biologist":
                astronaut = new Biologist(astronautName);
                break;
            case "Geodesist":
                astronaut = new Geodesist(astronautName);
                break;
            case "Meteorologist":
                astronaut = new Meteorologist(astronautName);
                break;
            default:
                throw new IllegalArgumentException(ASTRONAUT_INVALID_TYPE);
        }
        astronautRepository.add(astronaut);
        return String.format(ASTRONAUT_ADDED, type, astronautName);
    }

    @Override
    public String addPlanet(String planetName, String... items) {
        Planet planet = new PlanetImpl(planetName);

        for (String item : items) {
            planet.getItems().add(item);
        }

        planetRepository.add(planet);

        return String.format(PLANET_ADDED, planetName);
    }

    @Override
    public String retireAstronaut(String astronautName) {
        if(astronautRepository.findByName(astronautName) == null) {
            throw new IllegalArgumentException(String.format(ASTRONAUT_DOES_NOT_EXIST, astronautName));
        }

        astronautRepository.remove(astronautRepository.findByName(astronautName));

        return String.format(ASTRONAUT_RETIRED, astronautName);
    }

    @Override
    public String explorePlanet(String planetName) {
        List<Astronaut> suitableAstronauts = astronautRepository.getModels()
                .stream()
                .filter(a -> a.getOxygen() > 60)
                .collect(Collectors.toList());

        if(suitableAstronauts.size() == 0) {
            throw new IllegalArgumentException(PLANET_ASTRONAUTS_DOES_NOT_EXISTS);
        }

        Planet planetToExplore = planetRepository.findByName(planetName);

        Mission mission = new MissionImpl();

        mission.explore(planetToExplore, suitableAstronauts);

        List<Astronaut> astronautsLeft = suitableAstronauts
                .stream()
                .filter(a -> a.getOxygen() == 0)
                .collect(Collectors.toList());

        exploredPlanetsCount++;

        return String.format(PLANET_EXPLORED, planetName, astronautsLeft.size());
    }

    @Override
    public String report() {
        //"{exploredPlanetsCount} planets were explored!
        //Astronauts info:
        //Name: {astronautName One}
        //Oxygen: {astronautOxygen One}
        //Bag items: {bagItem1, bagItem2, bagItem3, …, bagItemn \ none}
        //…
        //Name: {astronautName N}
        //Oxygen: {astronautOxygen N}
        //Bag items: {bagItem1, bagItem2, bagItem3, …, bagItemn \ none}"

        return String.format(REPORT_PLANET_EXPLORED, exploredPlanetsCount) + System.lineSeparator() +
                String.format(REPORT_ASTRONAUT_INFO) + System.lineSeparator() +
                String.format(astronautRepository.getModels().stream().map(Astronaut::toString).collect(Collectors.joining(System.lineSeparator())));






    }
}
