package playground;


/**
 * Created by Kaiser on 6/18/20.
 */
public class GCD {

    // the complexity of Euclid's algorithm is O(Log min(n1, n2))
    public int generalizedGCD(int num, int[] arr) {

        int result = arr[0];

        for (int i = 1; i < num; i++){
            result = gcdByEuclidsAlgorithm(arr[i], result);

            if(result == 1)
            {
                return 1;
            }
        }

        return result;
    }
    // METHOD SIGNATURE ENDS
    int gcdByEuclidsAlgorithm(int n1, int n2) {
        if (n2 == 0) {
            return n1;
        }
        return gcdByEuclidsAlgorithm(n2, n1 % n2);
    }

    public static void main(String[] args) {
        int n = 5;
        int[] arr= {2,4,6,8,10};

        GCD gcd = new GCD();
        System.out.println(gcd.generalizedGCD(n, arr));
    }
}
