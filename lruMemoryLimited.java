import java.util.HashMap;
import java.util.Map;

public class lruMemoryLimited {
    /*
     * Design a in-memory Cache solution to provide fast and efficient way of retrieving data and
     * meet below requirement.
     * 
     * Fixed memory footprints in terms of MBytes: Cache needs to have an upper bound of memory
     * usages in MBs Fast Access: Cache Insert and lookup operation should be fast, preferably O(1)
     * time. Replacement of Entry in case , Memory Limit is reached: A cache should have efficient
     * algorithm to evict the entry when memory is full.
     */

    class Node {
        public String key;
        public String value;
        public Node next;
        public Node pre;

        public Node(String key, String value) {
            this.key = key;
            this.value = value;
        }

        public int getByteSize() {
            return (this.key.length() + this.value.length()) * 8;
        }
    }

    Node head, tail;
    int memoryLimitInByte;
    int memoryInByte;
    Map<String, Node> map;

    public lruMemoryLimited(int memoryLimitInByte) {
        this.memoryLimitInByte = memoryLimitInByte;
        map = new HashMap<>();
    }

    public void set(String key, String value) {
        Node node = new Node(key, value);

        if (node.getByteSize() > memoryLimitInByte) {
            throw new IllegalArgumentException();
        }

        if (map.containsKey(key)) {
            memoryInByte -= map.get(key).getByteSize();
            deleteNode(map.get(key));
            map.remove(key);
        }

        while (tail != null && memoryInByte + node.getByteSize() > memoryLimitInByte) {
            memoryInByte -= tail.getByteSize();
            map.remove(tail.key);
            deleteNode(tail);
        }

        addToFront(node);
        map.put(key, node);
        memoryInByte += node.getByteSize();
    }

    public String get(String key) {
        if (!map.containsKey(key))
            return null;

        moveToFront(map.get(key));
        return map.get(key).value;
    }

    void deleteNode(Node node) {
        if (node == null)
            return;

        Node pre = node.pre;
        Node next = node.next;
        if (pre != null) {
            pre.next = next;
            if (next == null)
                tail = pre;
        } else {
            head = tail = next;
        }
        node.pre = node.next = null;
    }

    void addToFront(Node node) {
        if (node == null)
            return;

        if (head == null) {
            head = tail = node;
        } else {
            node.next = head;
            head.pre = node;
            head = node;
        }
    }


    void moveToFront(Node node) {
        if (node == head)
            return;
        deleteNode(node);
        addToFront(node);
    }
}
