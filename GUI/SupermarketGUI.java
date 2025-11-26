/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

/**
 *
 * @author Gabriel
 */
import Base.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

/**
 * Improved Swing GUI for Supermarket Simulator
 * 
 * @author Gabriel
 * @version 1.1
 */
public class SupermarketGUI extends JFrame {
    // Components
    private JTextArea messageArea;
    private JPanel mapPanel;
    private JPanel inventoryPanel;
    private JLabel statusLabel;
    private JLabel floorLabel;
    
    // Game objects
    private Supermarket supermarket;
    private Shopper player;
    private boolean gameRunning = false;
    
    // Frame rate limiting
    private long lastPaintTime = 0;
    private static final long MIN_PAINT_INTERVAL = 16; // ~60 FPS
    
    // Constants
    private static final int CELL_SIZE = 28;
    private static final Color[] COLORS = {
        new Color(70, 70, 70),      // # Wall - Dark gray
        new Color(120, 120, 120),   // = Interior wall - Gray
        new Color(255, 165, 0),     // H Shelf - Orange
        new Color(144, 238, 144),   // T Table - Light green
        new Color(100, 149, 237),   // R Refrigerator - Cornflower blue
        new Color(173, 216, 230),   // C Chilled counter - Light blue
        new Color(255, 215, 0),     // $ Checkout - Gold
        new Color(255, 140, 0),     // ^ Stairs - Dark orange
        new Color(255, 105, 180),   // K Cart - Hot pink
        new Color(255, 255, 0),     // B Basket - Yellow
        new Color(220, 20, 60),     // E Entrance - Crimson
        new Color(65, 105, 225),    // ? Info - Royal blue
        new Color(255, 0, 0)        // S Player - Red
    };
    
