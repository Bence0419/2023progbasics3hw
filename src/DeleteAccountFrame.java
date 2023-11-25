import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeleteAccountFrame extends JFrame {
    private JTextField accountNumberField;
    private Bank bank;

    public DeleteAccountFrame(Bank bank) {
        this.bank = bank;

        // Ablak beállításai
        setTitle("Fiók Törlése");
        setSize(300, 100);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Layout beállítása
        setLayout(new GridLayout(2, 2, 10, 10)); // 2 sor, 2 oszlop, 10 pixel térköz vízszintesen és függőlegesen

        // Szövegdoboz és címke hozzáadása
        add(new JLabel("Bankszámlaszám:"));
        accountNumberField = new JTextField();
        add(accountNumberField);

        // OK gomb hozzáadása
        JButton okButton = new JButton("OK");
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Bankszámlaszám beolvasása a szövegdobozból
                String accountNumber = accountNumberField.getText();

                // Fiók törlése a bankból
                bank.deleteAccount(accountNumber);

                // Bank deszerializációja és mentése
                bank.serializeAccounts("bankdata.dat");

                // Ablak bezárása
                dispose();
            }
        });
        add(okButton);
    }
}
