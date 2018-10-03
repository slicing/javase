package slicing.com.secondObject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Test2 {
	public static void main(String[]args){
		List<Employee1> list = new ArrayList<Employee1>();
		list.add(new Employee1(3,"Java",21));
		list.add(new Employee1(2,"Java",22));
		list.add(new Employee1(1,"Java",23));
		System.out.println("排序前");
		for(Employee1 employee1:list){
			System.out.println(employee1);
		}
		System.out.println("排序后");
		Collections.sort(list);
		for(Employee1 employee1:list){
			System.out.println(employee1);
		}

	}
}
