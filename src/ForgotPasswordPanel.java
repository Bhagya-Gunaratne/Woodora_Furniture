import javax.swing.*;
import java.awt.*;

public class ForgotPasswordPanel extends JPanel {
    public ForgotPasswordPanel() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        // Header section with title
        JPanel headerSection = new JPanel(new BorderLayout());
        headerSection.setOpaque(false);
        headerSection.setBorder(BorderFactory.createEmptyBorder(70, 20, 50, 20));

        JLabel titleLabel = new JLabel("WOODORA FURNITURE");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 25));
        titleLabel.setForeground(Color.BLACK);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel titlePanel = new JPanel(new BorderLayout());
        titlePanel.setOpaque(false);
        titlePanel.add(titleLabel, BorderLayout.CENTER);

        headerSection.add(titlePanel, BorderLayout.NORTH);

        add(headerSection, BorderLayout.NORTH);

        // Main background panel
        JPanel forgotBody = new JPanel(new BorderLayout());
        forgotBody.setBackground(new Color(0x64A48C));
        forgotBody.setBorder(BorderFactory.createEmptyBorder(60, 330, 50, 330));

        // White form panel
        JPanel formContainer = new JPanel(new BorderLayout());
        formContainer.setBackground(Color.WHITE);
        formContainer.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        JPanel forgotForm = new JPanel();
        forgotForm.setLayout(new BoxLayout(forgotForm, BoxLayout.Y_AXIS));
        forgotForm.setOpaque(false);

        JLabel frgtPass = new JLabel("Forgot Your Password?");
        frgtPass.setFont(new Font("Arial", Font.BOLD, 20));
        frgtPass.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel frgtPassBody = new JLabel("<html>Enter the email address associated with your account. We'll send instructions to reset your password.</html>");
        frgtPassBody.setFont(new Font("Arial", Font.PLAIN, 15));
        frgtPassBody.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel email = new JLabel("Email Address:");
        email.setFont(new Font("Arial", Font.BOLD, 15));
        email.setAlignmentX(Component.LEFT_ALIGNMENT);

        JTextField emailInput = new JTextField();
        emailInput.setPreferredSize(new Dimension(100, 40));
        emailInput.setFont(new Font("SansSerif", Font.PLAIN, 16));

        forgotForm.add(frgtPass);
        forgotForm.add(Box.createVerticalStrut(15));
        forgotForm.add(frgtPassBody);
        forgotForm.add(Box.createVerticalStrut(30));
        forgotForm.add(email);
        forgotForm.add(Box.createVerticalStrut(8));
        forgotForm.add(emailInput);

        JPanel btnSet = new JPanel();
        btnSet.setLayout(new BoxLayout(btnSet, BoxLayout.Y_AXIS));
        btnSet.setOpaque(false);
        btnSet.setBorder(BorderFactory.createEmptyBorder(30, 0, 0, 0));

        JTextArea resendInfo = new JTextArea("Didn't get a code? Click RESEND to request another.");
        resendInfo.setFont(new Font("Arial", Font.PLAIN | Font.ITALIC, 14));
        resendInfo.setOpaque(false);
        resendInfo.setEditable(false);
        resendInfo.setFocusable(false);
        resendInfo.setWrapStyleWord(true);
        resendInfo.setLineWrap(true);
        resendInfo.setAlignmentX(Component.LEFT_ALIGNMENT);
        resendInfo.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));

        JButton submitBtn = new JButton("SUBMIT");
        submitBtn.setFont(new Font("SansSerif", Font.BOLD, 18));
        submitBtn.setForeground(Color.WHITE);
        submitBtn.setBackground(new Color(0x64A48C));
        submitBtn.setBorder(BorderFactory.createEmptyBorder(12, 10, 15, 10));
        submitBtn.setAlignmentX(Component.LEFT_ALIGNMENT);
        submitBtn.setFocusable(false);

        btnSet.add(resendInfo);
        btnSet.add(Box.createVerticalStrut(10));
        btnSet.add(submitBtn);

        formContainer.add(forgotForm, BorderLayout.NORTH);
        formContainer.add(btnSet, BorderLayout.SOUTH);

        forgotBody.add(formContainer, BorderLayout.CENTER);
        add(forgotBody, BorderLayout.CENTER);

        submitBtn.addActionListener(e -> MainFrame.cardLayout.show(MainFrame.cards, "login"));
    }
}
