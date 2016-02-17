//Matt McColeman 100525235
//Assignment 2 principals of computer science
//setup main function with a random integer array initially
//there is a float and string array commented out for testing
//switching between iSort, oSort and iSortTimed can be done by uncommenting 
//in the if statements choosing between bubble, quick and merge sort
import java.util.*;

class sortNumbers{
	public static void main(String[] args){
		Random rn = new Random();
		int size = Integer.parseInt(args[0]);
		Object[] array = new Object[size];
		
		for(int i = 0; i < size; i++)
		{
			array[i] = rn.nextInt(100) + 1;
			if(size <= 10) System.out.println(array[i]);
		}

		/*
		for(int i=0; i < size; i++){
			array[i] = rn.nextFloat();
			if(size <= 10) System.out.println(array[i]);
		}
		*/
		/*
		array[0] = "aaaaa";
		array[1] = "aaaa";
		array[2] = "aaa";
		array[3] = "aa";
		array[4] = "a";
		array[5] = "bbbbb";
		array[6] = "bbbb";
		array[7] = "bbb";
		array[8] = "bb";
		array[9] = "b";
		*/
		boolean order;
		if(args.length == 3){
			if(args[2].equals("descend")){
				order = false;
			} else {
				order = true;
			}
		} else {
			order = true;
		}

		if(args[1].equals("bubble")){
			BubbleSort a = new BubbleSort();
			Comparator compare = new IntegerComparator(order);
			//Comparator compare = new FloatComparator(order);
			//Comparator compare = new StringComparator(order);
			a.setComparator(compare);
			//a.iSort(array);
			//a.oSort(array);
			a.iSortTimed(array);
			System.out.println("----");
			if(size <= 10) for(int j = 0; j < size; j++) System.out.println(array[j]);
		} else if(args[1].equals("quick")){
			int low = 0;
			int high = array.length -1;

			QuickSort b = new QuickSort();
			Comparator compare = new IntegerComparator(order);
			//Comparator compare = new FloatComparator(order);
			//Comparator compare = new StringComparator(order);
			b.setComparator(compare);
			//b.iSort(array, low, high);
			//b.oSort(array);
			b.iSortTimed(array, low, high);
			System.out.println("----");
			if(size <= 10) for(int k = 0; k < size; k++) System.out.println(array[k]);
		} else if(args[1].equals("merge")){
			int low = 0;
			int high = array.length -1;

			MergeSort c = new MergeSort();
			Comparator compare = new IntegerComparator(order);
			//Comparator compare = new FloatComparator(order);
			//Comparator compare = new StringComparator(order);
			c.setComparator(compare);
			//c.iSort(array, low, high);
			//c.oSort(array);
			c.iSortTimed(array, low, high);
			System.out.println("----");
			if(size <= 10) for(int q=0; q < size; q++) System.out.println(array[q]);
		} else {
			System.out.println("you messed up yo");
		}
	}
}

class BubbleSort<T> extends ArraySort<T> {

	public void iSort(T[] inArray, int low, int high){
		System.out.println("why you gotta do that...");
	}

	public void iSort(T[] inArray) {
		for(int x=1; x<inArray.length; x++){
			for(int i=1; i<inArray.length; i++){
				T temp;
				if(compare.compare(inArray[i-1], inArray[i]) == 1){
					temp = inArray[i-1];
					inArray[i-1] = inArray[i];
					inArray[i] = temp;
				}
			}
		}
	}

	public T[] oSort(T[] inArray){
		int size = inArray.length;
		T[] temp = (T[]) new Object[size];
		for(int i = 0; i < size; i++){
			temp[i] = inArray[i];
		}
		iSort(temp);
		for(int i=0;i<size;i++){
			//System.out.println(temp[i]);
		}
		return temp;
	}	
}

class QuickSort<T> extends ArraySort<T> {
	public void iSort(T[] inArray){
		System.out.println("run the one with the other parameters");
	}

	public void iSort(T[] inArray, int low, int high){
		// choose the pivot
		int middle = low + (high - low) / 2;
		T pivot = inArray[middle];
 
		//sets left less than pivot and right greater than pivot
		int i = low, j = high;
		while (i <= j) {
			while (compare.compare(inArray[i], pivot) == -1) {
				i++;
			}
 
			while (compare.compare(inArray[j], pivot) == 1) {
				j--;
			}
 
			if (i <= j) {
				T temp = inArray[i];
				inArray[i] = inArray[j];
				inArray[j] = temp;
				i++;
				j--;
			}
		}
 
		//sorts recursively two sub arrays
		if (low < j)
			iSort(inArray, low, j);
 
		if (high > i)
			iSort(inArray, i, high);
	}

