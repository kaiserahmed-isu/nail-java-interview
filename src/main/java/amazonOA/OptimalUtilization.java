package amazonOA;

import java.util.*;

/**
 * Given 2 lists a and b. Each element is a pair of integers where the first integer represents the unique id and the second integer represents a value. Your task is to find an element from a and an element form b such that the sum of their values is less or equal to target and as close to target as possible. Return a list of ids of selected elements. If no pair is possible, return an empty list.
 */
public class OptimalUtilization {

    // Time: O(N x M ) | space O(1)
    public static List<List<Integer>> closestSum(List<List<Integer>> listA, List<List<Integer>> listB, int target) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        int diff = Integer.MAX_VALUE;
        for (List<Integer> lista : listA) {
            int first = lista.get(1);
            for (List<Integer> listb : listB) {
                //System.out.println(result);
                int second = listb.get(1);
                if ((first + second) <= target) {
                    if (target - (first + second) < diff) {
                        result.clear();
                        result.add(new ArrayList<Integer>(Arrays.asList(lista.get(0), listb.get(0))));
                        diff = target - (first + second);
                    } else if (target - (first + second) == diff) {
                        result.add(new ArrayList<Integer>(Arrays.asList(lista.get(0), listb.get(0))));
                    }
                }
            }
        }

        System.out.println("Output: " + result);

        return result;

    }


    /*
    O(KlogK) where K is the longest input array.
Note:
In my thought, we can sort forwardRouteList and returnRouteList by its second element as first. What we really care is the sum of route dist must be smaller than maxTravelDist and as large as possible. We can set two pointers and traverse two sorted arrays to find the max value (and the combination of course). Since the sorting part complexity is O(MlogM + NlogN) and two-pointer traversal is O(M + N), the final complexity can be regarded as O(KlogK) where K is the longest input array.
     */
    public static  List<List<Integer>> aircraftUtilization(int maxTravelDist, int[][] forwardRouteList, int[][] returnRouteList) {
        List<List<Integer>> res = new ArrayList<>();
        int len1 = forwardRouteList.length, len2 = returnRouteList.length;
        if (len1 == 0 || len2 == 0) return res;
        Arrays.sort(forwardRouteList, (int[] a, int[] b) -> (a[1] - b[1]));
        Arrays.sort(returnRouteList, (int[] a, int[] b) -> (a[1] - b[1]));
        int left = 0, right = len2 - 1, maxVal = -1;
        HashMap<Integer, List<List<Integer>>> map = new HashMap<>();
        while (left < len1 && right >= 0) {
            int sum = forwardRouteList[left][1] + returnRouteList[right][1];
            if (sum > maxTravelDist) {
                --right;
                continue;
            }
            if (sum >= maxVal) {
                int r = right;
                map.putIfAbsent(sum, new ArrayList<>());
                // check the duplicates
                while (r >= 0 && returnRouteList[r][1] == returnRouteList[right][1]) {
                    List<Integer> list = new ArrayList<>();
                    list.add(forwardRouteList[left][0]);
                    list.add(returnRouteList[r][0]);
                    map.get(sum).add(list);
                    --r;
                }
                maxVal = sum;
            }
            ++left;
        }
        return map.get(maxVal) == null ? new ArrayList<>(): map.get(maxVal);
    }

    public static void main(String[] args) {
        List<List<Integer>> listA = new ArrayList<List<Integer>>();
        List<List<Integer>> listB = new ArrayList<List<Integer>>();
        listA.add(new ArrayList<Integer>(Arrays.asList(1, 8)));
        listA.add(new ArrayList<Integer>(Arrays.asList(2, 15)));
        listA.add(new ArrayList<Integer>(Arrays.asList(3, 9)));
        // listA.add(new ArrayList<Integer>(Arrays.asList(4, 10)));
        listB.add(new ArrayList<Integer>(Arrays.asList(1, 8)));
        listB.add(new ArrayList<Integer>(Arrays.asList(2, 11)));
        listB.add(new ArrayList<Integer>(Arrays.asList(3, 12)));
        // listB.add(new ArrayList<Integer>(Arrays.asList(4, 5)));
        System.out.println("a = " + listA);
        System.out.println("b = " + listB);
        int target = 4;
        System.out.println("target = " + target);
        closestSum(listA, listB, target);


        int[] maxTravelDists = {0, -1, 7000, 10000, 10000};
        int[][] forwardRouteLists = {{1, 3000}, {2, 5000}, {3, 7000}, {4, 10000}};
        int[][] returnRouteLists = {{1, 2000}, {2, 2000}, {3, 4000}, {4, 4000}};

        for(int i = 0; i < maxTravelDists.length; ++i){
            System.out.println(aircraftUtilization(maxTravelDists[i], forwardRouteLists, returnRouteLists));
        }

    }
}



