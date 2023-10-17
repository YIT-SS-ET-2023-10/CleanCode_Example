package cleancode.dataStructure.polymorphism;

import java.awt.*;

public class Circle implements Shape{
    private Point center;
    private double radius;
    public final double PI = 3.14159265389793;

    @Override
    public double area() {
        return PI * radius * radius;
    }
}
