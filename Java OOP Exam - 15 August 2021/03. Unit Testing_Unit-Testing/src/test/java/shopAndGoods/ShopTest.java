package shopAndGoods;


import org.junit.Before;
import org.junit.Test;

import javax.naming.OperationNotSupportedException;

import static org.junit.Assert.*;

public class ShopTest {

    private Goods goods1;
    private Goods goods2;
    private Shop shop;
    private String infoForAddedGoods2;
    private String infoForRemovingGoods1;

    @Before
    public void setup() throws OperationNotSupportedException {
        goods1 = new Goods("package", "fdijafioijaf");
        goods2 = new Goods("package2", "fdijafisdadaoijaf");
        shop = new Shop();
        shop.addGoods("Shelves1", goods1);

        infoForAddedGoods2 = String.format("Goods: %s is placed successfully!", goods2.getGoodsCode());
        infoForRemovingGoods1 = String.format("Goods: %s is removed successfully!", goods1.getGoodsCode());
    }

    @Test(expected = UnsupportedOperationException.class)
    public void test_GetShelves_ShouldThrowIfModified() {
        shop.getShelves().put("TestShelve", goods1);
    }

    @Test
    public void test_GetShelves_ShouldReturnMapOfShelves_Unmodifiable() {
        assertEquals(12, shop.getShelves().size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_AddGoods_ShouldThrowIfShelveDoesNotExists() throws OperationNotSupportedException {
        shop.addGoods("DummyShelve", goods1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_AddGoods_ShouldThrowIfShelveAlreadyTaken() throws OperationNotSupportedException {
        Goods testGoods = new Goods("Kutiika", "jasjsjajas");
        shop.addGoods("Shelves1", testGoods);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void test_AddGoods_ShouldThrowIfGoodsAlreadyOnOtherShelf() throws OperationNotSupportedException {
        shop.addGoods("Shelves6", goods1);
    }

    @Test
    public void test_AddGoods() {
        Goods expectedGoods = goods1;
        assertEquals(expectedGoods, shop.getShelves().get("Shelves1"));
    }

    @Test
    public void test_AddingGoods_ReturnedInfo() throws OperationNotSupportedException {
        assertEquals(infoForAddedGoods2, shop.addGoods("Shelves2", goods2));
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_RemoveGoods_ShouldThrowIfShelveDoesNotExists() {
        shop.removeGoods("Shelves15", goods1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_RemoveGoods_ShouldThrowIfGoodsDoesNotExists() {
        shop.removeGoods("Shelves5", goods1);
    }

    @Test(expected = NullPointerException.class)
    public void test_RemoveGoods() {
        shop.removeGoods("Shelves1", goods1);

        assertNull(shop.getShelves().get("Shelves1").getName());
    }

    @Test
    public void test_RemovingGoods_ReturnedInfo() throws OperationNotSupportedException {
        assertEquals(infoForRemovingGoods1, shop.removeGoods("Shelves1", goods1));
    }
}