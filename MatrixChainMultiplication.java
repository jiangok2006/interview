public class MatrixChainMultiplication {
    // https://leetcode.com/discuss/general-discussion/1278305/all-about-matrix-chain-multiplication-easy
    // Function to find the minimum number of Multiplication
    // steps required in multiplying chain of n matrices.

    // same question: https://leetcode.com/problems/burst-balloons/description/

    int solve(int[] arr) {
        // arr: 3 2 5 7 4
        // mean: 3x2, 2x5, 5x7, 7x4 -> 3x4
        // return: min number fo multiplications.

        // analysis
        // mxn nxk -> nxkxm times
        // 3x4 4x9 9x2 -> 3x4x9 + 3x9x2 vs 3x4x2 + 4x9x2 greedy to eliminate big numbers?

        throw new IllegalArgumentException();


    }
}
