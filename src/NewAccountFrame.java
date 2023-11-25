import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NewAccountFrame extends JFrame {
    private JTextField nameField;
    private JTextField usernameField;
    private JTextField passwordField;
    private Bank bank;

    public NewAccountFrame(Bank bank) {
        this.bank = bank;

        // Ablak beállításai
        setTitle("Új Fiók Létrehozása");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Layout beállítása
        setLayout(new GridLayout(4, 2, 10, 10)); // 4 sor, 2 oszlop, 10 pixel térköz vízszintesen és függőlegesen

        // Szövegdobozok és címkék hozzáadása
        add(new JLabel("Név:"));
        nameField = new JTextField();
        add(nameField);

        add(new JLabel("Felhasználónév:"));
        usernameField = new JTextField();
        add(usernameField);

        add(new JLabel("Jelszó:"));
        passwordField = new JTextField();
        add(passwordField);

        // OK gomb hozzáadása
        JButton okButton = new JButton("OK");
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Adatok beolvasása a szövegdobozokból
                String name = nameField.getText();
                String username = usernameField.getText();
                String password = passwordField.getText();
                if(bank.isUsedUsername(username))
                {
                    JOptionPane.showMessageDialog(NewAccountFrame.this, "A felhaszálónév már foglalt.","Hiba", JOptionPane.ERROR_MESSAGE);
                }
                else{
                    // Új fiók létrehozása és hozzáadása a bankhoz
                    bank.addAccount(new Account(name, username, password));
    
                    // Bank deszerializációja és mentése
                    bank.serializeAccounts("bankdata.dat");
                    JOptionPane.showMessageDialog(NewAccountFrame.this, "A felhasználói adatok megadása sikeres.\n" +
                     bank.findAccount(username, password).getName() + " felhasználó bankszámlaszáma:\n" + bank.findAccount(username, password).getIBAN());
    
                    // Ablak bezárása
                    dispose();
                }
            }
        });
        add(okButton);
    }

}
