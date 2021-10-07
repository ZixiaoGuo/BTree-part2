package com.zixiaoguo.cs635.assignment2.main;

import java.util.AbstractSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;
import java.util.function.Consumer;

/**
 * Author: Zixiao Guo
 * RedId: 822029189
 * CS635 Assignment 2
 * Section 2
 * 10/5/2021
 *
 * This is the btree class with inner class for node
 */

/**
 * Since the node class is an inner class if com.zixiaoguo.cs635.assignment1.main.BTree, if I implement a generic interface,
 * my split node function will generate error that beyond my ability to fix, hence I
 * have to extend a non-generic collection class to solve this problem
 *
 */
public class BTree extends AbstractSet {

    private static final int ORDER = 3;
    private int size;
    private BTreeNode rootNode;
    private SortingStrategy strategy;

    /**
     * Use external iterator to acquire all the elements and convert them to string
     * @return String representation of this com.zixiaoguo.cs635.assignment1.main.BTree
     */
    @Override
    public String toString() {
        ArrayList<Student> outputString = new ArrayList<>();
        for (Object student: getBTree()) {
            outputString.add((Student) student);

        }
        return outputString.toString();
    }


    /**
     * Use external iterator to acquire all the objects, cast them to student then return
     * @return An array contains all the element of the com.zixiaoguo.cs635.assignment1.main.BTree
     */
    @Override
    public Student[] toArray() {
        ArrayList<Student> outputString = new ArrayList<>();
        for (Object student: getBTree()) {
            outputString.add((Student) student);
        }

        Student[] students = new Student[outputString.size()];
        for (int i = 0; i < students.length; i++) {
            students[i] = outputString.get(i);
        }
        return students;
    }

    /**
     * Constructor of com.zixiaoguo.cs635.assignment1.main.BTree class that accepts different strategies to arrange elements
     * @param sortingStrategy there are com.zixiaoguo.cs635.assignment1.main.SortByName and com.zixiaoguo.cs635.assignment1.main.SortByRedID strategies you can choose from
     */
    public BTree(SortingStrategy sortingStrategy) {
        size = 0;
        rootNode = new BTreeNode();
        strategy = sortingStrategy;
    }

    /**
     * This is used for methods from inner node class to get the reference of the tree
     * @return
     */
    private BTree getBTree() {
        return this;
    }

    /**
     * Constructs iterator to iterate through the tree
     * @return
     */
    @Override
    public Iterator iterator() {
        Iterator<Student> it = new Iterator<>() {
            private int currentIndex = 1;
            StudentSearcher searcher = new StudentSearcher();
            @Override
            public boolean hasNext() {
                return currentIndex < size + 1;
            }

            @Override
            public Student next() {
                Student result = searcher.getSpecificStudent(currentIndex, getBTree());
                currentIndex++;
                return result;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
        return it;
    }

    /**
     * Internal iterator of com.zixiaoguo.cs635.assignment1.main.BTree class
     * @param action
     */
    @Override
    public void forEach(Consumer action) {
        Objects.requireNonNull(action);
        StudentSearcher searcher= new StudentSearcher();
        int index = getBTree().size();
        for (int i = index; i > 0; i--) {
            Object student = searcher.getSpecificStudent(i,getBTree());
            action.accept(student);
        }

    }

    @Override
    public int size() {
        return size;
    }

    public int getOrder() {
        return ORDER;
    }

    protected void incrementSize() {
        size++;
    }

    //need to pass root to methods in testing class, hence need to make it public
    public BTreeNode getRootNode() {
        return rootNode;
    }

    /**
     * Achieve compatibility for previous project's code by calling functions in node class
     *
     * @param student com.zixiaoguo.cs635.assignment1.main.Student being inserted
     * @return
     */
    @Override
    public boolean add(Object student) {
        boolean result = strategy.insertStudent((Student) student, rootNode, this);
        rootNode = rootNode.getRoot();  // new root node may be generated during insertion
        return result;
    }

    public class BTreeNode {

        private Student[] students;       // entry of students in one node
        private BTreeNode parentNode;
        private BTreeNode[] childrenNode;

        public boolean isLeaf() {
            return (this.getChildrenNode()[0].isEmpty() && !this.isRoot());
        }

        public Student[] getStudents() {
            return students;
        }

        protected void setStudents(Student[] students){
            this.students = students;
        }

        public BTreeNode getParentNode() {
            return parentNode;
        }

        public BTreeNode[] getChildrenNode() {
            return childrenNode;
        }

        protected void setParentNode(BTreeNode parentNode) {
            this.parentNode = parentNode;
        }

        protected void setChildrenNode(BTreeNode[] childrenNode) {
            this.childrenNode = childrenNode;
        }

        /**
         * Creates new node of b-tree and instantiate its student entries and child nodes
         */
        protected BTreeNode() {
            this.students = new Student[3];
            this.childrenNode = new BTreeNode[4];
        }


        /**
         * Constructs a new node and assign the parent pointer to the node passed in
         * @param parent pointer of parent node
         */
        public BTreeNode(BTreeNode parent) {
            this();
            this.parentNode = parent;
        }

        /**
         * Finds the root node
         * @return the root node
         */
        private BTreeNode getRoot() {
            BTreeNode p = this;
            while(!p.isRoot()) {
                p = p.parentNode;
            }
            return p;
        }

        /**
         * Checks if a node is empty
         * @return returns true if node is empty, false otherwise
         */
        public boolean isEmpty() {
            if(students == null || StudentInsertionHelper.getNotNullLength(students) == 0) {
                return true;
            }
            return false;
        }

        /**
         * Check if current node is root
         * @return
         */
        public boolean isRoot() {
            return parentNode == null;
        }

    }

}
