import java.util.HashMap;
import java.util.Map;

public class divideString {
    public int divide(String s) {
        if (s == null || s.length() <= 1)
            return 0;

        Map<Character, Integer> left = new HashMap<>();
        Map<Character, Integer> right = new HashMap<>();

        int cnt = 0;

        for (int i = 0; i < s.length(); ++i) {
            char c = s.charAt(i);
            if (!right.containsKey(c))
                right.put(c, 0);
            right.put(c, right.get(c) + 1);
        }

        for (int i = 1; i < s.length(); ++i) {
            char c = s.charAt(i - 1);
            if (!left.containsKey(c)) {
                left.put(c, 0);
            }
            left.put(c, left.get(c) + 1);

            if (right.get(c) == 1) {
                right.remove(c);
            } else {
                right.put(c, right.get(c) - 1);
            }

            if (left.size() == right.size())
                cnt++;
        }
        return cnt;
    }

    public static void main(String args[]) {
        System.out.println(new divideString().divide("ababa"));
        System.out.println(new divideString().divide("abc"));
        System.out.println(new divideString().divide("aaaa"));

    }
}
