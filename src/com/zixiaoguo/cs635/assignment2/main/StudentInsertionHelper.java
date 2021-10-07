package com.zixiaoguo.cs635.assignment2.main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Author: Zixiao Guo
 * RedId: 822029189
 * CS635 Assignment 2
 * Section 2
 * 10/5/2021
 *
 * This class is used to implement some helper functions to insert students
 */
public final class StudentInsertionHelper {
    private StudentInsertionHelper() {

    }

    /**
     * Compares student names based on lexicographical order
     * @param node the node containing students
     * @param indexOfStudentInNode  the specific index of student in a node
     * @param student another student object used to compare with the student in the node
     * @return  negative if student in the specific location is less than the student passed in, 0 if they are equal,
     * positive number otherwise
     */
    public static int compareStudentNames (BTree.BTreeNode node, int indexOfStudentInNode, Student student) {
        return node.getStudents()[indexOfStudentInNode].getName().compareTo(student.getName());
    }

    /**
     * Compares student RedIDs
     * @param node the node containing students
     * @param indexOfStudentInNode the specific index of student in a node
     * @param student another student object used to compare with the student in the node
     * @return negative if student in the specific location is less than the student passed in, 0 if they are equal,
     * positive number otherwise
     */
    public static int compareStudentRedID (BTree.BTreeNode node, int indexOfStudentInNode, Student student) {
        int result = node.getStudents()[indexOfStudentInNode].getRedId() - student.getRedId();
        return result;
    }

    /**
     * Returns the length of array without counting null elements in it
     * @param arr array passed in
     * @param <T>
     * @return integer length
     */
    public static <T> int getNotNullLength(T[] arr){
        int count = 0;
        for(T element : arr)
            if (element != null)
                ++count;
        return count;
    }


    /**
     * Insert student element into the specific location inside array
     * @param arr
     * @param element
     * @param position
     * @return the array after insertion
     */
    public static Student[] insertStudentElement(
            Student[] arr, Student element,
            int position)
    {
        // Converting array to ArrayList
        List<Student> list = new ArrayList<>(
                Arrays.asList(arr));

        // Adding the element at position
        //TODO: probably need to change position here
        list.add(position, element);

        // Converting the list back to array
        arr = list.toArray(arr);
        return arr;
    }

    /**
     * Insert child node to one of the btree node
     * @param arr
     * @param element
     * @param position
     * @return the children entry of one node after insertion
     */
    public static BTree.BTreeNode[] insertBtreeElement(
            BTree.BTreeNode[] arr, BTree.BTreeNode element,
            int position)
    {
        // Converting array to ArrayList
        List<BTree.BTreeNode> list = new ArrayList<>(
                Arrays.asList(arr));

        // Adding the element at position
        list.add(position, element);

        // Converting the list back to array
        arr = list.toArray(arr);

        return arr;
    }

}
