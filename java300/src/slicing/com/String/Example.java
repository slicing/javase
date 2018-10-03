package slicing.com.String;

import java.util.Date;
import java.util.Locale;

public class Example {
	public static void main(String[]args){
		Date today = new Date();
		String a = String.format(Locale.US,"%tb",today);
		System.out.println(a);
		String b = String.format(Locale.US,"%tB",today);
		System.out.println(b);
		String c = String.format("%ta",today);
		System.out.println(c);
		String d = String.format("%tA",today);
		System.out.println(d);
		String e = String.format("%ty",today);
		System.out.println(e);
		String f = String.format("%tY",today);
		System.out.println(f);
		String g = String.format("%tm",today);
		System.out.println(g);
		String h = String.format("%td",today);
		System.out.println(h);
		String i = String.format("%te",today);
		System.out.println(i);

	}

}
