/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package RestaurantManagementSystem;

/**
 * Restaurant billing implementation 
 * that handles customer orders and bill calculation
 * @author ashongtical
 */

import java.util.*;

public class RestaurantManagementSystem {
    private static Scanner scanner = new Scanner(System.in);
    private static RestaurantBilling billing = new RestaurantBilling();
    private static HashMap<String, Ingredient> ingredientInventory = new HashMap<>();
    
    // Main method to run the application
    public static void main(String[] args) {
        System.out.println("Welcome to the Restaurant Management System!");
        boolean exit = false;
        
        // Add some sample ingredients to the inventory
        addSampleIngredients();
        
        while (!exit) {
            displayMainMenu();
            System.out.print("Enter your choice: ");
            String choice = scanner.nextLine();
            
            switch (choice) {
                case "1":
                    manageIngredients();
                    break;
                case "2":
                    manageMeals();
                    break;
                case "3":
                    manageMenu();
                    break;
                case "4":
                    manageOrders();
                    break;
                case "5":
                    exit = true;
                    System.out.println("Thank you for using the Restaurant Management System. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
    
    /**
     * Display the main menu options
     */
    private static void displayMainMenu() {
        System.out.println("\n===== RESTAURANT MANAGEMENT SYSTEM =====");
        System.out.println("1. Manage Ingredients");
        System.out.println("2. Manage Meals");
        System.out.println("3. Manage Menu");
        System.out.println("4. Manage Orders");
        System.out.println("5. Exit");
        System.out.println("=======================================");
    }
    
    /**
     * Add sample ingredients to the inventory for demonstration
     */
    private static void addSampleIngredients() {
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
    
   //Menu for managing ingredients
   
    private static void manageIngredients() {
        boolean back = false;
        
        while (!back) {
            System.out.println("\n===== INGREDIENT MANAGEMENT =====");
            System.out.println("1. Add New Ingredient");
            System.out.println("2. View All Ingredients");
            System.out.println("3. Remove Ingredient");
            System.out.println("4. Back to Main Menu");
            System.out.println("===============================");
            
            System.out.print("Enter your choice: ");
            String choice = scanner.nextLine();
            
            switch (choice) {
                case "1":
                    addIngredient();
                    break;
                case "2":
                    viewAllIngredients();
                    break;
                case "3":
                    removeIngredient();
                    break;
                case "4":
                    back = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
    
    // Add a new ingredient to the inventory
    private static void addIngredient() {
        System.out.print("Enter ingredient name: ");
        String name = scanner.nextLine();
        
        if (ingredientInventory.containsKey(name)) {
            System.out.println("An ingredient with this name already exists.");
            return;
        }
        
        double price = 0;
        boolean validPrice = false;
        
        while (!validPrice) {
            System.out.print("Enter ingredient price: RMB");
            try {
                price = Double.parseDouble(scanner.nextLine());
                if (price >= 0) {
                    validPrice = true;
                } else {
                    System.out.println("Price must be a positive number.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid price format. Please enter a valid number.");
            }
        }
        
        Ingredient newIngredient = new Ingredient(name, price);
        ingredientInventory.put(name, newIngredient);
        System.out.println("Ingredient '" + name + "' added successfully.");
    }
    
    //View all ingredients in the inventory
    
    private static void viewAllIngredients() {
        if (ingredientInventory.isEmpty()) {
            System.out.println("No ingredients in inventory.");
            return;
        }
        
        System.out.println("\n===== INGREDIENT INVENTORY =====");
        for (Ingredient ingredient : ingredientInventory.values()) {
            System.out.println(ingredient);
        }
        System.out.println("==============================");
    }
    
    //Remove an ingredient from the inventory
   
    private static void removeIngredient() {
        if (ingredientInventory.isEmpty()) {
            System.out.println("No ingredients in inventory to remove.");
            return;
        }
        
        System.out.print("Enter the name of the ingredient to remove: ");
        String name = scanner.nextLine();
        
        if (ingredientInventory.containsKey(name)) {
            ingredientInventory.remove(name);
            System.out.println("Ingredient '" + name + "' removed successfully.");
        } else {
            System.out.println("Ingredient '" + name + "' not found in inventory.");
        }
    }
    
   //Menu for managing meals
    private static void manageMeals() {
        boolean back = false;
        ArrayList<Meal> mealsList = new ArrayList<>();
        
        while (!back) {
            System.out.println("\n===== MEAL MANAGEMENT =====");
            System.out.println("1. Create New Meal");
            System.out.println("2. View All Created Meals");
            System.out.println("3. Add Meal to Menu");
            System.out.println("4. Back to Main Menu");
            System.out.println("==========================");
            
            System.out.print("Enter your choice: ");
            String choice = scanner.nextLine();
            
            switch (choice) {
                case "1":
                    Meal newMeal = createMeal();
                    if (newMeal != null) {
                        mealsList.add(newMeal);
                    }
                    break;
                case "2":
                    viewAllMeals(mealsList);
                    break;
                case "3":
                    addMealToMenu(mealsList);
                    break;
                case "4":
                    back = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
    
    //Create a new meal with ingredients
     //The newly created meal, or null if creation was cancelled
     
    private static Meal createMeal() {
        if (ingredientInventory.isEmpty()) {
            System.out.println("No ingredients available. Please add ingredients first.");
            return null;
        }
        
        System.out.print("Enter meal name: ");
        String name = scanner.nextLine();
        
        Meal meal = new Meal(name);
        
        boolean addingIngredients = true;
        
        while (addingIngredients) {
            viewAllIngredients();
            System.out.print("\nEnter ingredient name to add (or 'done' to finish): ");
            String ingredientName = scanner.nextLine();
            
            if (ingredientName.equalsIgnoreCase("done")) {
                addingIngredients = false;
            } else {
                Ingredient ingredient = ingredientInventory.get(ingredientName);
                if (ingredient != null) {
                    meal.addIngredient(ingredient);
                    System.out.println("Added '" + ingredientName + "' to the meal.");
                } else {
                    System.out.println("Ingredient '" + ingredientName + "' not found.");
                }
            }
        }
        
        System.out.println("Meal '" + name + "' created successfully.");
        System.out.println(meal);
        return meal;
    }
    
    //View all created meals
     //mealsList: List of meals to display
    
    private static void viewAllMeals(ArrayList<Meal> mealsList) {
        if (mealsList.isEmpty()) {
            System.out.println("No meals have been created yet.");
            return;
        }
        
        System.out.println("\n===== CREATED MEALS =====");
        for (int i = 0; i < mealsList.size(); i++) {
            System.out.println((i + 1) + ". " + mealsList.get(i));
        }
        System.out.println("=======================");
    }
    
    //Add a meal from the created meals list to the menu
    //mealsList: List of created meals
   
    private static void addMealToMenu(ArrayList<Meal> mealsList) {
        if (mealsList.isEmpty()) {
            System.out.println("No meals have been created yet to add to the menu.");
            return;
        }
        
        viewAllMeals(mealsList);
        
        System.out.print("Enter the number of the meal to add to the menu: ");
        try {
            int mealNumber = Integer.parseInt(scanner.nextLine());
            
            if (mealNumber >= 1 && mealNumber <= mealsList.size()) {
                Meal selectedMeal = mealsList.get(mealNumber - 1);
                billing.addMeal(selectedMeal);
            } else {
                System.out.println("Invalid meal number.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid number.");
        }
    }
    
    //Menu for managing the restaurant menu
   
    private static void manageMenu() {
        boolean back = false;
        
        while (!back) {
            System.out.println("\n===== MENU MANAGEMENT =====");
            System.out.println("1. View Menu");
            System.out.println("2. Remove Meal from Menu");
            System.out.println("3. Back to Main Menu");
            System.out.println("=========================");
            
            System.out.print("Enter your choice: ");
            String choice = scanner.nextLine();
            
            switch (choice) {
                case "1":
                    billing.displayMenu();
                    break;
                case "2":
                    removeMealFromMenu();
                    break;
                case "3":
                    back = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
    
    //Remove a meal from the menu
    
    private static void removeMealFromMenu() {
        billing.displayMenu();
        
        System.out.print("Enter the name of the meal to remove from the menu: ");
        String mealName = scanner.nextLine();
        
        billing.removeMeal(mealName);
    }
    
    //Menu for managing customer orders
    
    private static void manageOrders() {
        boolean back = false;
        
        while (!back) {
            System.out.println("\n===== ORDER MANAGEMENT =====");
            System.out.println("1. View Menu");
            System.out.println("2. Add Meal to Order");
            System.out.println("3. View Current Order");
            System.out.println("4. Generate Bill");
            System.out.println("5. Clear Order");
            System.out.println("6. Back to Main Menu");
            System.out.println("==========================");
            
            System.out.print("Enter your choice: ");
            String choice = scanner.nextLine();
            
            switch (choice) {
                case "1":
                    billing.displayMenu();
                    break;
                case "2":
                    addMealToOrder();
                    break;
                case "3":
                    billing.displayOrder();
                    break;
                case "4":
                    generateBill();
                    break;
                case "5":
                    billing.clearOrder();
                    break;
                case "6":
                    back = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
    
    //Add a meal from the menu to the current order
     //
    private static void addMealToOrder() {
        billing.displayMenu();
        
        System.out.print("Enter the name of the meal to add to the order: ");
        String mealName = scanner.nextLine();
        
        billing.addMealToOrder(mealName);
    }
    
    //Generate a bill for the current order
    //
    private static void generateBill() {
        System.out.print("Enter customer name: ");
        String customerName = scanner.nextLine();
        
        String bill = billing.generateBill(customerName);
        System.out.println(bill);
        
        System.out.print("Would you like to clear this order now? (y/n): ");
        String choice = scanner.nextLine();
        
        if (choice.equalsIgnoreCase("y")) {
            billing.clearOrder();
        }
    }
}
