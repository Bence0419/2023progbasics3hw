import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NewAdminFrame extends JFrame {
    private JTextField nameField;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private Bank bank;

    public NewAdminFrame(Bank bank) {
        this.bank = bank;

        // Ablak beállításai
        setTitle("Új Admin Létrehozása");
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
        passwordField = new JPasswordField();
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
                if(bank.isUsedAdmin(username))
                {
                    JOptionPane.showMessageDialog(NewAdminFrame.this, "A felhaszálónév már foglalt.","Hiba", JOptionPane.ERROR_MESSAGE);
                }
                else{
                    // Új fiók létrehozása és hozzáadása a bankhoz
                    bank.addAdmin(new Admin(name, username, password));
    
                    // Bank deszerializációja és mentése
                    bank.serializeAdmins("admins.dat");
                    JOptionPane.showMessageDialog(NewAdminFrame.this, "Az adminisztrátori adatok megadása sikeres.\n" +
                     bank.findAccount(username, password).getName() + " admin azonosítója:\n" + bank.findAdmin(username, password).getAdminId());
    
                    // Ablak bezárása
                    dispose();
                }
            }
        });
        add(okButton);
    }

}
