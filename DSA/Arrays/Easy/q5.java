// Rotate the Arrays by 1 steps 
class q5{

    // brute force method
    static void solve(int arr[], int n){
        int temp[]=new int[n];
        for(int i =1;i<n;i++){
            temp[i-1]=arr[i];
        }
        temp[n-1]=arr[0];
        for(int i =0;i<n;i++){
            System.out.println(temp[i]+"");
        }
    }
    
    // optimal solution
    static void sol2(int arr[],int n){
        int temp =arr[0];
        for (int i =0;i<n-1;i++){
            arr[i]=arr[i+1];
        }
        arr[n-1] = temp;
        for(int i=0;i<n;i++){
            System.out.println(arr[i]+ "");
        }
    }
    public static void main(String[] args) {
        int n = 5;
        int arr[] ={1,2,3,4,5};
        solve(arr,n);// by brute force
        sol2(arr,n);// by optimal method
    }

}