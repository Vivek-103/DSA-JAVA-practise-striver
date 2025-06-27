import java.util.*;
class BinarySearchRegular{  
    private static int binaryseachregular(int[] numbers,int numberToFind){
        int low = 0;
        int high = numbers.length-1;

        while(low<=high){
            int middlePosition = (low + high)/2;
            int middleNumber = numbers[middlePosition];


            if(numberToFind==middleNumber){
                return middlePosition;
            }

            if(numberToFind<middleNumber){
                high = middlePosition-1;
            }else{
                low = middlePosition+1 ;
            }
        }
        return -1;
    } 
    public static void main(String[] args) {
        int [] ints = {1,2,4,5,7,9,11};
        System.out.println( "index at which the number is present is (if -1 then no. not present):\n"+binaryseachregular(ints, 2));
    }
}
