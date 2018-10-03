package slicing.com.mingrisoft;

import java.util.Scanner;

public class ParifyCheck {
	public static void main(String[]args){
		Scanner scn = new Scanner(System.in);
		long A = scn.nextLong();
		String check = (A%2 ==0)?("这个数是偶数"):("这个数字是奇数");
		System.out.println(check);
	}
}
