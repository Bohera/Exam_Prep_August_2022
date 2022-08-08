package fairyShop.models;

public class Sleepy extends BaseHelper{

    private static int ENERGY = 50;

    public Sleepy(String name) {
        super(name, ENERGY);
    }

    @Override
    public void work() {
        super.work();
        ENERGY -= 5;
    }
}
