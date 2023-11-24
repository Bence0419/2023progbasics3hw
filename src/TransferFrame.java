import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TransferFrame extends JFrame {
    private JTextField accountNumberField;
    private JTextField amountField;
    private JButton okButton;

    private Bank bank;
    private Account account;

    public TransferFrame(Bank bank, Account account) {
        this.bank = bank;
        this.account = account;
        // Ablak beállításai
        setTitle("Átutalás");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Bezárja az ablakot, de nem zárja be az alkalmazást
        setLocationRelativeTo(null);

        // Komponensek inicializálása
        accountNumberField = new JTextField();
        amountField = new JTextField();
        okButton = new JButton("OK");

        // OK gomb eseménykezelő
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Az OK gombra kattintáskor történő művelet
                String targetAccountNumber = accountNumberField.getText();
                double transferAmount;
                try {
                    transferAmount = Double.parseDouble(amountField.getText());
                    if(!bank.isInAccounts(targetAccountNumber))
                    {
                        JOptionPane.showMessageDialog(TransferFrame.this, "Sikertelen utalás. Nincs ilyen bankszámlaszám!",
                            "Hiba", JOptionPane.ERROR_MESSAGE);
                    }
                    else{
                        if(transferAmount>account.getMoney())
                        {
                            JOptionPane.showMessageDialog(TransferFrame.this, "Érvénytelen összeg. Nincs ennyi pénz az egyenlegében.",
                                "Hiba", JOptionPane.ERROR_MESSAGE);
                        }else{
                            bank.transferMoney(bank.findAccount(account.getUsername(),account.getPassword()), bank.findByIBAN(targetAccountNumber), transferAmount);
                            JOptionPane.showMessageDialog(TransferFrame.this, "Átutalás sikeres: "
                                    + transferAmount + " Ft " + bank.findByIBAN(targetAccountNumber).getName()+ " számlájára.\nAz új egyenlege: " + 
                                    bank.findAccount(account.getUsername(),account.getPassword()).getMoney());
                            bank.serializeAccounts("bankdata.dat");
                            dispose(); // Az ablak bezárása
                        }
                    }
                } catch (NumberFormatException ex) {
                    // Hiba, ha nem számot adott meg
                    JOptionPane.showMessageDialog(TransferFrame.this, "Érvénytelen összeg. Kérem, adjon meg egy számot!",
                            "Hiba", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Layout beállítása
        setLayout(new GridLayout(3, 2));
        add(new JLabel("Cél bankszámlaszám:"));
        add(accountNumberField);
        add(new JLabel("Átutalás összege:"));
        add(amountField);
        add(new JLabel()); // Üres címke az elrendezés miatt
        add(okButton);
    }
}
