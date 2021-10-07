package com.zixiaoguo.cs635.assignment2.main;

import java.util.stream.IntStream;

/**
 * Author: Zixiao Guo
 * RedId: 822029189
 * CS635 Assignment 2
 * Section 2
 * 10/5/2021
 *
 * This is the class implements sort by redID strategy
 */

public class SortByRedID implements SortingStrategy{

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
    @Override
    public Boolean insertStudent(Student student, BTree.BTreeNode node, BTree bTree) {
        if (node.isEmpty()) {
            node.getStudents()[0] = student;
            //need to pass in the BTree instance to create a new node since node is an inner class
            node.getChildrenNode()[0] = bTree.new BTreeNode(node);
            node.getChildrenNode()[1] = bTree.new BTreeNode(node);
            bTree.incrementSize();
            return true;
        }
        BTree.BTreeNode position = search(bTree.getRootNode(), student);
        insertNode(position.getParentNode(), student, bTree.new BTreeNode(), bTree, bTree.getOrder());
        bTree.incrementSize();
        return true;
    }

    /**
     * Compare student objects with student entries in the node, insert into correct location and
     * attach additional child node, if size of the node is equal to order, spilit node
     * @param node node to insert
     * @param student student object to insert
     * @param extraChildNode extra child node comes with the new student entry
     * @param bTree reference of the btree
     * @param ORDER
     */
    @Override
    public void insertNode(BTree.BTreeNode node, Student student, BTree.BTreeNode extraChildNode, BTree bTree, int ORDER) {
        int valueIndex = 0;
        while(valueIndex < StudentInsertionHelper.getNotNullLength(node.getStudents()) && StudentInsertionHelper.compareStudentRedID(node, valueIndex, student) < 0) {
            valueIndex++;
        }
        node.setStudents(StudentInsertionHelper.insertStudentElement(node.getStudents(), student, valueIndex));

        //insert additional child node to fit the increase
        extraChildNode.setParentNode(node);
        node.setChildrenNode(StudentInsertionHelper.insertBtreeElement(node.getChildrenNode(), extraChildNode, valueIndex+1));

        if(StudentInsertionHelper.getNotNullLength(node.getStudents()) >  ORDER -1) {
            /*
             since this is an order 3 b-tree, when the new node need to be generated,
             the middle student entry get promoted, which index equals to M/2 = 1
             */
            int promoteIndex = ORDER /2;
            Student studentPromoted = node.getStudents()[promoteIndex];

            // instantiate a new node and moves the entries and child nodes into it
            BTree.BTreeNode rightNode = bTree.new BTreeNode();
            rightNode.setStudents(java.util.Arrays.stream(node.getStudents(), promoteIndex + 1, ORDER).toArray(Student[]::new)); //attach elements to new node
            rightNode.setChildrenNode(IntStream.range(promoteIndex+1, ORDER +1).mapToObj(i -> node.getChildrenNode()[i]).toArray(BTree.BTreeNode[]::new));
            for(BTree.BTreeNode rChild : rightNode.getChildrenNode()) {
                if(rChild!=null) {
                    rChild.setParentNode(rightNode);
                }
            }

            // remove previously assigned node, if the node is root node, generate new node as root
            node.setStudents(IntStream.range(0, promoteIndex).mapToObj(i -> node.getStudents()[i]).toArray(Student[]::new));
            node.setChildrenNode(IntStream.range(0, promoteIndex+1).mapToObj(i -> node.getChildrenNode()[i]).toArray(BTree.BTreeNode[]::new));
            if(node.getParentNode() == null) {
                node.setParentNode(bTree.new BTreeNode());
                node.getParentNode().getStudents()[0] = studentPromoted;
                node.getParentNode().getChildrenNode()[0] = node;
                node.getParentNode().getChildrenNode()[1] = rightNode;
                rightNode.setParentNode(node.getParentNode());
                return;
            }
            insertNode(node.getParentNode(), studentPromoted, rightNode, bTree, ORDER);
        }
    }

    /**
     * Recursively searches for the location and return the node to insert student
     * @param node
     * @param target
     * @return
     */
    @Override
    public BTree.BTreeNode search(BTree.BTreeNode node, Student target) {
        if (node.isEmpty()) {
            return node;
        }
        int valueIndex = 0;
        while (valueIndex < StudentInsertionHelper.getNotNullLength(node.getStudents()) &&
                StudentInsertionHelper.compareStudentRedID(node, valueIndex, target) <= 0) {
            if (StudentInsertionHelper.compareStudentRedID(node, valueIndex, target) == 0) {
                return node;
            }
            valueIndex++;
        }
        return search(node.getChildrenNode()[valueIndex], target);
    }
}
