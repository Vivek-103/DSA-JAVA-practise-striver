// Linear Search
import java.util.*;
class q8 {
    public static int Search(int arr[],int n, int num){
        int i;
        for(i=0;i<n;i++){
            if(arr[i]== num)
            return i;
        }
        return -1;
    }
    public static void main(String[] args) {
        int arr[] = {1, 2, 3, 4, 5,};
        int num = 4;
        int result = Search(arr, arr.length, num);
        System.out.println(result+" ");
    }
}
