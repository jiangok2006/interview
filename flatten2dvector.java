import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class flatten2dvector implements Iterator<Integer> {
    List<List<Integer>> vec2d;
    Iterator<List<Integer>> xit;
    Iterator<Integer> yit;

    public flatten2dvector(List<List<Integer>> vec2d) {
        this.vec2d = vec2d;
        advance();
    }

    void advance() {
        if (vec2d == null)
            return;

        if (xit == null)
            xit = vec2d.iterator();

        if (yit == null || !yit.hasNext()) {
            while (xit.hasNext()) {
                yit = xit.next().iterator();
                if (yit.hasNext())
                    break;
            }
        }
    }

    @Override
    public boolean hasNext() {
        return yit != null && yit.hasNext();
    }

    @Override
    public Integer next() {
        if (!hasNext())
            return null;
        int ret = yit.next();
        advance();
        return ret;
    }

    @Override
    public void remove() {
        // java's iterator next() is followed by remove() internally.
        // no need to call iterator.remove();
        next();
    }

    public static void main(String args[]) {
        List<Integer> a1 = Arrays.asList(new Integer[] {});
        List<Integer> a2 = Arrays.asList(new Integer[] {1, 2, 3});
        List<Integer> a3 = Arrays.asList(new Integer[] {4, 5});
        List<Integer> a4 = Arrays.asList(new Integer[] {});
        List<Integer> a5 = Arrays.asList(new Integer[] {});
        List<Integer> a6 = Arrays.asList(new Integer[] {6});
        List<Integer> a7 = Arrays.asList(new Integer[] {7, 8});
        List<Integer> a8 = Arrays.asList(new Integer[] {});
        List<Integer> a9 = Arrays.asList(new Integer[] {9});
        List<Integer> a10 = Arrays.asList(new Integer[] {10});
        List<Integer> a11 = Arrays.asList(new Integer[] {});

        List<List<Integer>> ll = new ArrayList<>();
        ll.add(a1);
        ll.add(a2);
        ll.add(a3);
        ll.add(a4);
        ll.add(a5);
        ll.add(a6);
        ll.add(a7);
        ll.add(a8);
        ll.add(a9);
        ll.add(a10);
        ll.add(a11);

        flatten2dvector f = new flatten2dvector(ll);
        // while (f.hasNext()) {
        // System.out.printf("%d,", f.next());
        // }
        // System.out.println();

        int i = 1;
        while (f.hasNext()) {
            if (i % 2 == 0) {
                f.remove();
            } else {
                System.out.printf("%d,", f.next());
            }
            i++;
        }
        System.out.println();
    }

}
