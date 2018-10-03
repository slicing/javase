package slicing.com.Mathee;

import java.math.BigDecimal;
import java.math.BigInteger;

public class RadianTest {
	public static void main(String[]args){
		System.out.println(Math.toRadians(30));
		System.out.println(Math.toDegrees(Math.PI/6));
		System.out.println(Math.toRadians(45));
		System.out.println(Math.toDegrees(Math.PI/4));
		System.out.println(Math.sin(Math.PI/6));
		System.out.println(Math.cos(Math.PI/6));
		System.out.println(Math.tan(Math.PI/6));

		System.out.println(Math.asin(0.5));
		System.out.println(Math.acos(0.866));
		System.out.println(Math.atan(0.5774));
		System.out.println();
		System.out.println(Math.sinh(30));
		System.out.println(Math.cosh(30));
		System.out.println(Math.tanh(30));
		System.out.println();
		System.out.println(Math.cbrt(8));
		System.out.println(Math.exp(8));
		System.out.println(Math.expm1(8));
		System.out.println(Math.log10(8));
		System.out.println(Math.log1p(8));
		System.out.println(Math.pow(2,3));
		System.out.println(Math.sqrt(8));
		System.out.println();
		BigInteger num1 = new BigInteger("12345");
		BigInteger num2 = new BigInteger("54321");
		BigInteger addition = num1.add(num2);
		BigInteger sub = num1.subtract(num2);
		BigInteger mul = num1.multiply(num2);
		BigInteger div = num1.divide(num2);
		System.out.println(num1);
		System.out.println(num2);
		System.out.println(addition);
		System.out.println(sub);
		System.out.println(mul);
		System.out.println(div);
		System.out.println();
		BigDecimal num11 = new BigDecimal("1.2345");
		BigDecimal num12 = new BigDecimal("5.4321");
		BigDecimal addition1 = num11.add(num12);
		BigDecimal sub1 = num11.subtract(num12);
		BigDecimal mul1 = num11.multiply(num12);
		System.out.println(num11);
		System.out.println(num12);
		System.out.println(addition1);
		System.out.println(sub1);
		System.out.println(mul1);

	}
}
