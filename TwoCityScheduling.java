import java.util.Arrays;

// https://leetcode.com/problems/two-city-scheduling/
public class TwoCityScheduling {

    public int twoCitySchedCost(int[][] costs) {
        int[][] dp = new int[costs.length][costs.length];
        for(int[] d : dp) {
            Arrays.fill(d, Integer.MAX_VALUE);
        }
        dp[0][0] = 0;

        int min = Integer.MAX_VALUE;
        int half = costs.length/2;

        for(int i=0; i<=half; ++i) {
            for(int j=0; j<=half; ++j) {

                if(i==0 && j==0)
                    continue;

                if(0<i) {
                    dp[i][j] = dp[i-1][j] + costs[i+j-1][1];
                }

                if(0<j) {
                    dp[i][j] = Math.min(dp[i][j], dp[i][j-1] + costs[i+j-1][0]);
                }
                
                if(i+j == costs.length) {
                    min = Math.min(min, dp[i][j]);
                }
            }
        }

        return min;
    }
}

