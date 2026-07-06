package kasir;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.ButtonModel;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.plaf.basic.BasicButtonUI;
import javax.swing.plaf.basic.BasicToggleButtonUI;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;

/**
 * Central dark, flat visual theme for the Kasir application.
 *
 * Design note: this class only ADDS new styling behaviour on top of the
 * existing NetBeans-generated forms. None of the generated
 * initComponents() methods are touched, so every form remains fully
 * editable in the NetBeans GUI builder. Each form calls the static
 * helpers below once, right after initComponents() runs, from a new
 * applyModernTheme() method.
 */
public final class Theme {

    private Theme() {
    }

    // ---------------------------------------------------------------
    // Palette (dark mode)
    // ---------------------------------------------------------------
    public static final Color BG            = new Color(0x12, 0x13, 0x17);
    public static final Color SIDEBAR       = new Color(0x17, 0x18, 0x1E);
    public static final Color SURFACE       = new Color(0x1E, 0x20, 0x28);
    public static final Color SURFACE_ALT   = new Color(0x26, 0x29, 0x33);
    public static final Color BORDER        = new Color(0x3A, 0x3D, 0x48);

    public static final Color TEXT          = new Color(0xE9, 0xEA, 0xEE);
    public static final Color TEXT_MUTED    = new Color(0x9D, 0xA1, 0xAC);
    public static final Color TEXT_DISABLED = new Color(0x6E, 0x71, 0x80);

    public static final Color PRIMARY         = new Color(0x5B, 0x8D, 0xEF);
    public static final Color PRIMARY_HOVER   = new Color(0x75, 0xA2, 0xF5);
    public static final Color PRIMARY_PRESSED = new Color(0x45, 0x73, 0xC9);

    public static final Color DANGER          = new Color(0xE5, 0x53, 0x4B);
    public static final Color DANGER_HOVER    = new Color(0xEB, 0x6E, 0x67);
    public static final Color DANGER_PRESSED  = new Color(0xC5, 0x43, 0x3C);

    public static final Color SUCCESS         = new Color(0x3F, 0xB3, 0x7F);
    public static final Color SUCCESS_HOVER   = new Color(0x56, 0xC2, 0x93);
    public static final Color SUCCESS_PRESSED = new Color(0x32, 0x92, 0x68);

    public static final Color NEUTRAL         = new Color(0x2E, 0x31, 0x3C);
    public static final Color NEUTRAL_HOVER   = new Color(0x39, 0x3D, 0x4A);
    public static final Color NEUTRAL_PRESSED = new Color(0x1E, 0x20, 0x28);

    public static final Color SELECTION       = new Color(0x2C, 0x45, 0x70);

    // ---------------------------------------------------------------
    // Fonts - picks the best available cross-platform font at runtime
    // ---------------------------------------------------------------
    private static final String FONT_FAMILY = pickFontFamily();

    private static String pickFontFamily() {
        String[] preferred = {"Segoe UI", "Inter", "Roboto", "Helvetica Neue", "Ubuntu", "Noto Sans", "Arial"};
        Set<String> available = new HashSet<String>(Arrays.asList(
                GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames()));
        for (String name : preferred) {
            if (available.contains(name)) {
                return name;
            }
        }
        return Font.SANS_SERIF;
    }

    public static Font bold(int size) {
        return new Font(FONT_FAMILY, Font.BOLD, size);
    }

    public static Font plain(int size) {
        return new Font(FONT_FAMILY, Font.PLAIN, size);
    }

    // ---------------------------------------------------------------
    // Look & Feel bootstrap - call once, before any UI is created
    // ---------------------------------------------------------------
    public static void applyLookAndFeel() {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ex) {
            // If Nimbus can't be installed we simply continue with the
            // platform default look and feel; per-component styling
            // still applies normally on top of it.
        }

