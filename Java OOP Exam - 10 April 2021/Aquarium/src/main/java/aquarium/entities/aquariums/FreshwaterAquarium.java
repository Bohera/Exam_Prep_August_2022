package aquarium.entities.aquariums;

public class FreshwaterAquarium extends BaseAquarium{

    private static final int FRESHWATER_CAPACITY = 50;

    public FreshwaterAquarium(String name) {
        super(name, FRESHWATER_CAPACITY);
    }
}
