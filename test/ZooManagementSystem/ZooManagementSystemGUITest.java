package ZooManagementSystem;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ZooManagementSystemGUITest {

    private Zoo southernZone;
    private Zoo northernZone;

    @BeforeEach
    void setup() {
        southernZone = new Zoo("Southern-Zone Zoo");
        northernZone = new Zoo("Northern-Zone Zoo");

        southernZone.addAnimal(new Animal("Simba", "African Lion", 6));
        southernZone.addAnimal(new Animal("Dumbo", "African Elephant", 12));
        northernZone.addAnimal(new Animal("Frost", "Arctic Fox", 3));
    }

    @Test
    void testAddAnimal() {
        int initialCount = southernZone.getCounter();
        southernZone.addAnimal(new Animal("Milo", "Meerkat", 2));
        assertEquals(initialCount + 1, southernZone.getCounter());
        assertEquals("Milo", southernZone.getAnimal(initialCount).getName());
    }

    @Test
    void testFindAnimal() {
        int index = southernZone.findAnimal("Simba");
        assertTrue(index >= 0);
        assertEquals("Simba", southernZone.getAnimal(index).getName());

        int notFound = southernZone.findAnimal("NonExistent");
        assertEquals(-1, notFound);
    }

    @Test
    void testMoveAnimal() {
        Animal dumbo = southernZone.getAnimal(southernZone.findAnimal("Dumbo"));
        Item vehicle = new Item("Truck", "VEH001");
        vehicle.setPrice(1000);
        Item fuel = new Item("Diesel", "FUEL001");
        fuel.setPrice(500);
        String[] caretakers = {"John", "Jane"};

        Logistics logistics = new Logistics(vehicle, fuel, caretakers);
        southernZone.moveAnimal("Dumbo", northernZone, logistics);

        assertEquals(-1, southernZone.findAnimal("Dumbo"));
        assertNotEquals(-1, northernZone.findAnimal("Dumbo"));
    }

    @Test
    void testRemoveAnimal() {
        int index = southernZone.findAnimal("Simba");
        assertTrue(index >= 0);
        southernZone.deleteAnimal("Simba");
        assertEquals(-1, southernZone.findAnimal("Simba"));
    }
}
