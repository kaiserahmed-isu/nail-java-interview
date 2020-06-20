package amazonOA;


import java.util.*;

/**
 * Given a 2D grid, each cell is either a zombie 1 or a human 0. Zombies can turn adjacent (up/down/left/right) human beings into zombies every hour. Find out how many hours does it take to infect all humans?
 * <p>
 * Example:
 * <p>
 * Input:
 * [[0, 1, 1, 0, 1],
 * [0, 1, 0, 1, 0],
 * [0, 0, 0, 0, 1],
 * [0, 1, 0, 0, 0]]
 * <p>
 * Output: 2
 * <p>
 * Explanation:
 * At the end of the 1st hour, the status of the grid:
 * [[1, 1, 1, 1, 1],
 * [1, 1, 1, 1, 1],
 * [0, 1, 0, 1, 1],
 * [1, 1, 1, 0, 1]]
 * <p>
 * At the end of the 2nd hour, the status of the grid:
 * [[1, 1, 1, 1, 1],
 * [1, 1, 1, 1, 1],
 * [1, 1, 1, 1, 1],
 * [1, 1, 1, 1, 1]]
 * <p>
 * int minHours(int rows, int columns, List<List<Integer>> grid) {
 * // todo
 * }
 */
public class ZombieInMatrix {
    // Find all Zombie position and add to the queue
    // Pop one at time from queue until queue is empty and infect neighbours
    // Add new Zombie to the queue
    // Check if any human left
    // if any humnan left return -1 or return hours/ turns



    public int minHours(int rows, int columns, List<List<Integer>> grid) {

        Queue<Zombie> zombieQueue = new LinkedList<>();

        //-1 represents either no hours taken or invalid input or human survived
        int hours = -1;





        // Find all Zombie position and add to the queue
        System.out.println("Finding Zombie...");
        for (int i = 0; i < grid.size(); i++) {
            for (int j = 0; j < grid.get(i).size(); j++) {
                if (grid.get(i).get(j) == 1) {
                    addZombieToQueue(grid, zombieQueue, i, j, -1);
                }
            }

        }
        System.out.println("Found all Zombies...");

        System.out.println("Polling  each Zombie from queue...");
        while (!zombieQueue.isEmpty()) {
            Zombie activeZombie = zombieQueue.poll();
            hours = activeZombie.infectedTime;

//            System.out.println("Infecting neighbor Zombies...");
            infectNeighbour(grid, zombieQueue, activeZombie.iIdx, activeZombie.jIdx, hours + 1);

        }


        // Check if there's any humans left in the grid.
        Boolean humanSurvived = false;
        for(int i = 0; i < grid.size(); i++)
        {
            for(int j = 0; j < grid.get(i).size(); j++)
            {
                if(grid.get(i).get(j) == 0)
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
        if (hours == -1) {
            return hours;
        }
        // Finally if zombie able to infect any human, add 1 to final count as it started with -1;
        return hours+1;
    }

    private void addZombieToQueue(List<List<Integer>> grid, Queue zombieQueue, int iIdx, int jIdx, int hourInfected) {

        //mark it as Zombie
        grid.get(iIdx).set(jIdx, 1);

        Zombie zombie = new Zombie(iIdx, jIdx, hourInfected);
        zombieQueue.offer(zombie);
    }

    private void infectNeighbour(List<List<Integer>> grid, Queue zombieQueue, int iIdx, int jIdx, int hourInfected) {

        // Look at the cells up, down, left, and right, if valid cells in the grid.
        // If there's a human there, convert it to a zombie and add it to the queue.

        // Up
        if (iIdx - 1 >= 0 && jIdx < grid.get(iIdx - 1).size() && grid.get(iIdx - 1).get(jIdx) == 0) {
            addZombieToQueue(grid, zombieQueue, iIdx - 1, jIdx, hourInfected);
        }

        // Down
        if (iIdx + 1 < grid.size() && jIdx < grid.get(iIdx + 1).size() && grid.get(iIdx + 1).get(jIdx) == 0) {
            addZombieToQueue(grid, zombieQueue, iIdx + 1, jIdx, hourInfected);
        }

        // Left
        if (jIdx - 1 >= 0 && grid.get(iIdx).get(jIdx - 1) == 0) {
            addZombieToQueue(grid, zombieQueue, iIdx, jIdx - 1, hourInfected);
        }

        // Right
        if (jIdx + 1 < grid.get(iIdx).size() && grid.get(iIdx).get(jIdx + 1) == 0) {
            addZombieToQueue(grid, zombieQueue, iIdx, jIdx + 1, hourInfected);
        }

    }


    private class Zombie {
        int iIdx;
        int jIdx;
        int infectedTime;

        public Zombie(int iIdx, int jIdx, int infectedTime) {
            this.iIdx = iIdx;
            this.jIdx = jIdx;
            this.infectedTime = infectedTime;
        }
    }


    public static void main(String[] args) {

        List<List<Integer>> grid = new LinkedList<>();

/*        List<Integer> row1 = new ArrayList<>(Arrays.asList(0, 1, 1, 0, 1));
        List<Integer> row2 = new ArrayList<>(Arrays.asList(0, 1, 0, 1, 0));
        List<Integer> row3 = new ArrayList<>(Arrays.asList(0, 0, 0, 0, 1));
        List<Integer> row4 = new ArrayList<>(Arrays.asList(0, 1, 0, 0, 0));
        grid.add(row1);
        grid.add(row2);
        grid.add(row3);
        grid.add(row4);*/

        List<Integer> row1 = new ArrayList<>(Arrays.asList(0));
        List<Integer> row2 = new ArrayList<>(Arrays.asList(0,1));
        grid.add(row1);
        grid.add(row2);


        ZombieInMatrix findZombie = new ZombieInMatrix();

        int resultHours = findZombie.minHours(4, 5, grid);

        System.out.println(resultHours);
    }
}


