// Class name kept short and meaningful
public class PowerCalc {

    // Recursive function to calculate x raised to power n (x^n)
    public double myPow(double x, int n) {

        // Base case: any number to the power 0 is 1
        if (n == 0) return 1;

        // If exponent is negative
        if (n < 0) {

            // Special case: when n = Integer.MIN_VALUE, negating it causes overflow
            // So we handle it by first adding 1, multiplying one extra 'x'
            return (n == Integer.MIN_VALUE)
                    ? 1 / (x * myPow(x, -(n + 1))) // safely handle overflow
                    : 1 / myPow(x, -n);            // normal case for negative n
        }

        // Recursive step: compute x^(n/2)
        double half = myPow(x, n / 2);

        // If n is even → x^n = (x^(n/2))²
        if (n % 2 == 0)
            return half * half;

        // If n is odd → x^n = x * (x^(n/2))²
        else
            return x * half * half;
    }

    // Main method to test different cases
    public static void main(String[] args) {
        PowerCalc sol = new PowerCalc();

        // Example test cases
        System.out.println(sol.myPow(2, 10));    // 2^10 = 1024.0
        System.out.println(sol.myPow(2, -2));    // 2^-2 = 1/(2^2) = 0.25
        System.out.println(sol.myPow(3, 5));     // 3^5 = 243.0
        System.out.println(sol.myPow(2.5, 3));   // 2.5^3 = 15.625
        System.out.println(sol.myPow(2, -2147483648)); // safely handles extreme case
    }
}
