package RestaurantManagementSystem;

import org.junit.Test;
import static org.junit.Assert.*;

public class IngredientTest {
    @Test
    public void testIngredientCreation() {
        Ingredient tomato = new Ingredient("Tomato", 0.75);
        assertEquals("Tomato", tomato.getName());
        assertEquals(0.75, tomato.getPrice(), 0.001);
    }

    @Test
    public void testToString() {
        Ingredient lettuce = new Ingredient("Lettuce", 0.50);
        String expected = "Lettuce              RMB0.50";
        assertEquals(expected, lettuce.toString());
    }
}