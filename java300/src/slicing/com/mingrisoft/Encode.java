package slicing.com.mingrisoft;

import java.util.Scanner;

public class Encode {
	public static void main(String[]args){
		Scanner scn = new Scanner(System.in);
		String passwd = scn.nextLine();
		char[] array = passwd.toCharArray();
		for(int i = 0;i<array.length;i++){
			array[i] = (char)(array[i]^2000);
		}
		System.out.println("加密后结果");
		System.err.println(new String(array));
	}
}
