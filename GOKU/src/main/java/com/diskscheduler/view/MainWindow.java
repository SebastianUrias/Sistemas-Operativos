package com.diskscheduler.view;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.EmptyBorder;

public class MainWindow extends JFrame {
    //Colores
    private static final Color BACKGROUND_COLOR = new Color(33, 33, 33);
    private static final Color PANEL_COLOR = new Color(45, 45, 45);
    private static final Color TEXT_COLOR = new Color(240, 240, 240);
    private static final Color ACCENT_COLOR = new Color(0, 150, 255);

    private JComboBox<String> algorithmSelector;
    private JButton generateButton;
    private JButton simulateButton;
    private JButton clearButton;
    private JTextArea logArea;
    private JPanel diskPanel;

    public MainWindow() {
        setTitle("Simulador de Planificación de Disco");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 700);
        getContentPane().setBackground(BACKGROUND_COLOR);
        initComponents();
    }

    private void initComponents() {
        //Principal
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBackground(BACKGROUND_COLOR);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        //Controles
        JPanel controlPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        controlPanel.setBackground(PANEL_COLOR);
        controlPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(ACCENT_COLOR, 1),
            BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));

        JLabel algoLabel = new JLabel("Algoritmo:");
        algoLabel.setForeground(TEXT_COLOR);
        algoLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        
        String[] algorithms = {
            "FCFS", "SSTF", "SCAN", "SCAN de N Pasos", "C-SCAN", "Eschenbach"
        };
        algorithmSelector = new JComboBox<String>(algorithms);
        styleComboBox(algorithmSelector);
        
        generateButton = new JButton("Generar Peticiones");
        simulateButton = new JButton("Simular");
        clearButton = new JButton("Limpiar Historial");
        
        styleButton(generateButton);
        styleButton(simulateButton);
        styleButton(clearButton);

        controlPanel.add(algoLabel);
        controlPanel.add(algorithmSelector);
        controlPanel.add(Box.createHorizontalStrut(20)); //Espacio 
        controlPanel.add(generateButton);
        controlPanel.add(simulateButton);
        controlPanel.add(clearButton);

        //Panel de visualizacion del disco
        diskPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                drawDisk(g);
            }
        };
        diskPanel.setBackground(PANEL_COLOR);
        diskPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(ACCENT_COLOR, 1),
            BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
        diskPanel.setPreferredSize(new Dimension(500, 500));

        //Panel para el log 
        JPanel logPanel = new JPanel(new BorderLayout());
        logPanel.setBackground(PANEL_COLOR);
        logPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
        
        //Titulo del log
        JLabel logTitle = new JLabel("Registro de Operaciones");
        logTitle.setForeground(TEXT_COLOR);
        logTitle.setFont(new Font("Segoe UI", Font.BOLD, 16));
        logTitle.setHorizontalAlignment(SwingConstants.CENTER);
        logTitle.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        
        //Area de log
        logArea = new JTextArea();
        logArea.setEditable(false);
        logArea.setBackground(new Color(30, 30, 30));
        logArea.setForeground(TEXT_COLOR);
        logArea.setFont(new Font("Consolas", Font.PLAIN, 14));
        logArea.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        JScrollPane scrollPane = new JScrollPane(logArea);
        scrollPane.setPreferredSize(new Dimension(300, 500));
        scrollPane.setBorder(BorderFactory.createLineBorder(ACCENT_COLOR, 1));
        scrollPane.getViewport().setBackground(new Color(30, 30, 30));
        
        logPanel.add(logTitle, BorderLayout.NORTH);
        logPanel.add(scrollPane, BorderLayout.CENTER);

        //Agregar componentes al panel principal
        mainPanel.add(controlPanel, BorderLayout.NORTH);
        mainPanel.add(diskPanel, BorderLayout.CENTER);
        mainPanel.add(logPanel, BorderLayout.EAST);

        //Agregar panel principal a la ventana
        add(mainPanel);

        //Configurar eventos de botones
        setupEvents();
    }

    private void drawDisk(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        int centerX = diskPanel.getWidth() / 2;
        int centerY = diskPanel.getHeight() / 2;
        int maxRadius = Math.min(centerX, centerY) - 50; 
        
        //Dibujar fondo del disco
        g2d.setColor(new Color(20, 20, 20));
        g2d.fillOval(centerX - maxRadius, centerY - maxRadius, maxRadius * 2, maxRadius * 2);
        
        //Dibujar circulos concentricos para representar cilindros
        g2d.setStroke(new BasicStroke(1.0f));
        for (int i = 0; i < 40; i++) {
            int radius = maxRadius - (i * maxRadius / 40);
            g2d.setColor(new Color(ACCENT_COLOR.getRed(), ACCENT_COLOR.getGreen(), 
                                 ACCENT_COLOR.getBlue(), 50));
            g2d.drawOval(centerX - radius, centerY - radius, radius * 2, radius * 2);
            
            //Agregar numeros de cilindro cada 5 cilindros
            if (i % 5 == 0) {
                g2d.setColor(TEXT_COLOR);
                g2d.setFont(new Font("Segoe UI", Font.PLAIN, 10));
                String num = String.valueOf(i);
                //Dibujar numero en cuatro posiciones para mejor visibilidad
                g2d.drawString(num, centerX + radius + 5, centerY);
                g2d.drawString(num, centerX - radius - 15, centerY);
                g2d.drawString(num, centerX, centerY + radius + 15);
                g2d.drawString(num, centerX, centerY - radius - 5);
            }
        }

        //Dibujar lineas y numeros para representar sectores
        for (int i = 0; i < 16; i++) {
            double angle = i * Math.PI / 8;
            int x2 = centerX + (int)(maxRadius * Math.cos(angle));
            int y2 = centerY + (int)(maxRadius * Math.sin(angle));
            
            //Lineas de sector
            g2d.setColor(new Color(TEXT_COLOR.getRed(), TEXT_COLOR.getGreen(), 
                                 TEXT_COLOR.getBlue(), 100));
            g2d.drawLine(centerX, centerY, x2, y2);
            
            //Numeros de sector
            double labelAngle = i * Math.PI / 8;
            int labelRadius = maxRadius + 20;
            int textX = centerX + (int)(labelRadius * Math.cos(labelAngle));
            int textY = centerY + (int)(labelRadius * Math.sin(labelAngle));
            
            g2d.setColor(TEXT_COLOR);
            g2d.setFont(new Font("Segoe UI", Font.BOLD, 12));
            String sectorNum = String.valueOf(i);
            
            //Ajustar posicion del texto segun el angulo
            FontMetrics fm = g2d.getFontMetrics();
            int textWidth = fm.stringWidth(sectorNum);
            int textHeight = fm.getHeight();
            textX -= textWidth / 2;
            textY += textHeight / 2;
            
            g2d.drawString(sectorNum, textX, textY);
        }
        
        //Dibujar punto central y cabezal simulado
        g2d.setColor(ACCENT_COLOR);
        g2d.setStroke(new BasicStroke(2.0f));
        g2d.fillOval(centerX - 4, centerY - 4, 8, 8);
        
        //Simular un cabezal en posicion aleatoria
        int currentRadius = maxRadius - (20 * maxRadius / 40);
        double sectorAngle = 5 * Math.PI / 8; // Sector 5
        int headX = centerX + (int)(currentRadius * Math.cos(sectorAngle));
        int headY = centerY + (int)(currentRadius * Math.sin(sectorAngle));
        
        //Dibujar cabezal
        g2d.setColor(Color.RED);
        g2d.drawOval(headX - 6, headY - 6, 12, 12);
        g2d.setStroke(new BasicStroke(1.0f));
        g2d.drawLine(headX - 8, headY, headX + 8, headY);
        g2d.drawLine(headX, headY - 8, headX, headY + 8);
    }

    private void setupEvents() {
        generateButton.addActionListener(e -> {
            //Implementar generacion de peticiones
            logArea.append("Generando nuevas peticiones...\n");
        });

        simulateButton.addActionListener(e -> {
            //Implementar simulacion
            String selectedAlgorithm = (String) algorithmSelector.getSelectedItem();
            logArea.append("Iniciando simulación con " + selectedAlgorithm + "\n");
        });

        clearButton.addActionListener(e -> {
            //Implementar limpieza de historial
            logArea.setText("");
        });
    }

    private void styleButton(JButton button) {
        button.setBackground(new Color(200, 200, 200));  //Gris 
        button.setForeground(new Color(0, 0, 0));      //Negro
        button.setFont(new Font("Segoe UI", Font.BOLD, 13));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(ACCENT_COLOR, 2),
            BorderFactory.createEmptyBorder(10, 20, 10, 20)
        ));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(220, 220, 220));  //Gris
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(200, 200, 200));  //Gris
            }
        });
    }

    private void styleComboBox(JComboBox<String> comboBox) {
        comboBox.setBackground(new Color(200, 200, 200));  //Gris 
        comboBox.setForeground(new Color(0, 0, 0));       //Negro
        comboBox.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        comboBox.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(ACCENT_COLOR, 2),
            BorderFactory.createEmptyBorder(8, 12, 8, 12)
        ));
        
        comboBox.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value,
                    int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (isSelected) {
                    setBackground(new Color(220, 220, 220));  //Gris
                    setForeground(new Color(0, 0, 0));       //Negro
                } else {
                    setBackground(new Color(200, 200, 200));  //Gris
                    setForeground(new Color(0, 0, 0));       //Negro
                }
                return this;
            }
        });
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            //Configurar el aspecto de los JOptionPane
            UIManager.put("OptionPane.background", BACKGROUND_COLOR);
            UIManager.put("Panel.background", BACKGROUND_COLOR);
            UIManager.put("OptionPane.messageForeground", TEXT_COLOR);
        } catch (Exception e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(() -> {
            LoginScreen loginScreen = new LoginScreen();
            loginScreen.setVisible(true);
        });
    }
}