package restaurant.entities.tables;

public class Indoors extends BaseTable{
    private static final double INDOORS_PRICE_PER_PERSON = 3.50;

    public Indoors(int number, int size) {
        super(number, size, INDOORS_PRICE_PER_PERSON);
    }
}