    public SupermarketGUI() {
        setTitle("🛒 SUPERMARKET SIMULATOR - MCO1");
        setSize(1400, 900);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));
        getContentPane().setBackground(new Color(45, 52, 54));
        
        // Initialize game first
        if (!showLoginDialog()) {
            System.exit(0);
            return;
        }
        
        // Create UI components
        add(createTopPanel(), BorderLayout.NORTH);
        add(createCenterPanel(), BorderLayout.CENTER);
        add(createRightPanel(), BorderLayout.EAST);
        add(createBottomPanel(), BorderLayout.SOUTH);
        
        setupKeyBindings();
        updateDisplay();
        
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
    /**
     * Show login dialog and initialize game
     */
    private boolean showLoginDialog() {
        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        JTextField nameField = new JTextField("Player");
        JTextField ageField = new JTextField("25");
        
        panel.add(new JLabel("👤 Name:"));
        panel.add(nameField);
        panel.add(new JLabel("🎂 Age:"));
        panel.add(ageField);
        
        int result = JOptionPane.showConfirmDialog(
            null, panel, "Welcome to SuperMart! 🛒", 
            JOptionPane.OK_CANCEL_OPTION, 
            JOptionPane.PLAIN_MESSAGE
        );
        
        if (result == JOptionPane.OK_OPTION) {
            String name = nameField.getText().trim();
            if (name.isEmpty()) name = "Player";
            
            int age = 25;
            try {
                age = Integer.parseInt(ageField.getText().trim());
                if (age < 1 || age > 150) age = 25;
            } catch (Exception e) {
                age = 25;
            }
            
            // Create appropriate shopper type
            if (age < 18) {
                player = new Minor(name, age);
                JOptionPane.showMessageDialog(null, 
                    "⚠️ As a minor, you cannot purchase Alcohol or Cleaning Agents.",
                    "Age Restriction", JOptionPane.INFORMATION_MESSAGE);
            } else if (age >= 60) {
                player = new Senior(name, age);
                JOptionPane.showMessageDialog(null, 
                    "🎉 Senior Discount: 20% off food, 10% off beverages!",
                    "Welcome Senior!", JOptionPane.INFORMATION_MESSAGE);
            } else {
                player = new Adult(name, age);
            }
            
            // Initialize supermarket
            supermarket = new Supermarket("SuperMart", 22, 22);
            supermarket.addShopper(player, 11, 21);
            gameRunning = true;
            
            return true;
        }
        
        return false;
    }
    
    /**
     * Create top status panel
     */
    private JPanel createTopPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 5));
        panel.setBackground(new Color(33, 37, 41));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
        
        // Floor and status info
        JPanel infoPanel = new JPanel(new GridLayout(2, 1, 5, 5));
        infoPanel.setOpaque(false);
        
        floorLabel = new JLabel("🏢 Floor: 1");
        floorLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        floorLabel.setForeground(Color.WHITE);
        
        statusLabel = new JLabel("📍 Position: (11, 21) | Facing: NORTH");
        statusLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        statusLabel.setForeground(new Color(200, 200, 200));
        
        infoPanel.add(floorLabel);
        infoPanel.add(statusLabel);
        
        // Player name label
        JLabel playerLabel = new JLabel("👤 " + (player != null ? player.getName() : "Player"));
        playerLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        playerLabel.setForeground(Color.CYAN);
        
        panel.add(infoPanel, BorderLayout.CENTER);
        panel.add(playerLabel, BorderLayout.EAST);
        
        return panel;
    }
    
    /**
     * Create center map panel
     */
    private JPanel createCenterPanel() {
        JPanel container = new JPanel(new BorderLayout());
        container.setBackground(new Color(45, 52, 54));
        container.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Title
        JLabel titleLabel = new JLabel("🗺️ SUPERMARKET MAP", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        
        // Map panel
        mapPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                drawMap((Graphics2D) g);
            }
        };
        mapPanel.setPreferredSize(new Dimension(CELL_SIZE * 22 + 40, CELL_SIZE * 22 + 40));
        mapPanel.setBackground(Color.BLACK);
        mapPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
        
        JScrollPane scrollPane = new JScrollPane(mapPanel);
        scrollPane.getViewport().setBackground(Color.BLACK);
        
        container.add(titleLabel, BorderLayout.NORTH);
        container.add(scrollPane, BorderLayout.CENTER);
        
        return container;
    }
    
    /**
     * Draw the map
     */
    private void drawMap(Graphics2D g) {
        if (!gameRunning || supermarket == null) return;
        
        // Limit repainting to ~60 FPS
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastPaintTime < MIN_PAINT_INTERVAL) {
            return;
        }
        lastPaintTime = currentTime;
        
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        Map map = supermarket.getMap();
        int offsetX = 20;
        int offsetY = 20;
        
        // Draw grid
        for (int y = 0; y < 22; y++) {
            for (int x = 0; x < 22; x++) {
                int px = offsetX + x * CELL_SIZE;
                int py = offsetY + y * CELL_SIZE;
                
                String cell = map.getCell(x, y);
                Color cellColor = getCellColor(cell);
                
                // Fill cell
                g.setColor(cellColor);
                g.fillRect(px, py, CELL_SIZE - 1, CELL_SIZE - 1);
                
                // Draw border
                g.setColor(new Color(50, 50, 50));
                g.drawRect(px, py, CELL_SIZE - 1, CELL_SIZE - 1);
                
                // Draw symbol
                if (!cell.equals(" ")) {
                    g.setColor(cell.equals("S") ? Color.WHITE : Color.BLACK);
                    g.setFont(new Font("Monospaced", Font.BOLD, 14));
                    FontMetrics fm = g.getFontMetrics();
                    int textX = px + (CELL_SIZE - fm.stringWidth(cell)) / 2;
                    int textY = py + (CELL_SIZE + fm.getAscent()) / 2 - 2;
                    g.drawString(cell, textX, textY);
                }
            }
        }
    }
    
    /**
     * Get color for cell type
     */
    private Color getCellColor(String cell) {
        switch (cell) {
            case "#": return COLORS[0];
            case "=": return COLORS[1];
            case "H": return COLORS[2];
            case "T": return COLORS[3];
            case "R": return COLORS[4];
            case "C": return COLORS[5];
            case "$": return COLORS[6];
            case "^": return COLORS[7];
            case "K": return COLORS[8];
            case "B": return COLORS[9];
            case "E": return COLORS[10];
            case "?": return COLORS[11];
            case "S": return COLORS[12];
            default: return Color.BLACK;
        }
    }
    
    /**
     * Create right inventory panel
     */
    private JPanel createRightPanel() {
        JPanel container = new JPanel(new BorderLayout(5, 5));
        container.setBackground(new Color(33, 37, 41));
        container.setPreferredSize(new Dimension(300, 600));
        container.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Title
        JLabel titleLabel = new JLabel("📦 INVENTORY", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        titleLabel.setForeground(Color.WHITE);
        
        // Inventory panel
        inventoryPanel = new JPanel();
        inventoryPanel.setLayout(new BoxLayout(inventoryPanel, BoxLayout.Y_AXIS));
        inventoryPanel.setBackground(new Color(45, 52, 54));
        
        JScrollPane scrollPane = new JScrollPane(inventoryPanel);
        scrollPane.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        scrollPane.getViewport().setBackground(new Color(45, 52, 54));
        
        // Legend
        JPanel legendPanel = createLegendPanel();
        
        container.add(titleLabel, BorderLayout.NORTH);
        container.add(scrollPane, BorderLayout.CENTER);
        container.add(legendPanel, BorderLayout.SOUTH);
        
        return container;
    }
    
    /**
     * Create legend panel
     */
    private JPanel createLegendPanel() {
        JPanel panel = new JPanel(new GridLayout(0, 1, 2, 2));
        panel.setBackground(new Color(33, 37, 41));
        panel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(Color.GRAY),
            "Legend",
            0, 0,
            new Font("Segoe UI", Font.BOLD, 12),
            Color.WHITE
        ));
        
        String[] legends = {
            "S - You", "# - Wall", "H - Shelf", "T - Table",
            "R - Fridge", "C - Chilled", "^ - Stairs", "? - Info",
            "$ - Checkout", "K - Cart", "B - Basket", "E - Exit"
        };
        
        for (String legend : legends) {
            JLabel label = new JLabel(legend);
            label.setFont(new Font("Monospaced", Font.PLAIN, 11));
            label.setForeground(Color.LIGHT_GRAY);
            panel.add(label);
        }
        
        return panel;
    }
    
    /**
     * Create bottom control panel
     */
    private JPanel createBottomPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBackground(new Color(33, 37, 41));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
        
        // Message area
        messageArea = new JTextArea(4, 50);
        messageArea.setEditable(false);
        messageArea.setLineWrap(true);
        messageArea.setWrapStyleWord(true);
        messageArea.setFont(new Font("Consolas", Font.PLAIN, 12));
        messageArea.setBackground(Color.BLACK);
        messageArea.setForeground(Color.GREEN);
        
        JScrollPane msgScroll = new JScrollPane(messageArea);
        msgScroll.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(Color.GRAY),
            "Messages",
            0, 0,
            new Font("Segoe UI", Font.BOLD, 12),
            Color.WHITE
        ));
        
        // Buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
        buttonPanel.setOpaque(false);
        
        JButton interactBtn = createStyledButton("🤝 Interact [E]", new Color(52, 152, 219));
        JButton inventoryBtn = createStyledButton("📦 View Inventory [V]", new Color(46, 204, 113));
        JButton restartBtn = createStyledButton("🔄 Restart", new Color(241, 196, 15));
        JButton quitBtn = createStyledButton("❌ Quit [Q]", new Color(231, 76, 60));
        
        interactBtn.addActionListener(e -> interact());
        inventoryBtn.addActionListener(e -> showInventoryDialog());
        restartBtn.addActionListener(e -> restartGame());
        quitBtn.addActionListener(e -> quitGame());
        
        buttonPanel.add(interactBtn);
        buttonPanel.add(inventoryBtn);
        buttonPanel.add(restartBtn);
        buttonPanel.add(quitBtn);
        
        panel.add(msgScroll, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);
        
        return panel;
    }
    
    /**
     * Create styled button
     */
    private JButton createStyledButton(String text, Color color) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btn.setBackground(color);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setBorder(BorderFactory.createEmptyBorder(8, 15, 8, 15));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        // Hover effect
        btn.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                btn.setBackground(color.brighter());
            }
            public void mouseExited(MouseEvent e) {
                btn.setBackground(color);
            }
        });
        
        return btn;
    }
    
    /**
     * Setup keyboard controls
     */
    private void setupKeyBindings() {
        JPanel contentPane = (JPanel) getContentPane();
        InputMap inputMap = contentPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = contentPane.getActionMap();
        
        // WASD + Arrow keys
        setupKey(inputMap, actionMap, "w", KeyEvent.VK_W, KeyEvent.VK_UP, "moveUp");
        setupKey(inputMap, actionMap, "s", KeyEvent.VK_S, KeyEvent.VK_DOWN, "moveDown");
        setupKey(inputMap, actionMap, "a", KeyEvent.VK_A, KeyEvent.VK_LEFT, "moveLeft");
        setupKey(inputMap, actionMap, "d", KeyEvent.VK_D, KeyEvent.VK_RIGHT, "moveRight");
        
        // IJKL for looking
        setupKey(inputMap, actionMap, "i", KeyEvent.VK_I, -1, "lookUp");
        setupKey(inputMap, actionMap, "k", KeyEvent.VK_K, -1, "lookDown");
        setupKey(inputMap, actionMap, "j", KeyEvent.VK_J, -1, "lookLeft");
        setupKey(inputMap, actionMap, "l", KeyEvent.VK_L, -1, "lookRight");
        
        // Actions
        inputMap.put(KeyStroke.getKeyStroke('e'), "interact");
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0), "interact");
        inputMap.put(KeyStroke.getKeyStroke('v'), "inventory");
        inputMap.put(KeyStroke.getKeyStroke('q'), "quit");
        
        actionMap.put("interact", new AbstractAction() {
            public void actionPerformed(ActionEvent e) { interact(); }
        });
        actionMap.put("inventory", new AbstractAction() {
            public void actionPerformed(ActionEvent e) { showInventoryDialog(); }
        });
        actionMap.put("quit", new AbstractAction() {
            public void actionPerformed(ActionEvent e) { quitGame(); }
        });
    }
    
    /**
     * Helper to setup key bindings
     */
    private void setupKey(InputMap inputMap, ActionMap actionMap, String letter, int keyCode, int arrowCode, String action) {
        inputMap.put(KeyStroke.getKeyStroke(letter.charAt(0)), action);
        inputMap.put(KeyStroke.getKeyStroke(keyCode, 0), action);
        if (arrowCode != -1) {
            inputMap.put(KeyStroke.getKeyStroke(arrowCode, 0), action);
        }
        
        actionMap.put(action, new AbstractAction() {
            public void actionPerformed(ActionEvent e) { handleMovement(letter); }
        });
    }
    
    /**
     * Handle movement
     */
    private void handleMovement(String direction) {
        if (!gameRunning) return;

        SwingUtilities.invokeLater(() -> {
            try {
                boolean moved = MovementController.handleInput(direction, player, supermarket.getMap());

                if (moved) {
                    appendMessage("Moved " + direction.toUpperCase());
                }

                // Small delay to prevent rapid-fire updates
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    // Ignore
                }

                updateDisplay();
            } catch (Exception e) {
                System.err.println("Movement error: " + e.getMessage());
            }
        });
    }
    
    /**
     * Handle interaction
     */
    private void interact() {
        if (!gameRunning) return;

        SwingUtilities.invokeLater(() -> {
            try {
                // Create a string builder to capture output
                StringBuilder output = new StringBuilder();

                // Get the cell in front
                FrontCell front = MovementController.getCellInFront(player, supermarket.getMap());
                if (front == null) {
                    appendMessage("Nothing in front.");
                    return;
                }

                // FIXED: Changed from getCellContent() to getCellType()
                String cellType = front.getCellType();
                output.append("Interacting with: ").append(cellType).append("\n");

                // Call the actual interaction
                supermarket.handleInteraction(player);

                // Check if player exited
                if (player.hasExited()) {
                    int choice = JOptionPane.showConfirmDialog(
                        SupermarketGUI.this,
                        "👋 You have left the supermarket!\n\nStart a new simulation?",
                        "Goodbye!",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.INFORMATION_MESSAGE
                    );

                    if (choice == JOptionPane.YES_OPTION) {
                        restartGame();
                    } else {
                        System.exit(0);
                    }
                    return;
                }

                // Update display
                updateDisplay();

            } catch (Exception e) {
                appendMessage("⚠️ Error: " + e.getMessage());
                e.printStackTrace();
            }
        });
    }

    
    /**
     * Show inventory dialog
     */
    private void showInventoryDialog() {
        InventoryDisplay.displayInventory(player);
    }
    
    /**
     * Update all displays - THREAD SAFE
     */
    private void updateDisplay() {
        if (!gameRunning) return;
        
        // Ensure GUI updates happen on EDT
        SwingUtilities.invokeLater(() -> {
            try {
                // Update status labels
                floorLabel.setText("🏢 Floor: " + supermarket.getCurrentFloor());
                statusLabel.setText(String.format("📍 Position: (%d, %d) | Facing: %s",
                    player.getX(), player.getY(), player.getFacingDirection()));
                
                // Update inventory panel
                updateInventoryPanel();
                
                // Repaint map (but don't trigger another update)
                mapPanel.repaint();
            } catch (Exception e) {
                System.err.println("Error updating display: " + e.getMessage());
            }
        });
    }
    
    /**
     * Update inventory panel
     */
    private void updateInventoryPanel() {
        inventoryPanel.removeAll();
        
        // Equipment info
        Equipment equip = player.getEquipment();
        JLabel equipLabel;
        if (equip != null) {
            String type = equip instanceof Cart ? "🛒 Cart" : "🧺 Basket";
            equipLabel = new JLabel(String.format("%s: %d/%d items",
                type, equip.getCurrentLoad(), equip.getCapacity()));
        } else {
            equipLabel = new JLabel("No Equipment");
        }
        equipLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        equipLabel.setForeground(Color.CYAN);
        equipLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        inventoryPanel.add(equipLabel);
        inventoryPanel.add(Box.createVerticalStrut(10));
        
        // Products
        ArrayList<Product> items = player.getAllProducts();
        if (items.isEmpty()) {
            JLabel emptyLabel = new JLabel("(No items)");
            emptyLabel.setForeground(Color.LIGHT_GRAY);
            emptyLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
            inventoryPanel.add(emptyLabel);
        } else {
            double total = 0;
            for (Product p : items) {
                JLabel itemLabel = new JLabel(String.format("• %s - ₱%.2f",
                    p.getName(), p.getPrice()));
                itemLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
                itemLabel.setForeground(Color.WHITE);
                itemLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
                inventoryPanel.add(itemLabel);
                inventoryPanel.add(Box.createVerticalStrut(5));
                total += p.getPrice();
            }
            
            inventoryPanel.add(Box.createVerticalStrut(10));
            JLabel totalLabel = new JLabel(String.format("Total: ₱%.2f", total));
            totalLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
            totalLabel.setForeground(Color.GREEN);
            totalLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
            inventoryPanel.add(totalLabel);
        }
        
        inventoryPanel.revalidate();
        inventoryPanel.repaint();
    }
    
    /**
     * Append message to message area
     */
    private void appendMessage(String msg) {
        messageArea.append(msg + "\n");
        messageArea.setCaretPosition(messageArea.getDocument().getLength());
    }
    
    /**
     * Restart game
     */
    private void restartGame() {
        int choice = JOptionPane.showConfirmDialog(this,
            "Start a new simulation?",
            "Restart Game",
            JOptionPane.YES_NO_OPTION);
        
        if (choice == JOptionPane.YES_OPTION) {
            dispose();
            new SupermarketGUI();
        }
    }
    
    /**
     * Quit game
     */
    private void quitGame() {
        int choice = JOptionPane.showConfirmDialog(this,
            "Are you sure you want to quit?",
            "Quit Game",
            JOptionPane.YES_NO_OPTION);
        
        if (choice == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }
    
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        SwingUtilities.invokeLater(() -> new SupermarketGUI());
    }
}