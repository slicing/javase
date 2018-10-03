package slicing.com.object;

public class OverloadingTest {
	public void info(){
		System.out.println("明日科技一岁了！");
	}
	public void info(int age){
		System.out.println("明日科技" +  age +"岁了！");
	}
	public static void main(String []args){
		OverloadingTest ot =new OverloadingTest();
		ot.info();
		for(int i = 1;i<5;i++){
			ot.info(i);
		}
	}
}
