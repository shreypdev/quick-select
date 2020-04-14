/**
 * September 18, 2019
 * Author: Shrey Patel
 * 
 * Description: This program can be used to find the kth smallest number
 * 				from the given lsit of numbers.
 * 
 * Time Complexity: Best-case and Average-case complexity: O(n) & Bad-case complexity: O(n^2).
 */
import java.util.*;
import java.io.*;

public class Quickselect {
	
	private static int kthSmallest(int arr[], int left, int right, int k) {

        if (k > 0 && k <= right - left + 1) {
            // number of elements in array
            int n = right - left + 1;

            int i, median[] = new int[(n + 8) / 9];

            for (i = 0; i < median.length - 1; i++) {
                median[i] = getMedian(Arrays.copyOfRange(arr, 9 * i + left, 9 * i + left + 8), 9);
            }

            if (n % 9 == 0) {
                median[i] = getMedian(Arrays.copyOfRange(arr, 9 * i + left, 9 * i + left + 8), 9);
                i++;
            } else {
                median[i] = getMedian(Arrays.copyOfRange(arr, 9 * i + left, 9 * i + left + (n % 9)), n % 9);
                i++;
            }

            int medOfMed = i == 1 ? median[i - 1]
                    : kthSmallest(median, 0, i - 1, i / 2);

            int partition = partition(arr, left, right, medOfMed);

            if (partition - left == k - 1)
                return arr[partition];
            if (partition - left > k - 1)
                return kthSmallest(arr, left, partition - 1, k);
			
            return kthSmallest(arr, partition + 1, right, k - (partition + 1) + left);
        }

        return -1;
    }

    private static int getMedian(int arr[], int n) {
        Arrays.sort(arr);
        return arr[n / 2];
    }

    private static void swap(int[] arr, int index1, int index2) {
        if (arr[index1] == arr[index2])
            return;
        int temp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = temp;
    }

    private static int partition(int[] arr, int left, int right, int pivot) {

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == pivot) {
                swap(arr, i, right);
                break;
            }
		}
		
        int index = left - 1;
		int i = left;
		
        while (i < right) {
            if (arr[i] < pivot) {
                index++;
                swap(arr, i, index);
            }
            i++;
        }
		index++;
		
		swap(arr, index, right);
		
        return index;
    }


    public static int Quickselect(int[] A, int k) {
		//	if k is greater than the length of A, it returns -1;
		if (k > A.length) return -1;
		
        return kthSmallest(A, 0, A.length - 1, k);
    }
    
    public static void main(String[] args) {
        Scanner s;
        int[] array;
        int k;
        if(args.length > 0) {
			try{
				s = new Scanner(new File(args[0]));
				int n = s.nextInt();
				array = new int[n];
				for(int i = 0; i < n; i++){
					array[i] = s.nextInt();
				}
			} catch(java.io.FileNotFoundException e) {
				System.out.printf("Unable to open %s\n",args[0]);
				return;
			}
			System.out.printf("Reading input values from %s.\n", args[0]);
        }
		else {
			s = new Scanner(System.in);
			System.out.printf("Enter a list of non-negative integers. Enter a negative value to end the list.\n");
			int temp = s.nextInt();
			ArrayList<Integer> a = new ArrayList<Integer>();
			while(temp >= 0) {
				a.add(temp);
				temp=s.nextInt();
			}
			array = new int[a.size()];
			for(int i = 0; i < a.size(); i++) {
				array[i]=a.get(i);
			}
	    
	    	System.out.println("Enter k");
        }
		k = s.nextInt();
		s.close();;
        System.out.println("The " + k + "th smallest number is the list is "
			   + Quickselect(array,k));	
    }
}
