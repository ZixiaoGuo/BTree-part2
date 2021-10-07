package com.zixiaoguo.cs635.assignment2.main;

import jdk.jshell.spi.ExecutionControl;

import java.util.ArrayList;

/**
 * Author: Zixiao Guo
 * RedId: 822029189
 * CS635 Assignment 2
 * Section 2
 * 10/5/2021
 *
 * This is the driver class to com.zixiaoguo.cs635.assignment1.main.test the program
 */
public class BTreeApplication {

    public static void main(String[] args) throws ExecutionControl.NotImplementedException {
        testCase1();
    }

    public static void testCase1() throws ExecutionControl.NotImplementedException {
        Student student1 = new Student("a", 14, 2.99f);
        Student student2 = new Student("b", 13, 3.10f);
        Student student3 = new Student("c", 12, 3.5f);
        Student student4 = new Student("d", 11, 2.80f);
        Student student5 = new Student("e", 10, 4.0f);
        Student student6 = new Student("f", 9, 2.74f);
        Student student7 = new Student("g", 8, 3.01f);
        Student student8 = new Student("h", 7, 3.99f);
        Student student9 = new Student("i", 6, 2.55f);
        Student student10 = new Student("j", 5, 4.0f);
        Student student11 = new Student("k", 4, 3.01f);
        Student student12 = new Student("l", 3, 4.0f);
        Student student13 = new Student("m", 2, 3.01f);
        Student student14 = new Student("n", 1, 2.11f);

        BTree bTree = new BTree(new SortByName());  //you can change SortByName to SortByRedID for different sorting strategy
        bTree.add(student1);
        bTree.add(student2);
        bTree.add(student3);
        bTree.add(student4);
        bTree.add(student5);
        bTree.add(student6);
        bTree.add(student7);
        bTree.add(student8);
        bTree.add(student9);
        bTree.add(student10);
        bTree.add(student11);
        bTree.add(student12);
        bTree.add(student13);
        bTree.add(student14);
        BTree.BTreeNode node = bTree.getRootNode();

        BTreePrinter printer = new BTreePrinter();
        printer.print(bTree.getRootNode());
        System.out.println();

        ArrayList<Student> studentOnProbation = new ArrayList<Student>();
        ArrayList<Student> studentWithGoodGPA = new ArrayList<Student>();
        StudentSearcher search = new StudentSearcher();
        studentOnProbation = search.getProbationStudents(node, studentOnProbation);
        studentWithGoodGPA = search.getHighGPAStudents(node, studentWithGoodGPA);
        System.out.println("Students with 4.0 GPA: " + studentWithGoodGPA);
        studentWithGoodGPA.clear();
        System.out.println("Students on probation: " + studentOnProbation);
        studentOnProbation.clear();
        int index = 14;
        Student specificStudent = search.getSpecificStudent(index, bTree);
        System.out.println(index + " th student inside tree " + specificStudent.toString());
        System.out.println();
        System.out.println("Testing reversed internal iterator below: ----------------------");
        bTree.forEach(System.out::println);
        System.out.println();
        System.out.println("Testing external iterator below: ----------------------");
        System.out.println(bTree.toString());
        Student[] students = bTree.toArray();
        for (Student student: students) {
            System.out.println(student);
        }

    }
}