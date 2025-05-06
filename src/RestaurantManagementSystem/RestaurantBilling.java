/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package RestaurantManagementSystem;

/**
 * Restaurant billing implementation that handles 
 * customer orders and bill calculation
 * @author ashongtical
 */

import java.util.ArrayList;
import java.util.HashMap;
public class RestaurantBilling extends RestaurantBillingSystem {
    private ArrayList<Meal> order;
    private HashMap<String, Integer> mealQuantities;

    public RestaurantBilling() {
        super();
        this.order = new ArrayList<>();
        this.mealQuantities = new HashMap<>();
    }

    @Override
    public boolean addMealToOrder(String mealName) {
        Meal meal = findMealByName(mealName);
        if (meal != null) {
            order.add(meal);
            mealQuantities.put(mealName, mealQuantities.getOrDefault(mealName, 0) + 1);
            System.out.println("Added '" + mealName + "' to order.");
            return true;
        }
        System.out.println("Meal '" + mealName + "' not found in menu.");
        return false;
    }

    @Override
    public double calculateBill() {
        double total = 0;
        for (Meal meal : order) {
            total += meal.getPrice();
        }
        return total;
    }

    public void clearOrder() {
        order.clear();
        mealQuantities.clear();
        System.out.println("Order cleared.");
    }

    public void displayOrder() {
        if (order.isEmpty()) {
            System.out.println("Current order is empty.");
            return;
        }

        System.out.println("\n===== CURRENT ORDER =====");
        HashMap<String, Integer> uniqueMeals = new HashMap<>();
        HashMap<String, Double> mealPrices = new HashMap<>();

        for (Meal meal : order) {
            String name = meal.getName();
            uniqueMeals.put(name, uniqueMeals.getOrDefault(name, 0) + 1);
            mealPrices.put(name, meal.getPrice());
        }

        for (String mealName : uniqueMeals.keySet()) {
            int quantity = uniqueMeals.get(mealName);
            double price = mealPrices.get(mealName);
            System.out.printf("%-20s %d x RMB%.2f = RMB%.2f\n",
                    mealName, quantity, price, quantity * price);
        }
        System.out.println("--------------------------");
        System.out.printf("TOTAL: RMB%.2f\n", calculateBill());
        System.out.println("========================");
    }

    public String generateBill(String customerName) {
        StringBuilder bill = new StringBuilder();
        bill.append("\n===== RESTAURANT BILL =====\n");
        bill.append("Customer: ").append(customerName).append("\n\n");
        bill.append("Order Details:\n");

        HashMap<String, Integer> uniqueMeals = new HashMap<>();
        HashMap<String, Double> mealPrices = new HashMap<>();

        for (Meal meal : order) {
            String name = meal.getName();
            uniqueMeals.put(name, uniqueMeals.getOrDefault(name, 0) + 1);
            mealPrices.put(name, meal.getPrice());
        }

        for (String mealName : uniqueMeals.keySet()) {
            int quantity = uniqueMeals.get(mealName);
            double price = mealPrices.get(mealName);
            bill.append(String.format("%-20s %d x RMB%.2f = RMB%.2f\n",
                    mealName, quantity, price, quantity * price));
        }

        bill.append("--------------------------\n");
        bill.append(String.format("TOTAL: RMB%.2f\n", calculateBill()));
        bill.append("==========================\n");
        bill.append("Thank you for dining with us!");

        return bill.toString();
    }
    
    //Constructor to initialize order list and meal quantities map
    //
    

    //Add a meal from the menu to the customer's order
    //mealName: The name of the meal to add
    //return true if meal was added successfully, false otherwise
    

    
    //Override the calculateBill() and calculate the total bill for the customer's order
    //return the total bill amount
    
    
    //Clear the current order
    
    
    
    //Display the current order with meal quantities and total price
    
    
    
    //Generate a formatted bill for the customer
    //customerName: The name of the customer
    //return String representation of the bill
    
    
}
