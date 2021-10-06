import java.util.AbstractSet;
import java.util.Iterator;
import java.util.stream.IntStream;

public class BTree extends AbstractSet {

    private static final int ORDER = 3;
    private int size;
    private BTreeNode rootNode;
    private SortingStrategy strategy;

    @Override
    public String toString() {
        //TODO: placeholder, needs to be changed
        return super.toString();
    }

    public BTree() {
        size = 0;
        rootNode = new BTreeNode();
        strategy = new SortByName();
    }

    @Override
    public Iterator iterator() {
        Iterator<BTreeNode> it = new Iterator<>() {
            @Override
            public boolean hasNext() {
                return false;
            }

            @Override
            public BTreeNode next() {
                return null;
            }
        };
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    public int getOrder() {
        return ORDER;
    }

    public void incrementSize() {
        size++;
    }

    public BTreeNode getRootNode() {
        return rootNode;
    }


    /**
     * Using adapter pattern to achieve compatibility for previous project's code
     * New root node may be generated during insertion, hence need find new root
     * @param student Student being inserted
     * @return whether insertion is successful
     */
    public boolean insertStudent(Student student) {
        //rootNode.insertStudent(student);
        //rootNode = rootNode.getRoot();  //new root node may be generated during insertion
        strategy.insertStudent(student, rootNode, this);
        rootNode = rootNode.getRoot();
        return true;
    }

    public class BTreeNode {

        //private static final int ORDER = 3;
        private Student[] students;       // entry of students in one node
        private BTreeNode parentNode;
        private BTreeNode[] childrenNode;

        public boolean isLeaf() {
            return (this.getChildrenNode()[0].isEmpty() && !this.isRoot()); //TODO: probably not need isroot here
        }

        public Student[] getStudents() {
            return students;
        }

        public void setStudents(Student[] students){
            this.students = students;
        }

        public BTreeNode getParentNode() {
            return parentNode;
        }

        public BTreeNode[] getChildrenNode() {
            return childrenNode;
        }

        public void setParentNode(BTreeNode parentNode) {
            this.parentNode = parentNode;
        }

        public void setChildrenNode(BTreeNode[] childrenNode) {
            this.childrenNode = childrenNode;
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
            while(valueIndex < StudentInsertionHelper.getNotNullLength(students) &&
                    StudentInsertionHelper.compareStudentNames(this, valueIndex, target) <= 0) {
                if(StudentInsertionHelper.compareStudentNames(this, valueIndex, target) == 0) {
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
            if(students == null || StudentInsertionHelper.getNotNullLength(students) == 0) {
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

}
