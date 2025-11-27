package org.example.merkle;

import com.google.common.hash.Hashing;

import java.nio.charset.StandardCharsets;

public abstract class Utils {

    public static String xorString(String s1, String s2) {
        if (s1.length() != s2.length()) {
            return null;
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < s1.length(); i++) {
            char c1 = s1.charAt(i);
            char c2 = s2.charAt(i);
            sb.append(c1 ^ c2);
        }
        return sb.toString();
    }

    public static String getHash(String s) {
        return Hashing.sha256()
                .hashString(s, StandardCharsets.UTF_8)
                .toString();
    }
}
