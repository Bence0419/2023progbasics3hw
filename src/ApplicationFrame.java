import javax.swing.*;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

// Alkalmazás ablak (ApplicationFrame) osztály
class ApplicationFrame extends JFrame {
    private Bank bank;
    private Account account;
    
    public ApplicationFrame(Bank b, Account account) {
        // Ablak beállításai
        this.bank = b;
        this.account = account;
        setTitle("Banki Alkalmazás");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        // Layout beállítása
        setLayout(new FlowLayout(FlowLayout.CENTER, 0, 10));
        
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                bank.serializeAccounts("bankdata.dat");
            }
        });
        // Feature-ök hozzáadása
        addFeatureButton("Adataim", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame myProfilefFrame = new MyProfileFrame(bank, account);
                myProfilefFrame.setVisible(true);
            }
        });

        addFeatureButton("Pénz befizetése", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame addMoneyFrame = new AddMoneyFrame(bank, account);
                addMoneyFrame.setVisible(true);
            }
        });

        addFeatureButton("Pénz levétele", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame moneyCheckout = new MoneyCheckoutFrame(bank, account);
                moneyCheckout.setVisible(true);
            }
        });

        addFeatureButton("Banki átutalás", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame transferFrame = new TransferFrame(bank, account);
                transferFrame.setVisible(true);
            }
        });

        addFeatureButton("Kijelentkezés", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(ApplicationFrame.this,"Kijelentkezve " + account.getUsername() + " fiókból.");
                dispose();
                JFrame loginFrame = new LoginFrame(bank);
                loginFrame.setVisible(true);
            }
        });
    }

    // Segédfüggvény a feature gomb hozzáadásához
    private void addFeatureButton(String label, ActionListener listener) {
        JButton button = new JButton(label);
        button.addActionListener(listener);
        add(button);
    }

}