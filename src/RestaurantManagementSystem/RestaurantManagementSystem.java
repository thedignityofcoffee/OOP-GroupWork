package RestaurantManagementSystem;

import BankingTaskListGUI.BankAccount;
import BankingTaskListGUI.BankingTaskManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

public class RestaurantManagementSystem extends JFrame {
    private RestaurantBilling billing = new RestaurantBilling();
    private HashMap<String, Ingredient> ingredientInventory = new HashMap<>();
    private ArrayList<Meal> mealsList = new ArrayList<>();

    private JTabbedPane tabbedPane;

    public RestaurantManagementSystem() {
        setTitle("Restaurant Management System");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        addSampleIngredients();
        initUI();
    }

    private void initUI() {
        tabbedPane = new JTabbedPane();

        // Create tabs
        tabbedPane.addTab("Ingredients", createIngredientsPanel());
        tabbedPane.addTab("Meals", createMealsPanel());
        tabbedPane.addTab("Menu", createMenuPanel());
        tabbedPane.addTab("Orders", createOrdersPanel());

        add(tabbedPane);
    }

    private JPanel createIngredientsPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        // Button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton addButton = new JButton("Add Ingredient");
        JButton viewButton = new JButton("View Ingredients");
        JButton removeButton = new JButton("Remove Ingredient");

        buttonPanel.add(addButton);
        buttonPanel.add(viewButton);
        buttonPanel.add(removeButton);

