/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package RestaurantManagementSystem;

/**
 * Abstract class that provides structure 
 * for the billing system
 * @author ashongtical
 */
import javax.swing.*;
import java.util.HashMap;
import java.util.ArrayList;
public abstract class RestaurantBillingSystem {
    protected ArrayList<Meal> menu;

    public RestaurantBillingSystem() {
        this.menu = new ArrayList<>();
    }

    public void addMeal(Meal meal) {
        menu.add(meal);
        System.out.println("Meal '" + meal.getName() + "' added to menu.");
    }

    public boolean removeMeal(String mealName) {
        Meal meal = findMealByName(mealName);
        if (meal != null) {
            menu.remove(meal);
            System.out.println("Meal '" + mealName + "' removed from menu.");
            return true;
        }
        System.out.println("Meal '" + mealName + "' not found in menu.");
        return false;
    }

    public void displayMenu(JTextArea textArea) {
        if (menu.isEmpty()) {
            textArea.append("Menu is currently empty.\n");
            return;
        }

        textArea.append("\n===== RESTAURANT MENU =====\n");
        for (Meal meal : menu) {
            textArea.append(meal.toString() + "\n");
        }
        textArea.append("==========================\n");
    }

    public Meal findMealByName(String mealName) {
        for (Meal meal : menu) {
            if (meal.getName().equalsIgnoreCase(mealName)) {
                return meal;
            }
        }
        return null;
    }

    public abstract boolean addMealToOrder(String mealName);
    public abstract double calculateBill();
    
   //Constructor to initialize the menu

    
    
   
    //Add a meal to the menu
    //meal: The meal to add
   
    
    
    
    //Remove a meal from the menu by name
    //mealName The name of the meal to remove
    //return true if meal was removed, false otherwise
    
    
    //Display all meals on the menu
    
    
    
    //Find a meal on the menu by name
    //mealName: The name of the meal to find
    //The meal if found, null otherwise
    
    
    
    //Abstract method for adding a meal to an order
    //mealName The name of the meal to add to the order
    // return boolean indicating success or failure
    
    
    
    
    //Abstract method for calculating the bill
    //return The total bill amount
    
}
