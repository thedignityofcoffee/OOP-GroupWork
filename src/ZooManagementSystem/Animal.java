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
    public String getSpecies() {
        return species;
    }
    public int getAge() {
        return age;
    }


    public void display() {
        System.out.print("Animal Name: " + name);
        System.out.print(" Species: " + species);
        System.out.println(" Age: " + age);
    }
}