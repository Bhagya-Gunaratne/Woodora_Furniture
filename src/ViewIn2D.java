import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.util.FPSAnimator;

import javax.swing.*;
import java.awt.*;

public class ViewIn2D {
    RenderParams renderParams;
    private JPanel panel;

    public ViewIn2D(RenderParams renderParams) {
        this.renderParams = renderParams;
        renderParams.setId(Product.currentId);
        initialize();
    }

    public void initialize() {

        final GLProfile profile = GLProfile.get(GLProfile.GL2);
        GLCapabilities caps = new GLCapabilities(profile);
        GLCanvas canvas = new GLCanvas(caps);

        Render2D render2D = new Render2D(renderParams);

        canvas.addKeyListener(render2D);
        canvas.addGLEventListener(render2D);

        final FPSAnimator animator = new FPSAnimator(canvas, 200, true);

        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JPanel topView = new JPanel();
        topView.setLayout(new BorderLayout());

        JLabel heading = new JLabel("FURNITURE IN 2D VIEW");
        heading.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 0));
        heading.setFont(new Font("SansSerif", Font.BOLD, 22));
        heading.setForeground(Color.white);
        heading.setHorizontalAlignment(SwingConstants.CENTER);

        JButton editBtn = new JButton("EDIT MODEL");
        editBtn.setBorder(BorderFactory.createEmptyBorder(8, 55, 10, 55));
        editBtn.setFont(new Font("SansSerif", Font.BOLD, 18));
        editBtn.setBackground(new Color(0x206351));
        editBtn.setForeground(Color.white);

        editBtn.addActionListener(e -> {
            DesignSetupObject designSetupObject = new DesignSetupObject("2D", renderParams);
            designSetupObject.initialize("2D");
            MainFrame.cards.add(designSetupObject.getPanelDesSetOBJ(), "edit2d");
            MainFrame.cardLayout.show(MainFrame.cards, "edit2d");
        });

        JButton backBtn = new JButton("\ud83c\udfe0 HOME");
        backBtn.setBorder(BorderFactory.createEmptyBorder(8, 30, 10, 30));
        backBtn.setFont(new Font("SansSerif", Font.BOLD, 18));
        backBtn.setBackground(new Color(0x206351));
        backBtn.setForeground(Color.WHITE);

        backBtn.addActionListener(e -> {
            MainFrame.cardLayout.show(MainFrame.cards, "home");
        });

        JPanel btnPanel = new JPanel();
        btnPanel.setBackground(new Color(0x64a48c));
        btnPanel.setLayout(new BoxLayout(btnPanel, BoxLayout.X_AXIS));

        btnPanel.add(Box.createHorizontalGlue());
        btnPanel.add(editBtn);
        btnPanel.add(Box.createHorizontalStrut(10));
        btnPanel.add(backBtn);
        btnPanel.add(Box.createHorizontalGlue());

        JPanel navPan = new JPanel(new BorderLayout());
        navPan.setBorder(BorderFactory.createEmptyBorder(20, 25, 20, 25));
        navPan.setBackground(new Color(0x64a48c));
        navPan.add(heading, BorderLayout.WEST);
        navPan.add(btnPanel, BorderLayout.EAST);

        topView.add(navPan, BorderLayout.NORTH);

        JPanel bothPanels = new JPanel(new BorderLayout());

        JPanel model = new JPanel(new BorderLayout());
        model.add(canvas);
        model.setPreferredSize(new Dimension(2000, 1500));

        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.Y_AXIS));
        controlPanel.setBackground(Color.LIGHT_GRAY);

        JPanel ObjectScaleSet = new JPanel(new GridLayout(2, 1));
        ObjectScaleSet.setBackground(new Color(0x64a48c));
        ObjectScaleSet.setPreferredSize(new Dimension(280, 70));
        JLabel objectscale = new JLabel("FURNITURE RESIZER");
        objectscale.setBorder(BorderFactory.createEmptyBorder(0, 15, 0, 0));
        objectscale.setFont(new Font("SansSerif", Font.BOLD, 18));
        objectscale.setForeground(Color.WHITE);

        JPanel scaleBtnSet = new JPanel(new GridLayout(0, 2, 5, 0));
        scaleBtnSet.setBackground(new Color(0x64a48c));
        scaleBtnSet.setBorder(BorderFactory.createEmptyBorder(0, 15, 15, 10));

        JButton minusBtn = new JButton("-");
        minusBtn.setFont(new Font("SansSerif", Font.BOLD, 20));
        minusBtn.setPreferredSize(new Dimension(50, 40));
        minusBtn.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        minusBtn.setBackground(new Color(0x206351));
        minusBtn.setForeground(Color.WHITE);
        minusBtn.addActionListener(e -> {
            renderParams.setScaleVal(renderParams.getScaleVal() - 0.05f);
            Render2D.ScaleVal -= 0.05f;
        });

        JButton plusBtn = new JButton("+");
        plusBtn.setFont(new Font("SansSerif", Font.BOLD, 20));
        plusBtn.setPreferredSize(new Dimension(50, 40));
        plusBtn.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        plusBtn.setBackground(new Color(0x206351));
        plusBtn.setForeground(Color.WHITE);
        plusBtn.addActionListener(e -> {
            renderParams.setScaleVal(renderParams.getScaleVal() + 0.05f);
            Render2D.ScaleVal += 0.05f;
        });

        scaleBtnSet.add(minusBtn);
        scaleBtnSet.add(plusBtn);

        ObjectScaleSet.add(objectscale);
        ObjectScaleSet.add(scaleBtnSet);

        JPanel instructionPanel = new JPanel();
        instructionPanel.setLayout(new BoxLayout(instructionPanel, BoxLayout.Y_AXIS));
        instructionPanel.setBackground(Color.WHITE);
        instructionPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(0x206351), 2),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));

        JLabel instructionTitle = new JLabel("USER GUIDE");
        instructionTitle.setFont(new Font("SansSerif", Font.BOLD, 16));
        instructionTitle.setForeground(new Color(0x206351));
        instructionTitle.setAlignmentX(Component.LEFT_ALIGNMENT);

        JTextArea instructionContent = new JTextArea();
        instructionContent.setText(
                "Use arrow keys to move the Furniture\n" +
                        "Use '+' and '-' to resize the furniture\n" +
                        "Press 'EDIT MODEL' to modify\n" +
                        "Click 'HOME' to return to main menu"
        );
        instructionContent.setFont(new Font("SansSerif", Font.PLAIN, 14));
        instructionContent.setEditable(false);
        instructionContent.setOpaque(false);
        instructionContent.setFocusable(false);
        instructionContent.setBorder(null);
        instructionContent.setAlignmentX(Component.LEFT_ALIGNMENT);

        instructionPanel.add(instructionTitle);
        instructionPanel.add(Box.createVerticalStrut(5));
        instructionPanel.add(instructionContent);

        JPanel scaleAndInstruction = new JPanel();
        scaleAndInstruction.setLayout(new BoxLayout(scaleAndInstruction, BoxLayout.X_AXIS));
        scaleAndInstruction.setBackground(new Color(0x64a48c));
        scaleAndInstruction.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        scaleAndInstruction.add(ObjectScaleSet);
        scaleAndInstruction.add(Box.createHorizontalStrut(20));
        scaleAndInstruction.add(instructionPanel);

        controlPanel.add(scaleAndInstruction);

        bothPanels.add(model, BorderLayout.CENTER);
        bothPanels.add(controlPanel, BorderLayout.SOUTH);

        topView.add(bothPanels);
        panel.add(topView);
        animator.start();
    }

    public JPanel getPanel2D() {
        return panel;
    }
}
