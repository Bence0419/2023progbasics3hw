import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class AccountTest {

    private Account testAccount;

    @Before
    public void setUp() {
        testAccount = new Account("John Doe", "john.doe", "password123");
    }

    @Test
    public void testSetAndGetMoney() {
        double initialMoney = testAccount.getMoney();
        double newMoney = 1000.0;

        testAccount.setMoney(newMoney);

        assertEquals("A pénzösszeg beállítása nem működik megfelelően.", newMoney, testAccount.getMoney(), 0.01);
        assertNotEquals("A pénzösszeg nem változott a beállítás után.", initialMoney, testAccount.getMoney(), 0.01);
    }
}