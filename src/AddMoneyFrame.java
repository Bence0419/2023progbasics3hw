import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

class AddMoneyFrame extends JFrame {
    private JTextField amountField;

    private Bank bank;

    public AddMoneyFrame(Bank b, Account account) {
        this.bank = b;
        // Ablak beállításai
        setTitle("Pénz hozzáadása");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Bezárja az ablakot, de nem zárja be az alkalmazást
        setLocationRelativeTo(null);

        // Komponensek inicializálása
        amountField = new JTextField();
        JButton okButton = new JButton("OK");

        // OK gomb eseménykezelő
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Pénz hozzáadása az account-hoz
                String amountText = amountField.getText();

                try {
                    double amount = Double.parseDouble(amountText);
                    bank.findAccount(account.getUsername(), account.getPassword()).setMoney(account.getMoney()+amount);
                    JOptionPane.showMessageDialog(AddMoneyFrame.this, "Pénz hozzáadva: " + amount + " Ft\n Jelenlegi egyenlege: " + bank.findAccount(account.getUsername(), account.getPassword()).getMoney() + " Ft");
                    bank.serializeAccounts("bankdata.dat");
                    dispose(); // Az ablak bezárása
                } catch (NumberFormatException ex) {
                    // Hiba, ha nem számot adott meg
                    JOptionPane.showMessageDialog(AddMoneyFrame.this, "Érvénytelen összeg. Kérem, adjon meg egy számot!",
                            "Hiba", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                bank.serializeAccounts("bankdata.dat");
            }
        });
        // Layout beállítása
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        add(new JLabel("Összeg:"));
        add(amountField);
        add(okButton);
    }
}