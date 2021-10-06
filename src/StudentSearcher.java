import jdk.jshell.spi.ExecutionControl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class StudentSearcher {

    private int counter;    //Variable used in findSpecificStudent method to act as a global counter
    private Student targetStudent;

    public ArrayList<Student> getHighGPAStudents(BTree.BTreeNode node, ArrayList<Student> students) {
        if (node!= null && !node.isEmpty()) {
            for (BTree.BTreeNode childNode : node.getChildrenNode()) {
                getHighGPAStudents(childNode, students);
            }
            for (Student student : node.getStudents()) {
                if (student!= null && student.getGpa() == 4.0f) {
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
    public ArrayList<Student> getProbationStudents(BTree.BTreeNode node, ArrayList<Student> studentsOnProbation) {
        if (node!= null && !node.isEmpty()) {
            for (BTree.BTreeNode childNode : node.getChildrenNode()) {
                getProbationStudents(childNode,studentsOnProbation);
            }
            for (Student student : node.getStudents()) {
                if (student!= null && student.getGpa() < 2.85f) {
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
    //TODO: nullptr exception here, change later
    public Student getSpecificStudents(int index, BTree.BTreeNode node) throws ExecutionControl.NotImplementedException {
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
    private ArrayList<Student> getAllStudents(BTree.BTreeNode node, ArrayList<Student> students) {
        if (node!= null && !node.isEmpty()) {
            for (BTree.BTreeNode childNode : node.getChildrenNode()) {
                getAllStudents(childNode, students);
            }
            for (Student student : node.getStudents()) {
                students.add(student);
            }

        }
        return students;
    }

    public Student findSpecificStudent(int index, BTree bTree) {
        counter = index-1;
        if (counter > bTree.size()-1) {
            throw new IndexOutOfBoundsException();
        }
        targetStudent = new Student();
        findSpecificStudent(bTree.getRootNode());
        return targetStudent;
    }

    private void findSpecificStudent(BTree.BTreeNode node) {
        int studentIndex, childrenIndex = 0;
        //while (studentIndex < )
        for (int i = 0; i < StudentInsertionHelper.getNotNullLength(node.getChildrenNode()); i++) {
            if (!node.isLeaf()) {
                findSpecificStudent(node.getChildrenNode()[i]);
            }
            if (i < StudentInsertionHelper.getNotNullLength(node.getStudents())) {
                //check if it is the element
                if (counter == 0) {
                    targetStudent = node.getStudents()[i];
                }
                counter--;
            }
        }



       // return null;
    }

}
