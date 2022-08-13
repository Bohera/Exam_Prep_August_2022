package heroRepository;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class HeroRepositoryTests {

    private HeroRepository heroRepository;
    private Hero heroOne;
    private Hero heroTwo;

    @Before
    public void setUp() {
        heroRepository = new HeroRepository();
        heroOne = new Hero("Gosho", 1);
        heroTwo = new Hero("Tosho", 11);

    }


    @Test(expected = NullPointerException.class)
    public void test_Create_ShouldThrow_IfHeroIsNull() {
        heroRepository.create(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_Create_ShouldThrow_IfHeroAlreadyExists() {
        heroRepository.create(heroOne);
        heroRepository.create(heroTwo);
        heroRepository.create(heroTwo);
    }

    @Test
    public void test_Create_ShouldAddHeroToRepository() {
        heroRepository.create(heroOne);
        heroRepository.create(heroTwo);

        assertEquals(2, heroRepository.getCount());
    }

    @Test
    public void test_GetCount_ShouldReturnCountOfHeroesInRepository() {
        heroRepository.create(heroTwo);

        assertEquals(1, heroRepository.getCount());
    }

    @Test(expected = NullPointerException.class)
    public void test_Remove_ShouldThrowIfGivenNameIsNull() {
        heroRepository.remove(null);
    }

    @Test
    public void test_Remove_ShouldReturnTrueIfRemoved() {
        heroRepository.create(heroTwo);

        assertTrue(heroRepository.remove(heroTwo.getName()));
    }

    @Test
    public void test_GetHeroWithHighestLevel() {
        heroRepository.create(heroOne);
        heroRepository.create(heroTwo);

        assertEquals(heroTwo, heroRepository.getHeroWithHighestLevel());
    }

    @Test
    public void test_GetHero_ByName() {
        heroRepository.create(heroOne);
        heroRepository.create(heroTwo);

        Hero heroN = new Hero("Mosho", 100);

        heroRepository.create(heroN);

        assertEquals(heroN, heroRepository.getHero(heroN.getName()));
    }

    @Test(expected = UnsupportedOperationException.class)
    public void test_GetHeroes_ShouldThrowIfTryToModify() {
        heroRepository.getHeroes().add(heroOne);
    }

    @Test
    public void test_GetHeroes_Should_ReturnUnmodifiableCollection() {
        heroRepository.create(heroOne);
        heroRepository.create(heroTwo);

        assertEquals(2, heroRepository.getHeroes().size());
    }
}
