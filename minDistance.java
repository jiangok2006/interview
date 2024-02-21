import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

public class minDistance {

    public int minDist(int[] arr) {
        if (arr.length <= 4)
            return 0;

        PriorityQueue<Integer> top4 = new PriorityQueue<>();
        PriorityQueue<Integer> bottom4 = new PriorityQueue<>(Collections.reverseOrder());
        for (int i = 0; i < arr.length; ++i) {
            top4.add(arr[i]);
            if (top4.size() > 4)
                top4.poll();
            bottom4.add(arr[i]);
            if (bottom4.size() > 4)
                bottom4.poll();
        }

        List<Integer> t4 = new ArrayList<>();
        List<Integer> b4 = new ArrayList<>();
        while (top4.size() > 0)
            t4.add(top4.poll()); // asc
        while (bottom4.size() > 0)
            b4.add(bottom4.poll()); // desc

        Collections.reverse(b4); // make b4 asc

        int min = Integer.MAX_VALUE;
        for (int i = 0; i < 4; ++i) {
            int lmin = b4.get(i);
            int lmax = t4.get(i);
            min = Math.min(min, lmax - lmin);
        }

        return min;
    }

    public static void main(String args[]) {
        System.out.println(new minDistance().minDist(new int[] {-1, 3, -1, 8, 5, 4}));
        System.out.println(new minDistance().minDist(new int[] {10, 10, 3, 4, 10}));
    }
}
