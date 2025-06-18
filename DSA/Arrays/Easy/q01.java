import java.util.Scanner;
import java.util.Arrays;
// find largest element in an array
// brute force approach
class q1 { 
    public static void main(String[] args) {
        System.out.println("enter the size of array");
        Scanner sc = new Scanner(System.in);
        int size = sc.nextInt();
        int[] arr = new int[size];
        System.out.println("enter the elements in the array");
        for (int i = 0; i < size; i++) {
            arr[i] = sc.nextInt();
        }
        Arrays.sort(arr);
        System.out.println("largest element in the array is " + arr[size-1]);
    }   
    
}

// recursive approach
// maintaining max variable and putting the value when its needed
    int arr1[] =  {2,5,1,3,0};
    System.out.println("The Largest element in the array is: "+findLargestElement(ar
    r1));
 
    int arr2[] =  {8,10,5,7,9};
    System.out.println("The Largest element in the array is: "+findLargestElement(ar
    r2));
  }
  static int findLargestElement(int arr[]) {
    int max= arr[0];
    for (int i = 0; i < arr.length; i++) {
      if (arr[i] > max) {
        max= arr[i];
      }
    }
    return max;
  }


