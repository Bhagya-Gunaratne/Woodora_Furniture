
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.List;

public class SavedTemplates {
    JPanel panel;
    private JFrame frame;
    private JPanel gridPanel, templateListPanel;
    List<RenderParams> _renderParams;
    static CardLayout cardLayout;
    static JPanel cards;

    public SavedTemplates() {
        initialize();
    }

    public JPanel initialize() {

        templateListPanel = new JPanel();
        templateListPanel.setBackground(Color.WHITE);
//        templateListPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        templateListPanel.setLayout(new BorderLayout(10, 5));

        JPanel HeaderMain = new JPanel(new BorderLayout());
        HeaderMain.setBackground(new Color(0x9cdccc));

        JPanel headerBody = new JPanel(new BorderLayout());
        headerBody.setBackground(new Color(0x64A48C));
        headerBody.setBorder(BorderFactory.createEmptyBorder(22, 22, 22, 22));

        JPanel navlinks = new JPanel(new FlowLayout());
        navlinks.setBorder(BorderFactory.createEmptyBorder(15, 650, 0, 0));
        navlinks.setBackground(new Color(0x64A48C));

        JLabel home = new JLabel("FURNITURE");
        home.setFont(new Font("Arial", Font.BOLD, 15));
        home.setForeground(Color.WHITE);
        
        home.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                MainFrame.cardLayout.show(MainFrame.cards, "home");
            }
        });

        JLabel savedtemps = new JLabel("MY TEMPLATES");
        savedtemps.setFont(new Font("Arial", Font.BOLD, 15));
        savedtemps.setForeground(Color.WHITE);

        savedtemps.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                MainFrame.cardLayout.show(MainFrame.cards, "template");
            }
        });

        navlinks.add(home);
        navlinks.add(Box.createHorizontalStrut(20));
        navlinks.add(savedtemps);

        ImageIcon WoodoraLogoIcon = new ImageIcon(getClass().getResource("Woodora-logo.png"));
        Image WoodorascaledImage = WoodoraLogoIcon.getImage().getScaledInstance(140, 55, Image.SCALE_SMOOTH);
        ImageIcon scaledWoodoraLogoIcon = new ImageIcon(WoodorascaledImage);
        JLabel WoodoraLogoLabel = new JLabel(scaledWoodoraLogoIcon);

        ImageIcon UserIcon = new ImageIcon(getClass().getResource("1177568.png"));
        Image UserIconImage = UserIcon.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        ImageIcon UserIconImageIcon = new ImageIcon(UserIconImage);
        JLabel UserIconLabel = new JLabel(UserIconImageIcon);
        UserIconLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                cardLayout.show(cards, "account");
            }
        });


        JPanel fullrowheader = new JPanel();
        fullrowheader.setBackground(new Color(0x64A48C));
        fullrowheader.setLayout(new BoxLayout(fullrowheader, BoxLayout.X_AXIS));
        fullrowheader.add(WoodoraLogoLabel);
        fullrowheader.add(navlinks);
        fullrowheader.add(UserIconLabel);

        headerBody.add(fullrowheader);
        HeaderMain.add(headerBody, BorderLayout.CENTER);

        gridPanel = new JPanel(new GridLayout(4, 4, 10, 10));
        gridPanel.setBackground(Color.WHITE);
        gridPanel.setBorder(BorderFactory.createEmptyBorder(5,10,0,10));

        List<Template> templates = loadProductsFromJson("templates.json");
        _renderParams = loadParamsFromJson("saved.json");
        int i = 0;
        if (templates != null) {
            for (Template template : templates) {
                addImagePanel(template, i);
                i++;
            }
        } else {
            System.err.println("Failed to load...");
        }

        templateListPanel.add(gridPanel);
        templateListPanel.add(HeaderMain, BorderLayout.NORTH);
        return templateListPanel;
    }

    public List<RenderParams> loadParamsFromJson(String jsonFilePath) {
        Gson gson = new Gson();
        Type renderParamsListType = new TypeToken<List<RenderParams>>() {}.getType();

        try (InputStreamReader reader = new InputStreamReader(getClass().getResourceAsStream(jsonFilePath))) {
            return gson.fromJson(reader, renderParamsListType);
        } catch (IOException e) {
            e.printStackTrace();
            return null; // Handle the exception as needed
        }
    }

    public List<Template> loadProductsFromJson(String jsonFilePath) {
        Gson gson = new Gson();
        Type renderParamsListType = new TypeToken<List<Template>>() {}.getType();

        try (InputStreamReader reader = new InputStreamReader(getClass().getResourceAsStream(jsonFilePath))) {
            return gson.fromJson(reader, renderParamsListType);
        } catch (IOException e) {
            e.printStackTrace();
            return null; // Handle the exception as needed
        }
    }

    private void addImagePanel(Template template, int index) {
        // Declare panel as a local variable
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
        panel.setBackground(new Color(0x64a48c));

        JPanel centerPanel = new JPanel(new GridLayout(5,1));
        centerPanel.setOpaque(false);

        JLabel nameLabel = new JLabel(template.getTemplateName());
        nameLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
        nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        nameLabel.setForeground(Color.WHITE);
        nameLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 8, 0));

        JLabel roomIdLabel = new JLabel("Room ID : " + template.getRoomId());
        roomIdLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
        roomIdLabel.setBorder(BorderFactory.createEmptyBorder(0,5,0,0));

        JLabel itemIdLabel = new JLabel("Item ID : " + template.getItemId());
        itemIdLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
        itemIdLabel.setBorder(BorderFactory.createEmptyBorder(0,5,0,0));

        JLabel dateLabel = new JLabel("Date : " + template.getDate());
        dateLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
        dateLabel.setBorder(BorderFactory.createEmptyBorder(0,5,0,0));

        JLabel timeLabel = new JLabel("Time : " + template.getTime());
        timeLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
        timeLabel.setBorder(BorderFactory.createEmptyBorder(0,5,0,0));

        roomIdLabel.setOpaque(true);
        itemIdLabel.setOpaque(true);
        dateLabel.setOpaque(true);
        timeLabel.setOpaque(true);

        centerPanel.add(nameLabel, BorderLayout.CENTER);
        centerPanel.add(roomIdLabel, BorderLayout.CENTER);
        centerPanel.add(itemIdLabel, BorderLayout.CENTER);
        centerPanel.add(dateLabel, BorderLayout.CENTER);
        centerPanel.add(timeLabel, BorderLayout.CENTER);

        panel.add(centerPanel);

        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ViewIn2D viewIn2D = new ViewIn2D(_renderParams.get(index));
                viewIn2D.initialize();
                MainFrame.cardLayout.show(MainFrame.cards, "2d");
                System.out.println(_renderParams.get(index).getId());
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                panel.setBackground(new Color(0x206351));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                panel.setBackground(new Color(0x64a48c));
            }
        });

        gridPanel.add(panel);
        panel.revalidate();
        panel.repaint();
    }


}
