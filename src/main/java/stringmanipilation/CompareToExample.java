package stringmanipilation;

/**
 * Created by Kaiser on 6/15/20.
 */
public class CompareToExample {
    public static void main(String[] args) {
        String s1 = "anacell";
        String s2 = "deltacellular";
        System.out.println("String 1: " + s1);
        System.out.println("String 2: " + s2);

        // Compare the two strings.
        int S = s1.compareTo(s2);
        System.out.println(S);

        // Show the results of the comparison.
        if (S < 0) { System.out.println("\"" + s1 + "\"" + " is lexicographically higher than " + "\"" + s2 + "\"");
        } else if (S == 0) {
            System.out.println("\"" + s1 + "\"" + " is lexicographically  equal to " + "\"" + s2 + "\"");
        } else if (S > 0) {
            System.out.println("\"" + s1 + "\"" + " is lexicographically less than " + "\"" + s2 + "\"");
        }
    }
}
