import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

class LoginFrame extends JFrame implements ActionListener {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private Bank bank;


    public LoginFrame(Bank bank) {
        // Komponensek inicializálása
        this.bank = bank;
        usernameField = new JTextField();
        passwordField = new JPasswordField();
        loginButton = new JButton("Bejelentkezés");

        // Bejelentkezés gomb eseménykezelő
        loginButton.addActionListener(this);
        

        // Ablak beállításai
        setTitle("Bejelentkezés");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Layout beállítása
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        add(new JLabel("Felhasználónév:"));
        add(usernameField);
        add(new JLabel("Jelszó:"));
        add(passwordField);
        add(loginButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        // Bejelentkezési adatok ellenőrzése
        if(bank.isAdmin(username, password))
        {
            // JFrame adminFrame = new adminFrame(bank);
            // adminFrame.setVisible(true);
            // dispose();
        }
        else 
        {
            if(bank.login(username, password))
            {
                Account account = new Account();
                account = bank.findAccount(username, password);
                JFrame appFrame = new ApplicationFrame(bank, account);
                appFrame.setVisible(true);
                dispose();
            }
            else
            {
                // Sikertelen bejelentkezés
                JOptionPane.showMessageDialog(LoginFrame.this, "Hibás felhasználónév vagy jelszó!","Hiba", JOptionPane.ERROR_MESSAGE);
                usernameField.setText(""); // Felhasználónév mező törlése
                passwordField.setText(""); // Jelszó mező törlése
            }
        }
    }
}
