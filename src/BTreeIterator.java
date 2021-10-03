import java.util.NoSuchElementException;
import java.util.Stack;

public class BTreeIterator {
    private BTree.BTreeNode nextNode;
    private Stack<BTree.BTreeNode> nodesBackTRoot;

    public BTreeIterator(BTree.BTreeNode rootNode) {
        nextNode = rootNode;
        Stack<BTree.BTreeNode> nodesBackTRoot = new Stack<>();
        // If no student inserted yet for the root, throw exception
        if (nextNode.isEmpty()) {
            throw new NoSuchElementException();
        }
        while (!nextNode.getChildrenNode()[0].isEmpty()) {
            nodesBackTRoot.push(nextNode.getChildrenNode()[0]);
            nextNode = nextNode.getChildrenNode()[0];
        }
    }
    //TODO: probably change the logic here
    public boolean hasNext() {
        return nextNode.isEmpty();
    }

    public BTree.BTreeNode next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        BTree.BTreeNode rightNode = nextNode;
        //if (nextNode.getChildrenNode()[1])
        return null;
    }

}
