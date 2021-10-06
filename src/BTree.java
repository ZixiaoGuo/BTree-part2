import java.util.AbstractSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.IntFunction;
import java.util.stream.IntStream;

public class BTree extends AbstractSet {

    private static final int ORDER = 3;
    private int size;
    private BTreeNode rootNode;
    private SortingStrategy strategy;

    @Override
    public String toString() {
        ArrayList<Student> outputString = new ArrayList<>();
        for (Object student: getBTree()) {
            outputString.add((Student) student);

        }
        return outputString.toString();
    }


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

    public BTree() {
        size = 0;
        rootNode = new BTreeNode();
        strategy = new SortByName();
    }

    private BTree getBTree() {
        return this;
    }

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
                Student result = searcher.findSpecificStudent(currentIndex, getBTree());
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

    @Override
    public void forEach(Consumer action) {
        Objects.requireNonNull(action);
        StudentSearcher searcher= new StudentSearcher();
        int index = getBTree().size();
        for (int i = index; i > 0; i--) {
            Object student = searcher.findSpecificStudent(i,getBTree());
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
            return (this.getChildrenNode()[0].isEmpty() && !this.isRoot());
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
