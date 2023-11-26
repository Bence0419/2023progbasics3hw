import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;

public class ListAdminsFrame extends JFrame {

    private JTable table;
    private Bank bank;
    public ListAdminsFrame(Bank b) {
        // Cím beállítása
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.bank = b;
        setTitle("Adminok");

        // Táblázat modell létrehozása
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Név");
        model.addColumn("felhasználónév");
        model.addColumn("Azonosító");

        // Szerializált adatok betöltése

        // Adatok hozzáadása a modellhez
        for (Admin admin : bank.admins) {
            model.addRow(new Object[]{admin.getName(), admin.getUsername(), admin.getAdminId()});
        }

        // Táblázat létrehozása a modell alapján
        table = new JTable(model);

        // Táblázat hozzáadása görgetősávval
        JScrollPane scrollPane = new JScrollPane(table);
        getContentPane().add(scrollPane);

        // Ablak beállításai
        setSize(400, 300);
        setLocationRelativeTo(null); // Az ablak középre helyezése
    }
}