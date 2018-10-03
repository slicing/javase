package slicing.com.object;

import java.io.*;

public class Test4 {
	public static void main(String[]args){
		Address address = new Address("中国","吉林","长春");
		Employee1 employee1 = new Employee1("张小小",30,address);
		System.out.println("员工信息");
		System.out.println(employee1);
		System.out.println("序列化后");
		ObjectOutputStream out = null;
		ObjectInputStream in = null;
		Employee1 employee2 = null;
		try{
			out = new ObjectOutputStream(new FileOutputStream("employee.dat"));
			out.writeObject(employee1);
			in = new ObjectInputStream(new FileInputStream("employee.dat"));
			employee2 = (Employee1)in.readObject();


		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}finally {

		}
		if(employee2 != null){
			employee2.getAddress().setState("中国");
			employee2.getAddress().setProvince("四川");
			employee2.getAddress().setCity("成都");
			employee2.setName("李小小");
			employee2.setAge(22);
			System.out.println("克隆后");
			System.out.println(employee1);
			System.out.println(employee2);
		}

	}
}
