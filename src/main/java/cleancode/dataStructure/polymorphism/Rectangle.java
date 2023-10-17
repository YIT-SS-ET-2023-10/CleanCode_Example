package cleancode.dataStructure.polymorphism;

import java.awt.*;

public class Rectangle implements Shape{
    private Point topLeft;
    private double height;
    private double width;

    @Override
    public double area() {
        return height * width;
    }
}
