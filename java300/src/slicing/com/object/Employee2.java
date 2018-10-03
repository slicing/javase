package slicing.com.object;

import java.io.Serializable;

public class Employee2 implements Cloneable,Serializable {
	private static final long serialVersionUID = 3049633059823371192L;
	private String name;
	private int age;

	public Employee2(String name, int age) {
		this.name = name;
		this.age = age;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("姓名" + name  +",");
		sb.append("年龄" + age +",");
		return sb.toString();
	}
	protected Employee2 clone(){
		Employee2 employee = null;
		try{
			employee = (Employee2) super.clone();
		}catch (CloneNotSupportedException e){
			e.printStackTrace();
		}
		return employee;
	}
}
