import java.util.HashMap;
import java.util.Map;

public class connectedComponents {

    /**
     * @param n: the number of vertices
     * @param edges: the edges of undirected graph
     * @return: the number of connected components
     */
    Map<Integer, Integer> map = new HashMap<>();

    public int countComponents(int n, int[][] edges) {
        // write your code here
        int cnt = 0;
        for (int[] e : edges) {
            if (!map.containsKey(e[0]) && !map.containsKey(e[1])) {
                map.put(e[0], e[0]);
                map.put(e[1], e[0]);
                cnt++;
            } else if (!map.containsKey(e[0])) {
                map.put(e[0], map.get(e[1]));
            } else if (!map.containsKey(e[1])) {
                map.put(e[1], map.get(e[0]));
            } else {
                int r0 = getRoot(e[0]);
                int r1 = getRoot(e[1]);
                if (r0 != r1) {
                    cnt--;
                    map.put(e[1], r0);
                }
            }
        }
        return cnt;
    }

    int getRoot(int c) {
        int p = map.get(c);
        while (p != c) {
            p = getRoot(p);
            map.put(c, p);
        }
        return p;
    }

    public static void main(String args[]) {
        int ret = new connectedComponents().countComponents(6,
                new int[][] {{0, 1}, {1, 2}, {2, 3}, {4, 5}});
        System.out.println(ret);
    }
}
