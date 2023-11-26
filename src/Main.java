import javax.swing.JFrame;

public class Main {
    public static void main(String[] args)
    {
        Bank bank = new Bank();
        bank.deserializeAccounts("bankdata.dat");
        bank.deserializeAdmins("admins.dat");
        bank.admins.get(0).setAdminID("0123456789");
        System.out.println(bank.admins.get(0).getAdminId());
        System.out.println(bank.accounts.size());
        System.out.println(bank.admins.size());
        JFrame app = new LoginFrame(bank);
        app.setVisible(true);
    }
}
