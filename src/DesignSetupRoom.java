
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.util.FPSAnimator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.lang.reflect.Type;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class DesignSetupRoom {
    private JPanel panel;

    String roomId = "1";

    public DesignSetupRoom(String twoOrthreeD, RenderParams renderParams) {
        initialize(twoOrthreeD, renderParams);
    }

    public void initialize(String twoOrthreeD, RenderParams renderParams) {

        final GLProfile profile = GLProfile.get(GLProfile.GL2);
        GLCapabilities caps = new GLCapabilities(profile);
        GLCanvas canvas = new GLCanvas(caps);

        if (twoOrthreeD.equals("2D")) {
            Render2D render2D = new Render2D(renderParams);

            canvas.addKeyListener(render2D);
            canvas.addGLEventListener(render2D);
        } else {
            Render render = new Render(renderParams);

            canvas.addKeyListener(render);
            canvas.addGLEventListener(render);
        }

        final FPSAnimator animator = new FPSAnimator(canvas, 300, true);

        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Color.PINK);
        panel.setPreferredSize(new Dimension(1200, 720));

        JPanel topView = new JPanel();
        topView.setLayout(new BorderLayout());
        topView.setBackground(Color.LIGHT_GRAY);

        JLabel heading = new JLabel("EDIT ROOM IN " + twoOrthreeD);
        heading.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
        heading.setFont(new Font("SansSerif", Font.BOLD, 22));
        heading.setForeground(Color.white);
        heading.setHorizontalAlignment(SwingConstants.CENTER);

        JButton viewBtn = new JButton("BACK TO CONTROLLER");
        viewBtn.setBorder(BorderFactory.createEmptyBorder(8, 25, 10, 25));
        viewBtn.setFont(new Font("SansSerif", Font.BOLD, 18));
        viewBtn.setBackground(new Color(0x206351));
        viewBtn.setForeground(Color.WHITE);

        viewBtn.addActionListener(e -> {
            renderParams.setId(Product.currentId);
            if (twoOrthreeD.equals("2D")) {
                ViewIn2D viewIn2D = new ViewIn2D(renderParams);
                viewIn2D.initialize();

                MainFrame.cardLayout.show(MainFrame.cards, "2d");
            } else {
                ViewIn3D viewIn3D = new ViewIn3D(renderParams);
                viewIn3D.initialize();

                MainFrame.cardLayout.show(MainFrame.cards, "3d");
            }
        });

        JButton backBtn = new JButton("RENDER ROOM");
        backBtn.setBorder(BorderFactory.createEmptyBorder(8, 10, 10, 10));
        backBtn.setFont(new Font("SansSerif", Font.BOLD, 18));
        backBtn.setBackground(new Color(0x206351));
        backBtn.setForeground(Color.WHITE);

        JPanel btnPanel = new JPanel();
        btnPanel.setBackground(new Color(0x64a48c));
        btnPanel.setLayout(new BoxLayout(btnPanel, BoxLayout.X_AXIS));

        btnPanel.add(Box.createHorizontalGlue());
        btnPanel.add(viewBtn);
        btnPanel.add(Box.createHorizontalStrut(10));
        btnPanel.add(backBtn);
        btnPanel.add(Box.createHorizontalGlue());

        JPanel bothPanels = new JPanel(new BorderLayout());
//        bothPanels.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JPanel model = new JPanel(new BorderLayout());
        model.setPreferredSize(new Dimension(700, 200));
        model.add(canvas);

        JPanel variationPanel = new JPanel(new BorderLayout());
        variationPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        variationPanel.setPreferredSize(new Dimension(300, 150));

        variationPanel.setBackground(new Color(0x64a48c));
//        variationPanel.setPreferredSize(new Dimension(300, variationPanel.getPreferredSize().height));

        JPanel RoomHeightSet = new JPanel(new GridLayout(2, 1));
        RoomHeightSet.setBackground(new Color(0x64a48c));
        RoomHeightSet.setPreferredSize(new Dimension(220, 100));
        RoomHeightSet.setBorder(BorderFactory.createEmptyBorder(20, 10, 30, 0));
        JLabel roomheight = new JLabel("HEIGHT OF THE ROOM: ");
        roomheight.setFont(new Font("SansSerif", Font.BOLD, 14));
        roomheight.setForeground(Color.WHITE);
        JTextField heightInput = new JTextField();
//        heightInput.setPreferredSize(new Dimension(50, 10));
        heightInput.setFont(new Font("SansSerif", Font.BOLD, 14));

        RoomHeightSet.add(roomheight);
        RoomHeightSet.add(heightInput);

        JPanel RoomLengthSet = new JPanel(new GridLayout(2, 1));
        RoomLengthSet.setBackground(new Color(0x64a48c));
        RoomLengthSet.setPreferredSize(new Dimension(220, 100));
        RoomLengthSet.setBorder(BorderFactory.createEmptyBorder(20, 20, 30, 0));
        JLabel roomlength = new JLabel("LENGTH OF THE ROOM: ");
        roomlength.setFont(new Font("SansSerif", Font.BOLD, 14));
        roomlength.setForeground(Color.WHITE);
        JTextField lengthInput = new JTextField();
//        lengthInput.setPreferredSize(new Dimension(50, 10));
        lengthInput.setFont(new Font("SansSerif", Font.BOLD, 14));

        RoomLengthSet.add(roomlength);
        RoomLengthSet.add(lengthInput);

        JPanel WallColorSet = new JPanel(new GridLayout(2, 1));
        WallColorSet.setBackground(new Color(0x64a48c));
        WallColorSet.setPreferredSize(new Dimension(220, 100));
        WallColorSet.setBorder(BorderFactory.createEmptyBorder(20, 10, 30, 0));
        JLabel wallcolor = new JLabel("COLOR OF THE WALL: ");
        wallcolor.setFont(new Font("SansSerif", Font.BOLD, 14));
        wallcolor.setForeground(Color.WHITE);
        String[] wallcolors = {"DEFAULT", "RED", "BLUE", "GREEN", "ORANGE"};
        JComboBox<String> WallColrOps = new JComboBox<>(wallcolors);
        WallColrOps.setFont(new Font("SansSerif", Font.BOLD, 14));

        WallColrOps.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String wallColor = (String) WallColrOps.getSelectedItem();

                if (wallColor.equals("RED")) {
                    Render2D.roomColors = new float[]{1, 0, 0};
                    Render.roomColor = new float[]{1, 0, 0};
                    renderParams.setRoomColors(new float[]{1, 0, 0});
                } else if (wallColor.equals("BLUE")) {
                    Render2D.roomColors = new float[]{0, 0, 1};
                    Render.roomColor = new float[]{0, 0, 1};
                    renderParams.setRoomColors(new float[]{0, 0, 1});
                } else if (wallColor.equals("GREEN")) {
                    Render2D.roomColors = new float[]{0, 1, 0};
                    Render.roomColor = new float[]{0, 1, 0};
                    renderParams.setRoomColors(new float[]{0, 1, 0});
                }
            }
        });

        WallColorSet.add(wallcolor);
        WallColorSet.add(WallColrOps);

        JPanel RoomShapeSet = new JPanel(new GridLayout(2, 1));
        RoomShapeSet.setBackground(new Color(0x64a48c));
        RoomShapeSet.setPreferredSize(new Dimension(220, 100));
        RoomShapeSet.setBorder(BorderFactory.createEmptyBorder(20, 10, 30, 0));
        JLabel roomshape = new JLabel("SHAPE OF THE ROOM: ");
        roomshape.setFont(new Font("SansSerif", Font.BOLD, 14));
        roomshape.setForeground(Color.WHITE);
        String[] roomshapes = {"DEFAULT", "SQAURE", "CORNER", "ROUND"};
        JComboBox<String> RoomShapeOps = new JComboBox<>(roomshapes);
        RoomShapeOps.setFont(new Font("SansSerif", Font.BOLD, 14));

        RoomShapeOps.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String roomShape = (String) RoomShapeOps.getSelectedItem();

                if (roomShape.equals("SQAURE")) {
                    Render2D.roomId = "1";
                    Render.roomId = "1";
                    renderParams.setRoomId("1");
                } else if (roomShape.equals("CORNER")) {
                    Render2D.roomId = "2";
                    Render.roomId = "2";
                    renderParams.setRoomId("2");
                } else if (roomShape.equals("ROUND")) {
                    Render2D.roomId = "3";
                    Render.roomId = "3";
                    renderParams.setRoomId("3");
                }
            }
        });

        RoomShapeSet.add(roomshape);
        RoomShapeSet.add(RoomShapeOps);

        JPanel gridPanel = new JPanel(new GridLayout(1, 2, 0, 0));
        gridPanel.setBackground(new Color(0x64a48c));
        gridPanel.add(RoomLengthSet);
        gridPanel.add(RoomHeightSet);
        gridPanel.add(RoomShapeSet);
        gridPanel.add(WallColorSet);

        JPanel combinepan = new JPanel(new BorderLayout());

        backBtn.addActionListener(e -> {

            String roomHeightVal = heightInput.getText();
            String roomWidthVal = lengthInput.getText();

            if (!roomHeightVal.isEmpty() && !roomWidthVal.isEmpty()) {
                try {
                    float height = Float.parseFloat(roomHeightVal);
                    float width = Float.parseFloat(roomWidthVal);
                    Render2D.roomDim = new float[]{width/10, height/10};
                    Render.roomSize = new float[]{width/10, height/10};
                    renderParams.setRoomDim(new float[]{width/10, height/10});
                } catch (NumberFormatException er) {
                    System.err.println("Error parsing input as float: " + er.getMessage());
                }
            } else {
                System.err.println("Please enter values for room height and width.");
            }
        });

        JPanel savesection = new JPanel(new GridLayout(1, 2));
        JTextField saveInput = new JTextField();
        saveInput.setPreferredSize(new Dimension(110, 50));
        saveInput.setFont(new Font("SansSerif", Font.BOLD, 16));
        JButton saveBtn = new JButton("SAVE");
        saveBtn.setBorder(BorderFactory.createEmptyBorder(8, 35, 10, 35));
        saveBtn.setFont(new Font("SansSerif", Font.BOLD, 16));
        saveBtn.setBackground(new Color(0x206351));
        savesection.setBorder(BorderFactory.createEmptyBorder(60,0,30,20));
        savesection.setBackground(new Color(0x64a48c));
        saveBtn.setForeground(Color.WHITE);
        savesection.add(saveInput);
        savesection.add(saveBtn);

        saveBtn.addActionListener(e -> {
            renderParams.setId(Product.currentId);
            if (twoOrthreeD.equals("2D")) {
                ViewIn2D viewIn2D = new ViewIn2D(renderParams);
                viewIn2D.initialize();
                MainFrame.cardLayout.show(MainFrame.cards, "2d");
            } else {
                ViewIn3D viewIn3D = new ViewIn3D(renderParams);
                viewIn3D.initialize();
                MainFrame.cardLayout.show(MainFrame.cards, "3d");
            }
            Template template = new Template();
            String saveName = saveInput.getText();

            //set save values
            template.setItemId(renderParams.getId());
            template.setRoomId(renderParams.getRoomId());

            //get date and time to store
            LocalDate currentDate = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String dateString = currentDate.format(formatter);
            template.setDate(dateString);

            LocalTime currentTime = LocalTime.now();
            DateTimeFormatter formatterTime = DateTimeFormatter.ofPattern("HH:mm:ss");
            String timeString = currentTime.format(formatterTime);
            template.setTime(timeString);

            template.setTemplateName(saveName);
            List<RenderParams> existingObjects = readJsonFromFile("saved.json");
            List<Template> existingObjectsTemp = readJsonFromFileTemp("templates.json");
            existingObjects.add(renderParams);
            existingObjectsTemp.add(template);
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String updatedJson = gson.toJson(existingObjects);
            String updatedJsonTemp = gson.toJson(existingObjectsTemp);

            URL fileUrl = getClass().getResource("saved.json");
            if (fileUrl != null) {
                writeJsonToFile(updatedJson, fileUrl);
            } else {
                System.out.println("Failed to get file URL.");
            }

            URL fileUrltemp = getClass().getResource("templates.json");
            if (fileUrl != null) {
                writeJsonToFile(updatedJsonTemp, fileUrl);
            } else {
                System.out.println("Failed to get file URL.");
            }

            writeJsonToFile(updatedJson, fileUrl);
            writeJsonToFile(updatedJsonTemp, fileUrltemp);
        });

        combinepan.add(savesection, BorderLayout.CENTER);

        variationPanel.add(gridPanel, BorderLayout.WEST);
        variationPanel.add(combinepan, BorderLayout.EAST);

        bothPanels.add(model, BorderLayout.CENTER);
        bothPanels.add(variationPanel, BorderLayout.SOUTH);

        JPanel navPan = new JPanel(new BorderLayout());
        navPan.setBorder(BorderFactory.createEmptyBorder(20, 25, 20, 25));
        navPan.setBackground(new Color(0x64a48c));
        navPan.add(heading, BorderLayout.WEST);
        navPan.add(btnPanel, BorderLayout.EAST);

        topView.add(navPan, BorderLayout.NORTH);
        topView.add(bothPanels);

        panel.add(topView);
        animator.start();
    }

    public List<RenderParams> readJsonFromFile(String jsonFilePath) {
        Gson gson = new Gson();
        Type renderParamsListType = new TypeToken<List<RenderParams>>() {}.getType();

        try (InputStreamReader reader = new InputStreamReader(getClass().getResourceAsStream(jsonFilePath))) {
            return gson.fromJson(reader, renderParamsListType);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Template> readJsonFromFileTemp(String jsonFilePath) {
        Gson gson = new Gson();
        Type renderParamsListType = new TypeToken<List<Template>>() {}.getType();

        try (InputStreamReader reader = new InputStreamReader(getClass().getResourceAsStream(jsonFilePath))) {
            return gson.fromJson(reader, renderParamsListType);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Write JSON data to file
    private static void writeJsonToFile(String json, URL fileUrl) {
        try (Writer writer = new FileWriter(fileUrl.toURI().getPath())) {
            writer.write(json);
            System.out.println("JSON data written to file: " + fileUrl.toURI().getPath());
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }


    public JPanel getPanelDesSetROOM() {
        return panel;
    }
}
