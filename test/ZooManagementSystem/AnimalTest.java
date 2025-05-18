package ZooManagementSystem;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AnimalTest {

    private Animal animal;

    @BeforeEach
    void setUp() {
        animal = new Animal("Simba", "Lion", 5);
    }

    @Test
    void testConstructorAndGetters() {
        assertEquals("Simba", animal.getName());
        assertEquals("Lion", animal.getSpecies());
        assertEquals(5, animal.getAge());
    }

    @Test
    void testDisplayDoesNotThrow() {
        assertDoesNotThrow(() -> animal.display());
    }
}
