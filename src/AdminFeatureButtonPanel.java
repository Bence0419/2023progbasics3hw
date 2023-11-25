import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

// Admin ablak (AdminFrame) osztály
class AdminFeatureButtonPanel extends JFrame {
    private Bank bank;

    public AdminFeatureButtonPanel(Bank b) {
        // Ablak beállításai
        this.bank = b;
        setTitle("Admin Alkalmazás");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Layout beállítása
        setLayout(new FlowLayout(FlowLayout.CENTER, 0, 10)); // Center igazítás, vízszintes és függőleges térköz

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                bank.serializeAccounts("bankdata.dat");
            }
        });

        // Feature-ök hozzáadása
        addFeatureButton("Fiókok listázása", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame adminFrame = new AdminFrame(bank);
                adminFrame.setVisible(true);
            }
        });

        addFeatureButton("Új fiók hozzáadása", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame newAccountFrame = new NewAccountFrame(bank);
                newAccountFrame.setVisible(true);
            }
        });

        addFeatureButton("Fiók törlése", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame deleteAccountFrame = new DeleteAccountFrame(bank);
                deleteAccountFrame.setVisible(true);
            }
        });

        addFeatureButton("Kijelentkezés", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(AdminFeatureButtonPanel.this, "Kijelentkezve adminisztrációból.");
                dispose();
                JFrame loginFrame = new LoginFrame(bank);
                loginFrame.setVisible(true);
            }
        });
    }

    // Segédfüggvény a feature gomb hozzáadásához
    private void addFeatureButton(String label, ActionListener listener) {
        JButton button = new JButton(label);
        button.addActionListener(listener);
        add(button);
    }
}