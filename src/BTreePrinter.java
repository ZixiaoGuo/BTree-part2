import java.util.Arrays;

public class BTreePrinter {

    /**
     * Print the tree structure
     */
    public void print(BTreeNode node) {
        printNode(node, 0);
    }

    //TODO: This method need to be moved to other class
    private void printNode(BTreeNode node, int depth) {
        StringBuilder sb = new StringBuilder();
        for(int i = 1; i < depth; i++) {
            sb.append("     ");
        }
        if(depth > 0) {
            sb.append("--  ");
        }
        if(node != null) {
            sb.append(Arrays.asList(node.getStudents()));
            System.out.println(sb);  //TODO: need to change this line
            for(BTreeNode child : node.getChildrenNode()) {
                printNode(child, depth+1);
            }
        }

    }



}
