package amazonOA;

import java.util.*;

/**
 * Created by Kaiser on 6/19/20.
 */
public class CriticalRouters {

    public static void main(String[] a) {
        ArrayList<ArrayList<Integer>> group = new ArrayList<>();
        group.add(new ArrayList<Integer>(Arrays.asList(0,1)));
        group.add(new ArrayList<Integer>(Arrays.asList(0,2)));
        group.add(new ArrayList<Integer>(Arrays.asList(1,3)));
        group.add(new ArrayList<Integer>(Arrays.asList(2,3)));
        group.add(new ArrayList<Integer>(Arrays.asList(2,5)));
        group.add(new ArrayList<Integer>(Arrays.asList(5,6)));
        group.add(new ArrayList<Integer>(Arrays.asList(3,4)));

        ;
        System.out.println(criticalRouters(7, 7, group));
    }

    static int time = 0;

    private static Set<Integer> criticalRouters(int numRouters, int numLInks, ArrayList<ArrayList<Integer>> links) {


        //Handling corner cases : number of nodes should be more than 1, at least 1 edge as well as make sure edges array is not empty
        if (numRouters <=1 || numLInks <= 0 || links.size() == 0 || links.get(0).size() ==0 ) {
            return new HashSet<>();
        }

        time = 0;

        //initialize result set
        Set<Integer> result = new HashSet<>();

        //build the graph:
        Map<Integer,Set<Integer>> graph = new HashMap<>();
        for(int i = 0; i < numRouters; i++) {
            graph.put(i, new HashSet<>());
        }
        for(ArrayList<Integer> link : links) {
//            System.out.print(edge[0]);
//            System.out.println(edge[1]);
            graph.get(link.get(0)).add(link.get(1));
            graph.get(link.get(1)).add(link.get(0));

        }

        // Bookkeeping
        // track visited nodes
        boolean[] isVisited = new boolean[numRouters];
        Arrays.fill(isVisited, false);

        // setup for tracking nodes
        int[] lowTime = new int[numRouters];
        int[] visitedTime = new int[numRouters];
        int[] parent = new int[numRouters];
        Arrays.fill(lowTime, -1);
        Arrays.fill(visitedTime, -1);
        Arrays.fill(parent, -1);

        // depth-first traversal of all nodes
        for(int i = 0; i < numRouters; i++) {
            if(!isVisited[i]) {
                dfs(graph, isVisited, lowTime, visitedTime, parent, i, result);
            }
        }

//        dfs(graph, isVisited, lowTime, visitedTime, parent, edges[0][0], result);

        return result;

    }



    private static void dfs(Map<Integer, Set<Integer>> graph, boolean[] isVisited, int[] lowTime, int[] visitedTime, int[] parent, int currentRouterIdx, Set<Integer> result) {

        // intial setup of the node
        isVisited[currentRouterIdx] = true;
        visitedTime[currentRouterIdx] = time;
        lowTime[currentRouterIdx] = time;
        time++;

        int childCount = 0;
        boolean isCriticalRouter = false;

        for(int adjRouter: graph.get(currentRouterIdx)) {

            // if adj is same as parent then just ignore this node
            if(adjRouter == parent[currentRouterIdx]) {
                continue;
            }

            // if adj has not been visited then visit it
            if(!isVisited[adjRouter]) {
                parent[adjRouter] = currentRouterIdx;
                childCount++;
                dfs(graph,isVisited,lowTime,visitedTime,parent,adjRouter,result);

                if(visitedTime[currentRouterIdx] <= lowTime[adjRouter]) {
                    // This (currNode - adjNodeis) path is critical because there is no path for adjNodeis to reach back to currNode or previous vertices of currNode
                    isCriticalRouter = true;
                }
                else {
                    lowTime[currentRouterIdx] = Math.min(lowTime[currentRouterIdx], lowTime[adjRouter]);
                }
            }
            else {
                lowTime[currentRouterIdx] = Math.min(lowTime[currentRouterIdx], visitedTime[adjRouter]);
            }
        }

        // check if it is not root of graph
        if((parent[currentRouterIdx] == -1 && childCount >= 2) || (parent[currentRouterIdx] != -1 && isCriticalRouter)) {
            result.add(currentRouterIdx);
        }
    }
}
