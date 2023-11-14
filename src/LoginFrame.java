import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class LoginFrame extends JFrame{
    JTextField usertf;
    JTextField pwtf;
    JButton button;
    ActionListener buttonlistener = new ButtonListener();

    public LoginFrame()
    {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        usertf = new JTextField();
        pwtf = new JTextField();
        button  = new JButton("OK!");
        add(usertf,BorderLayout.NORTH);
        add(pwtf,BorderLayout.CENTER);
        add(button,BorderLayout.SOUTH);
        pack();
    }

    class ButtonListener implements ActionListener
    {
        public void actionPerformed(ActionEvent ae)
        {
            for(Account account : Bank.accounts)
            {
                if(account.getUsername().equals(usertf.getText())&&account.getPassword().equals(pwtf.getText()))
                {
                    usertf.setText("JO");
                    pwtf.setText("JO");
                    break;
                }
                else
                {
                    usertf.setText("");
                    pwtf.setText("");
                }
            }
        }
    }
}
