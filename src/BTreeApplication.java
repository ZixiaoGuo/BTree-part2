import jdk.jshell.spi.ExecutionControl;

import java.util.ArrayList;

/**
 * Author: Zixiao Guo
 * RedId: 822029189
 * CS635 Assignment 1
 * Section 2
 * 9/7/2021
 *
 * This is the driver class to test the program
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

        BTree bTree = new BTree();
        BTree.BTreeNode node = bTree.getRootNode();
        bTree.insertStudent(student1);
        bTree.insertStudent(student2);
        node = bTree.getRootNode();
        bTree.insertStudent(student3);
        node = bTree.getRootNode();
        System.out.println(bTree.size());    //debug line
        bTree.insertStudent(student4);
        node = bTree.getRootNode();
        bTree.insertStudent(student5);
        bTree.insertStudent(student6);
        bTree.insertStudent(student7);
        bTree.insertStudent(student8);
        bTree.insertStudent(student9);
        bTree.insertStudent(student10);
        bTree.insertStudent(student11);
        bTree.insertStudent(student12);
        bTree.insertStudent(student13);
        bTree.insertStudent(student14);
        System.out.println(bTree.size());    //debug line
        node = bTree.getRootNode();

        BTreePrinter printer = new BTreePrinter();
        printer.print(bTree.getRootNode());

        ArrayList<Student> studentOnProbation = new ArrayList<Student>();
        ArrayList<Student> studentWithGoodGPA = new ArrayList<Student>();
        StudentSearcher search = new StudentSearcher();
        studentOnProbation = search.getProbationStudents(node, studentOnProbation);
        studentWithGoodGPA = search.getHighGPAStudents(node, studentWithGoodGPA);
        System.out.println("test------------Students with 4.0 GPA: " + studentWithGoodGPA);
        studentWithGoodGPA.clear();
        System.out.println("test-----------Students on probation: " + studentOnProbation);
        studentOnProbation.clear();
        Student specificStudent = search.findSpecificStudent(14, bTree);
        System.out.println(specificStudent.toString() + "+++++++++++++++++++++++++++++++");
        //int i = 4;
        //Student studentK = search.getSpecificStudents(i, node);
        //System.out.println("test----------------This is the " + i + " th element in the tree: " + studentK);




    }
}