package slicing.com.secondObject;

public abstract class Shape {
	public String getName(){
		return this.getClass().getSimpleName();
	}
	public abstract double getArea();
}
class Circle extends Shape{
	private double radius;
	public Circle(double radius){
		this.radius = radius;
	}
	public double getArea(){
		return Math.PI * Math.pow(radius,2);
	}
}
class Rectangle extends Shape{
	private double length;
	private double width;

	public Rectangle(double length, double width) {
		this.length = length;
		this.width = width;
	}
	public double getArea(){
		return length * width;
	}
}
