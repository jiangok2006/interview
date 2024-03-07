import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class maximumSum {
    /*
     * https://www.geeksforgeeks.org/maximum-sum-path-across-two-arrays/?ref=roadmap
     * 
     */

    class Result {
        public int sum;
        public int nextIdx;

        public Result(int sum, int nextIdx) {
            this.sum = sum;
            this.nextIdx = nextIdx;
        }
    }

    public int getMaxSum(int[] arr1, int[] arr2) {
        Set<Integer> set = new HashSet<>();
        for (int n : arr1) {
            set.add(n);
        }

        List<Integer> repeats = new ArrayList<>();
        for (int n : arr2) {
            if (set.contains(n)) {
                repeats.add(n);
            }
        }

        int i, j, k;
        i = j = k = 0;
        int sum = 0;
        while (i < arr1.length || j < arr2.length) {
            Integer common = k < repeats.size() ? repeats.get(k) : null;
            Result r1 = getSum(arr1, i, common);
            Result r2 = getSum(arr2, j, common);
            sum += Math.max(r1.sum, r2.sum) + (common == null ? 0 : common);
            i = r1.nextIdx;
            j = r2.nextIdx;
            k++;
        }
        return sum;
    }

    Result getSum(int[] arr, int st, Integer common) {
        if (st > arr.length - 1) {
            return new Result(0, st);
        }

        if (common == null) {
            int sum = 0;
            for (int i = st; i < arr.length; ++i) {
                sum += arr[i];
            }
            return new Result(sum, arr.length);
        }

        int sum = 0;
        int i = st;
        for (; arr[i] != common; ++i) {
            sum += arr[i];
        }
        return new Result(sum, i + 1);
    }

    public static void main(String args[]) {
        int sum = new maximumSum().getMaxSum(new int[] {2, 3, 7, 10, 12, 15, 30, 34},
                new int[] {1, 5, 7, 8, 10, 15, 16, 19});
        System.out.println(sum);
    }
}
