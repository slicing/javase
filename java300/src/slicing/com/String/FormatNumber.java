package slicing.com.String;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.Scanner;

public class FormatNumber {
	public static void main(String[]args){
		Scanner scn = new Scanner(System.in);
		double number = scn.nextDouble();
		NumberFormat format = NumberFormat.getCurrencyInstance(Locale.CANADA);
		System.out.println("Locale.CHINA:" + format.format(number));
		format = NumberFormat.getCurrencyInstance(Locale.US);
		System.out.println("Locale.US:" + format.format(number));
		format = NumberFormat.getCurrencyInstance(Locale.ENGLISH);
		System.out.println("Locale.ENGLISH:" + format.format(number));

	}
}
