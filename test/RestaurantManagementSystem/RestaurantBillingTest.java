package RestaurantManagementSystem;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class RestaurantBillingTest {
    private RestaurantBilling billing;
    private Meal burger;
    private Meal salad;

    @Before
    public void setUp() {
        billing = new RestaurantBilling();

        burger = new Meal("Burger");
        burger.addIngredient(new Ingredient("Bun", 0.80));
        burger.addIngredient(new Ingredient("Beef Patty", 3.50));

        salad = new Meal("Salad");
        salad.addIngredient(new Ingredient("Lettuce", 0.50));
        salad.addIngredient(new Ingredient("Tomato", 0.75));

        billing.addMeal(burger);
        billing.addMeal(salad);
    }

    @Test
    public void testAddMealToOrder() {
        assertTrue(billing.addMealToOrder("Burger"));
        assertFalse(billing.addMealToOrder("Pizza"));
    }

    @Test
    public void testCalculateBill() {
        billing.addMealToOrder("Burger");
        billing.addMealToOrder("Salad");
        billing.addMealToOrder("Burger");

        double expected = burger.getPrice() * 2 + salad.getPrice();
        assertEquals(expected, billing.calculateBill(), 0.001);
    }

    @Test
    public void testClearOrder() {
        billing.addMealToOrder("Burger");
        billing.clearOrder();
        assertEquals(0, billing.calculateBill(), 0.001);
    }
}