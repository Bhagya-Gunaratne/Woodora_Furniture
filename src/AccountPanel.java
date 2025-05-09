import javax.swing.*;
import java.awt.*;

public class AccountPanel extends JPanel {
    public AccountPanel() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        // Header section with title and profile image (outside main box)
        JPanel headerSection = new JPanel(new BorderLayout());
        headerSection.setOpaque(false);
        headerSection.setBorder(BorderFactory.createEmptyBorder(20, 20, 10, 20));

        JLabel myaccountLabel = new JLabel("MY ACCOUNT");
        myaccountLabel.setFont(new Font("Arial", Font.BOLD, 25));
        myaccountLabel.setForeground(Color.BLACK);
        myaccountLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel titlePanel = new JPanel(new BorderLayout());
        titlePanel.setOpaque(false);
        titlePanel.add(myaccountLabel, BorderLayout.CENTER);

        ImageIcon userProfile = new ImageIcon(getClass().getResource("user-profile1.png"));
        Image scaledUserProfile = userProfile.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
        ImageIcon scaledUserProfileIcon = new ImageIcon(scaledUserProfile);
        JLabel UserProfileLabel = new JLabel(scaledUserProfileIcon);
        UserProfileLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel imagePanel = new JPanel();
        imagePanel.setOpaque(false);
        imagePanel.add(UserProfileLabel);

        headerSection.add(titlePanel, BorderLayout.NORTH);
        headerSection.add(imagePanel, BorderLayout.CENTER);

        add(headerSection, BorderLayout.NORTH);

        // Main box for account information
        JPanel accountBody = new JPanel(new BorderLayout());
        accountBody.setBackground(new Color(0x64A48C));
        accountBody.setBorder(BorderFactory.createEmptyBorder(30, 330, 50, 330));

        JPanel myaccInfo = new JPanel();
        myaccInfo.setLayout(new BoxLayout(myaccInfo, BoxLayout.Y_AXIS));
        myaccInfo.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        String[][] infoData = {
                {"Your Email:", "thilaklalith@gmail.com"},
                {"Your Name:", "Thilak Seneviratne"},
                {"Phone Number:", "+94 712345678"},
                {"Username:", "thilak_s"},
                {"Account Created:", "2022-05-14"},
                {"User:", "Customer"},
                {"Last Login:", "2025-05-05 18:30"}
        };

        for (String[] row : infoData) {
            JPanel rowPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            JLabel label = new JLabel(row[0]);
            label.setFont(new Font("Arial", Font.PLAIN, 15));
            JLabel value = new JLabel(row[1]);
            value.setFont(new Font("Arial", Font.BOLD, 16));
            rowPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
            rowPanel.add(label);
            rowPanel.add(Box.createHorizontalStrut(10));
            rowPanel.add(value);
            myaccInfo.add(rowPanel);
            myaccInfo.add(Box.createVerticalStrut(10));
        }

        JPanel btnset = new JPanel(new GridLayout(2, 1));
        JTextArea support = new JTextArea("For security reasons, account details cannot be edited here. Please reach out to our support team for any changes.");
        support.setFont(new Font("Arial", Font.PLAIN | Font.ITALIC, 16));
        support.setWrapStyleWord(true);
        support.setLineWrap(true);
        support.setOpaque(false);
        support.setEditable(false);
        support.setFocusable(false);
        support.setBorder(BorderFactory.createEmptyBorder(0, 20, 20, 20));

        JButton logoutBtn = new JButton("LOG OUT");
        logoutBtn.setFont(new Font("SansSerif", Font.BOLD, 18));
        logoutBtn.setBorder(BorderFactory.createEmptyBorder(12, 10, 15, 10));
        logoutBtn.setForeground(Color.WHITE);
        logoutBtn.setBackground(new Color(0x206351));
        logoutBtn.setFocusable(false);

        logoutBtn.addActionListener(e -> MainFrame.cardLayout.show(MainFrame.cards, "welcome"));

        btnset.add(support);
        btnset.add(logoutBtn);

        JPanel accountContent = new JPanel(new BorderLayout());
        accountContent.setOpaque(false);
        accountContent.add(myaccInfo, BorderLayout.CENTER);
        accountContent.add(btnset, BorderLayout.SOUTH);

        accountBody.add(accountContent, BorderLayout.CENTER);

        add(accountBody, BorderLayout.CENTER);
    }
}