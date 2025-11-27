package org.example.merkle;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

public interface MerkleTree <T> {

    Node<T> getRoot();
    MerkleProof getProof(T leaf);

    @Data
    sealed class Node<T> permits MerkleTree.Leaf {
        public Node(Node<T> left, Node<T> right, String hash) {
            this.left = left;
            this.right = right;
            this.hash = hash;
        }
        protected Node<T> left;
        protected Node<T> right;
        protected String hash;
        protected Node<T> parent;
    }

    @Data
    final class Leaf<T> extends Node<T> {

        private T value;

        public Leaf(T value, String hash) {
            super(null, null, hash);
            this.value = value;
        }
    }

    record MerkleProof(List<String> hashes) {}
}
