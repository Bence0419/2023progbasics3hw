import javax.swing.JFrame;

public class Main {
    public static void main(String[] args)
    {
        Bank bank = new Bank();
        Account account1 = new Account("Joska Pista", "jozsi1", "jozsi1");
        Account account2 = new Account("Jolan Jolan", "jozsi2", "jozsi2");
        bank.addAccount(account1);
        bank.addAccount(account2);
        System.out.println(bank.accounts.get(1).getIBAN());
        JFrame app = new LoginFrame(bank);
        app.setVisible(true);
    }
}
