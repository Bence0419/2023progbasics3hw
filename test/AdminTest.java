import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class AdminTest {

    private Admin testAdmin;

    @Before
    public void setUp() {
        testAdmin = new Admin("Admin User", "admin.user", "adminPassword");
    }

    @Test
    public void testSetAndGetAdminID() {
        String initialAdminID = testAdmin.getAdminId();

        testAdmin.setAdminID("newAdminID");

        assertEquals("Az adminID beállítása nem működik megfelelően.", "newAdminID", testAdmin.getAdminId());
        assertNotEquals("Az adminID nem változott a beállítás után.", initialAdminID, testAdmin.getAdminId());
    }

    @Test
    public void testSetAdminIDRandom() {
        testAdmin.setAdminID();

        assertNotNull("Az új adminID null.", testAdmin.getAdminId());
        assertEquals("Az új adminID hossza helytelen.", 10, testAdmin.getAdminId().length());
    }
}
