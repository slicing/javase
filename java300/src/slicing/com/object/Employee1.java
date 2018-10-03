package slicing.com.object;

import java.io.Serializable;

public class Employee1 implements Serializable,Cloneable {
	private static final long serialVersionUID = 3049633059823371192L;
	private String name;
	private int age;
	private Address address;

	public Employee1(String name, int age, Address address) {
		this.name = name;
		this.age = age;
		this.address = address;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("姓名" + name  +",");
		sb.append("年龄" + age +",");
		sb.append("地址" + address);
		return sb.toString();
	}
	public Employee1 clone(){
		Employee1 employee = null;
		try{
			employee = (Employee1) super.clone();
			//employee.address = address.clone();
		}catch (CloneNotSupportedException e){
			e.printStackTrace();
		}
		return employee;
	}
}
