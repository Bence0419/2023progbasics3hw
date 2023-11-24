import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyProfileFrame extends JFrame {
    private JTextField nameField;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JTextField accountNumberField;
    private JTextField balanceField;
    private JButton showHidePasswordButton;
    private JButton editProfileButton;

    private Bank bank;
    private Account account;

    public MyProfileFrame(Bank b, Account account) {
        this.bank = b;
        this.account = account;
        // Ablak beállításai
        setTitle("Adataim");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Swing elemek inicializálása
        nameField = new JTextField(account.getName(),20);
        usernameField = new JTextField(account.getUsername(),20);
        passwordField = new JPasswordField(account.getPassword(),20);
        accountNumberField = new JTextField(account.getIBAN(),20);
        accountNumberField.setEditable(false);
        balanceField = new JTextField(Double.toString(account.getMoney()),20);
        balanceField.setEditable(false);
        showHidePasswordButton = new JButton("Jelszó felfedése");
        editProfileButton = new JButton("Adatok szerkesztése");

        // A showHidePasswordButton eseménykezelője
        showHidePasswordButton.addActionListener(new ActionListener() {
            private boolean passwordVisible = false;

            @Override
            public void actionPerformed(ActionEvent e) {
                passwordVisible = !passwordVisible;
                if (passwordVisible) {
                    passwordField.setEchoChar((char) 0); // Jelszó láthatóvá tétele
                } else {
                    passwordField.setEchoChar('*'); // Jelszó elrejtése
                }
            }
        });

        editProfileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Módosítások mentése
                String newName = nameField.getText();
                String newUsername = usernameField.getText();
                String newPassword = new String(passwordField.getPassword());
                String newAccountNumber = accountNumberField.getText();
                double newBalance = Double.parseDouble(balanceField.getText());
        
                // Frissítjük az Account objektumot az új adatokkal
                account.setName(newName);
                account.setUsername(newUsername);
                account.setPassword(newPassword);
                account.setIBAN(newAccountNumber);
                account.setMoney(newBalance);
        
                // Bank frissítése a módosított Account objektummal
                bank.serializeAccounts("bankdata.dat");
        
                // Frissítsük a mezőket az új adatokkal
                nameField.setText(account.getName());
                usernameField.setText(account.getUsername());
                passwordField.setText(account.getPassword());
                accountNumberField.setText(account.getIBAN());
                balanceField.setText(Double.toString(account.getMoney()));
            }
        });

        // Layout beállítása
        setLayout(new GridLayout(6, 2));

        // Mezők és címkék hozzáadása
        add(new JLabel("Név:"));
        add(nameField);
        add(new JLabel("Felhasználónév:"));
        add(usernameField);
        add(new JLabel("Jelszó:"));
        add(passwordField);
        add(new JLabel("Számlaszám:"));
        add(accountNumberField);
        add(new JLabel("Egyenleg:"));
        add(balanceField);
        add(showHidePasswordButton); // Az ikonos gomb egy sorban a jelszóval
        add(editProfileButton);
    }
}
