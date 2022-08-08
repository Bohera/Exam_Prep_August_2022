package glacialExpedition.models.explorers;

public class AnimalExplorer extends BaseExplorer{

    private static final double ANIMAL_EXPLORER_STARTING_ENERGY = 40;

    public AnimalExplorer(String name) {
        super(name, ANIMAL_EXPLORER_STARTING_ENERGY);
    }
}
