package leetcode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by Kaiser on 6/15/20.
 */
public class L994_RottingOranges {

    private static class Zombie {
        int i;
        int j;
        int turnInfected;

        public Zombie(int i, int j, int turnInfected) {
            this.i = i;
            this.j = j;
            this.turnInfected = turnInfected;
        }
    }

    public int orangesRotting(int[][] grid){


        /*if(grid.length < 1 || grid[0].length < 1) {
            return -1; //empty
        }*/

        int turn = -1;

        // Create a queue that holds zombies.
        Queue<Zombie> q = new LinkedList<>();

        // Go through the grid to add the initial zombies to the queue.
        for(int i = 0; i < grid.length; i++)
        {
            for(int j = 0; j < grid[i].length; j++)
            {
                // If this cell is a zombie...
                if(grid[i][j] == 2)
                {
                    // Handle infecting its neighbors.
                    AddZombieToQueue(grid, q, i, j, -1);
                }
            }
        }


        // Process each zombie in the queue by infecting any humans adjacent to it.
        while(!q.isEmpty())
        {
            Zombie o = q.poll();

            // Update the turn. (This zombie is definitely infected last, cuz q).
            turn = o.turnInfected;

            // Handle infecting any neighbors.
            InfectNeighbors(grid, q, o.i, o.j, o.turnInfected+1);
        }


        // Check if there's any humans left in the grid.
        Boolean humanSurvived = false;
        for(int i = 0; i < grid.length; i++)
        {
            for(int j = 0; j < grid[i].length; j++)
            {
                if(grid[i][j] == 1)
                {
                    humanSurvived = true;
                    break;
                }
            }
            if(humanSurvived)
            { break; }
        }

        // If a human survived then return -1. Otherwise return the minute the last human got infected.
        if (humanSurvived){
            return -1;
        }
        // no human exist to be infected by Zombie
        if (turn == -1) {
            return turn;
        }
        // Finally if zombie able to infect any human, add 1 to final count as it started with -1;
        return turn +1;

    }




    void AddZombieToQueue(int[][] grid, Queue<Zombie> q, int i, int j, int turnInfected)
    {
        // Mark this as a zombie in the grid.
        grid[i][j] = 2;

        // Add this zombie to the queue.
        Zombie o = new Zombie(i, j, turnInfected);

        q.offer(o);
    }

    void InfectNeighbors(int[][] grid, Queue<Zombie> q, int i, int j, int turnInfected)
    {
        // Look at the cells up, down, left, and right, if valid cells in the grid.
        // If there's a human there, convert it to a zombie and add it to the queue.

        // Up
        if(i-1 >= 0 && j < grid[i-1].length && grid[i-1][j] == 1)
        { AddZombieToQueue(grid, q, i-1, j, turnInfected); }

        // Down
        if(i+1 < grid.length && j < grid[i+1].length && grid[i+1][j] == 1)
        { AddZombieToQueue(grid, q, i+1, j, turnInfected); }

        // Left
        if(j-1 >= 0 && grid[i][j-1] == 1)
        { AddZombieToQueue(grid, q, i, j-1, turnInfected); }

        // Right
        if(j+1 < grid[i].length && grid[i][j+1] == 1)
        { AddZombieToQueue(grid, q, i, j+1, turnInfected); }
    }

    public static void main(String[] args) {
        int[][] oranges = {
                {1, 2, 2, 1, 2},
                {1, 2, 1, 2, 1},
                {1, 1, 1, 1, 2},
                {1, 2, 1, 1, 1}
        }; // return 2

//        int[][] oranges = {
//                {1},
//                {2},
//                {},
//                {}
//        };



        L994_RottingOranges intRO = new L994_RottingOranges();

        int resultHours = intRO.orangesRotting(oranges);

        System.out.println(resultHours);
    }


}
