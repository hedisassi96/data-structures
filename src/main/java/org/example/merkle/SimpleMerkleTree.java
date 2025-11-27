package org.example.merkle;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class SimpleMerkleTree<T> implements MerkleTree<T> {

    private final Node<T> root;

    public SimpleMerkleTree(List<T> leaves) {
        this.root = buildTree(leaves);
    }

    @Override
    public Node<T> getRoot() {
        return this.root;
    }

    @Override
    public MerkleProof getProof(T element) {

        final Leaf<T> leaf = findLeaf(element);
        if (leaf == null) {
            return null;
        }
        final List<String> hashes = getHashesForProof(leaf);
        return new MerkleProof(hashes);
    }

    private List<String> getHashesForProof(final Leaf<T> leaf) {
        final List<String> hashes = new ArrayList<>();
        Node<T> current = leaf;

        while(current.getParent() != null) {
            final Node<T> parent = current.getParent();
            if (parent.left == current) {
                hashes.add(parent.right.getHash());
            } else {
                hashes.add(parent.left.getHash());
            }
            current = parent;
        }

        return hashes;
    }

    private Leaf<T> findLeaf(T element) {
        final Stack<Node<T>> stack = new Stack<>();
        stack.add(this.root);

        while(!stack.isEmpty()) {
            final Node<T> current = stack.pop();
            switch (current) {
                case Leaf<T> l:
                    if (l.getValue().equals(element)) {
                        return l;
                    }
                    break;
                case Node<T> ignored:
                    if(current.right != null) {
                        stack.push(current.right);
                    }
                    if(current.left != null) {
                        stack.push(current.left);
                    }
                    break;
            }
        }

        return null;
    }

    private Node<T> buildTree(List<T> leaves) {
        List<? extends Node<T>> nodes = leaves.stream()
                .map(e -> new Leaf<>(e, Utils.getHash(e.toString())))
                .toList();

        while(nodes.size() > 1) {
            nodes = hashPairs(nodes);
        }
        return nodes.getFirst();
    }

    private List<? extends Node<T>> hashPairs(List<? extends Node<T>> nodes) {
        if (nodes.size() == 1) {
            return nodes;
        }

        final List<Node<T>> newNodes = new ArrayList<>();

        for (int i = 0; i < nodes.size(); i += 2) {
            final Node<T> left = nodes.get(i);
            final Node<T> right;
            if (nodes.size() > i+1) {
                right = nodes.get(i+1);
            } else {
                // duplicate node
                right = new Node<>(null, null, left.getHash());
            }

            final Node<T> parentNode = pairNodes(left, right);
            left.setParent(parentNode);
            right.setParent(parentNode);
            newNodes.add(parentNode);
        }

        return newNodes;
    }

    private Node<T> pairNodes(final  Node<T> left, final Node<T> right) {
        final String jointHash =  Utils.xorString(left.getHash(),right.getHash());
        final String hash = Utils.getHash(jointHash);
        return new Node<>(left, right, hash);
    }

}
