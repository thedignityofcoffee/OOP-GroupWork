/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package oop.demo.zoomanagementsystem;

/**
 * Logistics Class - Manages transportation details for animal movements
 * @author JerryLee
 */

import java.util.Arrays;
import java.util.List;

public class Logistics {
    public Item vehicle;
    public Item fuel;
    public List<String> caretakers;

    public Logistics(Item vehicle, Item fuel, String[] caretakers) {
        this.vehicle = vehicle;
        this.fuel = fuel;
        this.caretakers = Arrays.asList(caretakers);
    }

    public void display() {
        System.out.println("Vehicle Information:");
        vehicle.display();
        System.out.println("Fuel Information:");
        fuel.display();
        System.out.println("Caretakers: " + caretakers);
    }
}