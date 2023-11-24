import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

// Alkalmazás ablak (ApplicationFrame) osztály
class AdminFeatureButtonPanel extends JFrame {
    private Bank bank;
    
    public AdminFeatureButtonPanel(Bank b) {
        // Ablak beállításai
        this.bank = b;
        bank.deserializeAccounts("bankdata.dat");
        setTitle("Adminisztrátori alkalmazás");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        // Layout beállítása
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                bank.serializeAccounts("bankdata.dat");
            }
        });
        // Feature-ök hozzáadása
        addFeatureButton("Bankfiókok listázása", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame adminFrame = new AdminFrame(bank);
                adminFrame.setVisible(true);
            }
        });

        addFeatureButton("Új fiók hozzáadása", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame newAccountFrame = new NewAccountFrame(bank);
                newAccountFrame.setVisible(true);
            }
        });

        addFeatureButton("Pénz levétele", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Fasz");
            }
        });

        addFeatureButton("Banki átutalás", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Fasz");
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