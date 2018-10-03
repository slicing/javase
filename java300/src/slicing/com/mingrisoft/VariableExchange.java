package slicing.com.mingrisoft;

import java.util.Scanner;

public class VariableExchange {
	public static void main(String[]args){
		Scanner scn = new Scanner(System.in);
		long A = scn.nextLong();
		long B = scn.nextLong();
		System.out.println("A" + A + "\tB" + B);
		A = A ^ B;
		B = B ^ A;
		A = A ^ B;
		System.out.println("A" + A + "\tB" + B);
	}
}
