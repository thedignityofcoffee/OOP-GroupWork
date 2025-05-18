package RestaurantManagementSystem;

import org.junit.Test;
import static org.junit.Assert.*;

public class MealTest {
    @Test
    public void testMealCreation() {
        Meal burger = new Meal("Burger");
        assertEquals("Burger", burger.getName());
        assertTrue(burger.getIngredients().isEmpty());
    }

    @Test
    public void testAddIngredient() {
        Meal salad = new Meal("Salad");
        Ingredient lettuce = new Ingredient("Lettuce", 0.50);
        Ingredient tomato = new Ingredient("Tomato", 0.75);

        salad.addIngredient(lettuce);
        salad.addIngredient(tomato);

        assertEquals(2, salad.getIngredients().size());
        assertEquals(1.25, salad.getPrice(), 0.001);
    }

}