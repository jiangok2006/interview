class Solution2 {
    public static int rob(int[] nums) {
        if (nums.length == 1)
            return nums[0];

        int[][] dp = new int[nums.length][];
        for (int i = 0; i < nums.length; ++i) {
            dp[i] = new int[nums.length];
        }

        for (int i = 0; i < nums.length; ++i) {
            dp[i][i] = nums[i];
        }

        for (int len = 2; len < nums.length; ++len) {
            for (int st = 0; st < nums.length; ++st) {
                int end = st + len - 1;

                int max = 0;
                for (int i = st; i <= end; ++i) {
                    int left = 0;
                    if (st < i)
                        left = dp[st][(i - 1) % nums.length];
                    int right = 0;
                    if (i < end)
                        right = dp[(i + 1) % nums.length][end % nums.length];
                    max = Math.max(max, left + right);
                }
                dp[st][end % nums.length] = max;
            }
        }

        int max = 0;
        for (int i = 0; i < nums.length; ++i) {
            max = Math.max(max, dp[(i + 1) % nums.length][(i + nums.length - 1) % nums.length]);
        }

        return max;
    }

    public static void main(String args[]) {
        int ret = rob(new int[] {2, 3, 2});
        System.out.println(ret);
    }
}
