// FIND MISSING NUMBER IN AN ARRAY 
class q10 {
 //brute force approach
 public static int missingNumberBruteForce(int []a, int N) {

        // Outer loop that runs from 1 to N:
        for (int i = 1; i <= N; i++) {

            // flag variable to check
            //if an element exists
            int flag = 0;

            //Search the element using linear search:
            for (int j = 0; j < N - 1; j++) {
                if (a[j] == i) {

                    // i is present in the array:
                    flag = 1;
                    break;
                }
            }

            // check if the element is missing
            //i.e flag == 0:

            if (flag == 0) return i;
        }

        // The following line will never execute.
        // It is just to avoid warnings.
        return -1;
    }
    //OPTIMAL APPROACH
    public static int missingNumberOptimal(int []nums){
        int N = nums.length;
        int Sum = (N*(N+1))/2;
        int Sum2 =0;
        for (int i=0;i<N;i++){
            Sum2 += nums[i];
        }
        int MissingnNumber = Sum - Sum2;
        return MissingnNumber;   


    }
}

// XOR METHOD (optimal-est method)
public static int missingNumber(int []a, int N) {

        int xor1 = 0, xor2 = 0;

        for (int i = 0; i < N - 1; i++) {
            xor2 = xor2 ^ a[i]; // XOR of array elements
            xor1 = xor1 ^ (i + 1); //XOR up to [1...N-1]
        }
        xor1 = xor1 ^ N; //XOR up to [1...N]

        return (xor1 ^ xor2); // the missing number
    }


    
