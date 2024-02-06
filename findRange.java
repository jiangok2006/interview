/*
 * GIven a sorted Array [1,3,5,7] find [1,6) find number of values within that range where 1 is inclusive 6 is exclusive. Tiktok
 * In this case, it should return [1, 3, 5]
 */

public class findRange {
    void findNumbers(int[] sorted, int low, int high_ex) {
        // assume the sorted arr can have dup.
        // find low using bst
        int li = bst_low(sorted, low, 0, sorted.length-1);
        if(li == -1) { // low is higher than max
            return;
        }

        // find high using bst
        int hi = bst_high(sorted, high_ex, 0, sorted.length-1);
        if(hi == -1) {
            // high is less or equal to min
            return;
        }

        for(int i=li; i<=hi; ++i) {
            System.out.printf("%d,", sorted[i]);
        }
    }

    int bst_low(int[] sorted, int low, int st, int end) {
        if(st > end) {
            return -1;
        }
        if(st == end) {
            return sorted[st] >= low ? st: -1;
        }

        int mid = st + (end-st)/2;
        if(sorted[mid] >= low) { // consider dups in sorted.
            int r = bst_low(sorted, low, st, mid-1);
            if(r == -1)
                return mid;
            else return r;
        } else {
            return bst_low(sorted, low, mid+1, end);
        }
    }

    int bst_high(int[] sorted, int high_ex, int st, int end) {
        if(st > end) return -1;
        if(st == end) {
            return sorted[st] >= high_ex? -1: st;
        }

        int mid = st + (end-st)/2;
        if(sorted[mid] < high_ex) {
            int r = bst_high(sorted, high_ex, mid+1, end);
            if(r == -1)
                return mid;
            return r;
        } else {
            return bst_high(sorted, high_ex, st, mid-1);
        }
    }

    public static void main(String args[])  {
        new findRange().findNumbers(new int[]{1, 3, 3, 5, 7}, 0, 10);
    }
}
