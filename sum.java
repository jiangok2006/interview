import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;    


class Solution {
    String getCode(int st, int end, int sum) {
        return String.format("%d_%d_%d", st, end, sum);
    } 

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        int n = candidates.length;
        Arrays.sort(candidates);
        Map<String, List<List<Integer>>> map = new HashMap<>();
        helper(candidates, 0, n-1, target, map, new ArrayList<Integer>());
        return map.get(getCode(0, n-1, target));
    }

    void helper(int[] candidates, int st, int end, int target, Map<String, List<List<Integer>>> map, List<Integer> list) {
        List<List<Integer>> comb = new ArrayList<>();
        String code = getCode(st, end, target);
        if(map.containsKey(code)) {
                return;
        }

        for(int i=st; i<=end; ++i) {
            int c = candidates[i];
            if(c > target) {
                break;
            }

            if(target == c) {
                List<Integer> list2 = new ArrayList<>(list);
                list2.add(c);
                comb.add(list2);
                break;
            }
            List<Integer> list2 = new ArrayList<>(list);
            list2.add(c);
            helper(candidates, i, end, target-c, map, list2);
            for(int j=0; j<map.get(getCode(i, end, target-c)).size(); ++j) {
                comb.add(map.get(getCode(i, end, target-c)).get(j));
            }
        }
        map.put(code, comb);
    }

    public static void main(String args[])  {
        System.out.println("hello   `   ");

        Solution s = new Solution();
        List<List<Integer>> ret = s.combinationSum(new int[]{7, 3, 2}, 18);
        for(int i=0; i<ret.size(); ++i) {
            for(int j=0; j<ret.get(i).size(); ++j) {
                System.out.printf("%d ", ret.get(i).get(j));
            }
            System.out.println();
        }
    }
}

/*
2 3 6  7,  7

0-3-7 ?
0-3-5 ?
0-3 3 ?
0-3-1 0
1-3 3 1
 


*/