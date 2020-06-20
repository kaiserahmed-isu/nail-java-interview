package leetcode;

import java.util.*;

/**
 * 937. Reorder Data in Log Files
 * Easy
 *
 * You have an array of logs.  Each log is a space delimited string of words.
 *
 * For each log, the first word in each log is an alphanumeric identifier.  Then, either:
 *
 *     Each word after the identifier will consist only of lowercase letters, or;
 *     Each word after the identifier will consist only of digits.
 *
 * We will call these two varieties of logs letter-logs and digit-logs.  It is guaranteed that each log has at least one word after its identifier.
 *
 * Reorder the logs so that all of the letter-logs come before any digit-log.  The letter-logs are ordered lexicographically ignoring identifier, with the identifier used in case of ties.  The digit-logs should be put in their original order.
 *
 * Return the final order of the logs.
 *
 * Example 1:
 *
 * Input: logs = ["dig1 8 1 5 1","let1 art can","dig2 3 6","let2 own kit dig","let3 art zero"]
 * Output: ["let1 art can","let3 art zero","let2 own kit dig","dig1 8 1 5 1","dig2 3 6"]
 *
 *
 *
 * Constraints:
 *
 *     0 <= logs.length <= 100
 *     3 <= logs[i].length <= 100
 *     logs[i] is guaranteed to have an identifier, and a word after the identifier.
 */
public class L937_ReorderDataInLogFiles {

    // Time: O(nlogn) where n=number of letterlogs | space: O (n + m), m = number of digitlogs

    public String[] reorderLogFiles(String[] logs) {
        
        if(logs.length == 0) return logs;
        
        List<String> letterLogs = new ArrayList<>();
        List<String> digitLogs = new ArrayList<>();
        
        
        separateLettersDigits(logs, letterLogs, digitLogs);
        sortLetterLogs(letterLogs);
        return generateOutput(letterLogs, digitLogs);
    }




    private void separateLettersDigits(String[] logs, List<String> letterLogs, List<String> digitLogs) {

        for(String log : logs) {// O(n)

            //check if it's a Digit log by looking at the last char
            if(Character.isDigit(log.charAt(log.length()-1))){
                digitLogs.add(log); // O(1)
            } else {
                letterLogs.add(log);
            }
        }
    }

    private void sortLetterLogs(List<String> letterLogs) {
        Collections.sort(letterLogs, new Comparator<String>() {  //O (n log(n) ), n= number of letterlogs
            public int compare(String o1, String o2) {
                //Ignore the identifier first part of log seperated by space
                String s1 = o1.substring(o1.indexOf(" ") + 1);
                String s2 = o2.substring(o2.indexOf(" ") + 1);

                return s1.equals(s2) ? o1.compareTo(o2) : s1.compareTo(s2);
            }
        });
    }

    private String[] generateOutput(List<String> letterLogs, List<String> digitLogs) {

        String[] output = new String[letterLogs.size() + digitLogs.size()];
        for(int i = 0; i < letterLogs.size(); i++) {
            output[i] = letterLogs.get(i);
        }
        for(int i = letterLogs.size(); i < output.length; i++) {
            output[i] = digitLogs.get(i-letterLogs.size());
        }
        return output;
    }


}
