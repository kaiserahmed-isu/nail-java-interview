package leetcode;

import java.util.*;

/**
 * 692. Top K Frequent Words
 * Medium
 *
 * Given a non-empty list of words, return the k most frequent elements.
 *
 * Your answer should be sorted by frequency from highest to lowest. If two words have the same frequency, then the word with the lower alphabetical order comes first.
 *
 * Example 1:
 *
 * Input: ["i", "love", "leetcode", "i", "love", "coding"], k = 2
 * Output: ["i", "love"]
 * Explanation: "i" and "love" are the two most frequent words.
 *     Note that "i" comes before "love" due to a lower alphabetical order.
 *
 * Example 2:
 *
 * Input: ["the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"], k = 4
 * Output: ["the", "is", "sunny", "day"]
 * Explanation: "the", "is", "sunny" and "day" are the four most frequent words,
 *     with the number of occurrence being 4, 3, 2 and 1 respectively.
 *
 * Note:
 *
 *     You may assume k is always valid, 1 ≤ k ≤ number of unique elements.
 *     Input words contain only lowercase letters.
 *
 * Follow up:
 *
 *     Try to solve it in O(n log k) time and O(n) extra space.
 */


public class L692_TopKFrequentWords {
    // 1. Create hash table for the word with frequency
    // 2. Create heap with words
    // 3. Poll each from heap and add them to list
    // 4. Reverse ??

    public static List<String> topKFrequent(String[] words, int k){

        List<String> results = new ArrayList<String>();

        // 1. Create hash table for the word with frequency
        Map<String , Integer> wordFrequency = new HashMap<>();

        for( String word : words){
            int frequency = wordFrequency.getOrDefault(word, 0) + 1;
            wordFrequency.put(word.toLowerCase(), frequency);
        }

        // 2. Create heap and add all words
        /*
        heap: insertion, deletion, O(logN)
        peak, poll : O(1)
         */

        /*
        Time: O(NlogN) Space: O(c)
        Queue<String> heap = new PriorityQueue<>(new FrequencyComparator(wordFrequency));

        for (String word: wordFrequency.keySet()){
            heap.add(word);

        }

        // 3. Poll top k from the heap
        while (!heap.isEmpty() && k-- > 0){
            results.add(heap.poll());
        }


         */

        // 2. Create heap and add k words | Time: O(Nlogk) Space: O(k)
//        Queue<String> minHeap = new PriorityQueue<>(new FrequencyComparator(wordFrequency));
        Queue<Map.Entry<String, Integer>> minHeap = new PriorityQueue<>((a, b)->
                a.getValue() == b.getValue() ?
                        a.getKey().compareTo(b.getKey()) : b.getValue()- a.getValue()
        );


        for (Map.Entry<String, Integer> entry : wordFrequency.entrySet()) {
            minHeap.add(entry);
            if(minHeap.size() > k){
                minHeap.poll();
            }
        }

        while(!minHeap.isEmpty()) {
            results.add(minHeap.poll().getKey());
        }
/*
        for (String word: wordFrequency.keySet()){
            minHeap.add(word);
            if (minHeap.size() > k) {
                minHeap.poll();
            }

        }

        // 3. Poll top k from the heap
        while (!minHeap.isEmpty()){
            results.add(minHeap.poll());
        }*/



        return results;
    }

    private static class FrequencyComparator implements Comparator<String> {
        private final Map<String, Integer> wordFrequency;

        public FrequencyComparator(Map<String, Integer> wordFrequency) {

            this.wordFrequency = wordFrequency;
        }

        @Override
        public int compare(String left, String  right) {
            if (wordFrequency.get(left) == wordFrequency.get(right)){
                System.out.println(right+" compares with " + left);
                System.out.println(right.compareTo(left));
                return right.compareTo(left);
            }
//            return wordFrequency.get(right) - wordFrequency.get(left); //Higher value of right - MaxHeap
//            return wordFrequency.get(left) - wordFrequency.get(right); //Higher value of left - minHeap
//            System.out.println(wordFrequency.get(left).compareTo(wordFrequency.get(right)));
            return wordFrequency.get(right) - wordFrequency.get(right); //Higher value of left - minHeap
        }
    }


    public static void main(String[] args) {
        String [] words = {"the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"};
        int k = 4;

        List <String> topWords = topKFrequent(words, k);

        System.out.println(topWords);

    }
}
