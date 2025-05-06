/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ZooManagementSystem;

/**
 * Animal Class - Represents animals in the zoo
 * @author JerryLee
 */
public class Animal {
    public String name;
    public String species;
    public int age;

    public Animal(String name, String species, int age) {
        this.name = name;
        this.species = species;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void display() {
        System.out.print("Animal Name: " + name);
        System.out.print(" Species: " + species);
        System.out.println(" Age: " + age);
    }
}