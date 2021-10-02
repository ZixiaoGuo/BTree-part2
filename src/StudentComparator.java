import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    public static int compareStudentNames (BTree.BTreeNode node, int indexOfStudentInNode, Student student) {
        //return node.getStudents().get(indexOfStudentInNode).getName().compareTo(student.getName());
        return node.getStudents()[indexOfStudentInNode].getName().compareTo(student.getName());
    }

    //Function that returns the length of array without counting null elements
    public static <T> int getNotNullLength(T[] arr){
        int count = 0;
        for(T element : arr)
            if (element != null)
                ++count;
        return count;
    }

    public static Student[] addStudentElement(
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
/*
        // Printing the original array
        System.out.println("Initial Array:\n"
                + Arrays.toString(arr));

        // Printing the updated array
        System.out.println("\nArray with " + element
                + " inserted at position "
                + position + ":\n"
                + Arrays.toString(arr));

 */
        return arr;
    }


    public static BTree.BTreeNode[] addElement(
            BTree.BTreeNode[] arr, BTree.BTreeNode element,
            int position)
    {
        // Converting array to ArrayList
        List<BTree.BTreeNode> list = new ArrayList<>(
                Arrays.asList(arr));

        // Adding the element at position
        //TODO: probably need to change position here
        list.add(position, element);

        // Converting the list back to array
        arr = list.toArray(arr);

        return arr;
    }

}
