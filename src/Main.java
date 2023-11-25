import javax.swing.JFrame;

public class Main {
    public static void main(String[] args)
    {
        Bank bank = new Bank();
        bank.deserializeAccounts("bankdata.dat");
        bank.deserializeAdmins("admins.dat");
        System.out.println(bank.accounts.get(0).getIBAN());
        System.out.println(bank.accounts.size());
        System.out.println(bank.admins.size());
        JFrame app = new LoginFrame(bank);
        app.setVisible(true);
    }
}
