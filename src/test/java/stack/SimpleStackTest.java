package stack;

import org.example.stack.SimpleStack;
import org.junit.jupiter.api.Test;

public class SimpleStackTest {

    @Test
    void testPushAndPopValue() {
        SimpleStack<Integer> stack = new SimpleStack<>();
        CacheTest.testPushAndPopValue(stack);
    }

    @Test
    void testLastInFirstOut() {
        SimpleStack<Integer> stack = new SimpleStack<>();
        CacheTest.testLastInFirstOut(stack);
    }

    @Test
    void testPeekDoesNotRemoveElement() {
        SimpleStack<Integer> stack = new SimpleStack<>();
        CacheTest.testPeekDoesNotRemoveElement(stack);
    }

    @Test
    void testEmptyStackReturnsNull() {
        SimpleStack<Integer> stack = new SimpleStack<>();
        CacheTest.testEmptyStackReturnsNull(stack);
    }
}
