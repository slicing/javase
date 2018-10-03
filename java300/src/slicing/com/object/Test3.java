package slicing.com.object;

public class Test3 {
	public static void main(String[]args) {
		Employee employee1 = new Employee();
		employee1.setName("张小小");
		employee1.setAge(20);
		System.out.println(employee1);
		Employee employee2 = new Employee();
		employee2.setName("李小小");
		employee2.setAge(23);
		System.out.println("员工信息");
		System.out.println(employee1);
		System.out.println(employee2);
	}
}
