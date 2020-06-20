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
public class TopKFrequentlyMentionedKeywordsII {

    public static void main(String[] args) {
/*
        int k1 = 2;
        String[] keywords1 = { "anacell", "cetracular", "betacellular" };
        String[] reviews1 = { "Anacell provides the best services in the city", "betacellular has awesome services",
                "Best services provided by anacell, everyone should use anacell", };
        int k2 = 2;
        String[] keywords2 = { "anacell", "betacellular", "cetracular", "deltacellular", "eurocell" };
        String[] reviews2 = { "I love anacell Best services; Best services provided by anacell",
                "betacellular has great services", "deltacellular provides much better services than betacellular",
                "cetracular is worse than anacell", "Betacellular is better than deltacellular.", };
        System.out.println(findTopKeywords(k1, keywords1, reviews1));
        System.out.println(findTopKeywords(k2, keywords2, reviews2));
*/

        //Test 1
        String[] toys = {"elmo", "elsa", "legos", "drone", "tablet", "warcraft"};
        int topToys = 10;

        String [] quotes = {
                "Elmo is the hottest of the season! Elmo will be on every kid's wishlist!",
                "The new Elmo dolls are super high quality",
                "Expect the Elsa dolls to be very popular this year, Elsa!",
                "Elsa and Elmo are the toys I'll be buying for my kids, Elsa is good",
                "For parents of older kids, look into buying them a drone",
                "Warcraft is slowly rising in popularity ahead of the holiday season"
        };


        System.out.println(findTopKeywords(topToys, toys, quotes));


        //Test 2
        String[] toys2 = {};
        int topToys2 = 0;

        String [] quotes2 = {
        };


        System.out.println(findTopKeywords(topToys2, toys2, quotes2));



    }

    /*
    Intuition and Algorithm

Count the frequency of each word, then add it to heap that stores the best k candidates. Here, "best" is defined with our custom ordering relation, which puts the worst candidates at the top of the heap. At the end, we pop off the heap up to k times and reverse the result so that the best candidates are first.

Complexity Analysis

    Time Complexity: O(Nlog⁡k), where N is the length of words. We count the frequency of each word in O(N) time, then we add NNN words to the heap, each in O(log⁡k) time. Finally, we pop from the heap up to k times. As k≤N , this is O(Nlog⁡k) in total.


    Space Complexity: O(N), the space used to store our count.

     */

    private static List<String> findTopKeywords(int k, String[] keywords, String[] reviews) {

        // O(r X w) + O(wlogk) T | O(w) S where r is number of reviews and w is number of words in review

        List<String> result = new ArrayList<>();

        if (k <= 0 || keywords.length ==0 || reviews.length == 0 ) return result;

        Set<String> keywordsSet = new HashSet<>(Arrays.asList(keywords));
        Map<String, Integer[]> keywordFrequencyMap = new HashMap<>();

        for(String review : reviews) {

            String[] words = review.split("\\W");

            Set<String> countedWordsForReview = new HashSet<>();

            for(String word : words) {
                word = word.toLowerCase();

                if(keywordsSet.contains(word)) {

                    Integer [] currentFreq = keywordFrequencyMap.getOrDefault(word, new Integer[] {0,0});
                    currentFreq[0] = currentFreq[0] + 1;

                    if (!countedWordsForReview.contains(word)){
                        currentFreq[1] = currentFreq[1] + 1;
                        countedWordsForReview.add(word);
                    }
//                    System.out.println(word + ": " + currentFreq[0] + "," + currentFreq[1]);

                    keywordFrequencyMap.put(word, currentFreq);
                }
            }

        }

        for (Map.Entry<String, Integer[]> mp : keywordFrequencyMap.entrySet()){
            System.out.println(mp.getKey() +": freq: " + mp.getValue()[0]);
            System.out.println(mp.getKey() +": review: " + mp.getValue()[1]);
        }

        Queue<Map.Entry<String, Integer[]>> heap = new PriorityQueue<>(new Comparator<Map.Entry<String, Integer[]>>() {
            @Override
            public int compare(Map.Entry<String, Integer[]> a, Map.Entry<String, Integer[]> b) {


                if (a.getValue()[0].equals(b.getValue()[0])){
                    return a.getValue()[1] - b.getValue()[1];
                }
                else {
                    return a.getValue()[0] - b.getValue()[0];
                }


                /*if(a.getValue()[0] < b.getValue()[0]){
//                    System.out.println(a.getValue()[0]  + "<" + b.getValue()[0] );
                    return -1;
                }
                else if (a.getValue()[0] > b.getValue()[0]){
//                    System.out.println(a.getValue()[0]  + ">" + b.getValue()[0] );
                    return 1;
                }
//                else if (a.getValue()[0].equals(b.getValue()[0])){
//                    return a.getValue()[1].compareTo(b.getValue()[1]);
//                }
                else {
                    System.out.println(a.getValue()[0]  + "=" + b.getValue()[0] );
                    return 0;
                }*/

            }
        });

        // Get keys and values
        for (Map.Entry<String, Integer[]> entry : keywordFrequencyMap.entrySet()) {
            heap.offer(entry);
            if(heap.size() > k){
                heap.poll();
            }
        }

        System.out.println(heap);


        while(!heap.isEmpty()) {

            result.add(heap.poll().getKey());
        }
        Collections.reverse(result);
        return result;
    }

}
