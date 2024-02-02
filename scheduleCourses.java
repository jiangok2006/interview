import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;


class Solution3 {
    static class Node {
        int val;
        List<Integer> dependOn;
        List<Integer> dependedBy;

        public Node(int val) {
            this.val = val;
            this.dependOn = new ArrayList<>();
            this.dependedBy = new ArrayList<>();
        }

        public int getVal() {
            return this.val;
        }

        public List<Integer> getDependOn() {
            return this.dependOn;
        }

        public List<Integer> getDependedBy() {
            return this.dependedBy;
        }
    }

    public static boolean canFinish(int numCourses, int[][] prerequisites) {
        Map<Integer, Node> map = new HashMap<>();
        Set<Integer> noDep = new HashSet<>();

        for(int i=0; i<prerequisites.length; ++i) {
            int[] pair = prerequisites[i];
            int dor = pair[0];
            int dee = pair[1];
            if(!map.containsKey(dor)) {
                map.put(dor, new Node(dor));
            }
            if(noDep.contains(dor))
                noDep.remove(dor);

            if(!map.containsKey(dee)) {
                map.put(dee, new Node(dee));
                noDep.add(dee);
            }
            map.get(dor).getDependOn().add(dee);
            map.get(dee).getDependedBy().add(dor);
        }

        int total = 0;
        for(int i=0; i<numCourses; ++i) {
            if(!map.containsKey(i)) {
                total++;
            }
        }

        while(noDep.size()>0) {
            Set<Integer> nextNoDep = new HashSet<>();
            Iterator<Integer> itr = noDep.iterator();
            while(itr.hasNext()) {
                Integer cur = itr.next();
                total++;
                Node node = map.get(cur);
                for(int i=0; i<node.getDependedBy().size(); ++i) {
                    int child = node.getDependedBy().get(i);
                    Node ch = map.get(child);
                    ch.getDependOn().remove(cur);
                    if(ch.getDependOn().size() == 0)
                        nextNoDep.add(child);
                }
            }
            noDep = nextNoDep;
        }
        
        return total == numCourses;
    }

    public static void main(String args[])  {
        int[][] input = new int[1][];
        input[0] = new int[]{0, 1};
        boolean ret = canFinish(2, input);
        System.out.println(ret);
        }
}