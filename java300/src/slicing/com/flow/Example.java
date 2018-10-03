package slicing.com.flow;
import java.math.BigDecimal;
public class Example {
	public static void main(String[]args){
		BigDecimal sum = new BigDecimal(0.0);
		BigDecimal factional = new BigDecimal(1.0);
		int i = 1;

		while(i<=20){
			sum = sum.add(factional);
			++i;
			factional = factional.multiply(new BigDecimal(1.0/i));

		}
		System.out.println(sum);
	}
}
