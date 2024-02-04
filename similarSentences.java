import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* 
 * https://www.lintcode.com/problem/856/description
 * 
 * 
["great","acting","skills"]
["fine","drama","talent"]
[["great","good"],["drama","acting"],["skills","talent"],["good", "fine"]]

output: true,
expect: false.

It seems lintcode test is wrong.
 * 
 */

public class similarSentences {
    /**
     * @param words1: a list of string
     * @param words2: a list of string
     * @param pairs: a list of string pairs
     * @return: return a boolean, denote whether two sentences are similar or not
     */
    public boolean isSentenceSimilarity(String[] words1, String[] words2, List<List<String>> pairs) {
        // write your code her

        if(words1.length != words2.length) return false;

        int cnt = 0;
        Map<String, Integer> map = new HashMap<>();
        Map<Integer, Set<String>> map2 = new HashMap<>();

        for(int i=0; i<pairs.size(); ++i) {
            List<String> pair = pairs.get(i);
            String p0 = pair.get(0);
            String p1 = pair.get(1);

            if(!map.containsKey(p0) && !map.containsKey(p1)) {
                map.put(p0, cnt);
                map.put(p1, cnt);
                Set<String> words = new HashSet<>();
                words.add(p0);
                words.add(p1);
                map2.put(cnt, words);
                cnt++;
            } else if (!map.containsKey(p1)) {
                int m0 = map.get(p0);

                map.put(p1, m0);
                if(!map2.get(m0).contains(p1))
                    map2.get(m0).add(p1);
            } else if(!map.containsKey(p0)) {
                int m1 = map.get(p1);

                map.put(p0, m1);
                if(!map2.get(m1).contains(p0))
                    map2.get(m1).add(p0);
            } else {
                int m0 = map.get(p0);
                int m1 = map.get(p1);

                if(m0 != m1) {
                    int toRemove = m1;
                    Iterator<String> itr = map2.get(m1).iterator();
                    while(itr.hasNext()) {
                        String str = itr.next();

                        if(!map2.get(m0).contains(str))
                            map2.get(m0).add(str);
                        map.put(str, m0);
                    }

                    map2.remove(toRemove);
                }
            }
        }

        

        for(int i=0; i<words1.length; ++i) {
            if(words1[i].equals(words2[i])) continue;

            if(!map.containsKey(words1[i]) && !map.containsKey(words2[i]))
                throw new IllegalArgumentException("bad input");

            if(!map.containsKey(words1[i]) || !map.containsKey(words2[i])) 
                return false;

            // LESSON: map.get(words1[i]) == map.get(words2[i]) does NOT work as it compares Integer references (always unequal) instead of values.
            if(!map.get(words1[i]).equals(map.get(words2[i]))) { 
                return false;
            }
        }
        return true;
    }
}