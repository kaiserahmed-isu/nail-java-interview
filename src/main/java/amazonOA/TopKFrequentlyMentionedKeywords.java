package amazonOA;

import java.util.*;

/**
 * Given a list of reviews, a list of keywords and an integer k. Find the most popular k keywords in order of most to least frequently mentioned.
 *
 * The comparison of strings is case-insensitive.
 * Multiple occurances of a keyword in a review should be considred as a single mention.
 * If keywords are mentioned an equal number of times in reviews, sort alphabetically.
 *
 * Example 1:
 *
 * Input:
 * k = 2
 * keywords = ["anacell", "cetracular", "betacellular"]
 * reviews = [
 *   "Anacell provides the best services in the city",
 *   "betacellular has awesome services",
 *   "Best services provided by anacell, everyone should use anacell",
 * ]
 *
 * Output:
 * ["anacell", "betacellular"]
 *
 * Explanation:
 * "anacell" is occuring in 2 different reviews and "betacellular" is only occuring in 1 review.
 *
 * Example 2:
 *
 * Input:
 * k = 2
 * keywords = ["anacell", "betacellular", "cetracular", "deltacellular", "eurocell"]
 * reviews = [
 *   "I love anacell Best services; Best services provided by anacell",
 *   "betacellular has great services",
 *   "deltacellular provides much better services than betacellular",
 *   "cetracular is worse than anacell",
 *   "Betacellular is better than deltacellular.",
 * ]
 *
 * Output:
 * ["betacellular", "anacell"]
 *
 * Explanation:
 * "betacellular" is occuring in 3 different reviews. "anacell" and "deltacellular" are occuring in 2 reviews, but "anacell" is lexicographically smaller.
 *
 * I couldn't solve the top N as it was asking me to sort based on max no. of occurrences in different reviews(if 2 words have the same frequency, we need to sort the word that occurs in more no. of reviews first).
 *
 */
public class TopKFrequentlyMentionedKeywords {

    public static void main(String[] args) {
        int k1 = 2;
        String[] keywords1 = { "anacell", "cetracular", "betacellular" };
        String[] reviews1 = { "Anacell provides the best services in the city", "betacellular has awesome services",
                "Best services provided by anacell, everyone should use anacell", };
        int k2 = 3;
        String[] keywords2 = { "anacell", "betacellular", "cetracular", "deltacellular", "eurocell" };
        String[] reviews2 = { "I love anacell Best services; Best services provided by anacell",
                "betacellular has great services", "deltacellular provides much better services than betacellular",
                "cetracular is worse than anacell", "Betacellular is better than deltacellular.", };
        System.out.println(findTopKeywords(k1, keywords1, reviews1));
        System.out.println("---------");
        System.out.println(findTopKeywords(k2, keywords2, reviews2));
    }

    /*
    A brute force solution could be to sort the array and return the largest K numbers. The time complexity of such an algorithm will be O(N∗logN) as we need to use a sorting algorithm like Timsort if we use Java’s Collection.sort(). To do better, the best data structure that comes to mind to keep track of top ‘K’ elements is Heap.

    If we iterate through the array one element at a time and keep ‘K’ largest numbers in a heap such that each time we find a larger number than the smallest number in the heap, we do two things:

    1. Take out the smallest number from the heap, and
    2. Insert the larger number into the heap.

This will ensure that we always have ‘K’ largest numbers in the heap. The most efficient way to repeatedly find the smallest number among a set of numbers will be to use a min-heap. As we know, we can find the smallest number in a min-heap in constant time O(1), since the smallest number is always at the root of the heap. Extracting the smallest number from a min-heap will take O(logN) (if the heap has ‘N’ elements) as the heap needs to readjust after the removal of an element.
     */

    private static List<String> findTopKeywords(int k, String[] keywords, String[] reviews) {

        // O(r X w) + O(Nlogk) T | O(N+k) S
        // which asymptotically equal to O(N∗logk)for Time and O(N) for space
        // r is number of reviews,
        // w is Avg number of words in review,
        // N is number of keywords

        List<String> result = new ArrayList<>();
        Set<String> keywordsSet = new HashSet<>(Arrays.asList(keywords));
        Map<String, Integer> keywordFrequencyMap = new HashMap<>();


        for(String review : reviews) {

            String[] words = review.split("\\W");
            Set<String> countedWords = new HashSet<>();

            for(String word : words) {
                word = word.toLowerCase();
                if(keywordsSet.contains(word) && !countedWords.contains(word)) {
                    keywordFrequencyMap.put(word, keywordFrequencyMap.getOrDefault(word, 0) + 1);
                    countedWords.add(word);
                }
            }
        }

        for (Map.Entry<String, Integer> mp : keywordFrequencyMap.entrySet()){
            System.out.println(mp.getKey() +": freq: " + mp.getValue());
        }

        // using minHeap so that we can poll smallest number from heap quickly as O(1) time
        Queue<Map.Entry<String, Integer>> heap = new PriorityQueue<>((a, b)->
                a.getValue() == b.getValue() ?
                        b.getKey().compareTo(a.getKey()) : a.getValue() - b.getValue()
        );

        // Get keys and values
        for (Map.Entry<String, Integer> entry : keywordFrequencyMap.entrySet()) {
            if(heap.size() < k) {
                heap.add(entry); //klog(k)| N = no of keywords
            }
            else if (heap.peek().getValue() < entry.getValue()){
                heap.poll(); //(N-k)log(k)
                heap.add(entry);
            }

            /*if(heap.size() > k){
                heap.poll(); //(N-k)log(k)
            }*/
        }


        while(!heap.isEmpty()) {
            result.add(heap.poll().getKey()); //klogk
        }
        Collections.reverse(result); // klogk
        return result;
    }

}