        // Text area for display
        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);

        panel.add(buttonPanel, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);

        // Button actions
        addButton.addActionListener(e -> {
            String name = JOptionPane.showInputDialog(this, "Enter ingredient name:");
            if (name != null && !name.trim().isEmpty()) {
                if (ingredientInventory.containsKey(name)) {
                    textArea.append("An ingredient with this name already exists.\n");
                    return;
                }

                try {
                    String priceStr = JOptionPane.showInputDialog(this, "Enter ingredient price:");
                    double price = Double.parseDouble(priceStr);
                    if (price >= 0) {
                        Ingredient newIngredient = new Ingredient(name, price);
                        ingredientInventory.put(name, newIngredient);
                        textArea.append("Ingredient '" + name + "' added successfully.\n");
                    } else {
                        textArea.append("Price must be a positive number.\n");
                    }
                } catch (NumberFormatException ex) {
                    textArea.append("Invalid price format. Please enter a valid number.\n");
                }
            }
        });

        viewButton.addActionListener(e -> {
            if (ingredientInventory.isEmpty()) {
                textArea.append("No ingredients in inventory.\n");
                return;
            }

            textArea.append("\n===== INGREDIENT INVENTORY =====\n");
            for (Ingredient ingredient : ingredientInventory.values()) {
                textArea.append(ingredient + "\n");
            }
            textArea.append("==============================\n");
        });

        removeButton.addActionListener(e -> {
            if (ingredientInventory.isEmpty()) {
                textArea.append("No ingredients in inventory to remove.\n");
                return;
            }

            String name = JOptionPane.showInputDialog(this, "Enter the name of the ingredient to remove:");
            if (name != null && !name.trim().isEmpty()) {
                if (ingredientInventory.containsKey(name)) {
                    ingredientInventory.remove(name);
                    textArea.append("Ingredient '" + name + "' removed successfully.\n");
                } else {
                    textArea.append("Ingredient '" + name + "' not found in inventory.\n");
                }
            }
        });

        return panel;
    }

    private JPanel createMealsPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        // Button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton createButton = new JButton("Create Meal");
        JButton viewButton = new JButton("View Meals");
        JButton addToMenuButton = new JButton("Add Meal to Menu");

        buttonPanel.add(createButton);
        buttonPanel.add(viewButton);
        buttonPanel.add(addToMenuButton);

        // Text area for display
        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);

        panel.add(buttonPanel, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);

        // Button actions
        createButton.addActionListener(e -> {
            if (ingredientInventory.isEmpty()) {
                textArea.append("No ingredients available. Please add ingredients first.\n");
                return;
            }

            String name = JOptionPane.showInputDialog(this, "Enter meal name:");
            if (name != null && !name.trim().isEmpty()) {
                Meal meal = new Meal(name);

                // Show ingredient selection dialog
                while (true) {
                    StringBuilder ingredientsList = new StringBuilder("Available Ingredients:\n");
                    for (String ingredientName : ingredientInventory.keySet()) {
                        ingredientsList.append("- ").append(ingredientName).append("\n");
                    }

                    String ingredientName = JOptionPane.showInputDialog(this,
                            ingredientsList.toString() + "\nEnter ingredient name to add (or 'done' to finish):");

                    if (ingredientName == null || ingredientName.equalsIgnoreCase("done")) {
                        break;
                    }

                    Ingredient ingredient = ingredientInventory.get(ingredientName);
                    if (ingredient != null) {
                        meal.addIngredient(ingredient);
                        textArea.append("Added '" + ingredientName + "' to the meal.\n");
                    } else {
                        textArea.append("Ingredient '" + ingredientName + "' not found.\n");
                    }
                }

                mealsList.add(meal);
                textArea.append("Meal '" + name + "' created successfully.\n");
                textArea.append(meal.toString() + "\n");
            }
        });

        viewButton.addActionListener(e -> {
            if (mealsList.isEmpty()) {
                textArea.append("No meals have been created yet.\n");
                return;
            }

            textArea.append("\n===== CREATED MEALS =====\n");
            for (int i = 0; i < mealsList.size(); i++) {
                textArea.append((i + 1) + ". " + mealsList.get(i).toString() + "\n");
            }
            textArea.append("=======================\n");
        });

        addToMenuButton.addActionListener(e -> {
            if (mealsList.isEmpty()) {
                textArea.append("No meals have been created yet to add to the menu.\n");
                return;
            }

            StringBuilder mealOptions = new StringBuilder("Select a meal to add to menu:\n");
            for (int i = 0; i < mealsList.size(); i++) {
                mealOptions.append((i + 1)).append(". ").append(mealsList.get(i).getName()).append("\n");
            }

            try {
                String selection = JOptionPane.showInputDialog(this, mealOptions.toString());
                if (selection != null) {
                    int mealNumber = Integer.parseInt(selection);
                    if (mealNumber >= 1 && mealNumber <= mealsList.size()) {
                        Meal selectedMeal = mealsList.get(mealNumber - 1);
                        billing.addMeal(selectedMeal);
                        textArea.append("Meal '" + selectedMeal.getName() + "' added to menu.\n");
                    } else {
                        textArea.append("Invalid meal number.\n");
                    }
                }
            } catch (NumberFormatException ex) {
                textArea.append("Please enter a valid number.\n");
            }
        });

        return panel;
    }

    private JPanel createMenuPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        // Button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton viewButton = new JButton("View Menu");
        JButton removeButton = new JButton("Remove Meal");

        buttonPanel.add(viewButton);
        buttonPanel.add(removeButton);

        // Text area for display
        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);

        panel.add(buttonPanel, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);

        // Button actions
        viewButton.addActionListener(e -> {
            billing.displayMenu(textArea);
        });

        removeButton.addActionListener(e -> {
            String mealName = JOptionPane.showInputDialog(this, "Enter the name of the meal to remove from the menu:");
            if (mealName != null && !mealName.trim().isEmpty()) {
                if (billing.removeMeal(mealName)) {
                    textArea.append("Meal '" + mealName + "' removed from menu.\n");
                } else {
                    textArea.append("Meal '" + mealName + "' not found in menu.\n");
                }
            }
        });

        return panel;
    }

    private JPanel createOrdersPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        // Button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton viewMenuButton = new JButton("View Menu");
        JButton AddOrderButton = new JButton("Add Order");
        JButton viewOrderButton = new JButton("View Order");
        JButton generateBillButton = new JButton("Generate Bill");
        JButton clearOrderButton = new JButton("Clear Order");

        buttonPanel.add(viewMenuButton);
        buttonPanel.add(AddOrderButton);
        buttonPanel.add(viewOrderButton);
        buttonPanel.add(generateBillButton);
        buttonPanel.add(clearOrderButton);

        // Text area for display
        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);

        panel.add(buttonPanel, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);

        // Button actions
        viewMenuButton.addActionListener(e -> {
            billing.displayMenu(textArea);
        });

        AddOrderButton.addActionListener(e -> {
            String mealName = JOptionPane.showInputDialog(this, "Enter the name of the meal to add to the order:");
            if (mealName != null && !mealName.trim().isEmpty()) {
                if (billing.addMealToOrder(mealName)) {
                    textArea.append("Added '" + mealName + "' to order.\n");
                } else {
                    textArea.append("Meal '" + mealName + "' not found in menu.\n");
                }
            }
        });

        viewOrderButton.addActionListener(e -> {
            billing.displayOrder(textArea);
        });

        generateBillButton.addActionListener(e -> {
            String customerAccount = JOptionPane.showInputDialog(this, "Enter customer bank account:");
            BankingTaskManager bankingTaskManager = new BankingTaskManager();
            BankAccount account = bankingTaskManager.getAccount(customerAccount);
            while (account == null){
                customerAccount = JOptionPane.showInputDialog(this, "Could not find this account, enter it again");
                bankingTaskManager = new BankingTaskManager();
                account = bankingTaskManager.getAccount(customerAccount);
            }
            bankingTaskManager.withdraw(customerAccount, billing.calculateBill());
            if (customerAccount != null && !customerAccount.trim().isEmpty()) {
                String bill = billing.generateBill(customerAccount);
                textArea.append(bill + "\n");

                int option = JOptionPane.showConfirmDialog(this,
                        "Would you like to clear this order now?",
                        "Clear Order",
                        JOptionPane.YES_NO_OPTION);

                if (option == JOptionPane.YES_OPTION) {
                    billing.clearOrder();
                    textArea.append("Order cleared.\n");
                }
            }
        });

        clearOrderButton.addActionListener(e -> {
            billing.clearOrder();
            textArea.append("Order cleared.\n");
        });

        return panel;
    }

    private void addSampleIngredients() {
        ingredientInventory.put("Tomato", new Ingredient("Tomato", 0.75));
        ingredientInventory.put("Lettuce", new Ingredient("Lettuce", 0.50));
        ingredientInventory.put("Cheese", new Ingredient("Cheese", 1.25));
        ingredientInventory.put("Beef Patty", new Ingredient("Beef Patty", 3.50));
        ingredientInventory.put("Chicken Breast", new Ingredient("Chicken Breast", 2.75));
        ingredientInventory.put("Bun", new Ingredient("Bun", 0.80));
        ingredientInventory.put("French Fries", new Ingredient("French Fries", 1.50));
        ingredientInventory.put("Rice", new Ingredient("Rice", 1.00));
        ingredientInventory.put("Pasta", new Ingredient("Pasta", 1.20));
        ingredientInventory.put("Marinara Sauce", new Ingredient("Marinara Sauce", 1.75));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            RestaurantManagementSystem system = new RestaurantManagementSystem();
            system.setVisible(true);
        });
    }
}