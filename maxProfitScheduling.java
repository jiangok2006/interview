import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// https://leetcode.com/problems/maximum-profit-in-job-scheduling/description/
// Why this code still TLE even it uses memorization?
public class maxProfitScheduling {
    
    class Node implements Comparable<Node> {
        public int st;
        public int end;
        public int prof;
        public Node(int st, int end, int prof) {
            this.st = st;
            this.end = end;
            this.prof = prof;
        }

        @Override
        public int compareTo(Node other) {
            return this.st - other.st;
        }
    }

    public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        List<Node> list = new ArrayList<>();
        for(int i=0; i<startTime.length; ++i) 
            list.add(new Node(startTime[i], endTime[i], profit[i]));
        Collections.sort(list);
        int[] max = new int[startTime.length];
        for(int i=0; i<startTime.length; ++i)
            max[i] = -1;
        helper(list, 0, max);
        return max[0];
    }

    void helper(List<Node> list, int st, int[] max) {
        if(st > list.size()-1)
            return;

        if(max[st] != -1)
            return;
        
        // find the next non-overlap
        int i = st+1;
        while(i < list.size()) {
            if(list.get(st).end <= list.get(i).st)
                break;
            ++i;
        }

        int curMax = list.get(st).prof;
        if(i<list.size()) {
            helper(list, i, max);
            curMax = Math.max(curMax, max[i] + list.get(st).prof);
        }

        if(st < list.size()-1 && list.get(st+1).st < list.get(st).end) 
        {
            helper(list, st+1, max);
            curMax = Math.max(curMax, max[st+1]);
        }

        max[st] = curMax;
    }
}