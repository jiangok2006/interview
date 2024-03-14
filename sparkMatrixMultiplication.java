import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// https://www.lintcode.com/problem/654/
public class sparkMatrixMultiplication {

    /**
     * @param a: a sparse matrix
     * @param b: a sparse matrix
     * @return: the result of A * B
     */

    String getKey(int a, int b) {
        return String.format("%d_%d", a, b);
    }

    public int[][] multiply(int[][] a, int[][] b) {
        int am = a.length;
        int an = a[0].length;
        int bm = b.length;
        int bn = b[0].length;

        int[][] ret = new int[am][bn];

        List<int[]>[] alist = new ArrayList[am]; // NOTE: this is a little tricky.
        Set<String> bset = new HashSet<>();

        for (int i = 0; i < am; ++i) {
            List<int[]> row = new ArrayList<>();
            for (int j = 0; j < an; ++j) {
                if (a[i][j] != 0) {
                    row.add(new int[] {i, j});
                }
            }
            alist[i] = row;
        }

        for (int i = 0; i < bm; ++i) {
            for (int j = 0; j < bn; ++j) {
                if (b[i][j] != 0) {
                    bset.add(getKey(i, j));
                }
            }
        }

        for (int i = 0; i < am; ++i) {
            List<int[]> row = alist[i];
            for (int j = 0; j < bn; ++j) {
                int sum = 0;
                for (int[] c : row) {
                    int bi = c[1];
                    int bj = j;
                    if (bset.contains(getKey(bi, bj))) {
                        sum += a[c[0]][c[1]] * b[bi][bj];
                    }
                }
                ret[i][j] = sum;
            }
        }
        return ret;
    }
}
