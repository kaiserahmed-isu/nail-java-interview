package amazonOA;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Amazon | OA 2019 | Treasure Island II
 *
 * You have a map that marks the locations of treasure islands. Some of the map area has jagged rocks and dangerous reefs. Other areas are safe to sail in. There are other explorers trying to find the treasure. So you must figure out a shortest route to one of the treasure islands.
 *
 * Assume the map area is a two dimensional grid, represented by a matrix of characters. You must start from one of the starting point (marked as S) of the map and can move one block up, down, left or right at a time. The treasure island is marked as X. Any block with dangerous rocks or reefs will be marked as D. You must not enter dangerous blocks. You cannot leave the map area. Other areas O are safe to sail in. Output the minimum number of steps to get to any of the treasure islands.
 *
 * Example:
 *
 * Input:
 * [['S', 'O', 'O', 'S', 'S'],
 *  ['D', 'O', 'D', 'O', 'D'],
 *  ['O', 'O', 'O', 'O', 'X'],
 *  ['X', 'D', 'D', 'O', 'O'],
 *  ['X', 'D', 'D', 'D', 'O']]
 *
 * Output: 3
 * Explanation:
 * You can start from (0,0), (0, 3) or (0, 4). The treasure locations are (2, 4) (3, 0) and (4, 0). Here the shortest route is (0, 3), (1, 3), (2, 3), (2, 4).
 *
 * Related problems:
 */
public class TreasureIslandII {



    private int shortestPathToTreasureIsland(char[][] grid) {
        System.out.println("--------Starting------");
        //invalid islandGrid
        if (grid == null || grid.length == 0) return -1;

        int row=grid.length, col=grid[0].length, minStep=Integer.MAX_VALUE;

        for(int i=0; i<row; i++) {
            for(int j=0; j<col; j++) {
                if(grid[i][j]=='S') {
                    System.out.println("current start point:" + i +"," +j );
                    minStep=Math.min(minStep, this.searchForIsland(grid, i, j));
                }
            }
        }
        System.out.println("--------End------");
        return minStep;
    }


    public int searchForIsland(char[][] islandGrid, int row, int col) {

        int steps = 0;

        Queue<TreasureIsland.Coordinate> queue = new LinkedList<>();
        queue.offer(new TreasureIsland.Coordinate(row, col));

        boolean[][] visited = new boolean[islandGrid.length][islandGrid[0].length];

        visited[row][col] = true;

        int[][] directions = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

        // bfs
        while (!queue.isEmpty()) {

            int size=queue.size();
            while(size>0) {
                TreasureIsland.Coordinate coordinate = queue.poll();
                System.out.println(coordinate.x + "," +coordinate.y);
                int x = coordinate.x;
                int y = coordinate.y;
                if (islandGrid[x][y] == 'X')  {
                    System.out.println("steps: "+ steps);
                    return steps;
                }

                for (int[] dir : directions) {
                    int newX = x + dir[0];
                    int newY = y + dir[1];

                    if (newX >= 0 && newX < islandGrid.length && newY >= 0 && newY < islandGrid[0].length &&
                            islandGrid[newX][newY] != 'D' && !visited[newX][newY]) {
                        queue.offer(new TreasureIsland.Coordinate(newX, newY));
                        visited[newX][newY] = true;
                    }
                }
                size--;
            }
            steps++;
        }

        return -1;

/*

        Queue<int[]> queue=new LinkedList<>();
        queue.offer(new int[]{row, col});
        int steps=0;
        boolean[][] visited=new boolean[islandGrid.length][islandGrid[0].length];
        int[][] directions=new int[][]{{0,1},{0,-1},{1,0},{-1,0}};
        while(!queue.isEmpty()) {
            int size=queue.size();
            while(size>0) {
                int[] coor=queue.poll();
                int x=coor[0], y=coor[1];
                if(islandGrid[x][y]=='X') return steps;
                visited[x][y]=true;
                for(int i=0; i<directions.length; i++) {
                    int dx=x+directions[i][0];
                    int dy=y+directions[i][1];
                    if(dx>=0 && dx<islandGrid.length && dy>=0 && dy<islandGrid[0].length && !visited[dx][dy] && islandGrid[dx][dy]!='D') {
                        queue.offer(new int[]{dx,dy});
                    }
                }
                size--;
            }
            steps++;
        }
        return -1;*/


    }
    static class Coordinate {
        int x;
        int y;

        Coordinate(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }


    public static void main(String[] args) {
        TreasureIslandII main=new TreasureIslandII();
        char[][] grid1=new char[][]{
                {'S', 'O', 'O', 'S', 'S'},
                {'D', 'O', 'D', 'O', 'D'},
                {'O', 'O', 'O', 'O', 'X'},
                {'X', 'D', 'D', 'O', 'O'},
                {'X', 'D', 'D', 'D', 'O'}};

        char[][] grid2=new char[][]{
                {'S', 'O', 'O', 'S', 'S'},
                {'D', 'O', 'D', 'O', 'O'},
                {'O', 'O', 'O', 'O', 'X'},
                {'X', 'D', 'D', 'O', 'O'},
                {'X', 'D', 'D', 'D', 'O'}};
        int tc1 = main.shortestPathToTreasureIsland(grid1);
        int tc2 = main.shortestPathToTreasureIsland(grid2);

        System.out.println("Test1: "+ tc1);
        System.out.println("Test2: "+ tc2);
        if(tc1==3 && tc2==2) {
            System.out.println("All Test Case Passes!");
        } else {
            System.out.println("There are test failures!");
        }
    }
}
