package RestaurantManagementSystem;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class RestaurantBillingSystemTest {
    private RestaurantBillingSystem system;
    private Meal pizza;

    @Before
    public void setUp() {
        system = new RestaurantBilling() {};

        pizza = new Meal("Pizza");
        pizza.addIngredient(new Ingredient("Dough", 1.50));
        pizza.addIngredient(new Ingredient("Cheese", 1.25));
    }

    @Test
    public void testAddMeal() {
        system.addMeal(pizza);
        assertEquals(pizza, system.findMealByName("Pizza"));
    }

    @Test
    public void testRemoveMeal() {
        system.addMeal(pizza);
        assertTrue(system.removeMeal("Pizza"));
        assertFalse(system.removeMeal("Pizza"));
    }

    @Test
    public void testFindMealByName() {
        system.addMeal(pizza);
        assertEquals(pizza, system.findMealByName("Pizza"));
        assertNull(system.findMealByName("Burger"));
    }
}