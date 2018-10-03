package slicing.com.secondObject;

public class Test1 {
	public static void main(String[]args){
		Circle cle = new Circle(1);
		System.out.println("图形名称" + cle.getName());
		System.out.println("图形面积" + cle.getArea());
		Rectangle rectangle = new Rectangle(1,1);
		System.out.println("图形名称" + rectangle.getName());
		System.out.println("图形面积" + rectangle.getArea());
	}


}
