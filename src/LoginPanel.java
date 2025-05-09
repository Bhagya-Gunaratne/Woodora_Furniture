import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LoginPanel extends JPanel {
    public LoginPanel() {
        setLayout(new BorderLayout());
        setBackground(new Color(0x9cdccc));

        JPanel loginBodyLeft = new JPanel(new BorderLayout());
        loginBodyLeft.setBackground(new Color(0x64A48C));

        JPanel loginBodyRight = new JPanel();
        loginBodyRight.setLayout(new GridBagLayout());
        loginBodyRight.setPreferredSize(new Dimension(450, loginBodyRight.getPreferredSize().height));
        loginBodyRight.setBackground(new Color(0xFFFFFF));
        loginBodyRight.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        JPanel formContainer = new JPanel();
        formContainer.setLayout(new BoxLayout(formContainer, BoxLayout.Y_AXIS));
        formContainer.setOpaque(false);
        formContainer.setMaximumSize(new Dimension(350, Integer.MAX_VALUE));

        // Logo Panel
        ImageIcon originalIcon = new ImageIcon(getClass().getResource("Woodora-logo.png"));
        Image scaledImage = originalIcon.getImage().getScaledInstance(600, 200, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        JLabel logoImageLabel = new JLabel(scaledIcon);
        logoImageLabel.setBorder(BorderFactory.createEmptyBorder(0, 40, 50, 0));
        JPanel bottomLeftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        bottomLeftPanel.setOpaque(false);
        bottomLeftPanel.add(logoImageLabel);

        // Welcome Text
        JLabel headTxt = new JLabel("WELCOME");
        headTxt.setFont(new Font("Arial", Font.BOLD, 26));
        headTxt.setAlignmentX(Component.CENTER_ALIGNMENT);
        headTxt.setForeground(new Color(0x333333));

        // Email Field
        JTextField emailInput = new JTextField("Email");
        emailInput.setPreferredSize(new Dimension(300, 50));
        emailInput.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
        emailInput.setFont(new Font("SansSerif", Font.PLAIN, 16));
        emailInput.setForeground(Color.GRAY);

        // Clear placeholder on focus
        emailInput.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                if (emailInput.getText().equals("Email")) {
                    emailInput.setText("");
                    emailInput.setForeground(Color.BLACK);
                }
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                if (emailInput.getText().isEmpty()) {
                    emailInput.setForeground(Color.GRAY);
                    emailInput.setText("Email");
                }
            }
        });

        // Password Field
        JPasswordField passwordInput = new JPasswordField("Password");
        passwordInput.setPreferredSize(new Dimension(300, 50));
        passwordInput.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
        passwordInput.setFont(new Font("SansSerif", Font.PLAIN, 16));
        passwordInput.setForeground(Color.GRAY);

        // Clear placeholder on focus
        passwordInput.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                if (String.valueOf(passwordInput.getPassword()).equals("Password")) {
                    passwordInput.setText("");
                    passwordInput.setEchoChar('•');
                    passwordInput.setForeground(Color.BLACK);
                }
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                if (String.valueOf(passwordInput.getPassword()).isEmpty()) {
                    passwordInput.setForeground(Color.GRAY);
                    passwordInput.setText("Password");
                    passwordInput.setEchoChar((char)0);
                }
            }
        });

        // Set no echo char for placeholder
        passwordInput.setEchoChar((char)0);

        // Forgot Password
        JLabel forgotPass = new JLabel("Forgot Password?");
        forgotPass.setFont(new Font("Arial", Font.PLAIN, 14));
        forgotPass.setForeground(Color.BLUE);
        forgotPass.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 2, 10, 0));
        buttonPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 60));

        // REGISTER Button
        JButton registerBtn = new JButton("REGISTER");
        registerBtn.setFont(new Font("SansSerif", Font.BOLD, 16));
        registerBtn.setBackground(new Color(0x64A48C));
        registerBtn.setForeground(Color.WHITE);
        registerBtn.setFocusable(false);
        registerBtn.setPreferredSize(new Dimension(100, 40));
        registerBtn.setMinimumSize(new Dimension(100, 40));
        registerBtn.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));

        // LOGIN Button
        JButton loginBtn = new JButton("LOGIN");
        loginBtn.setFont(new Font("SansSerif", Font.BOLD, 16));
        loginBtn.setBackground(new Color(0x64A48C));
        loginBtn.setForeground(Color.WHITE);
        loginBtn.setFocusable(false);
        loginBtn.setPreferredSize(new Dimension(100, 40));
        loginBtn.setMinimumSize(new Dimension(100, 40));
        loginBtn.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));


        buttonPanel.add(registerBtn);
        buttonPanel.add(loginBtn);

        // Add to form container
        formContainer.add(headTxt);
        formContainer.add(Box.createRigidArea(new Dimension(0, 30)));
        formContainer.add(emailInput);
        formContainer.add(Box.createRigidArea(new Dimension(0, 20)));
        formContainer.add(passwordInput);
        formContainer.add(Box.createRigidArea(new Dimension(0, 20)));
        formContainer.add(forgotPass);
        formContainer.add(Box.createRigidArea(new Dimension(0, 30)));
        formContainer.add(buttonPanel);

        loginBodyRight.add(formContainer, new GridBagConstraints());

        // Left panel logo
        loginBodyLeft.add(bottomLeftPanel, BorderLayout.SOUTH);

        // Add to main panel
        add(loginBodyLeft, BorderLayout.CENTER);
        add(loginBodyRight, BorderLayout.EAST);

        // Event Listeners
        loginBtn.addActionListener(e -> {
            String name = emailInput.getText();
            String pass = String.valueOf(passwordInput.getPassword());
            if(name.equalsIgnoreCase("admin") && pass.equalsIgnoreCase("admin")){
                MainFrame.cardLayout.show(MainFrame.cards, "home");
            }else{
                headTxt.setText("Invalid Credentials");
            }
        });

        registerBtn.addActionListener(e -> {
            MainFrame.cardLayout.show(MainFrame.cards, "register");
        });

        forgotPass.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                MainFrame.cardLayout.show(MainFrame.cards, "forgot");
            }
        });
    }
}