package oop;

import java.util.HashMap;
import java.util.Map;

/*
 * https://leetcode.com/discuss/interview-question/542850/postmates-senior-software-engg-sf-hq-mar-
 * 2020-reject
 * 
 * Implement (code) a Key value store with transactions. Write test cases
 * 
 * Following api to be supported: Set Get Delete
 * 
 * for transactions Begin Commit Rollback
 */
public class transaction1 {
    Map<String, String> map;
    Map<String, String> mapStage;

    public transaction1() {
        this.map = new HashMap<>();
    }

    String get(String k) {
        return map.get(k);
    }

    void set(String k, String v) {
        this.mapStage.put(k, v);
    }

    void delete(String k) {
        this.mapStage.remove(k);
    }

    void begin() {
        // deep copy
        mapStage = new HashMap<>();
        for (Map.Entry<String, String> e : map.entrySet()) {
            String k = new String(e.getKey());
            String v = new String(e.getValue());
            mapStage.put(k, v);
        }
    }

    void commit() {
        map = mapStage;
    }

    void rollback() {

    }

    public static void main(String args[]) {
        transaction1 tra = new transaction1();
        tra.begin();
        tra.set("k1", "v1");
        tra.set("k1", "v3");
        tra.commit();

        assert (tra.get("k1").equals("v3"));

        tra.begin();
        tra.set("k1", "v4");
        tra.rollback();

        assert (tra.get("k1").equals("v3"));
    }
}
