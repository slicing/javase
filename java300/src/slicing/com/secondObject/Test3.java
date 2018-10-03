package slicing.com.secondObject;

public class Test3 {
	public static void main(String[]args){
		System.out.println("自定义普通汽车");
		Car1 car1 = new Car1();
		car1.setName("Adui");
		car1.setSpeed(60);
		System.out.println(car1);
		System.out.println("自定义GPS汽车");
		GPSCar gpsCar = new GPSCar();
		gpsCar.setName("Adui");
		gpsCar.setSpeed(60);
		System.out.println(gpsCar);

	}
}
