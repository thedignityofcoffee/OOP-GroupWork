package ZooManagementSystem;

/**
 * Zoo Class - Manages a collection of animals
 * @author JerryLee
 */

public class Zoo {
    public String name;
    public Animal[] animals;
    public int counter;

    public Zoo(String name) {
        this.name = name;
        this.animals = new Animal[10];
        this.counter = 0;
    }

    public String getName() {
        return name;
    }

    public int getCounter() {
        return counter;
    }

    public void addAnimal(Animal animal) {
        if (counter < 10) {
            animals[counter++] = animal;
        } else {
            System.out.println("Zoo is full. Cannot add more animals.");
        }
    }

    public void deleteAnimal(String animalName) {
        for (int i = 0; i < counter; i++) {
            if (animals[i].getName().equals(animalName)) {
                for (int j = i; j < counter - 1; j++) {
                    animals[j] = animals[j + 1];
                }
                counter--;
                System.out.println(animalName + " has been removed from the zoo.");
                return;
            }
        }
        System.out.println(animalName + " not found in the zoo.");
    }

    public int findAnimal(String animalName) {
        for (int i = 0; i < counter; i++) {
            if (animals[i].getName().equals(animalName)) {
                return i;
            }
        }
        return -1;
    }

    public Animal getAnimal(int index) {
        if (index >= 0 && index < counter) {
            return animals[index];
        }
        return null;
    }

    public void moveAnimal(String animalName, Zoo destinationZoo, Logistics logistics) {
        int index = findAnimal(animalName);
        if (index != -1) {
            Animal animal = animals[index];
            for (int i = index; i < counter - 1; i++) {
                animals[i] = animals[i + 1];
            }
            counter--;
            destinationZoo.addAnimal(animal);
            System.out.println(animalName + " has been moved to " + destinationZoo.getName() + ".");
            System.out.println(" ");
            System.out.println("----- Logistics Details -----");
            logistics.display();
            System.out.println("---------------------------");
            System.out.println(" ");
            System.out.println("****************************************");
            System.out.println(" ");
        } else {
            System.out.println(animalName + " not found in this zoo.");
        }
    }

    public void displayAnimals() {
        System.out.println(" ");
        System.out.println("--- Animals in " + name + " ---");
        for (int i = 0; i < counter; i++) {
            animals[i].display();
        }
        System.out.println("---------------------------");
    }
}