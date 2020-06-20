package datastructures;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by Kaiser on 4/4/20.
 */
public class QueueExample {

    public static void main(String[] args) {

        //FIFO
        // Good for storing order of process

        Queue<String> queue = new LinkedList<String>();

        queue.add("Andrew");
        queue.add("Julie");
        queue.add("Glernn");

        System.out.println(queue);

        System.out.println(queue.peek());

        System.out.println("Next is: " + queue.remove());
        /*for (int i=0; i < queue.size(); i++){
            queue.remove();
            System.out.println(queue);
        }*/
    }
}
