package slicing.com.secondObject;

import com.sun.org.apache.regexp.internal.RE;

import java.awt.*;

public class Car1 {
	private String name;
	private double speed;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("车名：" + name + ",");
		sb.append("车速：" + speed + "千米/小时");
		return sb.toString();
	}
}
interface GPS {
	Point getLocation();
}
class GPSCar extends Car1 implements GPS{

	@Override
	public Point getLocation() {
		Point point = new Point();
		point.setLocation(super.getSpeed(),super.getSpeed());
		return point;
	}
	public String toString(){
		StringBuilder sb= new StringBuilder();
		sb.append(super.toString());
		sb.append(",坐标：（" + getLocation().x + "," + getLocation().y + "）");
		return sb.toString();
	}
}

