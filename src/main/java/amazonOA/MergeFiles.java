package amazonOA;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Given n files of different times, we need to connect these files into one file. We can connect only 2 files at a time. The time to merge required to connect 2 files is equal to sum of their times. The time of this connected file is also equal to the sum of their times. This process is repeated until n files are connected into a single file. Find the min possible time to merge required to connect all files.
 *
 * Example 1:
 *
 * Input: files = [8, 4, 6, 12]
 * Output: 58
 * Explanation: The optimal way to connect files is as follows
 * 1. Connect the files of time 4 and 6 (time to merge is 10). files after connecting: [8, 10, 12]
 * 2. Connect the files of time 8 and 10 (time to merge is 18). files after connecting: [18, 12]
 * 3. Connect the files of time 18 and 12 (time to merge is 30).
 * Total time to merge to connect the files is 10 + 18 + 30 = 58
 *
 * Example 2:
 *
 * Input: files = [20, 4, 8, 2]
 * Output: 54
 *
 * Example 3:
 *
 * Input: files = [1, 2, 5, 10, 35, 89]
 * Output: 224
 *
 * Example 4:
 *
 * Input: files = [2, 2, 3, 3]
 * Output: 20
 *
 *
 *
 * Approach:
 *
 * In order to solve this problem, all we have to make sure that we add smalles file with the second smallest and so on. This gives the idea that if we sort the files by their sizes and add them, sort again the array again until there are no files to add. It will always give us the optimal solution to connect files.
 *
 * if we try to solve it by sorting then the complexity will be dominated by the sorting algorithm, best we can achieve is O(nlogn) using quicksort or merge sort. Also, connecting two files we have to sort the array again. So overall complexity of this method is O(n^2 * logn)
 *
 * The best way I can think of using Priority Queue which will give us the minimum element in the least time as All we need is the two files with the least length.
 * 
 *  If we create a min-heap with lengths of files, we can easily find the two files with the least length in O(1) complexity.
 *
 *     1. Create a min heap (priority queue) from the array of file lengths
 *     2. Fetch the root which will give us smallest file
 *     3. Fetch the root again which will give us second smallest file
 *     4. Add two files and put it back into heap (heapify)
 *     5. Go back to step 2
 */
public class MergeFiles {

    public static void main(String[] args) {
        int[] files1 = {8, 4, 6, 12};
        int[] files2 = {20, 4, 8, 2};
        int[] files3 = {1, 2, 5, 10, 35, 89};
        int[] files4 = {2, 2, 3, 3};
        int[] files5 = {2};

        System.out.println(mergeFiles(files1));
        System.out.println(mergeFiles(files2));
        System.out.println(mergeFiles(files3));
        System.out.println(mergeFiles(files4));
        System.out.println(mergeFiles(files5));
    }


    private static int mergeFiles(int[] files) {

        //edge cases
        if (files == null ||  files.length == 0) return -1;
        if (files.length == 1) return files[0];

        Queue<Integer> minHeap = new PriorityQueue<Integer>();

        //Traverse over all files and add them to heap
        for(int file : files) {
            minHeap.offer(file); // time cost is O(NlogN)
        }
        int result = 0;
        while(minHeap.size() > 1) {
            //poll 2 files with least time
            int f1 = minHeap.poll();
            int f2 = minHeap.poll();

            //add two files with least time then add them to result
            int tmp = f1 + f2;
            result += tmp;

            //add the result back to heap for next file
            minHeap.offer(tmp);
        }
        return result;
    }
}
