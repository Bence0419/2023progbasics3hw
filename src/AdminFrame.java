import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.*;

public class AdminFrame extends JFrame {

    private JTable table;
    private Bank bank;
    public AdminFrame(Bank b) {
        // Cím beállítása
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.bank = b;
        setTitle("Bank táblázat példa");

        // Táblázat modell létrehozása
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Név");
        model.addColumn("Számlaszám");
        model.addColumn("Egyenleg");

        // Szerializált adatok betöltése

        // Adatok hozzáadása a modellhez
        for (Account account : bank.accounts) {
            model.addRow(new Object[]{account.getName(), account.getIBAN(), account.getMoney()});
        }

        // Táblázat létrehozása a modell alapján
        table = new JTable(model);

        // Táblázat hozzáadása görgetősávval
        JScrollPane scrollPane = new JScrollPane(table);
        getContentPane().add(scrollPane);

        // Ablak beállításai
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Az ablak középre helyezése
    }
}