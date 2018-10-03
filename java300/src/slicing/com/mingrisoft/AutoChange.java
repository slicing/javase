package slicing.com.mingrisoft;

import javax.print.DocFlavor;

public class AutoChange {
	public static void main(String[]args){
		byte b = 127;
		char c = 'W';
		short s = 23561;
		int i = 3333;
		long l = 4000000L;
		float f = 3.14159F;
		double d = 54.231;
		System.out.println("累加byte等于" + b);
		System.out.println("累加byte等于" + (b+c));
		System.out.println("累加byte等于" + (b+c+s));
		System.out.println("累加byte等于" + (b+c+s+i));
		System.out.println("累加byte等于" + (b+c+s+i+l));
		System.out.println("累加byte等于" + (b+c+s+i+l+f));
		System.out.println("累加byte等于" + (b+c+s+i+l+f+d));
		System.out.println("强制转换为int" + (int)l);




	}
}
