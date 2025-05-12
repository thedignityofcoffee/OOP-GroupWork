package bouncebox;

import bounceboxframework.Shape;
import java.awt.Graphics2D;
import java.awt.Color;

/**
 * 
 * * @author Bertrand
 */
public class Rectangle extends Shape {
    
    private int width;
    private int height;
    private double contactRadius;
    
    public Rectangle(int x, int y, int width, int height) {
        super(x, y);
        this.width = width;
        this.height = height;
        // calculated as the distance from the center to a corner
        this.contactRadius = Math.sqrt((width * width + height * height) / 4.0);
    }
    
    // Overloaded constructor to set velocity and color
    public Rectangle(int x, int y, int width, int height, double vx, double vy, int red, int green, int blue) {
        this(x, y, width, height);
        setVelocity(vx, vy);
        setColor(new Color(red, green, blue));
    }
    
    public int getWidth() {
        return width;
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
       return width * height;
   }
    
   @Override
   public void draw(Graphics2D g) {
       g.setColor(getColor());
       int left = (int)(getX() - width / 2);
       int top = (int)(getY() - height / 2);
       g.fillRect(left, top, width, height);
   }
}