import java.util.HashMap;
import java.util.Map;

public class tasks {
    /*
     * Given a list of tasks (e.g. A, B, C, B, C), each task takes 1 hr execution time and some
     * cooldown time. e.g. A - 1 hr B - 3 hr C - 2 hr Return the total time for these tasks to
     * finish. In this example, 7 hours:
     * 
     * 0 1 2 3 4 5 6 7 8 9 A B C x x B C The second B need to wait for the first B to cool down
     * before start.
     */


    public int getDuration(char[] tasks, int[] cool) {
        // key: task, val: available time.
        Map<Character, Integer> map = new HashMap<>();

        int max = 0;
        for (int i = 0; i < tasks.length; ++i) {
            char t = tasks[i];
            int available = max;
            if (map.containsKey(t)) {
                available = Math.max(available, map.get(t));
            }
            max = Math.max(max, available + 1);
            map.put(t, max + cool[t - 'A']);
        }
        return max;
    }

    public static void main(String args[]) {
        System.out.println(
                new tasks().getDuration(new char[] {'A', 'B', 'C', 'B', 'C'}, new int[] {1, 3, 2}));
    }
}
