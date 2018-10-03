package slicing.com.secondObject;

public class MaxMin {
	public static class Result{
		private double max;
		private double min;

		public double getMax() {
			return max;
		}

		public double getMin() {
			return min;
		}
		public Result(double max, double min) {

			this.max = max;
			this.min = min;
		}
	}
	public static Result getResult(double[]arry){
		double max = Double.MIN_VALUE;
		double min = Double.MAX_VALUE;
		for(double i :arry){
			if(i>max){
				max = i;
			}
			if(i < min){
				min = i;
			}
		}
		return new Result(max,min);
	}
}
