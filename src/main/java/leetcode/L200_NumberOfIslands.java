package leetcode;

/**
 * 200. Number of Islands
 * Medium
 *
 * Given a 2d grid map of '1's (land) and '0's (water), count the number of islands. An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.
 *
 * Example 1:
 *
 * Input:
 * 11110
 * 11010
 * 11000
 * 00000
 *
 * Output: 1
 *
 * Example 2:
 *
 * Input:
 * 11000
 * 11000
 * 00100
 * 00011
 *
 * Output: 3
 */
public class L200_NumberOfIslands {

    // O(N x M) T, O(1) S

    public int numIslands(char[][] grid) {

        if (grid == null || grid.length == 0 || grid[0].length == 0) return 0;

        int count = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1') {
                    count++;
                    exploreIslandUsingDFS(grid, i, j);
                }
            }
        }
        return count;
    }

    public void exploreIslandUsingDFS(char[][] grid, int i, int j) {

        if (i >= grid.length || i < 0 || j >= grid[0].length || j < 0 || grid[i][j] == 'x' || grid[i][j] == '0') return;

        grid[i][j] = 'x';

        //Down
        exploreIslandUsingDFS(grid, i+1, j);
        //UP
        exploreIslandUsingDFS(grid, i-1, j);
        //Right
        exploreIslandUsingDFS(grid, i, j+1);
        //Left
        exploreIslandUsingDFS(grid, i, j-1);
    }

    public static void main(String[] args) {
        char M[][] = new char[][] {
                { '1', '1', '0', '0', '0' },
                { '0', '1', '0', '0', '1' },
                { '1', '0', '0', '1', '1' },
                { '0', '0', '0', '0', '0' },
                { '1', '0', '1', '0', '1' } };
        L200_NumberOfIslands I = new L200_NumberOfIslands();
        System.out.println("Number of islands is: " + I.numIslands(M));
    }
}