        UIManager.put("control", BG);
        UIManager.put("nimbusBase", new Color(0x1A, 0x1C, 0x22));
        UIManager.put("nimbusBlueGrey", SURFACE_ALT);
        UIManager.put("nimbusLightBackground", SURFACE);
        UIManager.put("info", SURFACE);
        UIManager.put("text", TEXT);
        UIManager.put("controlText", TEXT);
        UIManager.put("infoText", TEXT);
        UIManager.put("nimbusSelectionBackground", SELECTION);
        UIManager.put("nimbusSelectedText", TEXT);
        UIManager.put("nimbusFocus", PRIMARY);
        UIManager.put("nimbusDisabledText", TEXT_DISABLED);
        UIManager.put("nimbusBorder", BORDER);
        UIManager.put("defaultFont", plain(13));

        UIManager.put("Panel.background", BG);
        UIManager.put("Label.foreground", TEXT);
        UIManager.put("Label.font", plain(13));

        UIManager.put("TextField.background", SURFACE_ALT);
        UIManager.put("TextField.foreground", TEXT);
        UIManager.put("TextField.caretForeground", PRIMARY);
        UIManager.put("TextField.font", plain(13));
        UIManager.put("TextField.disabledBackground", SIDEBAR);
        UIManager.put("TextField.disabledForeground", TEXT_DISABLED);
        UIManager.put("TextField.inactiveBackground", SIDEBAR);
        UIManager.put("TextField.inactiveForeground", TEXT_MUTED);

        UIManager.put("FormattedTextField.background", SURFACE_ALT);
        UIManager.put("FormattedTextField.foreground", TEXT);
        UIManager.put("FormattedTextField.font", plain(13));

        UIManager.put("PasswordField.background", SURFACE_ALT);
        UIManager.put("PasswordField.foreground", TEXT);
        UIManager.put("PasswordField.font", plain(13));

        UIManager.put("ComboBox.background", SURFACE_ALT);
        UIManager.put("ComboBox.foreground", TEXT);
        UIManager.put("ComboBox.font", plain(13));

        UIManager.put("Button.background", NEUTRAL);
        UIManager.put("Button.foreground", TEXT);
        UIManager.put("Button.font", bold(13));
        UIManager.put("ToggleButton.background", NEUTRAL);
        UIManager.put("ToggleButton.foreground", TEXT);
        UIManager.put("ToggleButton.font", bold(13));

        UIManager.put("Table.background", SURFACE);
        UIManager.put("Table.foreground", TEXT);
        UIManager.put("Table.font", plain(13));
        UIManager.put("Table.gridColor", BORDER);
        UIManager.put("Table.selectionBackground", SELECTION);
        UIManager.put("Table.selectionForeground", TEXT);
        UIManager.put("TableHeader.background", SURFACE_ALT);
        UIManager.put("TableHeader.foreground", TEXT);
        UIManager.put("TableHeader.font", bold(12));

        UIManager.put("ScrollBar.thumb", SURFACE_ALT);
        UIManager.put("ScrollBar.track", BG);
        UIManager.put("ScrollBar.background", BG);

