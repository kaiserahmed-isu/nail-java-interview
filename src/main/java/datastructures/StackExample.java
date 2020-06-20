package datastructures;

import java.util.Stack;

/**
 * Created by Kaiser on 4/4/20.
 */
public class StackExample {
    public static void main(String[] args) {

        //LIFO
        // Use to reverse things
        // Runtime stack

        Stack<String> stack = new Stack<String>();

        stack.push("layer 1");
        stack.push("layer 2");
        stack.push("layer 3");

        System.out.println(stack);

        System.out.println("Who is on top: "+ stack.peek());
        System.out.println("Size: "+ stack.size());


        while (!stack.empty()){
            System.out.println("Removed: "+stack.pop());

        }
    }
}
