package ZooManagementSystem;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ItemTest {

    private Item item;

    @BeforeEach
    void setUp() {
        item = new Item("Transport Truck", "VEH_001");
    }

    @Test
    void testConstructorInitialization() {
        assertEquals("Transport Truck", item.name);
        assertEquals("VEH_001", item.code);
        assertEquals(0.0, item.price);
    }

    @Test
    void testSetPrice() {
        item.setPrice(5000.75);
        assertEquals(5000.75, item.price);
    }

    @Test
    void testDisplayDoesNotThrow() {
        item.setPrice(2000);
        assertDoesNotThrow(() -> item.display());
    }
}
