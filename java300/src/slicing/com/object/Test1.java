package slicing.com.object;

public class Test1 {
	public static void main(String[]args){
		Emperor emperor1 =  Emperor.getInstance();
		emperor1.getName();
		Emperor emperor2 = Emperor.getInstance();
		emperor2.getName();

	}
}
