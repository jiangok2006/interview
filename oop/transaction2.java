package oop;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

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
public class transaction2 {
    enum OpType {
        SET, GET, DELETE
    }

    class Node {
        public OpType op;
        public String key;
        public String val;

        public Node(OpType op, String key, String val) {
            this.op = op;
            this.key = key;
            this.val = val;
        }
    }

    Map<String, String> map;
    Map<String, String> mapStage;
    Stack<Node> changes;

    public transaction2() {
        this.map = new HashMap<>();
    }

    String get(String k) {
        return map.get(k);
    }

    void set(String k, String v) {
        this.changes.add(new Node(OpType.SET, k, v));
    }

    void delete(String k) {
        this.changes.add(new Node(OpType.DELETE, k, null));

    }

    void begin() {
        this.mapStage = new HashMap<>();
        for (Map.Entry<String, String> e : map.entrySet()) {
            String k = new String(e.getKey());
            String v = new String(e.getValue());
            mapStage.put(k, v);
        }

        changes = new Stack<>();
    }

    void commit() {
        for (int i = 0; i < changes.size(); ++i) {
            Node c = changes.get(i);
            switch (c.op) {
                case SET:
                    mapStage.put(c.key, c.val);
                    break;
                case DELETE:
                    mapStage.remove(c.key);
                    break;
                default:
                    throw new IllegalArgumentException();
            }
        }
        map = mapStage;
    }

    void rollback() {

    }

    public static void main(String args[]) {
        transaction2 tra = new transaction2();
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
