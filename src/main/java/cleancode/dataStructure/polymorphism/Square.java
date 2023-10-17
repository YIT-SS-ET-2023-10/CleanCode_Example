package cleancode.dataStructure.polymorphism;

import java.awt.*;

public class Square implements Shape{
    private Point topLeft;
    private double side;

    @Override
    public double area() {
        return side*side;
    }
}
