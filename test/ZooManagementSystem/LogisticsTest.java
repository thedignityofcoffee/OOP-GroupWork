package ZooManagementSystem;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LogisticsTest {

    private Item vehicle;
    private Item fuel;
    private String[] caretakers;
    private Logistics logistics;

    @BeforeEach
    void setUp() {
        vehicle = new Item("Truck", "V001");
        vehicle.setPrice(1000);

        fuel = new Item("Diesel", "F001");
        fuel.setPrice(300);

        caretakers = new String[] {"Alice", "Bob"};

        logistics = new Logistics(vehicle, fuel, caretakers);
    }

    @Test
    void testDisplayDoesNotThrow() {
        assertDoesNotThrow(() -> logistics.display());
    }
}
