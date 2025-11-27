package BST;

import org.example.bst.BST;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BSTTest {

    @Test
    void testInsertionThenSearch() {
        final BST<Integer> tree = new BST<>();
        Assertions.assertTrue(tree.add(1));
        Assertions.assertTrue(tree.containsElement(1));
        Assertions.assertFalse(tree.containsElement(2));

        Assertions.assertTrue(tree.remove(1));
        Assertions.assertFalse(tree.containsElement(1));
    }

    @Test
    void testInsertionTwice() {
        final BST<Integer> tree = new BST<>();
        Assertions.assertTrue(tree.add(1));
        Assertions.assertFalse(tree.add(1));
        Assertions.assertTrue(tree.containsElement(1));
    }

    @Test
    void testDeletionTwice() {
        final BST<Integer> tree = new BST<>();
        Assertions.assertTrue(tree.add(1));
        Assertions.assertTrue(tree.remove(1));
        Assertions.assertFalse(tree.remove(1));
    }

    @Test
    void testSearchEmptyTree() {
        final BST<Integer> tree = new BST<>();
        Assertions.assertFalse(tree.containsElement(1));
        Assertions.assertFalse(tree.remove(1));
    }

    @Test
    void testRemoveRoot() {
        final BST<Integer> tree = new BST<>();
        tree.add(6);
        tree.add(4);
        tree.add(14);
        tree.add(10);
        tree.add(12);

        Assertions.assertTrue(tree.remove(6));
        Assertions.assertTrue(tree.remove(10));
        Assertions.assertTrue(tree.remove(4));
        Assertions.assertTrue(tree.remove(12));
        Assertions.assertTrue(tree.containsElement(14));
        Assertions.assertTrue(tree.remove(14));
    }

}
