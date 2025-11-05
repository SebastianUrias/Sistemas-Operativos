package com.diskscheduler.view;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LoginScreen extends JFrame {
    
    //Bordes redondeados
    private static class RoundedBorder extends AbstractBorder {
        private final Color color;
        private final int radius;
        
        RoundedBorder(Color color, int radius) {
            this.color = color;
            this.radius = radius;
        }
        
        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(color);
            g2.drawRoundRect(x, y, width - 1, height - 1, radius * 2, radius * 2);
            g2.dispose();
        }
        
        @Override
        public Insets getBorderInsets(Component c) {
            return new Insets(radius, radius, radius, radius);
        }
        
        @Override
        public Insets getBorderInsets(Component c, Insets insets) {
            insets.left = insets.right = insets.top = insets.bottom = radius;
            return insets;
        }
    }
    private static final Color TEXT_COLOR = new Color(240, 240, 240);
    private static final Color ACCENT_COLOR = new Color(0, 150, 255);

    private JTextField userField;
    private JPasswordField passwordField;
    private JButton loginButton;

    public LoginScreen() {
        setTitle("Login - Simulador de Disco");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 600);
        setLocationRelativeTo(null);
        setMinimumSize(new Dimension(800, 500));
        initComponents();
    }

    private void initComponents() {
        //Gradiente
        JPanel mainPanel = new JPanel(new GridBagLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
                int w = getWidth();
                int h = getHeight();
                GradientPaint gp = new GradientPaint(0, 0, new Color(25, 25, 25), 0, h, new Color(45, 45, 45));
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, w, h);
            }
        };

        //Panel (lado izquierdo)
        JPanel infoPanel = new JPanel(new GridBagLayout());
        infoPanel.setOpaque(false);
        infoPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(ACCENT_COLOR, 2),
            new EmptyBorder(20, 30, 20, 30)
        ));

        //Panel (lado derecho)
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setOpaque(false);
        formPanel.setPreferredSize(new Dimension(350, 500));
        formPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(ACCENT_COLOR, 2),
            new EmptyBorder(20, 30, 20, 30)
        ));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        //Titulo
        //Icono 
        ImageIcon originalIcon = new ImageIcon(getClass().getResource("/disk_icon.png"));
        if (originalIcon.getImageLoadStatus() == MediaTracker.COMPLETE) {
            //Redimensionar el icono
            Image image = originalIcon.getImage();
            Image newImg = image.getScaledInstance(150, 150, Image.SCALE_SMOOTH);
            ImageIcon resizedIcon = new ImageIcon(newImg);
            
            JLabel iconLabel = new JLabel(resizedIcon);
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.gridwidth = 2;
            gbc.insets = new Insets(0, 0, 20, 0);
            formPanel.add(iconLabel, gbc);
        }

        //Titulo
        JLabel titleLabel = new JLabel("Bienvenido");
        titleLabel.setFont(new Font("Segoe UI Light", Font.PLAIN, 28));
        titleLabel.setForeground(TEXT_COLOR);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(0, 0, 20, 0);
        formPanel.add(titleLabel, gbc);

        //Usuario
        JLabel userLabel = new JLabel("Usuario:");
        userLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        userLabel.setForeground(TEXT_COLOR);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(0, 0, 5, 0);
        formPanel.add(userLabel, gbc);

        userField = new JTextField(20);
        styleTextField(userField);
        gbc.gridy = 3;
        formPanel.add(userField, gbc);

        //Contraseña
        JLabel passwordLabel = new JLabel("Contraseña:");
        passwordLabel.setForeground(TEXT_COLOR);
        passwordLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        gbc.gridy = 4;
        formPanel.add(passwordLabel, gbc);

        passwordField = new JPasswordField(20);
        styleTextField(passwordField);
        gbc.gridy = 5;
        formPanel.add(passwordField, gbc);

        //Boton de login
        loginButton = new JButton("Iniciar Sesión");
        styleButton(loginButton);
        gbc.gridy = 6;
        gbc.insets = new Insets(20, 5, 5, 5);
        formPanel.add(loginButton, gbc);

        //Configurar el panel de informacion
        GridBagConstraints infoGbc = new GridBagConstraints();
        infoGbc.gridx = 0;
        infoGbc.gridy = 0;
        infoGbc.anchor = GridBagConstraints.CENTER;
        infoGbc.insets = new Insets(10, 10, 10, 10);

        //Panel para el titulo y subtitulo
        JPanel titlePanel = new JPanel(new GridBagLayout());
        titlePanel.setOpaque(false);
        GridBagConstraints titleGbc = new GridBagConstraints();
        titleGbc.gridwidth = GridBagConstraints.REMAINDER;
        titleGbc.anchor = GridBagConstraints.CENTER;

        //Titulo del sistema
        JLabel systemTitle = new JLabel("Sistema de Simulación");
        systemTitle.setFont(new Font("Segoe UI Light", Font.PLAIN, 28));
        systemTitle.setForeground(TEXT_COLOR);
        titleGbc.insets = new Insets(0, 0, 5, 0);
        titlePanel.add(systemTitle, titleGbc);

        //Subtitulo
        JLabel systemSubtitle = new JLabel("Planificación de Disco");
        systemSubtitle.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        systemSubtitle.setForeground(ACCENT_COLOR);
        titleGbc.insets = new Insets(0, 0, 30, 0);
        titlePanel.add(systemSubtitle, titleGbc);

    // Información del curso / contexto
    JLabel courseLabel = new JLabel("Curso: Sistemas Operativos - 2025");
    courseLabel.setFont(new Font("Segoe UI", Font.ITALIC, 13));
    courseLabel.setForeground(new Color(200, 200, 200));
    titleGbc.insets = new Insets(0, 0, 20, 0);
    titlePanel.add(courseLabel, titleGbc);

        infoGbc.gridy = 0;
        infoGbc.anchor = GridBagConstraints.CENTER;
        infoPanel.add(titlePanel, infoGbc);

        // Panel para la información del equipo (sección separada)
        JPanel teamPanel = new JPanel();
        teamPanel.setOpaque(false);
        teamPanel.setLayout(new BoxLayout(teamPanel, BoxLayout.Y_AXIS));

        JLabel teamTitle = new JLabel("Equipo");
        teamTitle.setFont(new Font("Segoe UI", Font.BOLD, 18));
        teamTitle.setForeground(new Color(255, 215, 0));
        teamTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        teamPanel.add(teamTitle);
        teamPanel.add(Box.createRigidArea(new Dimension(0, 8)));

        JLabel teamNumber = new JLabel("Equipo N° 5");
        teamNumber.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        teamNumber.setForeground(TEXT_COLOR);
        teamNumber.setAlignmentX(Component.CENTER_ALIGNMENT);
        teamPanel.add(teamNumber);
        teamPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        // Miembros listado verticalmente
        String[] members = new String[] {
            "Jorgeluis Quintero Contreras 23170286",
            "Josue Feliciano Rubio Echevarria 21170469",
            "Gabino Iriarte Pellegrin",
            "Carlos Eduardo león Rodriguez"
        };
        for (String m : members) {
            JLabel memberLabel = new JLabel(m);
            memberLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
            memberLabel.setForeground(TEXT_COLOR);
            memberLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            teamPanel.add(memberLabel);
            teamPanel.add(Box.createRigidArea(new Dimension(0, 12)));
        }

        infoGbc.gridy = 1;
        infoPanel.add(teamPanel, infoGbc);

        // Separador visual
        infoGbc.gridy = 2;
        JSeparator sep = new JSeparator();
        sep.setForeground(ACCENT_COLOR);
        sep.setPreferredSize(new Dimension(300, 2));
        infoGbc.insets = new Insets(10, 0, 10, 0);
        infoPanel.add(sep, infoGbc);

        // Mover la descripción a la siguiente fila (gridy = 3)
        infoGbc.gridy = 3;

        // Descripción como JLabel HTML (más fiable visualmente)
        String descHtml = "<html>"
            + "<div style='width:300px;color:#F0F0F0;font-family:Segoe UI;font-size:13px;'>"
            + "<p>Este sistema simula la gestión de peticiones de acceso a disco mediante diferentes algoritmo de planificación:</p>"
            + "<ul>"
            + "<li>FCFS (First Come First Served)</li>"
            + "<li>SSTF (Shortest Seek Time First)</li>" 
            + "<li>SCAN (Elevador)</li>"
            + "<p>"
            + "<li>N-Step SCAN (SCAN de N Pasos)</li>"
            + "<li>C-SCAN (SCAN Circular)</li>"
            + "<li>Eschenbach</li>"
            + "</ul>"
            + "<p>El simulador permite visualizar el comportamiento de cada algoritmo y comparar su eficiencia.</p>"
            + "</div>"
            + "</html>";

        JLabel descriptionLabel = new JLabel(descHtml);
        descriptionLabel.setForeground(TEXT_COLOR);
        descriptionLabel.setVerticalAlignment(SwingConstants.TOP);
        descriptionLabel.setOpaque(false);

        //Panel contenedor para la descripcion (sin scroll interno)
        JPanel descriptionPanel = new JPanel();
        descriptionPanel.setOpaque(false);
        descriptionPanel.setPreferredSize(new Dimension(330, 350));
        descriptionPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        descriptionPanel.add(descriptionLabel);
        
        infoGbc.gridy = 2;
        infoGbc.fill = GridBagConstraints.BOTH;
        infoGbc.weightx = 1.0;
    infoGbc.weighty = 0.0;
    infoPanel.add(descriptionPanel, infoGbc);

    // Agregar un relleno extra abajo para que el scroll pueda bajar más (más pequeño)
    JPanel bottomFiller = new JPanel();
    bottomFiller.setOpaque(false);
    bottomFiller.setPreferredSize(new Dimension(10, 60));
    infoGbc.gridy = 3;
    infoGbc.weighty = 1.0;
    infoPanel.add(bottomFiller, infoGbc);

    // Agregar los paneles al panel principal
    GridBagConstraints mainGbc = new GridBagConstraints();
    mainGbc.weightx = 1.0;
    mainGbc.weighty = 1.0;
    mainGbc.fill = GridBagConstraints.BOTH;
    mainGbc.insets = new Insets(20, 20, 20, 20);

    // Hacemos el panel izquierdo desplazable
    JScrollPane infoScroll = new JScrollPane(infoPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
        JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    // Mantener el borde azul alrededor (transferir el border al scrollpane)
    infoScroll.setBorder(infoPanel.getBorder());
    // dejar un padding interior para separar del scrollbar (más espacio a la derecha)
    infoPanel.setBorder(new EmptyBorder(20, 30, 20, 45));
    infoScroll.setMinimumSize(new Dimension(400, 500));
    infoPanel.setMinimumSize(new Dimension(380, 500));
    // Hacer el JScrollPane transparente para que se vea el gradiente detrás
    infoScroll.setOpaque(false);
    infoScroll.setBackground(new Color(0,0,0,0));
    // Hacer el viewport transparente y coincidir con el estilo oscuro
    infoScroll.getViewport().setOpaque(false);
    infoScroll.getViewport().setBackground(new Color(45,45,45));
    // Ajustar apariencia de la barra de desplazamiento: ancho más reducido y color acorde al tema
    JScrollBar vBar = infoScroll.getVerticalScrollBar();
    vBar.setOpaque(false);
    vBar.setPreferredSize(new Dimension(12, Integer.MAX_VALUE));
    vBar.setBackground(new Color(60, 60, 60));
    vBar.setForeground(ACCENT_COLOR);
    // Personalizar UI para usar nuestro color de acento en el thumb
    vBar.setUI(new javax.swing.plaf.basic.BasicScrollBarUI() {
        @Override
        protected void configureScrollBarColors() {
            this.thumbColor = ACCENT_COLOR;
            this.trackColor = new Color(60, 60, 60);
        }
        @Override
        protected Dimension getMinimumThumbSize() {
            return new Dimension(8, 30);
        }
    });

    mainGbc.gridx = 0;
    mainGbc.anchor = GridBagConstraints.EAST;
    mainPanel.add(infoScroll, mainGbc);

    mainGbc.gridx = 1;
    mainGbc.anchor = GridBagConstraints.WEST;
    mainPanel.add(formPanel, mainGbc);

        setContentPane(mainPanel);
        setupEvents();
    }

    private void styleTextField(JTextField textField) {
        textField.setBackground(new Color(60, 60, 60));
        textField.setForeground(new Color(255, 255, 255));
        textField.setCaretColor(new Color(255, 255, 255));
        textField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(ACCENT_COLOR, 2),
            new EmptyBorder(8, 12, 8, 12)
        ));
        textField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        textField.setPreferredSize(new Dimension(200, 35));
    }

    private void styleButton(JButton button) {
        button.setBackground(ACCENT_COLOR);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setPreferredSize(new Dimension(180, 40));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        //Bordes redondeados y padding
        button.setBorder(BorderFactory.createCompoundBorder(
            new RoundedBorder(ACCENT_COLOR, 8),
            BorderFactory.createEmptyBorder(8, 20, 8, 20)
        ));

        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(0, 130, 255)); 
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(ACCENT_COLOR);
            }
        });
    }

    private void setupEvents() {
        loginButton.addActionListener(e -> {
            String username = userField.getText().trim();
            String password = new String(passwordField.getPassword());

            // Permitir entrar si ambos campos están vacíos (no es obligatorio llenarlos)
            // o si coinciden con credenciales válidas (admin/admin o carlos/programa peor que yo)
            boolean allowed = false;
            if (username.isEmpty() && password.isEmpty()) {
                allowed = true;
            } else if (username.equals("admin") && password.equals("admin")) {
                allowed = true;
            } else if (username.equalsIgnoreCase("carlos") && password.equals("programa peor que yo")) {
                allowed = true;
            }

            if (allowed) {
                // Mostrar ventana principal
                MainWindow mainWindow = new MainWindow();
                mainWindow.setVisible(true);

                // Cerrar ventana de login
                dispose();
            } else {
                JOptionPane.showMessageDialog(this,
                    "Usuario o contraseña incorrectos",
                    "Error de autenticación",
                    JOptionPane.ERROR_MESSAGE);
            }
        });

        //Permitir login con Enter
        passwordField.addActionListener(e -> loginButton.doClick());
    }
}