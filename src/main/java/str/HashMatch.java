/*
 * Ant Group
 * Copyright (c) 2004-2023 All Rights Reserved.
 */
package str;


/**
 * 维护字符串区间哈希值
 * @author sakura
 * @version HashMatch.java, v 0.1 2023年10月10日 10:44 sakura
 */
public class HashMatch {
    int b = 31;
    long m = 1000000007;

    long[] pow;

    long[] hash;

    /**
     * 默认认为字符串从左到右是正的。
     *
     * @param s
     */
    public HashMatch(String s) {
        pow = new long[s.length() + 1];
        pow[0] = 1L;
        for (int i = 1; i < pow.length; i++) {
            pow[i] = (pow[i - 1] * b) % m;
        }
        hash = new long[s.length() + 1];
        for (int i = 0; i < s.length(); i++) {
            hash[i + 1] = ((hash[i] * b) + (s.charAt(i) - 'a' + 1)) % m;
        }
    }

    public long getHash(int l, int r) {
        return ((hash[r + 1] - hash[l] * pow[r - l + 1]) % m + m) % m;
    }
}