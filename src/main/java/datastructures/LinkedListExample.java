package datastructures;

import java.util.LinkedList;

/**
 * Created by Kaiser on 4/4/20.
 */
public class LinkedListExample {
    public static void main(String[] args) {
        /**
         * A linked list is a linear data structure where elements containing data of the same type are linked using pointers.
         *
         * Each element in the linked list is called a node and contains data along with a pointer pointing to the next element in the linked list. If the next pointer is null, then it is the last element in the list. Since the elements are connected by pointers, the items do not need to be stored at contiguous locations, and insertion and deletion is less expensive. With these pointers, we can have singly-linked lists or doubly-linked lists. Singly-linked lists have nodes that only have a pointer pointing to the next node. Doubly-linked lists have nodes that have pointers both to the next node and the previous node.
         */


        LinkedList<String> students = new LinkedList<String>();

        //Add student
        students.add("Kaiser");
        students.add("Maria");

        students.addFirst("Ahmed");
        students.addLast("Akther");

        students.add(2, "Razeen");

        System.out.println("First: "+ students.getFirst());
        System.out.println("Last: " + students.getLast());
        System.out.println("Index of Kaiser: " + students.indexOf("Kaiser"));
        System.out.println(students);

        students.remove(2);
        System.out.println(students.contains("Maria"));


        /**
         * Advantages:
         * 1. Quicker at inserting and deleting
         * 2. Used to implement Stack and Queue
         *
         * Disadvantage:
         * 1. More memory
         * 2. Search or node traversal still time consuming
         *
         */

    }
}
