package com.zixiaoguo.cs635.assignment2.test;


import com.zixiaoguo.cs635.assignment2.main.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
/**
 * Author: Zixiao Guo
 * RedId: 822029189
 * CS635 Assignment 2
 * Section 2
 * 10/5/2021
 *
 * This is the class for testing
 */
public class BTreeTest {
    BTree bTree;

    Student student1 = new Student("a", 14, 2.99f);
    Student student2 = new Student("b", 13, 4.00f);
    Student student3 = new Student("c", 12, 3.5f);
    Student student4 = new Student("d", 11, 2.80f);


    @BeforeEach
    void setUp() {

    }

    @Test
    @DisplayName("Test for adding student")
    void testAddingStudents() {
        bTree = new BTree(new SortByName());
        assertEquals(bTree.add(student1), true);
        assertEquals(bTree.size(), 1);
        assertEquals(bTree.getRootNode().getStudents()[0], student1);
    }

    @Test
    @DisplayName("Test for toString method")
    void testToString() {
        bTree = new BTree(new SortByName());
        bTree.add(student1);

        String testString = student1.toString();
        String testString2 = bTree.toString();
        testString2 = testString2.substring(1, testString2.length()-1); //remove "[" and "]" in the string
        assertEquals(true, testString.equals(testString2));
    }

    @Test
    @DisplayName("Test for toArray method")
    void testToArray() {
        bTree = new BTree(new SortByName());
        bTree.add(student1);
        bTree.add(student2);
        bTree.add(student3);

        Student[] testArray = {student1, student2, student3};
        assertEquals(true, Arrays.equals(testArray, bTree.toArray()));
    }

    @Test
    @DisplayName("Test for iterator method")
    void testIterator() {
        bTree = new BTree(new SortByName());
        bTree.add(student1);
        bTree.add(student2);
        bTree.add(student3);

        Iterator it = bTree.iterator();
        for (int i = 1; i < bTree.size(); i++) {
            it.next();
        }

        assertEquals(true, it.next().toString().equals(student3.toString()));
        assertEquals(false, it.hasNext());
    }




    @Test
    @DisplayName("Test for sorting by name")
    void testSortByNameStrategy() {
        bTree = new BTree(new SortByName());
        bTree.add(student1);
        bTree.add(student2);
        bTree.add(student3);
        bTree.add(student4);
        ArrayList<String> testArr = new ArrayList<>();
        testArr.add("a");
        testArr.add("b");
        testArr.add("c");
        testArr.add("d");
        ArrayList<String> studentArr = new ArrayList<>();
        for (Object object: bTree) {
            Student student = (Student) object;
            studentArr.add(student.getName());
        }
        assertEquals(true, testArr.equals(studentArr));

    }

    @Test
    @DisplayName("Test for sorting by RedID")
    void testSortByRedIDStrategy() {
        bTree = new BTree(new SortByRedID());
        bTree.add(student1);
        bTree.add(student2);
        bTree.add(student3);
        bTree.add(student4);
        ArrayList<String> testArr = new ArrayList<>();
        testArr.add("d");
        testArr.add("c");
        testArr.add("b");
        testArr.add("a");
        ArrayList<String> studentArr = new ArrayList<>();
        for (Object object: bTree) {
            Student student = (Student) object;
            studentArr.add(student.getName());
        }
        assertEquals(true, testArr.equals(studentArr));

    }

    @Test
    @DisplayName("Test for getting size of BTree")
    void testBtreeSize() {
        bTree = new BTree(new SortByName());
        bTree.add(student1);
        bTree.add(student2);
        bTree.add(student3);
        bTree.add(student4);
        assertEquals(bTree.size(), 4);
    }

    @Test
    @DisplayName("Test for searching students with 4.0 GPA")
    void testSearchHighGPAStudent() {
        bTree = new BTree(new SortByName());
        bTree.add(student1);
        bTree.add(student2);
        bTree.add(student3);
        bTree.add(student4);
        StudentSearcher searcher = new StudentSearcher();
        Student testStudent = searcher.getHighGPAStudents(bTree.getRootNode(), new ArrayList<Student>()).get(0);
        assertEquals(true ,testStudent.getName().equals(student2.getName()));
    }

    @Test
    @DisplayName("Test for searching probation students")
    void testSearchProbationStudent() {
        bTree = new BTree(new SortByName());
        bTree.add(student1);
        bTree.add(student2);
        bTree.add(student3);
        bTree.add(student4);
        StudentSearcher searcher = new StudentSearcher();
        Student testStudent = searcher.getProbationStudents(bTree.getRootNode(), new ArrayList<Student>()).get(0);
        assertEquals(true ,testStudent.getName().equals(student4.getName()));
    }

    @Test
    @DisplayName("Test for searching specific students")
    void testSearchSpecificStudent() {
        bTree = new BTree(new SortByName());
        bTree.add(student1);
        bTree.add(student2);
        bTree.add(student3);
        bTree.add(student4);
        StudentSearcher searcher = new StudentSearcher();
        Student testStudent = searcher.getSpecificStudent(3, bTree);
        assertEquals(true ,testStudent.getName().equals(student3.getName()));
    }


}