        UIManager.put("OptionPane.background", SURFACE);
        UIManager.put("OptionPane.messageForeground", TEXT);
        UIManager.put("OptionPane.buttonAreaBackground", SURFACE);
    }

    // ---------------------------------------------------------------
    // Frames & panels
    // ---------------------------------------------------------------
    public static void styleFrame(JFrame frame) {
        frame.getContentPane().setBackground(BG);
    }

    public static void styleAppBackground(JPanel panel) {
        panel.setOpaque(true);
        panel.setBackground(BG);
    }

    public static void styleSidebarBase(JPanel panel) {
        panel.setOpaque(true);
        panel.setBackground(SIDEBAR);
    }

    public static void styleSidebarActive(JPanel panel) {
        panel.setOpaque(true);
        panel.setBackground(PRIMARY);
    }

    public static void styleCard(JPanel panel) {
        panel.setOpaque(true);
        panel.setBackground(SURFACE);
    }

    public static void styleCardAlt(JPanel panel) {
        panel.setOpaque(true);
        panel.setBackground(SURFACE_ALT);
    }

    // ---------------------------------------------------------------
    // Labels
    // ---------------------------------------------------------------
    public static void styleTitle(JLabel label) {
        label.setFont(bold(24));
        label.setForeground(TEXT);
    }

    public static void styleFieldLabel(JLabel label) {
        label.setFont(bold(13));
        label.setForeground(TEXT_MUTED);
    }

    public static void styleMuted(JLabel label) {
        label.setFont(plain(12));
        label.setForeground(TEXT_DISABLED);
    }

    public static void styleNavActive(JLabel label) {
        label.setFont(bold(14));
        label.setForeground(Color.WHITE);
        label.setOpaque(false);
        label.setCursor(Cursor.getDefaultCursor());
    }

    public static void styleNavInactive(final JLabel label) {
        label.setFont(bold(14));
        label.setForeground(TEXT_MUTED);
        label.setOpaque(false);
        label.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        label.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                label.setForeground(TEXT);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                label.setForeground(TEXT_MUTED);
            }
        });
    }

    // ---------------------------------------------------------------
    // Text fields
    // ---------------------------------------------------------------
    public static void styleField(final JTextField field) {
        field.setFont(plain(14));
        field.setForeground(TEXT);
        field.setBackground(SURFACE_ALT);
        field.setCaretColor(PRIMARY);
        field.setSelectionColor(SELECTION);
        field.setSelectedTextColor(TEXT);
        field.setBorder(new RoundedLineBorder(BORDER, PRIMARY, 8, 8));
        field.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                field.repaint();
            }

            @Override
            public void focusLost(FocusEvent e) {
                field.repaint();
            }
        });
    }

    public static void styleReadOnlyField(JTextField field) {
        styleField(field);
        field.setBackground(SIDEBAR);
        field.setForeground(TEXT_MUTED);
        field.setDisabledTextColor(TEXT_MUTED);
    }

    public static void styleAmountField(JTextField field) {
        styleReadOnlyField(field);
        field.setFont(bold(19));
        field.setForeground(TEXT);
        field.setDisabledTextColor(TEXT);
    }

    // ---------------------------------------------------------------
    // Buttons
    // ---------------------------------------------------------------
    public static void stylePrimary(AbstractButton b) {
        styleButton(b, PRIMARY, PRIMARY_HOVER, PRIMARY_PRESSED, Color.WHITE);
    }

    public static void styleDanger(AbstractButton b) {
        styleButton(b, DANGER, DANGER_HOVER, DANGER_PRESSED, Color.WHITE);
    }

    public static void styleSuccess(AbstractButton b) {
        styleButton(b, SUCCESS, SUCCESS_HOVER, SUCCESS_PRESSED, Color.WHITE);
    }

    public static void styleNeutral(AbstractButton b) {
        styleButton(b, NEUTRAL, NEUTRAL_HOVER, NEUTRAL_PRESSED, TEXT);
    }

    private static void styleButton(AbstractButton b, Color base, Color hover, Color pressed, Color fg) {
        if (b instanceof JToggleButton) {
            b.setUI(new FlatToggleButtonUI(base, hover, pressed));
        } else {
            b.setUI(new FlatButtonUI(base, hover, pressed));
        }
        b.setForeground(fg);
        b.setFont(bold(13));
        b.setFocusPainted(false);
        b.setBorderPainted(false);
        b.setContentAreaFilled(false);
        b.setOpaque(false);
        b.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        b.setMargin(new Insets(8, 16, 8, 16));
    }

    // ---------------------------------------------------------------
    // Tables & scroll panes
    // ---------------------------------------------------------------
    public static void styleTable(JTable table) {
        table.setFont(plain(13));
        table.setRowHeight(28);
        table.setShowGrid(false);
        table.setIntercellSpacing(new Dimension(0, 0));
        table.setBackground(SURFACE);
        table.setForeground(TEXT);
        table.setSelectionBackground(SELECTION);
        table.setSelectionForeground(TEXT);
        table.setGridColor(BORDER);
        table.setFillsViewportHeight(true);
        table.setDefaultRenderer(Object.class, new StripedRenderer());

        JTableHeader header = table.getTableHeader();
        if (header != null) {
            header.setFont(bold(12));
            header.setBackground(SURFACE_ALT);
            header.setForeground(TEXT);
            header.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, BORDER));
            header.setPreferredSize(new Dimension(header.getPreferredSize().width, 32));
            header.setReorderingAllowed(false);
        }
    }

    public static void styleScroll(JScrollPane scroll) {
        scroll.setBorder(BorderFactory.createLineBorder(BORDER));
        scroll.getViewport().setBackground(SURFACE);
        scroll.getVerticalScrollBar().setUnitIncrement(16);
    }

    public static void styleDateChooser(JComponent chooser) {
        chooser.setBackground(SURFACE_ALT);
        chooser.setForeground(TEXT);
        chooser.setFont(plain(13));
        chooser.setBorder(new RoundedLineBorder(BORDER, PRIMARY, 8, 4));
    }

    // ---------------------------------------------------------------
    // Painting helpers
    // ---------------------------------------------------------------
    private static Color currentFill(ButtonModel model, boolean enabled, Color base, Color hover, Color pressed) {
        if (!enabled) {
            return NEUTRAL_PRESSED;
        }
        if (model.isArmed() && model.isPressed()) {
            return pressed;
        }
        if (model.isRollover()) {
            return hover;
        }
        return base;
    }

    /** Flat, rounded, hover/press-aware paint for plain JButtons. */
    public static class FlatButtonUI extends BasicButtonUI {
        private final Color base;
        private final Color hover;
        private final Color pressed;

        public FlatButtonUI(Color base, Color hover, Color pressed) {
            this.base = base;
            this.hover = hover;
            this.pressed = pressed;
        }

        @Override
        public void paint(Graphics g, JComponent c) {
            AbstractButton b = (AbstractButton) c;
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            Color fill = currentFill(b.getModel(), b.isEnabled(), base, hover, pressed);
            g2.setColor(fill);
            g2.fillRoundRect(0, 0, c.getWidth(), c.getHeight(), 10, 10);
            g2.setColor(fill.darker());
            g2.drawRoundRect(0, 0, c.getWidth() - 1, c.getHeight() - 1, 10, 10);
            g2.dispose();
            super.paint(g, c);
        }
    }

    /** Same flat/rounded treatment for JToggleButtons used as plain action buttons. */
    public static class FlatToggleButtonUI extends BasicToggleButtonUI {
        private final Color base;
        private final Color hover;
        private final Color pressed;

        public FlatToggleButtonUI(Color base, Color hover, Color pressed) {
            this.base = base;
            this.hover = hover;
            this.pressed = pressed;
        }

        @Override
        public void paint(Graphics g, JComponent c) {
            AbstractButton b = (AbstractButton) c;
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            Color fill = currentFill(b.getModel(), b.isEnabled(), base, hover, pressed);
            g2.setColor(fill);
            g2.fillRoundRect(0, 0, c.getWidth(), c.getHeight(), 10, 10);
            g2.setColor(fill.darker());
            g2.drawRoundRect(0, 0, c.getWidth() - 1, c.getHeight() - 1, 10, 10);
            g2.dispose();
            super.paint(g, c);
        }
    }

    /** Rounded outline border that lights up in the accent color on focus. */
    public static class RoundedLineBorder implements Border {
        private final Color normalColor;
        private final Color focusColor;
        private final int arc;
        private final int pad;

        public RoundedLineBorder(Color normalColor, Color focusColor, int arc, int pad) {
            this.normalColor = normalColor;
            this.focusColor = focusColor;
            this.arc = arc;
            this.pad = pad;
        }

        @Override
        public Insets getBorderInsets(Component c) {
            return new Insets(pad, pad + 4, pad, pad + 4);
        }

        @Override
        public boolean isBorderOpaque() {
            return false;
        }

        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            boolean focused = c.isFocusOwner();
            g2.setColor(focused ? focusColor : normalColor);
            g2.setStroke(new BasicStroke(focused ? 2f : 1f));
            g2.drawRoundRect(x + 1, y + 1, width - 3, height - 3, arc, arc);
            g2.dispose();
        }
    }

    /** Zebra-striped table rows with a themed selection color. */
    public static class StripedRenderer extends DefaultTableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value,
                boolean isSelected, boolean hasFocus, int row, int column) {
            Component comp = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            if (isSelected) {
                comp.setBackground(SELECTION);
                comp.setForeground(TEXT);
            } else {
                comp.setBackground(row % 2 == 0 ? SURFACE : SURFACE_ALT);
                comp.setForeground(TEXT);
            }
            setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
            return comp;
        }
    }
}