	public T[] oSort(T[] inArray){
		int low = 0;
		int high = inArray.length -1;

		int size = inArray.length;
		T[] temp = (T[]) new Object[size];
		for(int i = 0; i < size; i++){
			temp[i] = inArray[i];
		}
		iSort(temp, low, high);
		for(int i=0;i<size;i++){
			//System.out.println(temp[i]);
		}
		return temp;
	}	
}


class MergeSort<T> extends ArraySort<T> {

		public void iSort(T[] inArray){
			System.out.println("run the one with the other parameters");
		}

		public void iSort(T[] inArray, int low, int high){
			int size = inArray.length;
			if(low<high){
				int middle = (low+high)/2;
				iSort(inArray, low, middle);
				iSort(inArray, middle+1, high);
				merge(inArray, low, middle, high);
			}
		}

		public void merge(T[] inArray, int low, int middle, int high){
			int size = inArray.length;
			T[] temp = (T[]) new Object[size];
			for(int i = low; i <= high; i++){
				temp[i] = inArray[i];
			}
			int i = low;
			int j = middle + 1;
			int k = low;

			while(i<=middle && j<=high){
				if (compare.compare(temp[i], temp[j]) == -1){
					inArray[k] = temp[i];
					i++;
				} else if(compare.compare(temp[i], temp[j]) == 0){
					inArray[k] = temp[i];
					i++;
				} else {
					inArray[k] = temp[j];
					j++;
				}
				k++;
			}
		}	

		public T[] oSort(T[] inArray){
			int low = 0;
			int high = inArray.length -1;

			int size = inArray.length;
			T[] temp = (T[]) new Object[size];
			for(int i = 0; i < size; i++){
				temp[i] = inArray[i];
			}
			iSort(temp, low, high);
			for(int i=0;i<size;i++){
				//System.out.println(temp[i]);
			}
			return temp;
		}	
}	

abstract class ArraySort<T> {
	protected Comparator compare;
	abstract public void iSort(T[] inArray);
	abstract public void iSort(T[] inArray, int low, int high);
	abstract public T[] oSort(T[] inArray);
	public long iSortTimed(T[] inArray) { 
		long startTime = System.nanoTime();
		iSort(inArray);
		long endTime = System.nanoTime();
		System.out.println("Runtime: " + (endTime - startTime));
		return(endTime - startTime);
	}
	public long iSortTimed(T[] inArray, int low, int high) { 
		long startTime = System.nanoTime();
		iSort(inArray, low, high);
		long endTime = System.nanoTime();
		System.out.println("Runtime: " + (endTime - startTime));
		return(endTime - startTime);
	}
	public void setComparator(Comparator<T> comparator) {
		compare = comparator;
	}
	
}

interface Comparator<T> {
	public abstract int compare(T a, T b);
}

class IntegerComparator<T> implements  Comparator<T> {
	boolean ascend;
	public IntegerComparator(boolean ascend){
		this.ascend=ascend;
	}

	public int compare(T a, T b) {
		if(ascend){
			if((Integer)a < (Integer)b){
				return -1;
			} else if((Integer)a == (Integer)b){
				return 0;
			} else {
				return 1;
			}
		} else {
			if((Integer)a > (Integer)b){
				return -1;
			} else if((Integer)a == (Integer)b){
				return 0;
			} else {
				return 1;
			}
		}
	}
}

class StringComparator<T> implements Comparator<T> {
	boolean ascend;
	public StringComparator(boolean ascend){
		this.ascend=ascend;
	}

	public int compare(T a, T b){
		String first = (String)a;
		String second = (String)b;
		if(ascend){
			if(first.length() < second.length()){
				return -1;
			} else if(first.length() == second.length()){
				return 0;
			} else {
				return 1;
			}
		} else {
			if(first.length() > second.length()){
				return -1;
			} else if(first.length() == first.length()){
				return 0;
			} else {
				return 1;
			}
		}
	}
}


class FloatComparator<T> implements Comparator<T> {
	boolean ascend;
	public FloatComparator(boolean ascend){
		this.ascend=ascend;
	}

	public int compare(T a, T b) {
		if(ascend){
			if((Float)a < (Float)b){
				return -1;
			} else if((Float)a == (Float)b){
				return 0;
			} else {
				return 1;
			}
		} else {
			if((Float)a > (Float)b){
				return -1;
			} else if((Float)a == (Float)b){
				return 0;
			} else {
				return 1;
			}
		}
	}
}
