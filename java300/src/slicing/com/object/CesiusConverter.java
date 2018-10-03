package slicing.com.object;

import java.util.Scanner;

public class CesiusConverter {

	public static void main(String []args){
		Scanner scn = new Scanner(System.in);
		double fatherenheit;
		double celsius = scn.nextDouble();
		fatherenheit = getFatherenheit(celsius);
		System.out.println(fatherenheit);
	}
	static double getFatherenheit(double celsius){
		double fatherenheit = 1.8 *celsius +32;
		return  fatherenheit;
	}
}
