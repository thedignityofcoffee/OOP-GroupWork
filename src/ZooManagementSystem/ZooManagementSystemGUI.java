package ZooManagementSystem;

/**
 * Animal Class - Represents animals in the zoo
 * @author JerryLee
 */

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import BankingTaskListGUI.*;

public class ZooManagementSystemGUI extends JFrame {
    private Zoo southernZone;
    private Zoo northernZone;
    private JTextArea outputArea;
    private HashMap<String, BankAccount> accounts;
    private TaskManager taskManager;

    public ZooManagementSystemGUI() {
        southernZone = new Zoo("Southern-Zone Zoo");
        northernZone = new Zoo("Northern-Zone Zoo");
        accounts = new HashMap<>();
        taskManager = new TaskManager();

        initializeAnimals();

        setTitle("Zoo Management System");
        setSize(700, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);

        JPanel topPanel = new JPanel();
        JLabel titleLabel = new JLabel("Zoo Management System");
        titleLabel.setFont(new Font("Lucida Console", Font.BOLD, 24));
        topPanel.add(titleLabel);

        outputArea = new JTextArea(20, 50);
        outputArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputArea);

        JPanel buttonPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        JButton displaySouthBtn = new JButton("Display Southern Zoo");
        JButton displayNorthBtn = new JButton("Display Northern Zoo");
        JButton addAnimalBtn = new JButton("Add Animal");
        JButton moveAnimalBtn = new JButton("Move Animal");
        JButton removeAnimalBtn = new JButton("Remove Animal");
        JButton findAnimalBtn = new JButton("Find Animal");
        JButton aboutBtn = new JButton("About");
        JButton exitBtn = new JButton("Exit");

        displaySouthBtn.addActionListener(e -> showAnimals(southernZone));
        displayNorthBtn.addActionListener(e -> showAnimals(northernZone));
        addAnimalBtn.addActionListener(e -> addAnimalDialog());
        moveAnimalBtn.addActionListener(e -> moveAnimalDialog());
        removeAnimalBtn.addActionListener(e -> removeAnimalDialog());
        findAnimalBtn.addActionListener(e -> findAnimalDialog());
        aboutBtn.addActionListener(e -> about());
        exitBtn.addActionListener(e -> System.exit(0));

        buttonPanel.add(displaySouthBtn);
        buttonPanel.add(displayNorthBtn);
        buttonPanel.add(addAnimalBtn);
        buttonPanel.add(moveAnimalBtn);
        buttonPanel.add(removeAnimalBtn);
        buttonPanel.add(findAnimalBtn);
        buttonPanel.add(aboutBtn);
        buttonPanel.add(exitBtn);

