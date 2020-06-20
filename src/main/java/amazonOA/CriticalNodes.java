package amazonOA;

import java.util.*;

/**
 * You are given an undirected connected graph. An articulation point (or cut vertex) is defined as a vertex which, when removed along with associated edges, makes the graph disconnected (or more precisely, increases the number of connected components in the graph). The task is to find all articulation points in the given graph.
 *
 * Input:
 * The input to the function/method consists of three arguments:
 *
 *     numNodes, an integer representing the number of nodes in the graph.
 *     numEdges, an integer representing the number of edges in the graph.
 *     edges, the list of pair of integers - A, B representing an edge between the nodes A and B.
 *
 * Output:
 * Return a list of integers representing the critical nodes.
 *
 * Example:
 *
 * Input: numNodes = 7, numEdges = 7, edges = [[0, 1], [0, 2], [1, 3], [2, 3], [2, 5], [5, 6], [3, 4]]
 *
 * Output: [2, 3, 5]
 * Related problems:
 *
 *     https://leetcode.com/discuss/interview-question/372581
 *     https://www.geeksforgeeks.org/articulation-points-or-cut-vertices-in-a-graph/
 *     https://cp-algorithms.com/graph/cutpoints.html
 *
 * REF: https://emre.me/algorithms/tarjans-algorithm/
 * https://leetcode.com/discuss/interview-question/372581
 *
 *     Algorithm
 *
 *     The above algorithm iterates over all the vertices and in one iteration applies a Depth First Search to find connected components, so time complexity of above algorithm is
 *
 * O(V x (V x E)), where V is the number of vertices and E is the number of edges in the graph.
 *
 * Clearly the brute force approach will fail for bigger graphs.
 *
 * It utilizes the property that nodes of strongly connected component form a subtree in the DFS spanning tree of the graph.
 * In DFS tree, a vertex u is articulation point if one of the following two conditions is true.
 * 1) u is root of DFS tree and it has at least two children.
 * 2) u is not root of DFS tree and it has a child v such that no vertex in subtree rooted with v has a back edge to one of the ancestors (in DFS tree) of u.
 *
 *
 * The steps involved are:
 *
 *     A dfs is run over the nodes and the subtrees of SCCs are removed and recorded as they are encounered.
 *     Two values dfs_num(u) and dfs_low(u) are maintained for each of the users. dfs_num(u) is the value of the counter when the node u is explored for the first time. dfs_low(u) stores the lowest dfs_num reachable from u which is not the part of another SCC.
 *     As the nodes are explored, they are pushed onto a stack.
 *     The unexplored children of a node are explored and dfs_low(u) is accordingly updated.
 *     A node is encountered with dfs_low(u) == dfs_num(u) is the first explored node in its strongly connected component and all the nodes above it in the stack are popped out and assigned the appropriate SCC number.
 *
 * Complexity
 *
 *     Time complexity: The algorithm is built upon DFS and therefore, each node is visited once and only once. For each node, we perform some constant amount of work and iterate over its adjanceny list. Thus, the complexity is O(|V|+ |E|)
 *     At maximum, the depth of recursion and the size of stack can be n nodes. Thus the complexity is O(|V|)
 */
public class CriticalNodes {
    static int time = 0;

    public static void main(String[] args)
    {

        //test 1
        int numNodes = 7;
        int numEdges = 7;
        int[][] edges = {{0,1},{0,2},{1,3},{2,3},{2,5},{5,6},{3,4}};

        System.out.println(getCriticalNodes(numNodes,numEdges,edges)); // 2, 3, 5

        //test2
        int[][] edges2 = {{0,1}, {1,2}};
        System.out.println(getCriticalNodes(3,2,edges2)); // 1

        //test3
        int[][] edges3 = {{1,0}, {0,2}, {2,1}, {0,3}, {3,4}};
        System.out.println(getCriticalNodes(5,5,edges3)); // 0,3


        //test4
        int[][] edges4 = {{0,1}, {0,2}, {2,3}, {0,3}, {3,4}};
        System.out.println(getCriticalNodes(5,5,edges4)); // [0,3]

        //test5
        int[][] edges5 = {{0, 1}, {0, 2}, {1, 2}, {1, 3}, {1, 4}, {3, 5}, {4, 5}, {0,4}};
        System.out.println(getCriticalNodes(6,8,edges5)); // []

        //test5
        int[][] edges6 = {{}};
        System.out.println(getCriticalNodes(1,0,edges6)); // []


//        System.out.println(edges4[0].length);
    }

