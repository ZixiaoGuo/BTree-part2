import jdk.jshell.spi.ExecutionControl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class StudentSearcher {
    public ArrayList<Student> getHighGPAStudents(BTreeNode node, ArrayList<Student> students) {
        if (!node.isEmpty()) {
            for (BTreeNode childNode : node.getChildrenNode()) {
                getHighGPAStudents(childNode, students);
            }
            for (Student student : node.getStudents()) {
                if (student.getGpa() == 4.0f) {
                    students.add(student);
                }
            }

        }
        // sort students in lexicographical order
        Collections.sort(students, Comparator.comparing(Student::getName));
        // reverse the order
        Collections.reverse(students);
        return students;
    }
    public ArrayList<Student> getProbationStudents(BTreeNode node, ArrayList<Student> studentsOnProbation) {
        if (!node.isEmpty()) {
            for (BTreeNode childNode : node.getChildrenNode()) {
                getProbationStudents(childNode,studentsOnProbation);
            }
            for (Student student : node.getStudents()) {
                if (student.getGpa() < 2.85f) {
                    studentsOnProbation.add(student);
                }
            }

        }
        // sort students in lexicographical order
        Collections.sort(studentsOnProbation, Comparator.comparing(Student::getName));
        return studentsOnProbation;
    }

    /**
     * Find the specific element, throw exception if index out of bound
     * @param index index of student
     * @param node  node passed in
     * @return  student found
     * @throws ExecutionControl.NotImplementedException
     */
    public Student getSpecificStudents(int index, BTreeNode node) throws ExecutionControl.NotImplementedException {
        ArrayList<Student> students = new ArrayList<Student>();
        getAllStudents(node, students);
        if (index > students.size()) {
            throw new IndexOutOfBoundsException();
        }
        else {
            Collections.sort(students, Comparator.comparing(Student::getName));
            return students.get(index-1);
        }
    }
    /**
     * Collect all elements inside the tree in an arraylist
     * @param node  root node passed in
     * @param students  array of students to collect results
     * @return
     */
    private ArrayList<Student> getAllStudents(BTreeNode node, ArrayList<Student> students) {
        if (!node.isEmpty()) {
            for (BTreeNode childNode : node.getChildrenNode()) {
                getAllStudents(childNode, students);
            }
            for (Student student : node.getStudents()) {
                students.add(student);
            }

        }
        return students;
    }

}
