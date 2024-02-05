import java.util.ArrayList;
import java.util.List;

// https://zhongwen.gitbook.io/leetcode-report/easy/339.-nested-list-weight-sum
// nested list weight sum II: https://www.lintcode.com/problem/905/?utm_source=%5B%27sc-bky-zy%27%5D
public class NestedListSum {
    class NestedInteger {
        public int num;
        public List<NestedInteger> list;
        public boolean isList;

        public NestedInteger(int num, List<NestedInteger> list, boolean isList) {
            this.num = num;
            this.list = list;
            this.isList = isList;
        }
    }

    public int depthSum(List<NestedInteger> nestedList, int lvl) {
        int sum = 0;
        for(int i=0; i<nestedList.size(); ++i) {
            NestedInteger c = nestedList.get(i);
            if(c.isList) {
                sum += depthSum(c.list, lvl+1);
            } else {
                sum += c.num * lvl;
            }
        }

        return sum;
    }



    public void test() {
        NestedInteger n1 = new NestedInteger(2, null, false);
        List<NestedInteger> list = new ArrayList<>();
        list.add(n1);
        NestedInteger n2 = new NestedInteger(-1, list, true);

        NestedInteger n3 = new NestedInteger(3, null, false);

        List<NestedInteger> l = new ArrayList<>();
        l.add(n2);
        l.add(n3);
        System.out.println(depthSum(l, 1) == 7);
    }

    public static void main(String args[])  {
        new NestedListSum().test();
    }
}
