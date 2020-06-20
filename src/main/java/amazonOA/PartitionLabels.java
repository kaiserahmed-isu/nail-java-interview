package amazonOA;

import java.util.ArrayList;
import java.util.List;

/**
 *A string S of lowercase English letters is given. We want to partition this string into as many parts as possible so that each letter appears in at most one part, and return a list of integers representing the size of these parts.
 *
 *
 *
 * Example 1:
 *
 * Input: S = "ababcbacadefegdehijhklij"
 * Output: [9,7,8]
 * Explanation:
 * The partition is "ababcbaca", "defegde", "hijhklij".
 * This is a partition so that each letter appears in at most one part.
 * A partition like "ababcbacadefegde", "hijhklij" is incorrect, because it splits S into less parts.
 *
 *
 *
 * Note:
 *
 *     S will have length in range [1, 500].
 *     S will consist of lowercase English letters ('a' to 'z') only.
 *
 *
 traverse the string record the last index of each char.
 using pointer to record end of the current sub string.


 O(n) time O(1) space
 */
public class PartitionLabels {

    public static void main(String[] args) {
        String S = "ababcbacadefegdehijhklij";

        System.out.println(partitionLabels(S));

    }

    public static List<Integer> partitionLabels(String S) {

        List<Integer> result = new ArrayList<>();

        if(S.length() == 0 || S == null) return null;

        //get last index of each char [a to z] 26 characters
        int[] lastIndexOfEachChar = new int[26];
        for (int i =0; i < S.length(); i++){
            lastIndexOfEachChar[S.charAt(i) - 'a'] = i;
        }

        int startPointOfPartition = 0;
        int endPointOfPartition = 0;

        for (int i=0; i< S.length(); i++){
            endPointOfPartition = Math.max(endPointOfPartition, lastIndexOfEachChar[S.charAt(i) - 'a']);

            if (i == endPointOfPartition){
                int lengthOfCurrentPartition = endPointOfPartition - startPointOfPartition + 1; // 1 as index starts from 0
                result.add(lengthOfCurrentPartition);

                System.out.println("Partition: " + S.substring(startPointOfPartition, endPointOfPartition));

                startPointOfPartition = endPointOfPartition + 1;

            }
        }

        return result;

    }
}
