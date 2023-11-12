/*
 * Ant Group
 * Copyright (c) 2004-2023 All Rights Reserved.
 */
package str;

import java.util.ArrayList;
import java.util.List;

/**
 * 最长前缀实现 KMP 算法
 *
 * @author sakura
 * @version KMPMatch.java, v 0.1 2023年10月10日 10:57 sakura
 */
public class KMPMatch {
    /**
     * 获取字符串中的每个最长前缀
     *
     * @param str 字符串
     * @return 每个最长前缀长度
     */
    int[] prefixFunction(String str) {
        int[] preLength = new int[str.length()];
        for (int i = 1; i < str.length(); i++) {
            int j = preLength[i - 1];
            while (j > 0 && str.charAt(i) != str.charAt(j)) {
                j = preLength[j - 1];
            }
            if (str.charAt(i) == str.charAt(j)) {
                j++;
            }
            preLength[i] = j;
        }
        return preLength;
    }

    /**
     * 匹配字符串中 str 出现 pattern 的下标
     *
     * @param str     字符串
     * @param pattern 模式
     * @return 返回值
     */
    List<Integer> matchIndex(String str, String pattern) {
        String cur = pattern + "#" + str;
        int[] preLen = prefixFunction(cur);
        int size1 = str.length();
        int size2 = pattern.length();
        List<Integer> result = new ArrayList<>();
        for (int i = size2 + 1; i <= size1 + size2; i++) {
            if (preLen[i] == size2) {
                result.add(i - 2 * size2);
            }
        }
        return result;
    }

}