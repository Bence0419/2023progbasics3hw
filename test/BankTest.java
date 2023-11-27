import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class BankTest {

    private Bank bank;
    private Account exampleAccount;

    @Before
    public void setUp() {
        bank = new Bank();
        exampleAccount = new Account("JohnDoe", "password123", 1000.0);
        bank.addAccount(exampleAccount);

        Admin exampleAdmin = new Admin("AdminUser", "adminPass");
        bank.addAdmin(exampleAdmin);
    }

    @Test
    public void testAddAccount() {
        Account newAccount = new Account("NewUser", "newPassword", 500.0);
        bank.addAccount(newAccount);

        assertTrue("Az új fiók nem lett hozzáadva.", bank.accounts.contains(newAccount));
    }

    @Test
    public void testIsUsedAdmin() {
        assertTrue("Az admin felhasználónév foglalt.", bank.isUsedAdmin("AdminUser"));
        assertFalse("A nem létező admin felhasználónév nem foglalt.", bank.isUsedAdmin("NonExistingAdmin"));
    }

    @Test
    public void testDeleteAccount() {
        String accountNumberToDelete = exampleAccount.getIBAN();
        bank.deleteAccount(accountNumberToDelete);

        assertFalse("A fiók nem lett törölve.", bank.accounts.stream().anyMatch(account -> account.getIBAN().equals(accountNumberToDelete)));
    }

    @Test
    public void testPrintBankNotes() {
        String result = bank.printBankNotes(12345.0);
        assertNotNull("A kiadott bankjegyek szövege null.", result);
        assertTrue("A kiadott bankjegyek szövege nem tartalmazza az elvárt értékeket.", result.contains("20000 Ft-os") && result.contains("5000 Ft-os") && result.contains("100 Ft-os"));
    }

    @Test
    public void testGenerateIban() {
        String generatedIban = bank.generateIban();
        assertNotNull("Az új IBAN null.", generatedIban);
        assertTrue("Az új IBAN hossza helytelen.", generatedIban.length() == 24);
        assertTrue("Az új IBAN nem a várt kezdettel kezdődik.", generatedIban.startsWith("HU42123"));
    }

    @Test
    public void testChangePassword() {
        String oldPassword = exampleAccount.getPassword();
        String newPassword = "newPassword123";
        bank.changePassword(oldPassword, newPassword);

        assertFalse("Az új jelszó nem lett beállítva.", oldPassword.equals(exampleAccount.getPassword()));
        assertTrue("Az új jelszó helytelen.", newPassword.equals(exampleAccount.getPassword()));
    }

    @Test
    public void testLogin() {
        assertTrue("Sikertelen belépés a meglévő fiókkal.", bank.login("JohnDoe", "password123"));
        assertFalse("Sikeres belépés nem létező fiókkal.", bank.login("NonExistingUser", "password123"));
        assertFalse("Sikeres belépés helytelen jelszóval.", bank.login("JohnDoe", "wrongPassword"));
    }
}