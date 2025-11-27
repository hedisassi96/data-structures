package merkle;

import com.google.common.hash.Hashing;
import org.example.merkle.MerkleProofValidator;
import org.example.merkle.MerkleTree;
import org.example.merkle.SimpleMerkleTree;
import org.example.merkle.Utils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;
import java.util.List;

public class SimpleMerkleTreeTest {

    @Test
    void testRootOfSingleNodeTree() {
        final String value = "test";

        final String expectedHash = Hashing.sha256()
                .hashString(value, StandardCharsets.UTF_8)
                .toString();

        List<String> values = List.of(value);
        SimpleMerkleTree<String> tree = new SimpleMerkleTree<>(values);
        final MerkleTree.Node<String> root = tree.getRoot();

        Assertions.assertEquals(expectedHash, root.getHash());
    }

    @Test
    void testRootOfTwoNodesTree() {
        final String value1 = "test1";
        final String value2 = "test2";

        final String expectedHash1 = Utils.getHash(value1);
        final String expectedHash2 = Utils.getHash(value2);

        final String jointHash = Utils.xorString(expectedHash1, expectedHash2);
        final String expectedHash = Utils.getHash(jointHash);

        List<String> values = List.of(value1, value2);
        SimpleMerkleTree<String> tree = new SimpleMerkleTree<>(values);
        final MerkleTree.Node<String> root = tree.getRoot();

        Assertions.assertEquals(expectedHash, root.getHash());
    }

    @Test
    void testRootOfThreeNodesTree() {
        final String value1 = "test1";
        final String value2 = "test2";
        final String value3 = "test3";

        final String expectedHash1 = Utils.getHash(value1);
        final String expectedHash2 = Utils.getHash(value2);
        final String expectedHash3 = Utils.getHash(value3);
        final String expectedHash4 = Utils.getHash(value3);

        final String jointHash12 = Utils.xorString(expectedHash1, expectedHash2);
        final String jointHash34 = Utils.xorString(expectedHash3, expectedHash4);

        final String expectedHash12 = Utils.getHash(jointHash12);
        final String expectedHash34 = Utils.getHash(jointHash34);

        final String jointHash = Utils.xorString(expectedHash12, expectedHash34);
        final String expectedHash = Utils.getHash(jointHash);

        List<String> values = List.of(value1, value2, value3);
        SimpleMerkleTree<String> tree = new SimpleMerkleTree<>(values);
        final MerkleTree.Node<String> root = tree.getRoot();

        Assertions.assertEquals(expectedHash, root.getHash());
    }

    @Test
    void testProofWithTwoNodes() {
        final String value1 = "test1";
        final String value2 = "test2";

        List<String> values = List.of(value1, value2);
        SimpleMerkleTree<String> tree = new SimpleMerkleTree<>(values);

        final SimpleMerkleTree.MerkleProof proofForValue1 = tree.getProof(value1);
        Assertions.assertEquals(1, proofForValue1.hashes().size());

        Assertions.assertTrue(MerkleProofValidator.isValid(proofForValue1, value1, tree.getRoot()));
    }

    @Test
    void proofWithUnknownValueShouldReturnNull() {
        final String value1 = "test1";
        final String value2 = "test2";

        List<String> values = List.of(value1, value2);
        SimpleMerkleTree<String> tree = new SimpleMerkleTree<>(values);

        final SimpleMerkleTree.MerkleProof proofForRandomValue= tree.getProof("random value");
        Assertions.assertNull(proofForRandomValue);
    }

    @Test
    void proofForNonMatchingValueShouldBeInvalid() {
        final String value1 = "test1";
        final String value2 = "test2";

        List<String> values = List.of(value1, value2);
        SimpleMerkleTree<String> tree = new SimpleMerkleTree<>(values);

        final SimpleMerkleTree.MerkleProof proofForValue2 = tree.getProof(value2);
        Assertions.assertNotNull(proofForValue2);

        Assertions.assertFalse(MerkleProofValidator.isValid(proofForValue2, value1, tree.getRoot()));
        Assertions.assertFalse(MerkleProofValidator.isValid(proofForValue2, "unknown", tree.getRoot()));

        final MerkleTree.Node<String> fakeRoot = new MerkleTree.Node<String>(null, null, "random root hash");
        Assertions.assertFalse(MerkleProofValidator.isValid(proofForValue2, value2, fakeRoot));

        Assertions.assertTrue(MerkleProofValidator.isValid(proofForValue2, value2, tree.getRoot()));
    }

    @Test
    void testProofWithThreeNodes() {
        final String value1 = "test1";
        final String value2 = "test2";
        final String value3 = "test3";

        List<String> values = List.of(value1, value2, value3);
        SimpleMerkleTree<String> tree = new SimpleMerkleTree<>(values);

        final SimpleMerkleTree.MerkleProof proofForValue1 = tree.getProof(value1);
        final SimpleMerkleTree.MerkleProof proofForValue2 = tree.getProof(value2);
        final SimpleMerkleTree.MerkleProof proofForValue3 = tree.getProof(value3);

        Assertions.assertEquals(2, proofForValue1.hashes().size());
        Assertions.assertEquals(2, proofForValue2.hashes().size());
        Assertions.assertEquals(2, proofForValue3.hashes().size());

        Assertions.assertTrue(MerkleProofValidator.isValid(proofForValue1, value1, tree.getRoot()));
        Assertions.assertTrue(MerkleProofValidator.isValid(proofForValue2, value2, tree.getRoot()));
        Assertions.assertTrue(MerkleProofValidator.isValid(proofForValue3, value3, tree.getRoot()));
    }
}
