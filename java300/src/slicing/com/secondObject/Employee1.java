package slicing.com.secondObject;

public class Employee1 implements Comparable<Employee1> {
	private int id;
	private String name;
	private int age;

	public Employee1(int id, String name, int age) {
		this.id = id;
		this.name = name;
		this.age = age;
	}

	@Override
	public int compareTo(Employee1 o) {
		if(id > o.id){
			return 1;
		}else if(id<o.id){
			return -1;
		}
		return 0;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("员工的编号"  + id + ",");
		sb.append("员工的年龄" + age + ",");
		sb.append("员工的姓名" + name );
		return sb.toString();
	}

}
