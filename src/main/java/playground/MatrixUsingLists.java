package playground;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Kaiser on 6/15/20.
 */
public class MatrixUsingLists {

    public static void main(String[] args){

        List<List<Integer>> matrix = new LinkedList<>();

        System.out.println(matrix.toString());//print empty matrix
        List<Integer> row1 = new ArrayList<>(Arrays.asList(1,2,3));
        List<Integer> row2 = new ArrayList<>(Arrays.asList(4,5));
        List<Integer> row3 = new ArrayList<>(Arrays.asList(7,8,9));
        matrix.add(row1);
        matrix.add(row2);
        matrix.add(row3);
        System.out.println(matrix.toString());

//        System.out.println(matrix.get(0).get(2));

        for (int i=0; i < matrix.size(); i++){
            for (int j=0; j < matrix.get(i).size(); j++){
                System.out.println(matrix.get(i).get(j));
            }
        }

    }
}
