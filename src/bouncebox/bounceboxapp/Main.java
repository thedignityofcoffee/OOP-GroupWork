/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

 package bounceboxapp;

 import bouncebox.BounceBox;
 import bouncebox.Circle;
 import bouncebox.Square;
 import bouncebox.Triangle;
 import bouncebox.Rectangle;
 import java.awt.Color;
 import java.io.FileInputStream;
 import java.io.FileNotFoundException;
 //import java.util.Random; // Never used this.
 import java.util.Scanner;
 
 /**
  *
  * @author ashongtical
  * @author Bertrand
  */
 public class Main {
 
    /**
    * @param args the command line arguments
    */
 
             
    public static void main(String[] args) throws FileNotFoundException {
        addShapes();
    }
     
    public static void addShapes() throws FileNotFoundException {
        BounceBox box = new BounceBox(700,500);
 
        FileInputStream fileIn = new FileInputStream("practical4.txt");
        Scanner scan = new Scanner(fileIn);
         
        int circleCount = 0;
        int squareCount = 0;
        int triangleCount = 0;
        int rectangleCount = 0;
        double totalArea = 0.0;
         
        while (scan.hasNextLine()) {
            String line = scan.nextLine().trim();
            if (line.isEmpty()) {
                continue;
            }
             
            Scanner lineScan = new Scanner(line);
            if (!lineScan.hasNext()) {
                continue;
            }
             
            String shapeType = lineScan.next();
             
            if (shapeType.equalsIgnoreCase("Circle")) {
                // parse circle part
                if (!lineScan.hasNextInt()) continue;
                int x = lineScan.nextInt();
                if (!lineScan.hasNextInt()) continue;
                int y = lineScan.nextInt();
                if (!lineScan.hasNextInt()) continue;
                int radius = lineScan.nextInt();
                Circle circle;
                if (lineScan.hasNextDouble() && lineScan.hasNextDouble()) {
                    double vx = lineScan.nextDouble();
                    double vy = lineScan.nextDouble();
                    if (lineScan.hasNextInt() && lineScan.hasNextInt() && lineScan.hasNextInt()) {
                        int red = lineScan.nextInt();
                        int green = lineScan.nextInt();
                        int blue = lineScan.nextInt();
                        circle = new Circle(x, y, radius);
                        circle.setVelocity(vx, vy);
                        circle.setColor(new Color(red, green, blue));
                    } else {
                        circle = new Circle(x, y, radius);
                        circle.setVelocity(vx, vy);
                    }
                } else {
                    circle = new Circle(x, y, radius);
                }
                box.addShape(circle);
                circleCount++;
                totalArea += Math.PI * radius * radius;
            } else if (shapeType.equalsIgnoreCase("Square")) {
                // parse square part
                if (!lineScan.hasNextInt()) continue;
                int x = lineScan.nextInt();
                if (!lineScan.hasNextInt()) continue;
                int y = lineScan.nextInt();
                if (!lineScan.hasNextInt()) continue;
                int side = lineScan.nextInt();
                Square square;
                if (lineScan.hasNextDouble() && lineScan.hasNextDouble()) {
                    double vx = lineScan.nextDouble();
                    double vy = lineScan.nextDouble();
                    if (lineScan.hasNextInt() && lineScan.hasNextInt() && lineScan.hasNextInt()) {
                        int red = lineScan.nextInt();
                        int green = lineScan.nextInt();
                        int blue = lineScan.nextInt();
                        square = new Square(x, y, side);
                        square.setVelocity(vx, vy);
                        square.setColor(new Color(red, green, blue));
                    } else {
                        square = new Square(x, y, side);
                        square.setVelocity(vx, vy);
                    }
                } else {
                    square = new Square(x, y, side);
                }
                box.addShape(square);
                squareCount++;
                totalArea += side * side;
            } else if (shapeType.equalsIgnoreCase("Triangle")) {
                // parse triangle part
                if (!lineScan.hasNextInt()) continue;
                int x = lineScan.nextInt();
                if (!lineScan.hasNextInt()) continue;
                int y = lineScan.nextInt();
                if (!lineScan.hasNextInt()) continue;
                int base = lineScan.nextInt();
                if (!lineScan.hasNextInt()) continue;
                int height = lineScan.nextInt();
                Triangle triangle;
                if (lineScan.hasNextDouble() && lineScan.hasNextDouble()) {
                    double vx = lineScan.nextDouble();
                    double vy = lineScan.nextDouble();
                    if (lineScan.hasNextInt() && lineScan.hasNextInt() && lineScan.hasNextInt()) {
                        int red = lineScan.nextInt();
                        int green = lineScan.nextInt();
                        int blue = lineScan.nextInt();
                        triangle = new Triangle(x, y, base, height, vx, vy, red, green, blue);
                    } else {
                        triangle = new Triangle(x, y, base, height);
                        triangle.setVelocity(vx, vy);
                    }
                } else {
                    triangle = new Triangle(x, y, base, height);
                }
                box.addShape(triangle);
                triangleCount++;
                totalArea += 0.5 * base * height;
            } else if (shapeType.equalsIgnoreCase("Rectangle")) {
                // parse rectangle part
                if (!lineScan.hasNextInt()) continue;
                int x = lineScan.nextInt();
                if (!lineScan.hasNextInt()) continue;
                int y = lineScan.nextInt();
                if (!lineScan.hasNextInt()) continue;
                int width = lineScan.nextInt();
                if (!lineScan.hasNextInt()) continue;
                int height = lineScan.nextInt();
                Rectangle rectangle;
                if (lineScan.hasNextDouble() && lineScan.hasNextDouble()) {
                    double vx = lineScan.nextDouble();
                    double vy = lineScan.nextDouble();
                    if (lineScan.hasNextInt() && lineScan.hasNextInt() && lineScan.hasNextInt()) {
                        int red = lineScan.nextInt();
                        int green = lineScan.nextInt();
                        int blue = lineScan.nextInt();
                        rectangle = new Rectangle(x, y, width, height, vx, vy, red, green, blue);
                    } else {
                        rectangle = new Rectangle(x, y, width, height);
                        rectangle.setVelocity(vx, vy);
                    }
                } else {
                    rectangle = new Rectangle(x, y, width, height);
                }
                box.addShape(rectangle);
                rectangleCount++;
                totalArea += width * height;
            } else {
                // Print: shapeType is not a recognized shape
                System.err.println(shapeType + " is not a recognized shape");
            }
             
            lineScan.close();
        }
         
        scan.close();
         
        // Step 3: Count Shapes
        // Display the results
        System.out.printf("Circle: %d      Square: %d\n", circleCount, squareCount);
        System.out.printf("Triangle: %d    Rectangle: %d\n", triangleCount, rectangleCount);
        System.out.printf("Total Area is %.11f\n", totalArea);
         
        // Write the output to the file -> shapesArea.txt
        java.io.PrintWriter out = new java.io.PrintWriter("shapesArea.txt");
        out.printf("Total Area of All Shapes: %.11f\n", totalArea);
        out.close();
         
        box.start();
    }
}
 