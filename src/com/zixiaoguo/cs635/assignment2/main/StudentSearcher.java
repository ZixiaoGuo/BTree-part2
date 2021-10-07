package com.zixiaoguo.cs635.assignment2.main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
/**
 * Author: Zixiao Guo
 * RedId: 822029189
 * CS635 Assignment 2
 * Section 2
 * 10/5/2021
 *
 * This is the class to help searching students
 */

public class StudentSearcher {

    private int counter;    //Variable used in findSpecificStudent method to act as a global counter
    private Student targetStudent;

    /**
     * Search for student with 4.0 GPA
     * @param node root node of com.zixiaoguo.cs635.assignment1.main.BTree
     * @param students Array to store students found
     * @return Arraylist of students with 4.0 GPA
     */
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

    /**
     * Search for student under probation
     * @param node root node of com.zixiaoguo.cs635.assignment1.main.BTree
     * @param studentsOnProbation Array to store students found
     * @return Arraylist of students below 2.85 GPA
     */
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
     * @param bTree  reference of bTree
     * @return  student found
     *
     */
    public Student getSpecificStudent(int index, BTree bTree) {
        if (index > bTree.size()) {
            throw new IndexOutOfBoundsException();
        }
        counter = index-1;
        if (counter > bTree.size()-1) {
            throw new IndexOutOfBoundsException();
        }
        targetStudent = new Student();
        getSpecificStudent(bTree.getRootNode());
        return targetStudent;
    }

    private void getSpecificStudent(BTree.BTreeNode node) {

        for (int i = 0; i < StudentInsertionHelper.getNotNullLength(node.getChildrenNode()); i++) {
            if (!node.isLeaf()) {
                getSpecificStudent(node.getChildrenNode()[i]);
            }
            if (i < StudentInsertionHelper.getNotNullLength(node.getStudents())) {
                //check if it is the element
                if (counter == 0) {
                    targetStudent = node.getStudents()[i];
                }
                counter--;
            }
        }
    }


}
