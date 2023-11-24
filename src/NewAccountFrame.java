import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

class NewAccountFrame extends JFrame implements ActionListener {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JTextField nameField;
    private JButton loginButton;
    private Bank bank;
    private Account account;


    public NewAccountFrame(Bank bank) {
        // Komponensek inicializálása
        this.bank = bank;
        bank.deserializeAccounts("bankdata.dat");
        usernameField = new JTextField();
        passwordField = new JPasswordField();
        loginButton = new JButton("OK");

        // Bejelentkezés gomb eseménykezelő
        loginButton.addActionListener(this);
        

        // Ablak beállításai
        setTitle("Új fiók hozzáadása");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // Layout beállítása
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        add(new JLabel("Név:"));
        add(nameField);
        add(new JLabel("Felhasználónév:"));
        add(usernameField);
        add(new JLabel("Jelszó:"));
        add(passwordField);
        add(loginButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Fasz");
    }
}