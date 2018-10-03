package slicing.com.array;

import java.lang.reflect.Array;
import java.util.Arrays;

public class Sort {
	public static void main(String[]args){
		int arr[] = new int[]{1,6,23,5,4,79,16,19,20};
		printArray(arr);
		//翻转
		int len = arr.length;
		int temp;
		/*for(int i = 0;i<len/2;i++){
			temp = arr[i];
			arr[i] = arr[len-1-i];
			arr[len-1-i] = temp;
			System.out.println("第"+ i + "排序后：");
			printArray(arr);
		}*/
		//冒泡
		/*for(int i = 0;i<len;i++){
			for(int j = 0;j<len-1-i;j++){
				if(arr[j]>arr[j+1]){
					temp = arr[j];
					arr[j] = arr[j+1];
					arr[j+1] = temp;
				}
			}
			System.out.println("第"+ i + "排序后：");
			printArray(arr);
		}*/
		//使用sort
		//Arrays.sort(arr);
		fastArraySort(arr,0,arr.length-1);
		printArray(arr);
	}
	static void printArray(int[] arr){
		for(int i = 0;i<arr.length;i++){
			System.out.print(arr[i]+ "\t");
		}
		System.out.println();
	}
	static void insertArraySort(int []arr){
		int temp;
		int j;
		for(int i = 1;i<arr.length;i++){
			temp = arr[i];
			for( j = i-1;j>=0 && arr[j]>temp;j--){
				arr[j+1] = arr[j];
			}
			arr[j+1] = temp;
		}
	}
	static void checkArraySort(int[] arr){
		int index;
		for(int i =1;i<arr.length;i++){
			index = 0;
			for(int j = 1;j<=arr.length-i;j++){
				if(arr[j]>arr[index]){
					index = j;
				}
			}
			swap(arr,arr.length -i,index);
		}

	}
	static void fastArraySort(int[] arr,int left,int right){
		int start = left;
		int end = right;
		int mid ;
		if(left<right){
			mid = arr[(start+end)/2];
			while(start < end){
				while((start < right) && (arr[start] < mid)){
					start++;
				}
				while ((end>left) && arr[end] > mid){
					--end;
				}
				if(start <= end){
					swap(arr,start,end);
					++start;
					--end;
				}
			}
			if(left < end){
				fastArraySort(arr,left,end);
			}
			if(start < right){
				fastArraySort(arr,start,right);
			}

		}
	}
	static void swap(int[]arr,int i,int j){
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
		printArray(arr);
	}

}
