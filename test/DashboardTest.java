import static org.junit.Assert.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.lang.reflect.Field;

import org.junit.Before;
import org.junit.Test;

public class DashboardTest {

    private Dashboard dashboard;
    private JFrame frame;

    @Before
    public void setUp() {
        // Create a new instance of Dashboard before each test
        dashboard = new Dashboard();

        // Retrieve the frame using reflection for testing
        try {
            Field frameField = Dashboard.class.getDeclaredField("frame");
            frameField.setAccessible(true);
            frame = (JFrame) frameField.get(dashboard);
        } catch (Exception e) {
            fail("Failed to access frame field: " + e.getMessage());
        }
    }

    @Test
    public void testDashboardInitialization() {
        // Verify the frame properties
        assertEquals("System Dashboard", frame.getTitle());
        assertEquals(JFrame.EXIT_ON_CLOSE, frame.getDefaultCloseOperation());
        assertNotNull(frame.getLayout());
        assertTrue(frame.isVisible());
    }

    @Test
    public void testButtonSetup() {
        // Check if all buttons are added to the frame
        Component[] components = frame.getContentPane().getComponents();
        int buttonCount = 0;

        for (Component component : components) {
            if (component instanceof JButton) {
                buttonCount++;
                JButton btn = (JButton) component;
                assertTrue(btn.getFont().getSize() > 12); // Check scaled font
            }
        }

        assertEquals(5, buttonCount); // There should be 5 buttons
    }

    @Test
    public void testButtonActionListeners() {
        // Retrieve buttons from the frame
        Component[] components = frame.getContentPane().getComponents();

        for (Component component : components) {
            if (component instanceof JButton) {
                JButton btn = (JButton) component;
                ActionListener[] listeners = btn.getActionListeners();

                // Ensure each button has at least one action listener
                assertTrue(btn.getText() + " should have an action listener", listeners.length > 0);
            }
        }
    }

    @Test
    public void testExceptionHandling() {
        // This test would require modifying the Dashboard class to expose
        // the class names or provide a way to inject mock classes.
        // For simplicity, this test is a placeholder.
        assertTrue(true); // TODO: Implement proper exception handling test
    }

    // Helper method to find a button by its text in a container
    private JButton findButtonByText(Container container, String text) {
        Component[] components = container.getComponents();

        for (Component component : components) {
            if (component instanceof JButton && text.equals(((JButton) component).getText())) {
                return (JButton) component;
            } else if (component instanceof Container) {
                JButton result = findButtonByText((Container) component, text);
                if (result != null) return result;
            }
        }

        return null;
    }
}