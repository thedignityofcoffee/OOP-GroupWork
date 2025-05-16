import javax.swing.*;
import java.awt.*;
//import java.awt.event.*;
import java.io.*;
import java.util.Scanner;

public class BounceBoxDashboard {
    private JFrame frame;
    private String lastLoadedFile = null;
    private int circleCount, squareCount, triangleCount, rectangleCount;
    private double totalArea;

    public BounceBoxDashboard() {
        frame = new JFrame("Bounce Box Dashboard");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 350);
        frame.setLayout(new GridLayout(3, 2, 10, 10));

        JButton btnLoadFile = new JButton("Import Shape File");
        JButton btnShowCounts = new JButton("Show Shape Counts");
        JButton btnShowAnimation = new JButton("Animation Window");
        JButton btnShowArea = new JButton("Show Total Area");
        JButton btnAbout = new JButton("About");
        JButton btnExit = new JButton("Back to Main Menu");

        btnLoadFile.addActionListener(e -> showFileChooser());
        btnShowCounts.addActionListener(e -> showShapeCounts());
        btnShowAnimation.addActionListener(e -> showAnimationWindow());
        btnShowArea.addActionListener(e -> showTotalArea());
        btnAbout.addActionListener(e -> showAboutDialog());
        btnExit.addActionListener(e -> exitToMainMenu());

        frame.add(btnLoadFile);
        frame.add(btnShowCounts);
        frame.add(btnShowAnimation);
        frame.add(btnShowArea);
        frame.add(btnAbout);
        frame.add(btnExit);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void showFileChooser() {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(frame);
        if (result == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            lastLoadedFile = file.getAbsolutePath();
            parseShapeFile(file);
            JOptionPane.showMessageDialog(frame, "File imported successfully!", "Info", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void parseShapeFile(File file) {
        circleCount = squareCount = triangleCount = rectangleCount = 0;
        totalArea = 0.0;
        try (Scanner scan = new Scanner(file)) {
            while (scan.hasNextLine()) {
                String line = scan.nextLine().trim();
                if (line.isEmpty()) continue;
                Scanner lineScan = new Scanner(line);
                if (!lineScan.hasNext()) continue;
                String shapeType = lineScan.next();
                if (shapeType.equalsIgnoreCase("Circle")) {
                    if (!lineScan.hasNextInt()) continue;
                    lineScan.nextInt(); // x
                    if (!lineScan.hasNextInt()) continue;
                    lineScan.nextInt(); // y
                    if (!lineScan.hasNextInt()) continue;
                    int radius = lineScan.nextInt();
                    circleCount++;
                    totalArea += Math.PI * radius * radius;
                } else if (shapeType.equalsIgnoreCase("Square")) {
                    if (!lineScan.hasNextInt()) continue;
                    lineScan.nextInt(); // x
                    if (!lineScan.hasNextInt()) continue;
                    lineScan.nextInt(); // y
                    if (!lineScan.hasNextInt()) continue;
                    int side = lineScan.nextInt();
                    squareCount++;
                    totalArea += side * side;
                } else if (shapeType.equalsIgnoreCase("Triangle")) {
                    if (!lineScan.hasNextInt()) continue;
                    lineScan.nextInt(); // x
                    if (!lineScan.hasNextInt()) continue;
                    lineScan.nextInt(); // y
                    if (!lineScan.hasNextInt()) continue;
                    int base = lineScan.nextInt();
                    if (!lineScan.hasNextInt()) continue;
                    int height = lineScan.nextInt();
                    triangleCount++;
                    totalArea += 0.5 * base * height;
                } else if (shapeType.equalsIgnoreCase("Rectangle")) {
                    if (!lineScan.hasNextInt()) continue;
                    lineScan.nextInt(); // x
                    if (!lineScan.hasNextInt()) continue;
                    lineScan.nextInt(); // y
                    if (!lineScan.hasNextInt()) continue;
                    int width = lineScan.nextInt();
                    if (!lineScan.hasNextInt()) continue;
                    int height = lineScan.nextInt();
                    rectangleCount++;
                    totalArea += width * height;
                }
                lineScan.close();
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(frame, "Failed to parse file: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void showShapeCounts() {
        if (lastLoadedFile == null) {
            JOptionPane.showMessageDialog(frame, "Please import a shape file first!", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        String msg = String.format("Circle: %d\nSquare: %d\nTriangle: %d\nRectangle: %d", circleCount, squareCount, triangleCount, rectangleCount);
        JOptionPane.showMessageDialog(frame, msg, "Shape Counts", JOptionPane.INFORMATION_MESSAGE);
    }

    private void showAnimationWindow() {
        if (lastLoadedFile == null) {
            JOptionPane.showMessageDialog(frame, "Please import a shape file first!", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        // Launch the BounceBox animation window
        new Thread(() -> {
            try {
                bounceboxapp.Main.addShapesFromFile(lastLoadedFile);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Failed to launch animation window: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }).start();
    }

    private void showTotalArea() {
        if (lastLoadedFile == null) {
            JOptionPane.showMessageDialog(frame, "Please import a shape file first!", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        String msg = String.format("Total Area: %.2f", totalArea);
        JOptionPane.showMessageDialog(frame, msg, "Total Area", JOptionPane.INFORMATION_MESSAGE);
    }

    private void showAboutDialog() {
        String msg = "GitHub: https://github.com/thedignityofcoffee/OOP-GroupWork © 2025 thedignityofcoffee\n";
        JOptionPane.showMessageDialog(frame, msg, "About", JOptionPane.INFORMATION_MESSAGE);
    }

    private void exitToMainMenu() {
        frame.dispose();
        // TODO: Back to main menu logic
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(BounceBoxDashboard::new);
    }
}
