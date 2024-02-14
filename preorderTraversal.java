public class preorderTraversal {
    // https://www.geeksforgeeks.org/check-if-a-given-array-can-represent-preorder-traversal-of-binary-search-tree/

    public boolean isPreOrder(int[] arr) {
        return helper(arr, 0, arr.length - 1);
    }

    boolean helper(int[] arr, int st, int end) {
        if (end - st <= 1)
            return true;
        int c = arr[st];
        int i = st + 1;
        for (; i <= end; ++i) {
            if (arr[i] > c)
                break;
        }
        for (int j = i; j <= end; ++j) {
            if (arr[j] < c) {
                return false;
            }
        }
        boolean l = helper(arr, st + 1, i - 1);
        if (!l)
            return false;
        return helper(arr, i, end);
    }


    public static void main(String args[]) {
        System.out.println(new preorderTraversal().isPreOrder(new int[] {40, 30, 35, 20, 80, 100}));
    }
}
