package slicing.com.secondObject;


public class Customer {
	public static void main(String[]args){
		System.out.println("顾客购买BMW");
		Car bmw = CarFactory.getCar("BMW");
		System.out.println("提取汽车：" + bmw.getInfo());
		System.out.println("顾客要买Benz");
		Car benz = CarFactory.getCar("Benz");
		System.out.println("提取汽车：" + benz.getInfo());
	}
}
