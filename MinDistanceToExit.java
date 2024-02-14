/*

https://leetcode.com/discuss/interview-question/1128831/tiktok-round-1

 * We have a m x n 2D grid initialized with three possible values:
-1 - An obstacle.
0 - An exit.
INF - An empty room. We use the value 2^31 - 1 = 2147483647 to represent INF as you may assume that the distance to an exit is less than 2147483647.

We want to fill each empty room with the distance to its nearest exit. If it is impossible to reach an exit, it should be filled with INF.

Example:
Given the 2D grid:

INF -1 0 INF
INF INF INF -1
INF -1 INF -1
0 -1 INF INF

We expect the output 2D grid as:

3 -1 0 1
2 2 1 -1
1 -1 2 -1
0 -1 3 4


 */

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.List;


public class MinDistanceToExit {
        class Node {
        public int cnt;
        public int x;
        public int y;
        public Node(int cnt, int x, int y) {
            this.cnt = cnt;
            this.x = x;
            this.y = y;
        }
    }
    public void getDists(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        List<List<Integer>> exits = new ArrayList<>();
        for(int i=0; i<m; i++) {
            for(int j=0; j<n; ++j) {
                if(grid[i][j] == 0) {
                    List<Integer> coord = new ArrayList<>();
                    coord.add(i);
                    coord.add(j);
                    exits.add(coord);
                }
            }
        }

        for(int i=0; i<exits.size(); ++i) {
            List<Integer> coord = exits.get(i);
            bfs(grid, m, n, coord);
        }
    }

        void bfs(int[][] grid, int m, int n, List<Integer> coord) {
            int[][] dirs = new int[][]{{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

            int cx = coord.get(0);
            int cy = coord.get(1);
            boolean[][] visited = new boolean[m][n];
            visited[cx][cy] = true;
            Queue<Node> queue = new LinkedList<>();
            queue.add(new Node(0, cx, cy));
            while(queue.size() > 0) {
                Node c = queue.poll();
                if(grid[c.x][c.y] != 0) {
                    grid[c.x][c.y] = Math.min(grid[c.x][c.y], c.cnt);
                }

                for(int[] dir: dirs) {
                    int x2 = c.x + dir[0];
                    int y2 = c.y + dir[1];
                    if(-1 < x2 && x2 < m && -1 < y2 && y2 < n && !visited[x2][y2]) {
                        visited[x2][y2] = true;

                        if(grid[x2][y2] == 0 || grid[x2][y2] == -1)
                        {
                            continue;
                        }

                        queue.add(new Node(c.cnt+1, x2, y2));
                    }
                }
            }
        }

    public static void main(String args[])  {
        int[][] grid = new int[][]{{999,-1, 0, 999}, {999, 999, 999, -1}, {999,-1,999,-1}, {0,-1,999,999}};
        new MinDistanceToExit().getDists(grid);
        for(int i=0; i<grid.length; ++i) {
            for(int j=0; j<grid[0].length; ++j) {
                System.out.printf("%d,", grid[i][j]);
            }
            System.out.println();
        }
    }
}
