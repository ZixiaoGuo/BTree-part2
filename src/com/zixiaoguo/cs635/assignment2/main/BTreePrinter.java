package com.zixiaoguo.cs635.assignment2.main;

import java.util.Arrays;

/**
 * Author: Zixiao Guo
 * RedId: 822029189
 * CS635 Assignment 2
 * Section 2
 * 10/5/2021
 *
 * This is the helper class to print out btree structure
 */
public class BTreePrinter {

    /**
     * Print the tree structure
     */
    public void print(BTree.BTreeNode node) {
        printNode(node, 0);
    }

    private void printNode(BTree.BTreeNode node, int depth) {
        StringBuilder sb = new StringBuilder();
        for(int i = 1; i < depth; i++) {
            sb.append("     ");
        }
        if(depth > 0) {
            sb.append("--  ");
        }
        if(node != null) {
            sb.append(Arrays.asList(node.getStudents()));
            System.out.println(sb);
            for(BTree.BTreeNode child : node.getChildrenNode()) {
                printNode(child, depth+1);
            }
        }

    }



}
