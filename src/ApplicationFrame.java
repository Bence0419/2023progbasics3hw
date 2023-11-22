import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Alkalmazás ablak (ApplicationFrame) osztály
class ApplicationFrame extends JFrame {
    public ApplicationFrame(Bank b, Account account) {
        // Ablak beállításai

        setTitle("Banki Alkalmazás");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Layout beállítása
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        // Feature-ök hozzáadása
        addFeatureButton("Adataim", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame myProfilefFrame = new MyProfileFrame(account);
                myProfilefFrame.setVisible(true);
            }
        });

        addFeatureButton("Pénz befizetése", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame addMoneyFrame = new AddMoneyFrame(account);
                addMoneyFrame.setVisible(true);
            }
        });

        addFeatureButton("Pénz levétele", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame moneyCheckout = new MoneyCheckoutFrame(account);
                moneyCheckout.setVisible(true);
            }
        });

        addFeatureButton("Banki átutalás", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame transferFrame = new TransferFrame(b, account);
                transferFrame.setVisible(true);
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