import java.math.BigInteger;

public class fib {
    /**
     * The fib
     * @param n
     * @return int
     */
    public static int fib(int n) {
        if (n == 1) return 1;
        else if (n == 0) return 0;
        else return fib (n - 1) + fib (n - 2);

    }
     
    public static BigInteger fact(BigInteger n) {
        if (n.equals(BigInteger.ONE)) return BigInteger.ONE;
        else return n.multiply(fact(n.subtract(BigInteger.ONE)));

    }

    public static void main(String[] args) {
       // Scanner sc = new Scanner(System.in);
       

        for( int i = 1; i <= 46; i++) {
        //for ( int i = 45; i >= 1; i--) {
            System.out.print(i + ": ");
            System.out.println("Fib" + fib(i));
            System.out.println(fact(BigInteger.valueOf(i)));
        }
    }
     
}
