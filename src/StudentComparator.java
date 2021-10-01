/**
 * This class is used to implement some helper functions to compare students
 */
public final class StudentComparator {
    private StudentComparator() {

    }

    /**
     * Compares student names based on lexicographical order
     * @param node the node containing students
     * @param indexOfStudentInNode  the specific index of student in a node
     * @param student   another student object used to compare with the student in the node
     * @return  the result of comparison
     */
    public static int compareStudentNames (BTreeNode node, int indexOfStudentInNode, Student student) {
        return node.getStudents().get(indexOfStudentInNode).getName().compareTo(student.getName());
    }
}
