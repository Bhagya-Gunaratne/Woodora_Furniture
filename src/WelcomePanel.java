import javax.swing.*;
import java.awt.*;

public class WelcomePanel extends JPanel {
    public WelcomePanel() {
        setLayout(new BorderLayout());
//        setBorder(BorderFactory.createEmptyBorder(70, 70, 70, 70));
        setBackground(Color.LIGHT_GRAY);

        JPanel welcomeBody = new JPanel(new BorderLayout());
        welcomeBody.setBackground(new Color(0x64A48C));

        JLabel welcomeLabel = new JLabel("WOODORA FURNITURE");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 40));
        welcomeLabel.setForeground(Color.WHITE);
        welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JButton continueBtn = new JButton("START");
        continueBtn.setFont(new Font("SansSerif", Font.BOLD, 18));
        continueBtn.setBorder(BorderFactory.createEmptyBorder(12, 10, 15, 10));
        continueBtn.setForeground(Color.WHITE);
        continueBtn.setBackground(new Color(0x206351));
        continueBtn.setFocusable(false);

        welcomeBody.add(welcomeLabel);
        welcomeBody.add(continueBtn, BorderLayout.SOUTH);

        add(welcomeBody, BorderLayout.CENTER);

        continueBtn.addActionListener(e -> {
            MainFrame.cardLayout.show(MainFrame.cards, "login");
        });
    }
}
