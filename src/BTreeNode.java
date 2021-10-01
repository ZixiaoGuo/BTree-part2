import java.util.ArrayList;

/**
 * Author: Zixiao Guo
 * RedId: 822029189
 * CS635 Assignment 1
 * Section 2
 * 9/7/2021
 * This is the class to implement the B-tree
 */
public class BTreeNode {

    private static final int Order = 3;
    //TODO: change arraylist to array
    private ArrayList<Student> students;       // entry of students in one node
    private BTreeNode parentNode;
    private ArrayList<BTreeNode> childrenNode;

    public ArrayList<Student> getStudents() {
        return students;
    }

    public BTreeNode getParentNode() {
        return parentNode;
    }

    public ArrayList<BTreeNode> getChildrenNode() {
        return childrenNode;
    }

    /**
     * Creates new node of b-tree and instantiate its student entries and child nodes
     */
    public BTreeNode() {
        this.students = new ArrayList<Student>();
        this.childrenNode = new ArrayList<BTreeNode>();
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
     * Insert student object to the b-tree, if node is empty, instantiate the children nodes
     * search from the root to determine which node to insert, then insert the node
     * if new node is generated during the insertion, we need to find the new root to return
     * @param student student object to insert into
     * @return returns the root node of the b-tree
     */
    public BTreeNode insertStudent(Student student) {
        if(isEmpty()) {
            students.add(student);
            childrenNode.add(new BTreeNode(this));
            childrenNode.add(new BTreeNode(this));
            return this;
        }
        BTreeNode p = getRoot().search(student);
        insertNode(p.parentNode, student, new BTreeNode());
        return getRoot();
    }

    /**
     * Insert student object into the student entries of the target node
     * we also need to add the additional child node to fit the increase of the node
     * @param node target node to insert student object
     * @param student student to insert into the target node
     * @param extraChildNode extra child node comes with the new student entry
     */
    private void insertNode(BTreeNode node, Student student, BTreeNode extraChildNode) {
        int valueIndex = 0;
        while(valueIndex < node.students.size() && StudentComparator.compareStudentNames(node, valueIndex, student)  < 0) {
            valueIndex++;
        }
        //TODO: make a function to compare student name for code abstraction
        node.students.add(valueIndex, student);

        //insert additional child node to fit the increase
        extraChildNode.parentNode = node;
        node.childrenNode.add(valueIndex+1, extraChildNode);

        // if size is greater or equal to order, need to generate new nodes
        if(node.students.size() > Order -1) {
            /*
             since this is an order 3 b-tree, when the new node need to be generated,
             the middle student entry get promoted, which index equals to M/2 = 1
             */
            int promoteIndex = Order /2;
            Student studentPromoted = node.students.get(promoteIndex);

            // instantiate a new node and moves the entries and child nodes into it
            BTreeNode rightNode = new BTreeNode();
            rightNode.students = new ArrayList(node.students.subList(promoteIndex+1, Order));
            rightNode.childrenNode = new ArrayList(node.childrenNode.subList(promoteIndex+1, Order +1));
            for(BTreeNode rChild : rightNode.childrenNode) {
                rChild.parentNode = rightNode;
            }

            // remove previously assigned node, if the node is root node, generate new node as root
            node.students = new ArrayList(node.students.subList(0, promoteIndex));
            node.childrenNode = new ArrayList(node.childrenNode.subList(0, promoteIndex+1));
            if(node.parentNode == null) {
                node.parentNode = new BTreeNode();
                node.parentNode.students.add(studentPromoted);
                node.parentNode.childrenNode.add(node);
                node.parentNode.childrenNode.add(rightNode);
                rightNode.parentNode = node.parentNode;
                return;
            }
            insertNode(node.parentNode, studentPromoted, rightNode);
        }
    }

    /**
     * Recursively search the target entry inside the b-tree
     * if found the target, return the current node, otherwise call the method on the children
     * if the result is empty node, return the empty node
     * @param target the target student object
     * @return returns the search result
     */
    public BTreeNode search(Student target) {
        if(isEmpty()) {
            return this;
        }
        int valueIndex = 0;
        while(valueIndex < students.size() && StudentComparator.compareStudentNames(this, valueIndex, target) <= 0) {
            if(StudentComparator.compareStudentNames(this, valueIndex, target) == 0) {
                return this;
            }
            valueIndex++;
        }
        return childrenNode.get(valueIndex).search(target);
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
    //TODO: maybe change to private later
    public boolean isEmpty() {
        if(students == null || students.size() == 0) {
            return true;
        }
        return false;
    }

    /**
     * Check if current node is root
     * @return
     */
    private boolean isRoot() {
        return parentNode == null;
    }

    /**
     * Print the tree structure
     */
    public void print() {
        printNode(this, 0);
    }

    //TODO: This method need to be deprecated
    private void printNode(BTreeNode node, int depth) {
        StringBuilder sb = new StringBuilder();
        for(int i = 1; i < depth; i++) {
            sb.append("     ");
        }
        if(depth > 0) {
            sb.append("--  ");
        }
        sb.append(node.students);
        System.out.println(sb.toString());  //TODO: need to change this line
        for(BTreeNode child : node.childrenNode) {
            printNode(child, depth+1);
        }
    }



}