package ZooManagementSystem;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ZooTest {

    private Zoo zoo;
    private Zoo otherZoo;

    @BeforeEach
    void setUp() {
        zoo = new Zoo("Test Zoo");
        otherZoo = new Zoo("Other Zoo");
        zoo.addAnimal(new Animal("Leo", "Lion", 5));
        zoo.addAnimal(new Animal("Ella", "Elephant", 10));
    }

    @Test
    void testAddAnimal() {
        int initialCount = zoo.getCounter();
        zoo.addAnimal(new Animal("Tiger", "Bengal Tiger", 4));
        assertEquals(initialCount + 1, zoo.getCounter());
        assertEquals("Tiger", zoo.getAnimal(zoo.getCounter() - 1).getName());
    }

    @Test
    void testAddAnimalBeyondCapacity() {
        for (int i = zoo.getCounter(); i < 10; i++) {
            zoo.addAnimal(new Animal("Animal" + i, "Species", i));
        }
        assertEquals(10, zoo.getCounter());

        // Adding 11th animal should not increase counter
        zoo.addAnimal(new Animal("Extra", "Overflow", 1));
        assertEquals(10, zoo.getCounter(), "Zoo should not exceed capacity of 10 animals");
    }

    @Test
    void testDeleteAnimal() {
        int indexBefore = zoo.findAnimal("Leo");
        assertTrue(indexBefore >= 0);

        zoo.deleteAnimal("Leo");
        int indexAfter = zoo.findAnimal("Leo");

        assertEquals(-1, indexAfter);
        assertEquals(1, zoo.getCounter()); // One animal should remain
    }

    @Test
    void testDeleteAnimalNotFound() {
        int countBefore = zoo.getCounter();
        zoo.deleteAnimal("NonExistent");
        assertEquals(countBefore, zoo.getCounter());
    }

    @Test
    void testFindAnimal() {
        assertEquals(0, zoo.findAnimal("Leo"));
        assertEquals(1, zoo.findAnimal("Ella"));
        assertEquals(-1, zoo.findAnimal("Unknown"));
    }

    @Test
    void testGetAnimal() {
        Animal a = zoo.getAnimal(0);
        assertNotNull(a);
        assertEquals("Leo", a.getName());

        assertNull(zoo.getAnimal(-1));
        assertNull(zoo.getAnimal(100));
    }

    @Test
    void testMoveAnimal() {
        Logistics logistics = new Logistics(
                new Item("Truck", "V001") {{ setPrice(1000); }},
                new Item("Fuel", "F001") {{ setPrice(500); }},
                new String[]{"Sam", "Alex"}
        );

        zoo.moveAnimal("Leo", otherZoo, logistics);

        assertEquals(-1, zoo.findAnimal("Leo"));
        assertNotEquals(-1, otherZoo.findAnimal("Leo"));
        assertEquals(1, zoo.getCounter());
        assertEquals(1, otherZoo.getCounter());
    }

    @Test
    void testMoveAnimalNotFound() {
        Logistics logistics = new Logistics(
                new Item("Truck", "V001") {{ setPrice(1000); }},
                new Item("Fuel", "F001") {{ setPrice(500); }},
                new String[]{"Sam"}
        );

        int originalCount = zoo.getCounter();
        otherZoo.moveAnimal("Ghost", zoo, logistics);  // Should not find and move anything

        assertEquals(originalCount, zoo.getCounter());
        assertEquals(0, otherZoo.getCounter());
    }
}
