package playground;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Kaiser on 6/18/20.
 */
public class CellComete {

    public List<Integer> cellCompete(int[] states, int days)
    {

        List<Integer> list = new ArrayList<Integer>();



        // if the number of cells is not equals to 8
        // or number of days is less than 1
        if (states.length != 8 || days < 1)
        {
            return list;
        }

        // local variables
        int index, previousValue, nextValue;

        // loop for each day
        for (int i = 0; i < days; i++)
        {
            // index of current cell
            index = 0;
            // value of the previous cell
            previousValue = 0;
            // value of the next cell
            nextValue = 0;

            // loop for each cell in the array
            while (index < states.length)
            {
                // if the current index is not last index of the given array,
                // set the value of nextValue, else it would remain 0.
                if (index < states.length - 1)
                {
                    nextValue = states[index + 1];
                }
                else if (index == states.length - 1)
                {
                    nextValue = 0;
                }

                // if nextValue is same as previousValue
                if (nextValue == previousValue)
                {
                    // save the current index value for next iteration of loop
                    previousValue = states[index];
                    // set current index value
                    states[index] = 0;
                }
                else
                {
                    // save the current index value for next iteration of loop
                    previousValue = states[index];
                    // set current index value
                    states[index] = 1;
                }

                // next item in the loop
                index++;
            }
        }

        for (int i = 0; i < states.length; i++) {
            list.add(Integer.valueOf(states[i]));
        }

        // return states array
        return list;
    }
}
