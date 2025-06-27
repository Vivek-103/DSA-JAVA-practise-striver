import java.util.*;
class BinarySearchRecursive {
    private static int binarysearchrecursive(int[] numbers,int numberToFind,int low , int high){
        if(high>=low && low<=numbers.length-1){
            int middlePosition = low+(high-low)/2;
            int middleNumber = numbers[middlePosition];

            if(numberToFind==middleNumber){
                return middlePosition;
            }
            if(numberToFind<middleNumber){
                return binarysearchrecursive(numbers, numberToFind, low, middlePosition-1);
            }else{
                return binarysearchrecursive(numbers, numberToFind, middlePosition+1, high);
            }
        }
        
        return -1;
    }
    
    public static void main(String[] args) {
        int[] ints = {1, 2, 4, 5, 7, 9, 11};
        System.out.println("number is found at(-1 if no.does not exist):" + binarysearchrecursive(ints, 7, 0, ints.length-1));
        
    }
    
}
