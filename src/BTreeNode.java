import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * Author: Zixiao Guo
 * RedId: 822029189
 * CS635 Assignment 1
 * Section 2
 * 9/7/2021
 * This is the class to implement the B-tree
 */
public class BTreeNode<E> {

    private static final int Order = 3;
    private Student[] students;       // entry of students in one node
    private BTreeNode parentNode;
    private BTreeNode[] childrenNode;

    public Student[] getStudents() {
        return students;
    }

    public BTreeNode getParentNode() {
        return parentNode;
    }

    public BTreeNode[] getChildrenNode() {
        return childrenNode;
    }

    /**
     * Creates new node of b-tree and instantiate its student entries and child nodes
     */
    public BTreeNode() {
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
     * Insert student object to the b-tree, if node is empty, instantiate the children nodes
     * search from the root to determine which node to insert, then insert the node
     * if new node is generated during the insertion, we need to find the new root to return
     * @param student student object to insert into
     * @return returns the root node of the b-tree
     */
    public BTreeNode insertStudent(Student student) {
        if(isEmpty()) {
            students[0] = student;
            childrenNode[0] = new BTreeNode(this);
            childrenNode[1] = new BTreeNode(this);
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
        // TODO: error prone here
        while(valueIndex < StudentComparator.getNotNullLength(node.students) && StudentComparator.compareStudentNames(node, valueIndex, student)  < 0) {
            valueIndex++;
        }

        node.students = StudentComparator.addStudentElement(node.students, student, valueIndex);

        //insert additional child node to fit the increase
        extraChildNode.parentNode = node;
        node.childrenNode = StudentComparator.addElement(node.childrenNode, extraChildNode, valueIndex+1);

        // if size is greater or equal to order, need to generate new nodes
        if(StudentComparator.getNotNullLength(node.students) > Order -1) {
            /*
             since this is an order 3 b-tree, when the new node need to be generated,
             the middle student entry get promoted, which index equals to M/2 = 1
             */
            int promoteIndex = Order /2;
            Student studentPromoted = node.students[promoteIndex];

            // instantiate a new node and moves the entries and child nodes into it
            BTreeNode rightNode = new BTreeNode();
            rightNode.students = IntStream.range(promoteIndex+1, Order+1).mapToObj(i -> node.students[i]).toArray(Student[]::new); //attach elements to new node
            rightNode.childrenNode = IntStream.range(promoteIndex+1, Order+2).mapToObj(i -> node.childrenNode[i]).toArray(BTreeNode[]::new);
            for(BTreeNode rChild : rightNode.childrenNode) {
                if(rChild!=null) {
                    rChild.parentNode = rightNode;
                }
            }

            // remove previously assigned node, if the node is root node, generate new node as root
            node.students = IntStream.range(0, promoteIndex).mapToObj(i -> node.students[i]).toArray(Student[]::new);
            node.childrenNode = IntStream.range(0, promoteIndex+1).mapToObj(i -> node.childrenNode[i]).toArray(BTreeNode[]::new);
            if(node.parentNode == null) {
                node.parentNode = new BTreeNode();
                node.parentNode.students[0] = studentPromoted;
                node.parentNode.childrenNode[0] = node;
                node.parentNode.childrenNode[1] = rightNode;
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
        while(valueIndex < StudentComparator.getNotNullLength(students) && StudentComparator.compareStudentNames(this, valueIndex, target) <= 0) {
            if(StudentComparator.compareStudentNames(this, valueIndex, target) == 0) {
                return this;
            }
            valueIndex++;
        }
        return childrenNode[valueIndex].search(target);
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
        if(students == null || StudentComparator.getNotNullLength(students) == 0) {
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

}