        add(topPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void initializeAnimals() {
        // Add 5 animals to Southern-Zone Zoo
        southernZone.addAnimal(new Animal("Simba", "African Lion", 6));
        southernZone.addAnimal(new Animal("Dumbo", "African Elephant", 12));
        southernZone.addAnimal(new Animal("Luna", "Gray Wolf", 4));
        southernZone.addAnimal(new Animal("Poe", "Raven", 3));
        southernZone.addAnimal(new Animal("Benny", "Grizzly Bear", 8));

        // Add 5 animals to Northern-Zone Zoo
        northernZone.addAnimal(new Animal("Arctic", "Polar Bear", 7));
        northernZone.addAnimal(new Animal("Blizzard", "Snow Leopard", 5));
        northernZone.addAnimal(new Animal("Frost", "Arctic Fox", 3));
        northernZone.addAnimal(new Animal("Penguin", "Emperor Penguin", 4));
        northernZone.addAnimal(new Animal("Aurora", "Caribou", 6));
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
        JTextField vehicleCost = new JTextField(10);
        JTextField fuelCost = new JTextField(10);
        JTextField caretakersField = new JTextField(20);
        JTextField accountField = new JTextField(15);

        JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.add(new JLabel("Move animal from which zoo?"));
        panel.add(fromSelector);
        panel.add(new JLabel("Animal Name to Move:"));
        panel.add(nameField);
        panel.add(new JLabel("Enter vehicle cost: RMB:"));
        panel.add(vehicleCost);
        panel.add(new JLabel("Enter fuel cost: RMB:"));
        panel.add(fuelCost);
        panel.add(new JLabel("Caretakers (#-separated, max 3):"));
        panel.add(caretakersField);
        panel.add(new JLabel("Bank Account Number:"));
        panel.add(accountField);

        int result = JOptionPane.showConfirmDialog(this, panel, "Move Animal", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            Zoo from = fromSelector.getSelectedIndex() == 0 ? southernZone : northernZone;
            Zoo to = fromSelector.getSelectedIndex() == 0 ? northernZone : southernZone;
            String name = nameField.getText();

            int index = from.findAnimal(name);
            if (index != -1) {
                try {
                    double vCost = Double.parseDouble(vehicleCost.getText());
                    double fCost = Double.parseDouble(fuelCost.getText());
                    double totalCost = vCost + fCost;

                    String[] caretakers = caretakersField.getText().split("#");
                    if (caretakers.length < 1 || caretakers.length > 3) {
                        throw new IllegalArgumentException("Number of caretakers must be between 1 and 3.");
                    }

                    String accountNumber = accountField.getText();
                    BankAccount account = accounts.get(accountNumber);
                    if (account == null) {
                        throw new IllegalArgumentException("Account not found: " + accountNumber);
                    }

                    double previousBalance = account.getBalance();
                    account.withdraw(totalCost);

                    if (account.getBalance() < previousBalance) {
                        Item vehicle = new Item("Transport Truck", "VEH_001");
                        vehicle.setPrice(vCost);

                        Item fuel = new Item("Diesel Fuel", "FUEL_001");
                        fuel.setPrice(fCost);

                        Logistics logistics = new Logistics(vehicle, fuel, caretakers);

                        from.moveAnimal(name, to, logistics);
                        outputArea.setText("Animal moved from " + from.getName() + " to " + to.getName() + ". Total cost RMB " + totalCost + " withdrawn from account: " + accountNumber);
                        taskManager.addTask("Withdrew RMB " + totalCost + " from account: " + accountNumber);
                    } else {
                        JOptionPane.showMessageDialog(this, "Insufficient funds for transaction.", "Transaction Failed", JOptionPane.ERROR_MESSAGE);
                        taskManager.addTask("Failed withdrawal attempt of RMB " + totalCost + " from account: " + accountNumber);
                    }

                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(this, "Invalid cost format!", "Error", JOptionPane.ERROR_MESSAGE);
                } catch (IllegalArgumentException e) {
                    JOptionPane.showMessageDialog(this, e.getMessage(), "Invalid Input", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                outputArea.setText("Animal not found in " + from.getName() + ".");
            }
        }
    }

    private void removeAnimalDialog() {
        JComboBox<String> zooSelector = new JComboBox<>(new String[]{"Southern-Zone", "Northern-Zone"});
        JTextField nameField = new JTextField(10);

        JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.add(new JLabel("Select Zoo:"));
        panel.add(zooSelector);
        panel.add(new JLabel("Enter Animal Name to Remove:"));
        panel.add(nameField);

        int result = JOptionPane.showConfirmDialog(this, panel, "Remove Animal", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            Zoo zoo = zooSelector.getSelectedIndex() == 0 ? southernZone : northernZone;
            String name = nameField.getText();
            zoo.deleteAnimal(name);
            outputArea.setText("'" + name + "' has been removed from " + zoo.getName() + ".");
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

    private void about() {
        JFrame frame = new JFrame("About");
        frame.setSize(300, 200);
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        JLabel label = new JLabel("Designed by JerryLee", JLabel.CENTER);
        label.setFont(new Font("Courier New", Font.PLAIN, 24));
        panel.add(label, BorderLayout.CENTER);
        frame.add(panel);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ZooManagementSystemGUI gui = new ZooManagementSystemGUI();
            gui.setVisible(true);
        });
    }
}
