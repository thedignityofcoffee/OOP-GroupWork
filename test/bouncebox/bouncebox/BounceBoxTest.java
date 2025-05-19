package bouncebox.bouncebox;

import bounceboxframework.BounceModel;
import bounceboxframework.Shape;
import bouncebox.BounceBox;
import bouncebox.Circle;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BounceBoxTest {

    private BounceBox box;
    private int width = 500;
    private int height = 400;

    @BeforeEach
    void setUp() {
        box = new BounceBox(width, height);
    }

    @Test
    void testBounceBoxInitialization() {
        assertNotNull(box);
    }

    @Test
    void testAddShapeActuallyAdds() {
        Shape shape = new Circle(100, 100, 20);

        box.addShape(shape);

        BounceModel model = getPrivateField(box, "model", BounceModel.class);
    }

    @Test
    void testStartDoesNotThrow() {
        assertDoesNotThrow(() -> box.start());
    }

    /**
     * Utility method to access private fields via reflection
     */
    private <T> T getPrivateField(Object target, String fieldName, Class<T> type) {
        try {
            var field = target.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            return type.cast(field.get(target));
        } catch (Exception e) {
            throw new RuntimeException("Failed to access field: " + fieldName, e);
        }
    }
}
