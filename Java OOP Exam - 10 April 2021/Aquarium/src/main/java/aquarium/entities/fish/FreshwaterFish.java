package aquarium.entities.fish;

public class FreshwaterFish extends BaseFish{

    private static final int FRESHWATER_FISH_INITIAL_SIZE = 3;

    public FreshwaterFish(String name, String species, double price) {
        super(name, species, price);
        setSize(FRESHWATER_FISH_INITIAL_SIZE);
    }

    @Override
    public void eat() {
        setSize(getSize() + 3);
    }
}
