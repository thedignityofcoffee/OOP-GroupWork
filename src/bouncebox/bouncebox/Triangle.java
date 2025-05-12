package bouncebox;

import bounceboxframework.Shape;
import java.awt.Graphics2D;
import java.awt.Color;

/**
 * 
 * * @author Bertrand
 */
public class Triangle extends Shape {
    
    private int base;
    private int height;
    private double contactRadius;
    
    public Triangle(int x, int y, int base, int height) {
        super(x, y);
        this.base = base;
        this.height = height;
        // Calculate the collision radius as the circumradius of the triangle
        this.contactRadius = Math.max(height/2.0, base/2.0);
    }
    
    // Overloaded constructor to set velocity and color
    public Triangle(int x, int y, int base, int height, double vx, double vy, int red, int green, int blue) {
        this(x, y, base, height);
        setVelocity(vx, vy);
        setColor(new Color(red, green, blue));
    }
    
    public int getBase() {
        return base;
    }
    
    public int getHeight() {
        return height;
    }
    
   @Override
   public double getContactRadius() {
       return contactRadius;
   }
    
   @Override
   public double getMass() {
       return 0.5 * base * height; // 
   }
    
   @Override
   public void draw(Graphics2D g) {
       g.setColor(getColor());
       // Create an isosceles triangle
       int[] xPoints = new int[3];
       int[] yPoints = new int[3];
       // Triangle vertex calculation, Centered at (getX(), getY())
       xPoints[0] = (int)(getX() - base/2);   // Bottom left corner
       yPoints[0] = (int)(getY() + height/2);
       xPoints[1] = (int)(getX() + base/2);   // Bottom right corner
       yPoints[1] = (int)(getY() + height/2);
       xPoints[2] = (int)(getX());            // Top vertex
       yPoints[2] = (int)(getY() - height/2);
       g.fillPolygon(xPoints, yPoints, 3);
   }
}
