package slicing.com.secondObject;

import java.util.Date;

public class Test {
	public static void main(String[]args){
		/*Employee employee = new Employee();
		employee.setName("JAVA");
		employee.setSalary(100);
		employee.setBrithday(new Date());
		Manager manager = new Manager();
		manager.setName("明日科技");
		manager.setSalary(3000);
		manager.setBrithday(new Date());
		manager.setBonus(2000);
		System.out.println("员工姓名：" + employee.getName());
		System.out.println("员工工资：" + employee.getSalary());
		System.out.println("员工生日：" + employee.getBrithday());
		System.out.println("经理姓名：" + manager.getName());
		System.out.println("经理工资：" + manager.getSalary());
		System.out.println("经理生日：" + manager.getBrithday());
		System.out.println("经理奖金：" + manager.getBonus());*/
		Employee employee = new Employee();
		System.out.println(employee.getInfo());
		Manager manager = new Manager();
		System.out.println(manager.getInfo());
	}
}
