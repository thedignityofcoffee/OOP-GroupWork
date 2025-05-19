package bouncebox.bouncebox;

import bouncebox.BounceBoxDashboard;
import org.junit.jupiter.api.*;
import java.io.*;
import java.lang.reflect.*;

import static org.junit.jupiter.api.Assertions.*;

public class BounceBoxDashboardTest {

    private BounceBoxDashboard dashboard;

    @BeforeEach
    void setUp() {
        dashboard = new BounceBoxDashboard();
    }

    @Test
    void testParseShapeFileCorrectlyUpdatesCountsAndArea() throws Exception {
        // Create a temp shape file
        File tempFile = File.createTempFile("shapes", ".txt");
        try (PrintWriter writer = new PrintWriter(tempFile)) {
            writer.println("Circle 10 20 5");
            writer.println("Square 30 40 4");
            writer.println("Triangle 15 25 6 8");
            writer.println("Rectangle 50 60 10 5");
        }

        // Use reflection to invoke parseShapeFile(File file)
        Method parseMethod = BounceBoxDashboard.class.getDeclaredMethod("parseShapeFile", File.class);
        parseMethod.setAccessible(true);
        parseMethod.invoke(dashboard, tempFile);

        // Access private fields using reflection
        int circleCount = getPrivateIntField(dashboard, "circleCount");
        int squareCount = getPrivateIntField(dashboard, "squareCount");
        int triangleCount = getPrivateIntField(dashboard, "triangleCount");
        int rectangleCount = getPrivateIntField(dashboard, "rectangleCount");
        double totalArea = getPrivateDoubleField(dashboard, "totalArea");

        assertEquals(1, circleCount);
        assertEquals(1, squareCount);
        assertEquals(1, triangleCount);
        assertEquals(1, rectangleCount);

        double expectedArea = Math.PI * 25 + 16 + 0.5 * 6 * 8 + 50;
        assertEquals(expectedArea, totalArea, 0.01);
    }

    @Test
    void testParseShapeFileWithInvalidLinesSkipsThem() throws Exception {
        File tempFile = File.createTempFile("invalid_shapes", ".txt");
        try (PrintWriter writer = new PrintWriter(tempFile)) {
            writer.println("Circle 10 20"); // missing radius
            writer.println("Square x y z"); // invalid ints
            writer.println("Triangle 15 25 6"); // missing height
            writer.println("Rectangle"); // incomplete
        }

        Method parseMethod = BounceBoxDashboard.class.getDeclaredMethod("parseShapeFile", File.class);
        parseMethod.setAccessible(true);
        parseMethod.invoke(dashboard, tempFile);

        assertEquals(0, getPrivateIntField(dashboard, "circleCount"));
        assertEquals(0, getPrivateIntField(dashboard, "squareCount"));
        assertEquals(0, getPrivateIntField(dashboard, "triangleCount"));
        assertEquals(0, getPrivateIntField(dashboard, "rectangleCount"));
        assertEquals(0.0, getPrivateDoubleField(dashboard, "totalArea"), 0.001);
    }

    // Utility methods for accessing private fields
    private int getPrivateIntField(Object obj, String fieldName) throws Exception {
        Field field = BounceBoxDashboard.class.getDeclaredField(fieldName);
        field.setAccessible(true);
        return field.getInt(obj);
    }

    private double getPrivateDoubleField(Object obj, String fieldName) throws Exception {
        Field field = BounceBoxDashboard.class.getDeclaredField(fieldName);
        field.setAccessible(true);
        return field.getDouble(obj);
    }
}
