import java.util.HashMap;
import java.util.Map;

public class hitCount_readOpt {
    // https://www.geeksforgeeks.org/design-a-hit-counter/ apple

    class Node {
        public int ts;
        public int cnt;

        public Node(int ts, int cnt) {
            this.ts = ts;
            this.cnt = cnt;
        }
    }

    Node[] arr = new Node[300];

    public hitCount_readOpt() {}

    // Record a hit.
    // @param timestamp (seconds)
    public void hit(int timestamp) {
        int idx = (timestamp - 1) % 300;
        if (arr[idx] != null && arr[idx].ts == timestamp)
            arr[idx].cnt++;
        else
            arr[idx] = new Node(timestamp, 1);
    }


    // Returns the number of hits in the past 300 seconds from the given timestamp (Sliding Window
    // behavior)
    // @param timestamp - The current timestamp (in seconds granularity).
    public int getHits(int timestamp) {
        int count = 0;
        for (int i = 0; i < 300; ++i) {
            if (arr[i] != null && arr[i].ts > timestamp - 300) {
                count += arr[i].cnt;
            }
        }
        return count;
    }

    public static void main(String args[]) {
        hitCount counter = new hitCount();
        counter.hit(5);
        counter.hit(40);
        counter.hit(100);
        counter.hit(100);
        System.out.println(counter.getHits(100) == 4);

        counter.hit(220);
        counter.hit(290);
        counter.hit(310);
        counter.hit(320);
        System.out.println(counter.getHits(320) == 7);

        counter.hit(540);
        counter.hit(540);
        counter.hit(560);
        System.out.println(counter.getHits(560) == 6);
        System.out.println(counter.getHits(565) == 6);

        System.out.println(counter.getHits(100) == 4);
        System.out.println(counter.getHits(320) == 7);
        System.out.println(counter.getHits(560) == 6);
    }
}