    private static List<Integer> getCriticalNodes(int numNodes, int numEdges, int[][] edges) {

        //Handling corner cases : number of nodes should be more than 1, at least 1 edge as well as make sure edges array is not empty
        if (numNodes <=1 || numEdges <= 0 || edges.length == 0 || edges[0].length ==0 ) {
            return new ArrayList<>();
        }

        int time = 0;

        //initialize result set
        Set<Integer> result = new HashSet<>();

        //build the graph:
        Map<Integer,Set<Integer>> graph = new HashMap<>();
        for(int i = 0; i < numNodes; i++) {
            graph.put(i, new HashSet<>());
        }
        for(int[] edge : edges) {
//            System.out.print(edge[0]);
//            System.out.println(edge[1]);
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);

/*            if(graph.get(edge[0]) == null){
                graph.put(edge[0], new HashSet<>());
            }
            graph.get(edge[0]).add(edge[1]);

            if(graph.get(edge[1]) == null){
                graph.put(edge[1], new HashSet<>());
            }
            graph.get(edge[1]).add(edge[0]);*/
        }

        // Bookkeeping
        // track visited nodes
        boolean[] isVisited = new boolean[numNodes];
        Arrays.fill(isVisited, false);

        // setup for tracking nodes
        int[] lowTime = new int[numNodes];
        int[] visitedTime = new int[numNodes];
        int[] parent = new int[numNodes];
        Arrays.fill(lowTime, -1);
        Arrays.fill(visitedTime, -1);
        Arrays.fill(parent, -1);

        // depth-first traversal of all nodes
/*        for(int i = 0; i < numNodes; i++) {
            if(!isVisited[i]) {
                dfs(graph, isVisited, lowTime, visitedTime, parent, i, result);
            }
        }*/

        dfs(graph, isVisited, lowTime, visitedTime, parent, edges[0][0], result);

        return new ArrayList<>(result);
    }

    private static void dfs(Map<Integer, Set<Integer>> graph, boolean[] isVisited, int[] lowTime, int[] visitedTime, int[] parent, int currNode, Set<Integer> result) {

        // Mark the current node as visited
        isVisited[currNode] = true;

        //Initialize discovery time and low value
        visitedTime[currNode] = time;
        lowTime[currNode] = time;
        time++;


        int childCount = 0;
        boolean isCriticalNode = false;

        for(int adjNode: graph.get(currNode)) {

            // if adjNode is same as parent then just ignore this node
            if(adjNode == parent[currNode]) {
                continue;
            }

            // if adj has not been visited then visit it
            if(!isVisited[adjNode]) {
                parent[adjNode] = currNode;
                childCount++;
                dfs(graph,isVisited,lowTime,visitedTime,parent,adjNode,result);

                if(visitedTime[currNode] <= lowTime[adjNode]) {
                    // This (currNode - adjNodeis) path is critical because there is no path for adjNodeis to reach back to currNode or previous vertices of currNode
                    isCriticalNode = true;
                }
                else {
                    lowTime[currNode] = Math.min(lowTime[currNode], lowTime[adjNode]);
                }
            }
            else {
                lowTime[currNode] = Math.min(lowTime[currNode], visitedTime[adjNode]);
            }
        }

        // currNode is an articulation point in following cases
        //1. is root of DFS tree and has two or more chilren. Or
        //2. is not root and marked as critical i.e. low value of one of its child is more than discovery value of it
        if((parent[currNode] == -1 && childCount >= 2) || (parent[currNode] != -1 && isCriticalNode)) {
            result.add(currNode);
        }
    }
}
