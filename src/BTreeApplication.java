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
        Student student1 = new Student("a", 822018452, 2.99f);
        Student student2 = new Student("b", 822018431, 3.10f);
        Student student3 = new Student("c", 822018455, 3.5f);
        Student student4 = new Student("d", 822018412, 2.80f);
        Student student5 = new Student("e", 822017897, 4.0f);
        Student student6 = new Student("f", 822017999, 2.74f);
        Student student7 = new Student("g", 822017832, 3.01f);
        Student student8 = new Student("h", 822017845, 3.99f);
        Student student9 = new Student("i", 822017878, 2.55f);
        Student student10 = new Student("j", 822017921, 4.0f);
        Student student11 = new Student("k", 822017785, 3.01f);
        Student student12 = new Student("l", 822017622, 4.0f);
        Student student13 = new Student("m", 822017545, 3.01f);
        Student student14 = new Student("n", 822017653, 2.11f);


        BTreeNode node = new BTreeNode();
        node = node.insertStudent(student1);
        node = node.insertStudent(student2);
        node = node.insertStudent(student3);
        node = node.insertStudent(student4);
        node = node.insertStudent(student5);
        node = node.insertStudent(student6);
        node = node.insertStudent(student7);
        node = node.insertStudent(student8);
        node = node.insertStudent(student9);
        node = node.insertStudent(student10);
        node = node.insertStudent(student11);
        node = node.insertStudent(student12);
        node = node.insertStudent(student13);
        node = node.insertStudent(student14);

        BTreePrinter printer = new BTreePrinter();
        printer.print(node);

        ArrayList<Student> studentOnProbation = new ArrayList<Student>();
        ArrayList<Student> studentWithGoodGPA = new ArrayList<Student>();
        StudentSearcher search = new StudentSearcher();
        studentOnProbation = search.getProbationStudents(node, studentOnProbation);
        studentWithGoodGPA = search.getHighGPAStudents(node, studentWithGoodGPA);
        System.out.println("test------------Students with 4.0 GPA: " + studentWithGoodGPA);
        studentWithGoodGPA.clear();
        System.out.println("test-----------Students on probation: " + studentOnProbation);
        studentOnProbation.clear();
        //int i = 4;
        //Student studentK = search.getSpecificStudents(i, node);
        //System.out.println("test----------------This is the " + i + " th element in the tree: " + studentK);




    }
}