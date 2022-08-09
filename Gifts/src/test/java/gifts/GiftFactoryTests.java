package gifts;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class GiftFactoryTests {

    private List<Gift> gifts;
    private GiftFactory giftFactory;

    @Before
    public void setUp() {
        gifts = new ArrayList<>();
        Gift gift1 = new Gift("Big", 15.00);
        Gift gift2 = new Gift("Medium", 10.00);
        Gift gift3 = new Gift("Small", 5.00);

        gifts.add(gift1);
        gifts.add(gift2);
        gifts.add(gift3);

        this.giftFactory = new GiftFactory();

        giftFactory.createGift(gifts.get(0));
        giftFactory.createGift(gifts.get(1));
        giftFactory.createGift(gifts.get(2));
    }


    @Test
    public void test_getCount_ShouldReturnSize() {
        assertEquals(gifts.size(), giftFactory.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_createGift_ShouldThrowIfGiftAlreadyIn() {
        giftFactory.createGift(gifts.get(0));
    }

    @Test
    public void test_createGift() {
        assertEquals(gifts.size(), giftFactory.getCount());
    }

    @Test(expected = NullPointerException.class)
    public void test_removeGift_ShouldThrowNameCannotBeNull() {
        giftFactory.removeGift(null);
    }

    @Test
    public void test_removeGift() {
        giftFactory.removeGift(gifts.get(0).getType());

        assertEquals(2, giftFactory.getCount());
    }

    @Test
    public void test_getPresentWithLeastMagic() {
        assertEquals(gifts.get(2), giftFactory.getPresentWithLeastMagic());
    }

    @Test
    public void test_getPresent_ShouldReturnPresentWithThisName() {
        assertEquals(gifts.get(2), giftFactory.getPresent(gifts.get(2).getType()));
    }

    @Test(expected = UnsupportedOperationException.class)
    public void test_getPresents_ShouldThrowWhenTryToModify() {
        giftFactory.getPresents().clear();
    }

    @Test
    public void test_getPresents_ShouldReturnUnmodifiableCollectionOfGifts() {
        assertEquals(gifts.size(), giftFactory.getPresents().size());
    }
}
