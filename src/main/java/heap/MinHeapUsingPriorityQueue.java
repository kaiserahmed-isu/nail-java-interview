package heap;

/**
 * Created by Kaiser on 6/14/20.
 */
public class MinHeapUsingPriorityQueue {
    public static void main(String[] args) {
/*        PriorityQueue<Integer> pq = new PriorityQueue<Integer>(16);

        // adding numbers into PriorityQueue in arbitrary order
        pq.add(3);
        pq.add(7);
        pq.add(2);
        pq.add(4);
        pq.add(1);
        pq.add(5);


        Integer head = pq.peek();
        System.out.println("size of PriorityQueue : " + pq.size());
        System.out.println("head of the PriorityQueue : " + head); // 1

        // Now, let's remove the head and see what comes
        // next, you can use poll() method to remove
        // element from head

        head = pq.poll(); // 1
        head = pq.peek();

        System.out.println("Current value of head : " + head);
        System.out.println("size of PriorityQueue : " + pq.size());


        // Iterating over PriorityQueue, iterator returned
        // by PriorityQueue doesn't guarantee any order
        Iterator<Integer> itr = pq.iterator();
        System.out.println("Iterating over PriorityQueue");

        while(itr.hasNext()){
            System.out.println(itr.next());
        }*/


        /*
        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("betacellular" , 2);
        map.put("anacell" , 2);
        map.put("cetracular" , 1);


        PriorityQueue<Map.Entry<String, Integer>> pq1= new PriorityQueue<Map.Entry<String, Integer>>(10, new Comparator<Map.Entry<String, Integer>>() {
            public int compare(Map.Entry<String, Integer> x, Map.Entry<String, Integer> y) {
                if (x.getValue() < y.getValue()) return 1; // move to later
                if (x.getValue() > y.getValue()) return -1; // move to top
                return 0;
            }
        });
        pq1.addAll(map.entrySet());
        System.out.println("Peek: "+pq1.poll());
        System.out.println("Peek: "+pq1.poll());
        System.out.println("Peek: "+pq1.poll());*/



    }
}
