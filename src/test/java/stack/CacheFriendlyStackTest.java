package stack;

import org.example.stack.CacheFriendlyStack;
import org.junit.jupiter.api.Test;

public class CacheFriendlyStackTest {
    @Test
    void testPushAndPopValue() {
        CacheFriendlyStack<Integer> stack = new CacheFriendlyStack<>();
        CacheTest.testPushAndPopValue(stack);
    }

    @Test
    void testLastInFirstOut() {
        CacheFriendlyStack<Integer> stack = new CacheFriendlyStack<>();
        CacheTest.testLastInFirstOut(stack);
    }

    @Test
    void testPeekDoesNotRemoveElement() {
        CacheFriendlyStack<Integer> stack = new CacheFriendlyStack<>();
        CacheTest.testPeekDoesNotRemoveElement(stack);
    }

    @Test
    void testEmptyStackReturnsNull() {
        CacheFriendlyStack<Integer> stack = new CacheFriendlyStack<>();
        CacheTest.testEmptyStackReturnsNull(stack);
    }
}
