package slicing.com.secondObject;

public class Test55 {
	public static void main(String[]args){
		double[]arr = new double[5];
		for(int i = 0;i<arr.length;i++){
			arr[i] = 100 * Math.random();
		}

		System.out.println("原数组：");
		for(int i = 0;i<arr.length;i++){
			System.out.print(arr[i] + "\t");
		}
		System.out.println();
		System.out.println("最大值" + MaxMin.getResult(arr).getMax());
		System.out.println("最小值" + MaxMin.getResult(arr).getMin());
	}
}
