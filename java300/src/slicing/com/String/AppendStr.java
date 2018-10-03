package slicing.com.String;

public class AppendStr {
	public static void main(String[]args){
		String appendStr = "";
		long startTime = System.nanoTime();
		for(int i= 20000;i<50000;i++){
			appendStr+=(char)i;
		}
		long endTime = System.nanoTime();
		System.out.println((endTime-startTime)/1000000d);

		StringBuffer stringBuffer = new StringBuffer();
		startTime = System.nanoTime();
		for (int i=20000;i<50000;i++){
			stringBuffer.append((char)i);
		}
		endTime = System.nanoTime();
		System.out.println((endTime-startTime)/1000000);
	}
}
