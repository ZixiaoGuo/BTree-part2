package com.zixiaoguo.cs635.assignment2.main;

/**
 * Author: Zixiao Guo
 * RedId: 822029189
 * CS635 Assignment 2
 * Section 2
 * 10/5/2021
 *
 * This is the interface for sorting strategy
 */

public interface SortingStrategy {

    /**
     * Check if the root is empty first, if empty, initialize children nodes
     * and assign first element to the root
     * If it is not empty, call the search function to find the location to insert node and call the insertNode
     * function to insert from the location's parent node
     * @param student student object to insert into tree
     * @param node root node of the tree
     * @param bTree the reference of btree
     * @return
     */
    public Boolean insertStudent(Student student, BTree.BTreeNode node, BTree bTree);

    /**
     * Compare student objects with student entries in the node, insert into correct location and
     * attach additional child node, if size of the node is equal to order, spilit node
     * @param node node to insert
     * @param student student object to insert
     * @param extraChildNode extra child node comes with the new student entry
     * @param bTree reference of the btree
     * @param ORDER
     */
    void insertNode(BTree.BTreeNode node, Student student, BTree.BTreeNode extraChildNode,BTree bTree, int ORDER);

    /**
     * Recursively searches for the location and return the node to insert student
     * @param rootNode
     * @param target
     * @return
     */
    public BTree.BTreeNode search(BTree.BTreeNode rootNode, Student target);
}
