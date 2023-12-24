/*
 * Ant Group
 * Copyright (c) 2004-2023 All Rights Reserved.
 */
package org.example;

import java.util.Arrays;

/**
 * @author sakura
 * @version Solution.java, v 0.1 2023年09月05日 20:59 sakura
 */
class Solution {
    public long minimumCost(String source, String target, char[] original, char[] changed, int[] cost) {
        int[][] graph = new int[26][26];
        for (int[] ints : graph) {
            Arrays.fill(ints, Integer.MAX_VALUE / 2);
        }
        for (int i = 0; i < 26; i++) {
            graph[i][i] = 0;
        }
        for (int i = 0; i < original.length; i++) {
            graph[original[i] - 'a'][changed[i] - 'a'] = Math.min(graph[original[i] - 'a'][changed[i] - 'a'], cost[i]);
        }
        int[][] minDistance = minDistanceWithN3(graph);
        long ans = 0;
        int m = source.length();
        for (int i = 0; i < m; i++) {
            long cur = minDistance[source.charAt(i) - 'a'][target.charAt(i) - 'a'];
            if (cur == Integer.MAX_VALUE / 2) {
                return -1;
            }
            ans += cur;
        }
        return ans;
    }

    public static int[][] minDistanceWithN3(int[][] graph) {
        int[][][] minDistance = new int[graph.length][graph.length][graph.length];
        for (int i = 0; i < graph.length; i++) {
            System.arraycopy(graph[i], 0, minDistance[0][i], 0, graph.length);
        }
        for (int k = 0; k < graph.length; k++) {
            for (int i = 0; i < graph.length; i++) {
                for (int j = 0; j < graph.length; j++) {
                    minDistance[k][i][j] = Math.min(minDistance[k - 1][i][j],
                            minDistance[k - 1][i][k] + minDistance[k - 1][k][j]);
                }
            }
        }
        return minDistance[graph.length - 1];
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.minimumCost("abcd", "acbe", new char[]{'a','b','c','c','e','d'}, new char[]{'b','c','b','e','b','e'}, new int[]{2,5,5,1,2,20}));
    }
}