package slicing.com.mingrisoft;

import java.util.Scanner;

public class MulExample {
	public static void main(String[]args){
		Scanner scn = new Scanner(System.in);
		System.out.print("请输入数字：");
		long num = scn.nextLong();
		System.out.println("你输入的数字是：" + num);
		System.out.println("该数字乘以2是：" + (num<<1));
		System.out.println("该数字乘以4是：" + (num<<2));
		System.out.println("该数字乘以8是：" + (num<<3));
		System.out.println("该数字乘以16是：" + (num<<4));

	}
}
