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

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = new int[]{1,2,3,4,5};
        System.out.println(solution.maximumStrongPairXor(nums));
    }

    static class TrieNode {
        TrieNode[] children;

        int cnt;


        TrieNode() {
            children = new TrieNode[2];
        }
    }

    static class Tree {
        private final static int MAX_BIT = 31;
        TrieNode root;

        Tree() {
            root = new TrieNode();
        }

        public void add(int num) {
            TrieNode cur = root;
            for (int i = MAX_BIT; i >= 0; i--) {
                int bit = (num >> i) & 1;
                if (cur.children[bit] == null) {
                    cur.children[bit] = new TrieNode();
                }
                cur = cur.children[bit];
                cur.cnt++;
            }
        }

        public void remove(int num) {
            TrieNode cur = root;
            for (int i = MAX_BIT; i >= 0; i--) {
                int bit = (num >> i) & 1;
                cur.cnt--;
                cur = cur.children[bit];
            }
        }

        public int maxXor(int num) {
            TrieNode cur = root;
            int xor = 0;
            for (int i = MAX_BIT; i >= 0; i--) {
                int bit = (num >> i) & 1;
                if (cur.children[bit ^ 1] != null && cur.children[bit ^ 1].cnt > 0) {
                    xor += (1 << i);
                    cur = cur.children[bit ^ 1];
                } else {
                    cur = cur.children[bit];
                }
            }
            return xor;
        }
    }

    public int maximumStrongPairXor(int[] nums) {
        Tree tree = new Tree();
        Arrays.sort(nums);
        int right = 0;
        int res = 0;
        for (int num : nums) {
            while (right < nums.length && nums[right] <= 2 * num) {
                tree.add(nums[right]);
                right++;
            }
            res = Math.max(res, tree.maxXor(num));
            tree.remove(num);
        }
        return res;
    }
}
