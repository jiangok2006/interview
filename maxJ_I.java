import java.util.Stack;

public class maxJ_I {
   /*
    * https://www.geeksforgeeks.org/given-an-array-arr-find-the-maximum-j-i-such-that-arrj-arri/?ref
    * =roadmap
    */

   class Node {
      public int num;
      public int idx;
      public int gap;

      public Node(int num, int idx, int gap) {
         this.num = num;
         this.idx = idx;
         this.gap = gap;
      }
   }

   public int maxDiff(int[] arr) {
      int max = 0;
      Stack<Node> stack = new Stack<>();
      for (int i = 0; i < arr.length; ++i) {
         int localmax = 0;
         while (stack.size() > 0 && stack.peek().num < arr[i]) {
            Node node = stack.pop();
            int gap = i - node.idx + node.gap;
            localmax = Math.max(localmax, gap);
         }
         stack.push(new Node(arr[i], i, localmax));
         max = Math.max(max, localmax);
      }
      return max;
   }

   public static void main(String args[]) {
      int r = new maxJ_I().maxDiff(new int[] {34, 8, 10, 3, 2, 80, 30, 33, 1});
      System.out.println(r);
   }
}
