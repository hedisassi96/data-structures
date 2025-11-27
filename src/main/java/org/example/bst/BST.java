package org.example.bst;


import lombok.Data;

import java.util.Stack;

public class BST<T extends Comparable<T>> {

    @Data
    private static class Node<T> {
        private Node<T> left;
        private Node<T> right;
        private T value;

        public Node(T value) {
            this.value = value;
        }
    }

    private Node<T> root;

    public BST() {
        this.root = null;
    }

    public boolean add(T element) {
        if (root == null) {
            this.root = new Node<>(element);
            return true;
        }
        final Stack<Node<T>> stack = new Stack<>();
        stack.add(this.root);

        while(!stack.isEmpty()) {
            final Node<T> currentNode = stack.pop();
            if (element.equals(currentNode.getValue())) {
                // value already in the tree
                return false;
            }

            if (element.compareTo(currentNode.getValue()) < 0) {
                // value should be inserted on the left
                if (currentNode.getLeft() == null) {
                    currentNode.left = new Node<>(element);
                    return true;
                }

                stack.add(currentNode.getLeft());
            } else {
                // value should be inserted on the right
                if (currentNode.getRight() == null) {
                    currentNode.right = new Node<>(element);
                    return true;
                }

                stack.add(currentNode.getRight());
            }
        }
        return false;
    }

    public boolean containsElement(T element) {
        if (this.root == null) {
            return false;
        }
        final Stack<Node<T>> stack = new Stack<>();
        stack.add(this.root);

        while(!stack.isEmpty()) {
            final Node<T> currentNode = stack.pop();
            if (element.equals(currentNode.getValue())) {
                // found the value
                return true;
            }

            if (element.compareTo(currentNode.getValue()) < 0) {
                // value should be on the left
                if (currentNode.getLeft() != null) {
                    stack.add(currentNode.getLeft());
                }
            } else {
                // value should be on the right
                if (currentNode.getRight() != null) {
                    stack.add(currentNode.getRight());
                }
            }
        }
        return false;
    }

    public boolean remove(T element) {
        if (this.root == null) {
            return false;
        }

        final Stack<Node<T>> stack = new Stack<>();
        stack.add(this.root);

        Node<T> previous = null;

        while(!stack.isEmpty()) {
            final Node<T> currentNode = stack.pop();
            if (element.equals(currentNode.getValue())) {
                // found the value
                if (isLeaf(currentNode)) {
                    if (previous == null) {
                        // we are removing the root
                        this.root = null;
                        return true;
                    }

                    if (previous.getLeft() == currentNode) {
                        previous.left = null;
                        return true;
                    } else {
                        previous.right = null;
                        return true;
                    }
                }

                if (currentNode.getLeft() == null) {
                    currentNode.value = currentNode.getRight().getValue();
                    return true;
                }
                if (currentNode.getRight() == null) {
                    currentNode.value = currentNode.getLeft().getValue();
                    return true;
                }

                replaceWithSuccessor(currentNode);
                return true;
            }

            if (element.compareTo(currentNode.getValue()) < 0) {
                // value should be on the left
                if (currentNode.getLeft() != null) {
                    stack.add(currentNode.getLeft());
                }
            } else {
                // value should be on the right
                if (currentNode.getRight() != null) {
                    stack.add(currentNode.getRight());
                }
            }

            previous = currentNode;
        }
        return false;
    }

    private void replaceWithSuccessor(Node<T> root) {
        if (root.getRight().getLeft() == null) {
            root.value = root.getRight().getValue();
            root.right = root.getRight().getRight();
            return;
        }
        Node<T> previous = root.getRight();
        Node<T> current = root.getRight().getLeft();
        while(current.getLeft() != null) {
            previous = current;
            current = current.getLeft();
        }

        root.value = current.getValue();
        previous.left = current.getRight();
    }

    private boolean isLeaf(Node<T> node) {
        return node.getRight() == null && node.getLeft() == null;
    }
}
