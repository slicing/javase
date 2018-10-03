package slicing.com.secondObject;

public abstract class Car {
	public abstract String getInfo();
}
class BMW extends Car{
	public String getInfo(){
		return "BMW";
	}
}
class Benz extends Car{
	public String getInfo(){
		return "Benz";
	}
}
class CarFactory{
	public static Car getCar(String name){
		if(name.equalsIgnoreCase("BMW")){
			return new BMW();
		}else if(name.equalsIgnoreCase("Benz")){
			return new Benz();
		}else{
			return null;
		}
	}
}
