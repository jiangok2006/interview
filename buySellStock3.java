// time: O(2^n), space: O(n)
public class buySellStock3 {
    // https://www.codingninjas.com/studio/problems/buy-and-sell-stock_1071012?source=youtube&campaign=striver_dp_videos&utm_source=youtube&utm_medium=affiliate&utm_campaign=striver_dp_videos&leftPanelTab=0
    static Integer[][][] dp; // len, trans, buy/sell
    // at price idx i, with trans 0, 1 or 2, buy or sell's max.
    static int[] prices;
    static int len;
    static int BUYING = 0;
    static int SELLING = 1;

    public static int maxProfit(int[] prices) {
        // Write your code here.
        len = prices.length;
        dp = new Integer[len][3][2];
        buySellStock3.prices = prices;
        return dfs(0, BUYING, 2);
    }

    static int dfs(int idx, int buyOrSell, int remainTrans) {
        if (idx > len - 1) {
            return 0;
        }

        if (dp[idx][remainTrans][buyOrSell] != null)
            return dp[idx][remainTrans][buyOrSell];

        if (buyOrSell == BUYING) {
            int buy = 0;
            if (remainTrans > 0)
                buy = dfs(idx + 1, SELLING, remainTrans - 1) - prices[idx];
            int rest = dfs(idx + 1, BUYING, remainTrans);
            dp[idx][remainTrans][buyOrSell] = Math.max(buy, rest);
        } else {
            int sell = dfs(idx + 1, BUYING, remainTrans) + prices[idx];
            int rest = dfs(idx + 1, SELLING, remainTrans);
            dp[idx][remainTrans][buyOrSell] = Math.max(sell, rest);
        }

        return dp[idx][remainTrans][buyOrSell];
    }

    public static void main(String args[]) {
        System.out.println(buySellStock3.maxProfit(new int[] {1, 7, 20, 23, 21, 11, 3, 15}));
    }
}
