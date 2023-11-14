import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Alkalmazás ablak (ApplicationFrame) osztály
class ApplicationFrame extends JFrame {
    public ApplicationFrame() {
        // Ablak beállításai
        setTitle("Banki Alkalmazás");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Layout beállítása
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        // Feature-ök hozzáadása
        addFeatureButton("Új fiók hozzáadása", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Új fiók hozzáadása
                JOptionPane.showMessageDialog(ApplicationFrame.this, "Új fiók hozzáadása");
            }
        });

        addFeatureButton("Pénz felvétele", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Pénz felvétele
                JOptionPane.showMessageDialog(ApplicationFrame.this, "Pénz felvétele");
            }
        });

        addFeatureButton("Pénz levétele", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Pénz levétele
                JOptionPane.showMessageDialog(ApplicationFrame.this, "Pénz levétele");
            }
        });

        addFeatureButton("Banki átutalás", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Banki átutalás
                JOptionPane.showMessageDialog(ApplicationFrame.this, "Banki átutalás");
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