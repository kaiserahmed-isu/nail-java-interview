package binarytree;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kaiser on 6/11/20.
 */
public class BranchSums {

    public static class BinaryTree {
        int value;
        BinaryTree left;
        BinaryTree right;

        BinaryTree(int value) {
            this.value = value;
            this.left = null;
            this.right = null;
        }
    }

    // O(n) time | O(n) Space - where n is the number of nodes in the binary tree

    public static List<Integer> branchSums(BinaryTree root){

        List<Integer> sums = new ArrayList<Integer>();

        calculateBranchSums(root, 0, sums);

        return sums;
    }

    private static void calculateBranchSums(BinaryTree node, int runningSum, List<Integer> sums) {

        if (node == null) return;

        int newRunningSum = runningSum + node.value;

        //Check if it's the leaf
        if (node.left == null && node.right == null){
            sums.add(newRunningSum);
            return;
        }

        calculateBranchSums(node.left, newRunningSum, sums);
        calculateBranchSums(node.right, newRunningSum, sums);
    }
}
