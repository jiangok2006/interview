import java.util.HashMap;
import java.util.Map;

public class firstUniqueUser {
    /*
     * amazon VO 3/14/2024
     * 
     * design an API to get the oldest visitor who only visited once.
     * 
     * login(john)
     * login(tom)
     * login(tom)
     * getOldestVisitor() -> john
     * 
     * login(john)
     * login(adam)
     * getOldestVisitor() -> adam
     * 
     */

    class Node {
        public String user;
        public Node pre;
        public Node next;
        public Node(String user) {
            this.user = user;
            pre = next = null;
        }
    }

     Map<String, Node> map;
     Node head, tail;

     public firstUniqueUser() {
        map = new HashMap<>();
        head = tail = null;
     }

     public void login(String user) {
        if(map.containsKey(user)) {
            // remove from list.
            removeFromList(user);
        } else {
            // add to list and map
            Node node = new Node(user);
            if(head == null) {
                head = tail = node;
            } else {
                tail.next = node;
                node.pre = tail;
                tail = tail.next;
            }
        }
     }

     public String getOldestVisitor() {
        if(head == null)
            return null;
        return head.user;
     }

     void removeFromList(String user) {
        Node node = map.get(user);
        Node pre = node.pre;
        Node next = node.next;

        if(pre == null) {
            head = next;
        } else {
            pre.next = next;
        }

        if(next == null)
            tail = pre;
        else
            next.pre = pre;

        node.pre = node.next = null;
     }
}
