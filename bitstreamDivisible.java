
// https://www.geeksforgeeks.org/check-divisibility-binary-stream/, apple
public class bitstreamDivisible {
    static public boolean divisible(int a, int b) {
        // a>=b
        int re = a;
        while(re > 0) {
            int t = b;
            while( t<<1 <= re) {
                t = t<<1;
            }

            re -= t;
        }
        return re == 0;
    } 

    public static void main(String args[])  {
        System.out.println(divisible(4,2) == true);
        System.out.println(divisible(49,7) == true);
        System.out.println(divisible(49,2) == false);


    }
}
