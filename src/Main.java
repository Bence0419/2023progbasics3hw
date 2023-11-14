import javax.swing.JFrame;

public class Main {
    public static void main(String[] args)
    {
        Bank bank = new Bank();
        JFrame app = new LoginFrame(bank);
        app.setVisible(true);
    }
}
