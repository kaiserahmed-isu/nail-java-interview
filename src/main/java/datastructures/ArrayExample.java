package datastructures;

import java.util.Arrays;

/**
 * Created by Kaiser on 4/4/20.
 */
public class ArrayExample {
    public static void main(String[] args) {
        // Array: Collection of objects of same data type and size cannot be changed

        //Declare an array
        int [] nums;

        //Declare and allocate an array
        double [] doubles = new double[5];

        //Declare, allocate and initializing an array in java it's {} instead of []
        String[] availablePets = {"dog", "cat", "bird"};

        System.out.println(Arrays.toString(availablePets));

        System.out.println(isItemInArray("dog", availablePets));

    }

    public static boolean isItemInArray(String item, String [] items){
        for(String i : items){
            if (i.toLowerCase().equals(item)){
                return true;
            }
        }

        return false;
    }


    /**
     * Advantages:
     * 1. Used to implement other data structures
     * 2. Act as a container object for multiple items
     * 3. Less memory overhead
     *
     * Disadvantages:
     * 1. Takes longer to search as loop over all items
     * 2. Fixed size so need to create another bigger array to resize and copy all array to the new array
     */
}
