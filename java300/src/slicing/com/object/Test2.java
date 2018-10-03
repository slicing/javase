package slicing.com.object;

import java.awt.*;

public class Test2 {
	public static void main(String[]args){
		Cat cat1 = new Cat("java",12,21,Color.BLACK);
		Cat cat2 = new Cat("c++",12,21,Color.BLUE);
		Cat cat3 = new Cat("java",12,21,Color.DARK_GRAY);
		System.out.println("猫咪一号" + cat1.hashCode());
		System.out.println("猫咪二号" + cat2.hashCode());
		System.out.println("猫咪三号" + cat3.hashCode());
		System.out.println("猫咪一二号是否相同" + cat1.equals(cat2));
		System.out.println("猫咪一三号是否相同" + cat1.equals(cat3));

	}
}
