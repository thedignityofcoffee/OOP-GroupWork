package ZooManagementSystem;

/**
 * Animal Class - Represents animals in the zoo
 * @author JerryLee
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ZooManagementSystemGUI extends JFrame {
    private Zoo southernZone;
    private Zoo northernZone;
    private JTextArea outputArea;

    public ZooManagementSystemGUI() {
        southernZone = new Zoo("Southern-Zone Zoo");
        northernZone = new Zoo("Northern-Zone Zoo");

        initializeAnimals();

        setTitle("Zoo Management System");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel();
        JLabel titleLabel = new JLabel("Zoo Management System");
        titleLabel.setFont(new Font("Lucida Console", Font.BOLD, 24));
        topPanel.add(titleLabel);

        outputArea = new JTextArea(20, 50);
        outputArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputArea);

        JPanel buttonPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        JButton displaySouthBtn = new JButton("Display Southern Zoo");
        JButton displayNorthBtn = new JButton("Display Northern Zoo");
        JButton addAnimalBtn = new JButton("Add Animal");
        JButton moveAnimalBtn = new JButton("Move Animal");
        JButton findAnimalBtn = new JButton("Find Animal");
        JButton exitBtn = new JButton("Exit");

        displaySouthBtn.addActionListener(e -> showAnimals(southernZone));
        displayNorthBtn.addActionListener(e -> showAnimals(northernZone));
        addAnimalBtn.addActionListener(e -> addAnimalDialog());
        moveAnimalBtn.addActionListener(e -> moveAnimalDialog());
        findAnimalBtn.addActionListener(e -> findAnimalDialog());
        exitBtn.addActionListener(e -> System.exit(0));

        buttonPanel.add(displaySouthBtn);
        buttonPanel.add(displayNorthBtn);
        buttonPanel.add(addAnimalBtn);
        buttonPanel.add(moveAnimalBtn);
        buttonPanel.add(findAnimalBtn);
        buttonPanel.add(exitBtn);

        add(topPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void initializeAnimals() {
        southernZone.addAnimal(new Animal("Simba", "African Lion", 6));
        southernZone.addAnimal(new Animal("Dumbo", "African Elephant", 12));
        northernZone.addAnimal(new Animal("Arctic", "Polar Bear", 7));
        northernZone.addAnimal(new Animal("Frost", "Arctic Fox", 3));
    }

    private void showAnimals(Zoo zoo) {
        outputArea.setText("Animals in " + zoo.getName() + ":\n");
        for (int i = 0; i < zoo.getCounter(); i++) {
            Animal animal = zoo.getAnimal(i);
            outputArea.append((i + 1) + ". " + animal.getName() + " - " +
                    animal.getSpecies() + ", Age: " + animal.getAge() + "\n");
        }
    }

    private void addAnimalDialog() {
        JTextField nameField = new JTextField(10);
        JTextField speciesField = new JTextField(10);
        JTextField ageField = new JTextField(5);

        JComboBox<String> zooSelector = new JComboBox<>(new String[]{"Southern-Zone", "Northern-Zone"});

        JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.add(new JLabel("Animal Name:"));
        panel.add(nameField);
        panel.add(new JLabel("Species:"));
        panel.add(speciesField);
        panel.add(new JLabel("Age:"));
        panel.add(ageField);
        panel.add(new JLabel("Zoo:"));
        panel.add(zooSelector);

        int result = JOptionPane.showConfirmDialog(this, panel, "Add New Animal", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            try {
                String name = nameField.getText();
                String species = speciesField.getText();
                int age = Integer.parseInt(ageField.getText());
                Zoo zoo = zooSelector.getSelectedIndex() == 0 ? southernZone : northernZone;
                zoo.addAnimal(new Animal(name, species, age));
                outputArea.setText("Animal added successfully to " + zoo.getName() + ".");
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Invalid age input!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void moveAnimalDialog() {
        JComboBox<String> fromSelector = new JComboBox<>(new String[]{"Southern-Zone", "Northern-Zone"});
        JTextField nameField = new JTextField(10);

        JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.add(new JLabel("Move animal from which zoo?"));
        panel.add(fromSelector);
        panel.add(new JLabel("Animal Name to Move:"));
        panel.add(nameField);

        int result = JOptionPane.showConfirmDialog(this, panel, "Move Animal", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            Zoo from = fromSelector.getSelectedIndex() == 0 ? southernZone : northernZone;
            Zoo to = fromSelector.getSelectedIndex() == 0 ? northernZone : southernZone;
            String name = nameField.getText();

            int index = from.findAnimal(name);
            if (index != -1) {
                from.moveAnimal(name, to, new Logistics(new Item("Truck", "001"), new Item("Fuel", "002"), new String[]{"John"}));
                outputArea.setText("Animal moved from " + from.getName() + " to " + to.getName() + ".");
            } else {
                outputArea.setText("Animal not found in " + from.getName() + ".");
            }
        }
    }

    private void findAnimalDialog() {
        String name = JOptionPane.showInputDialog(this, "Enter animal name to search:");
        if (name != null && !name.isBlank()) {
            int index1 = southernZone.findAnimal(name);
            int index2 = northernZone.findAnimal(name);
            if (index1 != -1) {
                outputArea.setText("Animal found in Southern-Zone Zoo:\n");
                southernZone.getAnimal(index1).display();
            } else if (index2 != -1) {
                outputArea.setText("Animal found in Northern-Zone Zoo:\n");
                northernZone.getAnimal(index2).display();
            } else {
                outputArea.setText("Animal not found in either zoo.");
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ZooManagementSystemGUI gui = new ZooManagementSystemGUI();
            gui.setVisible(true);
        });
    }
}
