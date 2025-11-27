package stack;

import org.example.stack.Stack;
import org.junit.jupiter.api.Assertions;

public abstract class CacheTest {

    static void testPushAndPopValue(Stack<Integer> stack) {
        stack.push(2);
        Assertions.assertEquals(1, stack.size());

        Assertions.assertEquals(2, stack.pop());
        Assertions.assertEquals(0, stack.size());
    }

    static void testLastInFirstOut(Stack<Integer> stack) {
        stack.push(2);
        stack.push(3);
        stack.push(20);

        Assertions.assertEquals(3, stack.size());

        Assertions.assertEquals(20, stack.pop());
    }

    static void testPeekDoesNotRemoveElement(Stack<Integer> stack) {
        stack.push(2);
        stack.push(3);
        stack.push(20);

        Assertions.assertEquals(3, stack.size());

        Assertions.assertEquals(20, stack.peek());
        Assertions.assertEquals(3, stack.size());
    }

    static void testEmptyStackReturnsNull(Stack<Integer> stack) {
        Assertions.assertEquals(0, stack.size());

        Assertions.assertNull(stack.peek());
        Assertions.assertNull(stack.pop());
    }
}
