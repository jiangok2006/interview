public class missingOrRepeating {
    /*
     * https://www.geeksforgeeks.org/find-a-repeating-and-a-missing-number/?ref=roadmap Given an
     * unsorted array of size n. Array elements are in the range of 1 to n. One number from set {1,
     * 2, …n} is missing and one number occurs twice in the array. Find these two numbers.
     */

    class Result {
        public int miss;
        public int repeat;

        public Result(int miss, int repeat) {
            this.miss = miss;
            this.repeat = repeat;
        }

        @Override
        public String toString() {
            return String.format("miss:%d, repeat:%d", miss, repeat);
        }
    }

    public Result getMissRepeat(int[] arr) {
        int missIdx = -1;
        int repeat = -1;
        for (int i = 0; i < arr.length; ++i) {
            if (arr[i] == -1 || arr[i] == i + 1)
                continue;

            int nextIdx = arr[i] - 1;
            if (arr[nextIdx] == -1) {
                arr[nextIdx] = arr[i];
                missIdx = i;
                arr[i] = -1;
                continue;
            }

            if (arr[nextIdx] == arr[i]) {
                repeat = arr[i];
                arr[i] = -1;
                missIdx = i;
                continue;
            }

            int tmp = arr[nextIdx];
            arr[nextIdx] = arr[i];
            arr[i] = tmp;
            i--;
        }
        return new Result(missIdx + 1, repeat);
    }

    public static void main(String args[]) {
        Result r = new missingOrRepeating().getMissRepeat(new int[] {4, 3, 6, 2, 1, 1});
        System.out.println(r.toString());
    }
}
