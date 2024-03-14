import java.util.Arrays;
import java.util.Stack;

// T: O(n) instead of O(N^2), every element of the array is added and removed from the stack at most once.
// S: O(n)
public class stockspan {
// https://www.geeksforgeeks.org/the-stock-span-problem/

    class Node {
        public int number;
        public int len;
        public Node(int number, int len) {
            this.number = number;
            this.len = len;
        }
    }

    public int[] stockSpan(int[] arr) {
        Stack<Node> stack = new Stack<>();
        int[] ret = new int[arr.length];
        ret[0] = 1;
        stack.push(new Node(arr[0], 1));
        for(int i=1; i<arr.length; ++i) {
            int count = 1;
            while(!stack.empty() && stack.peek().number<=arr[i]) {
                Node cur = stack.pop();
                count += cur.len;
            }
            stack.push(new Node(arr[i], count));
            ret[i] = count;
        }
        return ret;
    }

    public static void main(String args[]) {
        System.out.println(
            Arrays.toString(
                new stockspan().stockSpan(
                    new int[]{100, 80, 60, 70, 60, 75, 85})));

    }
}
