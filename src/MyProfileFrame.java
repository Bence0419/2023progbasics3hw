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

    public MyProfileFrame(Account account) {
        // Ablak beállításai
        setTitle("My Profile");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Swing elemek inicializálása
        nameField = new JTextField(account.getName(),20);
        usernameField = new JTextField(account.getUsername(),20);
        passwordField = new JPasswordField(account.getPassword(),20);
        accountNumberField = new JTextField(account.getIBAN(),20);
        balanceField = new JTextField(Double.toString(account.getMoney()),20);
        showHidePasswordButton = new JButton("(Un)hide");

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
    }
}
