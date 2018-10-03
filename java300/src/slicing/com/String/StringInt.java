package slicing.com.String;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

public class StringInt {
	public static void main(String[]args){
		Scanner scn = new Scanner(System.in);
		Double d1 = scn.nextDouble();
		Double d2 = scn.nextDouble();
		if(d1.compareTo(d2) == 0){
			System.out.println("相等");
		}else{
			System.out.println("不等");
		}
		/*String num = scn.nextLine();
		int i = Character.codePointAt(num,0);
		System.out.println(i);
		int a = scn.nextInt();
		char []aa = Character.toChars(a);
		System.out.println(aa);*/
		//char []arr = num.toCharArray();
		/*int number = Integer.parseInt(num);
		System.out.println(Integer.toBinaryString(number));
		System.out.println(Integer.toHexString(number));
		System.out.println(Integer.toOctalString(number));
		System.out.println(number * number);*/

		/*int count = 0;
		for(int i = 0;i<arr.length;i++){
			boolean matches;
			if (Pattern.matches("^[\u4E00-\u9FA5]{0,}$", arr[i])) matches = true;
			else matches = false;
		}*/
	}

}
