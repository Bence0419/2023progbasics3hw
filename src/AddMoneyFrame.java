import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class AddMoneyFrame extends JFrame {
    private JTextField amountField;

    public AddMoneyFrame(Account account) {
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
                    account.setMoney(account.getMoney()+amount);
                    JOptionPane.showMessageDialog(AddMoneyFrame.this, "Pénz hozzáadva: " + amount);
                    dispose(); // Az ablak bezárása
                } catch (NumberFormatException ex) {
                    // Hiba, ha nem számot adott meg
                    JOptionPane.showMessageDialog(AddMoneyFrame.this, "Érvénytelen összeg. Kérem, adjon meg egy számot!",
                            "Hiba", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Layout beállítása
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        add(new JLabel("Összeg:"));
        add(amountField);
        add(okButton);
    }
}