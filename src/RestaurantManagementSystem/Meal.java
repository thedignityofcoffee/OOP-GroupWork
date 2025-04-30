/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package oop.demo.restaurantmanagementsystem;

/**
 * Meal class for managing meals composed of ingredients
 * name: the meals name
 * Ingredients: list of ingrdients
 * @author ashongtical
 */

import java.util.*;
public class Meal implements Priceable {
    private String name;
    private ArrayList<Ingredient> ingredients;

    public Meal(String name) {
        this.name = name;
        this.ingredients = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void addIngredient(Ingredient ingredient) {
        ingredients.add(ingredient);
    }

    public ArrayList<Ingredient> getIngredients() {
        return ingredients;
    }

    @Override
    public double getPrice() {
        double total = 0;
        for (Ingredient ingredient : ingredients) {
            total += ingredient.getPrice();
        }
        return total;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Meal: %s (RMB%.2f)\n", name, getPrice()));
        sb.append("Ingredients:\n");
        for (Ingredient ingredient : ingredients) {
            sb.append("  - ").append(ingredient).append("\n");
        }
        return sb.toString();
    }
    
}
