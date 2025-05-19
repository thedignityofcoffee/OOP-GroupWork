package RestaurantManagementSystem;

import org.junit.jupiter.api.Test;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.*;

class RestaurantManagementSystemTest {

    @Test
    void testMainWindowStartup() {
        SwingUtilities.invokeLater(() -> {
            try {
                RestaurantManagementSystem system = new RestaurantManagementSystem();
                system.setVisible(true);

                assertTrue(system.isVisible());

                system.dispose();
            } catch (Exception e) {
                fail("Failed to start apps: " + e.getMessage());
            }
        });

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
