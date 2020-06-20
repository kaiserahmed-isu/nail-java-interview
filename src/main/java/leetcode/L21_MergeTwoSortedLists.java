package leetcode;

/**
 * Created by Kaiser on 6/16/20.
 */
public class L21_MergeTwoSortedLists {

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {

        if (l1 == null && l2 == null) {
            return null;
        }
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }

        ListNode left = l1;
        ListNode right = l2;
        ListNode leftPrev = null;

        while(left != null && right != null){

            if(left.val < right.val){
                leftPrev = left;
                left = left.next;
            }
            else{
                if(leftPrev != null ){
                    //point previous to right
                    leftPrev.next = right;
                }
                // Move forward
                //move previous cursor to right
                leftPrev = right;

                // move right cursor to right's next node
                right = right.next;

                // point previous's next to left
                leftPrev.next = left;
            }

        }

        // end of left but still has node in right then point previous next to right
        if (left == null) {
            leftPrev.next = right;
        }

        // whichever head has less value is the right one to return
        return l1.val < l2.val ? l1 : l2;
    }


//     * Definition for singly-linked list.
     public class ListNode {
         int val;
         ListNode next;
         ListNode() {}
         ListNode(int val) { this.val = val; }
         ListNode(int val, ListNode next) { this.val = val; this.next = next; }
     }

    public static void main(String[] args) {

    }
}
