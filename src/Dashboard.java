import javax.swing.*;
import java.awt.*;

public class Dashboard {
    private JFrame frame;

    public Dashboard() {
        frame = new JFrame("System Dashboard");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // close the whole program
        frame.setSize(350, 400);
        frame.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(15, 30, 15, 30);

        JButton btnBank = new JButton("Bank System");
        JButton btnZoo = new JButton("Zoo System");
        JButton btnRestaurant = new JButton("Restaurant System");
        JButton btnShape = new JButton("Shape System");
        JButton btnAbout = new JButton("About");

        gbc.gridy = 0;
        frame.add(btnBank, gbc);
        gbc.gridy = 1;
        frame.add(btnZoo, gbc);
        gbc.gridy = 2;
        frame.add(btnRestaurant, gbc);
        gbc.gridy = 3;
        frame.add(btnShape, gbc);
        gbc.gridy = 4;
        frame.add(btnAbout, gbc);

        btnBank.addActionListener(e -> {
            try {
                Class<?> clazz = Class.forName("BankingTaskListGUI.BankingTaskListGUI");
                clazz.getMethod("main", String[].class).invoke(null, (Object) new String[]{});
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Failed to launch Bank System GUI!\n" + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        btnShape.addActionListener(e -> {
            try {
                Class<?> clazz = Class.forName("bouncebox.BounceBoxDashboard");
                clazz.getMethod("main", String[].class).invoke(null, (Object) new String[]{});
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Failed to launch Shape System GUI!\n" + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        btnZoo.addActionListener(e -> {
            JOptionPane.showMessageDialog(frame, "Failed to launch Zoo System GUI!\n", "Info", JOptionPane.INFORMATION_MESSAGE);
        });
        btnRestaurant.addActionListener(e -> {
            JOptionPane.showMessageDialog(frame, "Failed to launch Restaurant System GUI!\n", "Info", JOptionPane.INFORMATION_MESSAGE);
        });
        btnAbout.addActionListener(e -> showAboutDialog());

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void showAboutDialog() {
        JDialog dialog = new JDialog(frame, "About", true);
        dialog.setLayout(new BorderLayout(10, 10));
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.setAlignmentX(Component.CENTER_ALIGNMENT);
        JButton githubButton;
        try {
            ImageIcon icon = new ImageIcon(getClass().getResource("github.png"));
            Image img = icon.getImage().getScaledInstance(24, 24, Image.SCALE_SMOOTH);
            icon = new ImageIcon(img);
            githubButton = new JButton("GitHub Repo Address", icon);
        } catch (Exception e) {
            githubButton = new JButton("GitHub Repo Address");
        }
        githubButton.setFocusPainted(false);
        githubButton.setAlignmentY(Component.CENTER_ALIGNMENT);
        githubButton.addActionListener(e -> {
            try {
                java.awt.Desktop.getDesktop().browse(new java.net.URI("https://github.com/thedignityofcoffee/OOP-GroupWork"));
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(dialog, "Failed to open browser.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        panel.add(Box.createHorizontalGlue());
        panel.add(githubButton);
        panel.add(Box.createRigidArea(new Dimension(30, 0)));
        JLabel copyright = new JLabel("© 2025 thedignityofcoffee");
        copyright.setAlignmentY(Component.CENTER_ALIGNMENT);
        panel.add(copyright);
        panel.add(Box.createHorizontalGlue());
        dialog.add(panel, BorderLayout.CENTER);
        dialog.setSize(450, 120);
        dialog.setLocationRelativeTo(frame);
        dialog.setResizable(false);
        dialog.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Dashboard::new);
    }
}