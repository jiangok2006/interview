import java.util.Stack;

public class specialStack {
    // https://www.geeksforgeeks.org/design-and-implement-special-stack-data-structure/

    Stack<Integer> mins;
    Stack<Integer> stack;
    int cap;

    public specialStack() {
        mins = new Stack<>();
        stack = new Stack<>();
        cap = 100;
    }


    void push(int a) {
        if(isFull())
            return;

        stack.push(a);
        if(mins.peek() >= a) {
            mins.push(a);
        }
    }

    int pop() {
        if(isEmpty())
            throw new IllegalArgumentException("stack is empty");
        
        int c = stack.pop();
        if(c == mins.peek())
            mins.pop();
        return c;
    }

    boolean isEmpty() {
        return stack.isEmpty();
    }

    boolean isFull() {
        return stack.size() == cap;
    }

    int getMin() {
        if(isEmpty())
            throw new IllegalArgumentException("stack is empty");
        return mins.peek();
    }
}
