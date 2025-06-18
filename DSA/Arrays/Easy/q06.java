// shift array by n places
class q6  {
    public static void Rotateright(int[] arr,int n, int k){
        if(n==0)
        return;
        k = k%n;
        if(k>n)
        return;
        int[] temp = new int[k];
        for (int i= n-k;i<n;i++){
            temp[i-n+k]=arr[i];
        }
        for(int i = n-k-1;i>=0;i--){
            arr[i+k]=arr[i];
        }
        for(int i =0;i<k;i++){
            arr[i]=temp[i];
        }
    }

    public static void Rotateleft(int[] arr,int n,int k){
         if (n == 0)
      return;
    k = k % n;
    if (k > n)
      return;
    int[] temp = new int[k];
    for (int i = 0; i < k; i++) {
      temp[i] = arr[i];
    }
    for (int i = 0; i < n - k; i++) {
      arr[i] = arr[i + k];
    }
    for (int i = n - k; i < n; i++) {
      arr[i] = temp[i - n + k];
    }
    }
    public static void main(String[] args) {
        int n =7;
        int[] arr = {1,2,3,4,5,6,7};
        int k = 3;
        Rotateright(arr, n, k);
        Rotateleft(arr,n,k);
        for(int i =0;i<n;i++){
            System.out.println(arr[i]+" ");
        }
    }   

// now optimal solution
public static void Reverse(int[] arr, int start,int end){
    while(start<=end){
        int temp = arr[start];
        arr[start]=arr[end];
        arr[end]=temp;
        start++;
        end--;
    }
}
public static void RotateleftOptimal(int[]arr, int n , int k){
    Reverse(arr,0,k-1);
    Reverse(arr,k,n-1);
    Reverse(arr,0,n-1);
}

}
