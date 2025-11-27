package org.example.merkle;



public class MerkleProofValidator {

    public static <T> boolean isValid(MerkleTree.MerkleProof proof, T element, MerkleTree.Node<T> merkleRoot) {
        if (proof == null
        || proof.hashes() == null)  {
            return false;
        }

        String currentHash = Utils.getHash(element.toString());

        for (String hash : proof.hashes()) {
            final String jointHash = Utils.xorString(currentHash, hash);
            currentHash = Utils.getHash(jointHash);
        }

        return merkleRoot.getHash().equals(currentHash);
    }
}
