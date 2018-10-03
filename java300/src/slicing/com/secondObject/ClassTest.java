package slicing.com.secondObject;

import java.util.Date;

public class ClassTest {
	@SuppressWarnings("uncheckrd")
	public static void main(String[]args) throws ClassNotFoundException {
		Class c1 = new Date().getClass();
		System.out.println("one " + c1.getName());
		Class c2 = boolean.class;
		System.out.println("two " + c2.getName());
		/*Class c3 = Class.forName("java.long.com");
		System.out.println("three " + c3.getName());*/
		Class c4 = Double.TYPE;
		System.out.println("four " + c4.getName());

	}
}
