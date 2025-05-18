package ZooManagementSystem;

/**
 * Item Class - Represents objects used in logistics operations
 * @author JerryLee
 */

public class Item {
    
    public String name;
    public double price;
    public String code;
    public Item(String name, String code) {
        this.name = name;
        this.code = code;
        this.price = 0;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void display() {
        System.out.print("Name: " + name);
        System.out.print(" Price: " + price);
        System.out.println(" Code: " + code);
    }
}