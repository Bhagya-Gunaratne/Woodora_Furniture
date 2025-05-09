import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.util.FPSAnimator;

import javax.swing.*;
import java.awt.*;

public class ViewIn3D {
    private JPanel panel;
    RenderParams renderParams;

    public ViewIn3D(RenderParams _renderParams) {
        this.renderParams = _renderParams;
        initialize();
    }

    public void initialize() {

        final GLProfile profile = GLProfile.get(GLProfile.GL2);
        GLCapabilities caps = new GLCapabilities(profile);
        GLCanvas canvas = new GLCanvas(caps);

        Render render = new Render(renderParams);

        canvas.addKeyListener(render);
        canvas.addGLEventListener(render);

        final FPSAnimator animator = new FPSAnimator(canvas, 300, true);


        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Color.PINK);
        panel.setPreferredSize(new Dimension(1200, 720));

        JPanel topView = new JPanel();
        topView.setLayout(new BorderLayout());
        topView.setBackground(Color.LIGHT_GRAY);

        JLabel heading = new JLabel("FURNITURE IN 3D VIEW");
        heading.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
        heading.setFont(new Font("SansSerif", Font.BOLD, 22));
        heading.setForeground(Color.white);
        heading.setHorizontalAlignment(SwingConstants.CENTER);

        JButton editBtn = new JButton("EDIT MODEL");
        editBtn.setBorder(BorderFactory.createEmptyBorder(8, 55, 10, 55));
        editBtn.setFont(new Font("SansSerif", Font.BOLD, 18));
        editBtn.setBackground(new Color(0x206351));
        editBtn.setForeground(Color.white);

        editBtn.addActionListener(e -> {
            DesignSetupObject designSetupObject = new DesignSetupObject("3D", renderParams);
            designSetupObject.initialize("3D");

            MainFrame.cards.add(designSetupObject.getPanelDesSetOBJ(), "edit3d");
            MainFrame.cardLayout.show(MainFrame.cards, "edit3d");
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

        JPanel variationPanel = new JPanel();
        variationPanel.setBackground(new Color(0x206351));
//        variationPanel.setPreferredSize(new Dimension(300, variationPanel.getPreferredSize().height));

        JPanel instructionPanel = new JPanel();
        instructionPanel.setLayout(new BoxLayout(instructionPanel, BoxLayout.Y_AXIS));
        instructionPanel.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
        instructionPanel.setBackground(new Color(0x206351));

        JLabel instructionTxt1 = new JLabel("<html>" + "Control Model Using below Instructions" + "</html>");
        instructionTxt1.setFont(new Font("SansSerif", Font.BOLD, 12));
        instructionTxt1.setPreferredSize(new Dimension(280, instructionTxt1.getPreferredSize().height + 300 ));
        instructionTxt1.setVerticalAlignment(JLabel.TOP);
        instructionTxt1.setHorizontalAlignment(JLabel.LEFT);
        instructionTxt1.setForeground(Color.WHITE);

        JLabel instructionTxt2 = new JLabel(
                "<html>" +
                        "<b>Light Control</b><br>" +
                        "W - Move Light Up<br>" +
                        "S - Move Light Down<br>" +
                        "A - Move Light Left<br>" +
                        "D - Move Light Right<br>" +
                        "1 - Move Light Closer<br>" +
                        "2 - Move Light Farther<br><br>" +

                        "<b>Model Movement</b><br>" +
                        "Arrow Left/Right - Move Model Left/Right<br>" +
                        "Arrow Up/Down - Move Model Forward/Backward<br><br>" +

                        "<b>Vertical Movement</b><br>" +
                        "U - Raise Entire Object<br>" +
                        "I - Lower Entire Object<br>" +
                        "K - Move Object Up<br>" +
                        "L - Move Object Down<br><br>" +

                        "<b>Rotation</b><br>" +
                        "H - Rotate Counterclockwise<br>" +
                        "J - Rotate Clockwise<br><br>" +

                        "<b>Horizontal Object Movement</b><br>" +
                        "O - Move Object Left<br>" +
                        "P - Move Object Right<br><br>" +

                        "<b>Scaling</b><br>" +
                        "X - Increase Scale<br>" +
                        "Z - Decrease Scale<br><br>" +

                        "<b>Lighting</b><br>" +
                        "Space - Toggle Light On/Off" +
                        "</html>"
        );

        instructionTxt2.setFont(new Font("SansSerif", Font.BOLD, 12));
        instructionTxt2.setPreferredSize(new Dimension(280, instructionTxt2.getPreferredSize().height + 300 ));
        instructionTxt2.setVerticalAlignment(JLabel.TOP);
        instructionTxt2.setHorizontalAlignment(JLabel.LEFT);
        instructionTxt2.setForeground(Color.WHITE);

        instructionPanel.add(Box.createVerticalStrut(15));
        instructionPanel.add(instructionTxt1);
        instructionPanel.add(Box.createVerticalStrut(10));
        instructionPanel.add(instructionTxt2);

        variationPanel.add(instructionPanel);

        bothPanels.add(model, BorderLayout.CENTER);
        bothPanels.add(variationPanel, BorderLayout.EAST);

        topView.add(navPan, BorderLayout.NORTH);
        topView.add(bothPanels);

        panel.add(topView);
        animator.start();
    }

    public JPanel getPanel3D() {
        return panel;
    }
}
