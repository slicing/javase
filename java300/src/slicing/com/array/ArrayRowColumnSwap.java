package slicing.com.array;

public class ArrayRowColumnSwap {
	public static void main(String[]args){
		int arr[][] = new int[][]{{1,2,3},{4,5,6},{7,8,9},{10,11,12}};
		printArray(arr);
		int arr2[][] = new int[arr[0].length][arr.length];
		for(int i = 0;i<arr[i].length;i++){
			for(int j = 0;j<arr.length;j++){
				arr2[i][j] = arr[j][i];
			}
		}
		printArray(arr2);
	}

	static void printArray(int[][] arr){
		for(int i = 0;i<arr.length;i++){
			for(int j = 0;j<arr[i].length;j++){
				System.out.print(arr[i][j]+"\t");
			}
			System.out.println();
		}
	}
}
